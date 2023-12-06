
package snake;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class gameframe extends JFrame {
    gameframe(){
         Image icon = new ImageIcon(this.getClass().getResource("/icon/snake.png")).getImage();
         this.setIconImage(icon);
        gamepanel panel = new gamepanel();
        this.add(panel);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
}
