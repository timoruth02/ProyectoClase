/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author PRV
 */
public class NivelEntity {

    private String CodNivel = "";
    private String Nivel_Piso = "";

    /**
     * @return the CodNivel
     */
    public String getCodNivel() {
        return CodNivel;
    }

    /**
     * @param CodNivel the CodNivel to set
     */
    public void setCodNivel(String CodNivel) {
        this.CodNivel = CodNivel;
    }

    /**
     * @return the Nivel_Piso
     */
    public String getNivel_Piso() {
        return Nivel_Piso;
    }

    /**
     * @param Nivel_Piso the Nivel_Piso to set
     */
    public void setNivel_Piso(String Nivel_Piso) {
        this.Nivel_Piso = Nivel_Piso;
    }

}
