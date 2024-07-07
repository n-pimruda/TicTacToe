import java.util.Scanner;

public class OX {
    private char[][] board;
    private boolean player1;
    private boolean gameEnded;
    private Scanner in;

    public OX() {
        board = new char[3][3];
        for(int i = 0; i<3 ; i++){
            for(int j = 0; j<3 ;j++){
                board[i][j] = '-';
            }
        }
        player1 = true;
        gameEnded = false;
        in = new Scanner(System.in);
    }

    public void printBoard(){
        for(int i = 0; i<3; i++){
            for(int j =0; j<3; j++){
                System.out.print(board[i][j] + " ");
            }
        System.out.println(" ");    
        }
    }

    public void playGame(){
        while(!gameEnded){
            printBoard();

            if(player1){
                System.out.println("Turn X ! ");
            } else {
                System.out.println("Turn O ! ");
            }

            char c = player1 ? 'X' : 'O';

            int row = 0;
            int col = 0;

            while(true){
                System.out.println("Please enter row col (e.g. 1 1 ) : ");
                row = in.nextInt() - 1;
                col = in.nextInt() - 1;
                
                // Out of the Board Check
                if(row<0 || col <0 || row>= 3 || col >=3){
                    System.out.println("This position is out of the board! Please Try again.");
                } else if(board[row][col] != '-'){
                    System.out.println("Someone already made a move! Please try again.");
                } else {
                    break;
                }       
            }
            board[row][col] = c;

            //Check win
            if(checkWin()){
                printBoard();
                System.out.println("Player " + c + " Wins !!!");
                gameEnded = true;
            } else if(isBoardFull()){
                printBoard();
                System.out.println("This game is a Tie !!!");
                gameEnded = true;
            } else {
                player1 = !player1;
            }
        }
        in.close();
    }
    private boolean checkWin(){
        return (checkRows() || checkColumns() || checkDiagonals());
    }
    private boolean checkRows(){
        for(int i = 0; i<3; i++){
            if(board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }
        return false;
    }
    private boolean checkColumns(){
        for(int i = 0; i<3; i++){
            if(board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonals(){
        if(board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return true;
        }
        if(board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            return true;
        }
        return false;
    }
    private boolean isBoardFull(){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(board[i][j] == '-'){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OX game = new OX();
        game.playGame();
    }
    
}
