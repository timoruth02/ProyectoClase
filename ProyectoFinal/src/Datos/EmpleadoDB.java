package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadoDB {

    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<EmpleadoEntity> GetEmpleados() {
        ArrayList<EmpleadoEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select \n"
                    + "CodEmpleado,Apellidos,Nombres,\n"
                    + "Fec_Nac,Sexo,CodNac,\n"
                    + "CodDoc_Identidad,NumDoc_Identidad,\n"
                    + "CodUbigeo,Direccion,Telefono,\n"
                    + "Email,CodSede,CodArea,CodCargo,\n"
                    + "Sueldo,Estado_Empleado,Obs_Estado_Empleado \n"
                    + "from Empleado";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            EmpleadoEntity Item = new EmpleadoEntity();
            while (rs.next()) {
                Item = null;
                Item = new EmpleadoEntity();
                Item.setCodEmpleado(rs.getString("CodEmpleado"));
                Item.setApellidos(rs.getString("Apellidos"));
                Item.setNombres(rs.getString("Nombres"));
                Item.setFec_Nac(rs.getDate("Fec_Nac"));
                Item.setSexo(rs.getString("Sexo"));
                Item.setCodNac(rs.getString("CodNac"));
                Item.setCodDoc_Identidad(rs.getString("CodDoc_Identidad"));
                Item.setNumDoc_Identidad(rs.getString("NumDoc_Identidad"));
                Item.setCodUbigeo(rs.getString("CodUbigeo"));
                Item.setDireccion(rs.getString("Direccion"));
                Item.setTelefono(rs.getString("Telefono"));
                Item.setEmail(rs.getString("Email"));
                Item.setCodSede(rs.getString("CodSede"));
                Item.setCodArea(rs.getString("CodArea"));
                Item.setCodCargo(rs.getString("CodCargo"));
                Item.setSueldo(rs.getDouble("Sueldo"));
                Item.setEstado_Empleado(rs.getString("Estado_Empleado"));
                Item.setObs_Estado_Empleado(rs.getString("Obs_Estado_Empleado"));
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
