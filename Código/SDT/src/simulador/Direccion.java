package simulador;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rugnor
 */
public class Direccion {
    String nombre = null;
    int distanciaSiguiente = 1;
    
    Direccion(String _nombre, int _distancia) {
        nombre = _nombre;
        distanciaSiguiente = _distancia;
    }
    
    static List<Direccion> direcciones = new ArrayList<>();
    
    static void simular() {
        direcciones.add(new Direccion("Clare Mount", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Exchequer Promenade", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Davis Terrace", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Majora Street", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Harrington Causeway", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Strand Square", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Stephen Street", (int)(Math.random()*20)));
        direcciones.add(new Direccion("O'Connell Villas", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Brabazon Cottages", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Francis Way", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Wood Avenue", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Parliament Villas", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Grafton Walk", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Harcourt Croft", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Sean Mcdermott Avenue", (int)(Math.random()*20)));
        direcciones.add(new Direccion("Brabazon Yard", (int)(Math.random()*20)));
    }
    
    public String toString() {
        return nombre;
    }
}
