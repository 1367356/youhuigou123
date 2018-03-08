package excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import excel.pojo.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;


public class WriteExcel {
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ReadExcel obj = new ReadExcel();	
		File file = ResourceUtils.getFile("classpath:test1.xls");
		List excelList = obj.readExcel(file);
		
		Field[] fields = Product.class.getFields();
		writeExcel(excelList, fields.length, "D:/test.xlsx");
	}

	public static void writeExcel(List<Product> dataList, int cloumnCount,String finalXlsxPath){
		OutputStream out = null;
		try {
			// 获取总列数
			int columnNumCount = cloumnCount;
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPath);
			Workbook workBook = getWorkbok(finalXlsxFile);
			// sheet 对应一个工作页
			Sheet sheet = workBook.getSheetAt(0);
			/**
			 * 删除原有数据，除了属性列
			 */
			int rowNumber = sheet.getLastRowNum();	// 第一行从0开始算
			System.out.println("原始数据总行数，包括属性列：" + rowNumber);
			for (int i = 1; i <= rowNumber; i++) {
				Row row = sheet.getRow(i);
				sheet.removeRow(row);
			}
			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out =  new FileOutputStream(finalXlsxPath);
			workBook.write(out);
			/**
			 * 往Excel中写新数据
			 */
			for (int j = 1; j < dataList.size(); j++) {
			    // 创建一行：从第二行开始，跳过属性列
			    Row row = sheet.createRow(j);
			    // 得到要插入的每一条记录
			    Product product = dataList.get(j);
//			    String name = dataMap.get("BankName").toString();
//			    String address = dataMap.get("Addr").toString();
//			    String phone = dataMap.get("Phone").toString();
//			    for (int k = 0; k <= columnNumCount; k++) {
				//添加一行
				Cell first = row.createCell(0);
				first.setCellValue(product.getId());
				
				Cell second = row.createCell(1);
				second.setCellValue(product.getName());
		
				Cell third = row.createCell(2);
				third.setCellValue(product.getImage());
				
				Cell four = row.createCell(3);
				four.setCellValue(product.getCategory());
				
				Cell five = row.createCell(4);
				five.setCellValue(product.getPrice());
				
				Cell six = row.createCell(5);
				six.setCellValue(product.getSale());
				
				Cell seven = row.createCell(6);
				seven.setCellValue(product.getPlatform());
				
				Cell eight = row.createCell(7);
				eight.setCellValue(product.getDisSum());
				
				Cell nine = row.createCell(8);
				nine.setCellValue(product.getDisSize());
				
				Cell ten = row.createCell(9);
				ten.setCellValue(product.getStartTime());
				
				Cell eleven = row.createCell(10);
				eleven.setCellValue(product.getEndTime());
				
				Cell twelve = row.createCell(11);
				twelve.setCellValue(product.getDisherf());
				
			  //  }
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out =  new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("数据导出成功");
	}

	/**
	 * 判断Excel的版本,获取Workbook
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(File file) throws IOException{
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if(file.getName().endsWith(EXCEL_XLS)){	 //Excel&nbsp;2003
			wb = new HSSFWorkbook(in);
		}else if(file.getName().endsWith(EXCEL_XLSX)){	// Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}
}