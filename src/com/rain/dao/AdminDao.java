package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.util.DBUtil;

/**
 * �йض����˺ŵ��������ݿ��������¼��֤��ע�ᣬ�޸��˺ţ��޸�����
 */
public class AdminDao {

	/**
	 * ��¼��֤���ܣ������û��������룬�����ݿ��в��ң�����ҵ��ˣ�����true��û�ҵ��򷵻�false
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean Login_verify(String username, String password) {
		Connection conn = DBUtil.getConnectDb();
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "select * from admin where username='" + username + " 'and password='" + password + "'";
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return false;
	}

	/**
	 * ע���˺ŵĺ����������˺ţ����룬���������䣬�ֻ��ţ������������ɽ�����
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phone
	 * @param times
	 * @param lend_num
	 * @param max_num
	 */
	public void Register(String username, String password, String name, String email, String phone, int lend_num,
			int max_num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert into admin(status,username,password,name,email,phone,lend_num,max_num) values(?,?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 1);
			stm.setString(2, username);
			stm.setString(3, password);
			stm.setString(4, name);
			stm.setString(5, email);
			stm.setString(6, phone);
			stm.setInt(7, lend_num);
			stm.setInt(8, max_num);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��������Ա�˺ţ������˺ţ����룬���������䣬�ֻ���
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phone
	 * @param times
	 * @param lend_num
	 * @param max_num
	 */
	public void Register2(String username, String password, String name, String email, String phone) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert into admin(status,username,password,name,email,phone) values(?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 2);
			stm.setString(2, username);
			stm.setString(3, password);
			stm.setString(4, name);
			stm.setString(5, email);
			stm.setString(6, phone);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���ݴ�����˺ţ����룬�����Ҷ�Ӧ�Ķ�����Ϣ������һ��AdminBean���ͣ�
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminBean getAdminInfo(String username, String password) {
		// TODO Auto-generated method stub
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username= '"+username+"' and password= '"+password+"'";
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * ��ȡȫ���û�����Ϣ������sql����е�status=1����ʾֻ���Ҷ��ߣ�����ʾ����Ա��
	 * 
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
				tag_Array.add(adminbean);
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
	 * ��ȡȫ���û�����Ϣ������sql����е�status=2����ʾֻ���ҹ���Ա������ʾ���ߵ�
	 * 
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo2() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=2";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				tag_Array.add(adminbean);
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
	 * ��ȡȫ���û�����Ϣ����������sql����е�status=1����ʾֻ���Ҷ��ߣ�����ʾ����Ա��
	 * 
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo3() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1 order by times desc";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
				tag_Array.add(adminbean);
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
	 * ���ݴ����aid�����ҵ���Ӧ�Ķ��ߵ�ȫ����Ϣ������һ��AdminBean���͵�����
	 * 
	 * @param aid
	 * @return
	 */
	public AdminBean get_AidInfo(int aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * ���ݴ����aid�����ҵ���Ӧ�Ķ��ߵ�ȫ����Ϣ������һ��AdminBean���͵����ݣ�����һ�����ƣ�ֻ��aid������ΪString
	 * 
	 * @param aid
	 * @return
	 */
	public AdminBean get_AidInfo2(String aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * �޸Ķ��ߵ���Ϣ
	 */
	public void updateUser(int aid, String username, String password, String name, String email, String phone,
			int lend_num, int max_num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, lend_num);
			stm.setInt(7, max_num);
			stm.setInt(8, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �޸Ĺ���Ա����Ϣ
	 */
	public void updateAdmin(int aid, String username, String password, String name, String email, String phone) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=? where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ɾ���û�����Ϣ�����ݴ����aid��Ϊ����
	 * 
	 * @param aid
	 */
	public void deleteUser(int aid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ɾ������Ա����Ϣ�����ݴ����aid��Ϊ����
	 * 
	 * @param aid
	 */
	public void deleteAdmin(int aid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �����û���������������ƣ�ʹ��like����ģ����ѯ��Ȼ�󷵻�һ��ArrayList��������
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<AdminBean> getLikeList(String name) {
		// TODO Auto-generated method stub
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where name like '%" + name + "%' or username like '%" + name + "%' or aid like '%" + name + "%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean tag = new AdminBean();
				tag.setAid(rs.getInt("aid"));
				tag.setStatus(rs.getInt("status"));
				tag.setUsername(rs.getString("username"));
				tag.setName(rs.getString("name"));
				tag.setPassword(rs.getString("password"));
				tag.setEmail(rs.getString("email"));
				tag.setPhone(rs.getString("phone"));
				tag.setTimes(rs.getInt("times"));
				tag.setLend_num(rs.getInt("lend_num"));
				tag.setMax_num(rs.getInt("max_num"));
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
