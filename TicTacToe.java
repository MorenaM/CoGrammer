import java.util.Scanner;

public class TicTacToe {

    public final static int TOTAL_ROWS = 3;
    public final static int TOTAL_COLS = 3;
    
    public final static int PLAYER_ONE = 0;
    public final static int PLAYER_TWO = 1;
    
    public final static char PLAYER_ONE_PIECE = 'X';
    public final static char PLAYER_TWO_PIECE = 'O';
    
    public static final char EMPTY = ' ';
    
    public final static int DIRECTION_LEFT = 0;
    public final static int DIRECTION_RIGHT = 1;
    public final static int DIRECTION_UP = 2;
    public final static int DIRECTION_DOWN = 3;
    
    public static void main(String[] args) {
    	
        //initialize the board
        initializeGameBoard();
        
        //We determine which player starts first.
        currentPlayer = 0;
        
        while(isEndOfGame() == false)
        {
        	displayGameBoard();
            
            int[] newPosition = getPosition();
            
            if(isPositionOccupied(newPosition[0], newPosition[1]) == true)
            {
                System.out.println("Position " + newPosition[0] + "," 
                + newPosition[1] + " is already occupied. Current player skips a turn.");
            }
            else
            {
                placeItem(newPosition[0], newPosition[1]);
            }
            
          //At the end of each turn, the current player is switched.
           currentPlayer = switchPlayer(currentPlayer);
            
        }
        
        // comment

        if(existRowOfItem(PLAYER_ONE_PIECE) == true)
        {
            System.out.println("PLAYER ONE wins");
        }
        else if(existRowOfItem(PLAYER_TWO_PIECE) == true)
        {
            System.out.println("PLAYER TWO wins");
        }
        else if(existColumnOfItem(PLAYER_ONE_PIECE) == true)
        {
            System.out.println("PLAYER ONE wins");
        }
        else if(existColumnOfItem(PLAYER_TWO_PIECE) == true)
        {
            System.out.println("PLAYER TWO wins");
        }
        else if(existDiagonalOfItem(PLAYER_ONE_PIECE) == true)
        {
            System.out.println("PLAYER ONE wins");
        }
        else if(existDiagonalOfItem(PLAYER_TWO_PIECE) == true)
        {
            System.out.println("PLAYER TWO wins");
        }
        else
        {
            System.out.println
            ("It is a Draw");
        }
    }
    
    /*
    Sets all the elements of the board array to EMPTY.
    */
    public static void initializeGameBoard()
    {
        for (int row = 0; row < TOTAL_ROWS; row++)
        {
            for (int col = 0; col < TOTAL_COLS; col++)
            {
                gameBoardPositions[row][col] = EMPTY;
            }
        }
    }
    
    /*
    Displays the game board.
    */
    public static void displayGameBoard()
    {
    	System.out.print(" -------------");
    	System.out.println();
        for (int row = 0; row < TOTAL_ROWS; row++)
        {
            for (int col = 0; col < TOTAL_COLS; col++)
            {
                char charDisplay = gameBoardPositions[row][col];
                System.out.print(" | " + charDisplay);
            }
            System.out.print(" |");
            System.out.println();
            System.out.print(" -------------");
            System.out.println();
        }
        System.out.println();
    }
    
    /*
    Gets a set of coordinates from players of the game
    */
    public static int [] getPosition()
    {
    	Scanner myObj = new Scanner(System.in);
    
    	int row = -1;
    	int col = -1;
    	
    	if (currentPlayer == PLAYER_ONE)
        {
            System.out.println("Player X, please enter a row (0, 1 or 2): ");
            row  = myObj.nextInt();
            System.out.println("\nPlayer X, please enter a column (0, 1 or 2): ");
            col = myObj.nextInt();
        }
        else
        {
        	System.out.println("Player O, please enter a row (0, 1 or 2): ");
            row  = myObj.nextInt();
            System.out.println("\nPlayer O, please enter a column (0, 1 or 2): ");
            col = myObj.nextInt();
        } 

        return new int[] { row, col };
    }
    
    /*
    Places the item on the selected coordinates of the game
    board, based on the current player.
    */
    public static void placeItem(int row, int col)
    {
        if(currentPlayer == PLAYER_ONE)
        {
            gameBoardPositions[row][col] = PLAYER_ONE_PIECE;
        }
        else
        {
            gameBoardPositions[row][col] = PLAYER_TWO_PIECE;
        }
    }
    
