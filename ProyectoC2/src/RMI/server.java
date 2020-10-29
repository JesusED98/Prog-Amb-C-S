/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Proyecto Unidad RMI
 */

package RMI;

//librerias para el uso de RMI
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

/*
UnicastRemoteObject se utiliza para exportar un objeto remoto con el
"Protocolo de método remoto de Java" y obtener un stub que se comunica
con el objeto remoto.
Se hereda UnicastRemoteObject y se implementa la interfaz IRMI
*/
public class server extends UnicastRemoteObject implements IRMI{
    
    //Constructor
    public server() throws RemoteException {
        // Constructor de la clase padre
        super();
    }
    
    /*
    Se ejecuta el servidor desde el main
    Se crea primero el registro en un puerto específico vinculándolo
    con el servidor. El vincilo tiene nombre y en la consola aparece
    una leyenda de que el servidor esta activo
    */
    public static void main(String[] args) {
        try{
            Registry registro = LocateRegistry.createRegistry(9999);
            registro.rebind("mates", new server());
            System.out.println("Servidor activo");
        }
        catch (RemoteException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    // implementación del método sumar1 = numerador --> n
    @Override
    public int sumar1(int n1, int d1, int n2, int d2) throws RemoteException {
        return n1 * d2 + d1 * n2;
    }
    
    // implementación del método sumar2 = denominador --> d
    @Override
    public int sumar2(int d1, int d2) throws RemoteException {
        return d1 * d2;
    }
    
    // implementación del método resta1 = numerador
    @Override
    public int resta1(int n1, int d1, int n2, int d2) throws RemoteException {
        return n1 * d2 - d1 * n2;
    }

    // implementación del método resta2 = denominador
    @Override
    public int resta2(int d1, int d2) throws RemoteException {
        return d1 * d2;
    }

    // implementación del método multi1 = numerador
    @Override
    public int multi1(int n1, int n2) throws RemoteException {
        return n1 * n2;
    }

    // implementación del método multi2 = denominador
    @Override
    public int multi2(int d1, int d2) throws RemoteException {
        return d1 * d2;
    }

    // implementación del método div1 = numerador
    @Override
    public int div1(int n1, int d2) throws RemoteException {
        return n1 * d2;
    }

    // implementación del método div2 = denominador
    @Override
    public int div2(int d1, int n2) throws RemoteException {
        return d1 * n2;
    }      
}
