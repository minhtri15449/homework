/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tictactoe;

/**
 *
 * @author hacke
 */
public class Game {
    public static int EMPTY = 0;
    public static int CROSS = 1;
    public static int CIRCLE = 2;
    
    public static int PLAYING = 0;
    public static int CROSSWIN = 1;
    public static int CIRCLEWIN = 2;
    public static int TIE = 3;
    
    public static int ROWS = 3, COLS = 3;
    public static int[][] board = new int [ROWS][COLS];
    public static int currentState;
    public static int currentPlayer;
    public static int currentRow, currentCol;
    public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args){
        StartGame();
        do{
            currentRowandColumn(currentPlayer);
            updateGameState(currentPlayer, currentRow, currentCol);
            printBoard();
            if (currentState == CROSSWIN){
                System.out.println("'X' win");
            }else if (currentState == CIRCLEWIN){
                System.out.println("'O' win");
            }else if (currentState == TIE){
                System.out.println("It's a tie");
            }
            currentPlayer = (currentPlayer == CROSS) ? CIRCLE : CROSS;
        }while(currentState == PLAYING);
    }
    public static void StartGame(){
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                board[row][col] = EMPTY;
            }
        }
        currentState = PLAYING;
        currentPlayer = CROSS;
    }
    public static void playerMove(int theFirst){
        boolean validInput = false;
        do{
            if (theFirst == CROSS){
                System.out.println("Player 'X' choose your tile (row[1-3], column[1-3]:");;
            }else{
                System.out.println("Player 'O' choose your tile (row[1-3], column[1-3]:");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board [row][col] == EMPTY){
                currentRow = row;
                currentCol = col;
                board[currentRow][currentCol] = theFirst;
                validInput = true;
            }else{
                System.out.println("Move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again");
            }
        }while (!validInput);
    }
    public static void updateGameState(int theFirst, int currentRow, int currentCol){
        if (checkWin(theFirst, currentRow, currentCol)){
            currentState = (theFirst == CROSS) ? CROSSWIN : CIRCLEWIN;
        }else if(isTie()){
            currentState = TIE;
        }
    }
    public static boolean isTie(){
        for (int row = 0; row < ROWS; ++ row){
            for (int col = 0; col < COLS; ++col){
                if (board[row][col] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkWin(int theFirst,int currentRow,int currentCol){
        return (board[currentRow][0] == theFirst
                && board[currentRow][1] == theFirst
                && board[currentRow][2] == theFirst
              || board[0][currentCol] == theFirst
                   && board[1][currentCol] == theFirst
                   && board[2][currentCol] == theFirst
              || currentRow == currentCol            
                   && board[0][0] == theFirst
                   && board[1][1] == theFirst
                   && board[2][2] == theFirst
              || currentRow + currentCol == 2
                   && board[0][2] == theFirst
                   && board[1][1] == theFirst
                   && board[2][0] == theFirst);
    }
    public static void printBoard(){
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                printCell(board[row][col]);
                if (col != COLS - 1){
                    System.out.println("|");
                }
            }
            System.out.println();
            if (row != ROWS - 1){
                System.out.println("-----------");
            }
        }
        System.out.println();
    }
    public static void printCell(int content){
        switch(content){
            case EMPTY: System.out.println(" "); break;
            case CROSS: System.out.println("X"); break;
            case CIRCLE: System.out.println("O"); break;
        }
    }
}
