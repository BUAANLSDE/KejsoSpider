package cn.kejso.Spider;

import java.util.ArrayList;
import java.util.List;

import cn.kejso.Template.ListAndContentTemplate;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

//���̻���������
public class SpiderChain {
	
	private List<Spider> spiderqueue=new ArrayList<Spider>();
	
	//�����������
	public SpiderChain AddSpiderNode(Spider spider)
	{
		return this;
	}
	
	public SpiderChain AddSpiderNode(PageProcessor pageprocessor,ListAndContentTemplate template)
	{
		return this;
	}
	
	
}
