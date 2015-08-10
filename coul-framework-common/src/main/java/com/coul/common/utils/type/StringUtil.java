/**
 * Created by ZengZhen on 2006-12-25
 */
package com.coul.common.utils.type;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ZengZhen
 *
 */
public class  StringUtil 
{
  static String valueOfEmptyString="空";

  /**区域与区域代码*/
   final static Map<Integer,String> areaMap = new HashMap<Integer,String>();
   
   /**默认设置会员等级（自定义） 数值大等级越要*/
   final static Map<Integer,String> cardLevelMap = new HashMap<Integer,String>();
  
  /**证件类型常量*/
  
  public final static int CERTIFI_TYPE_JMSFZ=10;//居民身份证
  
  private static final Log logger = LogFactory.getLog(StringUtil.class);
  
  

  /**
   * 新字符串转换方法,区别在于,对象为空时不返回"null",而是其它,如""
   * @param o
   * @return
   */
  static public String toString(Object obj)
  {
    if (obj == null)
      return valueOfEmptyString;
    return obj.toString();
  }
  /**
   * 新字符串转换方法,提供"null"的替代文本
   * @param obj
   * @param emptyString "null"的替代文本
   * @return
   */
  static public String toString(Object obj,String emptyString)
  {
    if (obj == null || obj.equals("") || obj.equals("null"))
      return emptyString;
    return obj.toString();
  }
  /**
   * 设置null的toString值
   * @param s
   */
  static void setValueOfEmptyString(String s)
  {
    valueOfEmptyString=s;
  }
  /**
   * 判断字符串是否为空(null或"")
   * @param str
   * @return
   */
  static public boolean isEmptyString(String str)
  {
    return str==null||str.trim().equals("")||str.trim().equals("null");
  }
  /**
   * 比较,考虑了null
   * @param a
   * @param b
   * @return
   */
  static public boolean equals(String a,String b)
  {
    return toString(a,"").equals(toString(b,""));
  }
  
   /**
	* 方法功能:处理隐藏身份证号码(3421271969****3617)
	* @param cardnumber
	* @author: LAIYONGMIN@FFCS.CN
	* @修改记录： 
	* ==============================================================<br>
	* 日期 2012-11-9 上午11:04:49
	* ==============================================================<br>
	*/
   public static String hiddenCardnumber(String cardnumber){
	  if(!isEmptyString(cardnumber)){
		  if(cardnumber.length()==18){
			  cardnumber = cardnumber.substring(0, 10)+"****"+cardnumber.substring(14, cardnumber.length());
		  }
	  }
	  return cardnumber;
	  
  }
   




   /**
    * 获取区域代码
    * @param areaName;
    * @return int areaCode;
    */
   @SuppressWarnings("unchecked")
   public static int getAreaCodeByAreaname(String areaName){
	   Iterator<?> it = areaMap.entrySet().iterator();
	   while(it.hasNext()){
		Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)it.next();
		   if(entry.getValue().equals(areaName)){
			   return entry.getKey();
		   }
	   }
	   return 0;
   }
   
   /**
    * 获取性别编号
    */
   public static int getSexCode(String sex){
	   if(sex.equals("男")){
		   return 1;
	   }else if(sex.equals("女")){
		   return 0;
	   }else{
		   return 2;
	   }
   }
   
   /**
    *获取等级数值
    *@param cardLevel 等级名称
    *@return 等级数字
    */
   public static int getCardLevelNum(String cardLevel){
	   Iterator<?> it = cardLevelMap.entrySet().iterator();
	   while(it.hasNext()){
		Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)it.next();
		   if(entry.getValue().equals(cardLevel)){
			   return entry.getKey();
		   }
	   }
	   return 0;
   }
   
   /**
	* 方法功能:生成可执行的存储过程
	* @param procedureName：存储过程名称；length:变量长度;
	* @author: LAIYONGMIN@FFCS.CN
	* @修改记录： 
	* ==============================================================<br>
	* 日期 2013-1-10 下午02:03:31
	* ==============================================================<br>
	*/
	public static String generationExcuteProcedure(String procedureName,int length){
		
		try{
			StringBuffer proce = new StringBuffer();
			proce.append("{Call ");
			proce.append(procedureName.trim());
			
			if(length >0){
				proce.append("(");
				for(int i=0;i<length;i++){
					proce.append("?");
					if(i != length-1){
						proce.append(",");
					}
				}
				proce.append(")");
			}
			proce.append("}");
			logger.debug("+++++++++++++生成的存储过程"+proce.toString());
			return proce.toString();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/**
	 *
	 * 获取webroot 路径
	 * pathName:必须为web-inf 同级目录
	 */
	public static String getWebRootByServlet(HttpServletRequest request,String pathName){
		return request.getSession().getServletContext().getRealPath(File.separator+pathName);
	}
   
}
