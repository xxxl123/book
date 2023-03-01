package com.rain.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.BookBean;
import com.rain.bean.TypeBean;
import com.rain.dao.BookDao;
import com.rain.dao.TypeDao;


/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("*.tp")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      TypeDao typedao=new TypeDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
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
	public void tobooktypeshow(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<TypeBean> list = typedao.get_ListInfo();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin_booktype.jsp").forward(req, resp);
	}
	protected void addBookTypeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡͼ����������
		String name = request.getParameter("name");
		TypeDao typedao = new TypeDao();
		// ���ú�������ͼ�������Ϣ
		typedao.addBookType(name);
		response.sendRedirect("tobooktypeshow.tp");
	}
	protected void deleteBookTypeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int tid = Integer.parseInt(request.getParameter("tid"));
		TypeDao typedao = new TypeDao();
		typedao.deleteBookType(tid);
		response.sendRedirect("tobooktypeshow.tp");
	}
	protected void updateBookTypeServlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		int tid = Integer.parseInt(request.getParameter("tid"));
		TypeDao typedao = new TypeDao();
		typedao.updateTypeBook(tid, name);
		response.sendRedirect("tobooktypeshow.tp");
	}

}
