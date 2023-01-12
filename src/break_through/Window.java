/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package break_through;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

/**
 *
 * @author Munkhtenger
 */
public class Window extends JFrame{
    private Game gameGUI;
    private JFrame baseFrame;
    private final int DefaultBoardSize=6;

    /**
     * Constructor for Window class that constructs a window, the game panel
     * and the label.
     */
    
    public Window(){              
        gameGUI= new Game(DefaultBoardSize);
        baseFrame= new JFrame("Break-through");
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        baseFrame.getContentPane().add(gameGUI.getDisplayTurns(), BorderLayout.SOUTH);
        baseFrame.getContentPane().add(gameGUI.getMatrixPanel(), BorderLayout.CENTER);
        
        int[] board_sizes = new int[]{6,8,10};
        JMenuBar menu_bar = new JMenuBar();
        baseFrame.setJMenuBar(menu_bar);
        JMenu game_menu = new JMenu("Game options");
        menu_bar.add(game_menu);
        JMenu boardSize_menu= new JMenu("Change board size");
        game_menu.add(boardSize_menu);
        
        for (int board_size : board_sizes) {
            JMenuItem sizeMenuItem=new JMenuItem(board_size +"x"+board_size);
            boardSize_menu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    baseFrame.getContentPane().remove(gameGUI.getMatrixPanel());
                    baseFrame.getContentPane().remove(gameGUI.getDisplayTurns());
                    gameGUI = new Game(board_size);
                    baseFrame.getContentPane().add(gameGUI.getDisplayTurns(),
                            BorderLayout.SOUTH);
                    baseFrame.getContentPane().add(gameGUI.getMatrixPanel(),
                            BorderLayout.CENTER);
                    baseFrame.pack();
                }
            });
        }
//        if(matrixGUI.refreshEachClick()){
            
//            this.dispose();
        
//                baseFrame.getContentPane().remove(gameGUI.getMatrixPanel());
//                baseFrame.getContentPane().remove(gameGUI.getDisplayTurns());
//                gameGUI = new Game(DefaultBoardSize);
//                baseFrame.getContentPane().add(gameGUI.getMatrixPanel(),
//                        BorderLayout.CENTER);
//                baseFrame.getContentPane().add(gameGUI.getDisplayTurns(),
//                        BorderLayout.SOUTH);
//                baseFrame.pack();
//        }
//            frame.getContentPane().remove(matrixGUI.getMatrixPanel());
//            frame.getContentPane().remove(matrixGUI.getTurnLabel());
//            matrixGUI = new GameWindow(BOARD_SIZE);
//            frame.getContentPane().add(matrixGUI.getMatrixPanel(),
//                    BorderLayout.CENTER);
//            frame.getContentPane().add(matrixGUI.getTurnLabel(),
//                    BorderLayout.SOUTH);
//            }
     //   this.dispose();
      //  matrixGUI.reallyEnd();
        JMenuItem menu_item_exit = new JMenuItem("Exit the game");
        game_menu.add(menu_item_exit);
        menu_item_exit.addActionListener(new ActionListener() {
            @Override
            
            /**
             * if menubar's exit button is clicked then system will exit. 
             */
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        baseFrame.pack();
       // baseFrame.setResizable(false);
        baseFrame.setVisible(true);
    }
}
