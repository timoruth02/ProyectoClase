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

//        Login l = new Login();
//        l.dispose();

        String m_Nombres = Utilidades.getM_UsuarioSistema().getNombres();
        String m_Apellidos = Utilidades.getM_UsuarioSistema().getApellidos();

        String NombreCompletos = m_Nombres + ", " + m_Apellidos;

        lb_EmpleadoNombreCompletos.setText("Empleados(a) : " + NombreCompletos);

        lb_NombreSede.setText("Sede : " + Utilidades.getM_UsuarioSistema().getCodSede());

//      setSize(300, 200);
//        setDefaultCloseOperation(Login.DISPOSE_ON_CLOSE);
        jp_separador2.setPreferredSize(new Dimension(0, 38));
        jp_separador.setPreferredSize(new Dimension(16, 304));
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

//        //combobox sede
//        ListaSede = Sede_db.getCodSedeItems();
//
//        for (SedeEntity item : ListaSede) {
//            CBO_Sede.addItem(item.getNombre_Sede());
//        }
//        CBO_Sede.setSelectedIndex(-1);
        //combobox Tipo Habitacion
        ListaTipo_Habitacion = Tipo_Habitaciondb.getCodTipo_HabitacionItems();

        for (Tipo_HabitacionEntity item : ListaTipo_Habitacion) {
            CBO_TipoHabitacion.addItem(item.getDescripcion_TipoHabitacion());
        }
        CBO_TipoHabitacion.setSelectedIndex(-1);

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
        //CREO LOS TITULOS DE LA TABLA  *****************************************************************
        Modelo = new DefaultTableModel();
        String titulos[] = {"Habitación", "Piso", "Tipo", "Estado"};
        Modelo.setColumnIdentifiers(titulos);
        jTable_RegistroCliente.setModel(Modelo);
//        ************************************************************************************************

