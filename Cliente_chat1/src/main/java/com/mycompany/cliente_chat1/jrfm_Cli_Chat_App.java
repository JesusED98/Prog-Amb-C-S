/*
 * JESUS ROBERTO ESTRADA DIAZ
 * 17231071
 * PRACTICA 2.2
 */
package com.mycompany.cliente_chat1;

import java.io.EOFException; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream;
import java.net.InetAddress; 
import java.net.Socket; 
import javax.swing.SwingUtilities;

public class jrfm_Cli_Chat_App extends javax.swing.JFrame {

    private ObjectOutputStream output; // flujo de salida al servidor 
    private ObjectInputStream input; // flujo de entrada desde el servidor 
    private String mensajeDeServidor = ""; // Mensaje del servidor 
    private String chatServer; // host de servidor para esta aplicación 
    private Socket socketCliente; // socket para comunicarse con el servidor
    
    public jrfm_Cli_Chat_App(String host) {
        super("Client"); 
        chatServer = host; // establecer servidor al que este cliente se conecta
        initComponents();
        txt_mensaje.setEditable(false); 
        txt_mensaje.setText("Escribe algo...");
    }
    
    private void mostrarMensaje(final String mensajeAMostrar) { 
        SwingUtilities.invokeLater(new Runnable() { 
            @Override 
            public void run() // actualiza displayArea 
            { 
                displayArea.append(mensajeAMostrar);
            } // fin method run 
        } // fin clase interna anónima 
        ); // fin llamar a SwingUtilities.invokeLater 
    } // fin method displayMessage
    
    private void hacerCajaDeTextoEditable(final boolean editable) { 
        SwingUtilities.invokeLater( 
            new Runnable() { 
                public void run() // establece la editabilidad de enterField 
                { 
                    txt_mensaje.setEditable(editable);
                } // fin method run
           } // fin clase interna anónima 
        ); // fin llamar a SwingUtilities.invokeLater 
    } // fin method setTextFieldEditable
    
    private void enviarDatos(String message) { 
        try // enviar objeto al servidor 
        { 
            output.writeObject("CLIENT>>> " + message);
            output.flush(); // vaciar datos a la salida 
            mostrarMensaje("\nCLIENT>>> " + message); 
        } // end try 
        catch (IOException ioException) { 
            displayArea.append("\nError escribiendo object"); 
        } // fin catch 
    } // fin method sendData
    
    private void cerrarConexion() { 
        mostrarMensaje("\nCerrando conexion");
        hacerCajaDeTextoEditable(false); // deshabilitar enterField 
        try { 
            output.close(); // cerrar flujo de salida 
            input.close(); // cerrar flujo de entrada 1 
            socketCliente.close(); // cerrar socket  
        } // end try 
        catch (IOException ioException) { 
            ioException.printStackTrace(); 
        } // fin catch 
    } // fin method closeConnection
    
    private void procesarConexion() throws IOException {
        // habilite enterField para que el usuario del cliente pueda enviar mensajes 
        hacerCajaDeTextoEditable(true); 
        do // procesar mensajes enviados desde el servidor 
        { 
            try // leer el mensaje y mostrarlo 
            { 
                mensajeDeServidor = (String) input.readObject(); // leer nuevo mensaje 
                mostrarMensaje("\n" + mensajeDeServidor); // mostrar mensaje 
            } // fin try 
            catch (ClassNotFoundException classNotFoundException) { 
                mostrarMensaje("\nTipo de objeto desconocido recibido");
            } // fin catch }
        } while (!mensajeDeServidor.equals("SERVER>>> TERMINAR")); 
    } // fin method processConnection
    
    private void obtenerFlujo() throws IOException { 
        // configurar flujo de salida para objetos 
        output = new ObjectOutputStream(socketCliente.getOutputStream()); 
        // búfer de salida de descarga para enviar información de encabezado 
        output.flush(); 
        // configurar flujo de entrada para objetos 
        input = new ObjectInputStream(socketCliente.getInputStream()); 
        mostrarMensaje("\nflujos obtenidos\n"); 
    } // fin method getStreams
    
    private void conectarAlServidor() throws IOException { 
        mostrarMensaje("Intentando conectar...\n");
        // crear Socket para construir conexion al servidor 
        socketCliente = new Socket(InetAddress.getByName(chatServer), 12345); 
        // mostrar información de conexión 
        mostrarMensaje("Connected to: " + socketCliente.getInetAddress().getHostName());
    } // fin method connectToServer
    
    public void ejecutarCliente() { 
        try // conectar con el servidor, obtener flujos, procesar conexión 
        { 
            conectarAlServidor();// crear un zócalo para hacer la conexión 
            obtenerFlujo(); // obtener los flujos de entrada y salida 
            procesarConexion(); // proceso de conexión 
        } // end try 
        catch (EOFException eofException) { 
            mostrarMensaje("\nEl cliente terminó conexión"); 
        } // end catch 
        catch (IOException ioException) { 
            ioException.printStackTrace(); 
        } // end catch 
        finally { 
            cerrarConexion(); // cerrar connection
        } // fin finally 
    } // fin method runClient   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_mensaje = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_mensaje.setText("jTextField1");
        txt_mensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mensajeActionPerformed(evt);
            }
        });

        displayArea.setColumns(20);
        displayArea.setRows(5);
        jScrollPane1.setViewportView(displayArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(txt_mensaje))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txt_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_mensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mensajeActionPerformed
        enviarDatos(txt_mensaje.getText());
        txt_mensaje.setText("");
    }//GEN-LAST:event_txt_mensajeActionPerformed

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
            java.util.logging.Logger.getLogger(jrfm_Cli_Chat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jrfm_Cli_Chat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jrfm_Cli_Chat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jrfm_Cli_Chat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        jrfm_Cli_Chat_App application; 
        application = new jrfm_Cli_Chat_App("127.0.0.1"); // conectarse a localhost 
        application.setVisible(true); 
        application.ejecutarCliente();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea displayArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_mensaje;
    // End of variables declaration//GEN-END:variables
}
