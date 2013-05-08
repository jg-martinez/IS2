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
    public int distanciaSiguiente = 1;
    
    Direccion(String _nombre, int _distancia) {
        nombre = _nombre;
        distanciaSiguiente = _distancia;
    }
    
    public static List<Direccion> direcciones = new ArrayList<Direccion>();
    
    private static int tiempoMaximo = 20;
    static void simular() {
        direcciones.add(new Direccion("Clare Mount", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Exchequer Promenade", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Davis Terrace", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Majora Street", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Harrington Causeway", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Strand Square", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Stephen Street", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("O'Connell Villas", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Brabazon Cottages", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Francis Way", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Wood Avenue", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Parliament Villas", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Grafton Walk", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Harcourt Croft", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Sean Mcdermott Avenue", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Brabazon Yard", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Of Passage", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Clarence Circus", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Emmett Court", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Thomas Link", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Narrow Caboches Pavement", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Mountjoy Street", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Heytesbury Drive", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Frenschemanne Lawn", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("New Chapel Drive", (int)(Math.random()*tiempoMaximo)));
        direcciones.add(new Direccion("Pembroke Boulevard", (int)(Math.random()*tiempoMaximo)));
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
