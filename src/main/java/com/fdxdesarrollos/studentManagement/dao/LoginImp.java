package com.fdxdesarrollos.studentManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdxdesarrollos.studentManagement.model.Login;
import com.fdxdesarrollos.studentManagement.util.DBConnection;

public class LoginImp implements ILogin{
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rst = null;
	private String sql = "";
	
	@Override
	public boolean access(Login login) {
		boolean bFlag = false;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			sql = "SELECT * FROM users WHERE username = ? AND password = password(?);";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, login.getUser());
            pstm.setString(2, login.getPass());
            rst = pstm.executeQuery();
            if (rst.next()) bFlag = true; 
	    }catch(SQLException ex){
	        System.out.println("Error al listar Sectores: " + ex.getMessage());
	        ex.printStackTrace();
	    }finally{
	        try {
	        	if(rst != null) rst.close();
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
	    }
		
		return bFlag;
	}

}
