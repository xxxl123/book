package com.rain.bean;

public class AdminBean {
	/**
	 * �û������ݱ��bean
	 */
	private int aid;// id
	private int status;// �����ж��ǹ���Ա���Ƕ��ߣ����ߵ�ֵΪ1������ԱΪ2
	private String username;// �˺�
	private String name;// ����
	private String password;// ����
	private String email;// ����
	private String phone;// �ֻ���
	private int times;//������
	private int lend_num;// �ɽ�������
	private int max_num;// ���ɽ���

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getLend_num() {
		return lend_num;
	}

	public void setLend_num(int lend_num) {
		this.lend_num = lend_num;
	}

	public int getMax_num() {
		return max_num;
	}

	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}

}
