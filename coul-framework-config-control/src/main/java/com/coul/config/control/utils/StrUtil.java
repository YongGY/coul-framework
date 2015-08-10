package com.coul.config.control.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class StrUtil {


	/**
	 * 函数作用：判断字符串是否全为数字 如果是返回true，否则返回false
	 * 
	 * @param str
	 * @return 修改人:舒才良 2012-02-14
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Log log
	 */
	private final static Log log = LogFactory.getLog(StrUtil.class);

	/**
	 * 将Unicode码字符串转为为GBK码
	 * 
	 * @param strIn
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录：
	 *  ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *  ==============================================================<br>
	 */
	public static String GBToUnicode(String strIn) {
		String strOut = null;

		if (strIn == null || (strIn.trim()).equals("")) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("GBK");
			strOut = new String(b, "ISO8859_1");
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 将GBK码转换为Unicode码
	 * 
	 * @param strIn
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String unicodeToGB(String strIn) {
		String strOut = null;

		if (strIn == null || (strIn.trim()).equals("")) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("ISO8859_1");
			strOut = new String(b, "GBK");
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * UTF-8编码 字符串转 8859_1编码 字符串
	 * 
	 * @param strIn
	 * @return
	 * @author: Liuzhuangfei
	 * @修改记录： ==============================================================<br>
	 *        日期:Mar 8, 2010 Liuzhuangfei 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String utf8ToUnicode(String strIn) {
		String strOut = null;

		if (strIn == null || (strIn.trim()).equals("")) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("UTF-8");
			strOut = new String(b, "8859_1");
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 8859_1编码 字符串转 UTF-8编码 字符串
	 * 
	 * @param strIn
	 * @return
	 * @author: Liuzhuangfei
	 * @修改记录： ==============================================================<br>
	 *        日期:Mar 8, 2010 Liuzhuangfei 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String unicodeToUtf8(String strIn) {
		String strOut = null;

		if (strIn == null || (strIn.trim()).equals("")) {
			return strIn;
		}
		try {
			byte[] b = strIn.getBytes("8859_1");
			strOut = new String(b, "UTF-8");
		} catch (Exception e) {
		}
		return strOut;
	}

	/**
	 * 字符串编码类型转换
	 * 
	 * @param str
	 *            待转换的字符串
	 * @param oldCharset
	 *            待转换的字符串的编码类型
	 * @param newCharset
	 *            新的编码类型
	 * @return 转换成新编码类型的字符串
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String encode(String str, String oldCharset, String newCharset) {
		if (str == null) {
			return str;
		}
		String newStr = null;
		try {
			newStr = new String(str.getBytes(oldCharset), newCharset);
		} catch (Exception e) {
		}
		return newStr;

	}

	/**
	 * 将以sgn为分隔符的字符串转化为数组
	 * 
	 * @param str
	 *            String
	 * @param sgn
	 *            String
	 * @return String[]
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2004-11-4 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String[] split(String str, String sgn) {
		String[] returnValue = null;
		if (!strnull(str).equals("")) {
			Vector vectors = new Vector();
			int i = str.indexOf(sgn);
			String tempStr = "";
			for (; i >= 0; i = str.indexOf(sgn)) {
				tempStr = str.substring(0, i);
				str = str.substring(i + 1);
				vectors.addElement(tempStr);
			}
			if (!str.equalsIgnoreCase("")) {
				vectors.addElement(str);
			}
			returnValue = new String[vectors.size()];
			for (i = 0; i < vectors.size(); i++) {
				returnValue[i] = (String) vectors.get(i);
				returnValue[i] = returnValue[i].trim();
			}
		}
		return returnValue;
	}

	/**
	 * 把数组转化为字符串
	 * 
	 * @param array
	 *            字符串数组
	 * @param split
	 *            分割字符串
	 * @return string whose format is like "1,2,3"
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String arrayToStr(String[] array, String split) {
		if (array == null || array.length < 1) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(split);
			}
			sb.append(strnull(array[i]));
		}
		return sb.toString();

	}

	/**
	 * @param array
	 *            String[]
	 * @param split
	 *            String
	 * @return string whose format like " '1','2','3'"
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String arrayToStrWithStr(String[] array, String split) {
		return arrayToStrWithStr(array, split, "0");

	}

	/**
	 * @param array
	 *            String[]
	 * @param split
	 *            String
	 * @param optType
	 *            操作类型0:普通; 1:在解析角色时去掉
	 * @return String string whose format like " '1','2','3'"
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String arrayToStrWithStr(String[] array, String split,
			String optType) {
		if (array == null || array.length < 1) {
			return null;
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append("'");
			if (optType.equals("1")) {
				String temp = strnull(array[i]);
				sb.append(temp.substring(1, temp.length()));
			} else {
				sb.append(strnull(array[i]));
			}
			sb.append("'");

		}
		return sb.toString();

	}

	/**
	 * 将以sgn为分隔符的字符串转化为数组
	 * 
	 * @param str
	 *            String
	 * @param sgn
	 *            String
	 * @return String[]
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2004-11-4 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String[] strConvertoArray(String str, String sgn) {
		StringTokenizer st = new StringTokenizer(str, sgn);
		String retstr[] = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			retstr[i] = st.nextToken();
		}
		return retstr;
	}

	/**
	 * 将以sgn为分隔符的字符串转化为List链表
	 * 
	 * @param str
	 *            String 要处理的字符串
	 * @param sgn
	 *            String 间隔符
	 * @return List
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2005-03-17 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static List strConvertoList(String str, String sgn) {
		StringTokenizer st = new StringTokenizer(str, sgn);
		List result = new LinkedList();

		for (int i = 0; st.hasMoreTokens(); i++) {
			result.add(st.nextToken());
		}
		return result;
	}

	/**
	 * 1 --> 00001将整数转化为指定长度字符串(lpMaxLength为5)
	 * 
	 * @param lpInt
	 *            int
	 * @param lpMaxLength
	 *            int
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String intToStr(int lpInt, int lpMaxLength) {
		int length, i;
		String returnValue = "";

		length = Integer.toString(lpInt).length();
		if (length < lpMaxLength) {
			i = lpMaxLength - length;
			while (i > 0) {
				returnValue = returnValue + "0";
				i--;
			}
			returnValue = returnValue + Integer.toString(lpInt);
		} else {
			returnValue = Integer.toString(lpInt);
		}
		return returnValue;
	}

	/**
	 * 将字符集转换成整型
	 * 
	 * @param source
	 *            String
	 * @return int
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static int toInt(String source) {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException notint) {
			return 0;
		}
	}

	/**
	 * 取路径后的文件名，也就是路径字串最后一个斜杠后的字串
	 * 
	 * @param path
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getPathFile(String path) {
		String substr = "";
		try {
			if (path != null && !path.equals("")) {
				int i = path.lastIndexOf("/");
				substr = path.substring(i + 1).trim();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return substr;
	}

	/**
	 * 取小数点后的字串，也就是小数点后的字串
	 * 
	 * @param str
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getLastTwo(String str) {
		String substr = "";
		try {
			if (str != null && !str.equals("")) {
				int i = str.lastIndexOf(".");
				substr = str.substring(i + 1).trim();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return substr;
	}

	/**
	 * 取得文件名的文件类型(如2003001.doc-->doc)
	 * 
	 * @param lpFileName
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getFileType(String lpFileName) {
		String lpReturnValue = "";

		if (lpFileName != null && !lpFileName.equals("")) {
			int i = lpFileName.lastIndexOf(".");
			lpReturnValue = lpFileName.substring(i, lpFileName.length());
		}
		return lpReturnValue;
	}

	/**
	 * 返回位于 String 对象中指定位置的子字符串
	 * 
	 * @param str
	 *            String
	 * @param beginIndex
	 *            int
	 * @param endIndex
	 *            int
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getSubString(String str, int beginIndex, int endIndex) {
		String str1 = "";

		if (str == null) {
			str = "";
		}
		if (str.length() >= beginIndex && str.length() >= endIndex) {
			str1 = str.substring(beginIndex, endIndex);
		} else {
			str1 = str;
		}
		return str1;
	}

	/**
	 * 如果入参是null或者"",用另一入参rpt替代入参返回，否则返回入参的trim()
	 * 
	 * @param str
	 *            String
	 * @param rpt
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(String str, String rpt) {
		if (str == null || str.equals("null") || str.equals("")
				|| str.trim() == null) {
			return rpt;
		} else {
			return str.trim();
		}
	}

	/**
	 * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号
	 * 
	 * @param strn
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(String strn) {
		return strnull(strn, "");
	}

	/**
	 * 为检查null值，如果为null，将返回""，不为空时将替换其非html符号
	 * 
	 * @param str
	 *            Object
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(Object str) {
		String result = "";
		if (str == null) {
			result = "";
		} else {
			result = str.toString();
		}
		return result;
	}

	/**
	 * 适用于web层 为检查null值，如果为null，将返回"&nbsp;"，不为空时将替换其非html符号
	 * 
	 * @param strn
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String repnull(String strn) {
		return strnull(strn, "&nbsp;");
	}

	/**
	 * 把Date的转化为字符串，为空时将为"0000-00-00 00:00:00"
	 * 
	 * @param strn
	 *            Date
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(Date strn) {
		String str = "";

		if (strn == null) {
			str = "0000-00-00 00:00:00";
		} else {
			// strn.toGMTString();
			str = strn.toString();
		}
		return (str);
	}

	/**
	 * 把BigDecimal的转换为字符串，为空将返回0
	 * 
	 * @param strn
	 *            BigDecimal
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(BigDecimal strn) {
		String str = "";

		if (strn == null) {
			str = "0";
		} else {
			str = strn.toString();
		}
		return (str);
	}

	/**
	 * 把int的转换为字符串(不为空，只起转换作用)
	 * 
	 * @param strn
	 *            int
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(int strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	/**
	 * 把float的转换为字符串(不为空，只起转换作用)
	 * 
	 * @param strn
	 *            float
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(float strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	public static String strnull(long strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	/**
	 * 把double的转换为字符串(不为空，只起转换作用)
	 * 
	 * @param strn
	 *            double
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String strnull(double strn) {
		String str = String.valueOf(strn);
		return (str);
	}

	/**
	 * 0-15转化为0-F
	 * 
	 * @param bin
	 *            int
	 * @return char
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static char hex(int bin) {
		char retval;
		if (bin >= 0 && bin <= 9) {
			retval = (char) ('0' + bin);
		} else if (bin >= 10 && bin <= 15) {
			retval = (char) ('A' + bin - 10);
		} else {
			retval = '0';
		}
		return retval;
	}

	/**
	 * 字符串替换
	 * 
	 * @param content
	 *            String
	 * @param oldString
	 *            String
	 * @param newString
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2004-1-6 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String replace(String content, String oldString,
			String newString) {
		if (content == null || oldString == null) {
			return content;
		}
		if (content.equals("") || oldString.equals("")) {
			return content;
		}

		String resultString = "";
		int stringAtLocal = content.indexOf(oldString);
		int startLocal = 0;
		while (stringAtLocal >= 0) {
			resultString = resultString
					+ content.substring(startLocal, stringAtLocal) + newString;
			startLocal = stringAtLocal + oldString.length();
			stringAtLocal = content.indexOf(oldString, startLocal);
		}

		resultString = resultString
				+ content.substring(startLocal, content.length());
		return resultString;
	}

	/**
	 * 替换字符串内容
	 * 
	 * @param strSource
	 * @param strFrom
	 * @param strTo
	 * @return
	 * @author: Liuzhuangfei
	 * @修改记录： ==============================================================<br>
	 *        日期:Feb 9, 2010 Liuzhuangfei 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String replaceStr(String strSource, String strFrom,
			String strTo) {
		if (strFrom == null || strFrom.equals(""))
			return strSource;
		String strDest = "";
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;
		return strDest;
	}

	/**
	 * @param strn
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String formatToHTML(String strn) {
		StringBuffer dest = new StringBuffer();
		if (strnull(strn).equals("")) {
			dest.append("&nbsp;");
		} else {
			for (int i = 0; strn != null && i < strn.length(); i++) {
				char c = strn.charAt(i);
				if (c == '\n') {
					dest.append("<br>");
				} else if (c == '\'') {
					dest.append("&#39;");
				} else if (c == '\"') {
					dest.append("&#34;");
				} else if (c == '<') {
					dest.append("&lt;");
				} else if (c == '>') {
					dest.append("&gt;");
				} else if (c == '&') {
					dest.append("&amp;");
				} else if (c == 0x20) {
					dest.append("&nbsp;");
				} else {
					dest.append(c);
				}
			}
		}
		return (dest.toString());
	}

	/**
	 * @param strn
	 *            String
	 * @param length
	 *            int
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String formatToHTML(String strn, int length) {
		int m = 0;
		StringBuffer dest = new StringBuffer();
		if (strnull(strn).equals("")) {
			dest.append("&nbsp;");
		} else {
			for (int i = 0; strn != null && i < strn.length(); i++) {
				m++;
				if (m == length) {
					dest.append("...");
					break;
				}
				char c = strn.charAt(i);
				if (c == '\n') {
					dest.append("<br>");
				} else if (c == '\'') {
					dest.append("&#39;");
				} else if (c == '\"') {
					dest.append("&#34;");
				} else if (c == '<') {
					dest.append("&lt;");
				} else if (c == '>') {
					dest.append("&gt;");
				} else if (c == '&') {
					dest.append("&amp;");
				} else if (c == 0x20) {
					dest.append("&nbsp;");
				} else {
					dest.append(c);
				}
			}
		}
		return (dest.toString());
	}

	/**
	 * @param strb
	 *            BigDecimal
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String formatToHTML(BigDecimal strb) {
		String strn = "";
		if (strb == null) {
			strn = "&nbsp;";
		} else {
			strn = strnull(strb);
		}
		return strn;
	}

	/**
	 * 将多行字符串转为有带有回车、换行的HTML格式
	 * 
	 * @param source
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String nl2Br(String source) {
		if (strnull(source).equals("")) {
			return "&nbsp;";
		}
		StringBuffer dest = new StringBuffer(source.length());
		for (int i = 0; i < source.length(); i++) {
			char c;
			c = source.charAt(i);
			if (c == '\n') {
				dest.append("<br>");
			} else if (c == 0x20) {
				dest.append("&nbsp;");
			} else {
				dest.append(c);
			}
		}
		return dest.toString();
	}

	/**
	 * @param sourceStr
	 *            String
	 * @param fieldStr
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static boolean findString(String sourceStr, String fieldStr) {
		boolean strExist = false;
		if (sourceStr.length() == 0) {
			return strExist;
		}
		if (sourceStr.indexOf(fieldStr) >= 0) {
			strExist = true;
		}
		return strExist;
	}

	/**
	 * @param exception
	 *            Throwable
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getStackTrace(Throwable exception) {
		StringWriter sw = new StringWriter();
		return sw.toString();
	}

	/**
	 * 给字符串数组排序
	 * 
	 * @param arr
	 *            String[] 要进行排序的字符串数组
	 * @return String[] 排序后的字符串数组
	 * @author: Linxf
	 * @修改记录： ==============================================================<br>
	 *        日期:2004-08-09 Linxf 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String[] bubbleSort(String[] arr) {
		int tag = 1;
		for (int i = 1; i < arr.length && tag == 1; i++) {
			tag = 0;
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					String temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					tag = 1;
				}
			}
		}
		return arr;
	}

	/**
	 * 依据ValueArr数组的排序，为ContentArr排序
	 * 
	 * @param valueArr
	 *            String[]
	 * @param contentArr
	 *            String[]
	 * @return String[]
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String[] bubbleSort(String[] valueArr, String[] contentArr) {
		int tag = 1;
		for (int i = 1; i < valueArr.length && tag == 1; i++) {
			tag = 0;
			for (int j = 0; j < valueArr.length - i; j++) {
				if (valueArr[j].compareTo(valueArr[j + 1]) > 0) {
					String temp1 = valueArr[j];
					String temp2 = contentArr[j];
					valueArr[j] = valueArr[j + 1];
					contentArr[j] = contentArr[j + 1];
					valueArr[j + 1] = temp1;
					contentArr[j + 1] = temp2;
					tag = 1;
				}
			}
		}
		return valueArr;
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arr
	 *            int[] 要进行排序的数组
	 * @return int[] 排序后的数组
	 * @author: Linxf
	 * @修改记录： ==============================================================<br>
	 *        日期:2004-08-09 Linxf 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static int[] bubbleSort(int[] arr) {
		int tag = 1;
		for (int i = 1; i < arr.length && tag == 1; i++) {
			tag = 0;
			for (int j = 0; j < arr.length - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					tag = 1;
				}
			}
		}
		return arr;
	}

	/**
	 * 将空字符串转为"0"字符串
	 * 
	 * @param str
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String nullToZero(String str) {
		if (str == null || str.equals("")) {
			str = "0";
		}
		return str;
	}

	/**
	 * request中获取long类型的参数值
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param paraName
	 *            String
	 * @return long
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static long getlongParameter(HttpServletRequest request,
			String paraName) {
		long value = new Long(nullToZero(request.getParameter(paraName)))
				.longValue();
		return value;
	}

	/**
	 * 从request中获取long类型的参数值
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param paraName
	 *            String
	 * @return Long
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static Long getLongParameter(HttpServletRequest request,
			String paraName) {
		Long value = new Long(nullToZero(request.getParameter(paraName)));

		return value;
	}

	/**
	 * 从request中获取int类型的参数值
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param paraName
	 *            String
	 * @return int
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static int getIntParameter(HttpServletRequest request,
			String paraName) {
		int value = Integer
				.parseInt(nullToZero(request.getParameter(paraName)));
		return value;
	}

	/**
	 * 从request中获取String类型的参数值
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param paraName
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getStringParameter(HttpServletRequest request,
			String paraName) {
		String value = strnull(request.getParameter(paraName));
		return value;
	}

	/**
	 * 返回字段的PO名
	 * 
	 * @param obName
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getPOFieldName(String obName) {
		String aFieldName = obName;
		if (aFieldName == null) {
			return null;
		}
		aFieldName = aFieldName.toLowerCase();
		while (aFieldName.indexOf("_") >= 0) {
			if (aFieldName.indexOf("_") >= 0) {
				int pos = aFieldName.indexOf("_");
				String low = aFieldName.substring(0, pos);
				String midd = aFieldName.substring(pos + 1, pos + 2)
						.toUpperCase();
				String end = aFieldName.substring(pos + 2, aFieldName.length());
				aFieldName = low + midd + end;
			}
		} // end while
		return aFieldName;
	}

	/**
	 * 返回表的PO名
	 * 
	 * @param obName
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getPOTableName(String obName) {
		String aTableName = obName;
		if (aTableName == null) {
			return null;
		}
		aTableName = aTableName.toLowerCase();
		while (aTableName.indexOf("_") >= 0) {
			if (aTableName.indexOf("_") >= 0) {
				int pos = aTableName.indexOf("_");
				String low = aTableName.substring(0, pos);
				String midd = aTableName.substring(pos + 1, pos + 2)
						.toUpperCase();
				String end = aTableName.substring(pos + 2, aTableName.length());
				aTableName = low + midd + end;
			}
		} // end while
		aTableName = aTableName.substring(0, 1).toUpperCase()
				+ aTableName.substring(1, aTableName.length());
		return aTableName;
	}

	/**
	 * Encode a string using algorithm specified in web.xml and return the
	 * resulting encrypted password. If exception, the plain credentials string
	 * is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this
	 *            username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * @param str
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return encoder.encodeBuffer(str.getBytes()).trim();
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 *            String
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String decodeString(String str) {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}

	/**
	 * 在右边填充字符串
	 * 
	 * @param rString
	 *            要填充的初始字符串
	 * @param rLength
	 *            填充后的长度
	 * @param rPad
	 *            填充字符
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String padTrailing(String rString, int rLength, String rPad) {
		String lTmpPad = "";

		String lTmpStr = strnull(rString);

		if (lTmpStr.length() >= rLength) {
			return lTmpStr.substring(0, lTmpStr.length());
		} else {
			for (int gCnt = 1; gCnt <= rLength - lTmpStr.length(); gCnt++) {
				lTmpPad = rPad + lTmpPad;
			}
		}
		return lTmpStr + lTmpPad;
	}

	/**
	 * 在左边填充字符串
	 * 
	 * @param rString
	 *            要填充的初始字符串
	 * @param rLength
	 *            填充后的长度
	 * @param rPad
	 *            填充字符
	 * @return String
	 * @author: chenjun
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-21 chenjun 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String padLeading(String rString, int rLength, String rPad) {
		String lTmpPad = "";

		String lTmpStr = strnull(rString);

		if (lTmpStr.length() >= rLength) {
			return lTmpStr.substring(0, lTmpStr.length());
		} else {
			for (int gCnt = 1; gCnt <= rLength - lTmpStr.length(); gCnt++) {
				lTmpPad = lTmpPad + rPad;
			}
		}
		return lTmpPad + lTmpStr;
	}

	/**
	 * 判断数组是否存在某个字符串，并返回索引
	 * 
	 * @param source
	 *            String[]
	 * @param subStr
	 *            String
	 * @return int
	 * @author: panchh
	 * @修改记录： ==============================================================<br>
	 *        日期:2007-9-1 panchh 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static int contains(String[] source, String subStr) {
		for (int i = 0; i < source.length; i++) {
			if (source[i].equals(subStr)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 检查字符串是否为空对象或者等于""
	 * 
	 * @param str
	 * @return 当对象不存在或者等于"" 返回 true
	 * @author: Hurz
	 * @修改记录： ==============================================================<br>
	 *        日期:Mar 10, 2010 Hurz 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.equals("null") || str.equals(""))
			return true;
		return false;
	}

	/**
	 * 获取制定的XML中间内容
	 * 
	 * @param inStr
	 * @param maskStartStr
	 * @param maskEndStr
	 * @return
	 * @throws ManagerException
	 * @author: Liuzhuangfei
	 * @修改记录： ==============================================================<br>
	 *        日期:Apr 2, 2010 Liuzhuangfei 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getXmlContent(String inXML, String maskStartStr,
			String maskEndStr) {
		String outStr = "";
		int startIndex = -1;
		int endIndex = -1;

		if (inXML != null) {
			startIndex = inXML.indexOf(maskStartStr);
			endIndex = inXML.indexOf(maskEndStr);

			if (startIndex != -1) {
				int contentStart = inXML.indexOf('>', startIndex) + 1;
				outStr = inXML.substring(contentStart, endIndex);
			}
		}

		return outStr;
	}

	/**
	 * 获得指定的XML内容list .
	 * 
	 * @param inXml
	 * @param maskStr
	 * @author: Hurz
	 * @return
	 */
	public static List<String> getSubXmlList(String inXml, String maskStartStr,
			String maskEndStr) {

		String tmp = inXml.replace(maskEndStr, maskStartStr);
		tmp += " ";
		String[] list = tmp.split(maskStartStr);
		List ret = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
			if (i != 0 && i != list.length - 1 && !list[i].trim().equals("")) {
				ret.add(list[i]);
			}
		}
		return ret;
	}

	/**
	 * 取得电话号码的区号
	 * 
	 * @param phoneNo
	 *            （号码格式： 0101234567）
	 * @return 区号：010
	 * @author: hurz
	 * @修改记录： ==============================================================<br>
	 *        日期:Apr 16, 2010 hurz 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getArea(String phoneNo) {
		String area = "";
		if (phoneNo.length() > 5) {
			if ("0".equals(phoneNo.substring(0, 1))) {
				int num = toInt(phoneNo.substring(1, 2));
				if (num < 3)
					area = phoneNo.substring(0, 3);
				else
					area = phoneNo.substring(0, 4);
			}
		}
		return area;
	}

	/**
	 * 取得电话号码
	 * 
	 * @param phoneNo
	 *            （号码格式： 0103456789）
	 * @return 电话号码： 3456789
	 * @author: hurz
	 * @修改记录： ==============================================================<br>
	 *        日期:Apr 16, 2010 hurz 创建方法，并实现其功能
	 *        ==============================================================<br>
	 */
	public static String getPhone(String phoneNo) {
		String phone = "";
		if (phoneNo.length() > 5) {
			if ("0".equals(phoneNo.substring(0, 1))) {
				int num = toInt(phoneNo.substring(1, 2));
				if (num < 3)
					phone = phoneNo.substring(3);
				else
					phone = phoneNo.substring(4);
			} else
				return phoneNo;
		} else
			return phoneNo;

		return phone;
	}

	/**
	 * 生成随机密码（数字型）
	 * 
	 * @param length
	 * @param str
	 * @return
	 */
	public static String randomPassWord(int length) {
		return randomPassWord(length, "");
	}

	private static String randomPassWord(int length, String str) {
		if (str.length() != 6) {
			str = String.valueOf(Math.round(Math.random() * 1000000));
			str = randomPassWord(length, str);
		}
		return str;
	}

	/**
	 * 生成节点字符串
	 * 
	 * @param nodeName
	 *            节点名称
	 * @param val
	 *            节点值
	 * @return
	 */
	public static String genNodeStr(String nodeName, String val) {
		if (isNullOrEmpty(nodeName)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if (isNullOrEmpty(val)) {
			sb.append("<").append(nodeName).append("/>");
		} else {
			sb.append("<").append(nodeName).append(">");
			sb.append(StrUtil.strnull(val));
			sb.append("</").append(nodeName).append(">");
		}
		return sb.toString();
	}

}
