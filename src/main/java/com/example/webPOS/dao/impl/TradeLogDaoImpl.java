package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.Product;
import com.example.webPOS.vo.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

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
        setSqlQuery("INSERT INTO tradelog (productId, tradeDate, QUANTITYTRADED, TOTALPRICE, STATE, STORENAME)VALUES (?, ?, ?, ?, ?, ?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(getSqlQuery(),
                        new String[]{"ID"}); // Return generated ID
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
    public List<TradeLog> findPeriod(String start, String end, String StoreName) {
        //구현!
        return null;
    }

    @Override
    public List<TradeLog> findPeriodDesc(String start, String end, String dateType) {
        //구현!
        return null;
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