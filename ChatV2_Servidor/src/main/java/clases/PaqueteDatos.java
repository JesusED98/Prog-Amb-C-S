/*
 * Jesus Roberto Estrada Diaz
 * 17231071
 * Practica 2.3
 */
package clases;

import java.io.Serializable;

public class PaqueteDatos implements Serializable {
    
    //propiedades
    private String nickName;
    private String ip;
    private String mensaje;
    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
