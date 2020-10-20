/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 3.2
 */
package rmiPack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface iRemote extends Remote{
    //interfaz remoto con los metodos que vamos a usar
    
    String bienvenida(String nombre) throws RemoteException;

    String despedida(String nombre) throws RemoteException;

    //para ingresar clientes
    ArrayList<String> put_Clientes(String cliente) throws RemoteException;

    //para visualizar clientes
    ArrayList<String> ver_Clientes() throws RemoteException;

    //para ingresar el estado del cliente, pago, nopago, gratis
    ArrayList<String> put_Estado(String estado) throws RemoteException;

    //para visualizar el estado de clientes
    ArrayList<String> ver_Estado() throws RemoteException;

    //para buscar el cliente obteniendo se ID
    int buscarCliente(String nombre) throws RemoteException;

    //para visualizar cliente segun ID
    String obtenerCliente(int ubicacion) throws RemoteException;

    //para visualizar el estado de pago segun ID
    String obtenerEstado(int ubicacion) throws RemoteException;

    //para buscar deudores
    ArrayList<String> buscarDeudores() throws RemoteException;

    //para ver deudores
    ArrayList<String> ver_Deudores() throws RemoteException;

    //para buscar los que han pagado
    ArrayList<String> buscarPagados() throws RemoteException;

    //para mostrar los que han pagado
    ArrayList<String> ver_Pagados() throws RemoteException;

    //para buscar los que estan en modo gratis
    ArrayList<String> buscarGratis() throws RemoteException;

    //para mostrar los que estan gratis
    ArrayList<String> ver_Gratis() throws RemoteException;
}
