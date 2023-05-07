package com.Renad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false; // didn't start playing
    private int score = 0; // number of score

    private int totalBricks = 35; // number of square
    private Timer timer;

    private int delay = 8;
    private int playerX = 310; // when start, makes the paddle in a middle

    // position of ball
    private int ballposX = 120; // in x-index (left, right) => (less = left, greater = right)
    private int ballposY = 350; // in y-index (top, bottom) => (less = top, greater = bottom)

    // it makes the ball faster
    private int ballXdir = -2; // -1
    private int ballYdir = -3; // -2

    private MapGenerator map;


    // constructor

    public GamePlay () {
        map = new MapGenerator(5, 7); // create a map
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    } // end constructor

    public void paint(Graphics g) {

        // background

        g.setColor(Color.white);
        g.fillRect(1, 1, 700, 650);

        // drawing map

        map.draw( (Graphics2D) g);


        // scores, compute points of hits the square

        g.setColor(new Color(244, 156, 139));
        g.setFont(new Font("Courier New", Font.PLAIN, 25) ); // font family, font style, font size
        g.drawString("Your score: " + score, 20, 430); // 20 in x-index(left), 430 in y-index(bottom)

        // The paddle

        g.setColor(Color.black);
        g.fillRect(playerX, 550, 100, 8); // y-index, width, height

        // The ball

        g.setColor(new Color( 230, 92, 0));
        g.fillOval(ballposX, ballposY, 40, 40); // Make a ball circle

        g.setColor(Color.white);
        g.fillOval(ballposX, ballposY, 35, 35);


        if(totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 45) ); // font family, font style, font size
            g.drawString("Congrats You Won !!!!! " , 140, 300);


        } // end if

        if(ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 35) ); // font family, font style, font size
            g.drawString("Game Over!!",250, 350);

            g.setFont(new Font("serif", Font.PLAIN, 25) ); // font family, font style, font size
            g.drawString("Press Enter to Play Again",230, 400);

        } // end if

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        // Move the ball
        if (play) {

            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8) ) ) {
                ballYdir = -ballYdir;
            }
            // make a square broken
            A:for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map[0].length; j++ ) {
                    if(map.map[i][j] > 0) {

                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight); // create obj from Rectangle
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)) {
        map.setBrickValue(0, i, j);
        totalBricks--;
        score += 2;

        if( (ballposX + 19 <= brickRect.x ) || (ballposX + 1 <= ballRect.x + brickRect.width) ) {
        ballXdir = -ballXdir;
        } else {
        ballYdir = -ballYdir;
        }
        break A;
        } // end nested if
        } // end if
        } // end nested for
        } // end for

        ballposX += ballXdir;
        ballposY += ballYdir;

        if(ballposX < 0) {
        ballXdir = -ballXdir;
        }

        if(ballposY < 0) {
        ballYdir = -ballYdir;
        }

        if(ballposX > 670) {
        ballXdir = -ballXdir;
        }
        }

        repaint(); // Make the paddle move
        }

@Override
public void keyTyped(KeyEvent e) {

        }

@Override
public void keyReleased(KeyEvent e) {

        }

@Override
public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (playerX >= 600) {
        playerX = 600;
        } else {
        moveRight();
        }
        } // end if

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        if (playerX < 10) {
        playerX = 10;
        } else {
        moveLeft();
        }
        } // end if

        // if the player loss, press enter then start play again
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        if (!play) {
        play = true;
        ballposX = 120;
        ballposY = 350;
        ballXdir = -2; // -1
        ballYdir = -3; // -2
        playerX = 310;
        score = 0;
        totalBricks = 35;
        map = new MapGenerator(5, 7);

        repaint();
        } // end nested if
        } // end if

        } // end keyPressed


// if you press right arrow, move the paddle 40 to right
public void moveRight() {
        play = true;
        playerX += 40;
        }

// if you press right arrow, move the paddle 40 to right
public void moveLeft() {
        play = true;
        playerX -= 40;
        }

        } // end class GamePlay