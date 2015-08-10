package com.coul.customer.constants;

/**
 * 客户模块的常量
 * Created by linzq on 2014/11/19.
 */
public class CustomerConstants {
   
    
    /**
     * 状态常量(有效).
     */
    public static String ENTIRY_STATUS_CD_ACTIVE  = "1000";
    
    /**
     * 状态常量(失效).
     */
    public static String ENTIRY_STATUS_CD_INVALID = "1100";

    /*
     * 客户信息常量
     */
   public static final String CUST_DEFAULT_PWD="123456"; 
    
   public static final int CUST_NAME_MAXlENG=80;
   public static final int CUST_ADDRESS_MAXlENG=80;
   
   
   /**
    * 客户表序列ID.
    */
   public static final String SEQ_CUST_ID = "SEQ_CUST_ID";
   /**
    * 证件表序列ID.
    */
   public static final String SEQ_CERTIFICTION_ID = "SEQ_CERTIFICTION_ID";
   /**
    * 联系人表序列ID.
    */
   public static final String SEQ_CONTACT_ID = "SEQ_CONTACT_ID";
   /**
    * 客户属性表序列ID.
    */
   public static final String SEQ_CUST_ATTR_ID = "SEQ_CUST_ATTR_ID";
   
   /*
    *账户表序列id 
    */
   public static final String SEQ_ACCOUNT_ID = "SEQ_ACCOUNT_ID";
   
   /*
    * 支付方式表序列id
    */
   public static final String SEQ_PAYMENTPLAN_ID = "SEQ_PAYMENTPLAN_ID";

   /*
    * 单据主表序列定义.
    */
   
   public static final String SEQ_BILL_ID = "SEQ_BILL_ID";
   /*
    *  单据明细表序列定义.
    */
   public static final String SEQ_BILLDTL_ID = "SEQ_BILLDTL_ID";
   /*
    *投递信息表序列定义.
    */
   public static final String SEQ_POSTADDR_ID = "SEQ_POSTADDR_ID";
  
   /*
    * 客户订单状态-已经归档
    */
   public static final String CUSTOMER_ORDER_STAT_ARCHIVED = "301200";
   
   /*
    * 客户订单状态-已经收费
    */
   public static final String CUSTOMER_ORDER_STAT_FARED = "101500";

}
