/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 3.1
 */
package rmi;

//librerías para uso de RMI
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

/*
UnicastRemoteObject Se utiliza para exportar un objeto remoto con el
"Protocolo de método remoto de Java" y obtener un stub que se comunica
con el objeto remoto.
Los stubs se generan en tiempo de ejecución utilizando objetos
de proxy dinámicos o se generan estáticamente en el tiempo de compilación,
normalmente utilizando la herramienta rmic (RMI Compiler).
*/
// heredamos de UnicastRemoteObject e implementamos la interfaz iRMI
public class Server extends UnicastRemoteObject implements iRMI{
    
    //constructor
    public Server() throws RemoteException {
        // llamamos al constructor de la clase padre
        super();
    }
    // implementación del método suma
    
    @Override
    public int sumar(int n1, int n2) throws RemoteException {
        return n1 + n2;
    }
    
    /*
    Se ejecuta el servidor desde aquí: main
    Se crea primero el registro en un puerto específico vinculándolo
    con el servidor.
    Este vínculo debe tener un nombre que en este caso es "mates".
    *Cuando el cliente llame al método remoto y haga la invocación
    al registro, también deberá usar el mismo nombre y puerto.
    */

    public static void main(String[] args) {
        try{
            Registry registro = LocateRegistry.createRegistry(9999);
            registro.rebind("mates", new Server());
            System.out.println("Servidor activo");
        }
        catch (RemoteException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int resta(int n1, int n2) throws RemoteException {
        return n1 - n2;
    }

    @Override
    public int multi(int n1, int n2) throws RemoteException {
        return n1 * n2;
    }

    @Override
    public int div(int n1, int n2) throws RemoteException {
        return n1/n2;
    }
}
