package Panel;

import Datos.*;
import Entidades.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JFrm_Datos_Cliente extends javax.swing.JPanel {

    //Llenar el combobox **nacionalidad** segun la base de datos
    NacionalidadDB nacionalidad_dt = new NacionalidadDB();
    ArrayList<NacionalidadEntity> Listanacionalidad = new ArrayList<>();

    //Llenar el combobox **ubigeo provincia** segun la base de datos
    UbigeoDB ubigeo_dt = new UbigeoDB();
    ArrayList<UbigeoEntity> ListUbigeopro = new ArrayList<>();

    //Llenar el combobox **ubigeo distrito** segun la base de datos
    UbigeoDB ubigeodis_dt = new UbigeoDB();
    ArrayList<UbigeoEntity> ListUbigeodis = new ArrayList<>();

    //Llenar el combobox **ubigeo departamento** segun la base de datos
    UbigeoDB ubigeodep_dt = new UbigeoDB();
    ArrayList<UbigeoEntity> ListUbigeodep = new ArrayList<>();

    //Llenar el combobox **docIdentidad** segun la base de datos
    DocIdentidadDB docIdentidad_dt = new DocIdentidadDB();
    ArrayList<DocIdentidadEntity> ListaDocIDentidad = new ArrayList<>();

    //Declara variable tipo: DefaultTableMode(Modelo de tabla)
    DefaultTableModel Modelo;

    //Declarar Variable de tipo:Entero
    int Codigo = 1, Filas;

    String Directorio = new File("src/Archivo_Datos/Datos_Cliente.txt").getAbsolutePath();

    public JFrm_Datos_Cliente() {
        initComponents();

        //combobox docIdentidad
        Listanacionalidad = nacionalidad_dt.GetBuscarNacionalidadItems();

        for (NacionalidadEntity item : Listanacionalidad) {
            CBO_Nacionalidad.addItem(item.getGentilicio_Nac());
        }
        CBO_Nacionalidad.setSelectedIndex(-1);

        //combobox ubigeo
        ListUbigeopro = ubigeo_dt.getBuscarUbigeoItems();

        for (UbigeoEntity item : ListUbigeopro) {
            CBO_Pronvincia.addItem(item.getProvincia());
        }
        CBO_Pronvincia.setSelectedIndex(-1);

        //combobox distrito
        ListUbigeodis = ubigeodis_dt.getBuscarUbigeoItems();

        for (UbigeoEntity item : ListUbigeodis) {
            CBO_Distrito.addItem(item.getDistrito());
        }
        CBO_Distrito.setSelectedIndex(-1);

        //combobox departamento
        ListUbigeodep = ubigeodep_dt.getBuscarUbigeoItems();

        for (UbigeoEntity item : ListUbigeodep) {
            CBO_Departamento.addItem(item.getDepartamento());
        }
        CBO_Departamento.setSelectedIndex(-1);

        //combobox docIdentidad
        ListaDocIDentidad = docIdentidad_dt.getCodDoc_IdentidadItems();

        for (DocIdentidadEntity item : ListaDocIDentidad) {
            CBO_TipoDoc.addItem(item.getTipo_Doc_Identidad());
        }
        CBO_TipoDoc.setSelectedIndex(-1);

        ArrayList<NacionalidadEntity> Listanacionalidad = new ArrayList<>();

        Modelo = new DefaultTableModel();
        //Establecer nombre de las columnas para el control: jTable_RegistroNotas(a travez de la vaiable:Modelo)
        Modelo.addColumn("Cod. Cliente");
        Modelo.addColumn("Apellidos");
        Modelo.addColumn("Nombres");
        Modelo.addColumn("F/Nacimiento");
        Modelo.addColumn("Sexo");
        Modelo.addColumn("Nacionalidad");
        Modelo.addColumn("Doc. Identidad");
        Modelo.addColumn("Departamento");
        Modelo.addColumn("Provincia");
        Modelo.addColumn("Distrito");
        Modelo.addColumn("Dirección");
        Modelo.addColumn("Telefono");
        Modelo.addColumn("Email");
        Modelo.addColumn("Estado Cliente");

        //Establecer el modelo al control Jtable
        this.jTable_DatosCliente.setModel(Modelo);
//
//        String titulos[] = {"Cod. Cliente",
//            "Apellidos",
//            "Nombres",
//            "F/Nacimiento",
//            "Sexo",
//            "Nacionalidad",
//            "Doc. Identidad",
//            "N° Doc. Identidad",
//            "Departamento",
//            "Provincia",
//            "Distrito",
//            "Dirección",
//            "Telefono",
//            "Email",
//            "Estado Cliente"};
//        Modelo.setColumnIdentifiers(titulos);

        //Cargar datos
        Limpiar();

    }

    void Limpiar() {
        this.TXT_Apellidos.setText("");
        this.TXT_Nombres.setText("");
        this.TXT_Direccion.setText("");
        this.TXT_Telefono.setText("");
        this.TXT_Email.setText("");
        this.TXT_DocIdentidad.setText("");
    }

    void Solo_Letras(java.awt.event.KeyEvent evt) {
        // Evaluar que solo  se haya ingresado Letras y no números
        if (!Character.isLetter(evt.getKeyChar()) && !Character.isSpaceChar(evt.getKeyChar())) {
            //Deshabilito teclado
            evt.consume();
        }
    }

    //Crear metodo local: Solo_Numeros()
    void Solo_Numeros(java.awt.event.KeyEvent evt, javax.swing.JTextField CajaTexto) {
        //Evaluar que solo  se haya ingresado Números y 
        // o letras o si el total de caracteres >="

        if (!Character.isDigit(evt.getKeyChar()) || CajaTexto.getText().length() >= 12) {
            //Desabilitar teclado
            evt.consume();
        }

    }

    void Fecha(java.awt.event.KeyEvent evt) {
        if (!Character.isDigit(evt.getKeyChar()) || this.TXT_Telefono.getText().length() >= 6) {
            //Desabilitar teclado
            evt.consume();
        }

    }

    void AutoAjustar_Columnas() {
        //Establecer ancho de columnas
        //definir el tamaño de cada columna del control (jTable):jTable_RegistroNotas
        int[] Anchos = {60, 120, 120, 180, 60, 60, 60, 60, 60, 80, 160, 200};

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

    void Cargar_Fila() {
        //Declarar una variable de tipo entero y obtener fila seleccionada
        int Seleccion = this.jTable_DatosCliente.getSelectedRow();

        //Cargar datos en controles 
        this.Lb_CodCliente.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 0).toString());
        this.TXT_Apellidos.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 1).toString());
        this.TXT_Nombres.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 2).toString());
        //this.JC_Fecha.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion,3));
        this.BTN_Masculino.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 4).toString());
        this.BTN_Femenino.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 5).toString());
        this.CBO_Nacionalidad.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 6).toString());
        this.CBO_Departamento.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 7).toString());
        this.CBO_Pronvincia.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 8).toString());
        this.CBO_Distrito.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 9).toString());
        this.CBO_TipoDoc.setSelectedItem(this.jTable_DatosCliente.getValueAt(Seleccion, 10).toString());
        this.TXT_DocIdentidad.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 11).toString());
        this.TXT_Direccion.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 12).toString());
        this.TXT_Telefono.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 13).toString());
        this.TXT_Email.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 14).toString());
        this.BTN_Registrado.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 15).toString());
        this.BTN_Anulado.setText(this.jTable_DatosCliente.getValueAt(Seleccion, 14).toString());

        Filas = Seleccion;
    }

    //Crear método local: Guardar_Fichero();
    void Guardar_Fichero() {
        //Crear Controlador de Errores:
        try {
            //Establecer la Ruta del Archivo de Texto a Escribir Secuencia de Datos
            FileWriter Guardar = new FileWriter(Directorio);

            //Recorrer Filas del Modelo de Datos
            for (int i = 0; i < this.jTable_DatosCliente.getRowCount(); i++) {
                //Escribir secuencia de Datos
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
                //Guardar.write(Modelo.getValueAt(i,14).toString()+"\n")
            }
            //Cerrar el Archivo de Texto
            Guardar.close();

            //Mostrar mensaje de informacion 
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito", "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception Error) {
            //mostrar mensaje de error
            JOptionPane.showMessageDialog(null, Error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    void Leer_Datos() {
        //Declarar variable de tipo:String(Cadena de Texto)
        String Codigo, Apellidos, Nombres, FechaNacimiento, Sexo, Nacionalidad, TipoDocIdentidad, DocIdentidad, Departamento, Distrito, Provincia, Direccion, Telefono, Email, EstadoCliente;

        //Obtener Nombre del archivo
        File Leer_Doc = new File(Directorio);

        //Crear controlador de error 
        try {
            //Leer Documento(Nombre del archivo)
            Scanner Linea = new Scanner(Leer_Doc);

            //Crear estructura repetitiva(bucle):While(Leer las lines del archivo)
            while (Linea.hasNextLine()) {
                //Leer Datos del archivo de texto
                Codigo = Linea.nextLine();
                Apellidos = Linea.nextLine();
                Nombres = Linea.nextLine();
                FechaNacimiento = Linea.nextLine();
                Sexo = Linea.nextLine();
                Nacionalidad = Linea.nextLine();
                TipoDocIdentidad = Linea.nextLine();
                DocIdentidad = Linea.nextLine();
                Departamento = Linea.nextLine();
                Distrito = Linea.nextLine();
                Provincia = Linea.nextLine();
                Direccion = Linea.nextLine();
                Telefono = Linea.nextLine();
                Email = Linea.nextLine();
                EstadoCliente = Linea.nextLine();

                //Agregar las filas al modelo de Datos del jTable.
                Modelo.addRow(new Object[]{Codigo, Apellidos, Nombres, FechaNacimiento, Sexo, Nacionalidad, TipoDocIdentidad, DocIdentidad,
                    Departamento, Distrito, Provincia, Direccion, Telefono, Email, EstadoCliente});

            }

        } catch (Exception Error) {
            //Mostrar Error
            JOptionPane.showMessageDialog(null, Error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void Salir() {
        // DECLARAR UNA VARIABLE DE TIPOM ENTERO(INT)
        int Rpta;

        //MOSTRAR UN MENSAJE DE CONFIRMACION
        Rpta = JOptionPane.showConfirmDialog(null,
                "¿Desea Salir de la Ventana de Factura de Venta", "Mensaje",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        //EVALUAR LA RESPUESTA DEL USUARIO
        if (Rpta == 0) {
            //SALIR DEL PROGRAMA
            System.exit(0);
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
        TXT_Apellidos = new javax.swing.JTextField();
        TXT_Nombres = new javax.swing.JTextField();
        TXT_Email = new javax.swing.JTextField();
        TXT_Telefono = new javax.swing.JTextField();
        BTN_Registrado = new javax.swing.JRadioButton();
        BTN_Anulado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        CBO_TipoDoc = new javax.swing.JComboBox();
        Lb_CodCliente = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TXT_DocIdentidad = new javax.swing.JTextField();
        JPanel_Sexo = new javax.swing.JPanel();
        BTN_Masculino = new javax.swing.JRadioButton();
        BTN_Femenino = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        CBO_Pronvincia = new javax.swing.JComboBox();
        CBO_Departamento = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TXT_Direccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CBO_Nacionalidad = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        CBO_Distrito = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
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

        TXT_Apellidos.setText("jTextField1");
        TXT_Apellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXT_ApellidosFocusLost(evt);
            }
        });
        TXT_Apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_ApellidosActionPerformed(evt);
            }
        });
        TXT_Apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_ApellidosKeyTyped(evt);
            }
        });

        TXT_Nombres.setText("jTextField1");
        TXT_Nombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXT_NombresFocusLost(evt);
            }
        });
        TXT_Nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_NombresActionPerformed(evt);
            }
        });
        TXT_Nombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_NombresKeyTyped(evt);
            }
        });

        TXT_Email.setText("jTextField1");

        TXT_Telefono.setText("jTextField1");
        TXT_Telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_TelefonoKeyTyped(evt);
            }
        });

        ButtonCli.add(BTN_Registrado);
        BTN_Registrado.setText("Registrado");

        ButtonCli.add(BTN_Anulado);
        BTN_Anulado.setText("Anulado");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tipo Documento:");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_TipoDoc.setForeground(new java.awt.Color(0, 0, 0));
        CBO_TipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_TipoDocActionPerformed(evt);
            }
        });

        Lb_CodCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Lb_CodCliente.setForeground(new java.awt.Color(255, 255, 255));
        Lb_CodCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_CodCliente.setText("Codigo");
        Lb_CodCliente.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Codigo:");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TXT_DocIdentidad.setText("jTextField1");
        TXT_DocIdentidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXT_DocIdentidadFocusLost(evt);
            }
        });
        TXT_DocIdentidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_DocIdentidadKeyTyped(evt);
            }
        });

        JPanel_Sexo.setBackground(new java.awt.Color(0, 102, 153));
        JPanel_Sexo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        buttonOp.add(BTN_Masculino);
        BTN_Masculino.setText("M");
        BTN_Masculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_MasculinoActionPerformed(evt);
            }
        });

        buttonOp.add(BTN_Femenino);
        BTN_Femenino.setText("F");
        BTN_Femenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_FemeninoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanel_SexoLayout = new javax.swing.GroupLayout(JPanel_Sexo);
        JPanel_Sexo.setLayout(JPanel_SexoLayout);
        JPanel_SexoLayout.setHorizontalGroup(
            JPanel_SexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SexoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanel_SexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_Femenino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Masculino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        JPanel_SexoLayout.setVerticalGroup(
            JPanel_SexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SexoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_Masculino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_Femenino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Provincia:");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Pronvincia.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Pronvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_PronvinciaActionPerformed(evt);
            }
        });

        CBO_Departamento.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_DepartamentoActionPerformed(evt);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CBO_Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(CBO_Pronvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 16, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(13, 13, 13)
                .addComponent(CBO_Pronvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CBO_Departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Dirección:");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        TXT_Direccion.setText("jTextField1");
        TXT_Direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXT_DireccionFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Nacionalidad:");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Nacionalidad.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Nacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_NacionalidadActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Distrito");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        CBO_Distrito.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Distrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_DistritoActionPerformed(evt);
            }
        });

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
                                    .addComponent(Lb_CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_Nombres)))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addComponent(JPanel_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                        .addComponent(CBO_TipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TXT_DocIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXT_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXT_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addComponent(BTN_Registrado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BTN_Anulado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BTN_DesactivadoLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel12)
                                        .addGap(87, 87, 87))
                                    .addComponent(TXT_Telefono)
                                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BTN_DesactivadoLayout.createSequentialGroup()
                                        .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(119, 119, 119))
                                    .addComponent(CBO_Distrito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBO_Nacionalidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(294, 294, 294))))
        );
        BTN_DesactivadoLayout.setVerticalGroup(
            BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(TXT_Nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TXT_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(24, 24, 24)))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(Lb_CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(TXT_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_Registrado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_Anulado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(BTN_DesactivadoLayout.createSequentialGroup()
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BTN_DesactivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CBO_TipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_DocIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CBO_Distrito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_Desactivado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BTN_Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BTN_Nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                                    .addComponent(BTN_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_Eliminar))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
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

    private void TXT_ApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXT_ApellidosFocusLost
        //Convertir mayúsculas  lo que contiene TXT_Apellidos
        this.TXT_Apellidos.setText(this.TXT_Apellidos.getText().toUpperCase());
    }//GEN-LAST:event_TXT_ApellidosFocusLost

    private void TXT_ApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_ApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_ApellidosActionPerformed

    private void TXT_ApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_ApellidosKeyTyped
        this.Solo_Letras(evt);
    }//GEN-LAST:event_TXT_ApellidosKeyTyped

    private void TXT_NombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXT_NombresFocusLost
        //Convertir mayúsculas  lo que contiene TXT_Apellidos
        this.TXT_Nombres.setText(this.TXT_Nombres.getText().toUpperCase());
    }//GEN-LAST:event_TXT_NombresFocusLost

    private void TXT_NombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_NombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_NombresActionPerformed

    private void TXT_NombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_NombresKeyTyped
        this.Solo_Letras(evt);
    }//GEN-LAST:event_TXT_NombresKeyTyped

    private void TXT_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_TelefonoKeyTyped
        
        if (!Character.isDigit(evt.getKeyChar()) || TXT_Telefono.getText().length() == 9) {
            //deshabilitar Teclado
            evt.consume();
        }
    }//GEN-LAST:event_TXT_TelefonoKeyTyped

    private void CBO_TipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_TipoDocActionPerformed
        if (CBO_TipoDoc.getSelectedIndex() == 0) {

        }
    }//GEN-LAST:event_CBO_TipoDocActionPerformed

    private void TXT_DocIdentidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXT_DocIdentidadFocusLost
        this.TXT_DocIdentidad.setText(this.TXT_DocIdentidad.getText().toUpperCase());
    }//GEN-LAST:event_TXT_DocIdentidadFocusLost

    private void TXT_DocIdentidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_DocIdentidadKeyTyped

        if (CBO_TipoDoc.getSelectedIndex() == 0) {
            if (!Character.isDigit(evt.getKeyChar()) || TXT_DocIdentidad.getText().length() >= 12) {
                //deshabilitar Teclado
                evt.consume();
            }
        } else if (CBO_TipoDoc.getSelectedIndex() == 2) {
            if (!Character.isDigit(evt.getKeyChar()) || TXT_DocIdentidad.getText().length() == 8) {
                //deshabilitar Teclado
                evt.consume();
            }
        }
    }//GEN-LAST:event_TXT_DocIdentidadKeyTyped

    private void BTN_MasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_MasculinoActionPerformed

    }//GEN-LAST:event_BTN_MasculinoActionPerformed

    private void BTN_FemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_FemeninoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_FemeninoActionPerformed

    private void CBO_PronvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_PronvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_PronvinciaActionPerformed

    private void CBO_DepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_DepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_DepartamentoActionPerformed

    private void TXT_DireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXT_DireccionFocusLost
        //Convertir mayúsculas  lo que contiene TXT_Apellidos
        this.TXT_Direccion.setText(this.TXT_Direccion.getText().toUpperCase());
    }//GEN-LAST:event_TXT_DireccionFocusLost

    private void CBO_NacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_NacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_NacionalidadActionPerformed

    private void CBO_DistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_DistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_DistritoActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        // INVOCAR AL METODO: LIMPIAR()
        this.Limpiar();

        //SELECCIONAR EL PRIMER ELMENTO DEL COMBOBOX
        this.CBO_TipoDoc.setSelectedIndex(0);

        //EVALUAR SI EL CONTROL: JTABLE TIENE REGISTROS
        if (this.jTable_DatosCliente.getRowCount() == 0) {
            //ASIGANAR VALOR A LA VARIABLE:CODIGO
            Codigo = 1;
        } else {
            //GENERAR EL CODIGO DEL ESTUDIANTE
            Codigo = this.jTable_DatosCliente.getRowCount() + 1;
        }

        //MOSTRAR CODIGO DEL ESTUDIANTE
        this.Lb_CodCliente.setText(String.valueOf(Codigo));
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        int Rpta;

        if (TXT_DocIdentidad.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Falta Numeros en Documento de Identidad");
        }
        if (TXT_Telefono.getText().length() < 9) {
            JOptionPane.showMessageDialog(null, "Faltan Números en el Campo Telefono");
        }

        if (TXT_Apellidos.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Falta Ingresar Datos en el campo Nombres");
        }
        if (TXT_Nombres.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Falta Ingresar Datos en el campo Nombres");
        }

        if (TXT_Direccion.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Falta Ingresar Datos en el campo Dirección");
        }
        if (TXT_Email.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Falta Ingresar Datos en el campo Email");
        }
        if (BTN_Masculino.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Falta Ingresar el Sexo");
        } else if (BTN_Masculino.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Falta Ingresar el Sexo");
        }
        //DECLARAR UNA VARIABLE DE TIPO:ENTERO

        if (this.TXT_Email.getText().length() > 0) {
            Rpta = JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);

            //EVALUAR SI EL USUARIO CONFIRMO RESPUESTA: SÍ(0)
            if (Rpta == 0) {

                //CREAR UN VECTOOR DE DATOS (STRING)
                String[] Informacion = new String[8];

                //ASIGNAR VALORES AL VECTOR DE DATOS
                Informacion[0] = this.Lb_CodCliente.getText();
                Informacion[1] = this.TXT_Apellidos.getText();
                Informacion[2] = this.TXT_Nombres.getText();
                Informacion[3] = this.jDateChooser1.getDateFormatString();
                Informacion[4] = this.buttonOp.getElements().toString();
                Informacion[5] = this.CBO_Nacionalidad.getSelectedItem().toString();
                Informacion[6] = this.CBO_TipoDoc.getSelectedItem().toString();
                Informacion[7] = this.TXT_DocIdentidad.getText();
                Informacion[8] = this.CBO_Departamento.getSelectedItem().toString();
                Informacion[9] = this.CBO_Pronvincia.getSelectedItem().toString();
                Informacion[10] = this.CBO_Distrito.getSelectedItem().toString();
                Informacion[11] = this.TXT_Direccion.getText();
                Informacion[12] = this.TXT_Telefono.getText();
                Informacion[13] = this.TXT_Email.getText();
                Informacion[14] = this.ButtonCli.getElements().toString();

                if (this.BTN_Registrado.isSelected() == true) {
                    Informacion[7] = "Habilitado";
                } else {
                    Informacion[7] = "Bloqueado";
                }

                //AGREGAR DATOS AL DEFAULTTABLEMODEL (MODELO TABLA): MODELO
                Modelo.addRow(Informacion);

                //MOSTRAR MENSAJE DE INFORMACIÓN
                JOptionPane.showMessageDialog(null, "Datos Registardos, con Éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                //INVOCAR EVENTO:
                this.BTN_NuevoActionPerformed(null);

            } else {

                //MOSTRAR MENSAJE ERROR
                JOptionPane.showMessageDialog(null, "Imposible Registar Datos....\nFalta completar los datos en Registro del Usuario",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        this.Salir();
    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void jTable_DatosClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DatosClienteMouseClicked

        this.Cargar_Fila();
    }//GEN-LAST:event_jTable_DatosClienteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BTN_Anulado;
    private javax.swing.JPanel BTN_Desactivado;
    private javax.swing.JToggleButton BTN_Editar;
    private javax.swing.JToggleButton BTN_Eliminar;
    private javax.swing.JRadioButton BTN_Femenino;
    private javax.swing.JToggleButton BTN_Guardar;
    private javax.swing.JRadioButton BTN_Masculino;
    private javax.swing.JToggleButton BTN_Nuevo;
    private javax.swing.JRadioButton BTN_Registrado;
    private javax.swing.JToggleButton BTN_Salir;
    private javax.swing.ButtonGroup ButtonCli;
    private javax.swing.JComboBox CBO_Departamento;
    private javax.swing.JComboBox CBO_Distrito;
    private javax.swing.JComboBox CBO_Nacionalidad;
    private javax.swing.JComboBox CBO_Pronvincia;
    private javax.swing.JComboBox CBO_TipoDoc;
    private javax.swing.JPanel JPanel_Sexo;
    private javax.swing.JLabel Lb_CodCliente;
    private javax.swing.JTextField TXT_Apellidos;
    private javax.swing.JTextField TXT_Direccion;
    private javax.swing.JTextField TXT_DocIdentidad;
    private javax.swing.JTextField TXT_Email;
    private javax.swing.JTextField TXT_Nombres;
    private javax.swing.JTextField TXT_Telefono;
    private javax.swing.ButtonGroup buttonOp;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
