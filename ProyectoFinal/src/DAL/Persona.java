package DAL;

import java.io.File;
import java.util.Scanner;

public class Persona {
       //Definir Atributos

    private String Apellidos;
    private String Nombres;
    private String Fec_Nac;
    private String Sexo;
    private String Nacionalidad;
    private String Tipo_Doc_Identidad;
    private String Num_Doc_Identidad;
    private String Departamento;
    private String Provincia;
    private String Distrito;
    private String Direccion;

    //Crear Constructor
    public Persona(String Apellidos,
            String Nombres,
            String Fec_Nac,
            String Sexo,
            String Nacionalidad,
            String Tipo_Doc_Identidad,
            String Num_Doc_Identidad,
            String Departamento,
            String Provincia,
            String Distrito,
            String Direccion) {
        this.Apellidos = Apellidos;
        this.Nombres = Nombres;
        this.Fec_Nac = Fec_Nac;
        this.Sexo = Sexo;
        this.Nacionalidad = Nacionalidad;
        this.Tipo_Doc_Identidad = Tipo_Doc_Identidad;
        this.Num_Doc_Identidad = Num_Doc_Identidad;
        this.Departamento = Departamento;
        this.Provincia = Provincia;
        this.Distrito = Distrito;
        this.Direccion = this.Direccion;

    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getFec_Nac() {
        return Fec_Nac;
    }

    public void setFec_Nac(String Fec_Nac) {
        this.Fec_Nac = Fec_Nac;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }

    public String getTipo_Doc_Identidad() {
        return Tipo_Doc_Identidad;
    }

    public void setTipo_Doc_Identidad(String Tipo_Doc_Identidad) {
        this.Tipo_Doc_Identidad = Tipo_Doc_Identidad;
    }

    public String getNum_Doc_Identidad() {
        return Num_Doc_Identidad;
    }

    public void setNum_Doc_Identidad(String Num_Doc_Identidad) {
        this.Num_Doc_Identidad = Num_Doc_Identidad;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    //Crear Vector de Datos Bidimensional(String)
    public String[][] MatrizNacionalidad;
    public String[][] MatrizDocumentosIdentidad;
    public String[][] MatrizDepartamento;
    public String[][] MatrizProvincia;
    public String[][] MatrizDistrito;

    //crear metodo público leer_Nacionalidad
    public void Leer_Nacionalidad(String Ruta) {
        Scanner Linea;

        File Leer_Doc = new File(Ruta);

        int Fila = 0;

        try {
            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) {
                Linea.nextLine();

                Fila++;
            }
            Fila = (Fila / 1);

            //asignar tamaño a la matriz
            MatrizNacionalidad = new String[Fila][1];

            Fila = 0;

            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) {
                this.setNacionalidad(Linea.nextLine());

                MatrizNacionalidad[Fila][0] = this.getNacionalidad();

                Fila++;
            }
        } catch (Exception Error) {
            System.out.println("Error:" + Error.getMessage());
        }
    }

    public void Leer_Documentos_Identidad(String Ruta) {

        Scanner Linea;

        File Leer_Doc = new File(Ruta);

        int Fila = 0;

        try {
            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) {
                Linea.nextLine();

                Fila++;
            }

            Fila = (Fila / 1);

            MatrizDocumentosIdentidad = new String[Fila][1];

            Fila = 0;

            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) {
                this.setTipo_Doc_Identidad(Linea.nextLine());

                MatrizDocumentosIdentidad[Fila][0] = this.getTipo_Doc_Identidad();

                Fila++;

            }
        } catch (Exception Error) {
            System.out.println("Error:" + Error.getMessage());
        }
    }

    public void Leer_Departamentos(String Ruta) {

        Scanner Linea;

        File Leer_Doc = new File(Ruta);

        int Fila = 0;

        try {
            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) {
                Linea.nextLine();

                Fila++;
            }

            Fila = (Fila / 1);

            MatrizDepartamento = new String[Fila][1];

            Fila = 0;

            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) 
            {
                this.setDepartamento(Linea.nextLine());

                MatrizDepartamento[Fila][0] = this.getDepartamento();

                Fila++;

            }
        } catch (Exception Error) {
            System.out.println("Error:" + Error.getMessage());
        }
    }
    public void Leer_Provincias(String Ruta) {

        Scanner Linea;

        File Leer_Doc = new File(Ruta);

        int Fila = 0;

        try {
            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) {
                Linea.nextLine();

                Fila++;
            }

            Fila = (Fila / 2);

            MatrizProvincia = new String[Fila][2];

            Fila = 0;

            Linea = new Scanner(Leer_Doc);

            while (Linea.hasNextLine()) 
            {
                this.setProvincia(Linea.nextLine());
                this.setDepartamento(Linea.nextLine());

                MatrizProvincia[Fila][0] = this.getProvincia();
                MatrizProvincia[Fila][1] = this.getDepartamento();
                
                Fila++;

            }
        } catch (Exception Error) {
            System.out.println("Error:" + Error.getMessage());
        }
    }
        //Crear Método Público: Leer_Distritos    
    public void Leer_Distritos(String Ruta) {
     
       //Declarar una variable de Tipo: Scanner 
       Scanner Linea; 
        
        //Obtener el Nombre del Archivo de Texto 
        File Leer_Doc = new File(Ruta); 
         
       //Declarar una Variable de Tipo: Contador        
       int Fila = 0; 
        
        //Crear Controlador de Error         
        try 
        {             
           //Leer Documento (Nombre del Archivo) 
           Linea = new Scanner(Leer_Doc); 
            
           //Crear Estructura Repetitiva (Bucle): while (Leer las líneas de texto del Archivo)            
           while (Linea.hasNextLine()) 
           { 
               //Leer Líneas 
               Linea.nextLine(); 
                
               //Incrementar el valor de la Variable  
               Fila++; 
           } 
                      
           //Establecer Valor de Fila 
           Fila = (Fila / 3); // 3 Columnas 
            
           //Asignar Tamaño de la Matriz de Datos 
           MatrizDistrito = new String [Fila][3]; // 3 Columnas 
             
           //Volver a Asignar el valor de 0 (Cero) a la Variable: Fila 
           Fila = 0; 
            
           //Leer Documento (Nombre del Archivo) 
           Linea = new Scanner(Leer_Doc); 
             
            //Crear Estructura Repetitiva (Bucle): while (Leer líneas del archivo)             
            while (Linea.hasNextLine()) 
            { 
                //Leer Datos del Archivo de Texto                 
                this.setDistrito(Linea.nextLine());                 
                this.setProvincia(Linea.nextLine());                 
                this.setDepartamento(Linea.nextLine()); 
   
                //Establecer Valores a la Matriz de Datos 
                MatrizDistrito[Fila][0] = this.getDistrito(); 
                MatrizDistrito[Fila][1] = this.getProvincia(); 
                MatrizDistrito[Fila][2] = this.getDepartamento(); 
                         
                //Incrementar el valor de la Variable  
                Fila++; 
            } 
        } 
        catch (Exception Error) 
        { 
            //Mostrar Error 
            System.out.println("Error: " + Error.getMessage()); 
        } 
    } 

}