//        Cargo La Base de Datos *********************************************************************
        HabitacionDB H_DB = new HabitacionDB();
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
        Lb_Ubigeo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        CBO_N_Habitacion = new javax.swing.JComboBox<>();
        CBO_TipoHabitacion = new javax.swing.JComboBox<>();
        CBO_Piso = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        TXT_Cantidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TXT_Precio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        TXTImporte = new javax.swing.JTextField();
        lbComentario = new javax.swing.JLabel();
        jP_Imagen = new javax.swing.JPanel();
        Lb_Foto = new javax.swing.JLabel();
        jp_foder = new javax.swing.JPanel();
        BTN_Anular = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        jp_separador = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        BTN_Salir = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lb_EmpleadoNombreCompletos = new javax.swing.JLabel();
        lb_NombreSede = new javax.swing.JLabel();
        jp_separador2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_RegistroCliente = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lb_Disponible = new javax.swing.JLabel();
        lb_Ocupado = new javax.swing.JLabel();
        lb_Mantenimiento = new javax.swing.JLabel();

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
        jp_CLiente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

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

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de la Habitación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N
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

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Tarifa", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        GpPorTarifa.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Hora");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        GpPorTarifa.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Noche");

        TXT_Cantidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TXT_Cantidad.setForeground(new java.awt.Color(0, 0, 0));
        TXT_Cantidad.setText("jTextField1");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha I");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Cantidad:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Precio:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Fecha S:");

        TXT_Precio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TXT_Precio.setForeground(new java.awt.Color(0, 0, 0));
        TXT_Precio.setText("jTextField1");

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

        TXTImporte.setBackground(new java.awt.Color(204, 204, 204));
        TXTImporte.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        TXTImporte.setForeground(new java.awt.Color(0, 0, 0));
        TXTImporte.setText("0.00");
        TXTImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTImporteActionPerformed(evt);
            }
        });

        lbComentario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbComentario.setForeground(new java.awt.Color(255, 51, 51));
        lbComentario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbComentario.setText("El checking se realiza a la 1pm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXT_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel16)
                                    .addComponent(TXT_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TXTImporte, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXTImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(TXT_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(TXT_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(CBO_TipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(CBO_Piso, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBO_N_Habitacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBO_TipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_Piso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBO_N_Habitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jP_Imagen.setBackground(new java.awt.Color(0, 102, 153));
        jP_Imagen.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Foto de la Habitación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(204, 204, 204))); // NOI18N

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

        lb_EmpleadoNombreCompletos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lb_EmpleadoNombreCompletos.setForeground(new java.awt.Color(255, 255, 255));
        lb_EmpleadoNombreCompletos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_EmpleadoNombreCompletos, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lb_EmpleadoNombreCompletos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_NombreSede.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lb_NombreSede.setForeground(new java.awt.Color(255, 255, 255));
        lb_NombreSede.setText("jLabel10");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb_NombreSede, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_NombreSede, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel8.setBackground(new java.awt.Color(0, 102, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Habitacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N

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

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Disponible");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Ocupado");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Mantenimiento");

        lb_Disponible.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lb_Disponible.setForeground(new java.awt.Color(0, 0, 0));
        lb_Disponible.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Disponible.setText("Disponible");

        lb_Ocupado.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lb_Ocupado.setForeground(new java.awt.Color(0, 0, 0));
        lb_Ocupado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Ocupado.setText("Ocupado");

        lb_Mantenimiento.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lb_Mantenimiento.setForeground(new java.awt.Color(0, 0, 0));
        lb_Mantenimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_Mantenimiento.setText("Mantenimiento");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Ocupado, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_Disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_Ocupado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_Mantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_foder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp_CLiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jP_Imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 38, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jP_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jp_separador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        this.Lb_Ubigeo.setText("");
        this.Lb_Direccion_Cliente.setText("");
        this.TXT_Cantidad.setText("");
        this.TXT_Precio.setText("");
        this.TXT_Cantidad.setText("");
        //LIMPIAR LAS CAJAS DE TEXTO 
        this.TXTImporte.setText("");
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
                this.Lb_Ubigeo.setText(Clientes[Fila][6]);
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
            this.Lb_Ubigeo.setText("");
            this.Lb_Direccion_Cliente.setText("");

            //UBICAR CURSOR EN EL CONTROL TXT RUC_CLIENTE
            this.TXTDoc_Identidad_Cliente.requestFocus();

        }
    }

    //Crear el método :autoAjustar Columnas
    void AutoAjustar_Columnas() {
        //Establer Anchos de Columnas
        //Definir el Tamaño de cado Columna del control (JTable):JTable_RegistroNotas
        int[] Anchos = {80, 50, 100, 150};

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

//    //Crear Método Cargar_Fila
//    void Cargar_Fila() {
//        //DECLARAR UNA VARIABLE DE TIPO ENTERO, OBTENER FILA SELECCIONADA DEL CONTROL JTABLE
//        int Seleccion = this.jTable_RegistroCliente.getSelectedRow();
//
//        //Cargar Datos en Controles
//        this.Lb_CodCliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 0).toString());
//        this.Lb_Apellidos_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 1).toString());
//        this.Lb_Nombre_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 2).toString());
//        this.CBO_DocIdentidad.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 3).toString());
//        this.TXTDoc_Identidad_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 4).toString());
//        this.Lb_Nacionalidad_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 5).toString());
//        this.Lb_Ubigeo.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 6).toString());
//        this.Lb_Direccion_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 7).toString());
//        this.CBO_Sede.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 10).toString());
//        this.CBO_TipoHabitacion.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 11).toString());
//        this.CBO_Piso.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 11).toString());
//        this.CBO_N_Habitacion.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 11).toString());
//       // this.jD_Fec_Inicio.setToolTipText(this.jTable_RegistroCliente.getValueAt(Seleccion, 15).toString());
////        this.Lb_Ubigeo.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 16).toString());
////        this.Lb_Direccion_Cliente.setText(this.jTable_RegistroCliente.getValueAt(Seleccion, 17).toString());
////        this.CBO_Sede.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 18).toString());
////        this.CBO_TipoHabitacion.setSelectedItem(this.jTable_RegistroCliente.getValueAt(Seleccion, 19).toString());
//    }
//    //Crear el metodo local: guardar fichero
//    void Guardar_Fichero() {
//        //crear controlador de Errores
//        try {
//            //Establecer ruta del Archivo de texto a escribir secuencia de datos
//            FileWriter Guardar = new FileWriter(Directorio);
//
//            //Recorrer filas de modelos de datos
//            for (int i = 0; i < this.jTable_RegistroCliente.getRowCount(); i++) {
//                //Escribir secuencia de datos
//                Guardar.write(Modelo.getValueAt(i, 0).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 1).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 2).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 3).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 4).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 5).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 6).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 7).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 8).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 9).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 10).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 11).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 12).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 13).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 14).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 15).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 16).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 17).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 18).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 19).toString() + "\n");
//                Guardar.write(Modelo.getValueAt(i, 20).toString() + "\n");
//
//            }
//            //Cerrar el Archivo de Texto
//            Guardar.close();
//
//            //Mensaje de informaccio
//            JOptionPane.showMessageDialog(null, "Registro Guardado con éxito", this.getTitle(),
//                    JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (Exception Error) {
//            //mostrar mensaje de error
//            JOptionPane.showMessageDialog(null, "Error" + Error.getMessage(), this.getTitle(),
//                    JOptionPane.ERROR_MESSAGE);
//
//        }
//    }
//    //Crear metodo leer datos
//    void Leer_Datos() {
//        //Declarar variable de tipo string(cadena de texto)
//        String Codigo, Apellido, Nombres, Tipo_DocIdentidad, N_DocIdentidad, Nacionalidad, Distrito, Direccion, Codigo_Empleado, Datos_Empleado, Sede, Tipo_Habitacion;
//
//        //Obtener el Nombre del Archivo de Texto
//        File Leer_Doc = new File(Directorio);
//
//        //Crear Controlador de Error
//        try {
//            //Leer Documento(Nombre del Archivo  
//            Scanner Linea = new Scanner(Leer_Doc);
//
//            //Crear Estructura repetitiva(Bucle):while(Leer lineas del archivo)
//            while (Linea.hasNextLine()) {
//
//                //Leer Documento(Nombre del Archivo                  
//                Codigo = Linea.nextLine();
//                Apellido = Linea.nextLine();
//                Nombres = Linea.nextLine();
//                Tipo_DocIdentidad = Linea.nextLine();
//                N_DocIdentidad = Linea.nextLine();
//                Nacionalidad = Linea.nextLine();
//                Distrito = Linea.nextLine();
//                Direccion = Linea.nextLine();
//                Codigo_Empleado = Linea.nextLine();
//                Datos_Empleado = Linea.nextLine();
//                Sede = Linea.nextLine();
//                Tipo_Habitacion = Linea.nextLine();
//
//                //Agregar Filas al Metodoo de datos del jtable
//                Modelo.addRow(new Object[]{Codigo, Apellido, Nombres, Tipo_DocIdentidad, N_DocIdentidad, Nacionalidad, Distrito, Direccion, Codigo_Empleado,
//                    Datos_Empleado, Sede, Tipo_Habitacion});
//            }
//        } catch (Exception Error) {
//            //Montrar Error
//            JOptionPane.showMessageDialog(null, "Error" + Error.getMessage(), this.getTitle(),
//                    JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    //Crear metodo local situacion_Estudiantes ******************
//    void Situacion_Habitacion() {
//        if (this.TXT_Disponible.getText().length() > 0) {
//            //capturar el promedio
//            int Situacion = Integer.parseInt(this.TXT_Disponible.getText());
//
//            //EValuar si el promedio final es mayor o igual a 13
//            if (Situacion >= 13) {
//                //Establecer situacion del estudiante como APROBADO
//                this.Lb_Situacion.setText("APROBADO");
//
//                //Asigna color a la etique label :azul
//                this.Lb_Situacion.setForeground(Color.BLUE);
//            } else {
//                //Establecer situacion del estudiante DESAPROBADO
//                this.Lb_Situacion.setText("DESAPROBADO");
//
//                //Asignar color a la etiqueta label : rojo
//                this.Lb_Situacion.setForeground(Color.red);
//            }
//
//        }
//    }
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
                informacion[6] = this.Lb_Ubigeo.getText();
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
        //Evaluar si el codigo del Estudiante ha sido generado
        if (this.CBO_DocIdentidad.getSelectedIndex() == 0) {
            //Mostrar Mensaje de informacion
            JOptionPane.showMessageDialog(null, "Debe Seleccionar el Documento de Identidad", this.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
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

    private void CBO_TipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBO_TipoHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBO_TipoHabitacionActionPerformed

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
    private javax.swing.JComboBox<String> CBO_N_Habitacion;
    private javax.swing.JComboBox<String> CBO_Piso;
    private javax.swing.JComboBox<String> CBO_TipoHabitacion;
    private javax.swing.ButtonGroup GpPorTarifa;
    private javax.swing.JLabel Lb_Apellidos_Cliente;
    private javax.swing.JLabel Lb_CodCliente;
    private javax.swing.JLabel Lb_Direccion_Cliente;
    private javax.swing.JLabel Lb_Foto;
    private javax.swing.JLabel Lb_Nacionalidad_Cliente;
    private javax.swing.JLabel Lb_Nombre_Cliente;
    private javax.swing.JLabel Lb_Ubigeo;
    private javax.swing.JTextField TXTDoc_Identidad_Cliente;
    private javax.swing.JTextField TXTImporte;
    private javax.swing.JTextField TXT_Cantidad;
    private javax.swing.JTextField TXT_Precio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP_Imagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_RegistroCliente;
    private javax.swing.JPanel jp_CLiente;
    private javax.swing.JPanel jp_foder;
    private javax.swing.JPanel jp_separador;
    private javax.swing.JPanel jp_separador2;
    private javax.swing.JLabel lbComentario;
    private javax.swing.JLabel lb_Disponible;
    private javax.swing.JLabel lb_EmpleadoNombreCompletos;
    private javax.swing.JLabel lb_Mantenimiento;
    private javax.swing.JLabel lb_NombreSede;
    private javax.swing.JLabel lb_Ocupado;
    private javax.swing.JPanel pl_InformacionCliente;
    // End of variables declaration//GEN-END:variables
}
