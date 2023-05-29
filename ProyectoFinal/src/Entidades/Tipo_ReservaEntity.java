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
public class Tipo_ReservaEntity {
        private String CodTipo_Reserva = "";
    private String Descripcion_TipoReserva = "";

    /**
     * @return the CodTipo_Reserva
     */
    public String getCodTipo_Reserva() {
        return CodTipo_Reserva;
    }

    /**
     * @param CodTipo_Reserva the CodTipo_Reserva to set
     */
    public void setCodTipo_Reserva(String CodTipo_Reserva) {
        this.CodTipo_Reserva = CodTipo_Reserva;
    }

    /**
     * @return the Descripcion_TipoReserva
     */
    public String getDescripcion_TipoReserva() {
        return Descripcion_TipoReserva;
    }

    /**
     * @param Descripcion_TipoReserva the Descripcion_TipoReserva to set
     */
    public void setDescripcion_TipoReserva(String Descripcion_TipoReserva) {
        this.Descripcion_TipoReserva = Descripcion_TipoReserva;
    }
    
}
