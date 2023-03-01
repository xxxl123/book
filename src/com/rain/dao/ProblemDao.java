package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rain.bean.AdminBean;
import com.rain.bean.ProblemBean;
import com.rain.util.DBUtil;

public class ProblemDao {
	/**
	 * ��ȡ���з����������Ϣ������������ʽ
	 * 
	 * @return
	 */
	public ArrayList<ProblemBean> get_ListInfo() {
		ArrayList<ProblemBean> tag_Array = new ArrayList<ProblemBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from problem";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				ProblemBean tag = new ProblemBean();
				tag.setPid(rs.getInt("pid"));
				tag.setAid(rs.getInt("aid"));
				tag.setName(rs.getString("name"));
				tag.setPage(rs.getString("page"));
				tag.setBody(rs.getString("body"));
				tag.setPhone(rs.getString("phone"));
				tag.setStatus(rs.getString("status"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * ��ȡ���з����������Ϣ������������ʽ
	 * 
	 * @return
	 */
	public ArrayList<ProblemBean> get_ListInfo2(String aid) {
		ArrayList<ProblemBean> tag_Array = new ArrayList<ProblemBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from problem where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				ProblemBean tag = new ProblemBean();
				tag.setPid(rs.getInt("pid"));
				tag.setAid(rs.getInt("aid"));
				tag.setName(rs.getString("name"));
				tag.setPage(rs.getString("page"));
				tag.setBody(rs.getString("body"));
				tag.setPhone(rs.getString("phone"));
				tag.setStatus(rs.getString("status"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * ���һ����������
	 * 
	 * @param adminbean
	 * @param name
	 * @param page
	 * @param body
	 * @param phone
	 */
	public void addProblem(AdminBean adminbean, String name, String page, String body, String phone) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert into problem(aid,name,page,body,phone) values(?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, adminbean.getAid());
			stm.setString(2, name);
			stm.setString(3, page);
			stm.setString(4, body);
			stm.setString(5, phone);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ɾ��һ����������
	 * 
	 * @param pid
	 */
	public void deleteProblem(int pid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from problem where pid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, pid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �޸ķ��������״̬
	 * 
	 * @param pid
	 * @param status
	 */
	public void updateProblem(int pid, String status) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update problem set status=? where pid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, status);
			stm.setInt(2, pid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���ҷ������⣬������������ƣ�ʹ��like����ģ����ѯ��Ȼ�󷵻�һ��ArrayList��������
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<ProblemBean> getLikeList(String name) {
		// TODO Auto-generated method stub
		ArrayList<ProblemBean> tag_Array = new ArrayList<ProblemBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from problem where name like '%" + name + "%' or page like '%" + name
				+ "%' or body like '%" + name + "%' or status like '%" + name + "%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				ProblemBean tag = new ProblemBean();
				tag.setPid(rs.getInt("pid"));
				tag.setAid(rs.getInt("aid"));
				tag.setName(rs.getString("name"));
				tag.setPage(rs.getString("page"));
				tag.setBody(rs.getString("body"));
				tag.setPhone(rs.getString("phone"));
				tag.setStatus(rs.getString("status"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
}
