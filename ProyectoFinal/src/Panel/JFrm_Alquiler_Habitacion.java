package Panel;

import Datos.*;
import Entidades.*;
import com.sun.accessibility.internal.resources.accessibility;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;

public class JFrm_Alquiler_Habitacion extends javax.swing.JPanel {

    //Llenar el combobox **docIdentidad** segun la base de datos
    DocIdentidadDB docIdentidad_dt = new DocIdentidadDB();
    ArrayList<DocIdentidadEntity> ListaDocIDentidad = new ArrayList<>();

    //Invocar a sql ***clientes***
    ClienteDB Cliente_dt = new ClienteDB();
    ArrayList<ClienteEntity> ListaCliente = new ArrayList<>();

    //Llenar el combobox **sede** segun la base de datos
    SedeDB Sede_db = new SedeDB();
    ArrayList<SedeEntity> ListaSede = new ArrayList<>();

    //Llenar el combobox **Habitacion** segun la base de datos
    Tipo_HabitacionDB Tipo_Habitaciondb = new Tipo_HabitacionDB();
    ArrayList<Tipo_HabitacionEntity> ListaTipo_Habitacion = new ArrayList<>();

    //Llenar el combobox **Habitacion** segun la base de datos
    HabitacionDB Habitaciondb = new HabitacionDB();
    ArrayList<HabitacionEntity> ListaHabitacion = new ArrayList<>();

    //Llenar el combobox **Piso** segun la base de datos
    NivelDB Niveldb = new NivelDB();
    ArrayList<NivelEntity> ListaNivel = new ArrayList<>();

    //Llenar el combobox **N° Habitacion**segun la base de datos
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

