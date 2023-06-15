package Panel;


import Datos.*;
import Entidades.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JFrm_mantenimiento_Cliente extends javax.swing.JPanel {
    
//Llenar el combobox **docIdentidad** segun la base de datos
    DocIdentidadDB docIdentidad_dt = new DocIdentidadDB();
    ArrayList<DocIdentidadEntity> ListaDocIDentidad = new ArrayList<>();
    
    //Invocar a sql ***clientes***
    ClienteDB Cliente_dt = new ClienteDB();
    ArrayList<ClienteEntity> ListaCliente = new ArrayList<>();
    
    //DECLARAR CARIABLE DE TIPO: DEFAULTTABLEMODEL(MODELODE TABLA)
    DefaultTableModel Modelo;

    //DECLARAR VARIABLE DE TIPO: ENTERO
    int Codigo = 1, Filas;

    // ESTABLECER EL DIRECTORIO DEL PROYECTO, DONDE SE DESEA GUARDAR EL ARCHIVO DE TEXTO 
    String Directorio = new File("src/Registro_Usuario.txt").getAbsolutePath();

    public JFrm_mantenimiento_Cliente() {
        initComponents();
        
        jp_Datos.setVisible(false);
        //combobox docIdentidad
        ListaDocIDentidad = docIdentidad_dt.getCodDoc_IdentidadItems();

        for (DocIdentidadEntity item : ListaDocIDentidad) {
            CBO_Identidad.addItem(item.getTipo_Doc_Identidad());
        }
        CBO_Identidad.setSelectedIndex(-1);

        //ESTABLECER  UN NUEVO MODELO DE LA CLASE: DEFAULTTABLEMODEL
        Modelo = new DefaultTableModel() {
            //CREAR FUNCIÓN PARA BLOQUEAR LA EDICIÓN DE CELDAS
            public boolean isCelleditable(int row, int column) {
                //BLOQUEAR EDICIÓN
                return false;
            }
        };

        //ESTABLECER NOMBRE DE LAS COLUMNAS PARA EL CONTROL: JTABLE_REGISTROUSUARIO(A TRAAVÉS DE LA VARIABLE:MODELO)
        Modelo.addColumn("Codigo");
        Modelo.addColumn("Apellidos");
        Modelo.addColumn("Nombres");
        Modelo.addColumn("Selec.Doc Identidad");
        Modelo.addColumn("Doc. Identidad");
        Modelo.addColumn("Código del Cliente");
        Modelo.addColumn("Password");
        Modelo.addColumn("Estado del Cliente");

        //ESTABLECER EL MODELO AL CONTROL: jTABLE_REGISTRO_USURAIO
        this.JTable_RegistroUsuario.setModel(Modelo);
        Limpiar();
    }

//CREAR METODO LIMPIAR 
    void Limpiar() {

        //LIMPIAR EL TEXTO DE LOS CONTROLES: JTEXTFIELD
        this.TXTApellidos.setText("");
        this.TXTNombres.setText("");
        this.TXTIdentidad.setText("");
        this.TXTCodigo.setText("");
        this.TXTPassword.setText("");

        //LIMPIAR EL TEXTO DE LOS CONTROLES LABELS
        this.Lb_Codigo.setText("");

        //UBICAR EL CURSOR EN EL CONTROL: TXTAPELLIDOS
        this.TXTApellidos.requestFocus();
    }

    //CREAR MÉTODO:CARGAR_CURSOS
//    void Cargar_Cursos() {
//        //LIMPIAR TODO LOS ELMENTOS DEL COMCOBOX: CBO_IDENTIDAD
//        this.CBO_Identidad.removeAllItems();
//
//        //AGREGAR LOS ELEMENTOS AL COMBOBOX: CBO_IDENTIDAD
//        this.CBO_Identidad.addItem("<Seleccion>");
//        this.CBO_Identidad.addItem("DNI");
//        this.CBO_Identidad.addItem("Carne Extrangería");
//    }

    //CREAR METODO LOCAL: BOTONES()
    void Botones(boolean valor) {
        //ESTABLECER HABILITACIÓN DE BOTONES: TRUE/FALSE
        this.BTN_Nuevo.setEnabled(valor);
        this.BTN_Salir.setEnabled(valor);
        this.BTN_Agregar.setEnabled(valor);
        this.BTN_Editar.setEnabled(valor);
        this.BTN_Eliminar.setEnabled(valor);
        this.BTN_Buscar.setEnabled(valor);

    }

    //CREAR METODO LOCAL: SOLO_LETRAS()
    void Solo_Letras(java.awt.event.KeyEvent evt) {
        // EVALUAR QUE SOLO SE HAYA INGRESADO LETRAS Y NO NÚMEROS
        if (!Character.isLetter(evt.getKeyChar()) && !Character.isSpaceChar(evt.getKeyChar())) {
            //DESHABILITAR TECLADO
            evt.consume();
        }
    }

    //CREAR METODO LOCAL: SOLO_NUMEROS()
    void Solo_Numeros(java.awt.event.KeyEvent evt, javax.swing.JTextField CajaTexto) {
        //EVALUAR QUE SOLO SE HAYA INGRESADO NÚMEROS Y NO LETRAS
        if (!Character.isDigit(evt.getKeyChar()) || CajaTexto.getText().length() >= 12) {
            //DESHABILITAR TECLADO
            evt.consume();

        }
    }

    //CREAR METODO: CAMBIAR_CURSOR()
    void Cambiar_Cursor(java.awt.event.KeyEvent evt, javax.swing.JTextField CajaTexto) {
        // EVALUAR SI SE HA PRESIONADO LA TECLA:ENTER O LA TECLA: TAB (TABULACIÓN)
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //UBICAR CURSOR EN EL CONTROL:JTEXTFIELD
            CajaTexto.requestFocus();
        }
    }

    //CREAR EL METODO LOCAL: AUTOAJUSTAR_COLUMNAS()
    void AutoAjustar_Columnas() {
        //ESTABLECER ANCHOS DE COLUMNAS 
        //DEFINIR EL TAMÑO DE CADA COLUMNA DEL CONTROL (JTABLE): JTABLE_REGISTRO Y USUARIO
        int[] Anchos = {50, 130, 140, 120, 120, 120, 120, 120};

        //RECORRER EL NUMERO DE COLUMNAS DEL OBJETO (JTABLE): JATBLE_REGISTRO_USUARIO
        for (int Columna = 0; Columna < this.JTable_RegistroUsuario.getColumnCount(); Columna++) {
            //ESTABLECER EL ANCHO DE COLUMNA PARA CADA COLUMNA DEL JTABLE: JTABLE_REGISTROUSUARIO
            this.JTable_RegistroUsuario.getColumnModel().getColumn(Columna).setPreferredWidth(Anchos[Columna]);
        }

        //ESTABLECER EL AUTOREDISEÑO D TAMAÑO DEL JTABLEEN: AUTO_RESIZE_OFF
        this.JTable_RegistroUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //MOSTRAR LAS BARRAS DE DESPLAZAMIENTO: VERTICAL Y HORIZONTAL
        this.JTable_RegistroUsuario.setShowVerticalLines(true);
        this.JTable_RegistroUsuario.setShowHorizontalLines(true);
    }

    //CREAR EL METODO LOCAL:CARGAR_FILA()
    void Cargar_Fila() {
        //DECLARAR UNA VARIABLE DE TIPO:ENTERO Y OBTENER FILA SELECCIONADA DEL CONTROL:JTABLE
        int Seleccion = this.JTable_RegistroUsuario.getSelectedRow();

        //VCARGAR DATOS EN CONTROLES
        this.Lb_Codigo.setText(this.JTable_RegistroUsuario.getValueAt(Seleccion, 0).toString());
        this.TXTApellidos.setText(this.JTable_RegistroUsuario.getValueAt(Seleccion, 1).toString());
        this.TXTNombres.setText(this.JTable_RegistroUsuario.getValueAt(Seleccion, 2).toString());
        this.CBO_Identidad.setSelectedItem(this.JTable_RegistroUsuario.getValueAt(Seleccion, 3).toString());
        this.TXTIdentidad.setText(this.JTable_RegistroUsuario.getValueAt(Seleccion, 4).toString());
        this.TXTCodigo.setText(this.JTable_RegistroUsuario.getValueAt(Seleccion, 5).toString());
        this.TXTPassword.setText(this.JTable_RegistroUsuario.getValueAt(Seleccion, 6).toString());
    }

    //CRERA METODO LOCAL: GUARDAR_FICHERO()
    void Guardar_Fichero() {
        //Crerar Controlador de errores
        try {
            //ESTABLECER RUTA DEL ARCHIVO DE TEXTO A ESCRIBIR SECUENCIA DE DATOS
            FileWriter Guardar = new FileWriter(Directorio);

            //RECORRER FILAS DEL MODELO DE DATOS
            for (int i = 0; i < this.JTable_RegistroUsuario.getRowCount(); i++) {
                //ESCRIBIR SECUENCIA DE DATOS 
                Guardar.write(Modelo.getValueAt(i, 0).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 1).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 2).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 3).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 4).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 5).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 6).toString() + "\n");
                Guardar.write(Modelo.getValueAt(i, 7).toString() + "\n");
            }

            //CREAR EL ARCHIVO DE TEXTO
            Guardar.close();

            //MOSTRAR MENSAJE DE INFORMACIÓN
            JOptionPane.showMessageDialog(null, "Registro Guardado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception Error) {
            //Mostrar Mensaje de Error
            JOptionPane.showMessageDialog(null, Error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR EL METODO LOCAL: LEER_DATOS()
    void Leer_Datos() {
        //DECLARAR VARIABLES DE TIPO: STRING (CADENA DE TEXTO)
        String Codigo, Apellidos, Nombres, Identidad, TIdentidad, CCliente, Password, Ecliente;

        //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO
        File Leer_Doc = new File(Directorio);

        //CREAR CONTROLADOR DE EEROR 
        try {
            //LEER DOCUMENTO (NOMBRE DEL ARCHIVO)
            Scanner Linea = new Scanner(Leer_Doc);

            //CREAR ESTRUCTURA REPETITIVA (BUCLE):WHILE(LEER LINEAS DEL ARCHIVO)
            while (Linea.hasNextLine()) {
                //LEER DATOS DEL ARCHIVO DE TEXTO
                Codigo = Linea.nextLine();
                Apellidos = Linea.nextLine();
                Nombres = Linea.nextLine();
                Identidad = Linea.nextLine();
                TIdentidad = Linea.nextLine();
                CCliente = Linea.nextLine();
                Password = Linea.nextLine();
                Ecliente = Linea.nextLine();

                //AGREGAR FILAS AL MODELO DE DATOS DEL JTABLE.
                Modelo.addRow(new Object[]{Codigo, Apellidos, Nombres, Identidad, TIdentidad, CCliente,
                    Password, Ecliente});

            }
        } catch (Exception Error) {
            //MOSTRAR ERROR 
            JOptionPane.showMessageDialog(null, Error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // CREAR EL METODO LOCAL: SALIR ()
    void Salir() {
        //DECLARAR VARIABLE DE TIPO: ENTERO
        int Rpta;

        //MOSTRAR MENSAJE DE CONFIRMACIÓN
        Rpta = JOptionPane.showConfirmDialog(null, "¿Desea salir de la Ventana?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
        //EVALUAR SI SE HA PULSADO EL BOTON: SÍ
        if (Rpta == 0) {
            //INVOCAR AL METODO:GUARDAR_FICHERO
            this.Guardar_Fichero();

            //SALIR DEL PROGRAMA
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jp_Datos = new javax.swing.JPanel();
        TXTNombres = new javax.swing.JTextField();
        TXTApellidos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Lb_Codigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CBO_Identidad = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        TXTIdentidad = new javax.swing.JTextField();
        BTN_Buscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TXTCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TXTPassword = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        RBT_Habilitado = new javax.swing.JRadioButton();
        RBT_Bloqueado = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_RegistroUsuario = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Actualizar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        BTN_Salir = new javax.swing.JButton();
        BTN_Eliminar = new javax.swing.JButton();
        BTN_Editar = new javax.swing.JButton();
        BTN_Agregar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS  DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        TXTNombres.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXTNombres.setForeground(new java.awt.Color(0, 0, 0));
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

        TXTApellidos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXTApellidos.setForeground(new java.awt.Color(0, 0, 0));
        TXTApellidos.setText("jTextField1");
        TXTApellidos.setSelectionEnd(117);
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

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nombres:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Apellidos:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Código:");

        Lb_Codigo.setBackground(new java.awt.Color(255, 255, 255));
        Lb_Codigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Lb_Codigo.setText("Lbcodigo");
        Lb_Codigo.setOpaque(true);

        javax.swing.GroupLayout jp_DatosLayout = new javax.swing.GroupLayout(jp_Datos);
        jp_Datos.setLayout(jp_DatosLayout);
        jp_DatosLayout.setHorizontalGroup(
            jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DatosLayout.createSequentialGroup()
                .addGroup(jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_DatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lb_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TXTApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_DatosLayout.setVerticalGroup(
            jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_DatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo. de Doc. Identidad:");

        CBO_Identidad.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        CBO_Identidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_IdentidadItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Doc. Identidad:");

        TXTIdentidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXTIdentidad.setForeground(new java.awt.Color(0, 0, 0));
        TXTIdentidad.setText("jTextField1");
        TXTIdentidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTIdentidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTIdentidadKeyTyped(evt);
            }
        });

        BTN_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/btnBuscarIcon.png"))); // NOI18N
        BTN_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBO_Identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TXTIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTN_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jp_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(33, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CBO_Identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TXTIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jp_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " REGISTRO  DEL CLIENTE ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Usuario:");

        TXTCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXTCodigo.setForeground(new java.awt.Color(0, 0, 0));
        TXTCodigo.setText("jTextField2");
        TXTCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTCodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTCodigoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña:");

        TXTPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXTPassword.setForeground(new java.awt.Color(0, 0, 0));
        TXTPassword.setText("jPasswordField1");
        TXTPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTPasswordKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        RBT_Habilitado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        RBT_Habilitado.setText("Habilitado");

        RBT_Bloqueado.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        RBT_Bloqueado.setText("Bloqueado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RBT_Habilitado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RBT_Bloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBT_Habilitado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RBT_Bloqueado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTable_RegistroUsuario.setBackground(new java.awt.Color(204, 204, 204));
        JTable_RegistroUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        JTable_RegistroUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_RegistroUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_RegistroUsuario);

        BTN_Nuevo.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        BTN_Nuevo.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nuevo.png"))); // NOI18N
        BTN_Nuevo.setText("Nuevo");
        BTN_Nuevo.setIconTextGap(10);
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });

        BTN_Actualizar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Actualizar.png"))); // NOI18N
        BTN_Actualizar.setText("Actualizar");
        BTN_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ActualizarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        BTN_Salir.setBackground(new java.awt.Color(153, 0, 0));
        BTN_Salir.setForeground(new java.awt.Color(255, 255, 255));
        BTN_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion.png"))); // NOI18N
        BTN_Salir.setText("Salir");
        BTN_Salir.setIconTextGap(10);
        BTN_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SalirActionPerformed(evt);
            }
        });

        BTN_Eliminar.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Eliminar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/X.png"))); // NOI18N
        BTN_Eliminar.setText("Eliminar");
        BTN_Eliminar.setIconTextGap(10);
        BTN_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EliminarActionPerformed(evt);
            }
        });

        BTN_Editar.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Editar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Nota.png"))); // NOI18N
        BTN_Editar.setText("Editar");
        BTN_Editar.setIconTextGap(10);
        BTN_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EditarActionPerformed(evt);
            }
        });

        BTN_Agregar.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Agregar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar.png"))); // NOI18N
        BTN_Agregar.setText("Agregar");
        BTN_Agregar.setIconTextGap(10);
        BTN_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(BTN_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(BTN_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BTN_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TXTApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTApellidosFocusLost
        //CONVERTIR A MAYUSCULAS LO QQUE CONTINE EL CONTROL: TXTAPELLIDOS
        this.TXTApellidos.setText(this.TXTApellidos.getText().toUpperCase());
    }//GEN-LAST:event_TXTApellidosFocusLost

    private void TXTApellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTApellidosKeyPressed
        // EVALUAR SI SE HA PRESIONADO LA TECLA:ENTER O LA TECLA: TAB (TABULACIÓN)
        this.Cambiar_Cursor(evt, this.TXTNombres);
    }//GEN-LAST:event_TXTApellidosKeyPressed

    private void TXTApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTApellidosKeyTyped
        //INVOCAR AL METODO: SOLO_LETRAS
        this.Solo_Letras(evt);
    }//GEN-LAST:event_TXTApellidosKeyTyped

    private void TXTNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTNombresFocusLost
        //CONVERTIR A MAYUSCULAS LO QUE CONTINE EL CONTROL: TXTNOMBRES
        this.TXTNombres.setText(this.TXTNombres.getText().toUpperCase());
    }//GEN-LAST:event_TXTNombresFocusLost

    private void TXTNombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNombresKeyPressed
        // EVALUAR SI SE HA PRESIONADO LA TECLA:ENTER O LA TECLA: TAB (TABULACIÓN)
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //UBICAR CURSOR EN EL CONTROL:JTEXTFIELD
            this.CBO_Identidad.requestFocus();
        }
    }//GEN-LAST:event_TXTNombresKeyPressed

    private void TXTNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTNombresKeyTyped
        //INVOCAR AL METODO:SOLO_LETRAS
        this.Solo_Letras(evt);
    }//GEN-LAST:event_TXTNombresKeyTyped

    private void CBO_IdentidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_IdentidadItemStateChanged
        // UBICAR CURSOR EN EL CONTROL:JCOMBOBOX
        this.TXTIdentidad.requestFocus();
    }//GEN-LAST:event_CBO_IdentidadItemStateChanged

    private void TXTIdentidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTIdentidadKeyPressed
        // EVALUAR SI SE HA PRESIONADO LA TECLA:ENTER O LA TECLA: TAB (TABULACIÓN)
        this.Cambiar_Cursor(evt, this.TXTCodigo);
    }//GEN-LAST:event_TXTIdentidadKeyPressed

    private void TXTIdentidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTIdentidadKeyTyped
        //INVOCAR AL METODO: SOLO_NUMEROS
        this.Solo_Numeros(evt, this.TXTIdentidad);
    }//GEN-LAST:event_TXTIdentidadKeyTyped

    private void BTN_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BuscarActionPerformed

        //Invocar a la base de datos cliente
        ListaCliente = Cliente_dt.getBuscarCliente(this.TXTIdentidad.getText());

        if (ListaCliente != null && ListaCliente.size() > 0) {

            ClienteEntity DataEncontrada = ListaCliente.get(0);

            Lb_Codigo.setText(DataEncontrada.getCodCliente());
            TXTApellidos.setText(DataEncontrada.getApellidos());
            TXTNombres.setText(DataEncontrada.getNombres());
           
            jp_Datos.setVisible(true);
        } else {
            //Evitar que aparezca el panel
            jp_Datos.setVisible(false);
            JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso", 0);
        }
        
    }//GEN-LAST:event_BTN_BuscarActionPerformed

    private void TXTCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTCodigoKeyPressed
        // EVALUAR SI SE HA PRESIONADO LA TECLA:ENTER O LA TECLA: TAB (TABULACIÓN)
        this.Cambiar_Cursor(evt, this.TXTPassword);
    }//GEN-LAST:event_TXTCodigoKeyPressed

    private void TXTCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTCodigoKeyTyped
        //INVOCAR AL METODO: SOLO_NUMEROS
        this.Solo_Numeros(evt, this.TXTCodigo);
    }//GEN-LAST:event_TXTCodigoKeyTyped

    private void TXTPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPasswordKeyPressed
        // EVALUAR SI SE HA PRESIONADO LA TECLA:ENTER O LA TECLA: TAB (TABULACIÓN)
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //UBICAR CURSOR EN EL CONTROL: JBUTTON(BTN_CALCULAR
            this.BTN_Nuevo.requestFocus();
        }
    }//GEN-LAST:event_TXTPasswordKeyPressed

    private void JTable_RegistroUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_RegistroUsuarioMouseClicked
        //INVOCAR AL METODO. CARGAR_FILA()
        this.Cargar_Fila();
    }//GEN-LAST:event_JTable_RegistroUsuarioMouseClicked

    private void BTN_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActualizarActionPerformed
        //Crear Vector de Datos
        String[] Informacion = new String[9];

        //Asignar al Vector de Datos
        Informacion[0] = this.Lb_Codigo.getText();
        Informacion[1] = this.TXTApellidos.getText();
        Informacion[2] = this.TXTNombres.getText();
        Informacion[3] = this.CBO_Identidad.getSelectedItem().toString();
        Informacion[4] = this.TXTIdentidad.getText();
        Informacion[5] = this.TXTCodigo.getText();
        Informacion[6] = this.TXTPassword.getText();
        Informacion[7] = this.RBT_Bloqueado.getText();
        Informacion[8] = this.RBT_Habilitado.getText();

        //actualizar Datos en el Modelo de Datos en base al Número Total de Colmunas del JTable
        for (int Columna = 0; Columna < this.JTable_RegistroUsuario.getColumnCount(); Columna++) {
            //establecer Valores en el Modelo de Datos
            Modelo.setValueAt(Informacion[Columna], Filas, Columna);
        }

        //Mostrar Mensaje de Información
        JOptionPane.showMessageDialog(null, "Registro de Identidad del Usuario Actualizado, Con Exito", "Información", JOptionPane.INFORMATION_MESSAGE);

        //Habilitar Botones
        this.Botones(true);
    }//GEN-LAST:event_BTN_ActualizarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        // INVOCAR AL METODO: LIMPIAR()
        this.Limpiar();

        //SELECCIONAR EL PRIMER ELMENTO DEL COMBOBOX
        this.CBO_Identidad.setSelectedIndex(0);

        //EVALUAR SI EL CONTROL: JTABLE TIENE REGISTROS
        if (this.JTable_RegistroUsuario.getRowCount() == 0) {
            //ASIGANAR VALOR A LA VARIABLE:CODIGO
            Codigo = 1;
        } else {
            //GENERAR EL CODIGO DEL ESTUDIANTE
            Codigo = this.JTable_RegistroUsuario.getRowCount() + 1;
        }

        //MOSTRAR CODIGO DEL ESTUDIANTE
        this.Lb_Codigo.setText(String.valueOf(Codigo));
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AgregarActionPerformed
        //DECLARAR UNA VARIABLE DE TIPO:ENTERO
        int Rpta;

        if (this.TXTPassword.getText().length() > 0) {
            Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Agregar los datos al Registro de usuario?",
                    "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);

            //EVALUAR SI EL USUARIO CONFIRMO RESPUESTA: SÍ(0)
            if (Rpta == 0) {

                //CREAR UN VECTOOR DE DATOS (STRING)
                String[] Informacion = new String[8];

                //ASIGNAR VALORES AL VECTOR DE DATOS
                Informacion[0] = this.Lb_Codigo.getText();
                Informacion[1] = this.TXTApellidos.getText();
                Informacion[2] = this.TXTNombres.getText();
                Informacion[3] = this.CBO_Identidad.getSelectedItem().toString();
                Informacion[4] = this.TXTIdentidad.getText();
                Informacion[5] = this.TXTCodigo.getText();
                Informacion[6] = this.TXTPassword.getText();

                if (this.RBT_Habilitado.isSelected() == true) {
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
    }//GEN-LAST:event_BTN_AgregarActionPerformed

    private void BTN_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EditarActionPerformed
        // DESHABILITAR BOTONES
        this.Botones(false);
    }//GEN-LAST:event_BTN_EditarActionPerformed

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        // CAPTURAR LA FILA SELECCIONADA
        int FilaSeleccionada = this.JTable_RegistroUsuario.getSelectedRow();

        //EVALUAR SIE EL VALOR DE LA VARIABLE fILASELEECIONADA ES DIFERENTE O DISTINTO DE -1
        if (FilaSeleccionada != -1) {

            //DECLARAR UNA VARIABLE DE TIPO: ENTERO
            int Rpta;
            Rpta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar '"
                    + this.JTable_RegistroUsuario.getValueAt(FilaSeleccionada, 0)
                    + "'?", "Confirmación", JOptionPane.YES_NO_OPTION);

            //EVALUAR SI RESPUESTA FUE AFIMRATIVA(0)
            if (Rpta == 0) {

                //CAPTUARAR EL INDICE DEL MODELO DE LA TABLA
                int IndiceModelo = this.JTable_RegistroUsuario.convertColumnIndexToModel(FilaSeleccionada);

                //Eliminar Fila del Modelo
                Modelo.removeRow(IndiceModelo);

                //Invocar al método Limpiar
                this.Limpiar();

                //Seleccionar el Primer Elemento del ComboBox
                this.CBO_Identidad.setSelectedIndex(0);
            }
        } else {
            //Mostrar Mensaje de Error
            JOptionPane.showMessageDialog(null, "Debe seleccionar la Fila a Eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        //INVOCAR AL METODO: SALIR()
        this.Salir();
    }//GEN-LAST:event_BTN_SalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actualizar;
    private javax.swing.JButton BTN_Agregar;
    private javax.swing.JButton BTN_Buscar;
    private javax.swing.JButton BTN_Editar;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JComboBox<String> CBO_Identidad;
    private javax.swing.JTable JTable_RegistroUsuario;
    private javax.swing.JLabel Lb_Codigo;
    private javax.swing.JRadioButton RBT_Bloqueado;
    private javax.swing.JRadioButton RBT_Habilitado;
    private javax.swing.JTextField TXTApellidos;
    private javax.swing.JTextField TXTCodigo;
    private javax.swing.JTextField TXTIdentidad;
    private javax.swing.JTextField TXTNombres;
    private javax.swing.JPasswordField TXTPassword;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp_Datos;
    // End of variables declaration//GEN-END:variables
}
