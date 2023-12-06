package snake;

import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public final class gamepanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 700;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int X[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts =8;
    int applesEaten;
    int appleX;
    int appley;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    gamepanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //this.setBackground(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
         this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
        if (running) {
            //
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                //g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                //g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            g.setColor(Color.RED);
            g.fillOval(appleX, appley, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    /// لون راس الافعى
                   g.setColor(Color.GREEN);
                  g.fillRect(X[i], y[i], UNIT_SIZE, UNIT_SIZE);// لون راس الافعى
                  // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                  
                } else {
                    
                    //لون الافعى باقي الافعى    //////snake Color
                 
                 g.setColor(Color.green);
               //g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
               //g.draw3DRect(X[i], y[i], UNIT_SIZE, UNIT_SIZE, running);
                    g.fillRect(X[i], y[i], UNIT_SIZE, UNIT_SIZE);
                  
                }
            }
            
            // result color
            
         // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
          
      g.setColor(Color.GREEN);
      g.setFont(new Font("Arial",Font.BOLD,50));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString("Score:"+applesEaten,(SCREEN_WIDTH -metrics.stringWidth("Score:"+applesEaten))/2,g.getFont().getSize());
    
    

        }
    else {
            gameover(g);
           
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appley = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            X[i] = X[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;

            case 'L':
                X[0] = X[0] - UNIT_SIZE;
                break;

            case 'R':
                X[0] = X[0] + UNIT_SIZE;
                break;

        }
    }

    public void checkApple() {
        if ((X[0] == appleX) && (y[0] == appley)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void ceckCollisions() {

        for (int i = bodyParts; i > 0; i--) {

            if ((X[0] == X[i] && (y[0]) == y[i])) {

                running = false;

            }
        }

        if (X[0] < 0) {
            running = false;
        }
        if (X[0] > SCREEN_WIDTH) {
            running = false;
        }
        if (y[0] < 0) {
            running = false;
        }
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }

    }

    public void gameover(Graphics g) {
        //النتيجة عند انتهى اللعب (لون النتيجة )
       
         g.setColor(Color.green);
        // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
     g.setFont(new Font("Arial",Font.BOLD,40));
      FontMetrics metrics1 = getFontMetrics(g.getFont());
      g.drawString("Total Score:"+applesEaten,(SCREEN_WIDTH -metrics1.stringWidth("Total Score:"+applesEaten))/2,g.getFont().getSize());
        // GAME OVER    
        
        
        g.setColor(Color.RED);
        // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
       g.setFont(new Font("Arial",Font.BOLD,85));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER")) / 2, SCREEN_HEIGHT / 2);
        
        
        g.setColor(Color.GREEN);
         g.setFont(new Font("Arial",Font.BOLD,32));
      FontMetrics metrics3 = getFontMetrics(g.getFont());
      g.drawString("By Abdullah Alghamdi", 200, 600);
    
    
       
        
        
       // زر اعادة اللعب 
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            ceckCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

          @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
      
            }

        }
        }
}

