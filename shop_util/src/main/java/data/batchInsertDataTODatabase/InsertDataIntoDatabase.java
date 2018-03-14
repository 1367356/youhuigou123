package data.batchInsertDataTODatabase;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import data.excel.ReadExcel;
import data.pojo.Product;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class InsertDataIntoDatabase {

    @Test
    public void test() throws Exception {
        ReadExcel readExcel=new ReadExcel();
        File file = ResourceUtils.getFile("D:/javatest/test2.xls");
//        File file = ResourceUtils.getFile("classpath:test1.xls");
        List listProduct = readExcel.readExcel(file);
        insertDataBySql(listProduct);
    }
    
    public void insertDataBySql(List<Product> listProduct) throws Exception{

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://" +
                "192.168.100.91:3306/youhuigou","root", "1367356");
        // 关闭事务自动提交
        con.setAutoCommit(false);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
        TimeZone t = sdf.getTimeZone();
        t.setRawOffset(0);
        sdf.setTimeZone(t);
        Long startTime =System.currentTimeMillis();

        PreparedStatement pst =(PreparedStatement) con.prepareStatement("insert into product values (0,?,?,?,?,?,?,?,?,?,?,?,?)");  //?占位符

        Iterator<Product> iterator = listProduct.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            pst.setString(1,product.getId());  //i 代表占位符的数据
            pst.setString(2,product.getName());
            pst.setString(3,product.getImage());
            pst.setString(4,product.getCategory());
            pst.setString(5,product.getPrice());
            pst.setString(6,product.getSale());
            pst.setString(7,product.getPlatform());
            pst.setString(8,product.getDisSum());
            pst.setString(9,product.getDisSize());
            pst.setString(10,product.getStartTime());
            pst.setString(11,product.getEndTime());
            pst.setString(12,product.getDisherf());

            // 把一个SQL命令加入命令列表
            pst.addBatch();
        }
        // 执行批量更新
        pst.executeBatch();
        // 语句执行完毕，提交本事务
        con.commit();

        Long endTime =System.currentTimeMillis();
        System.out.println("用时：" + sdf.format(new Date(endTime - startTime)));

        pst.close();
        con.close();
    }
}
