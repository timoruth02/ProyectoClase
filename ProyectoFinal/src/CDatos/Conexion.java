/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CDatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ruth Sayuri
 */
public class Conexion {

    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=Tienda1";
    private String user = "Gabita";
    private String pass = "123";

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
