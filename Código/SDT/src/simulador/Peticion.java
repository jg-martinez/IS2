
package simulador;

import java.util.Date;

public class Peticion {
    String nombre;
    Direccion direccion;
    int telefono;
    Date fecha;
    
    public Peticion (String _nombre, Direccion _direccion, int _telefono, Date _fecha) {
        nombre = _nombre;
        direccion = _direccion;
        telefono = _telefono;
        fecha = _fecha;
    }
    
    public String toString() {
        String res = "";
        res += fecha.toString() + "\n";
        res += nombre + "\n";
        res += telefono + "\n";
        res += direccion + "\n";
        res += "____________________________________\n\n";
        return res;
    }
}
