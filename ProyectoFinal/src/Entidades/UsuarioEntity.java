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
public class UsuarioEntity {

    private String CodCliente = "";
    private String CodUsuario = "";
    private String Password = "";
    private Date Fec_Registro = new Date();
    private String Estado_Usuario = "";

    /**
     * @return the CodCliente
     */
    public String getCodCliente() {
        return CodCliente;
    }

    /**
     * @param CodCliente the CodCliente to set
     */
    public void setCodCliente(String CodCliente) {
        this.CodCliente = CodCliente;
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
     * @return the Estado_Usuario
     */
    public String getEstado_Usuario() {
        return Estado_Usuario;
    }

    /**
     * @param Estado_Usuario the Estado_Usuario to set
     */
    public void setEstado_Usuario(String Estado_Usuario) {
        this.Estado_Usuario = Estado_Usuario;
    }
    

}