    /**
     * Creates new form JFrm_Alquiler_Habitacion
     */
    public JFrm_Alquiler_Habitacion() {
        initComponents();

        BodyPanel.setPreferredSize(new Dimension(1058, 652));
        jp_foder.setPreferredSize(new Dimension(400, 72));
        jp_CLiente.setPreferredSize(new Dimension(523, 309));
        pl_InformacionCliente.setPreferredSize(new Dimension(489, 182));
        pl_InformacionCliente.setVisible(false);

        //combobox docIdentidad
        ListaDocIDentidad = docIdentidad_dt.getCodDoc_IdentidadItems();

        for (DocIdentidadEntity item : ListaDocIDentidad) {
            CBO_DocIdentidad.addItem(item.getTipo_Doc_Identidad());
        }
        CBO_DocIdentidad.setSelectedIndex(-1);

        //combobox Tipo Habitacion
        ListaTipo_Habitacion = Tipo_Habitaciondb.getCodTipo_HabitacionItems();

        for (Tipo_HabitacionEntity item : ListaTipo_Habitacion) {
            CBO_TipoHabitacion.addItem(item.getDescripcion_TipoHabitacion());
        }
        CBO_TipoHabitacion.setSelectedIndex(-1);

//        ListaHabitacion = Tipo_Habitaciondb.GetCargarHabitacionesTarifa();
//
//        for (HabitacionEntity item : ListaHabitacion) {
//            CBO_TipoHabitacion.addItem(item.getNumHabitacion());
//        }
//        CBO_TipoHabitacion.setSelectedIndex(-1);
//        
//        
        ListaNivel = Niveldb.getCodNivelItems();

        for (NivelEntity item : ListaNivel) {
            CBO_Piso.addItem(item.getNivel_Piso());
        }
        CBO_Piso.setSelectedIndex(-1);

        //combobox N° Habitación
//        Listareserva_alquiler = reserva_alquilerdb.getCodDoc_IdentidadItems();
//
//        for (Reserva_AlquilerEntity item : Listareserva_alquiler) {
//            CBO_N_Habitacion.addItem(item.getNumAlquiler());
//        }
//        CBO_N_Habitacion.setSelectedIndex(-1);
        //Establecer el color de fondo del formulario
//        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        //Establecer un Nuevo Modelo de la clase :DEfaultTableModel
        //CREO LOS TITULOS DE LA TABLA  *****************************************************************
        Modelo = new DefaultTableModel();
        String titulos[] = {"Habitación", "Piso", "Tipo", "Estado"};
        Modelo.setColumnIdentifiers(titulos);
        jTable_RegistroCliente.setModel(Modelo);
//        ************************************************************************************************

//        Cargo La Base de Datos *********************************************************************
        HabitacionDB H_DB = new HabitacionDB();

        String CodSede = Utilidades.getM_UsuarioSistema().getCodSede();
//
        ArrayList<HabitacionEntity> HabitacionItems = H_DB.GetCargarHabitacionesParametro(CodSede, "NP002");
//
//        for (Reserva_AlquilerEntity item : Listareserva_alquiler) {
//            CBO_N_Habitacion.addItem(item.getNumAlquiler());
//        }
//        CBO_N_Habitacion.setSelectedIndex(-1);

        ArrayList<HabitacionEntity> ListaHabitaciones = H_DB.GetCargarHabitaciones();

//        ************************************************************************************************
//        Inserto a las Filas  ********************************************************************************    
        Object[] Fila = new Object[Modelo.getColumnCount()];

        for (HabitacionEntity item : ListaHabitaciones) {
            Fila[0] = item.getNumHabitacion();
            Fila[1] = item.getPiso();
            Fila[2] = item.getTipo();
            Fila[3] = item.getEstado_Habitacion();
            Modelo.addRow(Fila);
        }
        // ************************************************************************************************     

//        int cont = 0;
//        while (cont < ListaHabitaciones.size()) {
//
//            Fila[0] = ListaHabitaciones.get(cont).getNumHabitacion();
//            Fila[1] = ListaHabitaciones.get(cont).getPiso();
//            Fila[2] = ListaHabitaciones.get(cont).getTipo();
//            Fila[3] = ListaHabitaciones.get(cont).getEstado_Habitacion();
//            Modelo.addRow(Fila);
//            cont += 1;
//        }
        this.lb_FechaSalida.setText(this.Fecha_Actual());

        this.lb_FechaSalida.setText(this.Fecha_Actual());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnGp_Combo = new javax.swing.ButtonGroup();
        BodyPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_RegistroCliente = new javax.swing.JTable();
        jP_Imagen = new javax.swing.JPanel();
        Lb_Foto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        CBO_N_Habitacion = new javax.swing.JComboBox<>();
        CBO_TipoHabitacion = new javax.swing.JComboBox<>();
        CBO_Piso = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        rbt_Hora = new javax.swing.JRadioButton();
        rbt_Noche = new javax.swing.JRadioButton();
        TXT_Cantidad = new javax.swing.JTextField();
        lb_FechaSalida = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbComentario = new javax.swing.JLabel();
        lb_Precio = new javax.swing.JLabel();
        lb_Importe = new javax.swing.JLabel();
        lb_FechaIngreso1 = new javax.swing.JLabel();
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
        Lb_Ubigeo = new javax.swing.JLabel();
        jp_foder = new javax.swing.JPanel();
        BTN_Anular = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1054, 657));
        setMinimumSize(new java.awt.Dimension(1054, 657));

        BodyPanel.setBackground(new java.awt.Color(204, 204, 204));

        jPanel8.setBackground(new java.awt.Color(0, 102, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Habitacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jTable_RegistroCliente.setBackground(new java.awt.Color(204, 204, 204));
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
        jTable_RegistroCliente.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jTable_RegistroCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_RegistroClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_RegistroCliente);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        jP_Imagen.setBackground(new java.awt.Color(0, 102, 153));
        jP_Imagen.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Foto de la Habitación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

        Lb_Foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Foto.setMaximumSize(new java.awt.Dimension(50, 16));
        Lb_Foto.setMinimumSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout jP_ImagenLayout = new javax.swing.GroupLayout(jP_Imagen);
        jP_Imagen.setLayout(jP_ImagenLayout);
        jP_ImagenLayout.setHorizontalGroup(
            jP_ImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lb_Foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jP_ImagenLayout.setVerticalGroup(
            jP_ImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lb_Foto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de la Habitación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));

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
        CBO_N_Habitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_N_HabitacionActionPerformed(evt);
            }
        });
        CBO_N_Habitacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CBO_N_HabitacionKeyReleased(evt);
            }
        });

        CBO_TipoHabitacion.setBackground(new java.awt.Color(255, 255, 255));
        CBO_TipoHabitacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_TipoHabitacion.setForeground(new java.awt.Color(0, 0, 0));
        CBO_TipoHabitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_TipoHabitacionItemStateChanged(evt);
            }
        });
        CBO_TipoHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_TipoHabitacionActionPerformed(evt);
            }
        });

        CBO_Piso.setBackground(new java.awt.Color(255, 255, 255));
        CBO_Piso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CBO_Piso.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Piso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBO_PisoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Tarifa", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        BtnGp_Combo.add(rbt_Hora);
        rbt_Hora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbt_Hora.setForeground(new java.awt.Color(255, 255, 255));
        rbt_Hora.setText("Hora");
        rbt_Hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_HoraActionPerformed(evt);
            }
        });

        BtnGp_Combo.add(rbt_Noche);
        rbt_Noche.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rbt_Noche.setForeground(new java.awt.Color(255, 255, 255));
        rbt_Noche.setText("Noche");
        rbt_Noche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_NocheActionPerformed(evt);
            }
        });

        TXT_Cantidad.setBackground(new java.awt.Color(255, 255, 255));
        TXT_Cantidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TXT_Cantidad.setForeground(new java.awt.Color(0, 0, 0));
        TXT_Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_CantidadActionPerformed(evt);
            }
        });
        TXT_Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXT_CantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_CantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_CantidadKeyTyped(evt);
            }
        });

        lb_FechaSalida.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lb_FechaSalida.setForeground(new java.awt.Color(255, 255, 255));
        lb_FechaSalida.setText("Fecha I");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Cantidad:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Precio:");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("12:00PM");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("01:00PM");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Importe:");

        lbComentario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbComentario.setForeground(new java.awt.Color(255, 51, 51));
        lbComentario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbComentario.setText("El checking se realiza a la 1pm");

        lb_Precio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lb_Precio.setForeground(new java.awt.Color(0, 0, 0));
        lb_Precio.setText("0.00");

        lb_Importe.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lb_Importe.setForeground(new java.awt.Color(0, 0, 0));
        lb_Importe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Importe.setText("0.00");

        lb_FechaIngreso1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lb_FechaIngreso1.setForeground(new java.awt.Color(255, 255, 255));
        lb_FechaIngreso1.setText("Fecha I");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_FechaIngreso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbt_Hora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TXT_Cantidad)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(rbt_Noche, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_FechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Importe, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbt_Noche, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbt_Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TXT_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(lb_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_FechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_FechaIngreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Importe, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(CBO_TipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(CBO_Piso, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBO_N_Habitacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBO_TipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CBO_Piso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CBO_N_Habitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp_CLiente.setBackground(new java.awt.Color(0, 102, 153));
        jp_CLiente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

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
        TXTDoc_Identidad_Cliente.setText(" ");
        TXTDoc_Identidad_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTDoc_Identidad_ClienteKeyTyped(evt);
            }
        });

        BTN_NuevoCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        BTN_NuevoCliente.setForeground(new java.awt.Color(0, 0, 0));
        BTN_NuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/BtnAgregarClienteIcon.png"))); // NOI18N
        BTN_NuevoCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoClienteActionPerformed(evt);
            }
        });

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
        jLabel7.setText("Ubigeo");

        Lb_Ubigeo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Lb_Ubigeo.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Ubigeo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Ubigeo.setText("Lb_Ubigeo");

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
                            .addComponent(Lb_Ubigeo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(Lb_Ubigeo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(0, 21, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout BodyPanelLayout = new javax.swing.GroupLayout(BodyPanel);
        BodyPanel.setLayout(BodyPanelLayout);
        BodyPanelLayout.setHorizontalGroup(
            BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jp_foder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BodyPanelLayout.createSequentialGroup()
                        .addGroup(BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jp_CLiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jP_Imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        BodyPanelLayout.setVerticalGroup(
            BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BodyPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_CLiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jP_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_foder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(BodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    //CREAR FUNCION FECHA ACTUAL
    public String Fecha_Actual() {
        //DECLAR VARIABALE DE TIPO DATE
        Date Fecha = new Date();

        //DECLARAR UNA VARIABLE DE TIPO SIMPLEDATEFORMAT
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        //RETORNAR VALOR
        return formato.format(Fecha);

    }

    //CREAR FUNCION FECHA ACTUAL
    public String Correlativo() {
        //DECLAR VARIABALE DE TIPO DATE
        Date Fecha = new Date();

        //DECLARAR UNA VARIABLE DE TIPO SIMPLEDATEFORMAT
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyyHHmmss");

        //RETORNAR VALOR
        return formato.format(Fecha);

    }
    double Precio, Importe;

    //CREAR EL METODO MOSTRAR IMPORTE
    void Mostrar_Importe() {
        //DECLARAR UNA VARIABLE PARA ESTABLECER EL FORMATO
        DecimalFormat Num_Decimal = new DecimalFormat("0.00");
        //DECLARAR VARIABLES
        int Cant;

        //OBTENER VARIABLE        
        Precio = Double.parseDouble(this.lb_Precio.getText());
        Cant = Integer.parseInt(this.TXT_Cantidad.getText());

        //REALIZAR CALCULO
        Importe = (Precio * Cant);

        //MOSTRAR EL VALOR DEL IMPORTE
        this.lb_Importe.setText(Num_Decimal.format(Importe));
    }

   
    private void CBO_TipoHabitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_TipoHabitacionItemStateChanged

        //DECLARAR UNA VARIABLE PARA ESTABLECER EL FORMATO
//        DecimalFormat Num_Decimal = new DecimalFormat("0.00");
//        
//        //DECLARAR VARIABLES
//        int Cant;
//        double Precio, Importe;
//        
//        //OBTENER VARIABLE        
//        Precio = Double.parseDouble(this.lb_Precio.getText());
//        Cant = Integer.parseInt(this.TXT_Cantidad.getText());
//        
//        //REALIZAR CALCULO
//        Importe = (Precio*Cant);
//        
//        //MOSTRAR EL VALOR DEL IMPORTE
//        this.lb_Importe.setText(Num_Decimal.format(Importe));
//        String Habitacion=(String)CBO_TipoHabitacion.getSelectedItem();
//        if(Habitacion.equals("Simple")){
//            lb_Precio.setText("25.00");
//        
//        }
//        
        //        if(evt.getStateChange()== ItemEvent.SELECTED){
////            //LIMPIAR LOS TEXTOS DE LAS ETIQUETAS DE PRECIO E IMPORTE
//            this.lb_Precio.setText("0.00");
//            this.lb_Importe.setText("0.00");
//            
//            //INVOCAR AL METODO CARGAR DATOS
//            this.Mostrar_Importe();
//        
//            //UBICAR CURSOR EN EL CONTROL TXT CANTIDAD
//            this.TXT_Cantidad.setText("");
//            this.TXT_Cantidad.requestFocus();  
//        }
        // EVALUARA SI SE HA SELECCIONADO UN ELEMENTO DEL COMBOBOX
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //INVOCAR AL METODO CARGAR DATOS
//            this.Cargar_Datos(this.CBO_TipoHabitacion.getSelectedItem().toString());ERRORPANEL

        }

    }//GEN-LAST:event_CBO_TipoHabitacionItemStateChanged

    double PrecioHora = 0;
    private void rbt_HoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_HoraActionPerformed
//         EVALUARA SI SE HA SELECCIONADO UN ELEMENTO DEL JRadioButton

        //LIMPIAR LOS TEXTOS DE LAS ETIQUETAS DE PRECIO E INPORTE
        if (rbt_Hora.isSelected() == true) {

            lb_Precio.setText("" + PrecioHora);
        }


    }//GEN-LAST:event_rbt_HoraActionPerformed

    private void TXTDoc_Identidad_ClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTDoc_Identidad_ClienteKeyTyped
        // TODO add your handling code here:
        this.Solo_Numeros(evt);
    }//GEN-LAST:event_TXTDoc_Identidad_ClienteKeyTyped

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

    private void BTN_BuscarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BuscarCliente1ActionPerformed

        //Invocar a la base de datos cliente
        ListaCliente = Cliente_dt.getBuscarCliente(this.TXTDoc_Identidad_Cliente.getText());

        if (ListaCliente != null && ListaCliente.size() > 0) {

            ClienteEntity DataEncontrada = ListaCliente.get(0);

            Lb_CodCliente.setText(DataEncontrada.getCodCliente());
            Lb_Apellidos_Cliente.setText(DataEncontrada.getApellidos());
            Lb_Nombre_Cliente.setText(DataEncontrada.getNombres());
            Lb_Direccion_Cliente.setText(DataEncontrada.getDireccion());

            //Invocar a la conexion nacionalidadDB y NacionalidadEntity
            NacionalidadDB Nac_DB = new NacionalidadDB();

            ArrayList<NacionalidadEntity> Listanacionalidad = Nac_DB.GetBuscarNacionalidad(DataEncontrada.getCodNac());

            if (Listanacionalidad != null && Listanacionalidad.size() > 0) {

                NacionalidadEntity DataEncontradaNAC = Listanacionalidad.get(0);
                Lb_Nacionalidad_Cliente.setText(DataEncontradaNAC.getGentilicio_Nac());
            }

            //Invocar a la conexion UbigeoDB y UbigeoEntity
            UbigeoDB Ubigeo_DB = new UbigeoDB();
            ArrayList<UbigeoEntity> Listaubigeo = Ubigeo_DB.getBuscarUbigeo(DataEncontrada.getCodUbigeo());

            if (Listaubigeo != null && Listaubigeo.size() > 0) {

                UbigeoEntity DataEncontradaUbi = Listaubigeo.get(0);
                Lb_Ubigeo.setText(DataEncontradaUbi.getDistrito() + " - " + DataEncontradaUbi.getProvincia() + " - " + DataEncontradaUbi.getDepartamento());
            }

            pl_InformacionCliente.setVisible(true);
        } else {
            //Evitar que aparezca el panel
            pl_InformacionCliente.setVisible(false);
            JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso", 0);
        }
    }//GEN-LAST:event_BTN_BuscarCliente1ActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        AlquilerDB DB_AlQ = new AlquilerDB();

        AlquilerEntity Item_Data = new AlquilerEntity();

        Date d = new Date();
        String m_Correlativo = this.Correlativo();

        Item_Data.setNumAlquiler(m_Correlativo);
        Item_Data.setCodSede(Utilidades.getM_UsuarioSistema().getCodSede());
        Item_Data.setCodCliente(Lb_CodCliente.getText());
        Item_Data.setCodEmpleado(Utilidades.getM_UsuarioSistema().getCodEmpleado());
        Item_Data.setNumHabitacion(m_NumHabitacion);
        Item_Data.setFec_Alquiler(d);
        Item_Data.setFec_TerminoAlquiler(d);
        Item_Data.setImporte(Importe);

        DB_AlQ.Save(Item_Data);

        JOptionPane.showMessageDialog(this, "Se genero el alquiler con codigo: " + m_Correlativo, "Mensaje de Alerta", JOptionPane.WARNING_MESSAGE);

        int Rpta;

        //Evaluar si el total de caracteres del control TXTDoc_Identidad_Cliente es mayor a 0
        if (this.TXTDoc_Identidad_Cliente.getText().length() > 0) {
            //Mostrar mensaje de confirmacion
//            Rpta = JOptionPane.showConfirmDialog(null, "¿Desea Agregar los datos del cliente?", this.getTitle(),
//                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);ERRORPANEL

            //Evaluar si el usuario confirmo su respuesta se(0)
            Rpta = 0;
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
                informacion[6] = this.Lb_Ubigeo.getText();
                informacion[7] = this.Lb_Direccion_Cliente.getText();

                //Agregar Datos al DefaultTableModel(Modelo Tabla)Modelo
                Modelo.addRow(informacion);

                //Montrar mensaje de informacion
//                JOptionPane.showMessageDialog(null, "Datos Registrados con Éxito", this.getTitle(),
//                    JOptionPane.ERROR_MESSAGE);ERRORPANEL
                //Invocar al evento
                //  this.BTN_NuevoActionPerformed(null);
            }
        } else {
            //Montrar mensaje erros
//            JOptionPane.showMessageDialog(null,
//                "Imposible Registrar Datos.....\nNo Existe Imformacion sobre el Cliente",
//                this.getTitle(), JOptionPane.ERROR_MESSAGE);ERRORPANEL

        }
        //Evaluar si el codigo del Estudiante ha sido generado
        if (this.CBO_DocIdentidad.getSelectedIndex() == 0) {
            //Mostrar Mensaje de informacion
//            JOptionPane.showMessageDialog(null, "Debe Seleccionar el Documento de Identidad", this.getTitle(),
//                JOptionPane.INFORMATION_MESSAGE);ERRORPANEL
            this.CBO_DocIdentidad.requestFocus();

        } //Evaluar si el Usuario a Ingresasdo los Apellidos del Estudiante
        //        else if (this.TXTDoc_Identidad_Cliente.getText().length() == 0) {
        //            //Mostrar Mensaje de Información
        //            JOptionPane.showMessageDialog(null, "Debe Ingresar los Apellidos de Estudiante", this.getTitle(),
        //                    JOptionPane.INFORMATION_MESSAGE);
        //
        //            //Ubicar el Cursor en el Control:TXTAPELLIDOS
        //            this.TXTApellidos.requestFocus();
        //        } //Evaluar si el Usuario a Ingresado los Nombres del Estudiante
        //        else if (this.TXTNombres.getText().length() == 0) {
        //            //Montrar Mensaje de Informacion
        //            JOptionPane.showMessageDialog(null, "Deebe Ingresar los Nombres del Estudiante", this.getTitle(),
        //                    JOptionPane.INFORMATION_MESSAGE);
        //
        //            //Ubicar cursor en el control txtNombre
        //            this.TXTNombres.requestFocus();
        //        } //Evaluar si el Usuario a Seleccionado uno de los cursos
        //        else if (this.CBO_Curso.getSelectedIndex() == 0) {
        //            //Monstrar mensaje de informacion
        //            JOptionPane.showMessageDialog(null, "Debe Seleccionar el Nombre del Curso a Evaluar", this.getTitle(),
        //                    JOptionPane.INFORMATION_MESSAGE);
        //
        //            //Ubicar cursor en el control cbo_Curso
        //            this.CBO_Curso.requestFocus();
        //        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void jTable_RegistroClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_RegistroClienteMouseClicked

        // this.Cargar_Fila();
    }//GEN-LAST:event_jTable_RegistroClienteMouseClicked

    double PrecioNoche = 0;
    private void rbt_NocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_NocheActionPerformed

        if (rbt_Noche.isSelected() == true) {
            lb_Precio.setText("" + PrecioNoche);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_rbt_NocheActionPerformed

    void Cargar_Imagen(String CodTipoHabitacion) {
       String Imagen = "";

     
        switch (CodTipoHabitacion) {
            case "Hab001":
                Imagen = "Simple.jpeg";
                break;
            case "Hab002":
                Imagen = "Comun.jpeg";
                break;

            case "Hab003":
                Imagen = "Premium.jpeg";
              break;

        }
        ImageIcon Foto = new ImageIcon("src/TipoHabitacion/" +Imagen);

        
        
        Lb_Foto.setIcon(Foto);
        //ESTABLECER ICONO
//        if (Foto != null) {
//
//            Icon Icono = new ImageIcon(Foto.getImage().getScaledInstance(
//                    this.Lb_Foto.getWidth(), this.Lb_Foto.getHeight(),
//                    Image.SCALE_DEFAULT));
//
//            //ASIGNAR IMAGEN AL CONTROL: JLABEL
//            this.Lb_Foto.setIcon(Icono);
//
//            //REPRESENTAR IMAGEN
//            this.repaint();
//        }
    }
    private void CBO_TipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_TipoHabitacionActionPerformed

        String CodSede_Item = Utilidades.getM_UsuarioSistema().getCodSede();

        int index_Piso = CBO_Piso.getSelectedIndex();
        int index_Tipo = CBO_TipoHabitacion.getSelectedIndex();

        String CodNivel_Item, Tipo_Item;

        if (index_Tipo >= 0) {
           Cargar_Imagen(ListaTipo_Habitacion.get(index_Tipo).getCodTipo_Habitacion());
        }

        if (index_Piso >= 0 && index_Tipo >= 0) {

            CodNivel_Item = ListaNivel.get(index_Piso).getCodNivel();

            Tipo_Item = ListaTipo_Habitacion.get(index_Tipo).getCodTipo_Habitacion();

            ArrayList<HabitacionEntity> lst_Habitaciones = Habitaciondb.GetCargarHabitacionesNum(CodSede_Item, CodNivel_Item, Tipo_Item);

            CBO_N_Habitacion.removeAllItems();
            for (HabitacionEntity item : lst_Habitaciones) {
                CBO_N_Habitacion.addItem(item.getNumHabitacion());
            }
        }


    }//GEN-LAST:event_CBO_TipoHabitacionActionPerformed

    private void TXT_CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_CantidadActionPerformed

    }//GEN-LAST:event_TXT_CantidadActionPerformed

    private void TXT_CantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_CantidadKeyReleased
        //EVALUAR SI TXT CANTIDAD TIENE UN VALOR
        if (this.TXT_Cantidad.getText().length() > 0) {
            // INVOCAR IMPORTE

            this.Mostrar_Importe();
        } else {
            //  ESTABLECER IMPORTE
            this.lb_Importe.setText("0.00");
        }
    }//GEN-LAST:event_TXT_CantidadKeyReleased

    private void TXT_CantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_CantidadKeyPressed
        //EVALUAR SI SE HA PRECIONADO LA TECLA ENTER O TAB
        if (evt.getKeyCode() == KeyEvent.VK_ENTER
                || evt.getKeyCode() == KeyEvent.VK_TAB) {
            //INVOCAR AL METODO MOSTRAR IMPORTE
            this.Mostrar_Importe();

            //HABILITAR BOTONES}
            // this.Botones(true);
        }
    }//GEN-LAST:event_TXT_CantidadKeyPressed
    String m_NumHabitacion = "";
    private void CBO_N_HabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_N_HabitacionActionPerformed

        m_NumHabitacion = (String) CBO_N_Habitacion.getSelectedItem();
        ArrayList<HabitacionEntity> lst_HabitacionCostos = Habitaciondb.GetCargarHabitacionesTarifa(m_NumHabitacion);

        if (lst_HabitacionCostos != null && lst_HabitacionCostos.size() > 0) {

            PrecioHora = lst_HabitacionCostos.get(0).getPrecio_Hora();
            PrecioNoche = lst_HabitacionCostos.get(0).getPrecio_Noche();

            if (rbt_Hora.isSelected() == true) {

                lb_Precio.setText("" + PrecioHora);

            }

            if (rbt_Noche.isSelected() == true) {

                lb_Precio.setText("" + PrecioNoche);
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_N_HabitacionActionPerformed

    private void CBO_N_HabitacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBO_N_HabitacionKeyReleased
        // TODO add your handling code here:}

    }//GEN-LAST:event_CBO_N_HabitacionKeyReleased

    private void CBO_PisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_PisoActionPerformed

        String CodSede_Item = Utilidades.getM_UsuarioSistema().getCodSede();

        int index_Piso = CBO_Piso.getSelectedIndex();
        int index_Tipo = CBO_TipoHabitacion.getSelectedIndex();

        String CodNivel_Item, Tipo_Item;

        if (index_Piso >= 0 && index_Tipo >= 0) {

            CodNivel_Item = ListaNivel.get(index_Piso).getCodNivel();

            Tipo_Item = ListaTipo_Habitacion.get(index_Tipo).getCodTipo_Habitacion();

            ArrayList<HabitacionEntity> lst_Habitaciones = Habitaciondb.GetCargarHabitacionesNum(CodSede_Item, CodNivel_Item, Tipo_Item);

            CBO_N_Habitacion.removeAllItems();
            for (HabitacionEntity item : lst_Habitaciones) {
                CBO_N_Habitacion.addItem(item.getNumHabitacion());
            }
        }

    }//GEN-LAST:event_CBO_PisoActionPerformed

    private void TXT_CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_CantidadKeyTyped

        //CREAR CONTROLADOR DE ERROR
        try {
            //DECLARAR VARAIBLE TIPO CHAR(CARACTER)
            char Caracter;

            //OBTENER EL CARACTER TIPEADO
            Caracter = evt.getKeyChar();

            //EVALUAR SI EL CARACTER ESTA FUERA DE RANGO DEL 0  AL 9
            if (Caracter < '0' || Caracter > '9') {
                //DESHABILITAR TECLAS
                evt.consume();
            }
        } catch (Exception Error) {

        }
    }//GEN-LAST:event_TXT_CantidadKeyTyped

    private void BTN_NuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoClienteActionPerformed
        this.TXTDoc_Identidad_Cliente.setText("");
        this.Lb_Apellidos_Cliente.setText("");
        this.Lb_Nombre_Cliente.setText("");
        this.Lb_Direccion_Cliente.setText("");
        this.Lb_Nacionalidad_Cliente.setText("");
        this.Lb_Ubigeo.setText("");
