package com.rain.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.rain.bean.AdminBean;
import com.rain.util.DButils;

public class AdminDaoT {
	QueryRunner qr = DButils.getQueryRunner();
	AdminBean adminBean=new AdminBean();
	/**
	 * 登录验证功能，传入用户名和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public boolean Login_verify(String username, String password) throws SQLException{
		String sql="select* from admin where username=? and password=?";
		
		adminBean=qr.query(sql, new BeanHandler<AdminBean>(AdminBean.class),username,password);
		return adminBean != null;

	}

	/**
	 * 注册账号的函数，传入账号，密码，姓名，邮箱，手机号，借阅天数，可借阅数
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
	public int Register(String username, String password, String name, String email, String phone, int lend_num,
			int max_num) throws SQLException {
		String sql = "insert into admin(status,username,password,name,email,phone,lend_num,max_num) values(?,?,?,?,?,?,?,?)";
		return qr.execute(sql, 1,username,password,name,email,phone,7,8);

	}

	/**
	 * 新增管理员账号，传入账号，密码，姓名，邮箱，手机号
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phone
	 * @param times
	 * @param lend_num
	 * @param max_num
	 * @throws SQLException 
	 */
	public void Register2(String username, String password, String name, String email, String phone) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into admin(status,username,password,name,email,phone) values(?,?,?,?,?,?)";
		qr.execute(sql, 2,username,password,name,email,phone);
	}	
	/**
	 * 根据传入的账号，密码，来查找对应的读者信息，返回一个AdminBean类型，
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public AdminBean getAdminInfo(String username, String password) throws SQLException{
		// TODO Auto-generated method stub

		String sql = "select * from admin where username= '"+username+"' and password= '"+password+"'";
		return  qr.query(sql, new BeanHandler<AdminBean>(AdminBean.class));
		
	}

	/**
	 * 获取全部用户的信息，其中sql语句中的status=1，表示只查找读者，不显示管理员的
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<AdminBean> get_ListInfo() throws SQLException {
		/*ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();*/
		String sql = "select * from admin where status=1";
		return (ArrayList<AdminBean>) qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		
	}

	/**
	 * 获取全部用户的信息，其中sql语句中的status=2，表示只查找管理员，不显示读者的
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<AdminBean> get_ListInfo2() throws SQLException {
		
		String sql = "select * from admin where status=2";
		return (ArrayList<AdminBean>) qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		
	}
	/**
	 * 获取全部用户的信息并排序，其中sql语句中的status=1，表示只查找读者，不显示管理员的
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<AdminBean> get_ListInfo3() throws SQLException {
		
		String sql = "select * from admin where status=1 order by times desc";
		return (ArrayList<AdminBean>) qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		
	}

	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据
	 * 
	 * @param aid
	 * @return
	 * @throws SQLException 
	 */
	public AdminBean get_AidInfo(int aid) throws SQLException {
		
		String sql = "select * from admin where aid=" + aid;
		return  qr.query(sql, new BeanHandler<AdminBean>(AdminBean.class),aid);
		
	}

	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据，与上一个相似，只是aid的类型为String
	 * 
	 * @param aid
	 * @return
	 * @throws SQLException 
	 */
	public AdminBean get_AidInfo2(String aid) throws SQLException {
		/*AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();*/
		String sql = "select * from admin where aid=" + aid;
		return  qr.query(sql, new BeanHandler<AdminBean>(AdminBean.class),aid);
	
	}

	/**
	 * 修改读者的信息
	 * @throws SQLException 
	 */
	public void updateUser(int aid, String username, String password, String name, String email, String phone,
			int lend_num, int max_num) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
		qr.execute(sql, username,name,email,phone,password,lend_num,max_num,aid);

	}

	/**
	 * 修改管理员的信息
	 * @throws SQLException 
	 */
	public void updateAdmin(int aid, String username, String password, String name, String email, String phone) throws SQLException {
		// TODO Auto-generated method stub
		//Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=? where aid=?";
		qr.execute(sql, username,name,email,phone,password,aid);
		
	}

	/**
	 * 删除用户的信息，根据传入的aid作为条件
	 * 
	 * @param aid
	 * @throws SQLException 
	 */
	public void deleteUser(int aid) throws SQLException {
		// TODO Auto-generated method stub
		//Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";
		qr.execute(sql, aid);

	}

	/**
	 * 删除管理员的信息，根据传入的aid作为条件
	 * 
	 * @param aid
	 * @throws SQLException 
	 */
	public void deleteAdmin(int aid) throws SQLException {
		// TODO Auto-generated method stub
		//Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";
		qr.execute(sql, aid);
		/*PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	/**
	 * 查找用户，根据输入的名称，使用like进行模糊查询，然后返回一个ArrayList数组类型
	 * 
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<AdminBean> getLikeList(String name) throws SQLException {
		// TODO Auto-generated method stub
		/*ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();*/
		String sql = "select * from admin where (name like '%" + name + "%' or username like '%" + name + "%' or aid like '%" + name + "%') and status ='1'";
		return (ArrayList<AdminBean>) qr.query(sql, new BeanListHandler<AdminBean>(AdminBean.class));
		/*PreparedStatement stm = null;
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
		return tag_Array;*/
	}
	
}
