/*
 * JESUS ROBERTO ESTRADA DIAZ
 * 17231071
 * PRACTICA 2.2
 */
package com.mycompany.server_chat1;

import java.io.*; 
import java.net.*; 
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class jrfm_SrvChat_App extends javax.swing.JFrame {

    private ObjectOutputStream output; // flujo de salida al cliente 
    private ObjectInputStream input; // flujo de entrada del cliente 
    private ServerSocket server; // server socket 
    private Socket connection; // conexión al cliente 
    private int counter = 1; // contador de número de conexiones
    
    public jrfm_SrvChat_App() {
        super("Server"); // solo nombramos a la aplicación 
        initComponents(); // dejamos esta línea tal cual 
        Txt_entrada.setEditable(false); // inhabilitamos la edición del la caja de texto
        Txt_entrada.setText("Escribe un mensaje...");
    }
    
    private void mostrarMensaje(final String mensajeAMostrar) { 
        //metodo para mostrar un mensaje por medio de un subproceso 
        Runnable hilo = new Runnable() { 
            @Override 
            public void run() { //implementamos subproceso 
                displayArea.append(mensajeAMostrar); 
            } 
        }; SwingUtilities.invokeLater(hilo);
    } //
    
    private void enviarDatos(String mensaje) { 
        try // enviar objeto al cliente 
        { 
            output.writeObject("SERVER>>> " + mensaje);
            output.flush(); 
            mostrarMensaje("\nSERVER>>> " + mensaje);
        } // end try // 
        catch (IOException ioException) { 
            displayArea.append("\nError escribiendo objeto"); 
            JOptionPane.showMessageDialog(this, ioException.getMessage()); 
        } // fin catch  
    }// fin method enviarDatos 
    
    private void esperarConexion() throws IOException { 
        mostrarMensaje("Esperando conexion...\n");
        connection = server.accept(); // permite al servidor aceptar la conexion 
        mostrarMensaje("Conexion " + counter + " recibida de: " + connection.getInetAddress().getHostName()); 
    }// fin metodo esperarConexion
    
    private void obtenerStreams() throws IOException { 
        // configurar flujo de salida para objetos 
        output = new ObjectOutputStream(connection.getOutputStream()); 
        output.flush(); // descarga el búfer de salida para enviar información de encabezado 
        // configurar flujo de entrada para objetos 
        input = new ObjectInputStream(connection.getInputStream());
        mostrarMensaje("\nSe consiguió secuencia de E/S \n"); 
    }// fin method getStreams
    
    private void establecerCampoDeTextoeditable(final boolean editable) { 
        SwingUtilities.invokeLater(new Runnable() { 
                public void run() // establece la capacidad de edición de txt_entrada 
                {
                    Txt_entrada.setEditable(editable);
                } // fin method run 
            } // end inner class
        ); // fin llamada a SwingUtilities.invokeLater }        
    } // fin method setTextFieldEditable
    
    private void procesoDeConexion() throws IOException {
        String mensaje = "Conexión satisfactoria";
        enviarDatos(mensaje); // enviar mensaje de conexión exitosa
        establecerCampoDeTextoeditable(true);
        do // mensajes de proceso enviados desde el cliente 
        {
            try // read message and display it
            {
                mensaje = (String) input.readObject(); // Leer un mensaje nuevo
                mostrarMensaje("\n" + mensaje); // mostrar mensaje
            } // fin try
            catch (ClassNotFoundException classNotFoundException) {
                mostrarMensaje("\nTipo de objeto desconocido recibido");
            } // fin catch
        } while (!mensaje.equals("CLIENT>>> TERMINAR"));
    } // fin method processConnection
    
    private void closeConnection() { 
        mostrarMensaje("\nTerminando conexion...\n");
        establecerCampoDeTextoeditable(false); // deshabilitar enterField 
        try { 
            output.close(); // cerrar flujo de salida
            input.close(); // cerrar flujo de entrada 
            connection.close(); // cerrar socket        
        } // end try
        catch (IOException ioException) { 
            ioException.printStackTrace(); 
        } // end catch
    } // fin method closeConnection
    
    public void ejecutarServidor() { 
        try // configurar el servidor para recibir conexiones; conexiones de proceso 
        { 
            server = new ServerSocket(12345, 100); // crea ServerSocket 
            while (true) { 
                try { 
                    esperarConexion(); // espera una conexión 
                    obtenerStreams(); // obtener flujos de entrada y salida 
                    procesoDeConexion(); // proceso de conexión
                }// fin try 
                catch (EOFException eofException) { 
                    mostrarMensaje("\nEl Servidor terminó la conexión"); 
                } // fin catch 
                finally { 
                    closeConnection();// cerrar connection 
                    counter++;
                } // fin finally   
            } // fin while
        } // fin try // fin try
        catch (IOException ioException) { 
            ioException.printStackTrace(); 
        } // fin catch 
    } // fin method runServer

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        Txt_entrada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Txt_entrada.setText("jTextField1");
        Txt_entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_entradaActionPerformed(evt);
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
                    .addComponent(Txt_entrada))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Txt_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Txt_entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_entradaActionPerformed
        enviarDatos(evt.getActionCommand()); 
        Txt_entrada.setText("");
    }//GEN-LAST:event_Txt_entradaActionPerformed

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
            java.util.logging.Logger.getLogger(jrfm_SrvChat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jrfm_SrvChat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jrfm_SrvChat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jrfm_SrvChat_App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        jrfm_SrvChat_App application = new jrfm_SrvChat_App(); // crea servidor 
        application.setVisible(true); 
        application.ejecutarServidor();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Txt_entrada;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
