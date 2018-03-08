package excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import excel.pojo.Product;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ReadExcel {

	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	public List readExcel(File file) {
		int[] arr={0,1,2,4,6,7,13,15,17,18,19,20};
		try {
			// 创建输入流，读取Excel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			// jxl提供的Workbook类
			Workbook wb = Workbook.getWorkbook(is);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			for (int index = 0; index < 1; index++) {
				List<Product> outerList=new ArrayList<Product>();
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheet(index);
				// sheet.getRows()返回该页的总行数
				for (int i = 1; i < sheet.getRows(); i++) {

					List innerList=new ArrayList();
					// sheet.getColumns()返回该页的总列数
					for(int j:arr){
						String cellinfo = sheet.getCell(j, i).getContents();
						innerList.add(cellinfo);
					}
					
//					for (int j = 0; j < sheet.getColumns(); j++) {
//						String cellinfo = sheet.getCell(j, i).getContents();
//						innerList.add(cellinfo);
//					}
					Product product=new Product(null, null, null, null, null, null, null, null, null, null, null, null);
					product.setId(innerList.get(0).toString());
					product.setName(innerList.get(1).toString());
					product.setImage(innerList.get(2).toString());
					product.setCategory(innerList.get(3).toString());
					product.setPrice(innerList.get(4).toString());
					product.setSale(innerList.get(5).toString());
					product.setPlatform(innerList.get(6).toString());
					product.setDisSum(innerList.get(7).toString());
					product.setDisSize(innerList.get(8).toString());
					product.setStartTime(innerList.get(9).toString());
					product.setEndTime(innerList.get(10).toString());
					product.setDisherf(innerList.get(11).toString());
					
					outerList.add(product);
				}
				return outerList;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
