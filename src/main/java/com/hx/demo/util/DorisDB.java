package com.hx.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DorisDB {

	protected Connection con;
	protected Statement stmt;
	protected ResultSet rs;
	
	public DorisDB() {
		loadDriver();
	}
	public Connection getCon() {
		return con;
	}
	public void loadDriver() {

		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			this.conData();
		} catch (ClassNotFoundException e) {
			System.out.println("无法找到所加载的驱动，或是驱动文件不存在！！！");
			e.printStackTrace();
		}
	}
	public void conData() {
		try {
			//获取链接
			con = DriverManager.getConnection("jdbc:mysql://10.90.60.101:9030/hx_doris", "onlyreader",
					"hx2car88212994");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet selectSql(String sql) {
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	public void closeCon() {
		try {
			if (stmt != null){
				stmt.close();
			}
			if (con != null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
