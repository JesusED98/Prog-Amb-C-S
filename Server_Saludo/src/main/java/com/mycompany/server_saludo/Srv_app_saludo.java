package com.mycompany.server_saludo;

import java.io.IOException;

public class Srv_app_saludo {
    public static void main(String[] args) { 
        // escogemos un puerto para trabajar
        int port = 6066;
        try {
            // creamos un subproceso con el servicio 
            // S_Saludo y como argumento el puerto seleccionado 
            Thread t = new S_Saludo(port); 
            // iniciamos el subproceso/servicio 
            t.start();
        }catch (IOException e) { 
            System.out.println(e.getMessage()); 
        }
    }
}
