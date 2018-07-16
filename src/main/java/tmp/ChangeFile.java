package tmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import statistics.WriteExcel;

public class ChangeFile {
	
	public static void changFile() throws IOException {
		String srcpath = "E:\\第二批处理";
		String destpath = "E:\\第二批处理validate";
		String checkpath = "C:\\Users\\CVTE\\Desktop\\validation";
		File[] files = new File(checkpath).listFiles();
		for(File f : files) {
			String name = f.getName();
			File[] tmps = new File(checkpath + "/" + name).listFiles();
			for(File ff : tmps) {
				String img = ff.getName();
				File srcFile = new File(srcpath + "/" + img);
				File destFile = new File(destpath + "/" + img);
				FileUtils.copyFile(srcFile, destFile);
			}
		}
	}
	
	public static void exportFile() throws IOException {
		DecimalFormat df = new DecimalFormat("0000");
		// changeFile  名
		String oldpath = "E:\\第二批validate\\validation-new";
		String srcpath = "E:\\第二批处理validate";
		String destpath = "E:\\第二批处理Excel_validate\\validate";
		String excelpath = "E:\\第二批处理Excel_validate";
		List<ValidateData> list = new ArrayList<ValidateData>();
		List<String> listName = new ArrayList<String>();
		
		File[] files = new File(srcpath).listFiles();
		for(File f : files) {
			listName.add(f.getName());
		}
		
		List<String> list1 = ShuffleUtil.shuffleLabel(listName);
		for(int i = 0; i < list1.size(); i++) {
			
			String nam = list1.get(i);
			// 复制图片
			File srcFile = new File(srcpath + "/" + nam);
			File destFile = new File(destpath + "/V" + df.format((i + 1)) + ".jpg");
			FileUtils.copyFile(srcFile, destFile);
			
			ValidateData data = new ValidateData();
			File f = new File(oldpath + "/G/" + nam);
			if(f.exists()) {
				data.setId("" + (i + 1));
				data.setImgOldName(nam);
				data.setName("V" + df.format((i + 1)));
				data.setType("G");
			}else {
				data.setId("" + (i + 1));
				data.setImgOldName(nam);
				data.setName("V" + df.format((i + 1)));
				data.setType("N");
			}
			list.add(data);
		}
		
		//保存到Excel
		String title[] = {"ID","Name","ImgName","Type"};
        String fileName = "validateMessage";
        String fileType = "xlsx";
        WriteExcel.writerValidate(excelpath, fileName, fileType, list, title);
	}

	public static void main(String[] args) throws IOException {
		//changFile();
		
		//exportFile();
		
		//List<List<String>> list = readExcel("E:/第二批处理Excel_validate/validateMessage.xlsx");
		//exportExcel("E:/第二批处理Excel_validate", list);
		
		//List<String[]> list = POIUtil.readExcel(new File("E:/第二批处理Excel_validate/validateMessage.xlsx"));
		//exportExcelNew("E:/第二批处理Excel_validate", list);
		
		// readExcel and check
		List<String[]> list1 = POIUtil.readExcel(new File("E:/第二批处理Excel_validate/validate.xlsx"));
		List<String[]> list2 = POIUtil.readExcel(new File("E:/第二批处理Excel_validate/validate1.xlsx"));
		for(int i = 0; i < list1.size(); i++) {
			if(!list1.get(i)[0].equals(list2.get(i)[0])) {
				System.out.println(list1.get(i)[0] + "=" + list2.get(i)[0]);
			}
			if(!(list1.get(i)[1].equals(list2.get(i)[1]))) {
				System.out.println(list1.get(i)[1] + "=" + list2.get(i)[1]);
			}
		}
	}
	
	private static void exportExcelNew(String path, List<String[]> list) throws IOException {
		// 保存到Excel
		String title[] = { "ImgName", "Label" };
		String fileName = "validate1";
		String fileType = "xlsx";
		WriteExcel.writerValidateNew1(path, fileName, fileType, list, title);
	}

	public static void exportExcel(String path, List<List<String>> list) throws IOException {
		
		// 保存到Excel
		String title[] = { "ImgName", "Label" };
		String fileName = "validate";
		String fileType = "xlsx";
		WriteExcel.writerValidateNew(path, fileName, fileType, list, title);
	}

	public static List<List<String>> readExcel(String path) throws IOException {
		InputStream is = new FileInputStream(new File(path));
        // HSSFWorkbook 标识整个excel  
        //HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        int size = hssfWorkbook.getNumberOfSheets();
        // 循环每一页，并处理当前循环页
        for (int numSheet = 0; numSheet < size; numSheet++) {
            // HSSFSheet 标识某一页
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 处理当前页，循环读取每一行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                // HSSFRow表示行
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int minColIx = hssfRow.getFirstCellNum();
                int maxColIx = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                // 遍历改行，获取处理每个cell元素
                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                    // HSSFCell 表示单元格
                    XSSFCell cell = hssfRow.getCell(colIx);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(getStringVal(cell));
                }
                result.add(rowList);
            }
        }
        return result;
	}
	
	public static String getStringVal(XSSFCell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        case Cell.CELL_TYPE_NUMERIC:
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        default:
            return "";
        }
    }

}
