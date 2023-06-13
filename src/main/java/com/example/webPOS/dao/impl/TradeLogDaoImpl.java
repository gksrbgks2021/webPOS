package com.example.webPOS.dao.impl;

import java.util.*;
import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.vo.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class TradeLogDaoImpl implements TradeLogDAO {
	private JdbcTemplate jdbcTemplate;

	String sqlQuery;

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Autowired
	public TradeLogDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(TradeLog tradeLog) {
		setSqlQuery(
				"INSERT INTO tradelog (productId, tradeDate, QUANTITYTRADED, TOTALPRICE, STATE, STORENAME)VALUES (?, ?, ?, ?, ?, ?)");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(getSqlQuery(), new String[] { "ID" }); // Return
																										// generated ID
				pstmt.setLong(1, tradeLog.getProductId());
				pstmt.setTimestamp(2, Timestamp.valueOf(tradeLog.getTradeDate()));
				pstmt.setInt(3, tradeLog.getQuantityTraded());
				pstmt.setLong(4, tradeLog.getTotalPrice());
				pstmt.setString(5, tradeLog.getState());
				pstmt.setString(6, tradeLog.getStoreName());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		tradeLog.setId(keyValue.intValue());
	}

	@Override
	public Map<String, Integer> getProfit(String start, String end, String StoreName, String period) {
		if (period.equals("week")) {
			String query = "SELECT A.week AS w, B.s - A.o AS revenue "
					+ "FROM (SELECT WEEK(tradedate) AS week, SUM(totalprice) AS o " + "FROM tradelog "
					+ "WHERE storename = ? AND state = 'order' AND tradedate BETWEEN ? AND ? "
					+ "GROUP BY WEEK(tradedate)) AS A left join (SELECT WEEK(tradedate) AS week, SUM(totalprice) AS s "
					+ "FROM tradelog " + "WHERE storename = ? AND state = 'saled' AND tradedate BETWEEN ? AND ? "
					+ "GROUP BY WEEK(tradedate)) AS B on A.week = B.week ";

			return jdbcTemplate.query(query, (ResultSetExtractor<Map<String, Integer>>) rs -> {
				Map<String, Integer> weeklyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String week = rs.getString("w");
					int revenue = rs.getInt("revenue");
					weeklyTotalQuantities.put(week, revenue);
				}
				return weeklyTotalQuantities;
			}, StoreName, start, end, StoreName, start, end);
		} else if (period.equals("day")) {
			String query = "SELECT DATE_FORMAT(tradedate, '%Y-%m-%d') as Daily, SUM(CASE WHEN state = 'saled' THEN totalprice "
					+ "WHEN state = 'order' THEN -1 * totalprice " + "ELSE 0  " + "END) AS profit " + "FROM tradelog "
					+ "WHERE storename = ? AND tradedate BETWEEN ? AND ? "
					+ "GROUP BY DATE_FORMAT(tradedate, '%Y-%m-%d') ";

			return jdbcTemplate.query(query, (ResultSetExtractor<Map<String, Integer>>) rs -> {
				Map<String, Integer> dailyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String day = rs.getString("Daily");
					int revenue = rs.getInt("profit");
					dailyTotalQuantities.put(day, revenue);
				}
				return dailyTotalQuantities;
			}, StoreName, start, end);
		} else if (period.equals("month")) {
			String query = "SELECT A.month AS m, A.s - B.o AS revenue "
					+ "FROM (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(totalprice) AS s" + "FROM tradelog "
					+ "WHERE storename = ? AND state = 'saled' AND tradedate BETWEEN ? AND ? "
					+ "GROUP BY DATE_FORMAT(tradedate, '%Y-%m')) AS A left join (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(totalprice) AS o "
					+ "FROM tradelog " + "WHERE storename = ? AND state = 'order' AND tradedate BETWEEN ? AND ? "
					+ "GROUP BY DATE_FORMAT(tradedate, '%Y-%m'))  AS B on A.month = B.month ";

			return jdbcTemplate.query(query, (ResultSetExtractor<Map<String, Integer>>) rs -> {
				Map<String, Integer> monthlyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String month = rs.getString("m");
					int revenue = rs.getInt("revenue");
					monthlyTotalQuantities.put(month, revenue);
				}
				return monthlyTotalQuantities;
			}, StoreName, start, end, StoreName, start, end);
		} else {
			return null;
		}
	}

	@Override
	public Map<String, String> profitFirst(String start, String end, String period) {
		if (period.equals("week")) {
			String tmp = "SELECT a.storename AS n, a.week AS w"
					+ "FROM(SELECT week, MAX(profit) as max"
					+ "FROM (SELECT WEEK(tradedate) AS week, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "WHEN state = 'order' THEN -1 * totalprice "
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY week, storename) AS WeeklyProfit"
					+ "GROUP BY week) AS b left join (SELECT WEEK(tradedate) AS week, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "WHEN state = 'order' THEN -1 * totalprice n"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN '? AND ?"
					+ "GROUP BY week, storename) AS a on b.max = a.profit";

			return jdbcTemplate.query(tmp, (ResultSetExtractor<Map<String, String>>) rs -> {
				Map<String, String> WeeklyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String week = rs.getString("w");
					String storename = rs.getString("n");
					WeeklyTotalQuantities.put(week, storename);
				}
				return WeeklyTotalQuantities;
			}, start, end, start, end);
		} else if (period.equals("month")) {
			String tmp = "SELECT a.storename AS n, a.month AS m"
					+ "FROM(SELECT month, MAX(profit) as max"
					+ "FROM (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "WHEN state = 'order' THEN -1 * totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY month, storename) AS MonthlyProfit"
					+ "GROUP BY month) AS b left join (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "WHEN state = 'order' THEN -1 * totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY month, storename) AS a on b.max = a.profit";

			return jdbcTemplate.query(tmp, (ResultSetExtractor<Map<String, String>>) rs -> {
				Map<String, String> MonthlyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String month = rs.getString("m");
					String storename = rs.getString("n");
					MonthlyTotalQuantities.put(month, storename);
				}
				return MonthlyTotalQuantities;
			}, start, end, start, end);
		} else if (period.equals("day")) {
			String tmp = "SELECT a.storename AS n, a.Daily AS d"
					+ "FROM(SELECT Daily, MAX(profit) as max"
					+ "FROM (SELECT DATE_FORMAT(tradedate, '%Y-%m-%d') as Daily, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "WHEN state = 'order' THEN -1 * totalprice"
					+ "ELSE 0"
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY Daily, storename) AS DailyProfit"
					+ "GROUP BY Daily) AS b left join (SELECT DATE_FORMAT(tradedate, '%Y-%m-%d') as Daily, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "WHEN state = 'order' THEN -1 * totalprice "
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY Daily, storename) AS a on b.max = a.profit";
			return jdbcTemplate.query(tmp, (ResultSetExtractor<Map<String, String>>) rs -> {
				Map<String, String> DailyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String daily = rs.getString("d");
					String storename = rs.getString("n");
					DailyTotalQuantities.put(daily, storename);
				}
				return DailyTotalQuantities;
			}, start, end, start, end);
		} else {
			return null;
		}

	}

	@Override
	public Map<String, String> takeFirst(String start, String end, String period) {
		if (period.equals("week")) {
			String tmp = "SELECT a.storename AS n, a.week AS w"
					+ "FROM(SELECT week, MAX(profit) as max"
					+ "FROM (SELECT WEEK(tradedate) AS week, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY week, storename) AS WeeklyProfit"
					+ "GROUP BY week) AS b left join (SELECT WEEK(tradedate) AS week, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY week, storename) AS a on b.max = a.profit";

			return jdbcTemplate.query(tmp, (ResultSetExtractor<Map<String, String>>) rs -> {
				Map<String, String> WeeklyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String week = rs.getString("w");
					String storename = rs.getString("n");
					WeeklyTotalQuantities.put(week, storename);
				}
				return WeeklyTotalQuantities;
			}, start, end, start, end);
		} else if (period.equals("month")) {
			String tmp = "SELECT a.storename AS n, a.month AS m"
					+ "FROM(SELECT month, MAX(profit) as max"
					+ "FROM (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY month, storename) AS MonthlyProfit"
					+ "GROUP BY month) AS b left join (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY month, storename) AS a on b.max = a.profit";

			return jdbcTemplate.query(tmp, (ResultSetExtractor<Map<String, String>>) rs -> {
				Map<String, String> MonthlyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String month = rs.getString("m");
					String storename = rs.getString("n");
					MonthlyTotalQuantities.put(month, storename);
				}
				return MonthlyTotalQuantities;
			}, start, end, start, end);
		} else if (period.equals("day")) {
			String tmp = "SELECT a.storename AS n, a.Daily AS d"
					+ "FROM(SELECT Daily, MAX(profit) as max"
					+ "FROM (SELECT DATE_FORMAT(tradedate, '%Y-%m-%d') as Daily, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "ELSE 0"
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY Daily, storename) AS DailyProfit"
					+ "GROUP BY Daily) AS b left join (SELECT DATE_FORMAT(tradedate, '%Y-%m-%d') as Daily, SUM(CASE WHEN state = 'saled' THEN totalprice"
					+ "ELSE 0 "
					+ "END) AS profit, storename"
					+ "FROM tradelog"
					+ "WHERE tradedate BETWEEN ? AND ?"
					+ "GROUP BY Daily, storename) AS a on b.max = a.profit";
			return jdbcTemplate.query(tmp, (ResultSetExtractor<Map<String, String>>) rs -> {
				Map<String, String> DailyTotalQuantities = new HashMap<>();
				while (rs.next()) {
					String daily = rs.getString("d");
					String storename = rs.getString("n");
					DailyTotalQuantities.put(daily, storename);
				}
				return DailyTotalQuantities;
			}, start, end, start, end);
		} else {
			return null;
		}
	}

	@Override
	public TradeLog findById(Long productID) {
		setSqlQuery("SELECT * FROM TRADELOG  WHERE ProductID = ?");

		List<TradeLog> results = jdbcTemplate.query(getSqlQuery(), new RowMapper<TradeLog>() {
			@Override
			public TradeLog mapRow(ResultSet rs, int rowNum) throws SQLException {

				TradeLog tradeLog = new TradeLog();
				tradeLog.setId(rs.getInt("id"));
				tradeLog.setProductId(rs.getLong("productId"));
				tradeLog.setTradeDate(rs.getTimestamp("tradeDate").toLocalDateTime());
				tradeLog.setQuantityTraded(rs.getInt("quantityTraded"));
				tradeLog.setTotalPrice(rs.getLong("totalPrice"));
				tradeLog.setState(rs.getString("state"));
				tradeLog.setStoreName(rs.getString("storeName"));

				return tradeLog;
			}
		}, productID);
		return results.isEmpty() ? null : results.get(0);
	}

}