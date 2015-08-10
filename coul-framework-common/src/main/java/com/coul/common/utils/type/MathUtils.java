package com.coul.common.utils.type;

import java.math.BigDecimal;

import org.apache.commons.math.stat.descriptive.rank.Max;
import org.apache.commons.math.stat.descriptive.rank.Min;

public class MathUtils {
    // private static final int DEF_DIV_SCALE = 0;
    private MathUtils() {
    }
    
    public static double add(String v1, String v2) {
        
        BigDecimal b1 = new BigDecimal(v1);
        
        BigDecimal b2 = new BigDecimal(v2);
        
        return b1.add(b2).doubleValue();
        
    }
    
    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    
    public static double add(double v1, double v2) {
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.add(b2).doubleValue();
        
    }
    
    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    
    public static double sub(double v1, double v2) {
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.subtract(b2).doubleValue();
        
    }
    
    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    
    public static double mul(double v1, double v2) {
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.multiply(b2).doubleValue();
        
    }
    
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    
    public static double div(double v1, double v2) {
        
        return div(v1, v2, 0);
        
    }
    
    public static double div(long v1, long v2) {
        
        return div(v1, v2, 0);
        
    }
    
    public static long div2(long v1, long v2) {
        
        return div2(v1, v2, 0);
        
    }
    
    public static long div2(long v1, long v2, int scale) {
        
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        
        BigDecimal b2 = new BigDecimal(v2);
        
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).longValue();
        
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    
    public static double div(double v1, double v2, int scale) {
        
        if (scale < 0) {
            
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
            
        }
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        
    }
    
    public static double div(long v1, long v2, int scale) {
        
        if (scale < 0) {
            
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
            
        }
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    
    public static double round(double v, int scale) {
        
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
            
        }
        
        BigDecimal b = new BigDecimal(v);
        
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        
    }
    
    public static double max(double[] numbers) {
        Max max = new Max();
        return max.evaluate(numbers, 0, numbers.length);
        
    }
    
    public static long max(long[] values) {
        double[] ds = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            ds[i] = (double) values[i];
        }
        return (long) max(ds);
    }
    
    public static long min(long[] values) {
        double[] ds = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            ds[i] = (double) values[i];
        }
        return (long) min(ds);
    }
    
    public static double min(double[] numbers) {
        Min min = new Min();
        return min.evaluate(numbers, 0, numbers.length);
        
    }
    
    public static void main(String[] args) {
        System.out.println(max(new double[] {1.2, 34, 66, 0.1 }));
        System.out.println(min(new long[] {2, 34, 66, 134455666 }));
        System.out.println(max(new long[] {2, 34, 66, 134455666 }));
        
        System.out.println(round(1.632, 2));
        BigDecimal b = new BigDecimal(1.632);
        double d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(d);
    }
    
}
