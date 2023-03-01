package com.rain.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.HistoryBean;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("*.ht")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       BookDao bookdao=new BookDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
	public void tohistory(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException{
		
		ArrayList<HistoryBean> list = bookdao.get_HistoryListInfo2(1);
		req.setAttribute("list", list);
		System.out.println(list);
		System.out.println("112");
		req.getRequestDispatcher("/admin_history.jsp").forward(req, resp);
	}

}
