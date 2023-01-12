/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package break_through;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 *
 * @author Munkhtenger
 */
public class Game{
    private final JPanel matPanel;
    private final Matrix_Implementation rows_cols;
    private final JButton[][] buttonFields;    
    private Integer prevX1;
    private Integer prevY1;
    private Integer prevX2;
    private Integer prevY2;
    private Color prevColor1 = null;
    private Color prevColor2 = null;
    private int turn = 0;
    private final JLabel displayTurns;
    private final Color red = Color.red;
    private final Color blue = Color.blue;
    //Window gui=new Window();
   // private int matrixSize;
    
    /**
     * Constructor for implementing game panel with given game size
     * @param gameSize 
     */
    public Game(int gameSize) {
      //  matrixSize=matrix.getMatrixSize();
        matPanel = new JPanel();
        rows_cols = new Matrix_Implementation(gameSize);
        buttonFields = new JButton[rows_cols.getMatSize()][rows_cols.getMatSize()];
        matPanel.setLayout(new GridLayout(rows_cols.getMatSize(), rows_cols.getMatSize()));
                
        for (int i=0; i<2;++i) {
            for (int j=0; j<rows_cols.getMatSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(70, 70));
                try {
                  Image img = ImageIO.read(getClass().getResource("blue_doll.png"));
                  button.setIcon(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                } catch (Exception ex) {
                  System.out.println(ex);
                }
                buttonFields[i][j] = button;
                buttonFields[i][j].setBackground(blue);
                matPanel.add(button);
            }
        }
        
        for (int i=2; i<rows_cols.getMatSize()-2; ++i) {
            for (int j=0; j<rows_cols.getMatSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(70, 70));
                buttonFields[i][j] = button;
                buttonFields[i][j].setBackground(null);
                matPanel.add(button);
            }
        }
        
        for (int i=rows_cols.getMatSize()-2; i<rows_cols.getMatSize(); ++i) {
            for (int j=0; j<rows_cols.getMatSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(70, 70));
                try {
                  Image img = ImageIO.read(getClass().getResource("red_doll.png"));
                  button.setIcon(new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                } catch (Exception ex) {
                  System.out.println(ex);
                }
                buttonFields[i][j] = button;
                buttonFields[i][j].setBackground(red);
                matPanel.add(button);
            }
        }
        displayTurns = new JLabel("");
        displayTurns.setText("Player "+checkTurn() + "'s turn");
        displayTurns.setHorizontalAlignment(JLabel.CENTER);
    }
    
    
    /**
     * Method for checking which player's turn and gives back color
     * @return Color
     */
    public final Color ColorTurnCheck() {
        return this.turn%2==0 ? red : blue;
    }
    
    /**
     * Method for checking which player's turn and gives back player's name in string format
     * @return String
     */
    public final String checkTurn() {
        return this.turn%2==0 ? "RED" : "BLUE";
    }
    
    /**
     * Method for checking if a player won by reaching end 
     * row of the other side and gives back logical value
     * @return boolean
     */
    public boolean PlayerWinCheck() {
        for(int j=0;j<rows_cols.getMatSize(); ++j){
            if(buttonFields[0][j].getBackground()==red) return true;
            else if(buttonFields[rows_cols.getMatSize()-1][j].getBackground()==blue) return true;
        }
        return false;
    }
    
    /**
     * Method for checking which player won and displays message dialog with winner's name
     */
    public void refreshEachClick() {
        String winner_player=null;
        if(PlayerWinCheck()) {
            for(int j=0;j<rows_cols.getMatSize(); ++j){
                if(buttonFields[0][j].getBackground()==red) winner_player="RED";
                else if(buttonFields[rows_cols.getMatSize()-1][j].getBackground()==blue) winner_player="BLUE";
            }
            JOptionPane.showMessageDialog(matPanel, "Winner of the game: Player "+winner_player, "GAME OVER!",JOptionPane.INFORMATION_MESSAGE);
            Window newWindow = new Window();
            newWindow.setVisible(true);
            matPanel.setVisible(false);
            displayTurns.setVisible(false);
            //gui.dispose();
            
//            return true;
        }else {
            displayTurns.setText("Player " +checkTurn() + "'s turn");
//            return false;
            
        }
        
    }
