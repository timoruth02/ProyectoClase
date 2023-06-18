package Panel;

import DAL.*;
import Datos.*;
import Entidades.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javafx.scene.input.KeyCode.S;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JFrm_Datos_Cliente extends javax.swing.JPanel {

    //Declarar una Variable de Tipo: JFrame 
    JFrame Formulario;

    //Declarar variable de tipo: DefaultTableModel (Modelo de Tabla) 
    DefaultTableModel Modelo;

    //Declarar variable de tipo: TableRowSorter (Clasificador de Filas de Tabla) 
    TableRowSorter TFiltroFila; //Variable para Filtrar Datos en un Cojunto de Filas de una Tabla 

    //Declarar variable de tipo: Entero    
    int Codigo = 1, Filas;
    //Establecer el Directorio del Proyecto, de donde se desea leer y guardar datos del Archivo de Texto 
    String DirectorioNacionalidad = new File("src/Archivo_Datos/Registro_Nacionalidad.txt").getAbsolutePath();
    String DirectorioDocIdentidad = new File("src/Archivo_Datos/Registro_Documentos_Identidad.txt").getAbsolutePath();
    String DirectorioDepartamento = new File("src/Archivo_Datos/Registro_Departamentos.txt").getAbsolutePath();
    String DirectorioProvincia = new File("src/Archivo_Datos/Registro_Provincia.txt").getAbsolutePath();
    String DirectorioDistrito = new File("src/Archivo_Datos/Registro_Distrito.txt").getAbsolutePath();
    String DirectorioCliente = new File("src/Archivo_Datos/Registro_Clientes.txt").getAbsolutePath();
    //Instanciar la Clase: Persona, a través de un Objeto 
    Cliente ObjCliente = new Cliente("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

    //Declarar una variable Local de tipo: String 
    String CodCliente;

    //Declarar un variable de tipo: SimpleDateFormat (Formato de Fecha Simple)     
    SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public JFrm_Datos_Cliente() {
        initComponents();

        //Establecer un Nuevo modelo de la Clase: DefaultTableModel
        Modelo = new DefaultTableModel() {
            public boolean isCellEditable(int Fila, int Columna) {
                return false;
            }
        };

        //Establecer Nombre de las Columnas para el Control: jTable_RegistroNotas (A través de la variable: Modelo) 
        Modelo.addColumn("Código");
        Modelo.addColumn("Apellidos");
        Modelo.addColumn("Nombres");
        Modelo.addColumn("Fec_Nac");
        Modelo.addColumn("Sexo");
        Modelo.addColumn("Nacionalidad");
        Modelo.addColumn("Doc. Identidad");
        Modelo.addColumn("N° Doc. Identidad");
        Modelo.addColumn("Departamento");
        Modelo.addColumn("Provincia");
        Modelo.addColumn("Distrito");
        Modelo.addColumn("Dirección");
        Modelo.addColumn("Teléfono");
        Modelo.addColumn("Email");
        Modelo.addColumn("Foto");
        Modelo.addColumn("Estado del Cliente");

        //Establecer el Modelo al Control: jTable_RegistroClientes         
        this.jTable_DatosCliente.setModel(Modelo);

        //Invocar a Métodos: 
        //  this.Cargar_OpcionesBusqueda();   
        this.Cargar_Nacionalidad();
        this.Cargar_Doc_Identidad("<Seleccione>");
        this.Cargar_Departamento();
        this.Botones(true);
        this.Habilitar_Controles(false);
        this.AutoAjustar_Columnas();
        this.Leer_Datos();
        this.Total_Filas();

//        this.Limpiar();
    }
    //Declarar una Variable de Tipo: FileInputStream (Flujo de Entrada de Archivo)     
    FileInputStream Cargar_Archivo;

    //Crear el Método Local: Limpiar()     
    void Limpiar() {
        //Limpiar el Texto de los Controles: JTextField         
            this.TXTApellidos.setText("");
        this.TXTNombres.setText("");

        this.TXTNumDoc_Identidad.setText("");
        this.TXTDireccion.setText("");
        this.TXTNumTelefono.setText("");
        this.TXTEmail.setText("");
//        this.TXTBuscar.setText("");

        //Establecer la Selección del Primer índice (Elemento) del ComboBox         
//        this.CBO_Nacionalidad.setSelectedIndex(0);
//        this.CBO_Tipo_Doc_Identidad.setSelectedIndex(0);
//        this.CBO_Departamento.setSelectedIndex(0);
//        this.CBO_Provincia.setSelectedIndex(0);
//        this.CBO_Distrito.setSelectedIndex(0);
        //this.CBO_CampoBuscar.setSelectedIndex(0); 

        //Limpiar el Texto de los Controles: Labels         
        this.Lb_Codigo.setText("");
//        this.Lb_Foto.setIcon(null);         
//        this.Lb_Localizador.setText(""); 

        //Ubicar el Cursor en el Control: TXTApellidos         
        this.TXTApellidos.requestFocus();
    }

    //Crear Método Local: Cargar_Nacionalidad    
    void Cargar_Nacionalidad() {
        //Limpiar todos los Elementos del ComboBox: CBO_Nacionalidad        
//        this.CBO_Nacionalidad.removeAllItems();

        //Agregar un Primer Elemento al ComboBox: CBO_Nacionalidad       
        this.CBO_Nacionalidad.addItem("<Seleccione>");

        //Invocar al Método: Leer_Nacionalidad 
        ObjCliente.Leer_Nacionalidad(DirectorioNacionalidad);

        //Recorrer los Elementos de la Matriz: MatrizNacionalidad       
        for (int Fila = 0; Fila < ObjCliente.MatrizNacionalidad.length; Fila++) {
            //Agregar Datos al Control: CBO_Nacionalidad         
            this.CBO_Nacionalidad.addItem(ObjCliente.MatrizNacionalidad[Fila][0]);
        }
    }

    //Crear Método Local: Cargar_Doc_Identidad     
    void Cargar_Doc_Identidad(String Nacionalidad) {
        //Limpiar todos los Elementos del ComboBox: CBO_Tipo_Doc_Identidad   
//        this.CBO_Tipo_Doc_Identidad.removeAllItems();

        //Agregar un Primer Elemento al ComboBox: CBO_Tipo_Doc_Identidad         
        this.CBO_Tipo_Doc_Identidad.addItem("<Seleccione>");

        //Invocar al Método: Leer_Documentos_Identidad 
        ObjCliente.Leer_Documentos_Identidad(DirectorioDocIdentidad);

        if (Nacionalidad.equals("Peruana")) {
            //Recorrer los Elementos de la Matriz: MatrizDocumentosIdentidad            
            for (int Fila = 0; Fila < ObjCliente.MatrizDocumentosIdentidad.length; Fila++) {
                if (ObjCliente.MatrizDocumentosIdentidad[Fila][0].substring(0, 6).equals("D.N.I.")
                        || ObjCliente.MatrizDocumentosIdentidad[Fila][0].substring(0, 6).equals("R.U.C.")) {
                    //Agregar Datos al Control: CBO_Tipo_Doc_Identidad 
                    this.CBO_Tipo_Doc_Identidad.addItem(ObjCliente.MatrizDocumentosIdentidad[Fila][0]);
                }
            }
        } else if (!"<Seleccione>".equals(Nacionalidad)) {
            //Recorrer los Elementos de la Matriz: MatrizDocumentosIdentidad             
            for (int Fila = 0; Fila < ObjCliente.MatrizDocumentosIdentidad.length; Fila++) {
                if (!"D.N.I.".equals(ObjCliente.MatrizDocumentosIdentidad[Fila][0].substring(0, 6))
                        && !"R.U.C.".equals(ObjCliente.MatrizDocumentosIdentidad[Fila][0].substring(0, 6))) {
                    //Agregar Datos al Control: CBO_Tipo_Doc_Identidad 
                    this.CBO_Tipo_Doc_Identidad.addItem(ObjCliente.MatrizDocumentosIdentidad[Fila][0]);
                }
            }
        }
    }

    //Crear Método Local: Cargar_Departamento     
    void Cargar_Departamento() {
        //Limpiar todos los Elementos del ComboBox: CBO_Departamento         
//        this.CBO_Departamento.removeAllItems();

        //Agregar un Primer Elemento al ComboBox: CBO_Departamento         
        this.CBO_Departamento.addItem("<Seleccione>");

        //Invocar al Método: Leer_Departamentos 
        ObjCliente.Leer_Departamentos(DirectorioDepartamento);

        //Recorrer los Elementos de la Matriz: MatrizDepartamento         
        for (int Fila = 0; Fila < ObjCliente.MatrizDepartamento.length; Fila++) {
            //Agregar Datos al Control: CBO_Departamento             
            this.CBO_Departamento.addItem(ObjCliente.MatrizDepartamento[Fila][0]); // 0 Es la Columna del Departamento 
        }
    }

    //Crear Método Local: Cargar_Provincia()     
    void Cargar_Provincia(String NombreDepartamento) {
        //Limpiar todos los Elementos del ComboBox: CBO_Provincia       
//        this.CBO_Provincia.removeAllItems();

        //Agregar un Primer Elemento al ComboBox: CBO_Provincia        
        this.CBO_Provincia.addItem("<Seleccione>");

        //Invocar al Método: Leer_Provincias 
        ObjCliente.Leer_Provincias(DirectorioProvincia);
        //Recorrer los Elementos de la Matriz: MatrizProvincia       
        for (int Fila = 0; Fila < ObjCliente.MatrizProvincia.length; Fila++) {
            if (ObjCliente.MatrizProvincia[Fila][1].equals(NombreDepartamento)) // 1 Es la Columna del Departamento 
            {
                //Agregar Datos al Control: CBO_Provincia 
                this.CBO_Provincia.addItem(ObjCliente.MatrizProvincia[Fila][0]); 	// 	0 	Es 	la Columna de la Provincia 
            }
        }
    }

    //Crear Método Local: Cargar_Distrito()     
    void Cargar_Distrito(String NombreProvincia) {
        //Limpiar todos los Elementos del ComboBox: CBO_Distrito         
        this.CBO_Distrito.removeAllItems();

        //Agregar un Primer Elemento al ComboBox: CBO_Distrito         
        this.CBO_Distrito.addItem("<Seleccione>");

        //Invocar al Método: Leer_Distritos 
        ObjCliente.Leer_Distritos(DirectorioDistrito);

        //Recorrer los Elementos de la Matriz: MatrizDistrito        
        for (int Fila = 0; Fila < ObjCliente.MatrizDistrito.length; Fila++) {
            if (ObjCliente.MatrizDistrito[Fila][1].equals(NombreProvincia)) {
                //Agregar Datos al Control: CBO_Distrito                 
                this.CBO_Distrito.addItem(ObjCliente.MatrizDistrito[Fila][0]); // 0 Es la Columna de la Provincia 
            }
        }
    }

    //Crear Método Local: Habilitar_Controles()     
    void Habilitar_Controles(boolean Valor) {
        //Establecer Propiedad: Editable (setEditable)         
        this.TXTApellidos.setEnabled(Valor);
        this.TXTNombres.setEnabled(Valor);
        this.JDateFec_Nac.setEnabled(Valor);
        this.CBO_Nacionalidad.setEnabled(Valor);
        this.CBO_Tipo_Doc_Identidad.setEnabled(Valor);
        this.TXTNumDoc_Identidad.setEnabled(Valor);
        this.CBO_Departamento.setEnabled(Valor);
        this.CBO_Provincia.setEnabled(Valor);
        this.CBO_Distrito.setEnabled(Valor);
        this.TXTDireccion.setEnabled(Valor);
        this.TXTNumTelefono.setEnabled(Valor);
        this.TXTEmail.setEnabled(Valor);
    }

    //Crear Método Local: Botones()    
    void Botones(boolean valor) {
        //Establecer Habilitación de Botones: true/false         
        this.BTN_Nuevo.setEnabled(valor);
        this.BTN_Guardar.setEnabled(!valor);
        //  this.BTN_Cancelar.setEnabled(!valor);
        //this.BTN_Examinar.setEnabled(!valor);         
        this.BTN_Editar.setEnabled(valor);
        this.BTN_Eliminar.setEnabled(valor);
        // this.BTN_Limpiar.setEnabled(valor);
        this.BTN_Salir.setEnabled(valor);
    }

    //Crear Método Local: Solo_Letras()     
    void Solo_Letras(java.awt.event.KeyEvent evt) {
        //Evaluar que solo se haya ingresado Letras y No Números        
        if (!Character.isLetter(evt.getKeyChar()) && !Character.isSpaceChar(evt.getKeyChar())) {
            //Deshabilitar Teclado             
            evt.consume();
        }
    }

    //Crear Método Local: Solo_Numeros() 
    void Solo_Numeros(java.awt.event.KeyEvent evt, javax.swing.JTextField CajaTexto) {
        //Evaluar que solo se haya ingresado Números y No Letras  
        //o Si el Total de caracteres ingresados es mayor o igual a 2 caracteres         
        if (!Character.isDigit(evt.getKeyChar())) {
            //Deshabilitar Teclado             
            evt.consume();
        }
    }

    //Crear Método Local: Cambiar_Cursor()     
    void Cambiar_Cursor(java.awt.event.KeyEvent evt, javax.swing.JTextField CajaTexto) {
        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación)        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: JTextField 
            CajaTexto.requestFocus();
        }
    }

    //Crear Método Local: Convertir_TextoMayusculas()     
    void Convertir_TextoMayusculas(javax.swing.JTextField CajaTexto) {
        //Convertir Texto a Mayúsculas de Control: CajaTexto 
        CajaTexto.setText(CajaTexto.getText().toUpperCase());
    }

    //Crear Método Local: Convertir_TextoMinusculas()     
    void Convertir_TextoMinusculas(javax.swing.JTextField CajaTexto) {
        //Convertir Texto a Mayúsculas de Control: CajaTexto 
        CajaTexto.setText(CajaTexto.getText().toLowerCase());
    }
    //Crear el Método Público: Validar_Longitud_Datos 

    public void Validar_Longitud_Datos(JTextField Control, String Campo, String Texto) {
        //Declarar Variables 
        int NumMinimo = 0, NumMaximo = 0;

        if (Campo != "<Seleccione>") {
            //Evaluar si el Texto convertido a Mayusculas es igual a la Cadena: D.N.I.             
            if (Campo.substring(0, 6).toUpperCase().equals("D.N.I.")) {
                //Establecer Número Mínimo y Máximo de Caracteres 
                NumMinimo = 8;
                NumMaximo = 8;
            } //Evaluar si el Texto convertido a Mayusculas es igual a la Cadena: R.U.C. 
            else if (Campo.substring(0, 6).toUpperCase().equals("R.U.C.")) {
                //Establecer Número Mínimo y Máximo de Caracteres 
                NumMinimo = 11;
                NumMaximo = 11;
            } //Evaluar si el Texto convertido en Mayúsculas es igual a la Cadena: TELEFONO             
            else if (Campo.toUpperCase().equals("TELÉFONO")) {
                //Establecer Número Mínimo y Máximo de Caracteres 
                NumMinimo = 7;
                NumMaximo = 9;
            } else {
                //Establecer Número Máximo de Caracteres 
                NumMinimo = 12;
                NumMaximo = 12;
            }
            //Evaluar si la Longitud del Texto es Mayor a Cero y Menor al Número Mínimo de Caracteres             
            if (Texto.length() > 0 && Texto.length() < NumMinimo) {
                //Mostrar Mensaje de Error 
                JOptionPane.showMessageDialog(null, "Faltan Dígitos en el Campo: ", "Informacion" + Campo.toUpperCase(), JOptionPane.ERROR_MESSAGE);

                //Ubicar Cursor en el Control: JTextField 
                Control.requestFocus();
            } else if (Texto.length() > NumMaximo) {
                //Mostrar Mensaje de Error 
                JOptionPane.showMessageDialog(null, "xcede el N° de Dígitos en el Campo:", "Error", JOptionPane.ERROR_MESSAGE);

                JOptionPane.showMessageDialog(null, "Excede el N° de Dígitos en el Campo: ", "Informacion"
                        + Campo.toUpperCase(), JOptionPane.ERROR_MESSAGE);
                //Ubicar Cursor en el Control: JTextField 
                Control.requestFocus();
            }
        } else {
            //Mostrar Mensaje de Advertencia 
            JOptionPane.showMessageDialog(null, "Seleccione Primero el Tipo de Documento de Identidad del Cliente", "Información", JOptionPane.INFORMATION_MESSAGE);

            //Limpiar Texto 
            Control.setText("");

            //Ubicar Cursor en Control             
            this.CBO_Tipo_Doc_Identidad.requestFocus();
        }
    }

    //Crear una Funcion: MaxLength (Longitud Máxima)     
    public String MaxLength(String Campo, String Texto) {
        //Declarar Variables         
        String Valor = "";
        int NumMaximo = 0;

        //Evaluar si el Texto convertido a Mayusculas es igual a la Cadena: D.N.I. 
        if (Campo.substring(0, 6).toUpperCase().equals("D.N.I.")) {
            //Establecer Número Máximo de Caracteres 
            NumMaximo = 8;
        } //Evaluar si el Texto convertido a Mayusculas es igual a la Cadena: R.U.C. 
        else if (Campo.substring(0, 6).toUpperCase().equals("R.U.C.")) {
            //Establecer Número Máximo de Caracteres 
            NumMaximo = 11;
        } //Evaluar si el Texto convertido a Mayusculas es igual a la Cadena: TELEFONO         
        else if (Campo.toUpperCase().equals("TELÉFONO")) {
            //Establecer Número Máximo de Caracteres 
            NumMaximo = 9;
        } else {
            //Establecer Número Máximo de Caracteres 
            NumMaximo = 12;
        }

        //Evaluar si la Longitud del Texto es Mayor al Número Máximo de Caracteres         
        if (Texto.length() > NumMaximo) {
            //Obtener los Primeros Caracteres de la Variable: Texto 
            Valor = Texto.substring(0, NumMaximo);

            //Asignar Valor 
            Texto = Valor;
        } //Retornar Valor        
        return Texto;
    }

    //Crear el Método Local: Validar_Email()     
    void Validar_Email() {
        //Evaluar si la Longitud de Caracteres del Control: TXTEmail, es mayor a cero        
        if (this.TXTEmail.getText().length() > 0) {
            //Ptrón para Validar el Email 
//            Pattern PatronCaracteres = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([az]+))+");
            Pattern PatronCaracteres = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
            //Email a validar 
            Matcher Mather = PatronCaracteres.matcher(this.TXTEmail.getText());

            //Evaluar si encontró el Patrón de Caracteres 
            if (Mather.find() == false) {
                //Mostrar Mensaje de Error 
                JOptionPane.showMessageDialog(null, "El E-mail ingresado es Inválido!", "Mensaje",
                        JOptionPane.ERROR_MESSAGE);

                //Ubicar el Cursor en el Control: TXTEmail              
                this.TXTEmail.requestFocus();
            }

            //Inovar al Método: Convertir_TextoMinusculas             
            this.Convertir_TextoMinusculas(TXTEmail);
        }
    }

    //Crear Función: Fecha   
    private String Fecha(JDateChooser ControlCalendario) {
        //Declarar Variables de Tipo: String 
        String CDia, CMes, Fecha;

        //Declarar Variables de Tipo: int       
        int dia, mes, año;

        //Obtener Número de Día        
        dia = ControlCalendario.getCalendar().get(Calendar.DAY_OF_MONTH);

        //Obtener Número de Mes 
        mes = ControlCalendario.getCalendar().get(Calendar.MONTH) + 1;

        //Obtener Número de Año 
        año = ControlCalendario.getCalendar().get(Calendar.YEAR);

        //Evaluar si el Día esta entre 1 y 9        
        if (dia >= 1 && dia < 10) {
            //Colocar Día de Dos Dígitos, convertido a String 
            CDia = "0" + String.valueOf(dia);
        } else {
            //Obtener Número de Día convertido a String 
            CDia = String.valueOf(dia);
        }

        //Evaluar si el Mes esta entre 1 y 9         
        if (mes >= 1 && mes < 10) {
            //Colocar Mes de Dos Dígitos, convertido a String 
            CMes = "0" + String.valueOf(mes);
        } else {
            //Obtener Número de Mes convertido a String 
            CMes = String.valueOf(mes);
        }

        //Establecer Fecha 
        Fecha = CDia + "/" + CMes + "/" + String.valueOf(año);
        //Retornar Valor         
        return Fecha;
    }

    //Crear el Método Local: Mensaje_Validacion()     
    void Mensaje_Validacion(String Mensaje) {
        //Mostrar Mensaje de Advertencia 
        JOptionPane.showMessageDialog(null, "Mensaje", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    //Crear la Función: ExaminarArchivo     
    public String ExaminarArchivo() {
        //Declarar Variable de Tipo: String 
        String Ruta;

        //Filtrar Extensión de Nombre de Archivo 
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Imagen JPG", "jpg", "png");

        //Declarar Variable (Selector de Archivos): CuadroDialogo 
        JFileChooser CuadroDialogo = new JFileChooser();
        CuadroDialogo.setDialogTitle("Seleccionar Imagen de Paciente");
        //Establecer Filtro de Archivo 
        CuadroDialogo.setFileFilter(filtro);

        //Mostrar Cuadro de Dialogo Abrierto         
        int Valor = CuadroDialogo.showOpenDialog(Formulario);

        //Evaluar si el Valor del Selector de Archivos = Opción Aprobada         
        if (Valor == JFileChooser.APPROVE_OPTION) {
            //Obtener Ruta del Archivo Seleccionado 
            Ruta = CuadroDialogo.getSelectedFile().getPath();
            System.out.println(Ruta);
        } else {
            //Ruta es vacio 
            Ruta = "";
        }

        //Retornar Ruta        
        return Ruta;
    }

    //Crear Método: Mostrar_Imagen     
    void Mostrar_Imagen(String Imagen) {
        //Mostrar Imagen 
        ImageIcon Foto = new ImageIcon("src/Fotos_Cliente/" + Imagen);

        //Establecer Icono 
        Icon Icono = new ImageIcon(Foto.getImage().getScaledInstance(this.Lb_Foto.getWidth(), this.Lb_Foto.getHeight(), Image.SCALE_DEFAULT));

        //Asignar Imagen a Control: Lb_Foto        
        this.Lb_Foto.setIcon(Icono);
        //Representar Imagen         
        this.repaint();
    }

    void AutoAjustar_Columnas() {
        //Establecer ancho de columnas
        //definir el tamaño de cada columna del control (jTable):jTable_RegistroNotas
        int[] Anchos = {60, 120, 120, 180, 60, 60, 60, 60, 60, 80, 160, 200,50,50,50,10};

        //Recorrer  el número de columnas del objeto(jTable):jTable_RegistroNotas
        for (int Columna = 0; Columna < this.jTable_DatosCliente.getColumnCount(); Columna++) {
            //Establecer el Ancho de Columna para cada columna del jTable:jTable_RegistroNotas

            this.jTable_DatosCliente.getColumnModel().getColumn(Columna).setPreferredWidth(Anchos[Columna]);
        }
        //Establecer el autorediseño de Tamaño del jTable en:AUTO_RESIZE_OFF
        this.jTable_DatosCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //Mostrar las barras de desplazamientos: Vertical y Horizontal
        this.jTable_DatosCliente.setShowVerticalLines(true);
        this.jTable_DatosCliente.setShowHorizontalLines(true);
    }

    //Crear el Método Local: Leer_Datos()     
    void Leer_Datos() {
        //Invocar al Método: Leer_Cientes  
        ObjCliente.Leer_Cientes(DirectorioCliente);

        //Declarar variable local: Fila        
        int Fila;

        //Crear un Controlador de Erro         
        try {
            //Crear Estructura Repetitiva (Bucle): while (Leer líneas del archivo)            
            for (Fila = 0; Fila < ObjCliente.MatrizClientes.length; Fila++) {
                //Agregar Filas al Modelo de Datos del JTable. 
                Modelo.addRow(new Object[]{ObjCliente.MatrizClientes[Fila][0],
                    ObjCliente.MatrizClientes[Fila][1],
                    ObjCliente.MatrizClientes[Fila][2],
                    ObjCliente.MatrizClientes[Fila][3],
                    ObjCliente.MatrizClientes[Fila][4],
                    ObjCliente.MatrizClientes[Fila][5],
                    ObjCliente.MatrizClientes[Fila][6],
                    ObjCliente.MatrizClientes[Fila][7],
                    ObjCliente.MatrizClientes[Fila][8],
                    ObjCliente.MatrizClientes[Fila][9],
                    ObjCliente.MatrizClientes[Fila][10],
                    ObjCliente.MatrizClientes[Fila][11],
                    ObjCliente.MatrizClientes[Fila][12],
                    ObjCliente.MatrizClientes[Fila][13],
                    ObjCliente.MatrizClientes[Fila][14],
                    ObjCliente.MatrizClientes[Fila][15]});
            }
            //Capturar el Código del Cliente 
            CodCliente = ObjCliente.MatrizClientes[Fila - 1][0];
        } catch (Exception Error) {
            //Asignar Valor 
            CodCliente = "C000";
        }
    }

    //Crear el Método Local: Guardar_Fichero()     
    void Guardar_Fichero() {
        //Crear Controlador de Errores         
        try {
            //Establecer Ruta del Archivo de Texto a Escribir Secuencia de Datos 
            FileWriter Guardar = new FileWriter(DirectorioCliente);

            //Recorrer Filas del Modelo de Datos            
            for (int i = 0; i < this.jTable_DatosCliente.getRowCount(); i++) {
                //Escribir Secuencia de Datos 
                Guardar.write(Modelo.getValueAt(i, 0).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 1).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 2).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 3).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 4).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 5).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 6).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 7).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 8).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 9).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 10).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 11).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 12).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 13).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 14).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 15).toString() + "\n");
            }

            //Cerrar el Archivo de Texto 
            Guardar.close();
        } catch (Exception Error) {
            //Mostrar Mensaje de Error 
            JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Crear el Método Local: Total_Filas()     

    void Total_Filas() {
        //Obtener Número Total de Filas        
        int NumFilas = this.jTable_DatosCliente.getRowCount();

        //Mostrar el Total de Filas         
        this.Lb_TotalFilas.setText("" + NumFilas);
    }
    //Crear el Método Local: Cargar_Fila()    

    void Cargar_Fila() {
        //Declarar una variable de tipo: String 
        String Sexo, EstadoCliente, Foto;

        //Declarar una variable de tipo: Entero y Obtener Fila Seleccionada del Control: JTable    
        int Seleccion = this.jTable_DatosCliente.getSelectedRow();
        try {
            //Cargar Datos en Controles 
            this.Lb_Codigo.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 0).toString());
            this.TXTApellidos.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 1).toString());
            this.TXTNombres.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 2).toString());
            this.JDateFec_Nac.setDate(FormatoFecha.parse(this.jTable_DatosCliente.getValueAt(Seleccion, 3).toString()));
            Sexo = this.jTable_DatosCliente.getValueAt(Seleccion, 4).toString();

            //Evaluar si el Estado del Cliente es: MASCULINO             
            if (Sexo.equals("MASCULINO")) {
                //Establecer Selección del Control: RBTN_Masculino          
                this.RBTN_Masculino.setSelected(true);
            } else {
                //Establecer Selección del Control: RBTN_Femenino                
                this.RBTN_Femenino.setSelected(true);
            }

            this.CBO_Nacionalidad.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 5).toString());
            this.CBO_Tipo_Doc_Identidad.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 6).toString());
            this.TXTNumDoc_Identidad.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 7).toString());
            this.CBO_Departamento.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 8).toString());
            this.CBO_Provincia.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 9).toString());
            this.CBO_Distrito.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 10).toString());
            this.TXTDireccion.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 11).toString());
            this.TXTNumTelefono.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 12).toString());
            this.TXTEmail.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 13).toString());

            //Obtener Nombre de Imagen del Cliente 
            Foto = this.jTable_DatosCliente.getValueAt(Seleccion, 14).toString();
            EstadoCliente = this.jTable_DatosCliente.getValueAt(Seleccion, 15).toString();

            //Evaluar si el Estado del Cliente es: REGISTRADO             
            if (EstadoCliente.equals("REGISTRADO")) {
                //Establecer Selección del Control: RBTN_Registrado           
                this.RBTN_Registrado.setSelected(true);
            } else {
                //Establecer Selección del Control: RBTN_Anulado               
                this.RBTN_Anulado.setSelected(true);
            }

            if (!Foto.equals("") || Foto != null) {
                //Mostrar Imagen              
                this.Mostrar_Imagen(Foto);
            }

            //Establecer Valor a la variable: Filas, según el Valor de la Selección    
            Filas = Seleccion;
        } catch (Exception error) {
            //Imprimir Error 
            System.out.println(error);
        }
    }

    //Crear el Método: Agregar_Registro()     
    public void Agregar_Registro() {
        //Crear un vector de Datos(String) 
        String[] InfoClientes = new String[16];
        //Asignar valores al Vector de Datos (Array = Arreglo de Datos) 
        InfoClientes[0] = this.Lb_Codigo.getText();
        InfoClientes[1] = this.TXTApellidos.getText();
        InfoClientes[2] = this.TXTNombres.getText();
        InfoClientes[3] = Fecha(JDateFec_Nac);

        //Evaluar si el control: RBTN_Masculino, está Seleccionado       
        if (this.RBTN_Masculino.isSelected() == true) {
            InfoClientes[4] = "MASCULINO";
        } else {
            InfoClientes[4] = "FEMENINO";
        }

        InfoClientes[5] = this.CBO_Nacionalidad.getSelectedItem().toString();
        InfoClientes[6] = this.CBO_Tipo_Doc_Identidad.getSelectedItem().toString();
        InfoClientes[7] = this.TXTNumDoc_Identidad.getText();
        InfoClientes[8] = this.CBO_Departamento.getSelectedItem().toString();
        InfoClientes[9] = this.CBO_Provincia.getSelectedItem().toString();
        InfoClientes[10] = this.CBO_Distrito.getSelectedItem().toString();
        InfoClientes[11] = this.TXTDireccion.getText();
        InfoClientes[12] = this.TXTNumTelefono.getText();
        InfoClientes[13] = this.TXTEmail.getText();

        if (this.Lb_Foto.getIcon() == null) {
            InfoClientes[14] = "null";
        } else {
            InfoClientes[14] = this.Lb_Codigo.getText() + ".jpg";
        }

        //Evaluar si el control: RBTN_Registrado, está Seleccionado        
        if (this.RBTN_Registrado.isSelected() == true) {
            InfoClientes[15] = "REGISTRADO";
        } else {
            InfoClientes[15] = "ANULADO";
        }

        //Agregar Datos al Modelo de Datos de la Tabla 
        Modelo.addRow(InfoClientes);

        //Mostrar Mensaje de Confirmación 
        JOptionPane.showMessageDialog(null, "Registro Guardado con Éxito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);

        //Capturar el Código del Cliente 
        CodCliente = InfoClientes[0];

        //Ubicar Cursor en el Control: TXTApellidos 
        this.TXTApellidos.requestFocus();
    }

    //Crear el Método: Modificar_Registro    
    void Modificar_Registro() {
        //Mostrar Mensaje de Confirmación 
        int Rpta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que Desea Modificar los Datos del Cliente?", "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        //Evaluar si la Respuesta fue Afirmativa      
        if (Rpta == JOptionPane.YES_OPTION) {
            //Obtener el N° de Fila Seleccionada          

            int Fila = this.jTable_DatosCliente.getSelectedRow();

            //Establecer Valores en el Control: 
            this.jTable_DatosCliente.setValueAt(this.Lb_Codigo.getText(), Fila, 0);
            this.jTable_DatosCliente.setValueAt(this.TXTApellidos.getText(), Fila, 1);
            this.jTable_DatosCliente.setValueAt(this.TXTNombres.getText(), Fila, 2);
            this.jTable_DatosCliente.setValueAt(Fecha(JDateFec_Nac), Fila, 3);

            //Evaluar si el Control: RBTN_Masculino, está Seleccionado            
            if (this.RBTN_Masculino.isSelected() == true) {
                //Establecer Valor 
                this.jTable_DatosCliente.setValueAt("MASCULINO", Fila, 4);
            } else {
                //Establecer Valor               
                this.jTable_DatosCliente.setValueAt("FEMENINO", Fila, 4);
            }

            this.jTable_DatosCliente.setValueAt(this.CBO_Nacionalidad.getSelectedItem().toString(), Fila, 5);
            this.jTable_DatosCliente.setValueAt(this.CBO_Tipo_Doc_Identidad.getSelectedItem().toString(), Fila, 6);
            this.jTable_DatosCliente.setValueAt(this.TXTNumDoc_Identidad.getText(), Fila, 7);
            this.jTable_DatosCliente.setValueAt(this.CBO_Departamento.getSelectedItem().toString(), Fila, 8);
            this.jTable_DatosCliente.setValueAt(this.CBO_Provincia.getSelectedItem().toString(), Fila, 9);
            this.jTable_DatosCliente.setValueAt(this.CBO_Distrito.getSelectedItem().toString(), Fila, 10);
            this.jTable_DatosCliente.setValueAt(this.TXTDireccion.getText(), Fila, 11);
            this.jTable_DatosCliente.setValueAt(this.TXTNumTelefono.getText(), Fila, 12);
            this.jTable_DatosCliente.setValueAt(this.TXTEmail.getText(), Fila, 13);
            this.jTable_DatosCliente.setValueAt(this.Lb_Codigo.getText() + ".jpg", Fila, 14);

            //Evaluar si el Control: RBTN_Registrado, está Seleccionado            
            if (this.RBTN_Registrado.isSelected() == true) {
                //Establecer Valor              
                this.jTable_DatosCliente.setValueAt("REGISTRADO", Fila, 15);
            } else {
                //Establecer Valor               
                this.jTable_DatosCliente.setValueAt("ANULADO", Fila, 15);
            }
        }
    }

    //Crear el Método: Validar_DocIdentidad()  
    void Validar_DocIdentidad() {
        int Contador = 0;
        String NumDocIdentidad = "";

        //Evaluar si el Texto del Control: BTN_Guardar, es Igual a GUARDAR      
        if (this.BTN_Guardar.getText().toUpperCase().equals("GUARDAR")) {
            if (this.jTable_DatosCliente.getRowCount() == 0) {
                Contador = 0;
            } else {
                //Recorrer Elementos (Filas) del Control: jTable_RegistroClientes               
                for (int i = 0; i < this.jTable_DatosCliente.getRowCount(); i++) {
                    //Capturar valor del Doc. de Identidad 
                    NumDocIdentidad = this.jTable_DatosCliente.getValueAt(i, 7).toString(); // 7 Es el N° de Doc. Identidad 

                    //Evaluar si el N° de Documento de Identidad Existe                    
                    if (this.TXTNumDoc_Identidad.getText().equals(NumDocIdentidad)) {
                        //Mostrar Mensaje de Error 
                        JOptionPane.showMessageDialog(null, "El N° de Documento de Indentidad ya se encuentra Registrado", "Error", JOptionPane.ERROR_MESSAGE);

                        //Ubicar Cursor en el Control: TXTNumDoc_Identidad            
                        this.TXTNumDoc_Identidad.requestFocus();

                        //Incrementar el Valor de Contador 
                        Contador++;
                        //Fin del Proceso                        
                        break;
                    }
                }
            }
        }
    }

    //Crear el Método Local: Salir()   
    void Salir() {
        //Declarar variable de tipo: Entero     
        int Rpta;

        //Mostrar Mensaje de Confirmación 
        Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Salir de la ventana?",
                "Registro de Clientes",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        //Evaluar Si se ha pulsado el Botón: Sí       
        if (Rpta == 0) {
            //Establecer Conjunto de Filas a Filtar en el Control: JTable         
            this.jTable_DatosCliente.setRowSorter(null);

            //Invocar al Método: Guardar_Fichero           
            this.Guardar_Fichero();

            //Cerrar Formulario Actual        
            // this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonOp = new javax.swing.ButtonGroup();
        ButtonCli = new javax.swing.ButtonGroup();
        BTN_Desactivado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TXTApellidos = new javax.swing.JTextField();
        TXTNombres = new javax.swing.JTextField();
        TXTEmail = new javax.swing.JTextField();
        TXTNumTelefono = new javax.swing.JTextField();
        RBTN_Registrado = new javax.swing.JRadioButton();
        RBTN_Anulado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        CBO_Tipo_Doc_Identidad = new javax.swing.JComboBox();
        Lb_Codigo = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TXTNumDoc_Identidad = new javax.swing.JTextField();
        JPanel_Sexo = new javax.swing.JPanel();
        RBTN_Masculino = new javax.swing.JRadioButton();
        RBTN_Femenino = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        CBO_Provincia = new javax.swing.JComboBox();
        CBO_Departamento = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TXTDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CBO_Nacionalidad = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        CBO_Distrito = new javax.swing.JComboBox();
        JDateFec_Nac = new com.toedter.calendar.JDateChooser();
        Lb_TotalFilas = new javax.swing.JLabel();
        Lb_Foto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DatosCliente = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JToggleButton();
        BTN_Editar = new javax.swing.JToggleButton();
        BTN_Guardar = new javax.swing.JToggleButton();
        BTN_Eliminar = new javax.swing.JToggleButton();
        BTN_Salir = new javax.swing.JToggleButton();

        BTN_Desactivado.setBackground(new java.awt.Color(0, 102, 153));
        BTN_Desactivado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        BTN_Desactivado.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombres:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Apellidos:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha Nacimiento:");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Doc. de Identidad:");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Email:");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Teléfono:");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Estado del Cliente:");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TXTApellidos.setText("jTextField1");
        TXTApellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTApellidosFocusLost(evt);
            }
        });
        TXTApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTApellidosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTApellidosKeyTyped(evt);
            }
        });

        TXTNombres.setText("jTextField1");
        TXTNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTNombresFocusLost(evt);
            }
        });
        TXTNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTNombresKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTNombresKeyTyped(evt);
            }
        });

        TXTEmail.setText("jTextField1");
        TXTEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTEmailFocusLost(evt);
            }
        });
        TXTEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTEmailKeyPressed(evt);
            }
        });

        TXTNumTelefono.setText("jTextField1");
        TXTNumTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTNumTelefonoFocusLost(evt);
            }
        });
        TXTNumTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTNumTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTNumTelefonoKeyTyped(evt);
            }
        });

        ButtonCli.add(RBTN_Registrado);
        RBTN_Registrado.setText("Registrado");

        ButtonCli.add(RBTN_Anulado);
        RBTN_Anulado.setText("Anulado");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tipo Documento:");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Tipo_Doc_Identidad.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Tipo_Doc_Identidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_Tipo_Doc_IdentidadItemStateChanged(evt);
            }
        });
        CBO_Tipo_Doc_Identidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBO_Tipo_Doc_IdentidadKeyPressed(evt);
            }
        });

        Lb_Codigo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Lb_Codigo.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Codigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Codigo.setText("Codigo");
        Lb_Codigo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Codigo:");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TXTNumDoc_Identidad.setText("jTextField1");
        TXTNumDoc_Identidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTNumDoc_IdentidadFocusLost(evt);
            }
        });
        TXTNumDoc_Identidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTNumDoc_IdentidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTNumDoc_IdentidadKeyTyped(evt);
            }
        });

        JPanel_Sexo.setBackground(new java.awt.Color(0, 102, 153));
        JPanel_Sexo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        buttonOp.add(RBTN_Masculino);
        RBTN_Masculino.setText("M");

        buttonOp.add(RBTN_Femenino);
        RBTN_Femenino.setText("F");

        javax.swing.GroupLayout JPanel_SexoLayout = new javax.swing.GroupLayout(JPanel_Sexo);
        JPanel_Sexo.setLayout(JPanel_SexoLayout);
        JPanel_SexoLayout.setHorizontalGroup(
            JPanel_SexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SexoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanel_SexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RBTN_Femenino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RBTN_Masculino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        JPanel_SexoLayout.setVerticalGroup(
            JPanel_SexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SexoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RBTN_Masculino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RBTN_Femenino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Provincia:");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Provincia.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Provincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_ProvinciaItemStateChanged(evt);
            }
        });
        CBO_Provincia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBO_ProvinciaKeyPressed(evt);
            }
        });

        CBO_Departamento.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Departamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_DepartamentoItemStateChanged(evt);
            }
        });
        CBO_Departamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBO_DepartamentoKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Departamento:");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_Provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBO_Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addComponent(CBO_Provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Dirección:");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TXTDireccion.setText("jTextField1");
        TXTDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTDireccionFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Nacionalidad:");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Nacionalidad.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Nacionalidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_NacionalidadItemStateChanged(evt);
            }
        });
        CBO_Nacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBO_NacionalidadKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Distrito");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Distrito.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Distrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBO_DistritoKeyPressed(evt);
            }
        });

        Lb_TotalFilas.setText("jLabel4");

        Lb_Foto.setText("jLabel4");

        javax.swing.GroupLayout BTN_DesactivadoLayout = new javax.swing.GroupLayout(BTN_Desactivado);
        BTN_Desactivado.setLayout(BTN_DesactivadoLayout);
        BTN_DesactivadoLayout.setHorizontalGroup(
            BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXTApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXTNombres)))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addComponent(JPanel_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                        .addComponent(CBO_Tipo_Doc_Identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TXTNumDoc_Identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addComponent(RBTN_Registrado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RBTN_Anulado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Lb_Foto)
                                .addGap(25, 25, 25))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BTN_DesactivadoLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel12)
                                        .addGap(87, 87, 87))
                                    .addComponent(TXTNumTelefono)
                                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(JDateFec_Nac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BTN_DesactivadoLayout.createSequentialGroup()
                                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(119, 119, 119))
                                    .addComponent(CBO_Distrito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBO_Nacionalidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(Lb_TotalFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(294, 294, 294))))
        );
        BTN_DesactivadoLayout.setVerticalGroup(
            BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                            .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(54, 54, 54))
                        .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CBO_Nacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JDateFec_Nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TXTNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TXTApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(24, 24, 24)))
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(Lb_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addComponent(JPanel_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RBTN_Registrado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RBTN_Anulado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBO_Tipo_Doc_Identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTNumDoc_Identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTNumTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBO_Distrito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_TotalFilas)
                    .addComponent(Lb_Foto))
                .addGap(28, 28, 28))
        );

        jTable_DatosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_DatosCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DatosClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_DatosCliente);

        BTN_Nuevo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nuevo.png"))); // NOI18N
        BTN_Nuevo.setToolTipText("Nuevo Registro");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });

        BTN_Editar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        BTN_Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nota.png"))); // NOI18N
        BTN_Editar.setToolTipText("Editar Registro");
        BTN_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EditarActionPerformed(evt);
            }
        });

        BTN_Guardar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Guardar.png"))); // NOI18N
        BTN_Guardar.setToolTipText("Guardar Registro");
        BTN_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GuardarActionPerformed(evt);
            }
        });

        BTN_Eliminar.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        BTN_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/X.png"))); // NOI18N
        BTN_Eliminar.setToolTipText("Eliminar Registro");
        BTN_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EliminarActionPerformed(evt);
            }
        });

        BTN_Salir.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        BTN_Salir.setText("Salir");
        BTN_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_Desactivado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(BTN_Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTN_Nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                            .addComponent(BTN_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Eliminar)
                            .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(BTN_Desactivado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_Nuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_Guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_Editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_Eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(BTN_Salir)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        //Invocar al Método: Salir        
        this.Salir();
    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

        //Evaluar si el Código del Cliente ha sido generado        
        if (this.Lb_Codigo.getText().length() == 0) {
            //Invocar al Método: Mensaje_Validacion           
            this.Mensaje_Validacion("Debe Generar el Código del Cliente");
        } //Evaluar si el Usuario a Ingresado los Apellidos del Cliente        
        else if (this.TXTApellidos.getText().length() == 0) {
            //Invocar al Método: Mensaje_Validacion           
            this.Mensaje_Validacion("Debe Ingresar los Apellidos del Cliente");

            //Ubicar el Cursor en el Control: TXTApellidos         
            this.TXTApellidos.requestFocus();
        } //Evaluar si el Usuario a Ingresado los Nombres del Cliente        
        else if (this.TXTNombres.getText().length() == 0) {
            //Invocar al Método: Mensaje_Validacion           
            this.Mensaje_Validacion("Debe Ingresar los Nombres del Cliente");

            //Ubicar el Cursor en el Control: TXTNombres      
            this.TXTNombres.requestFocus();
        } //Evaluar si se ha Ingresado la Fecha de Nacimiento      
        else if (this.JDateFec_Nac.getDate() == null) {
            //Mostrar Mensaje 
            JOptionPane.showMessageDialog(null, "Seleccione Fecha de Nacimiento del Paciente", "Faltan 	Datos!", JOptionPane.INFORMATION_MESSAGE);

            //Ubicar Cursor en el Control: JDateFec_Nac           
            this.JDateFec_Nac.getDateEditor().getUiComponent().requestFocusInWindow();
        } //Evaluar si se ha Seleccionado el Sexo del Cliente 
        else if (this.RBTN_Masculino.isSelected() == false && this.RBTN_Femenino.isSelected() == false) {
            //Invocar al Método: Mensaje_Validacion          
            this.Mensaje_Validacion("Debe Seleccionar el Sexo del Cliente");

            //Establecer el Sexo del Cliente: MASCULINO            
            this.RBTN_Masculino.setSelected(true);
        } //Evaluar si el Usuario a Seleccionado un Elemento del ComboBox     
        else if (this.CBO_Nacionalidad.getSelectedIndex() == 0) {
            //Invocar al Método: Mensaje_Validacion            
            this.Mensaje_Validacion("Debe Seleccionar la Nacionalidad del Cliente");

            //Ubicar Cursor en el Control: CBO_Nacionalidad            
            this.CBO_Nacionalidad.requestFocus();
        } //Evaluar si el Usuario a Seleccionado un Elemento del ComboBox        
        else if (this.CBO_Tipo_Doc_Identidad.getSelectedIndex() == 0) {
            //Invocar al Método: Mensaje_Validacion           
            this.Mensaje_Validacion("Debe Seleccionar el Tipo de Documento de Identidad del Cliente");

            //Ubicar Cursor en el Control: CBO_Tipo_Doc_Identidad        
            this.CBO_Tipo_Doc_Identidad.requestFocus();
        } //Evaluar si el Usuario a Ingresado el Número de Documento de Identidad del Cliente 
        else if (this.TXTNumDoc_Identidad.getText().length() == 0) {
            //Invocar al Método: Mensaje_Validacion            
            this.Mensaje_Validacion("Debe Ingresar el Número de Documento de Identidad del Cliente");

            //Ubicar el Cursor en el Control: TXTNumDoc_Identidad      
            this.TXTNumDoc_Identidad.requestFocus();
        } //Evaluar si el Usuario a Seleccionado un Elemento del ComboBox     
        else if (this.CBO_Departamento.getSelectedIndex() == 0) {
            //Invocar al Método: Mensaje_Validacion 
            this.Mensaje_Validacion("Debe Seleccionar el Departamento donde vive el Cliente");

            //Ubicar Cursor en el Control: CBO_Departamento         
            this.CBO_Departamento.requestFocus();
        } //Evaluar si el Usuario a Seleccionado un Elemento del ComboBox       
        else if (this.CBO_Provincia.getSelectedIndex() == 0) {
            //Invocar al Método: Mensaje_Validacion 
            this.Mensaje_Validacion("Debe Seleccionar la Provincia donde vive el Cliente");

            //Ubicar Cursor en el Control: CBO_Provincia         
            this.CBO_Provincia.requestFocus();
        } //Evaluar si el Usuario a Seleccionado un Elemento del ComboBox        
        else if (this.CBO_Distrito.getSelectedIndex() == 0) {
            //Invocar al Método: Mensaje_Validacion            
            this.Mensaje_Validacion("Debe Seleccionar el Distrito donde vive el Cliente");

            //Ubicar Cursor en el Control: CBO_Distrito         
            this.CBO_Distrito.requestFocus();
        }
        //Evaluar si el Usuario a Ingresado la Dirección del Cliente else 
        if (this.TXTDireccion.getText().length() == 0) {
            //Invocar al Método: Mensaje_Validacion      
            this.Mensaje_Validacion("Debe Ingresar la Dirección del Cliente");

            //Ubicar el Cursor en el Control: TXTDireccion             
            this.TXTDireccion.requestFocus();
        } //Evaluar si el Usuario a Ingresado el Número de Telefono del Cliente         
        else if (this.TXTNumTelefono.getText().length() == 0) {
            //Invocar al Método: Mensaje_Validacion            
            this.Mensaje_Validacion("Debe Ingresar el N° de Teléfono del Cliente");

            //Ubicar el Cursor en el Control: TXTNumTelefono       
            this.TXTNumTelefono.requestFocus();
        } //Evaluar si el Usuario a Ingresado el Email del Cliente        
        else if (this.TXTEmail.getText().contains("@") == false) {
            //Invocar al Método: Mensaje_Validacion           
            this.Mensaje_Validacion("Debe Ingresar el Email del Cliente");

            //Ubicar el Cursor en el Control: TXTEmail            
            this.TXTEmail.requestFocus();
        } //Evaluar si se ha Seleccionado el Estado del Cliente 
        else if (this.RBTN_Registrado.isSelected() == false && this.RBTN_Anulado.isSelected() == false) {
            //Invocar al Método: Mensaje_Validacion          
            this.Mensaje_Validacion("Debe Seleccionar el Estado del Cliente");

            //Establecer el Estado de Registrado            
            this.RBTN_Registrado.setSelected(true);
        } else {
            //Declarar una variable de tipo: Entero          
            int Rpta;
            //Mostrar Mensaje de Confirmación 
            Rpta = JOptionPane.showConfirmDialog(null, "¿Desea " + this.BTN_Guardar.getText() + " los Datos del Cliente?",
                    "Confirmación...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //Evaluar si el Usuario Confirmó respuesta: Sí (0)           
            if (Rpta == 0) {
                //Invocar Método: Validar_DocIdentidad()      
                this.Validar_DocIdentidad();
            } else {
                //Invocar al Método: Limpiar()              
                this.Limpiar();
            }

            //Invocar al Evento: Botones        
            this.Botones(true);
        }


    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void jTable_DatosClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DatosClienteMouseClicked
        //Invocar al Método: Cargar_Fila()        
        this.Cargar_Fila();
    }//GEN-LAST:event_jTable_DatosClienteMouseClicked

    private void BTN_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EditarActionPerformed

        //Obtener el N° de Fila Seleccionada 
        int Fila = this.jTable_DatosCliente.getSelectedRow();

        //Evaluar si Fila es Menor a Cero 
        if (Fila < 0) {
            //Mostrar Mensaje de Advertencia 
            JOptionPane.showMessageDialog(null, "Debe Seleccionar el Registro (Fila) que desea editar los Datos", "Mensaje", JOptionPane.WARNING_MESSAGE);
        } else {
            //Mostrar Mensaje de Confirmación          
            int Rpta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea editar los datos del Cliente?",
                    "Confirmación...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //Evaluar Si respuesta fue Afirmativa            
            if (Rpta == JOptionPane.YES_OPTION) {
                //Habilitar Controles              
                this.Habilitar_Controles(true);

                //Establecer Texto: Actualizar, al Control: BTN_Guardar      
                this.BTN_Guardar.setText("Actualizar");

                //Deshabilitar Botones               
                this.Botones(false);
            }
        }
    }//GEN-LAST:event_BTN_EditarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        //Invocar al Método: Limpiar()
        this.Limpiar();

        //Declarar una variable de tipo: String 
        String CodigoCliente = "";

        //Declarar una variable de tipo: Entero    
        int Codigo = Integer.parseInt(CodCliente.substring(1, 4)) + 1;

        if (Codigo > 0 && Codigo < 10) {
            //Generar Código del Cliente 
            CodigoCliente = "C00" + String.valueOf(Codigo);
        } else if (Codigo >= 10 && Codigo <= 99) {
            //Generar Código del Cliente 
            CodigoCliente = "C0" + String.valueOf(Codigo);
        } else if (Codigo >= 100 && Codigo <= 999) {
            //Generar Código del Cliente 
            CodigoCliente = "C" + String.valueOf(Codigo);
        }

        //Mostrar Código del Cliente        
        this.Lb_Codigo.setText(CodigoCliente);

        //Invocar a Métodos:        
        this.Botones(false);
        this.Habilitar_Controles(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        //Capturar la Fila Seleccionada 
        int FilaSeleccionada = this.jTable_DatosCliente.getSelectedRow();

        //Evaluar si el valor de la variable FilaSeleccionada es diferente o distinto de -1    
        if (FilaSeleccionada != -1) {
            //Declarar una variable de tipo: Entero 
            int Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el Registro de Código: "
                    + this.jTable_DatosCliente.getValueAt(FilaSeleccionada, 0) + "?", "", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            //Evaluar si Respuesta fue afirmativa (0)      
            if (Rpta == 0) {
                //Capturar el Indice del Modelo de la Tabla 
                int IndiceModelo = this.jTable_DatosCliente.convertColumnIndexToModel(FilaSeleccionada);

                //Eliminar Fila del Modelo 
                Modelo.removeRow(IndiceModelo);

                //Invocar Método: Total_Filas()   
                this.Total_Filas();

                //Invocar al Método: Guardar_Fichero()            
                this.Guardar_Fichero();
            }
        } else {
            //Mostrar Mensaje de Error 
            JOptionPane.showMessageDialog(null, "Debe Seleccionar la Fila a Eliminar", "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void CBO_NacionalidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_NacionalidadKeyPressed
        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación) 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: JComboBox     
            this.CBO_Tipo_Doc_Identidad.requestFocus();
        }
    }//GEN-LAST:event_CBO_NacionalidadKeyPressed

    private void CBO_NacionalidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_NacionalidadItemStateChanged
        //Evaluar si se ha Seleccionado un Elemento de la Lista      
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //Invocar al Método: Cargar_Doc_Identidad           
            this.Cargar_Doc_Identidad(this.CBO_Nacionalidad.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CBO_NacionalidadItemStateChanged

    private void TXTEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTEmailKeyPressed
        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación) 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: RBTN_Registrado        
            this.RBTN_Registrado.requestFocus();
        }

    }//GEN-LAST:event_TXTEmailKeyPressed

    private void TXTEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTEmailFocusLost

        //Invocar Métodos:        
        this.Convertir_TextoMinusculas(TXTEmail);
        this.Validar_Email();
    }//GEN-LAST:event_TXTEmailFocusLost

    private void TXTNumTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNumTelefonoKeyTyped
        //Invocar al Método: Solo_Numeros       
        this.Solo_Numeros(evt, TXTNumTelefono);
    }//GEN-LAST:event_TXTNumTelefonoKeyTyped

    private void TXTNumTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNumTelefonoKeyPressed
        //Inocar a la Funcion: MaxLength         
        this.TXTNumTelefono.setText(MaxLength("TELÉFONO", this.TXTNumTelefono.getText()));

        //Invocar al Método: Cambiar_Cursor      
        this.Cambiar_Cursor(evt, this.TXTEmail);
    }//GEN-LAST:event_TXTNumTelefonoKeyPressed

    private void TXTNumTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTNumTelefonoFocusLost
        //Invocar al Método: Validar_Longitud_Datos    
        this.Validar_Longitud_Datos(this.TXTNumTelefono, "TELÉFONO", this.TXTNumTelefono.getText());
    }//GEN-LAST:event_TXTNumTelefonoFocusLost

    private void TXTDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTDireccionFocusLost
        //Invocar al Método: Convertir_TextoMayusculas        
        this.Convertir_TextoMayusculas(this.TXTDireccion);
    }//GEN-LAST:event_TXTDireccionFocusLost

    private void CBO_DistritoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_DistritoKeyPressed
        //Invocar al Método: Cambiar_Cursor()   
        this.Cambiar_Cursor(evt, this.TXTDireccion);
    }//GEN-LAST:event_CBO_DistritoKeyPressed

    private void CBO_ProvinciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_ProvinciaKeyPressed
        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación) 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: JComboBox           
            this.CBO_Distrito.requestFocus();
        }

    }//GEN-LAST:event_CBO_ProvinciaKeyPressed

    private void CBO_ProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_ProvinciaItemStateChanged
        //Evaluar si se ha Seleccionado un Elemento de la Lista 
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //Invocar al Método: Cargar_Provincia          
            this.Cargar_Distrito(this.CBO_Provincia.getSelectedItem().toString());
        }

    }//GEN-LAST:event_CBO_ProvinciaItemStateChanged

    private void CBO_DepartamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_DepartamentoKeyPressed
        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación) 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: JComboBox     
            this.CBO_Provincia.requestFocus();
        }

    }//GEN-LAST:event_CBO_DepartamentoKeyPressed

    private void CBO_DepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_DepartamentoItemStateChanged
        //Evaluar si se ha Seleccionado un Elemento de la Lista     
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //Invocar al Método: Cargar_Provincia    
            this.Cargar_Provincia(this.CBO_Departamento.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CBO_DepartamentoItemStateChanged

    private void TXTNumDoc_IdentidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNumDoc_IdentidadKeyTyped
        //Invocar al Método: Solo_Numeros 
        this.Solo_Numeros(evt, this.TXTNumDoc_Identidad);

    }//GEN-LAST:event_TXTNumDoc_IdentidadKeyTyped

    private void TXTNumDoc_IdentidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNumDoc_IdentidadKeyPressed
        //Invocar a la Función: MaxLength 

        this.TXTNumDoc_Identidad.setText(MaxLength(this.CBO_Tipo_Doc_Identidad.getSelectedItem().toString(),
                this.TXTNumDoc_Identidad.getText()));

        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación) 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: JComboBox         
            this.CBO_Departamento.requestFocus();
        }

    }//GEN-LAST:event_TXTNumDoc_IdentidadKeyPressed

    private void TXTNumDoc_IdentidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTNumDoc_IdentidadFocusLost
        //Invocar al Método: Validar_Longitud_Datos      
        this.Validar_Longitud_Datos(this.TXTNumDoc_Identidad,
                this.CBO_Tipo_Doc_Identidad.getSelectedItem().toString(),
                this.TXTNumDoc_Identidad.getText());
    }//GEN-LAST:event_TXTNumDoc_IdentidadFocusLost

    private void CBO_Tipo_Doc_IdentidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_Tipo_Doc_IdentidadKeyPressed
        //Invocar al Método: Cambiar_Cursor         
        this.Cambiar_Cursor(evt, this.TXTNumDoc_Identidad);
    }//GEN-LAST:event_CBO_Tipo_Doc_IdentidadKeyPressed

    private void CBO_Tipo_Doc_IdentidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_Tipo_Doc_IdentidadItemStateChanged
        //Limpiar Texto del Control: TXTNumDoc_Identidad      
        this.TXTNumDoc_Identidad.setText("");
    }//GEN-LAST:event_CBO_Tipo_Doc_IdentidadItemStateChanged

    private void TXTNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNombresKeyTyped
        //Invocar al Método: Solo_Letras     
        this.Solo_Letras(evt);
    }//GEN-LAST:event_TXTNombresKeyTyped

    private void TXTNombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNombresKeyPressed
        //Evaluar si se ha presionado la Tecla: Enter o la Tecla: Tab (Tabulación) 
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //Ubicar Cursor en el Control: JDC_Fec_Nac 
            this.JDateFec_Nac.transferFocus();
        }
    }//GEN-LAST:event_TXTNombresKeyPressed

    private void TXTNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTNombresFocusLost
        //Invocar al Método: Convertir_TextoMayusculas     
        this.Convertir_TextoMayusculas(this.TXTNombres);
    }//GEN-LAST:event_TXTNombresFocusLost

    private void TXTApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTApellidosKeyTyped
        //Invocar al Método: Solo_Letras       
        this.Solo_Letras(evt);
    }//GEN-LAST:event_TXTApellidosKeyTyped

    private void TXTApellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTApellidosKeyPressed
        //Invocar al Método: Cambiar_Cursor()    
        this.Cambiar_Cursor(evt, this.TXTNombres);
    }//GEN-LAST:event_TXTApellidosKeyPressed

    private void TXTApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTApellidosFocusLost
        //Invocar al Método: Convertir_TextoMayusculas       
        this.Convertir_TextoMayusculas(this.TXTApellidos);
    }//GEN-LAST:event_TXTApellidosFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BTN_Desactivado;
    private javax.swing.JToggleButton BTN_Editar;
    private javax.swing.JToggleButton BTN_Eliminar;
    private javax.swing.JToggleButton BTN_Guardar;
    private javax.swing.JToggleButton BTN_Nuevo;
    private javax.swing.JToggleButton BTN_Salir;
    private javax.swing.ButtonGroup ButtonCli;
    private javax.swing.JComboBox CBO_Departamento;
    private javax.swing.JComboBox CBO_Distrito;
    private javax.swing.JComboBox CBO_Nacionalidad;
    private javax.swing.JComboBox CBO_Provincia;
    private javax.swing.JComboBox CBO_Tipo_Doc_Identidad;
    private com.toedter.calendar.JDateChooser JDateFec_Nac;
    private javax.swing.JPanel JPanel_Sexo;
    private javax.swing.JLabel Lb_Codigo;
    private javax.swing.JLabel Lb_Foto;
    private javax.swing.JLabel Lb_TotalFilas;
    private javax.swing.JRadioButton RBTN_Anulado;
    private javax.swing.JRadioButton RBTN_Femenino;
    private javax.swing.JRadioButton RBTN_Masculino;
    private javax.swing.JRadioButton RBTN_Registrado;
    private javax.swing.JTextField TXTApellidos;
    private javax.swing.JTextField TXTDireccion;
    private javax.swing.JTextField TXTEmail;
    private javax.swing.JTextField TXTNombres;
    private javax.swing.JTextField TXTNumDoc_Identidad;
    private javax.swing.JTextField TXTNumTelefono;
    private javax.swing.ButtonGroup buttonOp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_DatosCliente;
    // End of variables declaration//GEN-END:variables
}
