package cn.kejso.Config;

public class Config {
	
	//mybatis配置文件
	public static final  String   Mybatis_config="mybatis_config.xml"; 
	
	//每插入多少条记录打印info
	public static final  int      Inertinfo_per_number=10;
	
	//爬虫运行结束后的分割线
	public static final  String   Spider_Info_line="################################";
	
	//mysql info
	public static final  String   Insert_info=" have insert {} units.";
	
	//Mybatis插入语句
	public static final  String   Insert_statement="SqlMapper.TemplateMapper.insertEntity";
	
	// 查记录条数语句
	public static final String TheRecordNumber_statement = "SqlMapper.TemplateMapper.getRecordNum";
		
	//Mybatis 获得最后一条记录的id
	public static final  String   TheLastId_statement="SqlMapper.TemplateMapper.getLastId";
	
	//Mybatis 获得最后一条记录的某一属性
	public static final  String   TheLastRecordField_statement="SqlMapper.TemplateMapper.getLastRecordField";

	//Mybatis 获得具有特定属性记录的ID
	public static final  String   TheCertainId_statement="SqlMapper.TemplateMapper.getCertainId";
	
	//Mybatis 获得两个数据域的差集
	public static final  String   TheDeltaField_statement="SqlMapper.TemplateMapper.getDeltaField";
	
	//新建表
	public static final  String   CreateTable_statement="SqlMapper.TemplateMapper.createNewTable";
	
	//获得target url语句
	public static final  String   AllUrl_statement="SqlMapper.TemplateMapper.getAllUrl";
	
	//获得target url语句
	public static final  String   PartUrl_statement="SqlMapper.TemplateMapper.getPartUrl";
	
	// 清空表语句
	public static final String TruncateTable_statement = "SqlMapper.TemplateMapper.truncateTable";

	// 删除表语句
	public static final String DropTable_statement = "SqlMapper.TemplateMapper.dropTable";
	
	//pipeline
	//实体标识
	public static final  String   PipeLine_Entity="storedEntity";
	//存储类型
	public static final  String   PipeLine_Type="type";
	public static final  String   PipeLine_TypeList="typeList";
	public static final  String   PipeLine_TypeOne="typeOne";
	
	//spider缓存
	public static final  String   Spider_CacheDir="SpiderScheduler/cache/";
	//error url
	public static final  String   Spider_ErrorDir="SpiderScheduler/error/";
	//mysql记录缓存
	public static final  String   Spider_SQLCacheDir="MysqlCache/";
	
	//useragent
	public static final  String   Spider_userAgent="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; GTB5; .NET CLR 2.0.50727; CIBA";
	
	public static final  String   Spider_Baidu_userAgent="Baiduspider+(+http://www.baidu.com/search/spider.htm)";
	
	public static final  String   Spider_Google_userAgent1="Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
	public static final  String   Spider_Google_userAgent2="Googlebot/2.1 (+http://www.googlebot.com/bot.html)";
	
	//MimvpProxy
	public static final  String   mimvpProxyDir="configs/MimvpProxy/";
	public static final  String   mimvpProxyAccount="account.xml";
}
