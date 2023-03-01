package com.rain.util;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DButils {

	//创建 设置连接池
	private static ComboPooledDataSource ds;
	
	static{
		ds=new ComboPooledDataSource("myConfig");
	}
	//封装一个方法 用来获取连接池
	public static DataSource getDataSource() {
		return ds;
	}

	public static QueryRunner getQueryRunner() {
		QueryRunner qr=new QueryRunner(DButils.getDataSource());
		return qr;
	}

}
