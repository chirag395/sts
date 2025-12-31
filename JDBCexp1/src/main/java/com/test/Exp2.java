package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class Exp2 {
	
	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/mladec";
		String user = "root";
		String pass = "root@39";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,user,pass);
		
		String sql1 = "insert into book1 values(?,?,?)";
		String sql2 = "update book1 set b_name = ? where b_id = ?";
		String sql3 = "select * from book1";
		
		PreparedStatement pst1 = con.prepareStatement(sql1);
		pst1.setInt(1, 3);
		pst1.setString(2, "python");
		pst1.setInt(3, 100);
		
		
		
		
		PreparedStatement pst2 = con.prepareStatement(sql2);
		pst2.setString(1, "php");
		pst2.setInt(2, 3);
		
		
		
		
		
		PreparedStatement pst3 = con.prepareStatement(sql3);
		
		pst1.addBatch();
		pst2.addBatch();
//		pst3.addBatch();
		
		pst1.executeBatch();
		
		ResultSet rs = pst3.executeQuery();
		
		while(rs.next()) {
			System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2) + ", Price: " + rs.getInt(3));
		}
		
		ResultSetMetaData rsd = rs.getMetaData();
		
		System.out.println(rsd.getColumnClassName(2));
	}

}
