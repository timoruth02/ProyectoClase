package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Ruth Sayuri
 */
public class JFrm_RegistroProductos extends javax.swing.JPanel {

    //DECLARAR VARIABLES DE TIPO ENTERO
    int NumFact;
            
    //DECLARAR VARIABLES PARA LAS LISTAS A UTILIZAR
    DefaultListModel ListaProd = new DefaultListModel();
    DefaultListModel ListaPrec = new DefaultListModel();
    DefaultListModel ListaCant = new DefaultListModel();
    DefaultListModel ListaImp = new DefaultListModel();
    
    //ESTABLECER EL DIREECTORIO DEL PROYECTO , DONDE SE DESEA GUARDAR EL ARCHIVO DE TEXTO
    String Directorio_Factura = new File("src/Archivo_Datos/Registro_Cuentas.txt").getAbsolutePath();
    String Directorio_DetalleFactura = new File("src/Archivo_Datos/Registro_Detalle_Producto.txt").getAbsolutePath();
    String Directorio_Clientes = new File("src/Archivo_Datos/Registro_ClienteJP.txt").getAbsolutePath();
    String Directorio_Productos = new File("src/Archivo_Datos/Registro_Productos.txt").getAbsolutePath();
    
    //CREAR UN VECTOR DE DATOS BIDIMENSIONAL (STRING) MATRIZ DE DATOS
    String[][]Clientes;
    String[][]Facturas;
    String[][]Detalle_Factura;
    
    //CREAR UN VECTOR DE DATOS BIDIRECCIONAL (STRING)
    String [][]Productos = new String[8][3];    //[N° Filas][Columnas]
    public JFrm_RegistroProductos() {
        initComponents();
        jPanel1_Cliente.setPreferredSize(new Dimension(466, 220));
       // jPanel1_Detalle_Factura.setPreferredSize(new Dimension(378, 396));
         jPanel1_Producto.setPreferredSize(new Dimension(422, 170));
        jPanel1_Producto5.setPreferredSize(new Dimension(72, 601));
        jPanel1_Producto2.setPreferredSize(new Dimension(280, 180));        
//INSTANCIAR VARIABLES PARA LOS CONTROLES JLISTBOX
        this.ListaProducto.setModel(ListaProd);
        this.ListaPrecio.setModel(ListaPrec);
        this.ListaCantidad.setModel(ListaCant);
        this.ListaImporte.setModel(ListaImp);

        
//        //INVOCAR METODOS
      //  this.Limpiar_Controles();
        this.Leer_Clientes();
  //      this.Leer_Productos();
        this.Cargar_Productos();
    
        //DESHABILITAR BOTONES
        this.Botones(false);
        
        //HABILIATR BOTONES
        this.Botones_Principales(true);
        
        //OBTENER FECHA ACTUAL
        this.Lb_Fec_Emision.setText(this.Fecha_Actual());
        
        //ESTABLECER EL MODO DE SELECCION PARA LAS LISTAS
        this.ListaProducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.ListaPrecio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.ListaCantidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.ListaImporte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //UBICAR CURSOR EN EL CONTROL RUC_CLIENTE
        this.TXTRuc_Cliente.requestFocus();
    }
 //METODO LIMPIAR CONTROLES
    void Limpiar_Controles()
    {
        
        //LIMPIAR LOS JLABEL
        this.Lb_NumFactura.setText("");
        this.Lb_Fec_Emision.setText("");
        this.Lb_Estado_Factura.setText("No Facturado");
        this.Lb_CodCliente.setText("");
        this.Lb_Apellidos.setText("");
        this.Lb_Nombres.setText("");
        this.Lb_Distrito.setText("");
        this.Lb_Direccion.setText("");
        this.Lb_Precio.setText("");
        this.Lb_Importe.setText("");
        this.Lb_SubTotal.setText("");
        this.Lb_IGV.setText("");
        this.Lb_Total.setText("");
        
        //LIMPIAR EL TEXTO DEL CONTROL JLABEL
        this.LB_Foto.setText("");
        
        //LIMPIAR LAS CAJAS DE TEXTO
        this.TXTRuc_Cliente.setText("");
        this.TXTCantidad.setText("");
        
        //LIMPIAR LAS LISTAS
        this.ListaProducto.removeAll();
        this.ListaPrecio.removeAll();
        this.ListaCantidad.removeAll();
        this.ListaImporte.removeAll();
        
        //LIMPIAR LAS LISTAS
        this.ListaProd.removeAllElements();
        this.ListaPrec.removeAllElements();
        this.ListaCant.removeAllElements();
        this.ListaImp.removeAllElements();
        
        //UBICAR CURSO EN EL CONTROL TXTRUC_CLIENTE
        this.TXTRuc_Cliente.requestFocus();
    }
    
    //Crear metodo solo numeros
    void Solo_Numeros(java.awt.event.KeyEvent evt)
    {
        //CREAR CONTROLADOR DE ERROR
        try
        {
            //DECLARAR VARAIBLE TIPO CHAR(CARACTER)
            char Caracter;
            
             //OBTENER EL CARACTER TIPEADO
            Caracter = evt.getKeyChar();
            
            //EVALUAR SI EL CARACTER ESTA FUERA DE RANGO DEL 0  AL 9
            if(Caracter <'0' || Caracter >'9')
            {
                //DESHABILITAR TECLAS
                evt.consume();
            }
        }
        catch(Exception Error)
        {
            
        }
    }
    
    //CREAR FUNCION FECHA ACTUAL
    public String Fecha_Actual()
    {
        //DECLAR VARIABALE DE TIPO DATE
        Date Fecha = new Date();
        
        //DECLARAR UNA VARIABLE DE TIPO SIMPLEDATEFORMAT
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        //RETORNAR VALOR
        return formato.format(Fecha);
    
    }
    
