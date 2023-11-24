import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize the game board
        char[][] board = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };   
        
        // Display the initial board
        PrintBoard(board);
        System.out.println("Welcome to Tic Tac Toe");

        Scanner userInput = new Scanner(System.in);
        char humanPlayerMarker = 'X';
        char computerPlayerMarker = 'O';

        Random random = new Random();
        int maxAttempts = 9;

        // Game loop
        for (int i = 0; i < 9; i++) {
            int choice;
            if (i % 2 == 0) {
                // Player's turn
                choice = getUserInput(userInput, board);
                PlaceMarker(board, choice, humanPlayerMarker);
            } else {
                // Computer's turn
                boolean validChoiceFound = false;
                // Attempt a valid computer move
                for (int attempt = 0; attempt < maxAttempts && !validChoiceFound; attempt++) {
                    int computerChoice = random.nextInt(9) + 1;
                    if (IsPositionAvailable(board, computerChoice)) {
                        // Place computer's marker on a valid position
                        PlaceMarker(board, computerChoice, computerPlayerMarker);
                        System.out.println("The Computer Moves");
                        validChoiceFound = true;
                    }
                }
                if (!validChoiceFound) {
                    System.out.println("Computer couldn't make a valid move. Ending the game.");
                    break;
                }
            }

            // Display the current board
            
            System.out.println();
            PrintBoard(board);

            // Check for a win or draw
            if (CheckWin(board, humanPlayerMarker)) {
                System.out.println("Congratulations! Player 'X' wins!");
                break;
            } else if (CheckWin(board, computerPlayerMarker)) {
                System.out.println("Computer wins! Better luck next time!");
                break;
            } else if (i == 8) {
                System.out.println("The game ends in a draw!");
            }
        }
    }


    public static int getUserInput(Scanner userInput, char[][] board) {
        int choice = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("Player 'X', choose a position on the grid from 1-9: ");
            if (userInput.hasNextInt()) {
                choice = userInput.nextInt();
                if (choice >= 1 && choice <= 9) {
                    isValidInput = IsPositionAvailable(board, choice);
                    if (!isValidInput) {
                        System.out.println("Position " + choice + " is already taken. Please choose another position.");
                    }
                } else {
                    System.out.println("Please enter a number from 1 to 9.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                userInput.next(); // Consume invalid input to prevent an infinite loop
            }
        }

        return choice;
    }
    private static boolean IsPositionAvailable(char[][] board, int choice) {
        int row = -1, col = -1;

        // Map choice to the corresponding row and column in the board
        switch (choice) {
            case 1:
                row = 0;
                col = 0;
                break;
            case 2:
                row = 0;
                col = 2;
                break;
            case 3:
                row = 0;
                col = 4;
                break;
            case 4:
                row = 2;
                col = 0;
                break;
            case 5:
                row = 2;
                col = 2;
                break;
            case 6:
                row = 2;
                col = 4;
                break;
            case 7:
                row = 4;
                col = 0;
                break;
            case 8:
                row = 4;
                col = 2;
                break;
            case 9:
                row = 4;
                col = 4;
                break;
            default:
                System.out.println("Invalid position. Please choose a position from 1-9.");
                return false;
        }

        // Check if the chosen position is within the bounds of the board and is not already occupied
        if (row != -1 && col != -1 && board[row][col] == ' ') {
            return true; // Position is available for placing a marker
        } else {
            return false; // Position is not available
        }
    }
	public static void PlaceMarker(char[][] board, int choice, char marker) {
        switch (choice) {
            case 1:
                board[0][0] = marker;
                break;
            case 2:
                board[0][2] = marker;
                break;
            case 3:
                board[0][4] = marker;
                break;
            case 4:
                board[2][0] = marker;
                break;
            case 5:
                board[2][2] = marker;
                break;
            case 6:
                board[2][4] = marker;
                break;
            case 7:
                board[4][0] = marker;
                break;
            case 8:
                board[4][2] = marker;
                break;
            case 9:
                board[4][4] = marker;
                break;
            default:
                System.out.println("Invalid position. Please choose a position from 1-9.");
        }
    }
    public static void PrintBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static boolean CheckWin(char[][] board, char marker) {
        // Check rows, columns, and diagonals for a winning combination

        // Check rows
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == marker && board[i][2] == marker && board[i][4] == marker)) {
                return true; // Winning row
            }
        }

        // Check columns
        for (int i = 0; i < 5; i += 2) {
            if ((board[0][i] == marker && board[2][i] == marker && board[4][i] == marker)) {
                return true; // Winning column
            }
        }

        // Check diagonals
        if ((board[0][0] == marker && board[2][2] == marker && board[4][4] == marker) ||
                (board[0][4] == marker && board[2][2] == marker && board[4][0] == marker)) {
            return true; // Winning diagonal
        }

        return false; // No winning combination found
    }
   
}