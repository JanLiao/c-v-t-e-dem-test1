package statistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.util.ArrayList;  
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.CellStyle;  
import org.apache.poi.ss.usermodel.Font;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tmp.ValidateData;  
  
/**  
 * 从excel读取数据/往excel中写入 excel有表头，表头每列的内容对应实体类的属性  
 *   
 * @author jan
 *   
 */    
public class WriteExcel{    
  
  
    public static void main(String[] args) throws IOException {    
        String path = "D:/test";    
        String fileName = "test04";    
        String fileType = "xlsx";    
        List<IOU> list = new ArrayList<>();  
        for(int i=0; i<6; i++){  
        	IOU bean = new IOU();    
            bean.setId(i);  
            bean.setName("4444444444"+i+","+"55544444444444"+i+","+"999999999999999"+i);  
            bean.setDiscIOU(0.9);  
            bean.setCupIOU(0.8);               
            list.add(bean);
        }  
        String title[] = {"ID","imgName","discIOU","cupIOU"};    
//        createExcel("E:/test.xlsx","sheet1",fileType,title);    
          
        writer(path, fileName, fileType, list, title);    
    }    
      
    @SuppressWarnings("resource")  
    public static void writer(String path, String fileName,String fileType,List<IOU> list,String titleRow[]) throws IOException {    
        Workbook wb = null;   
        String excelPath = path+File.separator+fileName+"."+fileType;  
        File file = new File(excelPath);  
        Sheet sheet =null;  
        //创建工作文档对象     
        if (!file.exists()) {  
            if (fileType.equals("xls")) {  
                wb = new HSSFWorkbook();  
                  
            } else if(fileType.equals("xlsx")) {  
                  
                    wb = new XSSFWorkbook();  
            } else {  
                throw new IOException("文件格式不正确");  
            }  
            //创建sheet对象     
            sheet = (Sheet) wb.createSheet("sheet1");    
            OutputStream outputStream = new FileOutputStream(excelPath);  
            wb.write(outputStream);  
            outputStream.flush();  
            outputStream.close();  
              
        } else {  
            if (fileType.equals("xls")) {    
                wb = new HSSFWorkbook();    
                  
            } else if(fileType.equals("xlsx")) {   
                wb = new XSSFWorkbook();    
                  
            } else {    
                throw new IOException("文件格式不正确");  
            }    
        }  
         //创建sheet对象     
        if (sheet==null) {  
            sheet = (Sheet) wb.createSheet("sheet1");    
        }  
          
        //添加表头    
        Row row = sheet.createRow(0);  
        Cell cell = row.createCell(0);  
        row.setHeight((short) 540);   
        cell.setCellValue("眼底图   ");    //创建第一行  
          
        //CellStyle style = wb.createCellStyle(); // 样式对象  
        XSSFCellStyle style = (XSSFCellStyle)wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色    
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);   
          
        //style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直    
        //style.setAlignment(CellStyle.ALIGN_CENTER);// 水平    
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //style.setAlignment((short) 2);
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行  
         
        cell.setCellStyle(style); // 样式，居中  
          
        Font font = wb.createFont();    
        //font.setBoldweight(Font.BOLDWEIGHT_BOLD);    
        font.setFontName("宋体");    
        font.setFontHeight((short) 280);    
        style.setFont(font);    
        // 单元格合并        
        // 四个参数分别是：起始行，起始列，结束行，结束列        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));    
        //sheet.autoSizeColumn(5200);  
          
        row = sheet.createRow(1);    //创建第二行      
        for(int i = 0;i < titleRow.length;i++){    
            cell = row.createCell(i);    
            cell.setCellValue(titleRow[i]);    
            cell.setCellStyle(style); // 样式，居中  
            sheet.setColumnWidth(i, 20 * 256);   
        }    
        row.setHeight((short) 540);   
  
        //循环写入行数据     
        for (int i = 0; i < list.size(); i++) {    
            row = (Row) sheet.createRow(i+2);    
            row.setHeight((short) 500);   
            row.createCell(0).setCellValue(( list.get(i)).getId());  
            row.createCell(1).setCellValue(( list.get(i)).getName());
            row.createCell(2).setCellValue(( list.get(i)).getDiscIOU());  
            row.createCell(3).setCellValue(( list.get(i)).getCupIOU());  
            row.createCell(4).setCellValue((list.get(i)).getFoveaX());
            row.createCell(5).setCellValue((list.get(i)).getFoveaY());
        }    
          
