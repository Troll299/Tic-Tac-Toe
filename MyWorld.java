import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

public class MyWorld extends World
{
    /**
     * Name: Mickail Krivoshea
     * Teacher: Mr. Hardman / hardman the heartless
     * Lab #1, Program #1
     * Date Last Modified: June 15 2018
     *
     */
    
     private boolean playerOneTurn = true;
    
    private boolean messageShown = false;
    
    private String playerOneName;
    private String playerTwoName;
    
    private String [][] board = new String [3][3];
    
    /**
     * Constructor for objects of class GameBoard.
     * 
     * @param There are no parameters
     * @return Objects of type GameBoard
     */
    public MyWorld()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(300, 300, 1); 
        
        drawLines();
        
        for( int r = 0; r < board.length; r++)
        {
            for( int c = 0; c < board[r].length; c++)
            {
                board[r][c] = "";
            }
        }
    }
    
    /**
     * drawLines Draws six lines to make the board
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void drawLines()
    {
        getBackground().setColor( Color.BLACK );
        
        for( int i = 100; i < getWidth(); i += 100 )
        {
            getBackground().drawLine(i, 0, i, getHeight() );
            getBackground().drawLine(0, i, getWidth(), i);
        }
    }
    
    /**
     * started Asks the two players for there names
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void started()
    {
       playerOneName = JOptionPane.showInputDialog( null, "Player one please enter your name:", "Player One Name", JOptionPane.QUESTION_MESSAGE );
       playerTwoName = JOptionPane.showInputDialog( null, "Player two please enter your name:", "Player Two Name", JOptionPane.QUESTION_MESSAGE );
    }
    
    /**
     * act Is basically the act cycle
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        displayBoard();
        Greenfoot.delay(10);
        
        if( checkPlayerOneWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player One!" );
            Greenfoot.stop();
        }
        else if( checkPlayerTwoWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player Two!" );
            Greenfoot.stop();
        }
        else if( checkBoardFilled() == true )
        {
           JOptionPane.showMessageDialog( null, "It is a draw!" ); 
           Greenfoot.stop();
        }
        else
        {
            if( messageShown == false )
            {
                showTurn();
                messageShown = true;
            }
            
            checkMouseClick();
        }
    }
    
    /**
     * showTurn Shows which player's turn it is
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void showTurn()
    {
        if( playerOneTurn == true )
        {
            JOptionPane.showMessageDialog( null, "Player One's turn" );
        }
        else if( playerOneTurn == false )
        {
            JOptionPane.showMessageDialog( null, "Player two's turn" );
        }
    }
    
    /**
     * checkMouseClick Checks if the players have clicked the mouse to place X's or O's on the board
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkMouseClick()
    {
        MouseInfo mouse;
        int rowNum;
        int columnNum;
        
        if( Greenfoot.mouseClicked(this) )
        {
            mouse = Greenfoot.getMouseInfo();
            columnNum = mouse.getX()/(getWidth()/3);
            rowNum = mouse.getY() / ( getHeight() / 3 );
            
            if( board[rowNum][columnNum] == "" )
            {
                if( playerOneTurn == true)
                {
                    board[rowNum][columnNum] = "X";
                    playerOneTurn = false;
                    messageShown = false;
                }
                else
                {
                    board[rowNum][columnNum] = "O";
                    playerOneTurn = true;
                    messageShown = false;
                }
            }
        }
    }
    
    /**
     * displayBoard displays the board to the user
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void displayBoard()
    {
        GreenfootImage toDisplay;
        
        for( int r= 0; r < board.length; r++)
        {
            for( int c =0; c < board[r].length; c++)
            {
                toDisplay = new GreenfootImage( board [r][c], 100, Color.BLACK, new Color(0, 0, 0, 0) );
                getBackground().drawImage( toDisplay, c * getWidth()/3 + (getWidth()/3 - toDisplay.getWidth())/2, r * getHeight()/3 + (getHeight()/3 - toDisplay.getHeight())/2);
                
            }
        }
    }
    
    /**
     * checkPlayerOneWin Checks if player one got 3 X's in a row
     * 
     * @param there are no parameters
     * @return A boolean variable
     */
    private boolean checkPlayerOneWin()
    {
        boolean playerOneWin = false;
        
        if( board[0][0] == "X" && board[0][1] == "X" && board[0][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[1][0] == "X" && board[1][1] == "X" && board[1][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[2][0] == "X" && board[2][1] == "X" && board[2][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][0] == "X" && board[1][0] == "X" && board[2][0] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][1] == "X" && board[1][1] == "X" && board[2][1] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][2] == "X" && board[1][2] == "X" && board[2][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][0] == "X" && board[1][1] == "X" && board[2][2] == "X")
        {
            playerOneWin = true;
        }
        else if( board[0][2] == "X" && board[1][1] == "X" && board[2][0] == "X")
        {
            playerOneWin = true;
        }
        
        return playerOneWin;
    }
    
    /**
     * checkPlayerOneWin Checks if player two got 3 O's in a row
     * 
     * @param there are no parameters
     * @return A boolean variable
     */
    private boolean checkPlayerTwoWin()
    {
        boolean playerTwoWin = false;

         if( board[0][0] == "O" && board[0][1] == "O" && board[0][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[1][0] == "O" && board[1][1] == "O" && board[1][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[2][0] == "O" && board[2][1] == "O" && board[2][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][0] == "O" && board[1][0] == "O" && board[2][0] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][1] == "O" && board[1][1] == "O" && board[2][1] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][2] == "O" && board[1][2] == "O" && board[2][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][0] == "O" && board[1][1] == "O" && board[2][2] == "O")
        {
            playerTwoWin = true;
        }
        else if( board[0][2] == "O" && board[1][1] == "O" && board[2][0] == "O")
        {
            playerTwoWin = true;
        }
        
        return playerTwoWin;
    }
    
    /**
     * checkBoardFilled Checks if the board is filled and no player won
     * 
     * @param there are no parameters
     * @return A boolean variable
     */
    private boolean checkBoardFilled()
    {
        boolean boardFilled = false;
        
        for( int r= 0; r < board.length; r++)
        {
            for( int c =0; c < board[r].length; c++)
            {
                
            }
        }
        
        return boardFilled;
    }
}
