package com.Renad;
import java.awt.*;


public class MapGenerator {

    public int [][]map;
    public int brickWidth;
    public int brickHeight;

    // constructor

    public MapGenerator(int row, int col) {
        map = new int[row][col];

        for (int[] map1 : map) {
            for (int j = 0; j < map[0].length; j++) {
                map1[j] = 1;
            }
        }

        // the width and height of square
        brickWidth = 540 / col;
        brickHeight = 200 / row;
    } // end constructor


    // draw big rectangle
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0) {

                    g.setColor(new Color(97, 134, 133)); // color inside the bricks
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); // shadow of border-top

                    g.setStroke(new BasicStroke(15)); // width of border

                    g.setColor(Color.white); // color of width
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); // shadow of border-bottom

                } // end if
            } // end for
        } // end nested for
    } // end function draw

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    } // end function setBrickValue

} // end class MapGenerator