    //CREAR FUNCION GENERAR_NUMFACTURA
    public String Generar_NumFactura()
    {
        //DECLARAR VARIABLE TIPO STRING
        String NumFactura = "";
        //DECLARAR UNA VARIABLE DE TIPO SCANNER
        Scanner Linea ;
        
        //OBTENER NOMBRE DEL ARCHIVO DE TEXTO
        File Leer_Doc = new File(Directorio_Factura);
        
        //DECLARA VARIAVLE TIPO ENTERO
        int Fila=0;
        
        //CREAR CONTROLADOR DE ERROR
        try
        {
           //LEER DOCUMENTO (NOMBRE DEL ARCHIVO)
            Linea = new  Scanner(Leer_Doc);
            
            //CREAR ESTRUCTURA REPETITIVA
            while(Linea.hasNextLine())
            {
                //LEER LINEAS
                Linea.nextLine();
            
                //INCREMENTAAAR EL VALOR DE LA VARIABLE
                Fila++;
            }
            //OBTENER EL TOTAL DE FILAS DEL ARCHIVO DE TEXTO
            Fila = (Fila/7); //7 columnas que tiene factura

            //EVALUAR SI EL RANGO DE FILA ESTA ENTRE 1 Y 9
            if(Fila>=0 && Fila <10)
            {
               //STABLECER EL NUMERO DE FACTURA
               NumFactura ="001-00000"+ String.valueOf(Fila+1); 
            }
            else if(Fila>=10 && Fila<=99)
            {
                //ESTABLECER NUMERO DE FACTURA
                NumFactura ="001-0000"+ String.valueOf(Fila+1);
            }
            else if(Fila>=100 && Fila<=999)
            {
                //ESTABLECER NUMERO DE FACTURA
                NumFactura ="001-000"+ String.valueOf(Fila+1);
            }
            else if(Fila>=1000 && Fila<=9999)
            {
                //ESTABLECER NUMERO DE FACTURA
                NumFactura ="001-00"+ String.valueOf(Fila+1);
            }
            else if(Fila>=10000 && Fila<=99999)
            {
                //ESTABLECER NUMERO DE FACTURA
                NumFactura ="001-0"+ String.valueOf(Fila+1);
            }
            else if(Fila>=100000 && Fila<=999999)
            {
                //ESTABLECER NUMERO DE FACTURA
                NumFactura ="001-"+ String.valueOf(Fila+1);
            }
        }
        catch(Exception Error)
        {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,"Error","Error"+ Error.getMessage(),
                    JOptionPane.ERROR_MESSAGE);   
        } 
        //RETORNAR VALOR
        return NumFactura;
    }
  
    //CREAR EL METODO LEER_CLIENTES
    void Leer_Clientes()
    {
        //DECLARAR UNA VARIABLE TIPO SCANNER
        Scanner Linea;
        
        //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO
        File Leer_Doc= new File(Directorio_Clientes);
    
        //DECLARAR UNA VARIABLE DE TIPO CONTADOR
        int Fila =0;
        
        //CREAR CONTROLADOR DE ERROR
        try
        {
            //LEER DOCUMENTO(NOMBRE DEL ARCHIRVO)
            Linea = new Scanner(Leer_Doc);
            
            //CREAR ESTRUCTURA REPETITIVA
            while(Linea.hasNextLine())
            {
                //LEER LINEAS
                Linea.nextLine();
                
                //INCREMENTAR EL VALOR DE LA VARIABLE
                Fila++;
            }
            //ESTABLECER VALOR DE LA FILA
            Fila =(Fila/8);
            
            //ASIGNAR TAMAÑO DE LA MATRIZ DE DATOS
            Clientes = new String[Fila][8];
            
            //VOLVER A ASIGNAAR EL VALOR 0 
            Fila=0;
            
            //LEER DOCUMENTO(NOMBRE DEL ARCHIRVO)
            Linea = new Scanner(Leer_Doc);
            
            //CREAR ESTRUCTURA REPETITIVA LEER LINEAS DE TEXTO DEL ARCHIVO
            while(Linea.hasNextLine())
            {
                //LEER Y ASIGNAR DATOS DEL ARCHIVO DE TEXTO A LA MATRIZ DE DATOS
                Clientes[Fila][0]= Linea.nextLine();
                Clientes[Fila][1]= Linea.nextLine();
                Clientes[Fila][2]= Linea.nextLine();
                Clientes[Fila][3]= Linea.nextLine();
                Clientes[Fila][4]= Linea.nextLine();
                Clientes[Fila][5]= Linea.nextLine();
                Clientes[Fila][6]= Linea.nextLine();
                Clientes[Fila][7]= Linea.nextLine();
                
                //INCREMENTAR EL VALOR DE LA VARIABLE
                Fila++;
            }
        }
        catch(Exception Error)
        {
        //MOSTRAR MENSAJE DE ERROR
        JOptionPane.showMessageDialog(null, "Error","Error"+ Error.getMessage(),
                                       JOptionPane.ERROR_MESSAGE);
        } 
    }
         
    //CREAR METODO BUSCAR_CLIENTE
    void Buscar_Cliente(String Ruc)
    {
        //CREAR UNA VARIABLE TIPO ENTERO
        int Fila =0,FilaEncontrada = 0;
        
        //CREAR ESTRUCTURA REPETITIVA
        while(Fila< Clientes.length)
        {
            //CREAR CONDICION PARA EVALUAR SI EL RUC ESPECIFICADO EXISTE DENTRO DE LA MATRIZ
            if(Ruc.equals(Clientes[Fila][4]))   //4 ES LA COLUMNA DEL NUMEO RUC
            {
                //OBTENER DATOS DE LA MATRIZ DE DATOS Y PASAR DATOS A LOS CONTROLADORES
                this.Lb_CodCliente.setText(Clientes[Fila][0]);
                this.Lb_Apellidos.setText(Clientes[Fila][1]);
                this.Lb_Nombres.setText(Clientes[Fila][2]);
                this.Lb_Distrito.setText(Clientes[Fila][5]);
                this.Lb_Direccion.setText(Clientes[Fila][6]);
                
                //ASIGNAR VALOR A LA VARIABLE FILAENCONTRADA DE 1
                FilaEncontrada =1;
                
                //FIN DEL BUCLE
                break;             
            }
            
            //INCREMENTAR EL VALOR DE LA FILA
            Fila++;
        }
        //EVALUAR SI EL VSALOR DE LA VARIABLE FILAENCONTRADA ES IGUAL 0
       if(FilaEncontrada ==0)
        {
            JOptionPane.showMessageDialog(null,"El Cliente de Ruc N° ","Mensaje"+ this.TXTRuc_Cliente.getText()+
                    ",No Existe",JOptionPane.ERROR_MESSAGE);
        
            //LIMPIAR CONTROLES
            this.Lb_CodCliente.setText("");
            this.Lb_Apellidos.setText("");
            this.Lb_Nombres.setText("");
            this.Lb_Distrito.setText("");
            this.Lb_Direccion.setText("");
            
            //UBICAR CURSOR EN EL CONTROL TXT RUC_CLIENTE
            this.TXTRuc_Cliente.requestFocus();
            
        }
    }
    
    //CREAR METODO LOCAL : LEER_BEBIDAS()
    void Leer_Productos()
    {
        //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO
        File Leer_Doc = new File(Directorio_Productos);
        //DECLARAR VARIABLE DE TIPO CONTADOR
        int Fila=0;
        
        //CREAR UN CONTROLADOR DE ERROR 
        try
        {
            //LEER DOCUMENTO (NOMBRE DEL ARCHIVO)
            Scanner Linea = new Scanner(Leer_Doc);
            
            //CREAR ESTRUCTURA REPETITIVA(BUCLE) WHILE(LEER LINEAS DE TEXTO DEL ARCHIVO)
            while(Linea.hasNextLine())
            {
                //LEER LOS DATOS DEL ARCHIVO DE TEXTO
                Productos[Fila][0] = Linea.nextLine();
                Productos[Fila][1] = Linea.nextLine();
                Productos [Fila][2] = Linea.nextLine();
                
                //INCREMENTAR VALOR DE LA VARIABLE FILA
                Fila++;
            }
        }
        catch(Exception Error)
        {
            //MOSTRAR  MENSAJE DE EEROR
            JOptionPane.showMessageDialog(null,"Error: ","Error"+Error.getMessage()
                   , JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    //CREAR METODO :CARGAR_BEBIDAS()
    void Cargar_Productos()
    {
        //ELIMINAR LOS ELEMENTOS DEL COMBOBOX
       // this.CBO_Producto.removeAllItems();
        
        //AGREGAR ELEMENTOS AL COMBOBOX
        this.CBO_Producto.addItem("<Seleccione>");

        //RECORRER ELEMENTOS DE LA MATRIZ DE DATOS
       for(int Fila=0;Fila<Productos.length;Fila++)
        {
            //AGREGAR LOS ELEMENTOS DE LA MATRIZ DE DATOS AL COMBOBOX 
            this.CBO_Producto.addItem(Productos[Fila][0]);            
        }
    }
    
    //CREAR METODO LOCAL BOTONES, EL CUAL RESIVIRA UN PARAMETRO
    void Botones(Boolean Valor)
    {
        //ESTABLEFCER VALLOR HABILITAD O DESHABILITAR PARA LOS BOTONES
        this.BTN_Agregar.setEnabled(Valor);
        this.BTN_Eliminar.setEnabled(Valor); 
    }
    
    //CREAR METODO BOTONES PRINCIPALES EL CUAL RECIBIRA UN PARAMETRO
    void Botones_Principales(boolean Valor)
    {
        //ESTABLECER VALOR DE HABILITAR O DESHABILITAT PARA LOS BOTONES
        this.BTN_Nuevo.setEnabled(Valor);
        this.BTN_Guardar.setEnabled(!Valor);
        this.BTN_Salir.setEnabled(Valor);
    }
    
    //CREAR METODO CARGAR IMAGEN
    void Cargar_Imagen(String Imagen)
    {
        ImageIcon Foto = new ImageIcon("src/Imagenes/"+Imagen);
        
        //ESTABLECER ICONO
        Icon Icono = new ImageIcon(Foto.getImage().getScaledInstance(
                this.LB_Foto.getWidth(),this.LB_Foto.getHeight(),
                Image.SCALE_DEFAULT));
    
        //ASIGNAR IMAGEN AL CONTROL: JLABEL
        this.LB_Foto.setIcon(Icono);
        
        //REPRESENTAR IMAGEN
        this.repaint();
    }
    
    //CREAR EL METODO LOCAL CARGAR DATOS
    void Cargar_Datos(String NombreProducto)
    {
        //CREAR UNA ESTRUCTURA REPETITIVA (BUCLE) 
        for (int Fila=0; Fila < Productos.length; Fila++)
        {
            //EVALUAR SI EL NOMBRE DEL PRODUCTO SELECCIONADO EXISTE DENTRO DE LA MATRIZ
            if(NombreProducto.equals(Productos[Fila][0]))
            {
                //OBTENER EL PRECIO DEL PRODUCTO
                this.Lb_Precio.setText(Productos[Fila][1]);
                
                //OBTENER EL NOMBRE DE LA IMAGEN
                this.Cargar_Imagen(Productos[Fila][2]);
                
                //FIN DEL BUCLE
                break;
            }
        } 
    }
    
    //CREAR EL METODO MOSTRAR IMPORTE
    void Mostrar_Importe()
    {
        //DECLARAR UNA VARIABLE PARA ESTABLECER EL FORMATO
        DecimalFormat Num_Decimal = new DecimalFormat("0.00");
        
        //DECLARAR VARIABLES
        int Cant;
        double Precio, Importe;
        
        //OBTENER VARIABLE        
        Precio = Double.parseDouble(this.Lb_Precio.getText());
        Cant = Integer.parseInt(this.TXTCantidad.getText());
        
        //REALIZAR CALCULO
        Importe = (Precio*Cant);
        
        //MOSTRAR EL VALOR DEL IMPORTE
        this.Lb_Importe.setText(Num_Decimal.format(Importe));
    }
    
    //CREAR METODO LOCAL Agregar_Producto
    void Agregar_Producto()
    {
        //DECLARAR VARIABLE DE TIPO ENTERO
        int Rpta;
        
        //EVALUAR SI EXISTE EL PRODUCTO SELECCIONADO DENTRO DE LA LISTA
        if(ListaProd.contains(this.CBO_Producto.getSelectedItem().toString())== true)
        {
            //MOSTRAR MENSAJE DE ADVERTENCIA
            JOptionPane.showMessageDialog(null,
                    "El Producto Seleccionado ya Existe en la Lista Productos","Mensaje"
                    , JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            //  MOSTRAR EL MENSAJE  DE CONFIRMACION
            Rpta = JOptionPane.showConfirmDialog(null,
                    "¿Desea Agregar el Producto?","Mensaje",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            // EVALUAR SI RPTA ES SI 
            if(Rpta == 0)
            {
                //AGREGAR LOS ELEMENTOS A LA LISTAS}
                ListaProd.add(ListaProd.size(), this.CBO_Producto.getSelectedItem().toString());
                ListaPrec.add(ListaPrec.size(), this.Lb_Precio.getText());
                ListaCant.add(ListaCant.size(), this.TXTCantidad.getText());
                ListaImp.add(ListaImp.size(), this.Lb_Importe.getText());          
            }
        }
    }
    
    //CREAR METODO CALCULAR
    void Calcular()
    {
        //DECLARAR VARIABLE E INICIAR DE 0
        int i =0;
        double SubTotal = 0, Igv, Total;
        String Cadena_Importe;
        
        //ESTABLECER UN FORMATO PARA LA CANTIDAD DE DECIMALES DE UN NUMERO
        DecimalFormat Formato = new DecimalFormat("0.00");
        
        //CREAR UNA ESTRUCTURA REPETITIVA
        while ( i <this.ListaImp.getSize())
        {
            //OBTENER EL VALOR DE LOS ITEMS DE LA LISTAIMP, A TRAVES DE UN INDEX
            Cadena_Importe = this.ListaImp.get(i).toString();
            
            //REEMPLAZAR CARACTER (,)POR EL (.)
            Cadena_Importe = Cadena_Importe.replace(",",".");
            
            //REALIZAR LA SUMATORIA DE LOS IMPORTE
           SubTotal = SubTotal + Double.parseDouble(Cadena_Importe);
           
           //INCREMENTAR EL VALOR DE LA VARIABLE
           i++;
        }
        
        //EFECTUAR CALCULO
        SubTotal = (SubTotal/1.18);
        Igv = (SubTotal*0.18);
        Total = (SubTotal + Igv);
        
        //MOSTRAR RESULTADO CON FORMATO DECIMAL
        this.Lb_SubTotal.setText(Formato.format(SubTotal));
        this.Lb_IGV.setText(Formato.format(Igv));
        this.Lb_Total.setText(Formato.format(Total));
    }
    
    //CREAR METODO ELIMINAR PRODUCTO
    void Eliminar_Producto()
    {
        //DECLARAR VARIABLE
        int Indice, Rpta;
        
        //CAPTURAR EL INDICE SELECCIONADO
        Indice = this.ListaProducto.getSelectedIndex();
        
        //EVALUAR SI EL INDICE ES IGUAL A -1 (SIGNIFICA NO HAY ELEMENTO SELECCIONADO
        if(Indice != -1)
        {
            Rpta = JOptionPane.showConfirmDialog(null,"¿Desea Eliminar este Producto: "+
                    this.ListaProducto.getSelectedValue().toString()+
                    "?","Confirmaci+on...", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            
            //EVALUAR SI RPTA ES SI
            if(Rpta ==0)
            {
                //ELIMINAR ITEM, SEGUN EL INDICE SELECCIONADO
                this.ListaProd.remove(Indice);
                this.ListaPrec.remove(Indice);
                this.ListaCant.remove(Indice);
                this.ListaImp.remove(Indice);
                
                //INVOCAR AL METODO :CALCULAR()
                this.Calcular();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "No existe un Elemento Seleccionado a Eliminar", "Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //  CREAR METODO GUARDAR_FACTURA
    void Guardar_Factura()
    {
        //EVALUAR SI SE HA GENERADO EL NUMERO DE FACTURA DE VENTA
        if(this.Lb_NumFactura.getText().length()==0)
        {
          //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,
            "Debe Generar el N° de Facturación para proceder a la Emisión de la Factura","Mensaje"
            ,JOptionPane.ERROR_MESSAGE);
        }
        //EVALUAR SI EXISTEN DATOS DEL CLIENTE
        else if(this.Lb_CodCliente.getText().equals(""))
        {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,
                    "Debe Filtrar los Datos del Cliente para proceder a la emisión de la Facturación",
                    "Mensaje", JOptionPane.ERROR_MESSAGE);
            
            //UBICAR EL CURSOR EN EL CONTRO RUC_CLIENTE
            this.TXTRuc_Cliente.requestFocus();
        }
        //EVALUAR SI SE HA GENERADO LA LA FECHA DE EMISION             
        else if(this.Lb_Fec_Emision.getText().equals(""))
        {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,
                    "Debe Especificar la Fecha de Emisión para la Factura",
                    "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        //EVALUAR SI EXISTEN PRODUCTOS EN LA LISTA A FACTURAR
        else if(this.ListaProd.getSize()==0 || this.Lb_Total.getText().equals("0.00"))
        {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,
                    "No Existen Productos a Facturar" ,"Mensaje",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            //DECLARAR UNA VARIABLE TIPO ENTERO
            int Rpta;
            
            //MOSTRAR MENSAJE DE CONFIRMACION
            Rpta= JOptionPane.showConfirmDialog(null,"¿Desea Guardar los Datos","Mensaje",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            //EVALUAR SI LA RESPUESTA DEL USUARAIO FUE AFIRMATIVA
            if(Rpta ==0)
            {
            
                //ESTABLECER EL ESYTADO DE FACTURA
                this.Lb_Estado_Factura.setText("Emitido");

                //DECLARAR UNA VARIABLE DE TIPO SCANNER
                Scanner Linea;
                
                //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO 
                File Leer_DocFactura = new File(Directorio_Factura);
                
                //DECLARAR UNA VARIABLE DE TIPO CONTADOR
                int Fila = 0;
                
                
                //CREAR CONTROLADOR DE ERROR
                try
                {
                    //LEER DOCUMENTO (NOMBRE DEL ARCHIVO)
                    Linea = new Scanner (Leer_DocFactura);
                    
                    //CREAR ESTRUCTURA REPTITIVA(BUCLE) WHILE(LEER LAS LINEAS DE TEXTO)
                    while(Linea.hasNextLine())
                    {
                        //LEER LINEA
                        Linea.nextLine();
                        
                        //INCREMENTAR EL VALOR DE LA VARIABLE FILA++
                        Fila++;
                    }
                    //ESTABLECER EL VALOR DE FILA
                    Fila = (Fila/7);    //7 columnas la factura 
                    
                    System.out.println(Fila);
                    
                    //ASIGNAR TAMAÑO DE LA MATRIZ DE DATOS
                    Facturas = new String[Fila+1 ][7];
                    
                    //VOLVER A ASIGNAR EL VALOR DE 0 A LA VARIABLE
                    Fila = 0;
                    
                    //LEER DOCUMENTO
                    Linea  = new Scanner(Leer_DocFactura);
                    
                    //CREAR ESTRUCTURA REPETITIVA
                    while(Linea.hasNextLine())
                    {
                        //LEER Y ASIGNAR DATOS
                        Facturas[Fila][0]=Linea.nextLine();
                        Facturas[Fila][1]= Linea.nextLine();
                        Facturas[Fila][2]=Linea.nextLine();
                        Facturas[Fila][3]=Linea.nextLine();
                        Facturas[Fila][4]= Linea.nextLine();
                        Facturas[Fila][5]=Linea.nextLine();
                        Facturas[Fila][6]=Linea.nextLine();

                        //INCREMENTAR EL VALOR DE LA VAARIABALE FILA
                        Fila++;
                    }
                    //ASIGNAR DATOS DEL FORMULARIO A LA MATRIZ
                    Facturas[Facturas.length-1][0]=this.Lb_NumFactura.getText();
                    Facturas[Facturas.length-1][1]=this.Lb_CodCliente.getText();
                    Facturas[Facturas.length-1][2]=this.Lb_Fec_Emision.getText();
                    Facturas[Facturas.length-1][3]=this.Lb_SubTotal.getText();
                    Facturas[Facturas.length-1][4]=this.Lb_IGV.getText();
                    Facturas[Facturas.length-1][5]=this.Lb_Total.getText();
                    Facturas[Facturas.length-1][6]=this.Lb_Estado_Factura.getText();

                    
                    //OBTENER LA RUTA DEL ARCHIVO DONDE DESEAMOS ESCRIBIR LOS DATOS
                    FileWriter GuardarCabecera = new FileWriter(Directorio_Factura);

                    //CREAR UN BUCLE
                    for(int i=0;i<Facturas.length;i++)
                    {
                    //ESCRIBIR SECUENCIA DE DATOS
                    GuardarCabecera.write(Facturas[i][0]+"\n");
                    GuardarCabecera.write(Facturas[i][1]+"\n");
                    GuardarCabecera.write(Facturas[i][2]+"\n");
                    GuardarCabecera.write(Facturas[i][3]+"\n");
                    GuardarCabecera.write(Facturas[i][4]+"\n");
                    GuardarCabecera.write(Facturas[i][5]+"\n");
                    GuardarCabecera.write(Facturas[i][6]+"\n");
                    }
                    
                       //CERRAR EL ARCHIVO DE TEXTO
                    GuardarCabecera.close();  
                }
                catch(Exception Error)
                {
                    //  MOSTRAR MENSAJE EL ERROR
                    JOptionPane.showMessageDialog(null,"Error","Error"+Error.getMessage(),
                                                   JOptionPane.ERROR_MESSAGE);               
                }
            }
            else
            {
                //HABILITAR BOTONES PRINCIPALES
                this.Botones_Principales(true);
            }
        }          
    }
    
    //CREAR EL METODO GUARDAR_DETALLEFACTURA
    void Guardar_DetalleFactura()
    {
        
        //OBTENER EL NOMBRE DEL ARCHIVO DE TEXTO 
        File Leer_DocDetalleFactura = new File(Directorio_DetalleFactura);
        
        
        //ASIGNAR VALOR DE 0 EN LA VARIABLE FILA
        int Fila = 0;
        
        //CREAR CONTROLADOR DE ERROR
        try 
        {
 
        //LEER DOCUMENTO (NOMBRE DEL ARCHIVO)
        Scanner LineaDetalle = new Scanner(Leer_DocDetalleFactura);
                    
        //CREAR ESTRUCTURA REPTITIVA(BUCLE) WHILE(LEER LAS LINEAS DE TEXTO)
        while(LineaDetalle.hasNextLine())
        {
             //LEER LINEA
            LineaDetalle.nextLine();
                        
            //INCREMENTAR EL VALOR DE LA VARIABLE FILA++
            Fila++;
            }
            //ESTABLECER EL VALOR DE FILA
            Fila = (Fila/5);    //5 columnas el detalle de factura 
                    
            //ASIGNAR TAMAÑO DE LA MATRIZ DE DATOS
            Detalle_Factura = new String[Fila+ this.ListaProd.getSize() ][5];
                    
            //VOLVER A ASIGNAR EL VALOR DE 0 A LA VARIABLE
            Fila = 0;
                    
            //LEER DOCUMENTO
            LineaDetalle  = new Scanner(Leer_DocDetalleFactura);
                    
            //CREAR ESTRUCTURA REPETITIVA
        while(LineaDetalle.hasNextLine())
        {
            //LEER Y ASIGNAR DATOS
            Detalle_Factura[Fila][0]= LineaDetalle.nextLine();
            Detalle_Factura[Fila][1]= LineaDetalle.nextLine();
            Detalle_Factura[Fila][2]= LineaDetalle.nextLine();
            Detalle_Factura[Fila][3]= LineaDetalle.nextLine();
            Detalle_Factura[Fila][4]= LineaDetalle.nextLine();                        

            //INCREMENTAR EL VALOR DE LA VAARIABALE FILA
            Fila++;
        }
                    
         //ASIGNAR DATOS DEL FORMULARIO A LA MATRIZ
        for(int i = 0;i< this.ListaProd.getSize();i++){
             //ASIGNAR valoresS
            Detalle_Factura[Fila][0]=this.Lb_NumFactura.getText();
            Detalle_Factura[Fila][1]=this.ListaProd.get(i).toString();
            Detalle_Factura[Fila][2]=this.ListaPrec.get(i).toString();
            Detalle_Factura[Fila][3]=this.ListaCant.get(i).toString();
            Detalle_Factura[Fila][4]=this.ListaImp.get(i).toString();
                    
            //INCREMENTAR EL VALOR DE LA VARIABLE
            Fila++;
        }
        //OBTENER EL NOMBRE DEL ARCHIVO D ETEXTO
        FileWriter GuardarDetalleFactura = new FileWriter(Directorio_DetalleFactura);
                    
        //RECORRER LAS FILAS DE LAS LISTAS
        for(int i=0; i<Detalle_Factura.length;i++)
        {
            //ESCRIBIR LA SECUENCIA DE DATOS 
            GuardarDetalleFactura.write(Detalle_Factura[i][0]+"\n");
            GuardarDetalleFactura.write(Detalle_Factura[i][1]+"\n");
            GuardarDetalleFactura.write(Detalle_Factura[i][2]+"\n");
            GuardarDetalleFactura.write(Detalle_Factura[i][3]+"\n");
            GuardarDetalleFactura.write(Detalle_Factura[i][4]+"\n"); 
        }
                    
        //CERRAR EL ARCHIVO DE TEXTO
        GuardarDetalleFactura.close();
        
         JOptionPane.showMessageDialog(null,"Factura N°"+ this.Lb_NumFactura.getText()+" Emitida con Exito",
        "Mensaje",JOptionPane.INFORMATION_MESSAGE);
         
        //HABILITAR BOTONES PRINCIPALES
        this.Botones_Principales(true);
        }
        catch(Exception Error)
        {
           //  MOSTRAR MENSAJE EL ERROR
            JOptionPane.showMessageDialog(null,"Error","Error"+Error.getMessage(),
                                                   JOptionPane.ERROR_MESSAGE); 
        }
 }
    
    
    //CREAR EL METODO: SALIR
    void Salir()
    {
         // DECLARAR UNA VARIABLE DE TIPOM ENTERO(INT)
        int Rpta;
        
        //MOSTRAR UN MENSAJE DE CONFIRMACION
        Rpta = JOptionPane.showConfirmDialog(null,
                "¿Desea Salir de la Ventana de Factura de Venta","Mensaje",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        //EVALUAR LA RESPUESTA DEL USUARIO
        if (Rpta ==0)
        {
            //SALIR DEL PROGRAMA
            System.exit(0);
        }
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1_Cliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Lb_CodCliente = new javax.swing.JLabel();
        Lb_Apellidos = new javax.swing.JLabel();
        Lb_Nombres = new javax.swing.JLabel();
        TXTRuc_Cliente = new javax.swing.JTextField();
        Lb_Distrito = new javax.swing.JLabel();
        Lb_Direccion = new javax.swing.JLabel();
        BTN_Buscar_Cliente = new javax.swing.JButton();
        BTN_Registrar_Cliente = new javax.swing.JButton();
        jPanel1_Producto = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Lb_Precio = new javax.swing.JLabel();
        CBO_Producto = new javax.swing.JComboBox<>();
        TXTCantidad = new javax.swing.JTextField();
        Lb_Importe = new javax.swing.JLabel();
        BTN_Eliminar = new javax.swing.JButton();
        BTN_Agregar = new javax.swing.JButton();
        jPanel1_Detalle_Factura = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaProducto = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaPrecio = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListaCantidad = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListaImporte = new javax.swing.JList<>();
        Lb_SubTotal = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Lb_IGV = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Lb_Total = new javax.swing.JLabel();
        jPanel1_Producto2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        Lb_NumFactura = new javax.swing.JLabel();
        Lb_Fec_Emision = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Lb_Estado_Factura = new javax.swing.JLabel();
        BTN_Salir = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jPanel1_Producto5 = new javax.swing.JPanel();
        LB_Foto3 = new javax.swing.JLabel();

        jPanel1_Cliente.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1_Cliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Cliente :");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellidos :");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nombres :");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Ruc :");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Dirección :");

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Distrito :");

        Lb_CodCliente.setBackground(new java.awt.Color(204, 204, 204));
        Lb_CodCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Lb_CodCliente.setForeground(new java.awt.Color(0, 0, 0));
        Lb_CodCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_CodCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_CodCliente.setOpaque(true);

        Lb_Apellidos.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Apellidos.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Lb_Apellidos.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Apellidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Apellidos.setText("Lb_Apellidos :");
        Lb_Apellidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Apellidos.setOpaque(true);

        Lb_Nombres.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Nombres.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Lb_Nombres.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Nombres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Nombres.setText("Lb_Nombres :");
        Lb_Nombres.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Nombres.setOpaque(true);

        TXTRuc_Cliente.setBackground(new java.awt.Color(204, 204, 204));
        TXTRuc_Cliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        TXTRuc_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        TXTRuc_Cliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TXTRuc_Cliente.setText("jTextField1");
        TXTRuc_Cliente.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TXTRuc_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTRuc_ClienteKeyTyped(evt);
            }
        });

        Lb_Distrito.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Distrito.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Lb_Distrito.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Distrito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Distrito.setText("Lb_Distrito :");
        Lb_Distrito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Distrito.setOpaque(true);

        Lb_Direccion.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Direccion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Lb_Direccion.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Direccion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Lb_Direccion.setText("Lb_Dirección :");
        Lb_Direccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Direccion.setOpaque(true);

        BTN_Buscar_Cliente.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Buscar_Cliente.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Buscar_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Buscar_Cliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\Lupa.png")); // NOI18N
        BTN_Buscar_Cliente.setToolTipText("Buscar Cliente");
        BTN_Buscar_Cliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BTN_Buscar_Cliente.setIconTextGap(10);
        BTN_Buscar_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Buscar_ClienteActionPerformed(evt);
            }
        });

        BTN_Registrar_Cliente.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Registrar_Cliente.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Registrar_Cliente.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Registrar_Cliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\Nota.png")); // NOI18N
        BTN_Registrar_Cliente.setToolTipText("Registrar Cliente");
        BTN_Registrar_Cliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BTN_Registrar_Cliente.setIconTextGap(10);
        BTN_Registrar_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Registrar_ClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1_ClienteLayout = new javax.swing.GroupLayout(jPanel1_Cliente);
        jPanel1_Cliente.setLayout(jPanel1_ClienteLayout);
        jPanel1_ClienteLayout.setHorizontalGroup(
            jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1_ClienteLayout.createSequentialGroup()
                        .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTRuc_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(BTN_Buscar_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1_ClienteLayout.createSequentialGroup()
                        .addComponent(Lb_CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lb_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1_ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Distrito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Registrar_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Nombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1_ClienteLayout.setVerticalGroup(
            jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_CodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1_ClienteLayout.createSequentialGroup()
                        .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Distrito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Direccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1_ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1_ClienteLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXTRuc_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BTN_Registrar_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BTN_Buscar_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1_Producto.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1_Producto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Seleccione Producto :");

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Precio :");

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Cantidad :");

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Importe :");

        Lb_Precio.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Precio.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_Precio.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Precio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Precio.setText("Lb_Precio");
        Lb_Precio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Precio.setOpaque(true);

        CBO_Producto.setBackground(new java.awt.Color(204, 204, 204));
        CBO_Producto.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        CBO_Producto.setForeground(new java.awt.Color(0, 0, 0));
        CBO_Producto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBO_Producto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBO_ProductoItemStateChanged(evt);
            }
        });

        TXTCantidad.setBackground(new java.awt.Color(204, 204, 204));
        TXTCantidad.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        TXTCantidad.setForeground(new java.awt.Color(0, 0, 0));
        TXTCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXTCantidad.setText("jTextField1");
        TXTCantidad.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TXTCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTCantidadKeyTyped(evt);
            }
        });

        Lb_Importe.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Importe.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_Importe.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Importe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Importe.setText("Lb_Importe");
        Lb_Importe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Importe.setOpaque(true);

        BTN_Eliminar.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Eliminar.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Eliminar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Eliminar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\tacho.png")); // NOI18N
        BTN_Eliminar.setText("Eliminar");
        BTN_Eliminar.setToolTipText("Eliminar Producto");
        BTN_Eliminar.setIconTextGap(10);
        BTN_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EliminarActionPerformed(evt);
            }
        });

        BTN_Agregar.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Agregar.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Agregar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Agregar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\Agregar.png")); // NOI18N
        BTN_Agregar.setText("Agregar");
        BTN_Agregar.setToolTipText("Agregar Producto");
        BTN_Agregar.setIconTextGap(10);
        BTN_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1_ProductoLayout = new javax.swing.GroupLayout(jPanel1_Producto);
        jPanel1_Producto.setLayout(jPanel1_ProductoLayout);
        jPanel1_ProductoLayout.setHorizontalGroup(
            jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_ProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1_ProductoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TXTCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))
                    .addGroup(jPanel1_ProductoLayout.createSequentialGroup()
                        .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1_ProductoLayout.createSequentialGroup()
                                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1_ProductoLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBO_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1_ProductoLayout.createSequentialGroup()
                                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lb_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_Importe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BTN_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1_ProductoLayout.setVerticalGroup(
            jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_ProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBO_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(Lb_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTN_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1_ProductoLayout.createSequentialGroup()
                        .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TXTCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1_ProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(Lb_Importe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1_Detalle_Factura.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1_Detalle_Factura.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Producto ");

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Precio");

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cantidad");

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Importe");

        ListaProducto.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ListaProducto);

        ListaPrecio.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(ListaPrecio);

        ListaCantidad.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(ListaCantidad);

        ListaImporte.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(ListaImporte);

        Lb_SubTotal.setBackground(new java.awt.Color(204, 204, 204));
        Lb_SubTotal.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_SubTotal.setForeground(new java.awt.Color(0, 0, 0));
        Lb_SubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_SubTotal.setText("Lb_SubTotal");
        Lb_SubTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_SubTotal.setOpaque(true);

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("SubTotal :");

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("IGV 18% :");

        Lb_IGV.setBackground(new java.awt.Color(204, 204, 204));
        Lb_IGV.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_IGV.setForeground(new java.awt.Color(0, 0, 0));
        Lb_IGV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_IGV.setText("Lb_IGV");
        Lb_IGV.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_IGV.setOpaque(true);

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Total a Pagar :");

        Lb_Total.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Total.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_Total.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Total.setText("Lb_Total");
        Lb_Total.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Total.setOpaque(true);

        javax.swing.GroupLayout jPanel1_Detalle_FacturaLayout = new javax.swing.GroupLayout(jPanel1_Detalle_Factura);
        jPanel1_Detalle_Factura.setLayout(jPanel1_Detalle_FacturaLayout);
        jPanel1_Detalle_FacturaLayout.setHorizontalGroup(
            jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Lb_SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(Lb_IGV, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(Lb_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                        .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)))
                        .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))))
                    .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1_Detalle_FacturaLayout.setVerticalGroup(
            jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_Detalle_FacturaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1_Detalle_FacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_IGV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1_Producto2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1_Producto2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura N° ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Fecha de Emision ");

        Lb_NumFactura.setBackground(new java.awt.Color(204, 204, 204));
        Lb_NumFactura.setFont(new java.awt.Font("Cambria", 1, 20)); // NOI18N
        Lb_NumFactura.setForeground(new java.awt.Color(0, 0, 0));
        Lb_NumFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_NumFactura.setText("Lb_NumFactura");
        Lb_NumFactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_NumFactura.setOpaque(true);

        Lb_Fec_Emision.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Fec_Emision.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_Fec_Emision.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Fec_Emision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Fec_Emision.setText("Lb_Fec_Emision");
        Lb_Fec_Emision.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Fec_Emision.setOpaque(true);

        jLabel23.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Estado de Factura");

        Lb_Estado_Factura.setBackground(new java.awt.Color(204, 204, 204));
        Lb_Estado_Factura.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Lb_Estado_Factura.setForeground(new java.awt.Color(0, 0, 0));
        Lb_Estado_Factura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Estado_Factura.setText("Estado_Factura");
        Lb_Estado_Factura.setToolTipText("");
        Lb_Estado_Factura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Lb_Estado_Factura.setOpaque(true);

        javax.swing.GroupLayout jPanel1_Producto2Layout = new javax.swing.GroupLayout(jPanel1_Producto2);
        jPanel1_Producto2.setLayout(jPanel1_Producto2Layout);
        jPanel1_Producto2Layout.setHorizontalGroup(
            jPanel1_Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_Producto2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1_Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1_Producto2Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lb_Estado_Factura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1_Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Lb_NumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1_Producto2Layout.createSequentialGroup()
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Lb_Fec_Emision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1_Producto2Layout.setVerticalGroup(
            jPanel1_Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_Producto2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_NumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1_Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Fec_Emision, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel1_Producto2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Estado_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        BTN_Salir.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Salir.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Salir.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Salir.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\salir.png")); // NOI18N
        BTN_Salir.setText("Salir");
        BTN_Salir.setToolTipText("Cerrar Formulario de Facturacion");
        BTN_Salir.setIconTextGap(10);
        BTN_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SalirActionPerformed(evt);
            }
        });

        BTN_Guardar.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Guardar.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Guardar.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Guardar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\Guardar.png")); // NOI18N
        BTN_Guardar.setToolTipText("Guardar  Factura de Ventas");
        BTN_Guardar.setIconTextGap(10);
        BTN_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GuardarActionPerformed(evt);
            }
        });

        BTN_Nuevo.setBackground(new java.awt.Color(204, 204, 204));
        BTN_Nuevo.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        BTN_Nuevo.setForeground(new java.awt.Color(0, 0, 0));
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ruth Sayuri\\Dropbox\\TAREA02\\Semana_08\\src\\Iconos\\Nuevo.png")); // NOI18N
        BTN_Nuevo.setToolTipText("Nuevo  Factura de Ventas");
        BTN_Nuevo.setIconTextGap(10);
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });

        jPanel1_Producto5.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1_Producto5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        LB_Foto3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        LB_Foto3.setForeground(new java.awt.Color(255, 255, 255));
        LB_Foto3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LB_Foto3.setText("Lb_Foto");

        javax.swing.GroupLayout jPanel1_Producto5Layout = new javax.swing.GroupLayout(jPanel1_Producto5);
        jPanel1_Producto5.setLayout(jPanel1_Producto5Layout);
        jPanel1_Producto5Layout.setHorizontalGroup(
            jPanel1_Producto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1_Producto5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LB_Foto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1_Producto5Layout.setVerticalGroup(
            jPanel1_Producto5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1_Producto5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LB_Foto3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(407, 407, 407))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1_Producto5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1_Cliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1_Producto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1_Producto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTN_Salir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(BTN_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(BTN_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel1_Detalle_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 234, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1_Detalle_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(BTN_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1_Producto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1_Producto5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TXTRuc_ClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTRuc_ClienteKeyTyped
        //INVOCAR AL METODO SOLO_NUMEROS
        this.Solo_Numeros(evt);
    }//GEN-LAST:event_TXTRuc_ClienteKeyTyped

    private void BTN_Buscar_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Buscar_ClienteActionPerformed
        // INVOCAR AL METODO BUSCAR CLIENTE
        this.Buscar_Cliente(this.TXTRuc_Cliente.getText());
    }//GEN-LAST:event_BTN_Buscar_ClienteActionPerformed

    private void BTN_Registrar_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Registrar_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_Registrar_ClienteActionPerformed

    private void CBO_ProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBO_ProductoItemStateChanged
        // EVALUARA SI SE HA SELECCIONADO UN ELEMENTO DEL COMBOBOX
        if(evt.getStateChange()== ItemEvent.SELECTED)
        {
            //LIMPIAR LOS TEXTOS DE LAS ETIQUETAS DE PRECIO E IMPORTE
            this.Lb_Precio.setText("0.00");
            this.Lb_Importe.setText("0.00");

            //INVOCAR AL METODO CARGAR DATOS
            this.Cargar_Datos(this.CBO_Producto.getSelectedItem().toString());

            //UBICAR CURSOR EN EL CONTROL TXT CANTIDAD
            this.TXTCantidad.setText("");
            this.TXTCantidad.requestFocus();
        }
    }//GEN-LAST:event_CBO_ProductoItemStateChanged

    private void TXTCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTCantidadKeyPressed
        //EVALUAR SI SE HA PRECIONADO LA TECLA ENTER O TAB
        if(evt.getKeyCode() == KeyEvent.VK_ENTER ||
            evt.getKeyCode() == KeyEvent.VK_TAB)
        {
            //INVOCAR AL METODO MOSTRAR IMPORTE
            this.Mostrar_Importe();

            //HABILITAR BOTONES}
        this.Botones(true);
        }
    }//GEN-LAST:event_TXTCantidadKeyPressed

    private void TXTCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTCantidadKeyReleased
        //EVALUAR SI TXT CANTIDAD TIENE UN VALOR
        if(this.TXTCantidad.getText().length()>0)
        {
            // INVOCAR IMPORTE
            this.Mostrar_Importe();
        }
        else
        {
            //  ESTABLECER IMPORTE
            this.Lb_Importe.setText("0.00");
        }
    }//GEN-LAST:event_TXTCantidadKeyReleased

    private void TXTCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTCantidadKeyTyped
        //INVOCAR AL METODO SOLO_NUMEROS
        this.Solo_Numeros(evt);
    }//GEN-LAST:event_TXTCantidadKeyTyped

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        // INVOCAR AL METODO ELIMINAR_PRODUCTO
        this.Eliminar_Producto();
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void BTN_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AgregarActionPerformed
        // EVALUAR SI EL PRECIO DEL PRODUCTO ES 0
        if(this.Lb_Precio.getText().equals("0.00"))
        {
            //MOSTRAR MENSAJE DE ERROR
//            JOptionPane.showMessageDialog(null,"Elija el Producto a Comprar",
//                this.getTitle(),0);

            //UBICAR CURSO EN EL CONTROL JCOMBOBOC
            this.CBO_Producto.requestFocus();
        }
        //EVALUAR SI SE HA INGRESADO LA CANTIDAD
        //SE VALIDA POR LA LONGITUD DE CARACTERES == 0
        else if(this.TXTCantidad.getText().length()==0)
        {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,"Ingrese la Cantidad a Comprar",
                "Mensaje",0);

            //UBICAR EL CURSOR EN EL CONTROL TXTCANTIDAD
            this.TXTCantidad.requestFocus();
        }
        //EVALUAR SI EL IMPORTE ES 0.00
        else if (this.Lb_Importe.getText().equals("0.00"))
        {
            //MOSTRAR MENSAJE DE ERROR
            JOptionPane.showMessageDialog(null,"Efectuar el Calculo del Importe a Pagar",
               "Mensaje",0);

            //UBICAR EL CURSOR EN EL CONTROL TXTCANTIDAD
            this.TXTCantidad.requestFocus();
        }
        else
        {
            //INVOCAR A METODOS
            this.Mostrar_Importe();
            this.Agregar_Producto();
            this.Calcular();
        }
    }//GEN-LAST:event_BTN_AgregarActionPerformed

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        // INVOCAR METODO SALIE
        this.Salir();
    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        // INVOCAR A METODOS GUARDAR
        this.Guardar_Factura();
        this.Guardar_DetalleFactura();
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        // INVOCAR AL MEETODO LIMPAR CONTROLES
        this.Limpiar_Controles();

        //INCOCAR A LA FUNCION GENERAR FACTURA
        this.Lb_NumFactura.setText(this.Generar_NumFactura());

        //INVOCAR A LA FUNCION FECHA ACTUAL
        this.Lb_Fec_Emision.setText(this.Fecha_Actual());

        //DESHABILITAR BOTONES
        this.Botones_Principales(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Agregar;
    private javax.swing.JButton BTN_Buscar_Cliente;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_Registrar_Cliente;
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JComboBox<String> CBO_Producto;
    private javax.swing.JLabel LB_Foto;
    private javax.swing.JLabel LB_Foto1;
    private javax.swing.JLabel LB_Foto2;
    private javax.swing.JLabel LB_Foto3;
    private javax.swing.JLabel Lb_Apellidos;
    private javax.swing.JLabel Lb_CodCliente;
    private javax.swing.JLabel Lb_Direccion;
    private javax.swing.JLabel Lb_Distrito;
    private javax.swing.JLabel Lb_Estado_Factura;
    private javax.swing.JLabel Lb_Fec_Emision;
    private javax.swing.JLabel Lb_IGV;
    private javax.swing.JLabel Lb_Importe;
    private javax.swing.JLabel Lb_Nombres;
    private javax.swing.JLabel Lb_NumFactura;
    private javax.swing.JLabel Lb_Precio;
    private javax.swing.JLabel Lb_SubTotal;
    private javax.swing.JLabel Lb_Total;
    private javax.swing.JList<String> ListaCantidad;
    private javax.swing.JList<String> ListaImporte;
    private javax.swing.JList<String> ListaPrecio;
    private javax.swing.JList<String> ListaProducto;
    private javax.swing.JTextField TXTCantidad;
    private javax.swing.JTextField TXTRuc_Cliente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1_Cliente;
    private javax.swing.JPanel jPanel1_Detalle_Factura;
    private javax.swing.JPanel jPanel1_Producto;
    private javax.swing.JPanel jPanel1_Producto1;
    private javax.swing.JPanel jPanel1_Producto2;
    private javax.swing.JPanel jPanel1_Producto3;
    private javax.swing.JPanel jPanel1_Producto4;
    private javax.swing.JPanel jPanel1_Producto5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
