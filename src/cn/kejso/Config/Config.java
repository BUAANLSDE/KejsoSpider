package cn.kejso.Config;

public class Config {
	
	//mybatis�����ļ�
	public static final  String   Mybatis_config="mybatis_config.xml"; 
	
	//mysql info
	public static final  String   Insert_info=" insert {} .";
	
	//Mybatis �������
	public static final  String   Insert_statement="SqlMapper.#Mapper.insert#";
	
	//Mybatis select-all���
	public static final  String   SelectAll_statement="SqlMapper.#Mapper.getAll#";
	
	//�Ƿ����һ�ű�
	public static final  String   ClassifyUrl_existTable="SqlMapper.ClassifyUrlMapper.existTable";
	//�����±�
	public static final  String   ClassifyUrl_createTable="SqlMapper.ClassifyUrlMapper.createNewTable";
	
	//��ȡtarget url���
	public static final  String   AllUrl_statement="SqlMapper.#Mapper.getAllUrl";
	
	
	//ģ�������ڵİ�
	public static final  String   StoredEntity="cn.kejso.StoredEntity";
	
	
	//pipeline��ʶ
	//�洢ʵ��
	public static final  String   PipeLine_Entity="storedEntity";
	//�洢����
	public static final  String   PipeLine_Type="type";
	public static final  String   PipeLine_TypeList="typeList";
	public static final  String   PipeLine_TypeOne="typeOne";
	
	
	public static final  String   Spider_CacheDir="SpiderScheduler/cache/";
	
}
