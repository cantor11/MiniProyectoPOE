package GeekOutMaster;

import javax.swing.*;
import java.awt.*;

public class GUIGridBagLayout extends JFrame {
    public static final String MENSAJE_INICIO="El objetivo de este juego es conseguir la mayor \n"
            + "cantidad de puntos juntando dados cuya cara\n"
            + "visible sea la cara 42";
    private Title headerProject;
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private JButton lanzarDados, usarDado, ayuda, salir;
    private JPanel panelDadosActivos, panelDadosInactivos, panelDadosUsados;
    private ImageIcon imagenDado;
    private JTextArea mensajesSalida, resultadosDados;

    //private Escucha escucha;
    private ModeloGOM modeloGOM;

    public GUIGridBagLayout(){
        initGUI();
        this.setTitle("Geek Out Master");
        this.setUndecorated(false);
        //this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //escucha = new Escucha(); //constructor
        modeloGOM = new ModeloGOM();
        headerProject = new Title("Geek Out Master", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton("?");
        //ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton("Salir");
        //salir = addActionListener(escucha);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        imagenDado = new ImageIcon(getClass().getResource("/resources/DadoSinEstablecer.PNG"));
        dado1 = new JLabel(imagenDado);
        dado2 = new JLabel(imagenDado);
        dado3 = new JLabel(imagenDado);
        dado4 = new JLabel(imagenDado);
        dado5 = new JLabel(imagenDado);
        dado6 = new JLabel(imagenDado);
        dado7 = new JLabel(imagenDado);
        dado8 = new JLabel(imagenDado);
        dado9 = new JLabel(imagenDado);
        dado10 = new JLabel(imagenDado);

        panelDadosInactivos = new JPanel();
        panelDadosInactivos.setPreferredSize(new Dimension(300,180));
        panelDadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados inactivos:"));
        panelDadosInactivos.add(dado8);
        panelDadosInactivos.add(dado9);
        panelDadosInactivos.add(dado10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDadosInactivos,constraints);

        panelDadosUsados = new JPanel();
        panelDadosUsados.setPreferredSize(new Dimension(675,300));
        panelDadosUsados.setBorder(BorderFactory.createTitledBorder("Dados utilizados:"));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDadosUsados,constraints);

        panelDadosActivos = new JPanel();
        panelDadosActivos.setPreferredSize(new Dimension(650,180));
        panelDadosActivos.setBorder(BorderFactory.createTitledBorder("Dados activos:"));
        panelDadosActivos.add(dado1);
        panelDadosActivos.add(dado2);
        panelDadosActivos.add(dado3);
        panelDadosActivos.add(dado4);
        panelDadosActivos.add(dado5);
        panelDadosActivos.add(dado6);
        panelDadosActivos.add(dado7);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDadosActivos,constraints);

        lanzarDados = new JButton("Lanzar dados");
        //lanzarDados = addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        this.add(lanzarDados,constraints);

        usarDado = new JButton("Usar dado");
        //usarDado = addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;
        this.add(usarDado,constraints);

        resultadosDados = new JTextArea(4,26);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados:"));
        resultadosDados.setBackground(null);
        resultadosDados.setEditable(false);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);

        mensajesSalida = new JTextArea(4,26);
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Mensajes:"));
        mensajesSalida.setText("Presione (?) para información");
        mensajesSalida.setBackground(null);
        mensajesSalida.setEditable(false);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(mensajesSalida,constraints);

    }

    public static void main(String[]args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {GUIGridBagLayout myGui = new GUIGridBagLayout();}
        });
    }
        /*
    ---Controlador---

    private class Escucha implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==lanzarDados){

                modeloGOM.tirarDado();
                int[] caras = modeloGOM.getCaras();
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
                dado1.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
                dado2.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[2]+".png"));
                dado3.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[3]+".png"));
                dado4.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[4]+".png"));
                dado5.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[5]+".png"));
                dado6.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[6]+".png"));
                dado7.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[7]+".png"));
                dado8.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[8]+".png"));
                dado9.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[9]+".png"));
                dado10.setIcon(imagenDado);

            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0)
                }
            }

            resultadosDados.setText(ModeloGOM.getEstadotoString()[0]);
            mensajesSalida.setText(ModeloGOM.getEstadotoString()[1]);

        }
    }
    */
}
