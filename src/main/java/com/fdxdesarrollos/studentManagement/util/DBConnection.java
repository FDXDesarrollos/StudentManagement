package com.fdxdesarrollos.studentManagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnection {	
	/*********************************************************************************************************************/
	/*Singleton con la propiedad de que sea thread-safe y con evaluación lazy 
	Esta implementación es thread-safe y tiene evaluación lazy que algunas de las anteriores no tienen. 
	Lo que hace es utilizar una clase anidada o inner para mantener la referencia a la instancia de la clase que la contiene. 
	 
	En este caso el propio lenguaje Java por la inicialización de las clases y propiedades static garantiza que es thread-safe.
	/*********************************************************************************************************************/
    private Connection conn = null;
    
    private final String driver = "org.mariadb.jdbc.Driver";
    private final String dbHost = "localhost";
    private final String dbPort = "3366";
    private final String dataBase = "studentdata";
    private final String dbUser = "pbiadmin";
    private final String dbPass = "4dm1nd4t";       
    
	private DBConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mariadb://" + dbHost + ":" + dbPort + "/" + dataBase + "?useSSL=false&serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true", dbUser, dbPass);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al crear la conexion a BD !!! \n" + e.getMessage());
            e.printStackTrace();
        }   		
	}
	
    private static class DBConnectionInner {
    	public static DBConnection instance = new DBConnection();
    }
    
    public Connection getConnection() {
        return conn;
    }    

    public static DBConnection getInstance() throws SQLException {
        //if (DBConnectionInner.instance == null || DBConnectionInner.instance.getConnection().isClosed()) {
    	if (DBConnectionInner.instance.getConnection().isClosed()) {
        	DBConnectionInner.instance = new DBConnection();
        }
        
        return DBConnectionInner.instance;        
    }
	
    /*********************************************************************************************************************/
    /*Singleton con la propiedad de que sea thread-safe
    
    La forma tradicional de implementar el patrón Singleton es utilizando una variable estática privada 
    para guardar la referencia de la única instancia, hacer el constructor privado de modo que el resto 
    de clases no tengan la posibilidad de crear más instancias y un método que crea la instancia si no ha 
    sido creada con anterioridad con una sentencia condicional y devuelve la referencia si ya existe la instancia.
    
    Esta implementación es muy utilizada, aunque es lazy ya que la instancia no se crea hasta que se realiza 
    la primera solicitud su inconveniente es que no es thread-safe si varios hilos intentan obtener una 
    instancia cuando aún no hay ninguna creada.
    
    Para implementar el patrón Singleton con la propiedad de que sea thread-safe la forma más sencilla es hacer 
    el método synchronized de modo que Java garantiza que si varios hilos intentan obtener la referencia de la 
    instancia cuando aún no está creada sólo uno de ellos la cree.
    
    El inconveniente de esta implementación con synchronized es que hace que el método para obtener la instancia sea más lento 
    debido a la propia sincronización y a la contención que se produce si hay múltiples hilos en ejecución que hacen uso 
    del método, si el rendimiento es una consideración importante hay otras formas de implementar el patrón Singleton 
    no mucho más difíciles que tienen mejor rendimiento.*/
	/*********************************************************************************************************************/
	
    /*private static DBConnection instance = null;
    private Connection conn = null;
    
    private final String driver = "org.mariadb.jdbc.Driver";
    private final String dbHost = "localhost";
    private final String dbPort = "3366";
    private final String dataBase = "studentdata";
    private final String dbUser = "pbiadmin";
    private final String dbPass = "4dm1nd4t";    

    private DBConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mariadb://" + dbHost + ":" + dbPort + "/" + dataBase + "?useSSL=false&serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true", dbUser, dbPass);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al crear la conexion a BD !!! \n" + e.getMessage());
            e.printStackTrace();
        }    	
    }

    public Connection getConnection() {
        return conn;
    }

    public synchronized static DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        
        return instance;
    }*/

    /*********************************************************************************************************************/
    //Conexión simple a base de datos
	/*********************************************************************************************************************/
    
    /*private static Connection conn = null;
    
    private DBConnection() {}
    
    static
    {
    	final String driver = "org.mariadb.jdbc.Driver";
    	final String dbHost = "localhost";
    	final String dbPort = "3366";
    	final String dataBase = "studentdata";
    	final String dbUser = "pbiadmin";
    	final String dbPass = "4dm1nd4t";
    	
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mariadb://" + dbHost + ":" + dbPort + "/" + dataBase + "?useSSL=false&serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true", dbUser, dbPass);
        }
        catch (ClassNotFoundException | SQLException e) {
        	System.out.println("Error de conexion a BD !!! \n" + e.getMessage());
        	e.printStackTrace();
        }
    }
    
    public synchronized static Connection getInstance() {
        return conn;
    }*/
    
}

// https://www.geeksforgeeks.org/jdbc-using-model-object-and-singleton-class 
