package day16.servlet.json;

import day14.domain.Book;
import day14.utils.C3P0Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    @Test
    //1.使用JSONObject封装对象类型数据
    public void test1(){
        Book book = new Book();
        book.setId("123");
        book.setName("鲁滨逊漂流记");

        String s = JSONObject.fromObject(book).toString();
        System.out.println(s);
    }
    @Test
    //2.使用JSONArray封装list<>对象数据
    public void test2(){
        List<Book> list = new ArrayList<>();
        Book b1= new Book();
        b1.setId("111");
        b1.setName("xxx");

        Book b2= new Book();
        b2.setId("111");
        b2.setName("xxx");

        Book b3= new Book();
        b3.setId("111");
        b3.setName("xxx");

        list.add(b1);
        list.add(b2);
        list.add(b3);

        String s = JSONArray.fromObject(list).toString();
        System.out.println(s);
    }
    @Test
    //3.使用JSONConfig去除不要的字段
    public void test3() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
       List<Book> list = qr.query("select * from book", new BeanListHandler<Book>(Book.class));

        JsonConfig jc = new JsonConfig();
        jc.setExcludes(new String[]{"price","description","category","pnum"});
        String s = JSONArray.fromObject(list, jc).toString();
        System.out.println(s);
    }
}
