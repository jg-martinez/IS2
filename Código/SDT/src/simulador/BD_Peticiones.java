
package simulador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;



public class BD_Peticiones {
    static ArrayList<Peticion> peticiones = new ArrayList<Peticion>();
    
    public static Peticion[] getPeticiones() {
        Peticion[] pets = new Peticion[peticiones.size()];
        for(int i = 0; i < pets.length; i++)
        {
            pets[i] = peticiones.get(i);
        }
        return (pets);
    }
    
    public static void anadir(Peticion nuevaPeticion) {
        peticiones.add(nuevaPeticion);
    }
    
    public static void rellenarRandom(int cantidad) {
        for (int i = 0; i < cantidad ; i++) {
            String nombre = "Random Name";
            
            Direccion direccion = Direccion.direcciones.get((int)(Math.random()*Direccion.direcciones.size()));
            
            int telefono = 900000000 + (int)(Math.random() * ((999999999 - 900000000) + 1));
            
            long offset = Timestamp.valueOf("2010-01-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
            long diff = end - offset + 1;
            Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
            Date fecha = new Date(rand.getTime());
            
            Peticion pet = new Peticion(nombre, direccion, telefono, fecha);
            
            peticiones.add(pet);
        }
    }
}
