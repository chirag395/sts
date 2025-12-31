package com.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Task1 {

	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/mladec";
		
		String user = "root";
		
		String pass = "root@39";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,user,pass);
		
		Statement stmt = con.createStatement();
		
//		stmt.execute("create table daaru(d_id int, d_name varchar(50), d_price int, isImported bool, d_city varchar(50))");
		
		
		
		
//		stmt.addBatch("insert into daaru values(1, 'BlueLevel', 3000, false, 'Bengaluru')");
//		System.out.println("Inserted with id 1");
		stmt.addBatch("UPDATE daaru SET d_name='RedLevel', d_price=3000, isImported=true, d_city='Delhi' WHERE d_id=2");
		System.out.println("Updated with id 2");
//		stmt.addBatch("insert into book1 values(3, 'SpringBoot', 150)");
//		System.out.println("Inserted with id 3");
//		stmt.addBatch("update book1 set b_name = 'react' where b_id = 3");
		
//		String sql = "select * from book1";
//		ResultSet rs = stmt.executeQuery(sql);
		
//		while(rs.next()) {
//			System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2) + ", Price: " + rs.getInt(3));
//		}
		
		
		
		
		stmt.executeBatch();
		
		con.close();
		
		
		System.out.println("Connected To DB");

	}

}