//        this.CBO_DocIdentidad.removeAllItems();
//        this.CBO_TipoHabitacion.removeAllItems();
//        this.CBO_Piso.removeAllItems();
//        this.CBO_N_Habitacion.removeAllItems();
        this.lb_Precio.setText("");
        this.TXT_Cantidad.setText("");
        this.lb_Importe.setText("");
        this.Lb_Foto.setText("");

    }//GEN-LAST:event_BTN_NuevoClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Anular;
    private javax.swing.JButton BTN_BuscarCliente1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_NuevoCliente;
    private javax.swing.JPanel BodyPanel;
    private javax.swing.ButtonGroup BtnGp_Combo;
    private javax.swing.JComboBox<String> CBO_DocIdentidad;
    private javax.swing.JComboBox<String> CBO_N_Habitacion;
    private javax.swing.JComboBox<String> CBO_Piso;
    private javax.swing.JComboBox<String> CBO_TipoHabitacion;
    private javax.swing.JLabel Lb_Apellidos_Cliente;
    private javax.swing.JLabel Lb_CodCliente;
    private javax.swing.JLabel Lb_Direccion_Cliente;
    private javax.swing.JLabel Lb_Foto;
    private javax.swing.JLabel Lb_Nacionalidad_Cliente;
    private javax.swing.JLabel Lb_Nombre_Cliente;
    private javax.swing.JLabel Lb_Ubigeo;
    private javax.swing.JTextField TXTDoc_Identidad_Cliente;
    private javax.swing.JTextField TXT_Cantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jP_Imagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_RegistroCliente;
    private javax.swing.JPanel jp_CLiente;
    private javax.swing.JPanel jp_foder;
    private javax.swing.JLabel lbComentario;
    private javax.swing.JLabel lb_FechaIngreso1;
    private javax.swing.JLabel lb_FechaSalida;
    private javax.swing.JLabel lb_Importe;
    private javax.swing.JLabel lb_Precio;
    private javax.swing.JPanel pl_InformacionCliente;
    private javax.swing.JRadioButton rbt_Hora;
    private javax.swing.JRadioButton rbt_Noche;
    // End of variables declaration//GEN-END:variables
}
