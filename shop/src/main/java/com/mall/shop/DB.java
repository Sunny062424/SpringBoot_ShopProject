package com.mall.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.sqlite.SQLiteConfig;

public class DB {
    Connection connection;
	private void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			this.connection = DriverManager.getConnection("jdbc:sqlite:C:\\kopo\\cartlist.db", config.toProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.connection = null;
	}
	public void createTable() {
		this.open();
		String query = "CREATE TABLE cartlist (idx INTEGER PRIMARY KEY AUTOINCREMENT, get_time  TEXT, product TEXT, price INTEGER)";
		try {
			Statement statement = this.connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public void insertData(String product, String price) {
		this.open();
		String query = "INSERT INTO cartlist (get_time, product, price) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(2, product);
			int price_int = Integer.parseInt(price);
			preparedStatement.setInt(3, price_int);
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatedNow = formatter.format(now);
			preparedStatement.setString(1, formatedNow);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
}
