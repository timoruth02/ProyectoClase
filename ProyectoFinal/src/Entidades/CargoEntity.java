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
public class CargoEntity {

    private String CodCargo = "";
    private String Nombre_Cargo = "";

    /**
     * @return the CodCargo
     */
    public String getCodCargo() {
        return CodCargo;
    }

    /**
     * @param CodCargo the CodCargo to set
     */
    public void setCodCargo(String CodCargo) {
        this.CodCargo = CodCargo;
    }

    /**
     * @return the Nombre_Cargo
     */
    public String getNombre_Cargo() {
        return Nombre_Cargo;
    }

    /**
     * @param Nombre_Cargo the Nombre_Cargo to set
     */
    public void setNombre_Cargo(String Nombre_Cargo) {
        this.Nombre_Cargo = Nombre_Cargo;
    }

}