//    public void reallyEnd(){
//       // return refresh();
//       Window newWindow = new Window();
//       newWindow.setVisible(true);
//    //   gui.dispose();
//    }
    
    public JPanel getMatrixPanel() {
        return matPanel;
    }
    
    public JLabel getDisplayTurns() {
        return displayTurns;
    }
    
    class ButtonListener implements ActionListener {
        private int currX, currY;
        /**
         * Constructor for ButtonListener class
         * @param currX
         * @param currY 
         */
        public ButtonListener(int currX, int currY) {
            this.currX = currX;
            this.currY = currY;
        }
        @Override
        /**
         * Method for handling the action when the button is clicked
         * and check if each player's move is valid
         */
        public void actionPerformed(ActionEvent e) {
            //System.out.println("("+prevX+","+prevY+")->"+"("+x+","+y+")");
//            if(prevX2!=null && prevY2!=null && blue==buttonFields[currX][currY].getBackground()&& ColorTurnCheck()==red) {
//                JOptionPane.showMessageDialog(matPanel, "Not BLUE's turn, player RED should move, try again!", "Alert!", JOptionPane.PLAIN_MESSAGE);
//                BlueSetPrev();
//                turn--;
//            }
//            else if(prevX1!=null && prevY1!=null && red==buttonFields[currX][currY].getBackground()&& ColorTurnCheck()==blue) {
//                JOptionPane.showMessageDialog(matPanel, "Not RED's turn, player BLUE should move try again!", "Alert!", JOptionPane.PLAIN_MESSAGE);
//                turn--;
//                RedSetPrev();
//            }
            if(ColorTurnCheck()==red){
                if(prevX1 == null && prevY1 == null) {
                        prevX1=currX;
                        prevY1=currY;
                } else if (RedDiffCheck() && (prevY1-currY==1 && prevX1-currX==1)) {
                        RedMakeMove();
                        RedSetPrev();

                } else if (RedDiffCheck() && (prevY1==currY && prevX1-1==currX) && buttonFields[currX][currY].getBackground()!=blue) {
                        RedMakeMove();
                        RedSetPrev();

                } else if (RedDiffCheck() && (currX-prevX1==-1 && currY-prevY1==1)) {
                        RedMakeMove();
                        RedSetPrev();
                }
//                else if(turnColor()!=buttons[prevX1][prevY1].getBackground()) {
//                    JOptionPane.showMessageDialog(matPanel, "Not RED'S turn : BLUE player should move try again!", "Alert!", JOptionPane.PLAIN_MESSAGE);

//                } 
                else {
//                    if(black!=buttons[prevX1][prevY1].getBackground()) {
//                    JOptionPane.showMessageDialog(matPanel, "Not RED'S turn : BLUE player should move try again!", "Alert!", JOptionPane.PLAIN_MESSAGE);
//                    setPrev1(); turn--;
//                    }
                    JOptionPane.showMessageDialog(matPanel, "INVALID MOVE, please try again!", "Alert!", JOptionPane.ERROR_MESSAGE);
                    RedSetPrev(); 
                    turn--;               
                }
                refreshEachClick();
            }
            else if(ColorTurnCheck()==blue){
                if(prevX2 == null && prevY2 == null) {
                    //  the next move havent chosen yet
                        prevX2=currX;
                        prevY2=currY;
                }
                else if (BlueDiffCheck() && (currX-prevX2==1 && currY-1==prevY2)) {
                    BlueMakeMove();
                    BlueSetPrev();
            
                }
                else if (BlueDiffCheck() && (currX-prevX2==1 && currY==prevY2) && buttonFields[currX][currY].getBackground()!=red) {
                        BlueMakeMove();
                        BlueSetPrev();
                }
                else if (BlueDiffCheck() && (currX-prevX2==1 && prevY2-1==currY)) {
                        BlueMakeMove();
                        BlueSetPrev();
                } 
                else {
                    JOptionPane.showMessageDialog(matPanel, "INVALID MOVE, please try again!", "Alert!", JOptionPane.ERROR_MESSAGE);
                    BlueSetPrev(); 
                    turn--;
                }
                refreshEachClick();
            }
            
        }
        
        /**
         * Method for checking if the red player played a different move
         * @return boolean
         */
        public boolean RedDiffCheck() {
            return (prevX1 != currX || currY != prevY1) && (buttonFields[currX][currY].getBackground() != buttonFields[prevX1][prevY1].getBackground());
        }
        
        /**
         * Method for checking if the blue player played a different move
         * @return boolean
         */
        public boolean BlueDiffCheck() {
            return (prevX2 != currX || currY != prevY2) && (buttonFields[currX][currY].getBackground() != buttonFields[prevX2][prevY2].getBackground());
        }
        
        /**
         * Method for resetting previous red player's point to null and increment turn
         */
        public void RedSetPrev() {
            prevX1=null;
            prevY1=null;
            turn++;
        }
        
        /**
         * Method for resetting previous blue player's point to null and increment turn
         */
        public void BlueSetPrev() {
            prevX2=null;
            prevY2=null;
            turn++;
        }
        
        /**
         * If the red player made a valid move, then this method will make 
         * previous move's icon and color to a new one.
         */
        public void RedMakeMove() {
                prevColor1 = buttonFields[currX][currY].getBackground();
                buttonFields[currX][currY].setBackground(buttonFields[prevX1][prevY1].getBackground());
                try {
                  Image img = ImageIO.read(getClass().getResource("red_doll.png"));
                  buttonFields[currX][currY].setIcon(new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                } catch (Exception ex) {
                  System.out.println(ex);
                }
                buttonFields[prevX1][prevY1].setBackground(null);
                buttonFields[prevX1][prevY1].setIcon(null);
        }
        
        /**
         * If the blue player made a valid move, then this method will change 
         * previous move's icon and color to a new one.
         */
        public void BlueMakeMove() {
                prevColor2 = buttonFields[currX][currY].getBackground();
                buttonFields[currX][currY].setBackground(buttonFields[prevX2][prevY2].getBackground());
                try {
                  Image img = ImageIO.read(getClass().getResource("blue_doll.png"));
                  buttonFields[currX][currY].setIcon(new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                } catch (Exception ex) {
                  System.out.println(ex);
                }
                buttonFields[prevX2][prevY2].setBackground(null);
                buttonFields[prevX2][prevY2].setIcon(null);
        }
    }
}
