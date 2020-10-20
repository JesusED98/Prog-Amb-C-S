/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 3.2
 */
package rmiPack;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws RemoteException, 
            AlreadyBoundException {
        //arrayLists con strings
        ArrayList<String> lClientes = new ArrayList<>();//clientes
        ArrayList<String> lEstadoCli = new ArrayList<>();// estado del pago
        ArrayList<String> lDeudores = new ArrayList<>();//clientes deudores
        ArrayList<String> lPagados = new ArrayList<>();//clientes que han pagado
        ArrayList<String> lGratis = new ArrayList<>();//clientes modo gratis

        Remote stub;
        stub = UnicastRemoteObject.exportObject(new iRemote(){
            @Override
            public String bienvenida(String nombre) throws RemoteException{
            return "Bienvenido a Netflix " + nombre;
            }

            @Override
            public String despedida(String nombre) throws RemoteException{
            return "Hasta Luego " + nombre;
            }

            @Override
            public ArrayList<String> put_Clientes(String cliente) throws RemoteException{
            lClientes.add(cliente);
                return lClientes;
            }

            @Override
            public ArrayList<String> ver_Clientes() throws RemoteException{
                return lClientes;
            }

            @Override
            public ArrayList<String> put_Estado(String estado) throws RemoteException {
                lEstadoCli.add(estado);
                return lEstadoCli;
            }

            @Override
            public ArrayList<String> ver_Estado() throws RemoteException{
            return lEstadoCli;
            }

            @Override
            public int buscarCliente(String nombre) throws RemoteException{
                int aux = -1;
                for (int i = 0; i < lClientes.size(); i++){
                    if (lClientes.get(i).equals(nombre)){
                        aux = i;
                    }
            }
            return aux;
            }

            @Override
            public String obtenerCliente(int ubicacion) throws RemoteException {
            if (ubicacion != -1){
                return lClientes.get(ubicacion);
            } else {
                return ("No existe el cliente");}
            }

            @Override
            public String obtenerEstado(int ubicacion) throws RemoteException {
                if (ubicacion != -1){
                    return lEstadoCli.get(ubicacion);
                } else {
                    return ("");
                }
            }

            @Override
            public ArrayList<String> buscarDeudores() throws RemoteException {
                for (int i = 0; i < lEstadoCli.size(); i++){
                    if (lEstadoCli.get(i).equals("No Pago")){
                        lDeudores.add(lClientes.get(i));
                    }
                }
                return lDeudores;
            }

            @Override
            public ArrayList<String> ver_Deudores() throws RemoteException{
                return lDeudores;
            }

            @Override
            public ArrayList<String> buscarPagados() throws RemoteException {
                for (int i = 0; i < lEstadoCli.size(); i++){
                    if (lEstadoCli.get(i).equals("Pago")){
                        lPagados.add(lClientes.get(i));}
                }
                return lPagados;
            }

            @Override
            public ArrayList<String> ver_Pagados() throws RemoteException {
                return lPagados;
            }

            @Override
            public ArrayList<String> buscarGratis() throws RemoteException {
                for (int i = 0; i < lEstadoCli.size(); i++){
                    if (lEstadoCli.get(i).equals("Gratis")){
                        lGratis.add(lClientes.get(i));
                    }
                }
                return lGratis;
            }

            @Override
            public ArrayList<String> ver_Gratis() throws RemoteException {
                return lGratis;
            }
        }, 0);
        Registry registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registro.bind("Test", stub);
    }
}
