/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Proyecto Unidad RMI
 */

package RMI;

import java.rmi.*;

public interface IRMI extends Remote{
    //Numerador = n
    public int sumar1(int n1, int d1, int n2, int d2)throws RemoteException;
    //Denominador = d
    public int sumar2(int d1, int d2)throws RemoteException;
    //Numerador
    public int resta1(int n1, int d1, int n2, int d2)throws RemoteException;
    //Denominador
    public int resta2(int d1, int d2)throws RemoteException;
    //Numerador
    public int multi1(int n1, int n2)throws RemoteException;
    //Denominador
    public int multi2(int d1, int d2)throws RemoteException;
    //Numerador
    public int div1(int n1, int d2)throws RemoteException;
    //Denominador
    public int div2(int d1, int n2)throws RemoteException; 
}
