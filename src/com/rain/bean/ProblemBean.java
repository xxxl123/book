package com.rain.bean;

public class ProblemBean {
	/**
	 * ��ʷ���ļ�¼�����ݱ��bean
	 */
	private int pid;// ���������id
	private int aid;//����������ϴ���
	private String name;// ����
	private String page;// ����ҳ��
	private String body;// ��������
	private String phone;// ��ϵ�绰
	private String status;//����״̬

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
