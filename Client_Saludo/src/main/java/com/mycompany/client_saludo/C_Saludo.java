package com.mycompany.client_saludo;

// agregamos librerías para comunicación y leer y escribir datos (entrada y salida). 
import java.io.*; 
import java.net.*;

public class C_Saludo {
    public static void main(String[] args) {
        // definimos el nombre del servidor 
        String nombreServidor = "localhost"; 
        //definimos puerto 
        int puerto = 6066; 
        
        try {
            System.out.println("Conectando a " + nombreServidor + " en puerto: " + puerto); 

            // (3)
            /* Después de que el servidor está esperando, 
            un cliente crea un objeto Socket, especificando el nombre del servidor 
            y el número de puerto para conectarse. 
            */
            try ( Socket clienteSock = new Socket(nombreServidor, puerto)) {
                //(4) 
                /* Si se establece la comunicación, 
                el cliente ahora tiene un objeto Socket 
                capaz de comunicarse con el servidor 
                */ 

                // getRemoteSocketAddress() Devuelve la dirección del punto 
                // final al que está conectado este socket, 
                // o nula si no está conectado. 
                String add_R = clienteSock.getRemoteSocketAddress().toString(); 
                System.out.println("Recien conectado a " + add_R); 

                //getOutputStream() Devuelve un flujo de salida para este socket, 
                // para poder enviar datos por el socket al servidor. 
                DataOutputStream salida_Datos = new DataOutputStream(clienteSock.getOutputStream()); 
                // escribiendo/enviando datos 
                salida_Datos.writeUTF("Hola desde " + clienteSock.getLocalSocketAddress()); 

                //getInputStream() Devuelve un flujo de entrada para este conector. 
                DataInputStream entrada_Datos = new DataInputStream(clienteSock.getInputStream()); 
                System.out.println("El servidor dice: " + entrada_Datos.readUTF());
            }//cerramos el recurso
        } catch (IOException ex) { 
            System.out.println(ex.getMessage()); 
        }   
    }
}
