/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 2.3
 */
package GUI;

import java.io.*;
import java.net.*;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import clases.PaqueteDatos;

public class Cliente extends javax.swing.JFrame implements Runnable {

    Thread miHilo;
    
    public Cliente() {
        initComponents();
        miHilo = new Thread(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNick = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        txtMensaje = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        txtNick.setText("jTextField1");

        jLabel2.setText("jLabel2");

        txtIP.setText("jTextField2");

        btnEnviar.setText("jButton1");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        txtMensaje.setText("jTextField3");

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // agregamos a nuestra area de texto lo que escribimos en mensaje
        txtArea.append("Yo: " + txtMensaje.getText() + "\n");
        try {
            // creamos nuestro socket
            //- cambiar ip del servidor-
            Socket socket_destino = new Socket("127.0.0.1", 9999);
            // creamos nuestro paquete de datos con los
            // datos que confiuramos (nick, ip, mensaje)
            PaqueteDatos datos = new PaqueteDatos();
            datos.setNickName(txtNick.getText());
            datos.setIp(txtIP.getText());
            datos.setMensaje(txtMensaje.getText());
            //creamos nuestro objeto de salida a partir de nuestro socket
            try ( ObjectOutputStream objetoSalida =
                    new ObjectOutputStream(socket_destino.getOutputStream())) {
                //El objeto de salida escribe el objeto datos
                objetoSalida.writeObject(datos);
            }//liberamos recursos
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            Cliente cli = new Cliente();
            
            @Override
            public void run() {
                cli.setVisible(true);
                cli.miHilo.start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextField txtNick;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            //creamos un socket servidor para monitorear los mensajes
            // que lleguen al cliente y lo ligamos a un puerto (9090)
            ServerSocket socketServidor = new ServerSocket(9090);
            // cremos un Paquete de datos para poder recibir datos
            PaqueteDatos paqueteRecibido;
            while (true) {
                // empezamos a esuchar el puerto por medio del socket
                try ( Socket socket_escucha = socketServidor.accept()) {
                    //creamos nuestro objeto de entrada para recibir datos
                    ObjectInputStream objetoEntrada
                            = new ObjectInputStream(socket_escucha.getInputStream());
                    //nuestro objeto de entrada lee el paquete que nos envían
                    paqueteRecibido = (PaqueteDatos) objetoEntrada.readObject();
                    //agregamos a nuestra area de texto los datos del paquete
                    txtArea.append(
                            paqueteRecibido.getNickName() +
                                    "; " +
                                    paqueteRecibido.getMensaje()
                    );
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    } //run 
}
