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
		// 1����ȡ��������� localhost:8080/hsdsx/login
		String url = request.getRequestURI();
		// ��url�н�ȡ��������·��
		String methodName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		// ͨ��Java�е���ķ������ ���������������� �Զ�ȥ�ҵ�ǰ������ͬ���ֵķ��� Ȼ��ִ��
		// 1���ҵ�������ͬ�ķ��� getClass()��ȡ��ǰ�� getDeclaredMethod���з���
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			// 2������ִ�и÷���
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
		if (list.size() > 5) {//�ж�list����
			
            List<BookBean> newList = list.subList(0, 5);//ȡǰ��������
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
			if (list.size() > fpage) {//�ж�list����
				if(b2page>5){
					 List<BookBean> newList = list.subList(fpage, bpage);//ȡǰ��������
			         req.setAttribute("list", newList);
			         req.getRequestDispatcher("/admin_book.jsp").forward(req, resp);
				}else{
					 //List<BookBean> newList = list.subLis(fpage, fpage+bpage);//ȡǰ��������
					 List<BookBean> newList = list.subList(fpage, fpage+b2page);//ȡǰ��������
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
		// ��ȡҪ���ͼ�����Ϣ
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		int num = Integer.parseInt(request.getParameter("num"));
		BookDao bookdao = new BookDao();
		// ���ú���������ͼ��
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
		// ��ȡҪ���ͼ�����Ϣ
		String card = request.getParameter("card");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		int num = Integer.parseInt(request.getParameter("num"));
		BookDao bookdao = new BookDao();
		// ���ú���������ͼ��
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
