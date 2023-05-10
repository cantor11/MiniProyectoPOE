/*
Clase Principal
@authors Jr Cantor Arevalo junior.cantor@correounivalle.edu.co
@authors Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
@version v.1.0.0 date: 05/05/2023
 */
package GeekOutMaster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class GUIGeekOutMaster extends JFrame{
    public  static final String AYUDA="El objetivo de este juego es conseguir la mayor \n"
            + "cantidad de puntos juntando dados cuya cara\n"
            + "visible sea la cara 42\n\n"
            + "Mepple: Permite relanzar activo.\n"
            + "Cohete: Envia un dado activo a la seccion de inactivos.\n"
            + "Superheroe: Voltea dado activo en su cara opuesta.\n"
            + "Corazón: Toma un dado inactivo y lo lanza.\n"
            + "Dragon: Pierde todos los puntos ganados y acumulados.\n"
            + "42: Suma puntos al final de la ronda.";

    private Title titleProject;
    private JLabel textoPuntaje, textoPuntajeTotal, textoRonda;
    private JButton lanzar, ayuda, salir, continuarReiniciar;
    private JPanel panelDadosActivos, panelDadosUtilizados, panelDadosInactivos, panelPuntaje, panelRonda;
    private ImageIcon imageDado;
    private Escucha escucha;
    private CambiarImagen cambiarImagen;
    private AccionSuperHeroe superheroe;
    private AccionCorazon corazon;
    private AccionCohete cohete;
    private ModeloGOM modeloGOM;
    private ArrayList<JButton> botones;
    private ArrayList<JButton> botonesUtilizados;
    private ArrayList<JButton> botonesInactivos;
    private HashMap<String, JButton> valorBotones;
    private HashMap<JButton, String> botonANombre;
    private int nuevoEscucha = 0; // Usa un MouseListener distinto dependiendo del numero
    private int puntaje, puntajeRonda;
    private int ronda;
    private int dragon;
    private int estadoDelJuego;

    /**
     Constructor de la clase GUI
     */
    public GUIGeekOutMaster(){
        initGUI();

        //Forma por defecto del JFrame
        this.setTitle("Geek Out Masters");
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     Crea objetos de escucha y control para la clase GUI
     */
    private void initGUI() {
        // Diseño del JFrame
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Se Crean objetos de escucha y objetos de control
        escucha = new Escucha();
        cambiarImagen = new CambiarImagen();
        superheroe = new AccionSuperHeroe();
        corazon = new AccionCorazon();
        cohete = new AccionCohete();
        modeloGOM = new ModeloGOM();
        botones = new ArrayList<>();
        botonesUtilizados = new ArrayList<>();
        botonesInactivos = new ArrayList<>();
        valorBotones = new HashMap<>();
        botonANombre = new HashMap<>();
        puntaje = 0;
        puntajeRonda = 0;
        ronda = 1;
        dragon = 0;
        estadoDelJuego = 0;

        /**
         Se crea el titulo
         */
        titleProject = new Title("Geek Out Master", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(titleProject,constraints);

        /**
         Se crea boton "Ayuda"
         */
        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        /**
         Se crea el boton para salir
         */
        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        // Puntaje de la ronda
        textoPuntaje = new JLabel();
        textoPuntaje.setHorizontalAlignment(SwingConstants.CENTER);

        // Puntaje total del juego
        textoPuntajeTotal = new JLabel();
        textoPuntajeTotal.setHorizontalAlignment(SwingConstants.CENTER);

        // Ronda
        textoRonda = new JLabel();

        /**
         Se crea el panel los dados activos
         */
        panelDadosActivos = new JPanel();
        panelDadosActivos.setPreferredSize(new Dimension(650,130));
        panelDadosActivos.setBorder(BorderFactory.createTitledBorder("Dados activos:"));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDadosActivos,constraints);

        /**
         Se crea el panel de los dados utilizados
         */
        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setPreferredSize(new Dimension(620,300));
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados:"));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDadosUtilizados,constraints);

        /**
         Se crea el panel de los dados inactivos
         */
        panelDadosInactivos = new JPanel();
        panelDadosInactivos.setPreferredSize(new Dimension(300,180));
        panelDadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados inactivos:"));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDadosInactivos,constraints);

        /**
         Se crea el panel del puntaje
         */
        panelPuntaje = new JPanel();
        panelPuntaje.setLayout(new GridLayout(0,1));
        panelPuntaje.setPreferredSize(new Dimension(200,100));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Puntos"));
        panelPuntaje.add(textoPuntajeTotal);
        panelPuntaje.add(textoPuntaje);
        constraints.gridx=2;
        constraints.gridy=2;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelPuntaje,constraints);

        /**
         Se crea el panel de la ronda
         */
        panelRonda = new JPanel();
        panelRonda.setPreferredSize(new Dimension(200,100));
        panelRonda.setBorder(BorderFactory.createTitledBorder("Ronda"));
        constraints.gridx=2;
        constraints.gridy=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelRonda,constraints);

        /**
         Se crea boton "Lanzar dados"
         */
        lanzar = new JButton("Lanzar dados");
        lanzar.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        this.add(lanzar,constraints);

        /**
         Se crea boton "Seguir jugando dado"
         */
        continuarReiniciar = new JButton();
        continuarReiniciar.setText("Seguir jugando");
        continuarReiniciar.addActionListener(escucha);
        continuarReiniciar.setName("continuarReiniciar");
        continuarReiniciar.setEnabled(false);
        constraints.gridx=1;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.EAST;
        this.add(continuarReiniciar,constraints);

        /**
         Se crean los dados
         */
        modeloGOM.lanzamientoDados();
        inicializarBotones();
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGeekOutMaster miProjectGUIGeekOutMaster = new GUIGeekOutMaster();
        });
    }

    /**
     Los botones activo e inactivos se inicializan en sus zonas.
     */
    public void inicializarBotones(){
        // Inicializacion dados activos
        for(int dado = 0; dado < modeloGOM.listaDados("activos").size(); dado++){
            botones.add(new JButton());
            botones.get(dado).setName("dado" + String.valueOf(dado+1));
            botones.get(dado).setBorder(null);
            botones.get(dado).setBackground(Color.white);
            botones.get(dado).addMouseListener(escucha);
            botones.get(dado).setVisible(false);
            imageDado = new ImageIcon(getClass().getResource("/resources/" + modeloGOM.getAccionDado("dado" + String.valueOf(dado+1), "activos") + ".png"));
            botones.get(dado).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            panelDadosActivos.add(botones.get(dado));
        }

        // Inicializacion dados inactivos
        for(int dado = 0; dado < modeloGOM.listaDados("inactivos").size(); dado++){
            botonesInactivos.add(new JButton());
            botonesInactivos.get(dado).setName("dado" + String.valueOf(dado+1));
            botonesInactivos.get(dado).setBorder(null);
            botonesInactivos.get(dado).setBackground(Color.white);
            botonesInactivos.get(dado).setVisible(false);
            imageDado = new ImageIcon(getClass().getResource("/resources/" + modeloGOM.getAccionDado("dado" + String.valueOf(dado+1), "inactivos") + ".png"));
            botonesInactivos.get(dado).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            panelDadosInactivos.add(botonesInactivos.get(dado));
        }
    }

    /**
     Actualiza el panel correspondiente
     */
    public void actualizarPanel(String nombrePanel){
        if(nombrePanel == "activos"){
            panelDadosActivos.removeAll();
            for(int boton=0; boton < botones.size(); boton++){
                panelDadosActivos.add(botones.get(boton));
            }
            panelDadosActivos.updateUI();
        }else{
            if(nombrePanel == "inactivos"){
                panelDadosInactivos.removeAll();
                for(int boton=0; boton < botonesInactivos.size(); boton++){
                    panelDadosInactivos.add(botonesInactivos.get(boton));
                }
                panelDadosInactivos.updateUI();
            }else{
                panelDadosUtilizados.removeAll();
                for(int boton=0; boton < botonesUtilizados.size(); boton++){
                    panelDadosUtilizados.add(botonesUtilizados.get(boton));
                }
                panelDadosUtilizados.updateUI();
            }
        }
    }

    /**
     Se renombran los botones si se elimina/agrega uno nuevo a una Arraylist
     */
    public void renombrarBotones(String nombreArray){
        if(nombreArray == "activos"){
            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).setName("dado" + String.valueOf(boton+1));
            }
        }else{
            if(nombreArray == "inactivos"){
                for(int boton=0; boton < botonesInactivos.size(); boton++){
                    botonesInactivos.get(boton).setName("dado" + String.valueOf(boton+1));
                }
            }else{
                for(int boton=0; boton < botonesUtilizados.size(); boton++){
                    botonesUtilizados.get(boton).setName("dado" + String.valueOf(boton+1));
                }
            }
        }
    }

    /**
     Hace parejas entre nombres y Jbutton dependiendo del Arraylist y retorna el JButton
     */
    public JButton mappingJButton(String nombreArray, String nombreDado){
        if(nombreArray == "activos"){
            for(int boton=0; boton < botones.size(); boton++){
                valorBotones.put(botones.get(boton).getName(), botones.get(boton));
            }
        }else{
            if(nombreArray == "inactivos"){
                for(int boton=0; boton < botonesInactivos.size(); boton++){
                    valorBotones.put(botonesInactivos.get(boton).getName(), botonesInactivos.get(boton));
                }
            }else{
                for(int boton=0; boton < botonesUtilizados.size(); boton++){
                    valorBotones.put(botonesUtilizados.get(boton).getName(), botonesUtilizados.get(boton));
                }
            }
        }

        return valorBotones.get(nombreDado);
    }

    /**
     Decide la ronda presente, puntaje total y de cada ronda
     */
    public void rondas(){
        int dados42 = 0; // 42
        int dadosDragon = 0; // dragones
        int seguirLanzando = 0; // 1 si sigue lanzando, de lo contrario 0
        String resultadoPuntaje = "";

        //Mensaje si en el array no hay dados activos
        if(botones.size() == 0){
            puntajeRonda = 0;
            puntaje += puntajeRonda;
            ronda += 1;
            estadoDelJuego = 1;
        }else{

            //Mensaje si en el array hay un dado activo
            if(botones.size() == 1){
                if(modeloGOM.getAccionDado(botones.get(0).getName(), "activos") == "corazon"){
                    nuevoEscucha = 0;
                    estadoDelJuego = 0;
                    seguirLanzando = 1;
                    escuchas();
                }else{
                    if(modeloGOM.getAccionDado(botones.get(0).getName(), "activos") == "42"){
                        puntajeRonda = 1;
                        puntaje += puntajeRonda;
                        ronda += 1;
                        estadoDelJuego = 1;
                    }else{
                        if(modeloGOM.getAccionDado(botones.get(0).getName(), "activos") == "dragon"){
                            puntajeRonda = 0;
                            puntaje = puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                        }else{
                            puntajeRonda = 0;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                        }
                    }
                }
            }else{
                // Cuenta cuantos dados 42 hay, al igual que cuantos dragones o si el ultimo dado es un corazon
                for (int boton=0; boton < botones.size(); boton++){
                    if(modeloGOM.getAccionDado(botones.get(boton).getName(), "activos") == "42"){
                        dados42 += 1;
                    }else{
                        if(modeloGOM.getAccionDado(botones.get(boton).getName(), "activos") == "dragon"){
                            dadosDragon += 1;
                        }
                    }
                }
                // Gana el juego, si el tamaño de la lista es igual a la cantidad de dados 42
                if(dados42 == botones.size()){
                    switch (dados42){
                        case 2:
                            puntajeRonda = 3;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 3:
                            puntajeRonda = 6;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 4:
                            puntajeRonda = 10;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 5:
                            puntajeRonda = 15;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 6:
                            puntajeRonda = 21;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 7:
                            puntajeRonda = 28;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 8:
                            puntajeRonda = 36;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 9:
                            puntajeRonda = 45;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        case 10:
                            puntajeRonda = 55;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            break;
                        default:
                            break;
                    }
                }else{
                    if(dados42 + dadosDragon == botones.size()){
                        puntajeRonda = 0;
                        puntaje = 0;
                        ronda += 1;
                        estadoDelJuego = 1;
                    }else{
                        seguirLanzando = 1;
                        estadoDelJuego = 0;
                    }
                }
            }
        }

        // Comprueba si se presiona la cara dragon
        if(dragon == 1){
            puntajeRonda = 0;
            puntaje = puntajeRonda;
            textoPuntajeTotal.setText("Puntaje: " + String.valueOf(puntaje));
        }

        if(seguirLanzando == 1){
            resultadoPuntaje = "Sigue lanzando";
        }else{
            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
        }

        if(estadoDelJuego == 1){
            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(escucha);
            }

            // Se borran los ArrayList de Dados para iniciar una nueva ronda
            modeloGOM.listaDados("activos").clear();
            modeloGOM.listaDados("inactivos").clear();
            modeloGOM.listaDados("utilizados").clear();

            // Se borran los ArrayList de botones
            botones.clear();
            botonesInactivos.clear();
            botonesUtilizados.clear();

            modeloGOM.lanzamientoDados();
            textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(puntaje));
            textoPuntaje.setText(resultadoPuntaje);
            inicializarBotones();

            if(ronda < 6 && puntaje < 29){
                continuarReiniciar.setEnabled(true); // Solo habilita el botón para continuar a la siguiente ronda
            }else{
                if(ronda < 6 && puntaje > 29){
                    resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntaje) + " ¡Has ganado!";
                }else{
                    resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntaje) + " ¡Has perdido!";
                }
                puntaje = 0;
                puntajeRonda = 0;
                ronda = 1;
                continuarReiniciar.setText("Jugar de nuevo");
                continuarReiniciar.setEnabled(true);
            }
            dragon = 0;
        }

        textoPuntaje.setText(resultadoPuntaje);
    }

    /**
     Establece que evento se invoca
     */
    public void escuchas(){
        class GetEscuchas implements MouseListener{
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (nuevoEscucha){
                    case 0:
                        escucha.mouseClicked(e);
                        break;
                    case 1:
                        cambiarImagen.mouseClicked(e);
                        break;
                    case 2:
                        superheroe.mouseClicked(e);
                        break;
                    case 3:
                        corazon.mouseClicked(e);
                        break;
                    case 4:
                        cohete.mouseClicked(e);
                        break;
                    default:
                        break;
                }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        }
    }

    /**
     Ejecuta la accion del cohete
     */
    private class AccionCohete implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modeloGOM.accionCohete(botonSecundario);
            mappingJButton("activos", botonSecundario).setEnabled(false); // Deshabilita el boton
            mappingJButton("activos", botonSecundario).removeMouseListener(this); // Remueve el MouseListener cohete
            botonesInactivos.add(mappingJButton("activos", botonSecundario)); // Adiciona el boton en la lista inactivos
            renombrarBotones("inactivos");
            botones.remove(mappingJButton("activos", botonSecundario)); // Borra el boton de la lista activos
            renombrarBotones("activos");
            actualizarPanel("inactivos");
            actualizarPanel("activos");

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }

            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    /**
     Ejecuta la accion del corazon
     */
    private class AccionCorazon implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modeloGOM.accionCorazon(botonSecundario);
            botones.add(mappingJButton("inactivos", botonSecundario)); // Adiciona el boton en la lista activos
            renombrarBotones("activos");
            botonesInactivos.remove(mappingJButton("inactivos", botonSecundario)); // Borra el boton de la lista inactivos
            renombrarBotones("inactivos");
            imageDado = new ImageIcon(getClass().getResource("/resources/" + modeloGOM.getAccionDado("dado" + String.valueOf(botones.size()), "activos") + ".png")); // Al invocar accionCorazon, el dado ingresa de ultimo a la lista de activos
            mappingJButton("activos", "dado" + String.valueOf(botones.size())).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            actualizarPanel("activos");
            actualizarPanel("inactivos");

            for(int boton=0; boton < botonesInactivos.size(); boton++){
                botonesInactivos.get(boton).removeMouseListener(this);
                botonesInactivos.get(boton).setEnabled(false);
            }

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }
            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    /**
     Ejecuta la accion del Superheroe
     */
    private class AccionSuperHeroe implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();

            // Se repite el lanzamiento si el dado es superheroe
            if(modeloGOM.getAccionDado(botonSecundario, "activos") == "superheroe"){
                textoPuntaje.setText("No se puede girar otro superheroe");
                nuevoEscucha = 2;
                escuchas();
            }else{
                modeloGOM.accionSuperHeroe(botonSecundario);
                imageDado = new ImageIcon(getClass().getResource("/resources/" + modeloGOM.getAccionDado(botonSecundario, "activos") + ".png"));
                mappingJButton("activos", botonSecundario).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));

                for(int boton=0; boton < botones.size(); boton++){
                    botones.get(boton).removeMouseListener(this);
                    botones.get(boton).addMouseListener(escucha);
                }
                nuevoEscucha = 0;
                escuchas();
                rondas();
            }

        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    /**
     Ejecuta la accion del Mepple
     */
    private class CambiarImagen implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modeloGOM.accionMepple(botonSecundario);
            imageDado = new ImageIcon(getClass().getResource("/resources/" + modeloGOM.getAccionDado(botonSecundario, "activos") + ".png"));
            mappingJButton("activos", botonSecundario).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }
            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    /**
     Evento cuando se lanza los dados y se presiona uno de ellos
     */
    private class Escucha implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == lanzar) {
                lanzar.setEnabled(false);

                textoRonda.setText("Ronda: " + String.valueOf(ronda));
                panelRonda.add(textoRonda);
                continuarReiniciar.setText("Continuar ronda");
                textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(puntaje));
                textoPuntaje.setText("¡Lanza un dado!");

                // Aparecen los dados activos e inactivos
                for (int dado = 0; dado < botones.size(); dado++) {
                    botones.get(dado).setVisible(true);
                }

                for (int dado = 0; dado < botonesInactivos.size(); dado++) {
                    botonesInactivos.get(dado).setVisible(true);
                    botonesInactivos.get(dado).setEnabled(false);
                }

            } else {
                if (e.getSource() == ayuda) {
                    JOptionPane.showMessageDialog(null, AYUDA);
                } else {
                    if (e.getSource() == continuarReiniciar) {
                        actualizarPanel("activos");
                        actualizarPanel("inactivos");
                        actualizarPanel("utilizados");
                        textoRonda.setText("Ronda: " + String.valueOf(ronda));
                        textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(puntaje));
                        textoPuntaje.setText(null);
                        continuarReiniciar.setEnabled(false);
                        lanzar.setEnabled(true);
                    } else {
                        System.exit(0);
                    }
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            String nombreBoton = "";
            String nombreAccion = "";

            nombreBoton = e.getComponent().getName();
            nombreAccion = modeloGOM.getAccionDado(nombreBoton, "activos");
            mappingJButton("activos", nombreBoton).setEnabled(false); // Desactiva el boton luego de haberlo presionarlo
            mappingJButton("activos", nombreBoton).removeMouseListener((MouseListener) this);
            botonesUtilizados.add(mappingJButton("activos", nombreBoton));
            renombrarBotones("utilizados"); // Se actualiza en la lista los nombres de los botones usados
            botones.remove(mappingJButton("activos", nombreBoton));
            renombrarBotones("activos"); // Se actualiza en la lista los nombres de los botones activos
            modeloGOM.dadosUtilizados(nombreBoton); // se elimina de la zona de activos el dado y se mueve a la zona utilizados
            actualizarPanel("activos");
            actualizarPanel("utilizados");
            valorBotones.clear();

            if (nombreAccion == "mepple") {
                for (int boton = 0; boton < botones.size(); boton++) {
                    botones.get(boton).removeMouseListener((MouseListener) this);
                    botones.get(boton).addMouseListener(cambiarImagen);
                }

                nuevoEscucha = 1;
                textoPuntaje.setText("Accion mepple activado");
                escuchas();
            } else {
                if (nombreAccion == "superheroe") {

                    // Si hay dos dados superheroes al final, se lanza uno si activar su accion y se obtienen 0 puntos en la ronda
                    if (botones.size() == 1 && modeloGOM.getAccionDado(botones.get(0).getName(), "activos") == "superheroe") {
                        rondas();
                    } else {
                        for (int boton = 0; boton < botones.size(); boton++) {
                            botones.get(boton).removeMouseListener((MouseListener) this);
                            botones.get(boton).addMouseListener(superheroe);
                        }

                        textoPuntaje.setText("Accion superheroe activado");
                        nuevoEscucha = 2;
                        escuchas();
                    }
                } else {
                    if (nombreAccion == "dragon") {
                        dragon = 1;
                        nuevoEscucha = 0;
                        puntaje = 0;
                        escuchas();
                        rondas();
                    } else {
                        if (nombreAccion == "corazon") {
                            if (botonesInactivos.size() > 0) {
                                for (int boton = 0; boton < botones.size(); boton++) {
                                    botones.get(boton).removeMouseListener((MouseListener) this);
                                }

                                for (int boton = 0; boton < botonesInactivos.size(); boton++) {
                                    botonesInactivos.get(boton).setEnabled(true);
                                    botonesInactivos.get(boton).addMouseListener(corazon);
                                }

                                nuevoEscucha = 3;
                                textoPuntaje.setText("Accion corazon activado");
                                escuchas();
                            } else {
                                nuevoEscucha = 0;
                                escuchas();
                                rondas();
                            }
                        } else {
                            if (nombreAccion == "cohete") {
                                for (int boton = 0; boton < botones.size(); boton++) {
                                    botones.get(boton).removeMouseListener((MouseListener) this);
                                    botones.get(boton).addMouseListener(cohete);
                                }

                                nuevoEscucha = 4;
                                textoPuntaje.setText("Accion cohete activado");
                                escuchas();
                            } else {
                                nuevoEscucha = 0;
                                escuchas();
                                rondas();
                            }
                        }
                    }
                }
            }
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}