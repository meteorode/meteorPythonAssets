import java.io.*;
import java.util.Random;

/**
 * Created by meteor on 2/12/14.
 */

public class paDragonBoard {
    class gameBoard{
        public int boardArray[][] = new int[height][width];

        public void gameBoard()
        {
            for (int i=0; i<height; i++)
                for (int j=0; j<width; j++)
                    boardArray[i][j] = Block;
        }
    }   // end class gameBoard

    class positionAtBoard{
        public int row;
        public int col;

        public void positionAtBoard()
        {
            row = 0;
            col = 0;
        }
    }

    private static int height = 5;
    private static int width = 6;   // 5*6 board with six elements
    public static int Fire = 1;
    public static int Wood = 2;
    public static int Aqua = 3;
    public static int Light = 4;
    public static int Dark = 5;
    public static int Heal = 6;    // six elements type
    public static int Block = -1;   // blocks
    public static int Match = -2;   // match
    public static int Outside = -3; // outside
    public gameBoard pGameBoard = new gameBoard();

    public void paDragonBoard() throws IOException // constructor
    {                                   // from [F,W,R,L,D,H\n W,L,H...] to generate a board

    }    // end constructor

    public void displayBoard()
    {
        for (int i=0; i<height; i++)
        {
            for (int j=0; j<width; j++)
                System.out.print(pGameBoard.boardArray[i][j]+",");
            System.out.println();
        }
        System.out.println();
    }

    public void generateBoard() throws IOException
    {
        System.out.println("Please Input Game Board Information:");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        for (int i=0; i<height; i++)
        {
            String s = br.readLine();
            for (int j=0; j<width; j++)
            {
                pGameBoard.boardArray[i][j] = toElement(s.charAt(j*2));
                //System.out.print(i+","+j+","+boardArray[i][j]);
            }
        }
    }

    public void randomGetBoard()
    {
        Random r = new Random();
        for (int i=0; i<height; i++)
            for (int j=0; j<width; j++)
                pGameBoard.boardArray[i][j] = r.nextInt(7);
    }

    public void checkBoard()   // check whether can be 3-match, all elements will disappear marked with -2;
    {
        // from left to right, top to down check; so search an element, -> v ^ <-
        positionAtBoard start = new positionAtBoard();
        positionAtBoard curPosition = start;
        positionAtBoard curRight = new positionAtBoard();
        positionAtBoard curDown = new positionAtBoard();
        positionAtBoard curUp = new positionAtBoard();
        positionAtBoard curLeft = new positionAtBoard();
        int curColor = getColor(curPosition);
        int matchArea = 1;
        positionAtBoard[] matchAreaQueue = new positionAtBoard[width * height];

        while (onBoard(curPosition) && curColor != Match && !inArray(curPosition,matchAreaQueue))    // curPosition on board and not set to match
        {
            matchAreaQueue[matchArea-1] = curPosition;
            curRight.col = curPosition.col + 1;
            curRight.row = curPosition.row;
            curDown.col = curPosition.col;
            curDown.row = curPosition.row + 1;
            curUp.col = curPosition.col;
            curUp.row = curPosition.row - 1;
            curLeft.col = curPosition.col - 1;
            curLeft.row = curPosition.row;  // neighborhood
            //System.out.println(curPosition.row+" "+curPosition.col+" "+curColor+" "+getColor(curRight));
            if (getColor(curRight) == curColor ) // right color match
            {
                curPosition = curRight;
                matchArea++;
            }   // end if color match right
            else if (getColor(curDown) == curColor) // down color match
            {
                curPosition = curDown;
                matchArea++;
            }
            else if (getColor(curUp) == curColor)   // up
            {
                curPosition = curUp;
                matchArea ++;
            }
            else if (getColor(curLeft) == curColor) // left
            {
                curPosition = curLeft;
                matchArea ++;
            }
            else    // no similar color, check match area
            {
                if (matchArea >= 3)     // can be formed and erased
                {
                    for (int i=0; i<matchArea; i++)
                        setColor(matchAreaQueue[i],Match);
                }
                else
                    break;
            }
        }   // end while (match search)
    }

    private boolean onBoard(positionAtBoard p)  // is p on board
    {
        if (p.row < height && p.col < width && p.row >=0 && p.col >= 0)
            return true;
        else
            return false;
    }

    private void setColor(positionAtBoard p,int color)
    {
        if (onBoard(p))
            pGameBoard.boardArray[p.row][p.col] = color;
    }

    private int getColor(positionAtBoard p) // return color presented by int
    {
        if (onBoard(p))
            return pGameBoard.boardArray[p.row][p.col];
        else
            return Outside;
    }

    private int toElement(char c)
    {
        switch (c)
        {
            case 'F':   return Fire;
            case 'W':   return Wood;
            case 'A':   return Aqua;   // aqua for water
            case 'L':   return Light;
            case 'D':   return Dark;
            case 'H':   return Heal;
            default:    return Block;
        }
    }

    private boolean inArray(positionAtBoard p, positionAtBoard[] pArray)
    {
        int length = pArray.length;
        for (int i=0; i<length; i++)
            if (pArray[i] == p)
                return true;
        return false;
    }

    private int getPosition(positionAtBoard p)
    {
        return p.row * width + p.col;
    }

    private positionAtBoard convertPosition(int pos)
    {
        positionAtBoard p = new positionAtBoard();
        p.row = pos/width;
        p.col = pos % width;
        return p;
    }
}
