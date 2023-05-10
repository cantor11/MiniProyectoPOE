package GeekOutMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/*
Modelo para la lógica del juego (modelo).
@authors Jr Cantor Arevalo junior.cantor@correounivalle.edu.co
@authors Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
@version v.1.0.0 date: 05/05/2023
 */
public class ModeloGOM {

    private int dadoRandom;
    private ArrayList<Dados> dados;
    private ArrayList<Dados> dadosInactivos;
    private ArrayList<Dados> dadosUtilizados;
    private HashMap<String, String> nombreAAccion; // Identifica la accion por medio de su nombre
    private HashMap<String, String> nombreAEstado; // Identifica cuales dados son activos e inactivos por medio de su nombre
    private HashMap<String, Dados> nombreAObjeto; //  Retorna el objeto por medio de su nombre
    
    public ModeloGOM(){
        nombreAAccion = new HashMap<>();
        nombreAObjeto = new HashMap<>();
        nombreAEstado = new HashMap<>();
        dadosInactivos = new ArrayList<>();
        dadosUtilizados = new ArrayList<>();
    }
    /**
     Retorna el array que se ingresa
     */
    public ArrayList listaDados(String nombreArray){
        ArrayList<Dados> auxiliar;
        if(nombreArray == "activos"){
            auxiliar = dados;
        }else{
            if(nombreArray == "inactivos"){
                auxiliar = dadosInactivos;
            }else{
                auxiliar = dadosUtilizados;
            }
        }
        return auxiliar;
    }

    /**
     Elimina un dado de la zona de dados activos y lo mueve a la zona de utilizados
     */
    public void dadosUtilizados(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        dadosUtilizados.add(nombreAObjeto.get(nombreDado));
        nombreAObjeto.get(nombreDado).setActivoInactivo("utilizado");
        dados.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("utilizados");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }

    /**
     Inicio del juego
     */
    public void lanzamientoDados(){

        //Se crean los 10 dados
        dados = new ArrayList<>();
        for(int dado=0; dado < 10; dado++){
            dados.add(new Dados());
        }

        asignacionAcciones(); // Asigna las acciones del ArrayList dados
        setActivo(); // Establece estado activo a todos los dados
        dadosInactivos(); // Selecciona 3 dados inactivos y los borra del Arraylist dados
        identidadDado("activos"); // Actualiza los nombres del ArrayList dados
        identidadDado("inactivos"); // Actualiza los nombres del ArrayList dadosInactivos
    }

    /**
     Se scoge 3 dados inactivos al azar
     */
    public void dadosInactivos(){
        for(int inactivo=0; inactivo < 3; inactivo++){
            Random random = new Random();
            dadoRandom = random.nextInt(dados.size());
            dados.get(dadoRandom).setActivoInactivo("inactivo");
            dadosInactivos.add(dados.get(dadoRandom));
            dados.remove(dadoRandom);
        }

        identidadDado("activos");
        identidadDado("inactivos");
    }

    /**
     Se le asigna el nombre a cada dado dependiendo del ArrayList
     */
    public void identidadDado(String array){
        if(array == "activos"){
            for(int dado=0; dado < dados.size(); dado++){
                dados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
            }
        }else{
            if(array == "inactivos"){
                for(int dado=0; dado < dadosInactivos.size(); dado++){
                    dadosInactivos.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
                }
            }else{
                for(int dado=0; dado < dadosUtilizados.size(); dado++){
                    dadosUtilizados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
                }
            }
        }
    }

    /**
     Asigna el estado inicial del ArrayList dados
     */
    public void setActivo(){
        for(int dado=0; dado < dados.size(); dado++){
            dados.get(dado).setActivoInactivo("activo");
        }
    }

    /**
     Dependiendo de su numero de accion se determinan las acciones de cada dado
     */
    public void listaAcciones(){
        for(int numero=0; numero < dados.size(); numero++){
            switch(dados.get(numero).getNumAccion()){
                case 1:
                    dados.get(numero).setAccion("Mepple");
                    break;
                case 2:
                    dados.get(numero).setAccion("superheroe");
                    break;
                case 3:
                    dados.get(numero).setAccion("dragon");
                    break;
                case 4:
                    dados.get(numero).setAccion("corazon");
                    break;
                case 5:
                    dados.get(numero).setAccion("cohete");
                    break;
                case 6:
                    dados.get(numero).setAccion("42");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     Establece las acciones a cada dado
     */
    public void asignacionAcciones(){
        for(int i=0; i < dados.size(); i++){
            dados.get(i).setNumAccion();
        }

        listaAcciones();
    }

    /**
     Retorna la acción de un dado, dependiendo del array donde este
     */
    public String getAccionDado(String _nombreDado, String nombreArray){
        String accionDado = "";
        if(nombreArray == "activos"){
            for(int dado=0; dado < dados.size(); dado++){
                nombreAAccion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
            }
            accionDado = nombreAAccion.get(_nombreDado);
        }else{
            for(int dado=0; dado < dadosInactivos.size(); dado++){
                nombreAAccion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
            }
            accionDado = nombreAAccion.get(_nombreDado);
        }
        return accionDado;
    }

    /**
     Establece accion del meple al dado seleccionado
     */
    public void accionMepple(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setNumAccion();
        listaAcciones();
        nombreAObjeto.clear();
    }

    /**
     Establece la accion del superheroe
     El dado se volte a su cara opuesta
     */
    public void accionSuperHeroe(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        if(nombreAObjeto.get(nombreDado).getAccion() == "Mepple"){
            nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(5);
            listaAcciones();
        }else{
            if(nombreAObjeto.get(nombreDado).getAccion() == "superheroe"){
                nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(3);
                listaAcciones();
            }else{
                if(nombreAObjeto.get(nombreDado).getAccion() == "dragon"){
                    nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(2);
                    listaAcciones();
                }else{
                    if(nombreAObjeto.get(nombreDado).getAccion() == "corazon"){
                        nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(6);
                        listaAcciones();
                    }else{
                        if(nombreAObjeto.get(nombreDado).getAccion() == "cohete"){
                            nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(1);
                            listaAcciones();
                        }else{
                            if(nombreAObjeto.get(nombreDado).getAccion() == "42"){
                                nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(4);
                                listaAcciones();
                            }
                        }
                    }
                }
            }
        }
        nombreAObjeto.clear();
    }

    /**
     Establece la accion del corazon
     Se activa el dado seleccionado de la zona de inactivos
     */
    public void accionCorazon(String nombreDado){
        for(int dado=0; dado < dadosInactivos.size(); dado++){
            nombreAObjeto.put(dadosInactivos.get(dado).getNombreDado(), dadosInactivos.get(dado));
        }

        nombreAObjeto.get(nombreDado).setNumAccion();
        nombreAObjeto.get(nombreDado).setActivoInactivo("activo");
        dados.add(nombreAObjeto.get(nombreDado));
        listaAcciones();
        dadosInactivos.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }

    /**
     Establece la accion del cohete
     Convierte el dado seleccionado en inactivo
     */
    public void accionCohete(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setActivoInactivo("inactivo");
        dadosInactivos.add(nombreAObjeto.get(nombreDado));
        dados.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }
}
