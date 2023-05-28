package Datos;

import Entidades.DocIdentidadEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DocIdentidad_DT {
    Connection cn;
    Conexion conect = new Conexion();
    
    public ArrayList<DocIdentidadEntity> getCodDoc_IdentidadItems() {
        ArrayList<DocIdentidadEntity> Lista = new ArrayList<>();
    
        try {
            cn = conect.conectar();
            String sql = "select CodDoc_Identidad, Tipo_Doc_Identidad from Documento_Identidad";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            DocIdentidadEntity Item = new DocIdentidadEntity();
            while (rs.next()) {
                Item = null;
                Item = new DocIdentidadEntity();
                Item.setCodDoc_Identidad(rs.getString("CodDoc_Identidad"));
                Item.setTipo_Doc_Identidad(rs.getString("Tipo_Doc_Identidad"));
                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return  Lista;
    } 
}
