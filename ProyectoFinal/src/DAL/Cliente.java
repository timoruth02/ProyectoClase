package DAL;

import java.io.File;
import java.util.Scanner;

public class Cliente extends Persona {

    //Atributos de la Sub Clase: Cliente     
    private String CodCliente;
    private String Telefono;
    private String Email;
    private String Estado_Cliente;
    private String Foto;

    //Constructor de la Sub Clase: Cliente     
    public Cliente(String Cli_Apellidos,
            String Cli_Nombres,
            String Cli_Fec_Nac,
            String Cli_Sexo,
            String Cli_Nacionalidad,
            String Cli_Tipo_Doc_Identidad,
            String Cli_Num_Doc_Identidad,
            String Cli_Departamento,
            String Cli_Provincia,
            String Cli_Distrito,
            String Cli_Direccion,
            String Cli_CodCliente,
            String Cli_Telefono,
            String Cli_Email,
            String Cli_Estado_Cliente,
            String Cli_Foto) {

        super(Cli_Apellidos,
                Cli_Nombres,
                Cli_Fec_Nac,
                Cli_Sexo,
                Cli_Nacionalidad,
                Cli_Tipo_Doc_Identidad,
                Cli_Num_Doc_Identidad,
                Cli_Departamento,
                Cli_Provincia,
                Cli_Distrito,
                Cli_Direccion);
        this.CodCliente = Cli_CodCliente;
        this.Telefono = Cli_Telefono;
        this.Email = Cli_Email;
        this.Foto = Cli_Foto;
        this.Estado_Cliente = Cli_Estado_Cliente;
    }

    //Definir las Propiedades (Encapsulamiento de Datos)     
    public String getCodCliente() {
        return CodCliente;
    }

    public void setCodCliente(String CodCliente) {
        this.CodCliente = CodCliente;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public String getEstado_Cliente() {
        return Estado_Cliente;
    }

    public void setEstado_Cliente(String Estado_Cliente) {
        this.Estado_Cliente = Estado_Cliente;
    }

    //Crear un Vector de Datos Bidimensional (String): Matriz de Datos     
    public String[][] MatrizClientes;

    //Crear el Método: Leer_Clientes()     
    public void Leer_Cientes(String Ruta) {
        //Declarar una variable de Tipo: Scanner 
        Scanner Linea;

        //Obtener el Nombre del Archivo de Texto 
        File Leer_Doc = new File(Ruta);

        //Declarar una Variable de Tipo: Contador 
        int Fila = 0;

        //Crear Controlador de Error         
        try {
            //Leer Documento (Nombre del Archivo) 
            Linea = new Scanner(Leer_Doc);

            //Crear Estructura Repetitiva (Bucle): while (Leer las líneas de texto del Archivo)            
            while (Linea.hasNextLine()) {
                //Leer Líneas 
                Linea.nextLine();

                //Incrementar el valor de la Variable  
                Fila++;
            }

            //Establecer Valor de Fila 
            Fila = (Fila / 16); // 16 Columnas 

            //Asignar Tamaño de la Matriz de Datos 
            MatrizClientes = new String[Fila][16]; // 16 Columnas 

            //Volver a Asignar el valor de 0 (Cero) a la Variable: Fila 
            Fila = 0;

            //Leer Documento (Nombre del Archivo) 
            Linea = new Scanner(Leer_Doc);

            //Crear Estructura Repetitiva (Bucle): while (Leer líneas del archivo)             
            while (Linea.hasNextLine()) {
                //Leer Datos del Archivo de Texto 
                this.setCodCliente(Linea.nextLine());
                this.setApellidos(Linea.nextLine());
                this.setNombres(Linea.nextLine());
                this.setFec_Nac(Linea.nextLine());
                this.setSexo(Linea.nextLine());
                this.setNacionalidad(Linea.nextLine());
                this.setTipo_Doc_Identidad(Linea.nextLine());
                this.setNum_Doc_Identidad(Linea.nextLine());
                this.setDepartamento(Linea.nextLine());
                this.setProvincia(Linea.nextLine());
                this.setDistrito(Linea.nextLine());
                this.setDireccion(Linea.nextLine());
                this.setTelefono(Linea.nextLine());
                this.setEmail(Linea.nextLine());
                this.setFoto(Linea.nextLine());
                this.setEstado_Cliente(Linea.nextLine());

                //Establecer Valores a la Matriz de Datos 
                MatrizClientes[Fila][0] = this.getCodCliente();
                MatrizClientes[Fila][1] = this.getApellidos();
                MatrizClientes[Fila][2] = this.getNombres();
                MatrizClientes[Fila][3] = this.getFec_Nac();
                MatrizClientes[Fila][4] = this.getSexo();
                MatrizClientes[Fila][5] = this.getNacionalidad();
                MatrizClientes[Fila][6] = this.getTipo_Doc_Identidad();
                MatrizClientes[Fila][7] = this.getNum_Doc_Identidad();
                MatrizClientes[Fila][8] = this.getDepartamento();
                MatrizClientes[Fila][9] = this.getProvincia();
                MatrizClientes[Fila][10] = this.getDistrito();
                MatrizClientes[Fila][11] = this.getDireccion();
                MatrizClientes[Fila][12] = this.getTelefono();
                MatrizClientes[Fila][13] = this.getEmail();
                MatrizClientes[Fila][14] = this.getFoto();
                MatrizClientes[Fila][15] = this.getEstado_Cliente();                 //Incrementar el valor de la Variable  
                Fila++;
            }
        } catch (Exception Error) {
            //Mostrar Error 
            System.out.println("Error: " + Error.getMessage());
        }
    }

}
