package com.coul.common.exception;
/**
 * 
 *
 */
public class BssException extends RuntimeException {
	private static final long serialVersionUID = -162688295582258697L;
    
	public BssException(String msg){	
		super(msg);
	}
	
	public BssException(String code,String msg){	
		super(code==null?msg:String.format("%s[%s].", msg,code));
	}
	 public BssException(String code,String msg, Throwable cause){
		 super(code==null?msg:String.format("%s[%s].", msg,code),cause);
	 }	 
	 public BssException(Throwable cause){
		 super(cause);
	 }
}
