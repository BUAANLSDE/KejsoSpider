package cn.kejso.Tool;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.kejso.Config.Config;
import cn.kejso.Template.ListAndContentTemplate;

public class SpiderUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SpiderUtil.class);
	private static SqlSessionFactory sessionFactory = null;
	
	//����mybatis�����ļ�
	static{
		Reader reader=null;
		try {
			reader = Resources.getResourceAsReader(Config.Mybatis_config);
		} catch (IOException e) {
			logger.error("��ȡmybatis�����ļ�����!");
			e.printStackTrace();
		}
		sessionFactory=new SqlSessionFactoryBuilder().build(reader);
	}
	
	public static SqlSession getSession()
	{
		return sessionFactory.openSession();
	}
	
	//���mybatis �������
	public static String  getInsertStatement(String moban)
	{
		String insert=Config.Insert_statement.replaceAll("#",moban);
		return insert;
		
	}
	
	//���mybatis targeturl���
	public static String  getAllurlStatement(String moban)
	{
		String insert=Config.AllUrl_statement.replaceAll("#",moban);
		return insert;
	}
	
	
	//���mysql��ʾ��Ϣ
	public static String  getMysqlinsertInfo(String moban)
	{
		String info=moban+Config.Insert_info;
		
		return info;
	}
	
	//�����б�ҳ
	public static List<String>  getListUrls(String site,int start,int end)
	{
		List<String>  starturls=new ArrayList<String>();
		for(int i=start;i<=end;i++)
		{
			starturls.add(site.replaceAll("#", String.valueOf(i)));
		}
		return starturls;
	}
	
	//����mapӳ���ֶ�
	public static List<String>  getMapFields(String field)
	{
		List<String>  fields=new ArrayList<String>();
		
		String[] temp=field.split("#");
		for(String one:temp)
		{
			if(!one.equals(""))
				fields.add(one);
			else
				fields.add(null);
		}
		System.out.println(fields.toString());
		return fields;
	}
	
	//��ȡָ�����е�url
	public static List<String> getTargetUrls(ListAndContentTemplate template)
	{
		SqlSession session=getSession();
		
		String table=template.getListconfig().getTablename();
		String statement=getAllurlStatement(template.getListconfig().getTablename());
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("tablename",table);
		
		List<String> urls=session.selectList(statement, map);
		return urls;
	}
	
	
	public static void main(String[] args) {
		//ListAndContentTemplate template=TemplateConstructor.getListAndContentTemplate("configs\\wanfangpaper.xml");
		//SpiderUtil.getTargetUrls(template);
		
		String field="library#titlenumber##department#level##timeline#promulgation#materail#contentclass";
		SpiderUtil.getMapFields(field);
	}
}
