package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.BookBean;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class bdtimesServlet
 */
@WebServlet("/bdtimesServlet")
public class bdtimesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bdtimesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��Ϊ�ڹ���Ա����Ͷ��߽��涼�в��ҹ��ܣ�Ϊ�˽����ҵĽ��������ȷ��ҳ�棬������tip��tip=1��ʾ����Ա����
		int tip = Integer.parseInt(request.getParameter("tip"));
		String name = request.getParameter("name");
		BookDao bookdao = new BookDao();
		ArrayList<BookBean> data = bookdao.getLikeList(name);
		// ����ȡ�Ľ������������
		request.setAttribute("data", data);
		String url = "";
		// ת����ͬ�Ľ���
		if (tip == 1) {
			url = response.encodeURL("admin_bdtimes.jsp");
		} else {
			url = response.encodeURL("bdtimes.jsp");
		}
		// ������ת��
		request.getRequestDispatcher(url).forward(request, response);
	}
}
