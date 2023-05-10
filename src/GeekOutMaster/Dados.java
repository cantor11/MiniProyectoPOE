package GeekOutMaster;

import java.util.Random;

/*
Clase dado para retornar valores aleatorios y asignarlos
@authors Jr Cantor Arevalo junior.cantor@correounivalle.edu.co
@authors Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
@version v.1.0.0 date: 5/5/2023
 */
public class Dados {
    private int numeroAccion;
    private String accion;
    private String nombreDado;
    private String activoInactivo;

    public Dados(){
    }

    /**
     Asigna un numero aleatorio para tener una accion
     */
    public void setNumAccion(){
        int numeroAleatorio;
        Random aleatorio = new Random();
        numeroAleatorio = aleatorio.nextInt(6)+1;
        if(numeroAccion == numeroAleatorio){
            setNumAccion();
        }else{
            numeroAccion = numeroAleatorio;
        }
    }

    /**
     Se determina un numero manualmente para tener una accion
     */
    public void setNumAccionNoAleatorio(int numero){
        numeroAccion = numero;
    }

    /**
     Se establece una accion
     */
    public void setAccion(String _accion){
        accion = _accion;
    }

    /**
     Se le establece un nombre al dado
     */
    public void setNombreDado(String _nombre){
        nombreDado = _nombre;
    }

    /**
     Se establce un estado a un dado
     */
    public void setActivoInactivo(String estado){
        activoInactivo = estado;
    }

    /**
     Retorna el numero que representa la accion
     */
    public int getNumAccion(){
        return numeroAccion;
    }

    /**
     Retorna el nombre de la accion
     */
    public String getAccion(){
        return accion;
    }

    /**
     Retorna el nombre del dado
     */
    public String getNombreDado(){
        return nombreDado;
    }
}