        //创建文件流     
        OutputStream stream = new FileOutputStream(excelPath);    
        //写入数据     
        wb.write(stream);    
        //关闭文件流     
        stream.close();    
    }

	public static void writerValidate(String excelpath, String fileName, String fileType, List<ValidateData> list,
			String[] title) throws IOException {
		Workbook wb = null;   
        String excelPath = excelpath+File.separator+fileName+"."+fileType;  
        File file = new File(excelPath);  
        Sheet sheet =null;  
        //创建工作文档对象     
        if (!file.exists()) {  
            if (fileType.equals("xls")) {  
                wb = new HSSFWorkbook();  
                  
            } else if(fileType.equals("xlsx")) {  
                  
                    wb = new XSSFWorkbook();  
            } else {  
                throw new IOException("文件格式不正确");  
            }  
            //创建sheet对象     
            sheet = (Sheet) wb.createSheet("sheet1");    
            OutputStream outputStream = new FileOutputStream(excelPath);  
            wb.write(outputStream);  
            outputStream.flush();  
            outputStream.close();  
              
        } else {  
            if (fileType.equals("xls")) {    
                wb = new HSSFWorkbook();    
                  
            } else if(fileType.equals("xlsx")) {   
                wb = new XSSFWorkbook();    
                  
            } else {    
                throw new IOException("文件格式不正确");  
            }    
        }  
         //创建sheet对象     
        if (sheet==null) {  
            sheet = (Sheet) wb.createSheet("sheet1");    
        }  
          
        //添加表头    
        Row row = sheet.createRow(0);  
        Cell cell = row.createCell(0);  
        row.setHeight((short) 540);   
        cell.setCellValue("眼底图  validate");    //创建第一行  
          
        //CellStyle style = wb.createCellStyle(); // 样式对象  
        XSSFCellStyle style = (XSSFCellStyle)wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色    
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);   
          
        //style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直    
        //style.setAlignment(CellStyle.ALIGN_CENTER);// 水平    
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //style.setAlignment((short) 2);
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行  
         
        cell.setCellStyle(style); // 样式，居中  
          
        Font font = wb.createFont();    
        //font.setBoldweight(Font.BOLDWEIGHT_BOLD);    
        font.setFontName("宋体");    
        font.setFontHeight((short) 280);    
        style.setFont(font);    
        // 单元格合并        
        // 四个参数分别是：起始行，起始列，结束行，结束列        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));    
        //sheet.autoSizeColumn(5200);  
          
        row = sheet.createRow(1);    //创建第二行      
        for(int i = 0;i < title.length;i++){    
            cell = row.createCell(i);    
            cell.setCellValue(title[i]);    
            cell.setCellStyle(style); // 样式，居中  
            sheet.setColumnWidth(i, 20 * 256);   
        }    
        row.setHeight((short) 540);   
  
        //循环写入行数据     
        for (int i = 0; i < list.size(); i++) {    
            row = (Row) sheet.createRow(i+2);    
            row.setHeight((short) 500);   
            row.createCell(0).setCellValue(( list.get(i)).getId());  
            row.createCell(1).setCellValue(( list.get(i)).getName());
            row.createCell(2).setCellValue(( list.get(i)).getImgOldName());  
            row.createCell(3).setCellValue(( list.get(i)).getType());  
        }    
          
        //创建文件流     
        OutputStream stream = new FileOutputStream(excelPath);    
        //写入数据     
        wb.write(stream);    
        //关闭文件流     
        stream.close();   
	}

	public static void writerValidateNew(String excelpath, String fileName, String fileType, List<List<String>> list,
			String[] title) throws IOException {
		Workbook wb = null;   
        String excelPath = excelpath+File.separator+fileName+"."+fileType;  
        File file = new File(excelPath);  
        Sheet sheet =null;  
        //创建工作文档对象     
        if (!file.exists()) {  
            if (fileType.equals("xls")) {  
                wb = new HSSFWorkbook();  
                  
            } else if(fileType.equals("xlsx")) {  
                  
                    wb = new XSSFWorkbook();  
            } else {  
                throw new IOException("文件格式不正确");  
            }  
            //创建sheet对象     
            sheet = (Sheet) wb.createSheet("sheet1");    
            OutputStream outputStream = new FileOutputStream(excelPath);  
            wb.write(outputStream);  
            outputStream.flush();  
            outputStream.close();  
              
        } else {  
            if (fileType.equals("xls")) {    
                wb = new HSSFWorkbook();    
                  
            } else if(fileType.equals("xlsx")) {   
                wb = new XSSFWorkbook();    
                  
            } else {    
                throw new IOException("文件格式不正确");  
            }    
        }  
         //创建sheet对象     
        if (sheet==null) {  
            sheet = (Sheet) wb.createSheet("sheet1");    
        }  
          
        //添加表头    
        Row row = sheet.createRow(0);  
        Cell cell = row.createCell(0);  
        row.setHeight((short) 540);   
        cell.setCellValue("眼底图  validate");    //创建第一行  
          
        //CellStyle style = wb.createCellStyle(); // 样式对象  
        XSSFCellStyle style = (XSSFCellStyle)wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色    
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);   
          
        //style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直    
        //style.setAlignment(CellStyle.ALIGN_CENTER);// 水平    
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //style.setAlignment((short) 2);
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行  
         
        cell.setCellStyle(style); // 样式，居中  
          
        Font font = wb.createFont();    
        //font.setBoldweight(Font.BOLDWEIGHT_BOLD);    
        font.setFontName("宋体");    
        font.setFontHeight((short) 280);    
        style.setFont(font);    
        // 单元格合并        
        // 四个参数分别是：起始行，起始列，结束行，结束列        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));    
        //sheet.autoSizeColumn(5200);  
          
        row = sheet.createRow(1);    //创建第二行      
        for(int i = 0;i < title.length;i++){    
            cell = row.createCell(i);    
            cell.setCellValue(title[i]);    
            cell.setCellStyle(style); // 样式，居中  
            sheet.setColumnWidth(i, 20 * 256);   
        }    
        row.setHeight((short) 540);   
  
        //循环写入行数据     
        for (int i = 0; i < list.size(); i++) {    
            row = (Row) sheet.createRow(i+2);    
            row.setHeight((short) 500);
            String imgname = list.get(i).get(1);
            row.createCell(0).setCellValue(imgname.toLowerCase() + ".jpg");
            String typeimg = list.get(i).get(3);
            int tp = 0;
            if("G".equals(typeimg)) {
            	tp = 1;
            }else if("N".equals(typeimg)) {
            	tp = 0;
            }
            row.createCell(1).setCellValue("" + tp);
        }    
          
        //创建文件流     
        OutputStream stream = new FileOutputStream(excelPath);    
        //写入数据     
        wb.write(stream);    
        //关闭文件流     
        stream.close();
	}

	public static void writerValidateNew1(String excelpath, String fileName, String fileType, List<String[]> list,
			String[] title) throws IOException {
		Workbook wb = null;   
        String excelPath = excelpath+File.separator+fileName+"."+fileType;  
        File file = new File(excelPath);  
        Sheet sheet =null;  
        //创建工作文档对象     
        if (!file.exists()) {  
            if (fileType.equals("xls")) {  
                wb = new HSSFWorkbook();  
                  
            } else if(fileType.equals("xlsx")) {  
                  
                    wb = new XSSFWorkbook();  
            } else {  
                throw new IOException("文件格式不正确");  
            }  
            //创建sheet对象     
            sheet = (Sheet) wb.createSheet("sheet1");    
            OutputStream outputStream = new FileOutputStream(excelPath);  
            wb.write(outputStream);  
            outputStream.flush();  
            outputStream.close();  
              
        } else {  
            if (fileType.equals("xls")) {    
                wb = new HSSFWorkbook();    
                  
            } else if(fileType.equals("xlsx")) {   
                wb = new XSSFWorkbook();    
                  
            } else {    
                throw new IOException("文件格式不正确");  
            }    
        }  
         //创建sheet对象     
        if (sheet==null) {  
            sheet = (Sheet) wb.createSheet("sheet1");    
        }  
          
        //添加表头    
        Row row = sheet.createRow(0);  
        Cell cell = row.createCell(0);  
        row.setHeight((short) 540);   
        cell.setCellValue("眼底图  validate");    //创建第一行  
          
        //CellStyle style = wb.createCellStyle(); // 样式对象  
        XSSFCellStyle style = (XSSFCellStyle)wb.createCellStyle();
        // 设置单元格的背景颜色为淡蓝色    
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);   
          
        //style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直    
        //style.setAlignment(CellStyle.ALIGN_CENTER);// 水平    
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //style.setAlignment((short) 2);
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行  
         
        cell.setCellStyle(style); // 样式，居中  
          
        Font font = wb.createFont();    
        //font.setBoldweight(Font.BOLDWEIGHT_BOLD);    
        font.setFontName("宋体");    
        font.setFontHeight((short) 280);    
        style.setFont(font);    
        // 单元格合并        
        // 四个参数分别是：起始行，起始列，结束行，结束列        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));    
        //sheet.autoSizeColumn(5200);  
          
        row = sheet.createRow(1);    //创建第二行      
        for(int i = 0;i < title.length;i++){    
            cell = row.createCell(i);    
            cell.setCellValue(title[i]);    
            cell.setCellStyle(style); // 样式，居中  
            sheet.setColumnWidth(i, 20 * 256);   
        }    
        row.setHeight((short) 540);   
  
        //循环写入行数据     
        for (int i = 0; i < list.size(); i++) {    
            row = (Row) sheet.createRow(i+2);    
            row.setHeight((short) 500);
            String imgname = list.get(i)[1];
            row.createCell(0).setCellValue(imgname.toLowerCase() + ".jpg");
            String typeimg = list.get(i)[3];
            int tp = 0;
            if("G".equals(typeimg)) {
            	tp = 1;
            }else if("N".equals(typeimg)) {
            	tp = 0;
            }
            row.createCell(1).setCellValue(tp);
        }    
          
        //创建文件流     
        OutputStream stream = new FileOutputStream(excelPath);    
        //写入数据     
        wb.write(stream);    
        //关闭文件流     
        stream.close();
	}    
      
}  
