/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 3.2
 */
package rmiPack;

import java.rmi.*;
import java.rmi.registry.*;

public class Cliente {
    public static void main(String[] args) throws RemoteException, NotBoundException{
        Registry registro = LocateRegistry.getRegistry();
        iRemote remoto = (iRemote) registro.lookup("Test");
        System.out.println(remoto.bienvenida("Alaxandra"));

        System.out.println("Ingresando Clientes....");
        remoto.put_Clientes("Ximena");
        remoto.put_Clientes("Gratis");

        remoto.put_Clientes("Xavier");
        remoto.put_Clientes("Pago");

        remoto.put_Clientes("Kevin");
        remoto.put_Clientes("No Pago");
        
        remoto.put_Clientes("Bryan");
        remoto.put_Clientes("No Pago");

        remoto.put_Clientes("Britany");
        remoto.put_Clientes("Gratis");

        remoto.put_Clientes("Paolo");
        remoto.put_Clientes("Pago");

        remoto.put_Clientes("Francesca");
        remoto.put_Clientes("Pago");

        System.out.println("Mostrando Clientes que estan en Netflix");
        System.out.println(remoto.ver_Clientes());
        System.out.println(remoto.ver_Estado());

        int id;
        String nom;
        System.out.println("Buscando el cliente Ximena");
        id = remoto.buscarCliente("Ximena");
        nom = remoto.obtenerCliente(id);
        System.out.println(nom);

        System.out.println("Buscando el cliente Jesus");
        id = remoto.buscarCliente("Jesus");
        nom = remoto.obtenerCliente(id);
        System.out.println(nom);

        System.out.println("Buscando deudores");
        remoto.buscarDeudores();
        System.out.println(remoto.ver_Deudores());
        System.out.println("Buscando Clientes que pagaron su mensualidad");
        remoto.buscarPagados();
        System.out.println(remoto.ver_Pagados());
        System.out.println("Mostrando clientes que tienen un mes gratis");
        remoto.buscarGratis();
        System.out.println(remoto.ver_Gratis());

        System.out.println(remoto.despedida("Alaxandra"));
    }
}
