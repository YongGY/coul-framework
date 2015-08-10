package com.coul.common.utils.type;

import java.rmi.server.UID;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.coul.common.utils.crypt.Base64Cryption;

public class RandomUtils {
    
    private static Random random = new Random();
    
    public static Integer[] getRandomArray(int n) {
        if (n <= 0) {
            return null;
        }
        Set<Integer> r = new LinkedHashSet<Integer>();
        int count = n * 10;
        int i = 0;
        while (i < count && r.size() < n) {
            int f = random.nextInt(n);
            // System.out.println(f);
            r.add(f);
            i++;
        }
        if (i >= count && r.size() < n) {
            return null;
        }
        // System.out.println("i=="+i);
        Integer[] t = new Integer[0];
        return r.toArray(t);
    }
    
    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
    
    public static String genUID() {
        UID uid = new UID();
        return uid.toString();
    }
    
    public static String genRandomString(int strLen) {
        byte[] bytes = new byte[strLen];
        random.nextBytes(bytes);
        try {
            return Base64Cryption.encodeBytes(bytes).substring(0, strLen);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        return "";
        // return RandomStringUtils.random(strLen);
    }
    
    public static String getRandomNumberString(int strLen) {
        String ss = "0123456789";
        String s = "";
        for (int i = 0; i < strLen; i++) {
            int n = random.nextInt(ss.length());
            char r = ss.charAt(n);
            s = s + r;
        }
        
        return s;
    }
    
    public static void main(String[] args) {
        String ss = "0123456789";
        System.out.println(random.nextInt(ss.length()));
    }
    
    public static String getRandomAlphaString(int strLen) {
        String ss = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s = "";
        for (int i = 0; i < strLen; i++) {
            int n = random.nextInt(ss.length());
            char r = ss.charAt(n);
            s = s + r;
        }
        return s;
    }
    
    // public static String getRandom(int x)
    // {
    // Random rd = new Random(); //创建随机对象
    // String n="";
    // //String num1="";
    // int rdGet; //取得随机数
    // do{
    // rdGet=Math.abs(rd.nextInt())%10+48; //产生48到57的随机数(0-9的键位值)
    // //rdGet=Math.abs(rd.nextInt())%26+97; //产生97到122的随机数(a-z的键位值)
    // char num1=(char)rdGet;
    // String dd=Character.toString(num1);
    // n+=dd;
    //
    // }while(n.length()<x);//
    // return n;
    // }
    public static String getRandomMixString(int strLen) {
        String ss = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String s = "";
        for (int i = 0; i < strLen; i++) {
            int n = random.nextInt(ss.length());
            char r = ss.charAt(n);
            s = s + r;
        }
        return s;
    }
    
    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
    
    /**
     * Returns the next pseudorandom, uniformly distributed float value between
     * 0.0 and 1.0 from the Math.random() sequence.
     * 
     * @return
     */
    public static double nextDouble() {
        return random.nextDouble();
    }
    
    /**
     * Returns the next pseudorandom, uniformly distributed float value between
     * 0.0 and 1.0 from the Math.random() sequence.
     * 
     * @return
     */
    public static float nextFloat() {
        return random.nextFloat();
    }
    
    public static int nextInt() {
        return random.nextInt();
    }
    
    /**
     * Returns a pseudorandom, uniformly distributed int value between 0
     * (inclusive) and the specified value (exclusive), from the Math.random()
     * sequence.
     * 
     * @param n
     * @return
     */
    public static int nextInt(int n) {
        return random.nextInt(n);
    }
    
    public static long nextLong() {
        return random.nextLong();
    }
    
    /*public static void main(String[] args) throws Exception {
    	System.out.println(genUUID());
    	System.out.println(genUID());
    	// System.out.println(genRandomString(3456709).length());
    	// System.out.println(genNameUUID("5486243a-73ec-42bd-b6e1"));
    	System.out.println(RandomUtils.genRandomString(20));
    	System.out.println(getRandomNumberString(20));
    	System.out.println(getRandomAlphaString(10));
    	System.out.println(getRandomMixString(100));
    	// System.out.println((char)(int)97);
    	System.out.println(CTime.formatDate().substring(8)
    			+ RandomUtils.getRandomNumberString(4));
    	System.out.println(CTime.formatWholeDate());
    	System.out.println(new UID().toString());

    }*/
}
