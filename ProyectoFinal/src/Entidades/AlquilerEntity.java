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
public class AlquilerEntity {
    private String NumAlquiler = "";
    private String CodSede = "";
    private String CodCliente = "";
    private String CodEmpleado = "";
    private String NumHabitacion = "";
    private Date Fec_Alquiler = new Date();
    private Date Fec_TerminoAlquiler = new Date();
    private double Importe = 0;
    /**
     * @return the NumAlquiler
     */
    public String getNumAlquiler() {
        return NumAlquiler;
    }

    /**
     * @param NumAlquiler the NumAlquiler to set
     */
    public void setNumAlquiler(String NumAlquiler) {
        this.NumAlquiler = NumAlquiler;
    }

    /**
     * @return the CodSede
     */
    public String getCodSede() {
        return CodSede;
    }

    /**
     * @param CodSede the CodSede to set
     */
    public void setCodSede(String CodSede) {
        this.CodSede = CodSede;
    }

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
     * @return the NumHabitacion
     */
    public String getNumHabitacion() {
        return NumHabitacion;
    }

    /**
     * @param NumHabitacion the NumHabitacion to set
     */
    public void setNumHabitacion(String NumHabitacion) {
        this.NumHabitacion = NumHabitacion;
    }

    /**
     * @return the Fec_Alquiler
     */
    public Date getFec_Alquiler() {
        return Fec_Alquiler;
    }

    /**
     * @param Fec_Alquiler the Fec_Alquiler to set
     */
    public void setFec_Alquiler(Date Fec_Alquiler) {
        this.Fec_Alquiler = Fec_Alquiler;
    }

    /**
     * @return the Fec_TerminoAlquiler
     */
    public Date getFec_TerminoAlquiler() {
        return Fec_TerminoAlquiler;
    }

    /**
     * @param Fec_TerminoAlquiler the Fec_TerminoAlquiler to set
     */
    public void setFec_TerminoAlquiler(Date Fec_TerminoAlquiler) {
        this.Fec_TerminoAlquiler = Fec_TerminoAlquiler;
    }

    /**
     * @return the Importe
     */
    public double getImporte() {
        return Importe;
    }

    /**
     * @param Importe the Importe to set
     */
    public void setImporte(double Importe) {
        this.Importe = Importe;
    }


}
