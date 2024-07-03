package com.fdxdesarrollos.studentManagement.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JFrame;

import com.fdxdesarrollos.studentManagement.App;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class PrintReport extends JFrame {
	private String reporte = "";
	private Map param = null;

	public PrintReport(String reporte, Map param) {
		super();
		this.reporte = reporte;
	}
	
	public void showReport() {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			InputStream stream = getClass().getResourceAsStream(App.class.getResource("report/" + reporte).getPath());
			JasperReport jasperReport = JasperCompileManager.compileReport(stream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
			JRViewer viewer = new JRViewer(jasperPrint);
			viewer.setOpaque(true);
			viewer.setVisible(true);
			
			this.add(viewer);
			this.setSize(800, 600);
			this.setVisible(true);
			
			//this.getContentPane().add(frame);
			//this.setExtendedState(JFrame.);
			//this.pack();
		} catch (JRException ex) {
			System.out.println("Error al generar reporte " + reporte + " " + ex.getMessage());
			ex.getStackTrace();
		} catch (SQLException ex) {
			System.out.println("Error al consultar información " + ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
