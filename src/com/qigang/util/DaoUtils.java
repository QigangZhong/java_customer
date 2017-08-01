package com.qigang.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoUtils {
	private static DataSource _source=new ComboPooledDataSource();
	private DaoUtils(){}
	
	public static DataSource GetDataSource(){
		return _source;
	}
	
	public Connection GetConn(){
		try {
			return _source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
