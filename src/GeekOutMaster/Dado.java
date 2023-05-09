package GeekOutMaster;

import java.util.Random;

/*
Clase dado para retornar valores aleatorios y asignarlos
@authors Jr Cantor Arevalo junior.cantor@correounivalle.edu.co
@version v.1.0.0 date: 5/5/2023
 */

public class Dado {
    private int cara;
    /*
    metodo para generar un valor aleatorio para asignar una cara posteriormente
    @return numero entre 1 y 6)
     */
    public static int getCara() {
        Random aleatorio = new Random();
        int cara = aleatorio.nextInt(6)+1;
        return cara;
    }
}