    /*
    Checks if a game board position specified by row and col is occuppied.
    */
    public static boolean isPositionOccupied(int row, int col)
    {
        if (gameBoardPositions[row][col] == EMPTY)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /*
    Changes the current player at the end of a turn.
    */

    public static int switchPlayer(int player)
    {
        if (currentPlayer == PLAYER_ONE)
        {
            return PLAYER_TWO;
        }
        else
        {
            return PLAYER_ONE;
        }
    }
    
    public static boolean isEndOfGame()
    {
      if(		endGameConditionOne() == true
                			||
                endGameConditionTwo() == true)
        {
        	System.out.println("Game Over.");
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /*
    Determines whether the game has ended, by checking if
    all positions on the game board have been filled up.
    */
    public static boolean endGameConditionOne()
    {   
        boolean isEndOfGame = true;
                
        for(int row = 0; row < TOTAL_ROWS; row++)
        {
            for(int col = 0; col < TOTAL_COLS; col++)
            {
                if(gameBoardPositions[row][col] == EMPTY)
                {
                	// If any position on the game board is found to be empty,
                	// isEndGame is set to false and returned
                    isEndOfGame = false;
                    return isEndOfGame;
                }
            }
        }
        
        return isEndOfGame;
    }
    
    /*
    Checks if the game has ended based depending on the presence of filled
    diagonals, rows and columns (with the same piece type)
    */
    public static boolean endGameConditionTwo()
    {   
        if(existRowOfItem(PLAYER_ONE_PIECE) == true
                			||
           existRowOfItem(PLAYER_TWO_PIECE) == true
           					||
           existColumnOfItem(PLAYER_ONE_PIECE) == true
           					||
           existColumnOfItem(PLAYER_TWO_PIECE) == true
           					||
           existDiagonalOfItem(PLAYER_ONE_PIECE)
           					||
           existDiagonalOfItem(PLAYER_TWO_PIECE))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /*
    Checks if a row of a game piece type exists on the board.
    */
    public static boolean existRowOfItem(char item)
    {
        int countNoOfItem = 0;
        boolean existRowOfItem = false;
        
        for(int row = 0; row < TOTAL_ROWS; row++)
        {
            countNoOfItem = 0;
            
            for(int col = 0; col < TOTAL_COLS; col++)
            {
                if(gameBoardPositions[row][col] == item)
                {
                    countNoOfItem++;
                }
                
                if(countNoOfItem == TOTAL_COLS)
                {
                    existRowOfItem = true;
                    return existRowOfItem;
                }
            }
        }
        return existRowOfItem;
    }
    
    /*
    Checks if a column of a game piece type exists on the board.
    */
    public static boolean existColumnOfItem(char item)
    {
        int countNoOfItem = 0;
        boolean existColumnOfItem = false;
        
        for(int col = 0; col < TOTAL_COLS; col++)
        {
            countNoOfItem = 0;
            
            for(int row = 0; row < TOTAL_ROWS; row++)
            {
                if(gameBoardPositions[row][col] == item)
                {
                    countNoOfItem++;
                }
                
                if(countNoOfItem == TOTAL_ROWS)
                {
                    existColumnOfItem = true;
                    return existColumnOfItem;
                }
            }
        }
        return existColumnOfItem;
    }
    
    /*
    Checks if a diagonal of a game piece type exists on the board.
    */
    public static boolean existDiagonalOfItem(char item)
    {
        int startRow = 0;
        int startCol = 0;
        
        int countNoOfItem = 0;
        boolean existDiagonalOfItem = false;
        
        /*
        Check for the presence of any diagonals from the Top-left
        corner to the Bottom-right corner.
        */

        for(int[] position = new int[] {startRow, startCol};       
        	position != null;
        	position = getNextDiagonalPosition(position, DIRECTION_RIGHT, DIRECTION_DOWN))
           {
            if(gameBoardPositions[position[0]][position[1]] == item)
            {
                countNoOfItem++;
            }
            
            if(countNoOfItem == TOTAL_ROWS)
            {
                existDiagonalOfItem = true;
                return existDiagonalOfItem;
            }
        }

        countNoOfItem = 0;
        startRow = 0;
        startCol = (TOTAL_COLS - 1);
        
        /*
        Check for the presence of diagonals from the Top-right corner to the Botton-left corner.
        */
        for(int[] position = new int[] {startRow, startCol};       
        	position != null;
        	position = getNextDiagonalPosition(position, DIRECTION_LEFT, DIRECTION_DOWN))
           {
            if(gameBoardPositions[position[0]][position[1]] == item)
            {
                countNoOfItem++;
            }
            
            if(countNoOfItem == TOTAL_ROWS)
            {
                existDiagonalOfItem = true;
                return existDiagonalOfItem;
            }
        }
        
        return existDiagonalOfItem;
    }
    
    /*
    Gets next coordinate position relative to a position on
    the board based on the direction parameters.
    */
    public static int [] getNextDiagonalPosition
    (int [] position, int horzDirection, int vertDirection)
    {
        int startRow = 0;
        int startCol = 0;

        int endRow = startRow + (TOTAL_ROWS - 1);
        int endCol = startCol + (TOTAL_COLS - 1);
        
        //Get the input position.
        int row = position[0];
        int col = position[1];
        
        if (horzDirection == DIRECTION_LEFT)
        {
            col--;
        }
        else if (horzDirection == DIRECTION_RIGHT)
        {
            col++;
        }        
        
        if (vertDirection == DIRECTION_UP)
        {
            row--;
        }
        else if (vertDirection == DIRECTION_DOWN)
        {
            row++;
        }
        
        //check board limits
        if (((row >= startRow) && (row <= endRow))
        				&&
            ((col >= startCol) && (col <= endCol)))
        {
            return new int[] { row, col };
        }
        else
        {
            return null;
        }
    }
    
    public static char[][] gameBoardPositions = new char[TOTAL_ROWS][TOTAL_COLS];
    public static int currentPlayer;
}
