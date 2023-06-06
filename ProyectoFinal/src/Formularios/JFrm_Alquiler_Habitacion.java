package Formularios;

import Datos.*;
import Entidades.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JFrm_Alquiler_Habitacion extends javax.swing.JFrame {

    //Llenar el combobox docIdentidad segun la base de datos
    DocIdentidadDB docIdentidad_dt = new DocIdentidadDB();
    ArrayList<DocIdentidadEntity> ListaDocIDentidad = new ArrayList<>();

    //Invocar a sql clientes
    ClienteDB Cliente_dt = new ClienteDB();
    ArrayList<ClienteEntity> ListaCliente = new ArrayList<>();

    //Llenar el combobox sede segun la base de datos
    SedeDB Sede_db = new SedeDB();
    ArrayList<SedeEntity> ListaSede = new ArrayList<>();

    //Llenar el combobox docIdentidad segun la base de datos
    Tipo_HabitacionDB Tipo_Habitaciondb = new Tipo_HabitacionDB();
    ArrayList<Tipo_HabitacionEntity> ListaTipo_Habitacion = new ArrayList<>();

    //Llenar el combobox Piso segun la base de datos
    NivelDB Niveldb = new NivelDB();
    ArrayList<NivelEntity> ListaNivel = new ArrayList<>();

    //Llenar el combobox N° Habitacion segun la base de datos
    Reserva_AlquilerDB reserva_alquilerdb = new Reserva_AlquilerDB();
    ArrayList<Reserva_AlquilerEntity> Listareserva_alquiler = new ArrayList<>();

    //DECLARAR VARIABLES PARA LAS LISTAS A UTILIZAR
    DefaultListModel ListaProd = new DefaultListModel();

    //ESTABLECER EL DIREECTORIO DEL PROYECTO , DONDE SE DESEA GUARDAR EL ARCHIVO DE TEXTO
    String Directorio_Clientes = new File("src/Archivo_Datos/Registro_Cliente.txt").getAbsolutePath();
    String Directorio_Habitacion = new File("src/Archivo_Datos/Datos_Habitacion.txt").getAbsolutePath();

    //CREAR UN VECTOR DE DATOS BIDIMENSIONAL (STRING) MATRIZ DE DATOS
    String[][] Clientes;

    String[][] TipoHabitacion = new String[9][4];

    //DECLARAR VARIABLE TIPO : DEFAULTTABLEMODEL(MODELI DE TABLA)
    DefaultTableModel Modelo;

    //DECLARAR VARIABLE DE TIPO : ENTERO
    int Codigo = 1, Filas;

    //Establecer el Directorio del Proyecto, donde se desea guardar el Archivo de Texto
    String Directorio = new File("src/Archivo_Datos/Registro_DatosClientes.txt").getAbsolutePath();

    public JFrm_Alquiler_Habitacion() {
        initComponents();
        jp_separador2.setPreferredSize(new Dimension(0, 38));
        jp_separador.setPreferredSize(new Dimension(16, 304));
        jp_foder.setPreferredSize(new Dimension(400, 72));
        jp_CLiente.setPreferredSize(new Dimension(523, 309));
        pl_InformacionCliente.setPreferredSize(new Dimension(489, 182));
        pl_InformacionCliente.setVisible(false);

        //combobox docIdentidad
        ListaDocIDentidad = docIdentidad_dt.getCodDoc_IdentidadItems();

        CBO_DocIdentidad.addItem("-Seleccionar-");
        CBO_DocIdentidad.setSelectedIndex(0);
        for (DocIdentidadEntity item : ListaDocIDentidad) {
            CBO_DocIdentidad.addItem(item.getTipo_Doc_Identidad());
        }
        CBO_DocIdentidad.setSelectedIndex(-1);

        //combobox sede
        ListaSede = Sede_db.getCodSedeItems();

        for (SedeEntity item : ListaSede) {
            CBO_Sede.addItem(item.getCodSede());
        }
        CBO_Sede.setSelectedIndex(-1);

        //combobox Tipo Habitacion
        ListaTipo_Habitacion = Tipo_Habitaciondb.getCodTipo_HabitacionItems();

        for (Tipo_HabitacionEntity item : ListaTipo_Habitacion) {
            CBO_TipoHabitacion.addItem(item.getDescripcion_TipoHabitacion());
        }
        CBO_TipoHabitacion.setSelectedIndex(-1);

//        SedeDB sede = new SedeDB();
//        TXTTarifa_Noche.setText(sede.elementoSede("SEDE1").getNombre_Sede());
        // sede.elementoSede("SEDE1"). ;

        //combobox Piso
        ListaNivel = Niveldb.getCodNivelItems();

        for (NivelEntity item : ListaNivel) {
            CBO_Piso.addItem(item.getNivel_Piso());
        }
        CBO_Piso.setSelectedIndex(-1);

        //combobox N° Habitación
        Listareserva_alquiler = reserva_alquilerdb.getCodDoc_IdentidadItems();

        for (Reserva_AlquilerEntity item : Listareserva_alquiler) {
            CBO_N_Habitacion.addItem(item.getNumAlquiler());
        }
        CBO_N_Habitacion.setSelectedIndex(-1);

        //Establecer el color de fondo del formulario
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        //Establecer un Nuevo Modelo de la clase :DEfaultTableModel
        Modelo = new DefaultTableModel() {
            //Crear funcion para bloquear la Edicion de Celdas
            public boolean isCellEditable(int row, int column) {
                //Boquear Edicion 
                return false;
            }
        };

        //Establecer Nombre de las Columnas para el control: Jtable_RegistroNotas(a travéz de la variable Modelo)
        Modelo.addColumn("Código");
        Modelo.addColumn("Apellidos");
        Modelo.addColumn("Nombres");
        Modelo.addColumn("Tipo de Doc. Identidad");
        Modelo.addColumn("N° Doc. Identidad");
        Modelo.addColumn("Nacionalidad");
        Modelo.addColumn("Distrito");
        Modelo.addColumn("Dirección");
        Modelo.addColumn("Sede");
        Modelo.addColumn("Tipo de Habitación");
        Modelo.addColumn("piso");
        // Modelo.addColumn("N° de Habitación");
        // Modelo.addColumn("Tirafa por hora");
        // Modelo.addColumn("Tarifa por noche");
        // Modelo.addColumn("Fecha de Alquiler");
        // Modelo.addColumn("Hora de Ingreso");
        // Modelo.addColumn("Fec. Termino de Alquiler");
        // Modelo.addColumn("Hora de Salida");
        // Modelo.addColumn("Importe");
        // Modelo.addColumn("Estado Alquiler");

        //Establecer el Modelo al control Jtable_RegistroNotas
        this.jTable_RegistroCliente.setModel(Modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GpPorTarifa = new javax.swing.ButtonGroup();
        jp_CLiente = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CBO_DocIdentidad = new javax.swing.JComboBox<>();
        TXTDoc_Identidad_Cliente = new javax.swing.JTextField();
        BTN_NuevoCliente = new javax.swing.JButton();
        BTN_BuscarCliente1 = new javax.swing.JButton();
        pl_InformacionCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Lb_CodCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Lb_Nombre_Cliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Lb_Apellidos_Cliente = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Lb_Direccion_Cliente = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Lb_Nacionalidad_Cliente = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Lb_Distrito_Cliente = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        CBO_N_Habitacion = new javax.swing.JComboBox<>();
        CBO_Sede = new javax.swing.JComboBox<>();
        CBO_TipoHabitacion = new javax.swing.JComboBox<>();
        CBO_Piso = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        TXTImporte = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        TXTTarifa_Noche = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jD_Fec_Inicio = new com.toedter.calendar.JDateChooser();
        CBO_H_Ingreso1 = new javax.swing.JComboBox<>();
        CBO_H_Ingreso2 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jD_Fec_Termino = new com.toedter.calendar.JDateChooser();
        CBO_H_Salida_1 = new javax.swing.JComboBox<>();
        CBO_H_Salida2 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        Lb_Foto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_RegistroCliente = new javax.swing.JTable();
        jp_foder = new javax.swing.JPanel();
        BTN_Anular = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        jp_separador = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        BTN_Salir = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jp_separador2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(100, 100));
        setMinimumSize(new java.awt.Dimension(100, 100));
        setModalExclusionType(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jp_CLiente.setBackground(new java.awt.Color(0, 102, 153));
        jp_CLiente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(204, 204, 204))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de doc. Identidad:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("N° doc. Identidad:");

        CBO_DocIdentidad.setBackground(new java.awt.Color(204, 204, 204));
        CBO_DocIdentidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_DocIdentidad.setForeground(new java.awt.Color(0, 0, 0));

        TXTDoc_Identidad_Cliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TXTDoc_Identidad_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        TXTDoc_Identidad_Cliente.setText("jTextField1");
        TXTDoc_Identidad_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTDoc_Identidad_ClienteKeyTyped(evt);
            }
        });

        BTN_NuevoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_NuevoCliente.setForeground(new java.awt.Color(0, 0, 0));
        BTN_NuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/BtnAgregarClienteIcon.png"))); // NOI18N
        BTN_NuevoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        BTN_BuscarCliente1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_BuscarCliente1.setForeground(new java.awt.Color(0, 0, 0));
        BTN_BuscarCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/LupaIcon.png"))); // NOI18N
        BTN_BuscarCliente1.setToolTipText("Bucar Cliente");
        BTN_BuscarCliente1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_BuscarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BuscarCliente1ActionPerformed(evt);
            }
        });

        pl_InformacionCliente.setBackground(new java.awt.Color(204, 204, 204));
        pl_InformacionCliente.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Código:");

        Lb_CodCliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_CodCliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_CodCliente.setText("Lb_Codigo");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombres:");

        Lb_Nombre_Cliente.setBackground(new java.awt.Color(255, 255, 255));
        Lb_Nombre_Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_Nombre_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Nombre_Cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Nombre_Cliente.setText("Lb_Nombres");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Apellidos:");

        Lb_Apellidos_Cliente.setBackground(new java.awt.Color(255, 255, 255));
        Lb_Apellidos_Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_Apellidos_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Apellidos_Cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Apellidos_Cliente.setText("Lb_Apellidos");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Dirección:");

        Lb_Direccion_Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_Direccion_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Direccion_Cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Direccion_Cliente.setText("Lb_Dirección");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nacionalidad:");

        Lb_Nacionalidad_Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_Nacionalidad_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Nacionalidad_Cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Nacionalidad_Cliente.setText("Lb_Nacionalidad");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Distrito:");

        Lb_Distrito_Cliente.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_Distrito_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Distrito_Cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Distrito_Cliente.setText("Lb_Distrito");

        javax.swing.GroupLayout pl_InformacionClienteLayout = new javax.swing.GroupLayout(pl_InformacionCliente);
        pl_InformacionCliente.setLayout(pl_InformacionClienteLayout);
        pl_InformacionClienteLayout.setHorizontalGroup(
            pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl_InformacionClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Lb_Direccion_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pl_InformacionClienteLayout.createSequentialGroup()
                        .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(Lb_Nacionalidad_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Distrito_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pl_InformacionClienteLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel8)
                    .addGroup(pl_InformacionClienteLayout.createSequentialGroup()
                        .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(Lb_CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Lb_Nombre_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Apellidos_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pl_InformacionClienteLayout.setVerticalGroup(
            pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl_InformacionClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Nombre_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Apellidos_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Lb_Direccion_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pl_InformacionClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Nacionalidad_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Distrito_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jp_CLienteLayout = new javax.swing.GroupLayout(jp_CLiente);
        jp_CLiente.setLayout(jp_CLienteLayout);
        jp_CLienteLayout.setHorizontalGroup(
            jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_CLienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_CLienteLayout.createSequentialGroup()
                        .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBO_DocIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jp_CLienteLayout.createSequentialGroup()
                                .addComponent(TXTDoc_Identidad_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTN_BuscarCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTN_NuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jp_CLienteLayout.createSequentialGroup()
                        .addComponent(pl_InformacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        jp_CLienteLayout.setVerticalGroup(
            jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_CLienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_BuscarCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_NuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_CLienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CBO_DocIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TXTDoc_Identidad_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pl_InformacionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Datos de la Habitación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Sede:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Habitación:");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Piso:");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("N° de Habitación:");

        CBO_N_Habitacion.setBackground(new java.awt.Color(255, 255, 255));
        CBO_N_Habitacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_N_Habitacion.setForeground(new java.awt.Color(0, 0, 0));

        CBO_Sede.setBackground(new java.awt.Color(255, 255, 255));
        CBO_Sede.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_Sede.setForeground(new java.awt.Color(0, 0, 0));

        CBO_TipoHabitacion.setBackground(new java.awt.Color(255, 255, 255));
        CBO_TipoHabitacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_TipoHabitacion.setForeground(new java.awt.Color(0, 0, 0));
        CBO_TipoHabitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_TipoHabitacionItemStateChanged(evt);
            }
        });

        CBO_Piso.setBackground(new java.awt.Color(255, 255, 255));
        CBO_Piso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_Piso.setForeground(new java.awt.Color(0, 0, 0));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Importe:");

        TXTImporte.setBackground(new java.awt.Color(204, 204, 204));
        TXTImporte.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        TXTImporte.setForeground(new java.awt.Color(0, 0, 0));
        TXTImporte.setText("0.00");
        TXTImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTImporteActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Tarifa", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        GpPorTarifa.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setText("Por Hora");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        GpPorTarifa.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton2.setText("Por Noche");

        TXTTarifa_Noche.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TXTTarifa_Noche.setForeground(new java.awt.Color(0, 0, 0));
        TXTTarifa_Noche.setText("jTextField1");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Precio:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(TXTTarifa_Noche, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTTarifa_Noche, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha Inicio Alquiler", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        CBO_H_Ingreso1.setBackground(new java.awt.Color(255, 255, 255));
        CBO_H_Ingreso1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_H_Ingreso1.setForeground(new java.awt.Color(0, 0, 0));
        CBO_H_Ingreso1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CBO_H_Ingreso2.setBackground(new java.awt.Color(255, 255, 255));
        CBO_H_Ingreso2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_H_Ingreso2.setForeground(new java.awt.Color(0, 0, 0));
        CBO_H_Ingreso2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jD_Fec_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CBO_H_Ingreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBO_H_Ingreso2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jD_Fec_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CBO_H_Ingreso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CBO_H_Ingreso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha Termino Alquiler", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        CBO_H_Salida_1.setBackground(new java.awt.Color(255, 255, 255));
        CBO_H_Salida_1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_H_Salida_1.setForeground(new java.awt.Color(0, 0, 0));
        CBO_H_Salida_1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CBO_H_Salida2.setBackground(new java.awt.Color(255, 255, 255));
        CBO_H_Salida2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_H_Salida2.setForeground(new java.awt.Color(0, 0, 0));
        CBO_H_Salida2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jD_Fec_Termino, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CBO_H_Salida_1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBO_H_Salida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jD_Fec_Termino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CBO_H_Salida_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CBO_H_Salida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(CBO_Sede, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(CBO_TipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(73, 73, 73)
                                        .addComponent(jLabel21))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(CBO_Piso, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CBO_N_Habitacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXTImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBO_Sede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_TipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_Piso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_N_Habitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Foto de la Habitación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(204, 204, 204))); // NOI18N

        Lb_Foto.setMaximumSize(new java.awt.Dimension(50, 16));
        Lb_Foto.setMinimumSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lb_Foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lb_Foto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTable_RegistroCliente.setBackground(new java.awt.Color(0, 102, 153));
        jTable_RegistroCliente.setForeground(new java.awt.Color(0, 0, 0));
        jTable_RegistroCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_RegistroCliente.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable_RegistroCliente.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable_RegistroCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_RegistroClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_RegistroCliente);

        jp_foder.setBackground(new java.awt.Color(0, 0, 0));

        BTN_Anular.setBackground(new java.awt.Color(20, 113, 176));
        BTN_Anular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_Anular.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Anular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rechazar.png"))); // NOI18N

        BTN_Nuevo.setBackground(new java.awt.Color(20, 113, 176));
        BTN_Nuevo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_Nuevo.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nuevodoc.png"))); // NOI18N
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });

        BTN_Guardar.setBackground(new java.awt.Color(20, 113, 176));
        BTN_Guardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_Guardar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salvar.png"))); // NOI18N
        BTN_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_foderLayout = new javax.swing.GroupLayout(jp_foder);
        jp_foder.setLayout(jp_foderLayout);
        jp_foderLayout.setHorizontalGroup(
            jp_foderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_foderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_Anular, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(BTN_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(BTN_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jp_foderLayout.setVerticalGroup(
            jp_foderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_foderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_foderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_Anular, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jp_separador.setBackground(java.awt.Color.lightGray);
        jp_separador.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        javax.swing.GroupLayout jp_separadorLayout = new javax.swing.GroupLayout(jp_separador);
        jp_separador.setLayout(jp_separadorLayout);
        jp_separadorLayout.setHorizontalGroup(
            jp_separadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        jp_separadorLayout.setVerticalGroup(
            jp_separadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        BTN_Salir.setBackground(new java.awt.Color(153, 0, 0));
        BTN_Salir.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_Salir.setForeground(new java.awt.Color(255, 255, 255));
        BTN_Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion.png"))); // NOI18N
        BTN_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SalirActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Empleado: Ruth Sayuri Timo Tasayco");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel10)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp_separador2.setBackground(java.awt.Color.lightGray);

        javax.swing.GroupLayout jp_separador2Layout = new javax.swing.GroupLayout(jp_separador2);
        jp_separador2.setLayout(jp_separador2Layout);
        jp_separador2Layout.setHorizontalGroup(
            jp_separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jp_separador2Layout.setVerticalGroup(
            jp_separador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_foder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jp_CLiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jp_separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp_separador2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jp_CLiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jp_separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)))
                .addComponent(jp_foder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_separador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void Limpiar() {
        //LIMPIAR LAS CAJAS DE TEXTO DEL CLIENTE
        this.Lb_Apellidos_Cliente.setText("");
        this.Lb_Nombre_Cliente.setText("");
        this.TXTDoc_Identidad_Cliente.setText("");
        this.Lb_Nacionalidad_Cliente.setText("");
        this.Lb_Distrito_Cliente.setText("");
        this.Lb_Direccion_Cliente.setText("");

        //LIMPIAR LAS CAJAS DE TEXTO 
        this.TXTImporte.setText("");
    }

    void Cargar_Datos_CBO() {

        this.CBO_H_Ingreso1.removeAllItems();

        this.CBO_H_Ingreso1.addItem("1");
        this.CBO_H_Ingreso1.addItem("2");
        this.CBO_H_Ingreso1.addItem("3");
        this.CBO_H_Ingreso1.addItem("4");
        this.CBO_H_Ingreso1.addItem("5");
        this.CBO_H_Ingreso1.addItem("6");
        this.CBO_H_Ingreso1.addItem("7");
        this.CBO_H_Ingreso1.addItem("8");
        this.CBO_H_Ingreso1.addItem("9");
        this.CBO_H_Ingreso1.addItem("10");
        this.CBO_H_Ingreso1.addItem("11");
        this.CBO_H_Ingreso1.addItem("12");
        this.CBO_H_Ingreso1.addItem("13");
        this.CBO_H_Ingreso1.addItem("14");
        this.CBO_H_Ingreso1.addItem("15");
        this.CBO_H_Ingreso1.addItem("16");
        this.CBO_H_Ingreso1.addItem("17");
        this.CBO_H_Ingreso1.addItem("18");
        this.CBO_H_Ingreso1.addItem("19");
        this.CBO_H_Ingreso1.addItem("20");
        this.CBO_H_Ingreso1.addItem("21");
        this.CBO_H_Ingreso1.addItem("22");
        this.CBO_H_Ingreso1.addItem("23");
        this.CBO_H_Ingreso1.addItem("24");

        this.CBO_H_Ingreso2.removeAllItems();

        this.CBO_H_Ingreso2.addItem(":00");
        this.CBO_H_Ingreso2.addItem(":15");
        this.CBO_H_Ingreso2.addItem(":30");
        this.CBO_H_Ingreso2.addItem(":45");

        this.CBO_H_Salida_1.removeAllItems();

        this.CBO_H_Salida_1.addItem("1");
        this.CBO_H_Salida_1.addItem("2");
        this.CBO_H_Salida_1.addItem("3");
        this.CBO_H_Salida_1.addItem("4");
        this.CBO_H_Salida_1.addItem("5");
        this.CBO_H_Salida_1.addItem("6");
        this.CBO_H_Salida_1.addItem("7");
        this.CBO_H_Salida_1.addItem("8");
        this.CBO_H_Salida_1.addItem("9");
        this.CBO_H_Salida_1.addItem("10");
        this.CBO_H_Salida_1.addItem("11");
        this.CBO_H_Salida_1.addItem("12");
        this.CBO_H_Salida_1.addItem("13");
        this.CBO_H_Salida_1.addItem("14");
        this.CBO_H_Salida_1.addItem("15");
        this.CBO_H_Salida_1.addItem("16");
        this.CBO_H_Salida_1.addItem("17");
        this.CBO_H_Salida_1.addItem("18");
        this.CBO_H_Salida_1.addItem("19");
        this.CBO_H_Salida_1.addItem("20");
        this.CBO_H_Salida_1.addItem("21");
        this.CBO_H_Salida_1.addItem("22");
        this.CBO_H_Salida_1.addItem("23");
        this.CBO_H_Salida_1.addItem("24");

        this.CBO_H_Salida2.removeAllItems();

        this.CBO_H_Salida2.addItem(":00");
        this.CBO_H_Salida2.addItem(":15");
        this.CBO_H_Salida2.addItem(":30");
        this.CBO_H_Salida2.addItem(":45");

    }

    //CREAR METODO LOCAL : LEER_TIPOHABITACION()
    void Leer_TipoHabitacion() {
        //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO
        File Leer_Doc = new File(Directorio_Habitacion);
        //DECLARAR VARIABLE DE TIPO CONTADOR
        int Fila = 0;

        //CREAR UN CONTROLADOR DE ERROR 
        try {
            //LEER DOCUMENTO (NOMBRE DEL ARCHIVO)
            Scanner Linea = new Scanner(Leer_Doc);

            //CREAR ESTRUCTURA REPETITIVA(BUCLE) WHILE(LEER LINEAS DE TEXTO DEL ARCHIVO)
            while (Linea.hasNextLine()) {
                //LEER LOS DATOS DEL ARCHIVO DE TEXTO
                TipoHabitacion[Fila][0] = Linea.nextLine();
                TipoHabitacion[Fila][1] = Linea.nextLine();

                //INCREMENTAR VALOR DE LA VARIABLE FILA
                Fila++;
            }
        } catch (Exception Error) {
            //MOSTRAR  MENSAJE DE EEROR
            JOptionPane.showMessageDialog(null, "Error: " + Error.getMessage(),
                    this.getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR METODO CARGAR IMAGEN
    void Cargar_TipoHabitacion(String Imagen) {
        ImageIcon Foto = new ImageIcon("src/TipoHabitacion/" + Imagen);

        //ESTABLECER ICONO
        Icon Icono = new ImageIcon(Foto.getImage().getScaledInstance(
                this.Lb_Foto.getWidth(), this.Lb_Foto.getHeight(),
                Image.SCALE_DEFAULT));

        //ASIGNAR IMAGEN AL CONTROL: JLABEL
        this.Lb_Foto.setIcon(Icono);

        //REPRESENTAR IMAGEN
        this.repaint();
    }

    //CREAR EL METODO LOCAL CARGAR DATOS
    void Cargar_Datos(String NombreProducto) {
        //CREAR UNA ESTRUCTURA REPETITIVA (BUCLE) 
        for (int Fila = 0; Fila < TipoHabitacion.length; Fila++) {
            //EVALUAR SI EL NOMBRE DEL PRODUCTO SELECCIONADO EXISTE DENTRO DE LA MATRIZ
            if (NombreProducto.equals(TipoHabitacion[Fila][0])) {
                //OBTENER EL NOMBRE DE LA IMAGEN
                this.Cargar_TipoHabitacion(TipoHabitacion[Fila][1]);

                //FIN DEL BUCLE
                break;
            }
        }
    }

    //CREAR METODO LOCAL Agregar_Producto
//    void Agregar_Producto() {
//        //DECLARAR VARIABLE DE TIPO ENTERO
//        int Rpta;
//
//        //EVALUAR SI EXISTE EL PRODUCTO SELECCIONADO DENTRO DE LA LISTA
//        if (ListaProd.contains(this.CBO_TipoHabitacion.getSelectedItem().toString()) == true) {
//            //MOSTRAR MENSAJE DE ADVERTENCIA
//            JOptionPane.showMessageDialog(null,
//                    "El Producto Seleccionado ya Existe en la Lista Productos",
//                    this.getTitle(), JOptionPane.WARNING_MESSAGE);
//        } else {
//            //  MOSTRAR EL MENSAJE  DE CONFIRMACION
//            Rpta = JOptionPane.showConfirmDialog(null,
//                    "¿Desea Agregar el Producto?", this.getTitle(),
//                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//
//            // EVALUAR SI RPTA ES SI 
//            if (Rpta == 0) {
//                //AGREGAR LOS ELEMENTOS A LA LISTAS}
//                ListaProd.add(ListaProd.size(), this.CBO_TipoHabitacion.getSelectedItem().toString());
//
//            }
//        }
//    }
    //Crear metodo solo numeros
    void Solo_Numeros(java.awt.event.KeyEvent evt) {
        //CREAR CONTROLADOR DE ERROR
        try {
            //DECLARAR VARAIBLE TIPO CHAR(CARACTER)
            char Caracter;

            //OBTENER EL CARACTER TIPEADO
            Caracter = evt.getKeyChar();

            //EVALUAR SI EL CARACTER ESTA FUERA DE RANGO DEL 0  AL 9
            if (Caracter < '0' || Caracter > '8') {
                //DESHABILITAR TECLAS
                evt.consume();
            }
        } catch (Exception Error) {

        }
    }

    //CREAR EL METODO LEER_CLIENTES
    void Leer_Clientes() {
        //DECLARAR UNA VARIABLE TIPO SCANNER
        Scanner Linea;

        //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO
        File Leer_Doc = new File(Directorio_Clientes);

        //DECLARAR UNA VARIABLE DE TIPO CONTADOR
        int Fila = 0;

        //CREAR CONTROLADOR DE ERROR
        try {
            //LEER DOCUMENTO(NOMBRE DEL ARCHIRVO)
            Linea = new Scanner(Leer_Doc);

            //CREAR ESTRUCTURA REPETITIVA
            while (Linea.hasNextLine()) {
                //LEER LINEAS
                Linea.nextLine();

                //INCREMENTAR EL VALOR DE LA VARIABLE
                Fila++;
            }
            //ESTABLECER VALOR DE LA FILA
            Fila = (Fila / 8);

            //ASIGNAR TAMAÑO DE LA MATRIZ DE DATOS
            Clientes = new String[Fila][8];

            //VOLVER A ASIGNAAR EL VALOR 0 
            Fila = 0;

            //LEER DOCUMENTO(NOMBRE DEL ARCHIRVO)
            Linea = new Scanner(Leer_Doc);

            //CREAR ESTRUCTURA REPETITIVA LEER LINEAS DE TEXTO DEL ARCHIVO
            while (Linea.hasNextLine()) {
                //LEER Y ASIGNAR DATOS DEL ARCHIVO DE TEXTO A LA MATRIZ DE DATOS
                Clientes[Fila][0] = Linea.nextLine();
                Clientes[Fila][1] = Linea.nextLine();
                Clientes[Fila][2] = Linea.nextLine();
                Clientes[Fila][3] = Linea.nextLine();
                Clientes[Fila][4] = Linea.nextLine();
                Clientes[Fila][5] = Linea.nextLine();
                Clientes[Fila][6] = Linea.nextLine();
                Clientes[Fila][7] = Linea.nextLine();

                //INCREMENTAR EL VALOR DE LA VARIABLE
                Fila++;
            }
        } catch (Exception Error) {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null, "Error" + Error.getMessage(), this.getTitle(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR METODO BUSCAR_CLIENTE
    void Buscar_Cliente(String Ruc) {
        //CREAR UNA VARIABLE TIPO ENTERO
        int Fila = 0, FilaEncontrada = 0;

        //CREAR ESTRUCTURA REPETITIVA
        while (Fila < Clientes.length) {
            //CREAR CONDICION PARA EVALUAR SI EL RUC ESPECIFICADO EXISTE DENTRO DE LA MATRIZ
            if (Ruc.equals(Clientes[Fila][4])) //4 ES LA COLUMNA DEL NUMERO RUC
            {
                //OBTENER DATOS DE LA MATRIZ DE DATOS Y PASAR DATOS A LOS CONTROLADORES
                this.Lb_CodCliente.setText(Clientes[Fila][0]);
                this.Lb_Apellidos_Cliente.setText(Clientes[Fila][1]);
                this.Lb_Nombre_Cliente.setText(Clientes[Fila][2]);
                this.Lb_Nacionalidad_Cliente.setText(Clientes[Fila][5]);
                this.Lb_Distrito_Cliente.setText(Clientes[Fila][6]);
                this.Lb_Direccion_Cliente.setText(Clientes[Fila][7]);

                //ASIGNAR VALOR A LA VARIABLE FILAENCONTRADA DE 1
                FilaEncontrada = 1;

                //FIN DEL BUCLE
                break;
            }

            //INCREMENTAR EL VALOR DE LA FILA
            Fila++;
        }
        //EVALUAR SI EL VSALOR DE LA VARIABLE FILAENCONTRADA ES IGUAL 0
        if (FilaEncontrada == 0) {
            JOptionPane.showMessageDialog(null, "El Cliente con el N° de Doc Identidad " + this.TXTDoc_Identidad_Cliente.getText()
                    + ",No Existe", this.getTitle(), JOptionPane.ERROR_MESSAGE);

            //LIMPIAR CONTROLES
            this.Lb_CodCliente.setText("");
            this.Lb_Apellidos_Cliente.setText("");
            this.Lb_Nombre_Cliente.setText("");
            this.Lb_Nacionalidad_Cliente.setText("");
            this.Lb_Distrito_Cliente.setText("");
            this.Lb_Direccion_Cliente.setText("");

            //UBICAR CURSOR EN EL CONTROL TXT RUC_CLIENTE
            this.TXTDoc_Identidad_Cliente.requestFocus();

        }
    }

    //Crear el método :autoAjustar Columnas
    void AutoAjustar_Columnas() {
        //Establer Anchos de Columnas
        //Definir el Tamaño de cado Columna del control (JTable):JTable_RegistroNotas
        int[] Anchos = {50, 200, 200, 150, 150, 100, 100, 100, 200, 250, 100};

        //Recorrer el numero de columnas del objeto JTable 
        for (int Columna = 0; Columna < this.jTable_RegistroCliente.getColumnCount(); Columna++) {
            //Establecer el Ancho de Columna para cada columna del JTable
            this.jTable_RegistroCliente.getColumnModel().getColumn(Columna).setPreferredWidth(Anchos[Columna]);
        }
        //Establecer el Autorisade de tamaño del jtable
        this.jTable_RegistroCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //Mostrar las barrass de desplazamiento: Vertical y Horizontal
        this.jTable_RegistroCliente.setShowVerticalLines(true);
        this.jTable_RegistroCliente.setShowHorizontalLines(true);
    }

    //Crear Método Cargar_Fila
    void Cargar_Fila() {
        //DECLARAR UNA VARIABLE DE TIPO ENTERO, OBTENER FILA SELECCIONADA DEL CONTROL JTABLE
        int Seleccion = this.jTable_RegistroCliente.getSelectedRow();

        //Cargar Datos en Controles
        this.Lb_CodCliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 0).toString());
        this.Lb_Apellidos_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 1).toString());
        this.Lb_Nombre_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 2).toString());
        this.CBO_DocIdentidad.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 3).toString());
        this.TXTDoc_Identidad_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 4).toString());
        this.Lb_Nacionalidad_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 5).toString());
        this.Lb_Distrito_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 6).toString());
        this.Lb_Direccion_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 7).toString());
        this.CBO_Sede.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 10).toString());
        this.CBO_TipoHabitacion.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 11).toString());

    }

    //Crear el metodo local: guardar fichero
    void Guardar_Fichero() {
        //crear controlador de Errores
        try {
            //Establecer ruta del Archivo de texto a escribir secuencia de datos
            FileWriter Guardar = new FileWriter(Directorio);

            //Recorrer filas de modelos de datos
            for (int i = 0; i < this.jTable_RegistroCliente.getRowCount(); i++) {
                //Escribir secuencia de datos
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
                //    Guardar.write(Modelo.getValueAt(i,12).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,13).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,14).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,15).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,16).toString()+"\n");
                //   Guardar.write(Modelo.getValueAt(i,17).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,18).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,19).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,20).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,21).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,22).toString()+"\n");
                //    Guardar.write(Modelo.getValueAt(i,23).toString()+"\n");

            }
            //Cerrar el Archivo de Texto
            Guardar.close();

            //Mensaje de informaccio
            JOptionPane.showMessageDialog(null, "Registro Guardado con éxito", this.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception Error) {
            //mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "Error" + Error.getMessage(), this.getTitle(),
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    //Crear metodo leer datos
    void Leer_Datos() {
        //Declarar variable de tipo string(cadena de texto)
        String Codigo, Apellido, Nombres, Tipo_DocIdentidad, N_DocIdentidad, Nacionalidad, Distrito, Direccion, Codigo_Empleado, Datos_Empleado, Sede, Tipo_Habitacion;

        //Obtener el Nombre del Archivo de Texto
        File Leer_Doc = new File(Directorio);

        //Crear Controlador de Error
        try {
            //Leer Documento(Nombre del Archivo  
            Scanner Linea = new Scanner(Leer_Doc);

            //Crear Estructura repetitiva(Bucle):while(Leer lineas del archivo)
            while (Linea.hasNextLine()) {

                //Leer Documento(Nombre del Archivo                  
                Codigo = Linea.nextLine();
                Apellido = Linea.nextLine();
                Nombres = Linea.nextLine();
                Tipo_DocIdentidad = Linea.nextLine();
                N_DocIdentidad = Linea.nextLine();
                Nacionalidad = Linea.nextLine();
                Distrito = Linea.nextLine();
                Direccion = Linea.nextLine();
                Codigo_Empleado = Linea.nextLine();
                Datos_Empleado = Linea.nextLine();
                Sede = Linea.nextLine();
                Tipo_Habitacion = Linea.nextLine();

                //Agregar Filas al Metodoo de datos del jtable
                Modelo.addRow(new Object[]{Codigo, Apellido, Nombres, Tipo_DocIdentidad, N_DocIdentidad, Nacionalidad, Distrito, Direccion, Codigo_Empleado,
                    Datos_Empleado, Sede, Tipo_Habitacion});
            }
        } catch (Exception Error) {
            //Montrar Error
            JOptionPane.showMessageDialog(null, "Error" + Error.getMessage(), this.getTitle(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR EL METODO SALIR
    void Salir() {
        //DECLARAMOS LA VARIABLE DE TIPO ENTERO
        int Rpta;

        //MOSTRAR MENSAJE DE CONFIRMACION
        Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Salir de la Ventana?", this.getTitle(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        //EVALUAR SI EL USUARIO RESPONDIO DE FORMA AFIRMATIVA
        if (Rpta == 0) {
            //CERRRAR FORMULARIO
            System.exit(0);
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TITULO DEL FORMULARIO
        this.setTitle("ALQUILER DE HABITACIÓN");

        //INVOCAR AL METODO LIMPIAR
        this.Limpiar();
        this.Cargar_Datos_CBO();
        this.Leer_Clientes();
        this.Leer_TipoHabitacion();
        this.AutoAjustar_Columnas();
        //  this.Cargar_TipoHabitacion();
        //this.Cargar_TipoHabitacion(Directorio_Habitacion);

        CBO_DocIdentidad.setSelectedIndex(0);
    }//GEN-LAST:event_formWindowOpened

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        // INVOCAR AL METODO SALIR
        this.Salir();
    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void TXTDoc_Identidad_ClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTDoc_Identidad_ClienteKeyTyped
        // TODO add your handling code here:
        this.Solo_Numeros(evt);
    }//GEN-LAST:event_TXTDoc_Identidad_ClienteKeyTyped

    private void BTN_BuscarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BuscarCliente1ActionPerformed

        ListaCliente = Cliente_dt.getBuscarCliente(this.TXTDoc_Identidad_Cliente.getText());

        if (ListaCliente != null && ListaCliente.size() > 0) {

            ClienteEntity DataEncontrada = ListaCliente.get(0);

            Lb_Apellidos_Cliente.setText(DataEncontrada.getApellidos());
            Lb_Nombre_Cliente.setText(DataEncontrada.getNombres());
            Lb_Direccion_Cliente.setText(DataEncontrada.getDireccion());
            Lb_Nacionalidad_Cliente.setText(DataEncontrada.getCodNac());
            pl_InformacionCliente.setVisible(true);
        } else {
            pl_InformacionCliente.setVisible(false);
            JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso", 0);
        }
//        String dato = CBO_DocIdentidad.getSelectedItem().toString();
//
//        if (CBO_DocIdentidad.getSelectedIndex() != 0) {
//
//            this.Buscar_Cliente(this.TXTDoc_Identidad_Cliente.getText());
//
//            pl_InformacionCliente.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "error", "Error", 0);
//        }

        // if(dato.equals("")){
        //    JOptionPane.showMessageDialog(null,"error","Error",0);
        //}
//                    , this.getTitle(),
//                    JOptionPane.INFORMATION_MESSAGE);       }
//         if (this.CBO_DocIdentidad.getSelectedIndex() == 0) {
//            //Monstrar mensaje de informacion
//            JOptionPane.showMessageDialog(null,"Debe Seleccionar el Nombre del Documento de Identidad"
//                    , this.getTitle(),
//                    JOptionPane.INFORMATION_MESSAGE);
//
//            //Ubicar cursor en el control cbo_Curso
//            this.CBO_DocIdentidad.requestFocus();
//        }

    }//GEN-LAST:event_BTN_BuscarCliente1ActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        // TODO add your handling code here:
        // Declarar una vaariable de tipo entero
        int Rpta;

        //Evaluar si el total de caracteres del control TXTDoc_Identidad_Cliente es mayor a 0
        if (this.TXTDoc_Identidad_Cliente.getText().length() > 0) {
            //Mostrar mensaje de confirmacion
            Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Agregar los datos del cliente?", this.getTitle(),
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //Evaluar si el usuario confirmo su respuesta se(0)
            if (Rpta == 0) {
                //Crear un Vetor de Datos (String)
                String[] informacion = new String[12];

                //Asignar los Valores al Vector de Datos
                informacion[0] = this.Lb_CodCliente.getText();
                informacion[1] = this.Lb_Apellidos_Cliente.getText();
                informacion[2] = this.Lb_Nombre_Cliente.getText();
                informacion[3] = this.CBO_DocIdentidad.getSelectedItem().toString();
                informacion[4] = this.TXTDoc_Identidad_Cliente.getText();
                informacion[5] = this.Lb_Nacionalidad_Cliente.getText();
                informacion[6] = this.Lb_Distrito_Cliente.getText();
                informacion[7] = this.Lb_Direccion_Cliente.getText();

                //Agregar Datos al DefaultTableModel(Modelo Tabla)Modelo
                Modelo.addRow(informacion);

                //Montrar mensaje de informacion
                JOptionPane.showMessageDialog(null, "Datos Registrados con Éxito", this.getTitle(),
                        JOptionPane.ERROR_MESSAGE);

                //Invocar al evento
                //  this.BTN_NuevoActionPerformed(null);
            }
        } else {
            //Montrar mensaje erros
            JOptionPane.showMessageDialog(null,
                    "Imposible Registrar Datos.....\nNo Existe Imformacion sobre el Cliente",
                    this.getTitle(), JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void jTable_RegistroClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_RegistroClienteMouseClicked

        this.Cargar_Fila();
    }//GEN-LAST:event_jTable_RegistroClienteMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void TXTImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTImporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTImporteActionPerformed

    private void CBO_TipoHabitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_TipoHabitacionItemStateChanged

        // EVALUARA SI SE HA SELECCIONADO UN ELEMENTO DEL COMBOBOX
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //INVOCAR AL METODO CARGAR DATOS
            this.Cargar_Datos(this.CBO_TipoHabitacion.getSelectedItem().toString());
        }
    }//GEN-LAST:event_CBO_TipoHabitacionItemStateChanged

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrm_Alquiler_Habitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrm_Alquiler_Habitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrm_Alquiler_Habitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrm_Alquiler_Habitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //  new JFrm_Alquiler_Habitacion().setVisible(true);
                //CREAR CONTROLADOR DE ERROR
                try {
                    //CREAR UN OBJETO QUE INSTANCIE EL FORMULARIO
                    JFrm_Alquiler_Habitacion formulario = new JFrm_Alquiler_Habitacion();

                    //EVITAR QUE EL USUARIO REDIMENSIONE EL TAMAÑO DE LA VENTANA
                    formulario.setResizable(false);

                    //UBICAR EL FORMULARIO EN EL CENTRO DE LA PANTALLA
                    formulario.setLocationRelativeTo(null);

                    //DESHABILITAR EL BOTON CERRA(X) DEL FORMULARIO
                    // formulario.setDefaultCloseOperation(0);
                    //MOSTRAR FORMULARIO
                    formulario.setVisible(true);
                } catch (Exception Error) {
                    //MOSTRAR MENSAJE DE ERROR
                    JOptionPane.showMessageDialog(null, Error.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Anular;
    private javax.swing.JButton BTN_BuscarCliente1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_NuevoCliente;
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JComboBox<String> CBO_DocIdentidad;
    private javax.swing.JComboBox<String> CBO_H_Ingreso1;
    private javax.swing.JComboBox<String> CBO_H_Ingreso2;
    private javax.swing.JComboBox<String> CBO_H_Salida2;
    private javax.swing.JComboBox<String> CBO_H_Salida_1;
    private javax.swing.JComboBox<String> CBO_N_Habitacion;
    private javax.swing.JComboBox<String> CBO_Piso;
    private javax.swing.JComboBox<String> CBO_Sede;
    private javax.swing.JComboBox<String> CBO_TipoHabitacion;
    private javax.swing.ButtonGroup GpPorTarifa;
    private javax.swing.JLabel Lb_Apellidos_Cliente;
    private javax.swing.JLabel Lb_CodCliente;
    private javax.swing.JLabel Lb_Direccion_Cliente;
    private javax.swing.JLabel Lb_Distrito_Cliente;
    private javax.swing.JLabel Lb_Foto;
    private javax.swing.JLabel Lb_Nacionalidad_Cliente;
    private javax.swing.JLabel Lb_Nombre_Cliente;
    private javax.swing.JTextField TXTDoc_Identidad_Cliente;
    private javax.swing.JTextField TXTImporte;
    private javax.swing.JTextField TXTTarifa_Noche;
    private com.toedter.calendar.JDateChooser jD_Fec_Inicio;
    private com.toedter.calendar.JDateChooser jD_Fec_Termino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_RegistroCliente;
    private javax.swing.JPanel jp_CLiente;
    private javax.swing.JPanel jp_foder;
    private javax.swing.JPanel jp_separador;
    private javax.swing.JPanel jp_separador2;
    private javax.swing.JPanel pl_InformacionCliente;
    // End of variables declaration//GEN-END:variables
}
