import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import simulador.BD_Peticiones;
import simulador.Direccion;
import simulador.Peticion;
import simulador.Taxi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//clase para suscribir la lista en el gestor de tooltips centralizado y especificar el contenido del mismo
class ListaTooltip extends JList {
    public ListaTooltip() {
        super();
	
        addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved( MouseEvent e) {
                ToolTipManager.sharedInstance().setDismissDelay(10*60*1000);
                lastMouseEvent = e;
                ListaTooltip lista = (ListaTooltip) e.getSource();
                ListModel model = lista.getModel();
                int index = lista.locationToIndex(e.getPoint());
                lastIndex = index;
                if (index > -1) {
                    lista.setToolTipText(null);
                    Taxi taxi = Taxi.taxis.get(index);
                    String text = SDT_UI.conectado ? String.format("El taxi %05d está %s." , taxi.id, taxi.ocupado ? "ocupado" : "libre" ) :  String.format("Taxi %05d  desconectado." , taxi.id);
                    lista.setToolTipText(text);
                }
            }
        }
        );
    }

    public String getToolTipText(MouseEvent e){
        return super.getToolTipText();
    }
    public static MouseEvent lastMouseEvent;
    public static int lastIndex = -1;
}



public class SDT_UI extends javax.swing.JFrame {
    
    //clase para el thread que descarga el log de peticiones
    class threadEscribirPeticiones extends Thread {

        public void run() {
            descargarHistorial.setEnabled(false);
            cancelarDescarga.setEnabled(true);
            
            try{
                File file = new File("logPeticiones.txt");
                if(file.exists()) {
                    file.delete();
                    file.createNewFile();
                }
                
                FileWriter fstream = new FileWriter("logPeticiones.txt");
                BufferedWriter archivo = new BufferedWriter(fstream);
                barraProgreso.setValue(0);
                Peticion[] peticiones = BD_Peticiones.getPeticiones();
                for(int i = 0; i < peticiones.length; i++) {
                    if(cancelarLog)  break;
                    barraProgreso.setValue((int)(((float)(i+1)/(float)peticiones.length)*100.0));
                    archivo.write(peticiones[i].toString());
                    try {  //tiempo extra para las pruebas
                        Thread.sleep(1);  
                    }  
                        catch (InterruptedException e) {  
                    }

                }

                archivo.close();

                if(cancelarLog) cancelarLog = false;
                else barraProgreso.setValue(100);
            }
            catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            descargarHistorial.setEnabled(true);
            cancelarDescarga.setEnabled(false);
        }

    }
    
    public boolean cancelarLog = false; //flag que escucha el proceso de generar el log de peticiones para cancelarse
    
