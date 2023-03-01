package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// ���ñ�������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ����Ϊ�˼򵥣�������tip�������������޸����빦�ܣ������޸ĸ������ϵĹ��ܣ�tip=1Ϊ�޸�����
		int tip = Integer.parseInt(request.getParameter("tip"));
		// ��ȡ��������ҳ����ļ����ƣ�����ڶ�Ӧ��jsp����ı���д���޸���ɺ�Ϳ���ֱ�ӷ��ض�Ӧ��ҳ��
		String url = request.getParameter("url");
		HttpSession session = request.getSession();
		AdminBean adminbean = new AdminBean();
		// ��ȡ�浽session��aid
		String aid = (String) session.getAttribute("aid");
		AdminDao admindao = new AdminDao();
		// ͨ��aid��ȡ�����ߵ���Ϣ
		adminbean = admindao.get_AidInfo2(aid);
		// �޸�����
		if (tip == 1) {
			// ��ȡ������ľ����룬������
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			// ��ȡ�������ݱ��е�����
			String old_password = adminbean.getPassword();
			// �Ծ�������бȽϣ������ͬ���޸ģ�����ͬ��ֱ���˳�
			if (old_password.equals(password)) {
				admindao.updateUser(adminbean.getAid(), adminbean.getUsername(), password2, adminbean.getName(),
						adminbean.getEmail(), adminbean.getPhone(), adminbean.getLend_num(), adminbean.getMax_num());
				response.sendRedirect("/books/" + url + ".jsp");
			} else {
				out.write("<script type='text/javascript'>alert('password error');location.href='" + url
						+ ".jsp';  </script>");
			}
		} else {
			// �޸ĸ�������
			// ��ȡ�������Ϣ
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			// �޸��������Ϣ�����ݱ���
			admindao.updateUser(adminbean.getAid(), adminbean.getUsername(), adminbean.getPassword(), name, email,
					phone, adminbean.getLend_num(), adminbean.getMax_num());
			response.sendRedirect("/books/" + url + ".jsp");
		}
	}

}
