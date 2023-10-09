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
			this.connection = DriverManager.getConnection("jdbc:sqlite:/Users/sunny/sqlite/cartlist.db", config.toProperties());
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

	public ArrayList<Cart> selectData() {
		ArrayList<Cart> result = new ArrayList<Cart>();
		this.open();
		try {
			String query = "SELECT * FROM cartlist";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String get_time = resultSet.getString("get_time");
				String product = resultSet.getString("product");
				int price = resultSet.getInt("price");
				result.add(new Cart(idx, get_time, product, price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

		return result;
	}

	public void deleteData(String idx){
		this.open();
		try {
			int intIdx = Integer.parseInt(idx);
			String query = "DELETE FROM cartlist WHERE idx=?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, intIdx);
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();

	}

	public long countData() {
		long result = 0;
		this.open();
		
		try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT COUNT(idx) FROM cartlist");
			 ResultSet resultset = preparedStatement.executeQuery()) {
			
			if (resultset.next()) {
				result = resultset.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}
	


}
