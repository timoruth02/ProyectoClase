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
public class HabitacionEntity {

    private String NumHabitacion = "";
    private String CodSede = "";
    private String CodNivel = "";
    private String CodTipo_Habitacion = "";
    private String Descripcion_Habitacion = "";
    private double Precio_Hora = 0;
    private double Precio_Noche = 0;
    private String Estado_Habitacion = "";

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
     * @return the CodTipo_Habitacion
     */
    public String getCodTipo_Habitacion() {
        return CodTipo_Habitacion;
    }

    /**
     * @param CodTipo_Habitacion the CodTipo_Habitacion to set
     */
    public void setCodTipo_Habitacion(String CodTipo_Habitacion) {
        this.CodTipo_Habitacion = CodTipo_Habitacion;
    }

    /**
     * @return the Descripcion_Habitacion
     */
    public String getDescripcion_Habitacion() {
        return Descripcion_Habitacion;
    }

    /**
     * @param Descripcion_Habitacion the Descripcion_Habitacion to set
     */
    public void setDescripcion_Habitacion(String Descripcion_Habitacion) {
        this.Descripcion_Habitacion = Descripcion_Habitacion;
    }

    /**
     * @return the Precio_Hora
     */
    public double getPrecio_Hora() {
        return Precio_Hora;
    }

    /**
     * @param Precio_Hora the Precio_Hora to set
     */
    public void setPrecio_Hora(double Precio_Hora) {
        this.Precio_Hora = Precio_Hora;
    }

    /**
     * @return the Precio_Noche
     */
    public double getPrecio_Noche() {
        return Precio_Noche;
    }

    /**
     * @param Precio_Noche the Precio_Noche to set
     */
    public void setPrecio_Noche(double Precio_Noche) {
        this.Precio_Noche = Precio_Noche;
    }

    /**
     * @return the Estado_Habitacion
     */
    public String getEstado_Habitacion() {
        return Estado_Habitacion;
    }

    /**
     * @param Estado_Habitacion the Estado_Habitacion to set
     */
    public void setEstado_Habitacion(String Estado_Habitacion) {
        this.Estado_Habitacion = Estado_Habitacion;
    }
//    ENTIDADES ENLAZADAS  ********************************************************************************************************

    private String Piso = "";
    private String Tipo = "";

    public String getPiso() {
        return Piso;
    }

    public void setPiso(String Piso) {
        this.Piso = Piso;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    private String Sede = "";
    private String NumHa = "";

    public String getSede() {
        return Sede;
    }

    public void setSede(String Sede) {
        this.Sede = Sede;
    }

    public String getNumHa() {
        return NumHa;
    }

    public void setNumHa(String NumHa) {
        this.NumHa = NumHa;
    }
}
