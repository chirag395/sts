package com.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exp1 {

	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/mladec";
		
		String user = "root";
		
		String pass = "root@39";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,user,pass);
		
		Statement stmt = con.createStatement();
		
//		stmt.execute("create table book1(b_id int, b_name varchar(50), b_price int)");
		
		
		
		
//		stmt.addBatch("insert into book1 values(2, 'Spring', 300)");
//		System.out.println("Inserted with id 2");
//		stmt.addBatch("insert into book1 values(3, 'SpringBoot', 150)");
//		System.out.println("Inserted with id 3");
//		stmt.addBatch("update book1 set b_name = 'react' where b_id = 3");
		
		String sql = "select * from book1";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2) + ", Price: " + rs.getInt(3));
		}
		
		
		
		
//		stmt.executeBatch();
		
		con.close();
		
		
		System.out.println("Connected To DB");

	}

}
