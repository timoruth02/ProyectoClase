package Datos;

import Entidades.DocIdentidadEntity;
import Entidades.SedeEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SedeDB {
    Connection cn;
    Conexion conect = new Conexion();
    
    public ArrayList<SedeEntity> getCodSedeItems() {
        ArrayList<SedeEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select CodSede, Nombre_Sede, CodUbigeo, Direccion, Telefono, Email, Estado_Sede from Sede";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            SedeEntity Item = new SedeEntity();
            while (rs.next()) {
                Item = null;
                Item = new SedeEntity();
                Item.setCodSede(rs.getString("CodSede"));
                Item.setNombre_Sede(rs.getString("Nombre_Sede"));
                Item.setCodUbigeo(rs.getString("CodUbigeo"));
                Item.setDireccion(rs.getString("Direccion"));
                Item.setTelefono(rs.getString("Telefono"));
                Item.setEmail(rs.getString("Email"));
                Item.setEstado_Sede(rs.getString("Estado_Sede"));
                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }

}
