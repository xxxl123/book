package com.rain.util;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DButils {

	//���� �������ӳ�
	private static ComboPooledDataSource ds;
	
	static{
		ds=new ComboPooledDataSource("myConfig");
	}
	//��װһ������ ������ȡ���ӳ�
	public static DataSource getDataSource() {
		return ds;
	}

	public static QueryRunner getQueryRunner() {
		QueryRunner qr=new QueryRunner(DButils.getDataSource());
		return qr;
	}

}
