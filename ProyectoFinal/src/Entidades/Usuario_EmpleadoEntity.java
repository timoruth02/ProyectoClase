/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author PRV
 */
public class Usuario_EmpleadoEntity {

    private String CodEmpleado = "";
    private String CodUsuario = "";
    private String Password = "";
    private String Perfil = "";
    private Date Fec_Registro = new Date();
    private String Estado_Empleado = "";

    /**
     * @return the CodEmpleado
     */
    public String getCodEmpleado() {
        return CodEmpleado;
    }

    /**
     * @param CodEmpleado the CodEmpleado to set
     */
    public void setCodEmpleado(String CodEmpleado) {
        this.CodEmpleado = CodEmpleado;
    }

    /**
     * @return the CodUsuario
     */
    public String getCodUsuario() {
        return CodUsuario;
    }

    /**
     * @param CodUsuario the CodUsuario to set
     */
    public void setCodUsuario(String CodUsuario) {
        this.CodUsuario = CodUsuario;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the Perfil
     */
    public String getPerfil() {
        return Perfil;
    }

    /**
     * @param Perfil the Perfil to set
     */
    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }

    /**
     * @return the Fec_Registro
     */
    public Date getFec_Registro() {
        return Fec_Registro;
    }

    /**
     * @param Fec_Registro the Fec_Registro to set
     */
    public void setFec_Registro(Date Fec_Registro) {
        this.Fec_Registro = Fec_Registro;
    }

    /**
     * @return the Estado_Empleado
     */
    public String getEstado_Empleado() {
        return Estado_Empleado;
    }

    /**
     * @param Estado_Empleado the Estado_Empleado to set
     */
    public void setEstado_Empleado(String Estado_Empleado) {
        this.Estado_Empleado = Estado_Empleado;
    }
    

}
