package com.fdxdesarrollos.studentManagement.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.fdxdesarrollos.studentManagement.App;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PrintReport {
	private String reporte = "";
	private Map param = null;

	public PrintReport(String reporte, Map param) {
		super();
		this.reporte = reporte;
	}
	
	public void showReport() {
		String pathFile = "";
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			
			pathFile = App.class.getResource("report/" + reporte).getPath();
			//InputStream stream = new FileInputStream( new File(pathFile) );
			JasperDesign design = JRXmlLoader.load(pathFile);
			JasperReport report = JasperCompileManager.compileReport(design);
			JasperPrint print = JasperFillManager.fillReport(report, param, conn);
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.setTitle("Student Management System");
			viewer.setSize(800, 600);
			viewer.setVisible(true);
			
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

/*************************************************************************/
/* !!!  MUY IMPORTANTE  !!!
 * 
 * EN EL ARCHIVO ".jrxml" HAY QUE ELIMINAR EL ATRIBUTO 'language="groovy"'
 * YA QUE DE LO CONTRARIO ESTO RPOVOCARA UN ERROR TODO EXTRAÑO Y
 * EL REPORTE NO SE MOSTRARA !!! 
 */
/**************************************************************************/
