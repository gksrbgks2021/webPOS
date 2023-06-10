package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.TradeLogDAO;
import com.example.webPOS.dto.TradeLog;
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

    @Autowired
    public TradeLogDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(TradeLog tradeLog) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "INSERT INTO trade_log (PRODUCT_ID, TRADE_DATE, QUANTITY_TRADED, TOTAL_PRICE, STATE, STORE_NAME) " +
                                "VALUES (?, ?, ?, ?, ?, ?)",
                        new String[]{"ID"}); // Return generated ID
                pstmt.setString(1, tradeLog.getProductId());
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
    public void update(TradeLog tradeLog) {
        jdbcTemplate.update("UPDATE trade_log SET " +
                        "PRODUCT_ID = ?, " +
                        "TRADE_DATE = ?, " +
                        "QUANTITY_TRADED = ?, " +
                        "TOTAL_PRICE = ?, " +
                        "STATE = ?, " +
                        "STORE_NAME = ? " +
                        "WHERE ID = ?",
                tradeLog.getProductId(),
                Timestamp.valueOf(tradeLog.getTradeDate()),
                tradeLog.getQuantityTraded(),
                tradeLog.getTotalPrice(),
                tradeLog.getState(),
                tradeLog.getStoreName(),
                tradeLog.getId());
    }

    @Override
    public TradeLog findById(int id) {
        List<TradeLog> results = jdbcTemplate.query("SELECT * FROM trade_log WHERE ID = ?",
                new RowMapper<TradeLog>() {
                    @Override
                    public TradeLog mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TradeLog tradeLog = new TradeLog();
                        tradeLog.setId(rs.getInt("ID"));
                        tradeLog.setProductId(rs.getString("PRODUCT_ID"));
                        tradeLog.setTradeDate(rs.getTimestamp("TRADE_DATE").toLocalDateTime());
                        tradeLog.setQuantityTraded(rs.getInt("QUANTITY_TRADED"));
                        tradeLog.setTotalPrice(rs.getLong("TOTAL_PRICE"));
                        tradeLog.setState(rs.getString("STATE"));
                        tradeLog.setStoreName(rs.getString("STORE_NAME"));
                        return tradeLog;
                    }
                }, id);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<TradeLog> findAll() {
        return jdbcTemplate.query("SELECT * FROM trade_log",
                new RowMapper<TradeLog>() {
                    @Override
                    public TradeLog mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TradeLog tradeLog = new TradeLog();
                        tradeLog.setId(rs.getInt("ID"));
                        tradeLog.setProductId(rs.getString("PRODUCT_ID"));
                        tradeLog.setTradeDate(rs.getTimestamp("TRADE_DATE").toLocalDateTime());
                        tradeLog.setQuantityTraded(rs.getInt("QUANTITY_TRADED"));
                        tradeLog.setTotalPrice(rs.getLong("TOTAL_PRICE"));
                        tradeLog.setState(rs.getString("STATE"));
                        tradeLog.setStoreName(rs.getString("STORE_NAME"));
                        return tradeLog;
                    }
                });
    }
}