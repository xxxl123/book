package com.rain.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("*.bk")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookDao bookdao=new BookDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1、获取请求的名称 localhost:8080/hsdsx/login
		String url = request.getRequestURI();
		// 从url中截取虚拟请求路径
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		// 通过Java中的类的反射机制 根据虚拟请求名称 自动去找当前类中相同名字的方法 然后执行
		// 1、找到名字相同的方法 getClass()获取当前类 getDeclaredMethod所有方法
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			// 2、调用执行该方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tobookshow(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<BookBean> list = bookdao.get_ListInfo();
		
		/*System.out.println("112");
		req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);*/
		if (list.size() > 5) {//判断list长度
			
            List<BookBean> newList = list.subList(0, 5);//取前四条数据
            req.setAttribute("list", newList);
            req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);
            
        } else {
        	req.setAttribute("list", list);
        	req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);
            
        }
	}
	
	public void tobookshowByid(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			ArrayList<BookBean> list = bookdao.get_ListInfo();
			int Tlist = list.size();
			
			req.setAttribute("Tlist", Tlist);
			int length = list.size();
			int fpage = (id-1)*5;
			int bpage = fpage+5;
			int b2page = length-fpage;
			if (list.size() > fpage) {//判断list长度
				if(b2page>5){
					 List<BookBean> newList = list.subList(fpage, bpage);//取前四条数据
			         req.setAttribute("list", newList);
			         req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);
				}else{
					 //List<BookBean> newList = list.subLis(fpage, fpage+bpage);//取前四条数据
					 List<BookBean> newList = list.subList(fpage, fpage+b2page);//取前四条数据
			         req.setAttribute("list", newList);
			         req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);
				}  
	        } else {
	        	
	        	req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);  
	        }
	}
	protected void selectBookServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 	request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        int tip = Integer.parseInt(request.getParameter("tip"));
	        String name = request.getParameter("name");
	        BookDao bookdao = new BookDao();
	        ArrayList<BookBean> data = bookdao.getLikeList(name);
	        request.setAttribute("list", data);
	        System.out.println(data);
	        String url = "";
	        if(tip == 1)
	            url = response.encodeURL("admin_book.jsp");
	        else
	            url = response.encodeURL("select.jsp");
	        request.getRequestDispatcher(url).forward(request, response);
	}
	protected void AddBookServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取要添加图书的信息
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		int num = Integer.parseInt(request.getParameter("num"));
		BookDao bookdao = new BookDao();
		// 调用函数，存入图书
		bookdao.addBook(card, name, type, autho, press, num);
		response.sendRedirect("tobookshow.bk");
	}
	protected void updateBookServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		int num = Integer.parseInt(request.getParameter("num"));
		int bid = Integer.parseInt(request.getParameter("updatebid"));
		BookDao bookdao = new BookDao();
		bookdao.updateBook(bid, card, name, type, autho, press, num);
		response.sendRedirect("tobookshow.bk");
	}
	protected void addBookServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取要添加图书的信息
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		int num = Integer.parseInt(request.getParameter("num"));
		BookDao bookdao = new BookDao();
		// 调用函数，存入图书
		bookdao.addBook(card, name, type, autho, press, num);
		response.sendRedirect("tobookshow.bk");
	}
	protected void deleteBookServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int bid = Integer.parseInt(request.getParameter("bid"));
		BookDao bookdao = new BookDao();
		bookdao.deleteBook(bid);
		response.sendRedirect("tobookshow.bk");
	}
}
