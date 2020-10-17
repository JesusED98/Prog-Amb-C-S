/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 3.1
 */
package rmi;

import java.rmi.*;

public interface iRMI extends Remote{
    public int sumar(int n1, int n2)throws RemoteException;
    public int resta(int n1, int n2)throws RemoteException;
    public int multi(int n1, int n2)throws RemoteException;
    public int div(int n1, int n2)throws RemoteException;
}
