package com.varxyz.jv301.mod010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
	private String jdbcDriver;
	private String jdbcUrl;
	private String jdbcUserName;
	private String jdbcPasswd;

	public DataSource(String jdbcDriver,String jdbcUrl, String jdbcUserName, String jdbcPasswd) {
		super();
		this.jdbcDriver = jdbcDriver;
		this.jdbcUrl = jdbcUrl;
		this.jdbcUserName = jdbcUserName;
		this.jdbcPasswd = jdbcPasswd;
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPasswd);
		}catch (SQLException e){
			throw new RuntimeException("ConnectionNotAcailableException");
		}
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null && rs.isClosed()) {
			rs.close();
		}
		if(pstmt != null && pstmt.isClosed()) {
			pstmt.close();
		}
		if(con != null && con.isClosed()) {
			con.close();
		}
	}
	
	public void close(PreparedStatement pstmt, Connection con) throws SQLException {
		close(null, pstmt, con);
	}
}

