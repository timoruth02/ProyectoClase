package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Usuario_EmpleadoDB {
    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<Usuario_EmpleadoEntity> GetBuscarUsuario_Empleado(String Codigo, String Password) {
        ArrayList<Usuario_EmpleadoEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select \n"
                    + "U_Em.CodEmpleado,\n"
                    + "U_Em.CodUsuario,\n"
                    + "U_Em.Password,\n"
                    + "U_Em.Perfil,\n"
                    + "U_Em.Fec_Registro,\n"
                    + "U_Em.Estado_Empleado,\n"
                    + "Em.Apellidos,\n"
                    + "Em.Nombres,\n"
                    + "Em.CodSede,\n"
                    + "Se.Nombre_Sede\n"
                    + "from Usuario_Empleado U_Em\n"
                    + "inner join Empleado as Em\n"
                    + "on Em.CodEmpleado = U_Em.CodEmpleado\n"
                    + "inner join Sede as Se\n"
                    + "on Se.CodSede = Em.CodSede\n"
                    + "where CodUsuario = '" + Codigo + "' and Password = '"+ Password + "'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            Usuario_EmpleadoEntity Item = new Usuario_EmpleadoEntity();
            while (rs.next()) {
                Item = null;
                Item = new Usuario_EmpleadoEntity();
                Item.setCodEmpleado(rs.getString("CodEmpleado"));
                Item.setCodUsuario(rs.getString("CodUsuario"));
                Item.setPassword(rs.getString("Password"));
                Item.setPerfil(rs.getString("Perfil"));
                Item.setFec_Registro(rs.getDate("Fec_Registro"));
                Item.setEstado_Empleado(rs.getString("Estado_Empleado"));
                Item.setApellidos(rs.getString("Apellidos"));
                Item.setNombres(rs.getString("Nombres"));
                Item.setCodSede(rs.getString("CodSede"));
                Item.setNombre_Sede(rs.getString("Nombre_Sede"));
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
