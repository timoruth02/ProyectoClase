package Datos;

import Entidades.NivelEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NivelDB {
  Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<NivelEntity> getCodNivelItems() {
        ArrayList<NivelEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select CodNivel, Nivel_Piso from Nivel";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            NivelEntity Item = new NivelEntity();
            while (rs.next()) {
                Item = null;
                Item = new NivelEntity();
                Item.setCodNivel(rs.getString("CodNivel"));
                Item.setNivel_Piso(rs.getString("Nivel_Piso"));
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
