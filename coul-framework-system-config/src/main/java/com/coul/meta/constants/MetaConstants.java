package com.coul.meta.constants;

/**
 * Created by linzq on 2014/11/17.
 */
public class MetaConstants {
    /**
     * DAT 数据驱动类
     */
    public static final String RULE_EXE_TYPE_DATA          = "DAT";                   // 数据驱动类
                                                                                       
    /**
     * BUS 规则引擎类
     */
    public static final String RULE_EXE_TYPE_ENG           = "BUS";                   // 规则引擎类
                                                                                       
    /**
     * JVA JAVA插件类
     */
    public static final String RULE_EXE_TYPE_JAVA          = "JVA";                   // JAVA插件类
                                                                                       
    /**
     * SQL SQL插件类
     */
    public static final String RULE_EXE_TYPE_SQL           = "SQL";                   // SQL插件类
                                                                                       
    /**
     * JS JScript插件类
     */
    public static final String RULE_EXE_TYPE_JS            = "JS";                    // JScript插件类
                                                                                       
    /**
     * groovy 动态语言插件类
     */
    public static final String RULE_EXE_TYPE_GROOVY        = "GVY";                   // groovy插件类
    /**
     * 录入未实现规则。
     */
    public static final String RULE_EXE_TYPE_UNIMPLEMENT   = "NVL";                   // groovy插件类
    /**
     * 开关规则类.
     */
    public static final String RULE_EXE_TYPE_SWITCH_CONFIG = "SWI";                   // 开关规则类
    /**
     * BeanShell 脚本语言。
     */
    public static final String RULE_EXE_TYPE_BEANSHELL     = "BSH";
    /**
     * SQL 执行器的spring bean 定义
     */
    public static final String EXE_BEAN_SQL                = "simpleSQLRuler";
    /**
     * BeanShell 执行器的spring bean 定义
     */
    public static final String EXE_BEAN_BEAN_SHELL         = "beanShellJavaCodeRuler";
    /**
     * SQL 执行器的spring bean 定义
     */
    public static final String EXE_BEAN_JS                 = "simpleJSRuler";
    
    /**
     * SQL 执行器的spring bean 定义
     */
    public static final String EXE_BEAN_ENG                = "simpleDroolsRuler";
    
    /**
     * SQL 执行器的spring bean 定义
     */
    public static final String EXE_BEAN_GROOVY             = "simpleGroovyRuler";
    /**
     * SQL 执行器的spring bean 定义
     */
    public static final String EXE_BEAN_SWI                = "switchConfigRuler";
    
    /**
     * statusCD  是否在用
     */
    public static final String META_STATUS_CD              = "1000";
    
    //失效状态
    public static final String META_STATUS_CD_EXP          = "1100";
    
    /**
     * 树节点状态
     */
    public static String       TREE_NODE_STATE_COLSE       = "closed";
    public static String       TREE_NODE_STATE_OPEN        = "open";
    
    /**
     * 自定义树的属性_节点类型
     */
    public static String       TREE_NODE_TYPE_CATALOG      = "CATALOG";
    public static String       TREE_NODE_TYPE_PROD         = "PRODUCT_CATALOG";
    public static String       TREE_NODE_TYPE_OFFER        = "OFFER_CATALOG";
    
    /**
     * 自定义树的类型
     */
    public static long         TREE_TYPE_PROD              = 1;
    public static long         TREE_TYPE_OFFER             = 2;
    
    /**
     * 账目项类型属性
     */
    public static String       ACCT_ITEM_TYPE_JAVA_CODE    = "acctItemType";
    public static String       ACCT_ITEM_TYPE_CLASS_CODE   = "AcctItemType";
    
    public static String       ATTR_SPEC_CLASSCODE         = "AttrSpec";
    public static String       ATTR_TYPE_JAVA_CODE         = "attrType";
    
    public static String       ATTR_VALUE_CLASSCODE        = "AttrValue";
    public static String       STATUS_CD_JAVACODE          = "statusCd";
    
    public static String       SERVICE_CLASSCODE           = "Service";
    public static String       MANAGEGRAD_JAVACODE         = "manageGrade";
    public static String       CHANNELMANAGEGRADE_JAVACODE = "channelManageGrade";
    
    /**
     * attrSpec类型常量
     * .
     */
    public static final String ATTR_SPEC_NULLABLE          = "0";
    public static final String ATTR_SPEC_FALSE             = "0";
    public static final String ATTR_SPEC_TRUE              = "1";
    
    /**
     * attrType
     * T1 	输入类型 
     * T2	关联类型
     * T3	选择类型（枚举）
     * T4	自动编码类型
     * T5	内存类型
     * .
     */
    public static final String INPUT_ATTR                  = "T1";
    public static final String RELA_ATTR                   = "T2";
    public static final String SELECT_ATTR                 = "T3";
    public static final String CODER_ATTR                  = "T4";
    public static final String MEMORY_ATTR                 = "T5";
    
    /**
     * attrValueType
     * C	字符类型
     * T	时间类型
     * D	日期类型
     * F	浮点数类型
     * L	双精度类型
     * N	整型
     * B	Blob
     * .
     */
    public static final char   CHAR_ATTR                   = 'C';
    public static final char   TIME_ATTR                   = 'T';
    public static final char   DATE_ATTR                   = 'D';
    public static final char   FLOAT_ATTR                  = 'F';
    public static final char   LONG_ATTR                   = 'L';
    public static final char   INT_ATTR                    = 'N';
    public static final char   BLOB_ATTR                   = 'B';
    
    /**
     * 属性关联类型定义
     * pk 主键
     * .
     */
    public static final String ATTR_SPEC_CNS_TYPE_PK       = "PK";                    // 主键属性
                                                                                       
    /**
     *自定义 数据同步的5种状态
     */
    public static final String SYNC_STATUS_ADD             = "1001";                  //新增
    public static final String SYNC_STATUS_PUBLISHING      = "2001";                  //待发布
    public static final String SYNC_STATUS_PUBLISHED       = "3001";                  //已发布
    public static final String SYNC_STATUS_ERROR           = "4001";                  //新增
    public static final String SYNC_STATUS_FAILURE         = "5001";                  //失效
                                                                                       
    /**
     * CRM业务实体基类
     */
    public static final String CRM_ENTITY_IMPL             = "CrmEntityImpl";
    
    /* 定义服务路由常量.*/
    /**
     * 渠道管理-全渠道开放. 
     */
    public static final String CHANNEL_MANAGE_PRIV_ALL		= "1000";
    /**
     * 渠道管理-渠道授权
     */
    public static final String CHANNEL_MANAGER_PRIV_AUTH	= "1100";
    /**
     * 管控级别-集团管控.
     */
    public static final String MANAGE_GRADE_GROUP			= "1000";
    /**
     * 管控级别-肾内管控.
     */
    public static final String MANAGE_GRADE_PROVINCE		= "1100";
    
    
}
