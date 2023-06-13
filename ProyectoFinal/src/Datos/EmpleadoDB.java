package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadoDB {

    Connection cn;
    Conexion conect = new Conexion();

    public boolean Save(AlquilerEntity Item) {

        try {
            cn = conect.conectar();
            String sql = "INSERT INTO Alquiler\n" +
"           (NumAlquiler,\n" +
"           CodSede,\n" +
"           CodCliente,\n" +
"           CodEmpleado,\n" +
"           NumHabitacion,\n" +
"           Fec_Alquiler,\n" +
"           Fec_TerminoAlquiler,\n" +
"           Importe)\n" +
"     VALUES\n" +
"           ('"+Item.getNumAlquiler()+"'\n" +
"           ,'"+Item.getCodSede()+"'\n" +
"           ,'"+Item.getCodCliente()+"'\n" +
"           ,'"+Item.getCodEmpleado()+" '\n" +
"           ,'"+Item.getNumHabitacion()+"'\n" +
"           ,"+Item.getFec_Alquiler()+"\n" +
"           ,"+Item.getFec_TerminoAlquiler()+"\n" +
"           ,"+Item.getImporte()+")";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
     
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
