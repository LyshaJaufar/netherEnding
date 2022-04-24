package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Console;

import java.io.IOException;

import javax.swing.*;

public class Main extends JPanel {
   private static final int RECT_X = 200;
   private static final int RECT_Y = RECT_X;
   private static final int RECT_WIDTH = 300;
   private static final int RECT_HEIGHT = RECT_WIDTH;
      
   static Color zombieBody = new Color(101, 235, 205);
   static Color zombieHead = new Color(115, 167, 56);
   static Color zombieArms = new Color(139, 191, 32);
   static Color playerStarterShirt = new Color(255, 0, 0);
   static Color playerHead = new Color(255, 255, 0);
   static Color playerArms = new Color(251, 225, 191);
 

   public static void createWindow() {
      JFrame window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);
      
      GamePanel gamePanel = new GamePanel();
      window.add(gamePanel);
      
      window.pack();
      
      window.setLocationRelativeTo(null);
      window.setVisible(true);
      
      gamePanel.startGameThread();
   }
  
   public static void main(String[] args) {
	   createWindow();
   }
}