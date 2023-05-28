
package Datos;


import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=BDHostal";
    private String user = "user";
    private String pass = "123456";

    Conexion() {
    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            return (DriverManager.getConnection(url, user, pass));
        } catch (Exception e) {

        }
        return null;
    }

    public void CerrarConexion(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
        }
    }
    
    
}
