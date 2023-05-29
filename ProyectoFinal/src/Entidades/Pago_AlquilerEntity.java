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
public class Pago_AlquilerEntity {

    private String NumPago = "";
    private String CodEmpleado = "";
    private String NumAlquiler = "";
    private Date Fec_Pago = new Date();
    private double Importe = 0;
    private String CodForma_Pago = "";
    private String Estado_Pago = "";

    /**
     * @return the NumPago
     */
    public String getNumPago() {
        return NumPago;
    }

    /**
     * @param NumPago the NumPago to set
     */
    public void setNumPago(String NumPago) {
        this.NumPago = NumPago;
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
     * @return the Fec_Pago
     */
    public Date getFec_Pago() {
        return Fec_Pago;
    }

    /**
     * @param Fec_Pago the Fec_Pago to set
     */
    public void setFec_Pago(Date Fec_Pago) {
        this.Fec_Pago = Fec_Pago;
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

    /**
     * @return the CodForma_Pago
     */
    public String getCodForma_Pago() {
        return CodForma_Pago;
    }

    /**
     * @param CodForma_Pago the CodForma_Pago to set
     */
    public void setCodForma_Pago(String CodForma_Pago) {
        this.CodForma_Pago = CodForma_Pago;
    }

    /**
     * @return the Estado_Pago
     */
    public String getEstado_Pago() {
        return Estado_Pago;
    }

    /**
     * @param Estado_Pago the Estado_Pago to set
     */
    public void setEstado_Pago(String Estado_Pago) {
        this.Estado_Pago = Estado_Pago;
    }

}
