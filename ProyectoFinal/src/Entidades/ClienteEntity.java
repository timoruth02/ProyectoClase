package Entidades;

import java.util.Date;

public class ClienteEntity {

    private String CodCliente = "";
    private String Apellidos = "";
    private String Nombres = "";
    private Date Fec_Nac = new Date();
    private String Sexo = "";
    private String CodNac = "";
    private String CodDoc_Identidad = "";
    private String NumDoc_Identidad = "";
    private String CodUbigeo = "";
    private String Direccion = "";
    private String Telefono = "";
    private String Email = "";
    private String Estado_Cliente = "";

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
     * @return the Apellidos
     */
    public String getApellidos() {
        return Apellidos;
    }

    /**
     * @param Apellidos the Apellidos to set
     */
    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    /**
     * @return the Nombres
     */
    public String getNombres() {
        return Nombres;
    }

    /**
     * @param Nombres the Nombres to set
     */
    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    /**
     * @return the Fec_Nac
     */
    public Date getFec_Nac() {
        return Fec_Nac;
    }

    /**
     * @param Fec_Nac the Fec_Nac to set
     */
    public void setFec_Nac(Date Fec_Nac) {
        this.Fec_Nac = Fec_Nac;
    }

    /**
     * @return the Sexo
     */
    public String getSexo() {
        return Sexo;
    }

    /**
     * @param Sexo the Sexo to set
     */
    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    /**
     * @return the CodNac
     */
    public String getCodNac() {
        return CodNac;
    }

    /**
     * @param CodNac the CodNac to set
     */
    public void setCodNac(String CodNac) {
        this.CodNac = CodNac;
    }

    /**
     * @return the CodDoc_Identidad
     */
    public String getCodDoc_Identidad() {
        return CodDoc_Identidad;
    }

    /**
     * @param CodDoc_Identidad the CodDoc_Identidad to set
     */
    public void setCodDoc_Identidad(String CodDoc_Identidad) {
        this.CodDoc_Identidad = CodDoc_Identidad;
    }

    /**
     * @return the NumDoc_Identidad
     */
    public String getNumDoc_Identidad() {
        return NumDoc_Identidad;
    }

    /**
     * @param NumDoc_Identidad the NumDoc_Identidad to set
     */
    public void setNumDoc_Identidad(String NumDoc_Identidad) {
        this.NumDoc_Identidad = NumDoc_Identidad;
    }

    /**
     * @return the CodUbigeo
     */
    public String getCodUbigeo() {
        return CodUbigeo;
    }

    /**
     * @param CodUbigeo the CodUbigeo to set
     */
    public void setCodUbigeo(String CodUbigeo) {
        this.CodUbigeo = CodUbigeo;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Estado_Cliente
     */
    public String getEstado_Cliente() {
        return Estado_Cliente;
    }

    /**
     * @param Estado_Cliente the Estado_Cliente to set
     */
    public void setEstado_Cliente(String Estado_Cliente) {
        this.Estado_Cliente = Estado_Cliente;
    }

}
