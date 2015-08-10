package com.coul.common.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 读取Excel数据操作对象
 * @author Administrator
 *
 */
public final class ReadExcel {
	
	private final static  Log  logger = LogFactory.getLog(ReadExcel.class);
	
	/**
	* <p>单元格格式为日期时，获取指定形式的日期格式</p>
	* @param cell
	* @return The value
	*/
	public String getExcelDateTime(HSSFCell cell) {
		if (isBlank(cell))return null;
		double d =cell.getNumericCellValue();
		Date date = HSSFDateUtil.getJavaDate(d);
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		String s_datetime = sFormat.format(date);
		return s_datetime;
	}
	 
	/**
	* <p>空判断</p>
	* @param cell
	* @return The value
	*/
	public boolean isBlank(HSSFCell  cell) {
		if (null == cell)return true;
		int cellType =cell.getCellType();
		if (HSSFCell.CELL_TYPE_BLANK == cellType) {
		return true;
		}
		return false;
	}
	 
	/**
	* <p>取得整型格式</p>
	* @param cell
	* @return The value
	*/
	public String getExcelInt(HSSFCell cell) {
		if (isBlank(cell))return null;
		return (int)cell.getNumericCellValue() + "";
	}
	/**
	* <p>取得长整型格式</p>
	* @param cell
	* @return The value
	*/
	public String getExcelLong(HSSFCell cell) {
		if (isBlank(cell))return null;
		return (long)cell.getNumericCellValue() + "";
	}
	/**
	* <p>取得长整型格式</p>
	* @param cell
	* @return The value
	*/
	public String getExcelDouble(HSSFCell cell) {
		if (isBlank(cell))return null;
		return  cell.getNumericCellValue() + "";
	}
	/**
	* <p>取得字符串形式
	* @param cell
	* @return The value
	*/
	public String getExcelString(HSSFCell cell) {
	    if (isBlank(cell))return null;
	    return  cell.getStringCellValue();
	}
	/**
	 * 读取Excel文件数据信息
	 * @param excelName  文件的位置名称
	 * @return  
	 */
	public  static List<Object[]>  ReadExcelToData(String  excelName){
		if(excelName == null || excelName.isEmpty()) return null;
		//创建接收Excel数据文件的数据
		List<Object[]>   elist = new   ArrayList<Object[]>();
		try {
			//1.创建文件流
			FileInputStream  input = new  FileInputStream(excelName);
			
			HSSFWorkbook  workBook = new  HSSFWorkbook(input);
			//2.读取Excel的所有行数
			HSSFSheet  sheet = workBook.getSheetAt(0);
			//HSSFRow    row = sheet.getRow(sheet.getFirstRowNum());  //读取第一行
			/**
			 * 改进
			 * 原:getPhysicalNumberOfRows();
			 * 新:getLastRowNum();
			 * 说明：隔行后不能正确的行数据(导致读取数据缺失)
			 */
			for(int i=0;i<=sheet.getLastRowNum();i++){//遍历行
				
				HSSFRow row= sheet.getRow(i);
				
				if(null !=row){//排除空行
					/**
					 * 改进
					 * 原:getPhysicalNumberOfCells();
					 * 新:sheet.getRow(0).getLastCellNum();
					 * 说明：隔列后不能正确读取模版标题对应的数据(导致封装的object数据缺失)
					 */
					int  colums = sheet.getRow(0).getLastCellNum();//始终获取模版标题的列数
					Object[]  obj = new  Object[colums];
					for(int j=0;j<colums;j++){
						//获取单元格并判断单元格的数据类型
						HSSFCell  cell = row.getCell(j);
						obj[j] = getCellValue(cell);
					}
					elist.add(obj);
				}
			}
			return elist;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 读取Excel文件数据信息
	 * @param inputStream  文件流
	 * @return  
	 */
	public static List<Object[]>  ReadExcelToData(FileInputStream inputStream){
		
		//创建接收Excel数据文件的数据
		List<Object[]>   elist = new   ArrayList<Object[]>();
		
		try {
			
			HSSFWorkbook  workBook = new  HSSFWorkbook(inputStream);
			//2.读取Excel的所有行数
			HSSFSheet  sheet = workBook.getSheetAt(0);
			//HSSFRow    row = sheet.getRow(sheet.getFirstRowNum());  //读取第一行
			/**
			 * 改进
			 * 原:getPhysicalNumberOfRows();
			 * 新:getLastRowNum();
			 * 说明：隔行后不能正确的行数据(导致读取数据缺失)
			 */
			for(int i=0;i<=sheet.getLastRowNum();i++){//遍历行
				
				HSSFRow row= sheet.getRow(i);
				
				if(null !=row){//排除空行
					/**
					 * 改进
					 * 原:getPhysicalNumberOfCells();
					 * 新:sheet.getRow(0).getLastCellNum();
					 * 说明：隔列后不能正确读取模版标题对应的数据(导致封装的object数据缺失)
					 */
					int  colums = sheet.getRow(0).getLastCellNum();//始终获取模版标题的列数
					Object[]  obj = new  Object[colums];
					for(int j=0;j<colums;j++){
						//获取单元格并判断单元格的数据类型
						HSSFCell  cell = row.getCell(j);
						obj[j] = getCellValue(cell);
					}
					elist.add(obj);
				}
			}
			return elist;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
   
	/**
	 * 读取Excel的数据文件操作
	 * @param sheet   
	 * @param dataRow  数据行开始
	 * @return
	 */
	public  static List<Object[]>  ReadExcelToDataBySheet(HSSFSheet  sheet , int  dataRow){
		if(sheet == null ) return null;
		//创建接收Excel数据文件的数据
		List<Object[]>   elist = new   ArrayList<Object[]>();
		try {
			/**
			 * 改进
			 * 原:getPhysicalNumberOfRows();
			 * 新:getLastRowNum();
			 * 说明：隔行后不能正确的行数据(导致读取数据缺失)
			 */
			for(int i=0;i<=sheet.getLastRowNum();i++){//遍历行
				
				HSSFRow row= sheet.getRow(i);
				
				if(null !=row){//排除空行
					/**
					 * 改进
					 * 原:getPhysicalNumberOfCells();
					 * 新:getLastCellNum();
					 * 说明：隔列后不能正确读取模版标题对应的数据(导致封装的object数据缺失)
					 */
					int  colums = row.getLastCellNum();
					Object[]  obj = new  Object[colums];
					for(int j=0;j<colums;j++){
						//获取单元格并判断单元格的数据类型
						HSSFCell  cell = row.getCell(j);
						obj[j] = getCellValue(cell);
					}
					elist.add(obj);
				}
			}
			return elist;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 读取Excel文件数据信息
	 * @param excelName
	 * @return  
	 */
	public  static HSSFSheet  getReadExcelSheet(final InputStream  input){
		if(input == null) return null;
		try {
			//1.创建文件流
			HSSFWorkbook  workBook = new  HSSFWorkbook(input);
			//2.读取Excel的所有行数
			HSSFSheet  sheet = workBook.getSheetAt(0);  
			input.close();
			return sheet;	
		} catch (Exception e) {
			logger.debug("Excel文件受损,请查看.");
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 判断单元格的数据类型并获取单元格的数据信息
	 * @param cell
	 * @return
	 */
	public  static  String   getCellValue(HSSFCell  cell){
		String  value = ""; 
		if(cell == null) return "";
		switch(cell.getCellType()){
	     	case  HSSFCell.CELL_TYPE_NUMERIC :
	     	    //value = cell.getNumericCellValue()+"";  break;  //科学计数法的读写
	     		double   cvalue   =   cell.getNumericCellValue();
	     		NumberFormat   nf   =   NumberFormat.getNumberInstance();
	     		nf.setGroupingUsed(false);
	     		value = nf.format(cvalue)+"";  break;//防止出现科学计数的参数信息比如：8.585637834999979E7 
	     	case  HSSFCell.CELL_TYPE_FORMULA : break;
	     	case  HSSFCell.CELL_TYPE_STRING :value = cell.getStringCellValue();
	     	case  HSSFCell.CELL_TYPE_ERROR : break;
		}
		return value;	
	}
	
	
	
	 
	/**
	 * 创建EXCEL文档操作
	 * @param column   创建文档的列数目(总列数)
	 * @param sheetName   EXCLE工作空间的名字
	 * @param sheetData   填充Excel文档数据的操作
	 * @param columnNames  每一列代表的名字（1..2...3...）表示可变数组操作
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public  static  HSSFWorkbook   createHssfWorkBook(int  column ,  String sheetName , List<Object[]>  sheetData , String...  columnNames){
		
		//判断是否是合法操作参数
		if( column <=0 ||  sheetName == null || sheetName.isEmpty() || 
				columnNames == null || columnNames.length <=0 )  return null;
		 	 
        //1、创建一个Excel的文档对象
	    HSSFWorkbook wb = new HSSFWorkbook() ;
	    //2、 sheet 对应一个工作页
	    String[] sheetNames = sheetName.split("\\.");
	    HSSFSheet sheet = wb.createSheet(sheetNames[0]) ;
	    //3.选择第一行进行设置每列的名称
	    HSSFRow firstrow = sheet.createRow(0); //下标为0的行开始 
	    HSSFCell[] firstcell = new HSSFCell[column];
	    //设置字体
	    HSSFFont font  = wb.createFont();  
	    font.setFontHeightInPoints((short)10);//10号字体
	    font.setFontName("宋体");
	    //设置单元格样式
	    HSSFCellStyle style = wb.createCellStyle();
	    style.setFont(font);
	    //制定单元格垂直居中对齐
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    //创建第一行标题头
	    for(short index= 0 ;index<columnNames.length; index++){
	       firstcell[index] = firstrow.createCell(index);
	       firstcell[index].setCellStyle(style); //设置单元格样式
	       firstcell[index].setCellValue(new HSSFRichTextString(columnNames[index]));
	    }
	    
	    //4、设置Excel文档的内容
	    int  rowNum = 1 ;  //行
	    HSSFRow row = null;
	    HSSFCell[] cell = new  HSSFCell[column];
	    for(int n = 0 ; n < sheetData.size() ; n++){
	    	
	    	Object[] obj = sheetData.get(n);
	    	if(obj.length >= column){
	    		//创建行
	    		row = sheet.createRow(rowNum);
	    		
	    		for(int nIndex = 0 ; nIndex < column ; nIndex++){
	    			cell[nIndex] = row.createCell(nIndex);
	    			cell[nIndex].setCellStyle(style); //设置单元格样式
	                cell[nIndex].setCellType(HSSFCell.CELL_TYPE_STRING);
	                //处理数据是否有空或是
	                if(obj[nIndex] == null){
		                cell[nIndex].setCellValue(new  HSSFRichTextString(""));	
	                }else{
		                cell[nIndex].setCellValue(new  HSSFRichTextString(obj[nIndex].toString()));	
	                }	
		    	}
	    		rowNum++;
	    	}
	    }
		return wb;
	}
	
	
	/**
	 * 判断Excel文档是否符合导入模板操作
	 * @param sheet   Excel文档
	 * @param columnNames   Excel模板文档的列字段名称
	 * @return 
	 */
	public static boolean   isSuitModelFile(HSSFSheet  sheet , String... columnNames){
		boolean  flag = false;
		//1.判断参数
		if(sheet == null || columnNames == null || columnNames.length <= 0)  return false;
		
		//2.获取第一行进行比较
		HSSFRow   firstRow = sheet.getRow(sheet.getFirstRowNum());
		
		if(firstRow.getPhysicalNumberOfCells() !=  columnNames.length) return false;
		
		//3.进行判断是否字段名称是否是一致的
		for(int  index = 0 ; index < firstRow.getPhysicalNumberOfCells() ; index++){
			HSSFCell  cell = firstRow.getCell(index);
			String   cellName = getCellValue(cell);
			if(!cellName.equals(columnNames[index])){
				flag = false;
				break;
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 判断Excel文档是否符合导入模板操作
	 * @param sheet   Excel文档
	 * @param modelFile 模板文件   columnNames Excel模板文档的列字段名称
	 * @return boolean false 不符合
	 * @throws IOException 
	 */
	
	public static boolean   isSuitModelFile(String modelFile,String... columnNames) throws IOException{
		boolean  flag = true;//操作标识
		//1.创建文件流
		FileInputStream  input = new  FileInputStream(modelFile);
		
		HSSFWorkbook  workBook = new  HSSFWorkbook(input);
		//2.读取Excel的所有行数
		HSSFSheet  sheet = workBook.getSheetAt(0);
		
		//1.判断参数
		if(sheet == null || columnNames == null || columnNames.length <= 0)  return false;
		
		//2.获取第一行进行比较
		HSSFRow   firstRow = sheet.getRow(sheet.getFirstRowNum());
		if(firstRow.getLastCellNum() !=  columnNames.length) return false;
		
		//3.进行判断是否字段名称是否是一致的
		for(int  index = 0 ; index < firstRow.getLastCellNum() ; index++){
			HSSFCell  cell = firstRow.getCell(index);
			String   cellName = getCellValue(cell).trim();//去除字符两边可能存在的空格字符
			if(!cellName.equals(columnNames[index])){
				flag = false;
				break;
			}
		}
		
		return flag;
	}	
	
	public static boolean   isSuitModelFile(FileInputStream inputStream,String... columnNames) throws IOException{
		
		boolean  flag = true;//操作标识
		
		HSSFWorkbook  workBook = new  HSSFWorkbook(inputStream);
		//2.读取Excel的所有行数
		HSSFSheet  sheet = workBook.getSheetAt(0);
		
		//1.判断参数
		if(sheet == null || columnNames == null || columnNames.length <= 0)  return false;
		
		//2.获取第一行进行比较
		HSSFRow   firstRow = sheet.getRow(sheet.getFirstRowNum());
		if(firstRow.getLastCellNum() !=  columnNames.length) return false;
		
		//3.进行判断是否字段名称是否是一致的
		for(int  index = 0 ; index < firstRow.getLastCellNum() ; index++){
			HSSFCell  cell = firstRow.getCell(index);
			String   cellName = getCellValue(cell).trim();//去除字符两边可能存在的空格字符
			if(!cellName.equals(columnNames[index])){
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	
	/**
	 * 导出Excel文件操作...通过Servlet的操作进行实现
	 * @param request
	 * @param response
	 * @param workBook
	 * @throws IOException
	 */
	public static  void  exportExcel(HttpServletRequest request, HttpServletResponse response , HSSFWorkbook workBook) throws IOException{
		   String   fileName = request.getParameter("fileName");
		   response.reset();
		   response.setContentType("application/vnd.ms-excel;charset=gbk");
		   response.setHeader("Content-Disposition", "attachment;filename="
		     + new String(fileName.getBytes(), "iso-8859-1"));
			OutputStream out = response.getOutputStream();
			workBook.write(out);
			out.flush();
			out.close();
			
	}
	
	
	
	/**
	 * test
	 * @param args
	 */
	public static  void  main(String[]  args){
		//测试改进
		List<Object[]> list = new ArrayList<Object[]>();
		list.addAll(ReadExcelToData("G:\\ZQCust_model.xls"));
		for(int i=0;i<list.size();i++){
			Object[] obj = list.get(i);
			for(int j=0;j<obj.length;j++){
				System.out.print(obj[j]+",");
			}
			System.out.print("\n");
		}
	}
}
	
		

		
	
	



	

