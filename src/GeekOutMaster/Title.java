package GeekOutMaster;

import javax.swing.*;
import java.awt.*;
/*
Etiqueta para nombrar nuestro juego "Geek Out Master"
@authors Jr Cantor Arevalo junior.cantor@correounivalle.edu.co
@authors Kevin Jordan Alzate kevin.jordan@correounivalle.edu.co
@version v.1.0.0 date: 05/05/2023
 */
public class Title extends JLabel {
    public Title(String title, Color backgroundColor){
        this.setText(title);
        this.setBackground(backgroundColor);
        this.setForeground(Color.white);
        //this.setFont();
        this.setFont(new Font("Tahoma", Font.BOLD+Font.ITALIC,26));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
}