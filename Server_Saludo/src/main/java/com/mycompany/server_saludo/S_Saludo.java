package com.mycompany.server_saludo;
// Java Net de las API J2SE contiene una colección de clases e interfaces que 
// proporcionan los detalles de comunicación de bajo nivel

import java.net.*; 
//Java IO es una API que está dirigida a leer 
//y escribir datos (entrada y salida).
import java.io.*;

public class S_Saludo extends Thread{
    // la clase java.net.ServerSocket la utilizamos para obtener un puerto y 
    // escuchar las solicitudes de los clientes. 
    private final ServerSocket socketServidor; 
    
    // Inicializamos el socket de nuestro servidor 
    public S_Saludo(int puerto) throws IOException { 
        //(1) 
        /* El servidor crea una instancia de ServerSocket, que indica por cuál 
        número de puerto se va a producir la comunicación. 
        */ 
        socketServidor = new ServerSocket(puerto); 
        //limitamos la espera del servidor a 60 segundos = 60 000 milisegundos 
        socketServidor.setSoTimeout(60000);
    }
    
    @Override
    public void run() { 
        // monitoreamos el puerto 
        while (true) { 
            try { 
                /* getLocalPort() Devuelve el puerto en el que escucha el 
                socket del servidor. 
                Este método es útil si se pasó como el número de puerto en un 
                constructor o se dejó que el servidor encuentre un puerto 
                automáticamente */ 
                int p = socketServidor.getLocalPort();
                System.out.println("Esperando al cliente en el puerto " + p + "..."); 

                //(2) 
                /* El servidor invoca el método accept() de la clase ServerSocket. 
                Este método espera hasta que un cliente se conecta al servidor 
                en el puerto dado. 
                */ 
                //(5) 
                /* El método accept () devuelve una referencia a un nuevo socket 
                en el servidor que está conectado al socket del cliente. 
                */ 
                try ( Socket S = socketServidor.accept()) { 
                    
                    // getRemoteSocketAddress() Devuelve la dirección del punto 
                    // final al que está conectado este socket, 
                    // o nula si no está conectado. 
                    String add_R = S.getRemoteSocketAddress().toString(); 
                    System.out.println("Recién conectado a " + add_R); 
                    
                    /* Una vez que se establecen las conexiones, 
                    la comunicación puede ocurrir usando flujos de E / S. 
                    Cada socket tiene un OutputStream y un InputStream. 
                    OutputStream del cliente está conectado a InputStream del servidor, e 
                    InputStream del cliente está conectado a OutputStream del servidor. 
                    */ 
                    //getInputStream() Devuelve un flujo de entrada para este conector. 
                    DataInputStream datos_Entrada = new DataInputStream(S.getInputStream());
                    System.out.println(datos_Entrada.readUTF()); 

                    //getOutputStream() Devuelve un flujo de salida para este socket, 
                    // para poder enviar datos por el socket al cliente. 
                    DataOutputStream datos_Salida = new DataOutputStream(S.getOutputStream()); 

                    //getLocalSocketAddress() Devuelve la dirección del punto final 
                    // al que está vinculado este socket. 
                    String add_L = S.getLocalSocketAddress().toString(); 
                    // escribiendo/enviando datos 
                    datos_Salida.writeUTF("Gracias por conectarte a " + add_L + "\nAdios!");
                }
            }catch (SocketTimeoutException stex) { 
                System.out.println("Socket desconectado!\n"+stex.getMessage()); 
                break; 
            } catch (IOException ex) { 
                System.out.println(ex.getMessage()); 
                break; 
            }
        } 
    }
}
