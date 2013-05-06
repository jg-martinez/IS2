package simulador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rugnor
 */




public class Taxi {
    public int id;
    public boolean ocupado = false; 
    public Direccion ubicacion = null;
    public Direccion destino = null;
    
    Taxi(int _id) {
        id = _id;
        ubicacion = Direccion.direcciones.get((int)(Math.random()*Direccion.direcciones.size()));
        
       //System.out.println(ubicacion);
    }
    
    
    private int tiempo = 0;
    private boolean sentido = true;
    public static List<Taxi> taxis = new ArrayList<>();
    static boolean continuarSimulacion = true;
    
    public static void simular(int numTaxis) {
        Direccion.simular();
        
        for(int i = 0; i < numTaxis; i++) {
            taxis.add(new Taxi(i));
        }
        
        int ratio = 1000; //milliseconds
        ActionListener temporizador = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for(int j = 0; j<taxis.size(); j++) {
                    taxis.get(j).actualizar();
                }
            }
        };
        new Timer(ratio, temporizador).start();
    }
    
    
    
    void actualizar() {
        
        int idDireccion = Direccion.direcciones.indexOf(ubicacion);
        tiempo--;
        if(tiempo <= 0) {
            if(!ocupado && Math.random() > 0.9) sentido = !sentido;
            if(!ocupado && Math.random() > 0.9) ocupar(Direccion.direcciones.get((int)Math.random()*Direccion.direcciones.size()));
            if(sentido) {
                ubicacion = Direccion.direcciones.get((idDireccion +1)%Direccion.direcciones.size());
            }
            else{
                int auxiliar = idDireccion -1;
                if(auxiliar<0) auxiliar = Direccion.direcciones.size()-1;
                ubicacion = Direccion.direcciones.get(auxiliar);
            }
            tiempo = ubicacion.distanciaSiguiente;
            if(ocupado) {
                if(ubicacion == destino) {
                    ocupado = false;
                    destino = null;
                }
            }
        }
    }
    
    void ocupar(Direccion nuevoDestino) {
        ocupado = true;
        destino = nuevoDestino;
        int idOrigen = Direccion.direcciones.indexOf(ubicacion);
        int idDestino = Direccion.direcciones.indexOf(destino);
        
        int distancia = idOrigen - idDestino;
        sentido = distancia < 0;
        if(distancia == 0) tiempo = 1;
    }
    
}
