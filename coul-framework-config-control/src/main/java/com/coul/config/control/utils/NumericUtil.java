package com.coul.config.control.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @��Ȩ��������� ��Ȩ���� (c) 2007
 * @�ļ���com.ffcs.crm.common.util.numeric.NumericUtil.java
 * @���ࣺNumericUtil
 * @author: chenjun
 * @version: V1.0
 * @see:
 * @�������ڣ�2007-9-20
 * @����˵����ʵ�����ּ����ָ�ʽ���Ļ���<br> <&nbsp>��������ȷ�ļӼ��˳�������ת �����ĵȡ�
 * @�޸ļ�¼�� =============================================================<br>
 *        ����:2007-9-20 chenjun ����
 *        =============================================================<br>
 */
public class NumericUtil {
    // Ĭ�ϳ����㾫��
    private static final int DEF_DIV_SCALE = 10;
    
    /**
     * �ṩ��ȷ�ļӷ����㡣
     * 
     * @param v1
     *            ������
     * @param v2
     *            ����
     * @return ��������ĺ�
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    
    /**
     * �ṩ��ȷ�ļ������㡣
     * 
     * @param v1
     *            ������
     * @param v2
     *            ����
     * @return ��������Ĳ�
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    
    /**
     * �ṩ��ȷ�ĳ˷����㡣
     * 
     * @param v1
     *            ������
     * @param v2
     *            ����
     * @return ��������Ļ�
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    
    /**
     * �ṩ����ԣ���ȷ�ĳ����㣬�����������ʱ����ȷ�� С����Ժ�10λ���Ժ�������������롣
     * 
     * @param v1
     *            ������
     * @param v2
     *            ����
     * @return double
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEF_DIV_SCALE);
    }
    
    /**
     * �ṩ����ԣ���ȷ�ĳ����㡣�����������ʱ����scale����ָ �����ȣ��Ժ�������������롣
     * 
     * @param v1
     *            ������
     * @param v2
     *            ����
     * @param scale
     *            ��ʾ��ʾ��Ҫ��ȷ��С����Ժ�λ��
     * @return double �����������
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * �ṩ��ȷ��С��λ�������봦�?
     * 
     * @param v
     *            ��Ҫ�������������
     * @param scale
     *            С��������λ
     * @return ���������Ľ��
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * �����ֽ��(BigDecimal����)ת��Ϊ���Ľ��
     * 
     * @param bigdMoneyNumber
     *            ת��ǰ�����ֽ��
     * @return String ���Ľ��
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2004-05-26 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static String lowerToUpperOfMoney(BigDecimal bigdMoneyNumber) {
        String[] straChineseUnit = new String[] {"��", "��", "Բ", "ʰ", "��", "Ǫ",
                "��", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ" };
        // ���������ַ�����
        String[] straChineseNumber = new String[] {"��", "Ҽ", "��", "��", "��",
                "��", "½", "��", "��", "��" };
        String strChineseCurrency = "";
        // ����λ���
        boolean bZero = true;
        // ���Ľ�λ�±�
        int chineseUnitIndex = 0;
        
        try {
            if (bigdMoneyNumber.intValue() == 0) {
                return "��Բ��";
            }
            // ����С��֣���������
            double doubMoneyNumber = Math
                .round(bigdMoneyNumber.doubleValue() * 100);
            // �Ƿ���
            boolean bNegative = doubMoneyNumber < 0;
            // ȡ���ֵ
            doubMoneyNumber = Math.abs(doubMoneyNumber);
            // ѭ������ת������
            while (doubMoneyNumber > 0) {
                // ��Ĵ���(��С��λ)
                if (chineseUnitIndex == 2 && strChineseCurrency.length() == 0) {
                    strChineseCurrency = strChineseCurrency + "��";
                }
                // ������λ�Ĵ���
                if (doubMoneyNumber % 10 > 0) {
                    strChineseCurrency = straChineseNumber[(int) doubMoneyNumber % 10]
                        + straChineseUnit[chineseUnitIndex]
                        + strChineseCurrency;
                    bZero = false;
                } else { // ����λ�Ĵ���
                    // Ԫ�Ĵ���(��λ)
                    if (chineseUnitIndex == 2) {
                        // ����������
                        if (doubMoneyNumber > 0) {
                            strChineseCurrency = straChineseUnit[chineseUnitIndex]
                                + strChineseCurrency;
                            bZero = true;
                        }
                    } else if (chineseUnitIndex == 6 || chineseUnitIndex == 10) { // ������λ�Ĵ���
                        // ����������
                        if (doubMoneyNumber % 1000 > 0) {
                            strChineseCurrency = straChineseUnit[chineseUnitIndex]
                                + strChineseCurrency;
                        }
                    }
                    // ǰһ��λ����Ĵ���
                    if (!bZero) {
                        strChineseCurrency = straChineseNumber[0]
                            + strChineseCurrency;
                    }
                    bZero = true;
                }
                doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
                chineseUnitIndex++;
            }
            // ����Ĵ���
            if (bNegative) {
                strChineseCurrency = "��" + strChineseCurrency;
            }
        } catch (Exception e) {
            return "";
        }
        return strChineseCurrency;
    }
    
    /**
     * ��Сд���(double����)ת��Ϊ����Ҵ�д��ʽ
     * 
     * @param je
     *            ת��ǰ��Сд���ֽ��
     * @return String ���Ľ��
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2004-05-26 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static String lowerToUpperOfMoney(double je) {
        String money = ""; // ת������ַ�
        String num = "��Ҽ��������½��ƾ�";
        String[] unit = {"Ԫ", "ʰ", "��", "Ǫ", "��", "ʰ��", "����", "Ǫ��", "��", "ʰ��",
                "����", "Ǫ��" };
        String s = String.valueOf(je); // �����ת��Ϊ�ַ�
        int a = s.indexOf("+"); // �ж�s�Ƿ��'+',��1.67E+4
        int e = s.indexOf("E"); // �ж�s�Ƿ��'E',��1.67E+4
        if (je == 0.00) {
            return money;
        }
        // ����'E'(�ý�����Կ�ѧ�����ʾ,��ת������ͨ��ʾ��)
        if (e != -1) {
            int index = 0; // ָ��ֵ
            if (a == -1) {
                index = Integer.parseInt(s.substring(e + 1)); // ȡ��ָ��ֵ
            } else {
                index = Integer.parseInt(s.substring(a + 1)); // ȡ��ָ��ֵ
            }
            String sub1 = s.substring(0, e); // ȡ��β��ֵ
            int dot = sub1.indexOf("."); // β���С���λ��
            // �����С���,���ں��油index��'0'
            if (dot == -1) {
                for (int i = 1; i <= index; i++) {
                    s = sub1 + "0";
                }
            } else { // �����С���,������ƶ�С���indexλ
                String sub11 = sub1.substring(0, dot); // С���ǰ����ִ�
                String sub12 = sub1.substring(dot + 1); // С��������ִ�
                if (index >= sub12.length()) {
                    int j = index - sub12.length();
                    for (int i = 1; i <= j; i++) {
                        sub12 = sub12 + "0";
                    }
                } else {
                    sub12 = sub12.substring(0, index) + "."
                        + sub12.substring(index);
                }
                s = sub11 + sub12;
            }
        }
        int sdot = s.indexOf("."); // s��С����λ��
        String beforeDot = ""; // С���ǰ����ִ�
        String afterDot = ""; // С��������ִ�
        // ����С���
        if (sdot != -1) {
            beforeDot = s.substring(0, sdot);
            afterDot = s.substring(sdot + 1);
        } else {// ����С���
            beforeDot = s;
        }
        int bl = beforeDot.length();
        boolean zero = false;// �����Ƿ�Ϊ��
        int z = 0;// '0'�ĸ���
        
        // ��λȡ����
        for (int j = 0, i = bl - 1; j <= bl - 1; j++, i--) {
            int number = Integer.parseInt(String.valueOf(beforeDot.charAt(j)));
            if (number == 0) {
                zero = true;
                z++;
            } else {
                zero = false;
                z = 0;
            }
            if (zero && z == 1) {
                money += "��";
            } else if (zero) {
            } else if (!zero) {
                money += num.substring(number, number + 1) + unit[i];
            }
        }
        
        // ɾȥ�����'��'��'��'
        for (int i = 1; i <= 2; i++) {
            String ss = "";
            if (i == 1) {
                ss = "��";
            } else {
                ss = "��";
            }
            int last = money.lastIndexOf(ss);
            if (last != -1) {
                String moneySub1 = money.substring(0, last);
                String moneySub2 = money.substring(last, money.length());
                int last2 = moneySub1.indexOf(ss);
                while (last2 != -1) {
                    moneySub1 = moneySub1.substring(0, last2)
                        + moneySub1.substring(last2 + 1, moneySub1.length());
                    last2 = moneySub1.indexOf(ss);
                }
                money = moneySub1 + moneySub2;
            }
        }
        
        // money���Ƿ��'Ԫ'
        int yuan = money.indexOf("Ԫ");
        // ����'Ԫ'
        if (yuan == -1) {
            int zi = money.lastIndexOf("��");
            // ������һλ�ַ�Ϊ'��',��ɾ����
            if (zi == money.length() - 1) {
                money = money.substring(0, money.length() - 1) + "Ԫ"; // ��money������'Ԫ'
            }
        }
        
        // ���С��������ִ���Ϊ��,����'��','��'
        if (!afterDot.equals("")) {
            int al = afterDot.length();
            if (al > 2) { // ����ִ����ȴ���2,��ض�
                afterDot = afterDot.substring(0, 2);
                al = afterDot.length();
            }
            // ����ַ�Ϊ'0'��'00',����,���򲻽��д���
            if (!afterDot.equals("0") && !afterDot.equals("00")) {
                // ��λȡ���ַ�
                for (int i = 0; i < al; i++) {
                    int number = Integer.parseInt(String.valueOf(afterDot
                        .charAt(i)));
                    if (number != 0 && i == 0) {
                        money += num.substring(number, number + 1) + "��";
                    } else if (number != 0 && i == 1) {
                        money += num.substring(number, number + 1) + "��";
                    } else if (number == 0 && i == 0) {
                        money += "��";
                    }
                }
            }
        }
        // ����'��','��'����������'��'��
        if (money.indexOf("��") == -1 && money.indexOf("��") == -1) {
            money += "��";
        }
        return money;
    }
    
    /**
     * ��double��ת��Ϊָ��λ����ַ�
     * 
     * @param num
     *            ��ת����float��
     * @param digits
     *            С�����λ��
     * @return String ָ��С��λ����ַ�
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2004-05-26 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static String getNumberFormat(float num, int digits) {
        String thenum;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(digits);
        nf.setMinimumFractionDigits(digits);
        thenum = nf.format(num).toString();
        return thenum;
    }
    
    /**
     * ��double��ת��Ϊָ��λ����ַ�<br>
     * ���磺 NumericUtil.getNumberFormat(123456.12345,3)
     * �Ľ��Ϊ123,456.123��ע��С������һλ��������
     * 
     * @param num
     *            ��ת����double��
     * @param digits
     *            С�����λ��
     * @return String ָ��С��λ����ַ�
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����2004-05-26 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static String getNumberFormat(double num, int digits) {
        String thenum;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(digits);
        nf.setMinimumFractionDigits(digits);
        thenum = nf.format(num).toString();
        return thenum;
    }
    
    /**
     * ��BigDecimal��ת��Ϊָ��λ����ַ�<br>
     * ���磺 NumericUtil.getNumberFormat(new BigDecimal(123456.12345),3)
     * �Ľ��Ϊ123,456.123��ע��С������һλ��������
     * 
     * @param num
     *            ��ת����BigDecimal��
     * @param digits
     *            С�����λ��
     * @return String ָ��С��λ����ַ�
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static String getNumberFormat(BigDecimal num, int digits) {
        String thenum = "";
        if (num == null) {
            num = new BigDecimal(0);
        }
        try {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(digits);
            nf.setMinimumFractionDigits(digits);
            thenum = nf.format(num).toString();
        } catch (NumberFormatException nfex) {
            throw new NumberFormatException(nfex.toString());
        }
        return thenum;
    }
    
    /**
     * ��ʽ�ַ�Ϊdouble���
     * 
     * @param lpNumberFormat
     *            ���ʽ�����ַ�
     * @return double double��ֵ
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double getNumberFormatStrToDouble(String lpNumberFormat) {
        double lpReturnNumber = 0;
        NumberFormat nf = NumberFormat.getInstance();
        try {
            Number lpResultNumber = nf.parse(lpNumberFormat);
            lpReturnNumber = lpResultNumber.doubleValue();
        } catch (ParseException pe) {
        }
        return lpReturnNumber;
    }
    
    /**
     * ��ʽ�ַ�Ϊfloat���
     * 
     * @param lpNumberFormat
     *            ���ʽ�����ַ�
     * @return float float��ֵ
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static float getNumberFormatStrToFloat(String lpNumberFormat) {
        float lpReturnNumber = 0;
        NumberFormat nf = NumberFormat.getInstance();
        try {
            Number lpResultNumber = nf.parse(lpNumberFormat);
            lpReturnNumber = lpResultNumber.floatValue();
        } catch (ParseException pe) {
        }
        return lpReturnNumber;
    }
    
    /**
     * ���ַ�ת��ΪBigDecimal����
     * 
     * @param str
     *            ��ת�����ַ�
     * @return BigDecimal ת�����BigDecimal������ַ�Ϊnull�� ��ôBigDecimalΪnew
     *         BigDecimal("0");
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static BigDecimal toBigDecimal(String str) {
        BigDecimal lpReturnValue;
        try {
            if (str == null) {
                str = "0";
            }
            lpReturnValue = new BigDecimal(str);
        } catch (NumberFormatException nfe) {
            lpReturnValue = new BigDecimal(0);
        }
        return lpReturnValue;
    }
    
    /**
     * ���ַ�ת��Ϊdouble���� Converts a string to double. If fails is not throwing a
     * NumberFormatException, instead return 0.
     * 
     * @param str
     *            ��ת�����ַ�
     * @return double���
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static double toDouble(String str) {
        double lpResult = 0;
        
        if (str == null) {
            str = "";
        } else {
            str = str.trim();
        }
        try {
            lpResult = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
        }
        return lpResult;
    }
    
    /**
     * ���ַ�ת��Ϊshort���� Converts a string to short. If fails is not throwing a
     * NumberFormatException, instead return 0.
     * 
     * @param str
     *            ��ת�����ַ�
     * @return int���
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static short toShort(String str) {
        short lpResult = 0;
        if (str == null) {
            str = "";
        } else {
            str = str.trim();
        }
        try {
            lpResult = Short.parseShort(str);
        } catch (NumberFormatException nfe) {
        }
        return lpResult;
    }
    
    /**
     * ���ַ�ת��Ϊint���� Converts a string to integer. If fails is not throwing a
     * NumberFormatException, instead return 0.
     * 
     * @param str
     *            ��ת�����ַ�
     * @return int ���
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static int toInt(String str) {
        int lpResult = 0;
        if (str == null) {
            str = "";
        } else {
            str = str.trim();
        }
        try {
            lpResult = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
        }
        return lpResult;
    }
    
    /**
     * ���ַ�ת��Ϊfloat���� Converts a string to float. If fails is not throwing a
     * NumberFormatException, instead return 0.
     * 
     * @param str
     *            ��ת�����ַ�
     * @return float ���
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static float toFloat(String str) {
        float lpResult = 0;
        if (str == null) {
            str = "";
        } else {
            str = str.trim();
        }
        try {
            lpResult = Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
        }
        return lpResult;
    }
    
    /**
     * ���ַ�ת��Ϊlong���� Converts a string to long. If fails is not throwing a
     * NumberFormatException, instead return 0.
     * 
     * @param str
     *            ��ת�����ַ�
     * @return long ���
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static long toLong(String str) {
        long lpResult = 0;
        if (str == null) {
            str = "";
        } else {
            str = str.trim();
        }
        try {
            lpResult = Long.parseLong(str);
        } catch (NumberFormatException nfe) {
        }
        return lpResult;
    }
    
    /**
     * ������Ϊ��ʱ������
     * 
     * @param obj
     *            Object
     * @return int
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static int nullToZero(Object obj) {
        int result = 0;
        if (obj == null || obj.toString().equals("")) {
            result = 0;
        } else {
            result = new Integer(obj.toString()).intValue();
        }
        
        return result;
        
    }
    
    public static int nullToZero(String obj) {
        int result = 0;
        if (obj == null || obj.toString().equals("")) {
            result = 0;
        } else {
            result = new Integer(obj.toString()).intValue();
        }
        
        return result;
        
    }
    
    /**
     * ������Ϊ��ʱ������
     * 
     * @param obj
     *            Object
     * @return BigDecimal
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static BigDecimal nullToBigDecimalZero(Object obj) {
        BigDecimal result = new BigDecimal("0");
        if (obj == null || obj.toString().equals("")) {
            result = new BigDecimal("0");
        } else {
            result = new BigDecimal(obj.toString());
        }
        
        return result;
        
    }
    
    /**
     * @param obj
     *            Object
     * @return String
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static String nullToStringZero(Object obj) {
        BigDecimal result = new BigDecimal("0");
        if (obj == null || obj.toString().equals("")) {
            result = new BigDecimal("0");
        } else {
            result = new BigDecimal(obj.toString());
        }
        
        return result.toString();
        
    }
    
    /**
     * @param obj
     *            Object
     * @return Long
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static Long nullToLongZero(Object obj) {
        
        Long result = new Long(0);
        
        try {
            if (obj == null || obj.toString().equals("")) {
                result = new Long(0);
            } else {
                result = new Long(obj.toString());
            }
        } catch (Exception ex) {
            result = new Long(0);
        }
        
        return result;
        
    }
    
    /**
     * @param obj
     *            Double
     * @return Double
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static Double nullToDoubleZero(Double obj) {
        Double result = new Double(0);
        if (obj == null) {
            result = new Double(0);
        } else {
            DecimalFormat format = new DecimalFormat("#.000");
            result = new Double(format.format(obj));
        }
        return result;
        
    }
    
    /**
     * @param obj
     *            Object
     * @return Double
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    public static Double nullToDoubleZero(Object obj) {
        Double result = new Double(0);
        if (obj == null) {
            result = new Double(0);
        } else {
            result = new Double(obj.toString());
        }
        return result;
        
    }
    /**
     * @param args
     *            String
     * @author: chenjun
     * @�޸ļ�¼�� ==============================================================<br>
     *        ����:2007-9-21 chenjun ������������ʵ���书��
     *        ==============================================================<br>
     */
    /*
     * public static void main(String args[]) { NumericUtil a = new
     * NumericUtil(); BigDecimal big = new BigDecimal(123456.12345); }
     */

}
