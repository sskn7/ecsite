package com.diworksdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diworksdev.ecsite.util.DBConnector;
import com.diworksdev.ecsite.util.DateUtil;

public class BuyItemCompleteDAO {
	public void buyItemeInfo(String item_transaction_id,String total_price,String total_count,String user_master_id,String pay)
	throws SQLException {
		DBConnector dbConnector =  new DBConnector();
		Connection connection = dbConnector.getConnection();
		DateUtil dateUtil = new DateUtil();
		String sql = "INSERT INTO user_buy_item_transaction(item_transaction_id,total_price,total_count,user_master_id,pay,insert_date) VALUES(?,?,?,?,?,?)";

		try {
		    connection.setAutoCommit(false); // 明示的にトランザクション開始
		    PreparedStatement ps = connection.prepareStatement(sql);
		    ps.setString(1, item_transaction_id);
		    ps.setString(2, total_price);
		    ps.setString(3, total_count);
		    ps.setString(4, user_master_id);
		    ps.setString(5, pay);
		    ps.setString(6, dateUtil.getDate());

		    int result = ps.executeUpdate(); // ← 件数を確認
		    System.out.println("挿入件数: " + result);

		    connection.commit(); // ← 忘れずにコミット
		} catch (Exception e) {
		    e.printStackTrace();
		    connection.rollback(); // エラー時はロールバック
		} finally {
		    connection.close();
		}

	}
}
