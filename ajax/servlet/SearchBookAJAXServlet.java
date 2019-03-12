package day16.servlet;

import day14.service.BookService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchBookAJAXServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//只能解决post方式的乱码
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");

        BookService bs = new BookService();

        List<Object> list = bs.searchBookByName(name);
//        String str = "";
//        for(int i = 0; i < list.size();i++){
//            if(i>0){
//                str+=",";
//            }
//            str += list.get(i);
//        }
        String str = JSONArray.fromObject(list).toString();
        System.out.println(str);
        response.getWriter().write(str);
    }
}