    /**
     * Creates new form SDT_UI
     */
    public SDT_UI() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/t.png")).getImage());
        Taxi.simular(30);//inicializa el simulador con N taxis
        botonRefrescarActionPerformed(null);//inicializa la tabla
        int ratio = 1000; //frecuencia de actualización de la tabla en milisegundos
        ActionListener temporizador = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonRefrescarActionPerformed(null);
            }
        };
        new Timer(ratio, temporizador).start();//temporizador para la tabla
        
        
        listaTaxis.addListSelectionListener(new ListSelectionListener()
        {
          public void valueChanged(ListSelectionEvent ev)
          {
            listaTaxisMouseClicked(null);
          } 
        });//listener para el evento de hacer click en la tabla con el ratón
        
        peticion_direccion.removeAll();//reinicia el comboBox de direcciones
        List<String> lista = new ArrayList<String>();
        for(Iterator<Direccion> direccionIterador = Direccion.direcciones.iterator(); direccionIterador.hasNext();) {
            Direccion direccion = direccionIterador.next();
            lista.add(direccion.toString());
        }
        peticion_direccion.setModel(new javax.swing.DefaultComboBoxModel(lista.toArray()));//llena el comboBox de direcciones
        
        BD_Peticiones.rellenarRandom(2000); //inicia la base de datos simulada con 20000 peticiones
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaSolicitar = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        solicitarTaxi = new javax.swing.JButton();
        cerrarVentanaSolicitar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ventanaError = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        solicitarTaxiError = new javax.swing.JButton();
        cerrarVentanaError = new javax.swing.JButton();
        mensajeError = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        secciones = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaTaxis = listaTaxis = new ListaTooltip();
        botonRefrescar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tituloUbicacion = new javax.swing.JLabel();
        tituloEstado = new javax.swing.JLabel();
        tituloDestino = new javax.swing.JLabel();
        datosTaxi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datos_id = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        datos_ubicacion = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        datos_destino = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        peticion_nombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        peticion_direccion = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        peticion_telefono = new javax.swing.JTextField();
        buscarTaxi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        barraProgreso = new javax.swing.JProgressBar();
        descargarHistorial = new javax.swing.JButton();
        cancelarDescarga = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        iconoConexion = new javax.swing.JLabel();
        botonConectar = new javax.swing.JButton();

        ventanaSolicitar.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        ventanaSolicitar.setMinimumSize(new java.awt.Dimension(350, 300));
        ventanaSolicitar.setResizable(false);
        //ventanaSolicitar.setType(java.awt.Window.Type.UTILITY);
        ventanaSolicitar.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                ventanaSolicitarWindowActivated(evt);
            }
        });

        solicitarTaxi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        solicitarTaxi.setText("00001");
        solicitarTaxi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarTaxiActionPerformed(evt);
            }
        });

        cerrarVentanaSolicitar.setText("CANCELAR");
        cerrarVentanaSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarVentanaSolicitarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("El taxi disponible mas cercano es:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cerrarVentanaSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solicitarTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel12)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel12)
                .addGap(36, 36, 36)
                .addComponent(solicitarTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(cerrarVentanaSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout ventanaSolicitarLayout = new javax.swing.GroupLayout(ventanaSolicitar.getContentPane());
        ventanaSolicitar.getContentPane().setLayout(ventanaSolicitarLayout);
        ventanaSolicitarLayout.setHorizontalGroup(
            ventanaSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventanaSolicitarLayout.setVerticalGroup(
            ventanaSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ventanaError.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        ventanaError.setMinimumSize(new java.awt.Dimension(350, 300));
        ventanaError.setResizable(false);
        //ventanaError.setType(java.awt.Window.Type.UTILITY);
        ventanaError.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                ventanaErrorWindowActivated(evt);
            }
        });

        solicitarTaxiError.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        solicitarTaxiError.setText("00001");
        solicitarTaxiError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarTaxiErrorActionPerformed(evt);
            }
        });

        cerrarVentanaError.setText("CANCELAR");
        cerrarVentanaError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarVentanaErrorActionPerformed(evt);
            }
        });

        mensajeError.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mensajeError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensajeError.setText("¡ERROR!");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Reintentar: ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(mensajeError)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(solicitarTaxiError, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(cerrarVentanaError, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(mensajeError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solicitarTaxiError, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(cerrarVentanaError, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout ventanaErrorLayout = new javax.swing.GroupLayout(ventanaError.getContentPane());
        ventanaError.getContentPane().setLayout(ventanaErrorLayout);
        ventanaErrorLayout.setHorizontalGroup(
            ventanaErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventanaErrorLayout.setVerticalGroup(
            ventanaErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listaTaxis.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        listaTaxis.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaTaxis.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                listaTaxisMouseWheelMoved(evt);
            }
        });
        listaTaxis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTaxisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaTaxis);

        botonRefrescar.setText("Refrescar");
        botonRefrescar.setMaximumSize(new java.awt.Dimension(79, 26));
        botonRefrescar.setMinimumSize(new java.awt.Dimension(79, 26));
        botonRefrescar.setPreferredSize(new java.awt.Dimension(79, 26));
        botonRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRefrescarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Taxi");

        tituloUbicacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloUbicacion.setText("Ubicación");

        tituloEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloEstado.setText("Estado");

        tituloDestino.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tituloDestino.setText("Destino");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(89, 89, 89)
                        .addComponent(tituloUbicacion)
                        .addGap(90, 90, 90)
                        .addComponent(tituloEstado)
                        .addGap(82, 82, 82)
                        .addComponent(tituloDestino)
                        .addGap(0, 342, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tituloUbicacion)
                    .addComponent(tituloEstado)
                    .addComponent(tituloDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datosTaxi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        datosTaxi.setEnabled(false);

        datos_id.setFocusable(false);
        jScrollPane2.setViewportView(datos_id);

        datos_ubicacion.setFocusable(false);
        jScrollPane3.setViewportView(datos_ubicacion);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ubicación:");

        datos_destino.setFocusable(false);
        jScrollPane4.setViewportView(datos_destino);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Destino:");

        jCheckBox1.setText("Ocupado");
        jCheckBox1.setEnabled(false);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBox1.setOpaque(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Datos del taxi");

        javax.swing.GroupLayout datosTaxiLayout = new javax.swing.GroupLayout(datosTaxi);
        datosTaxi.setLayout(datosTaxiLayout);
        datosTaxiLayout.setHorizontalGroup(
            datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datosTaxiLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(datosTaxiLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1))
                    .addGroup(datosTaxiLayout.createSequentialGroup()
                        .addGroup(datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosTaxiLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(datosTaxiLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(8, 8, 8)))
                        .addGroup(datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datosTaxiLayout.setVerticalGroup(
            datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datosTaxiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datosTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datosTaxi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datosTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        secciones.addTab("Taxis", jPanel1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Nombre: ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Dirección: ");

        peticion_direccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Teléfono: ");

        buscarTaxi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buscarTaxi.setText("Buscar taxi");
        buscarTaxi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTaxiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buscarTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(peticion_nombre))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(peticion_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(peticion_telefono))))
                .addContainerGap(446, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peticion_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(peticion_direccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peticion_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buscarTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );

        secciones.addTab("Nueva Petición", jPanel3);

        descargarHistorial.setText("Descargar historial de peticiones");
        descargarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descargarHistorialActionPerformed(evt);
            }
        });

        cancelarDescarga.setText("Cancelar");
        cancelarDescarga.setEnabled(false);
        cancelarDescarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarDescargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(descargarHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(cancelarDescarga, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descargarHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(cancelarDescarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(342, Short.MAX_VALUE))
        );

        secciones.addTab("Log Peticiones", jPanel4);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Sistema de Despacho de Taxis");

        iconoConexion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/connect.png"))); // NOI18N
        iconoConexion.setAlignmentY(0.0F);
        iconoConexion.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/disconnect.png"))); // NOI18N
        iconoConexion.setIconTextGap(0);

        botonConectar.setText("Desconectar");
        botonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(secciones)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonConectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(botonConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(secciones, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Solicitar lista de taxis
    private void botonRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRefrescarActionPerformed
        listaTaxis.removeAll(); //reinicia la lista
        List<String> lista = new ArrayList<String>();
        for(Iterator<Taxi> taxiIterador = Taxi.taxis.iterator(); taxiIterador.hasNext();) {
            Taxi taxi = taxiIterador.next();
            //lista.add("ID del taxi: \t" + taxi.id + "\t\tUbicación actual: \t" + taxi.ubicacion + " ");
            if(conectado) lista.add(String.format("%05d%-14s%-28s%-22s%-28s" , taxi.id,"", taxi.ubicacion, taxi.ocupado ? "Ocupado" : "Libre", taxi.ocupado ? taxi.destino : "" ));
            else lista.add(String.format("%05d" , taxi.id));
        } //solicita todos los taxis
        listaTaxis.setListData(lista.toArray()); //rellena la lista
        refrescarDatos();
    }//GEN-LAST:event_botonRefrescarActionPerformed

    void refrescarDatos() {
        if(ListaTooltip.lastMouseEvent != null) listaTaxis.dispatchEvent(ListaTooltip.lastMouseEvent);
        int id = ultimoTaxiClicado;
        if(id == -1) return;
        Taxi taxi = Taxi.taxis.get(id); //obtiene los datos del taxi
        datos_id.setText(String.format("%05d" , id)); 
        if(taxi.ocupado) datos_destino.setText(taxi.destino.toString()); //llena el campo de destino
        else datos_destino.setText(""); //o lo vacia, si no exite
        datos_ubicacion.setText(taxi.ubicacion.toString()); //campo de ubicacion
        jCheckBox1.setSelected(taxi.ocupado); //campo de ocupado
        datosTaxi.setVisible(true); //se asegura de que la ventana de datos es visible
    }
    
    int ultimoTaxiClicado = -1;
    //Mostrar datos de un taxi
    private void listaTaxisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTaxisMouseClicked
        if(!conectado) return;
        
        int id = listaTaxis.getMinSelectionIndex(); //obtiene el id seleccionado
        if(id != -1) ultimoTaxiClicado = id;
        refrescarDatos();
    }//GEN-LAST:event_listaTaxisMouseClicked
    
    private Taxi taxiMasCercano = null; //ultimo taxi designado como optimo
    
    Peticion ultimaPeticion; //ultimo objeto Peticion construido, listo para entrar en la BBDD
    
    //buscar un taxi optimo y mostrar ventana de asignarlo
    private void buscarTaxiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTaxiActionPerformed
        if(peticion_nombre.getText() == "") return;
        try{
            if(Integer.parseInt(peticion_telefono.getText()) < 600000000) return;
        }
        catch(Exception e){
            return;
        }
        peticion_direccion.setEnabled(false); //se desactiva parte de la interfaz
        peticion_nombre.setEnabled(false);
        peticion_telefono.setEnabled(false);
        //secciones.setEnabled(false);
        buscarTaxi.setEnabled(false);
        
        ultimaPeticion = new Peticion(peticion_nombre.getText(), Direccion.direcciones.get(peticion_direccion.getSelectedIndex()), Integer.parseInt(peticion_telefono.getText()), new Date() );
        
        int inicializadorTaxi = 0; //se inicializa la busqueda buscando el primer taxi libre
        while(inicializadorTaxi < Taxi.taxis.size() && Taxi.taxis.get(inicializadorTaxi).ocupado) {
            inicializadorTaxi++;
        }
        
        if(inicializadorTaxi >= Taxi.taxis.size()) { //si no encuentra ninguno libre, reactiva la interfaz y no realiza mas acciones
            cerrarVentanaSolicitarActionPerformed(null);
            return;
        }
        
        //se busca, de entre todos los taxis libres en este momento, el que menos tardaria en llegar al destino solicitado
        Taxi masCercano = Taxi.taxis.get(inicializadorTaxi);
        Direccion destino = Direccion.direcciones.get(peticion_direccion.getSelectedIndex());
        int minimaDistancia = calcularDistancia(masCercano.ubicacion, destino);
        for(Iterator<Taxi> taxiIterador = Taxi.taxis.iterator(); taxiIterador.hasNext();) { 
            Taxi taxi = taxiIterador.next();
            if(!taxi.ocupado) {
                int distanciaTaxiActual = calcularDistancia(taxi.ubicacion, destino);
                if(distanciaTaxiActual < minimaDistancia) {
                    minimaDistancia = distanciaTaxiActual;
                    masCercano = taxi;
                }
            }
        }
        
        taxiMasCercano = masCercano;
        ventanaSolicitar.setLocationRelativeTo(null); //se inicializa y lanza el dialogo de asignar taxi
        ventanaError.setLocationRelativeTo(null);
        ventanaSolicitar.setVisible(true);
    }//GEN-LAST:event_buscarTaxiActionPerformed

    //se intenta mandar un mensaje al taxi optimo
    private void solicitarTaxiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarTaxiActionPerformed
        if(!taxiMasCercano.ocupado) { //si esta libre, intentar enviar el mensaje
            //si consigue ocupar el taxi, cierra el dialogo y reactiva interfaz
            if(taxiMasCercano.ocupar(Direccion.direcciones.get(peticion_direccion.getSelectedIndex()))==0) {
                BD_Peticiones.anadir(ultimaPeticion);
                cerrarVentanaSolicitarActionPerformed(null); 
            }
            else {//si no, lanza el dialogo de error con el mensaje correspondiente
               mensajeError.setText("¡ERROR! Fallo en la comunicación."); 
                ventanaSolicitar.setVisible(false);
                ventanaError.setVisible(true); 
            }
        }
        else { //si el taxi esta ocupado, lanzar error
            mensajeError.setText("¡ERROR! El taxi ahora está ocupado.");
            ventanaSolicitar.setVisible(false);
            ventanaError.setVisible(true);
        }
    }//GEN-LAST:event_solicitarTaxiActionPerformed

    //se cierra la ventana de asignar un taxi y reactiva la interfaz
    private void cerrarVentanaSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarVentanaSolicitarActionPerformed
        ventanaError.setVisible(false);
        ventanaSolicitar.setVisible(false);
        peticion_direccion.setEnabled(true);
        peticion_nombre.setEnabled(true);
        peticion_telefono.setEnabled(true);
        secciones.setEnabled(true);
        buscarTaxi.setEnabled(true);
        peticion_nombre.setText("");
        peticion_telefono.setText("");
    }//GEN-LAST:event_cerrarVentanaSolicitarActionPerformed

    //actualizar el id del taxi optimo al abrir el dialogo de asignar
    private void ventanaSolicitarWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ventanaSolicitarWindowActivated
        solicitarTaxi.setText(String.format("%05d" , taxiMasCercano.id));
    }//GEN-LAST:event_ventanaSolicitarWindowActivated

    //se hace click en reintentar mandar el mensaje
    private void solicitarTaxiErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarTaxiErrorActionPerformed
        solicitarTaxiActionPerformed(null);
    }//GEN-LAST:event_solicitarTaxiErrorActionPerformed

    //actualizar el id del taxi optimo al abrir el dialogo de error
    private void ventanaErrorWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ventanaErrorWindowActivated
        solicitarTaxiError.setText(String.format("%05d" , taxiMasCercano.id));
    }//GEN-LAST:event_ventanaErrorWindowActivated

    //se cierra la ventana de error y reactiva la interfaz
    private void cerrarVentanaErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarVentanaErrorActionPerformed
        cerrarVentanaSolicitarActionPerformed(null);
    }//GEN-LAST:event_cerrarVentanaErrorActionPerformed

    //caza el evento de movel la rueda del raton para evitar que el tooltip se bloquee
    private void listaTaxisMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_listaTaxisMouseWheelMoved
        listaTaxis.setToolTipText(null);
    }//GEN-LAST:event_listaTaxisMouseWheelMoved

    //descargar el historial de logs
    private void descargarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descargarHistorialActionPerformed
        descargarHistorial.setEnabled(false);
        (new threadEscribirPeticiones()).start();
    }//GEN-LAST:event_descargarHistorialActionPerformed

    //boton para cancelar la descarga
    private void cancelarDescargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarDescargaActionPerformed
        cancelarLog = true;
    }//GEN-LAST:event_cancelarDescargaActionPerformed

    public static boolean conectado = true;
    
    //se lanza al realizarse un cambio en el estado de la conexión
    private void eventoConexion() {
        secciones.setEnabledAt(1, conectado);
        if(secciones.getSelectedIndex() == 1) secciones.setSelectedIndex(0);
        iconoConexion.setEnabled(conectado);
        botonConectar.setText(conectado?"Desconectar":"Conectar");
        datosTaxi.setVisible(conectado);
        tituloDestino.setVisible(conectado);
        tituloUbicacion.setVisible(conectado);
        tituloEstado.setVisible(conectado);
        botonRefrescar.setSize(120, 26);
    }
    
    private void botonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConectarActionPerformed
        conectado = !conectado;
        eventoConexion();
    }//GEN-LAST:event_botonConectarActionPerformed

    Taxi taxiActual = null;
    
    //funcion auxiliar que calcula el tiempo que tardaria un taxi cualquiera en llegar de un punto a otro
    public static int calcularDistancia(Direccion origen, Direccion destino) {
        Direccion actual = origen;
        int distancia = 0;
        int distancia2 = 0;
        int indiceSiguiente = Direccion.direcciones.indexOf(origen);
        while(actual != destino) {
            distancia += actual.distanciaSiguiente;
            indiceSiguiente = (indiceSiguiente + 1)% Direccion.direcciones.size();
            actual = Direccion.direcciones.get(indiceSiguiente);
        }
        actual = origen;
        indiceSiguiente = Direccion.direcciones.indexOf(origen);
        while(actual != destino) {
            distancia2 += actual.distanciaSiguiente;
            indiceSiguiente--;
            if(indiceSiguiente < 0) indiceSiguiente = Direccion.direcciones.size()-1;
            actual = Direccion.direcciones.get(indiceSiguiente);
        }
        
        return (distancia < distancia2 ? distancia : distancia2);
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SDT_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SDT_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SDT_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SDT_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SDT_UI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar barraProgreso;
    private javax.swing.JButton botonConectar;
    private javax.swing.JButton botonRefrescar;
    private javax.swing.JButton buscarTaxi;
    private javax.swing.JButton cancelarDescarga;
    private javax.swing.JButton cerrarVentanaError;
    private javax.swing.JButton cerrarVentanaSolicitar;
    private javax.swing.JPanel datosTaxi;
    private javax.swing.JTextPane datos_destino;
    private javax.swing.JTextPane datos_id;
    private javax.swing.JTextPane datos_ubicacion;
    private javax.swing.JButton descargarHistorial;
    private javax.swing.JLabel iconoConexion;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public ListaTooltip listaTaxis;
    private javax.swing.JLabel mensajeError;
    private javax.swing.JComboBox peticion_direccion;
    private javax.swing.JTextField peticion_nombre;
    private javax.swing.JTextField peticion_telefono;
    private javax.swing.JTabbedPane secciones;
    private javax.swing.JButton solicitarTaxi;
    private javax.swing.JButton solicitarTaxiError;
    private javax.swing.JLabel tituloDestino;
    private javax.swing.JLabel tituloEstado;
    private javax.swing.JLabel tituloUbicacion;
    private javax.swing.JDialog ventanaError;
    private javax.swing.JDialog ventanaSolicitar;
    // End of variables declaration//GEN-END:variables
}
