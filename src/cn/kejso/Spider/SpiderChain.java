package cn.kejso.Spider;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kejso.Config.Config;
import cn.kejso.Pipeline.MysqlPipeline;
import cn.kejso.Spider.Control.SpiderContainer;
import cn.kejso.Spider.SpiderHandler.BasicTableHandler;
import cn.kejso.Template.SpiderConf;
import cn.kejso.Tool.SqlUtil;
import us.codecraft.webmagic.Spider;

//one.getStatus()
//one.isExitWhenComplete()
//one.notify();
//one.setEmptySleepTime(emptySleepTime);

//流程化的爬虫链处理，前后顺序启动
public class SpiderChain {

	private static Logger logger = LoggerFactory.getLogger(SpiderChain.class);

	private List<SpiderContainer> spiderqueue = new ArrayList<SpiderContainer>();

	private String chainname = "AnonymousSpiderChain";

	public String getChainname() {
		return chainname;
	}

	public void setChainname(String chainname) {
		this.chainname = chainname;
	}

	// constructor
	public SpiderChain() {

	}

	public SpiderChain(String name) {
		this.chainname = name;
	}

	// 添加爬虫节点
	public SpiderChain AddSpiderNode(SpiderContainer spider) {
		spiderqueue.add(spider);
		return this;
	}

	// 去除爬虫节点
	public SpiderChain RemoveSpiderNode(SpiderContainer spider) {
		spiderqueue.remove(spider);
		return this;
	}

	// 启动爬虫队列
	public void startSpiders(boolean chain) {
		logger.info("Start SpiderChain {} .", chainname);

		long chainstart = System.currentTimeMillis();

		// true,默认顺序启动
		if (chain) {

			for (SpiderContainer container : spiderqueue) {
				while (true) {
					long start = System.currentTimeMillis();
					logger.info(container.getName() + " start ...");

					SpiderConf currentconf = container.getTemplate();

					// before-table-handler
					
					if (currentconf.getBeforehandler() != null && !currentconf.getBeforehandler().equals("")) {
						logger.info("数据表前置处理...");
						try {
							Class handlerclass = Class.forName(currentconf.getBeforehandler());
							BasicTableHandler handler = (BasicTableHandler) handlerclass.newInstance();
							handler.handler(currentconf.getConfig().getTablename());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
							logger.error("数据表前置处理失败。");
							e.printStackTrace();
						}

					}
					
					Spider current = null;
					if (!container.isStart()) {
						// 如果是首次启动
						container.setStart();
						current = container.getSpider();
						container.AddgetStartUrlHandler(BuildSpider.getStartUrlHandler(container.getTemplate(), false));
					} else if (container.continueCycle() && SqlUtil.hasRetryItem(container.getTemplate())) {
						// 创建一个新的实例，因为之前的实例无法导入抓取失败的URL
						container.minusCycleTimes();
						current = BuildSpider.getSpider(container.getTemplate());
						container.AddgetStartUrlHandler(BuildSpider.getStartUrlHandler(container.getTemplate(), true));
					} else {
						//离开循环
						break;
					}
					
					// 添加初始序列、清空临时表并启动爬虫 
					current.startUrls(container.getStartUrls());
					SqlUtil.cleanTempTable(container.getTemplate());
					current.run();

					// after-table-handler
					
					if (currentconf.getAfterhandler() != null && !currentconf.getAfterhandler().equals("")) {
						logger.info("数据表后置处理...");
						try {
							Class handlerclass = Class.forName(currentconf.getAfterhandler());
							BasicTableHandler handler = (BasicTableHandler) handlerclass.newInstance();
							handler.handler(currentconf.getConfig().getTablename());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
							logger.error("数据表后置处理失败。");
							e.printStackTrace();
						}

					}

					// 如果爬虫没有停止
					logger.info("爬虫状态: " + current.getStatus().toString() + " .");

					logger.info(container.getName() + " end . Cost time:{}秒",
							(System.currentTimeMillis() - start) / 1000.0);
					logger.info("下载页面数 : {} .", current.getPageCount());
					logger.info(Config.Spider_Info_line);
				}
				// 因为Waiting for table metadata lock而暂时禁用drop table
				// SqlUtil.deleteTempTable(container.getTemplate());
			}
			
		} else {
			// 异步启动
			for (SpiderContainer container : spiderqueue) {
				Spider current = container.getSpider();
				current.startUrls(container.getStartUrls()).runAsync();
				;
			}
		}

		logger.info(chainname + " end .Total Cost time:{}秒", (System.currentTimeMillis() - chainstart) / 1000.0);

	}

	// 启动单个spider
	public static void startSpider(SpiderContainer container) {
		long start = System.currentTimeMillis();
		logger.info(container.getName() + " start ...");
		// 添加url
		Spider current = container.getSpider();
		current.startUrls(container.getStartUrls()).run();

		// 将本次更新记录记录到cache中
		SqlUtil.PrintPositionToCache(container.getTemplate());
		// 如果爬虫没有停止
		logger.info("爬虫状态: " + current.getStatus().toString() + " .");

		logger.info(container.getName() + " end . Cost time:{}秒", (System.currentTimeMillis() - start) / 1000.0);
		logger.info("下载页面数 : {} .", current.getPageCount());
		logger.info(Config.Spider_Info_line);
	}

}
