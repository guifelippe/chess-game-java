package com.chessgame.application;

import com.chessgame.chess.ChessException;
import com.chessgame.chess.ChessMatch;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program
{
    public static void main(String[] args)
    {
        ChessMatch chessMatch = new ChessMatch();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
           try
           {
               UI.clearScreen();
               UI.printMatch(chessMatch);
               System.out.println();
               System.out.print("Source: ");
               ChessPosition source = UI.readChessPosition(scanner);

               boolean[][] possibleMoves = chessMatch.possibleMoves(source);
               UI.clearScreen();
               UI.printBoard(chessMatch.getPieces(), possibleMoves);

               System.out.println();
               System.out.print("Target: ");
               ChessPosition target = UI.readChessPosition(scanner);

               ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
           }
           catch (ChessException err)
           {
               System.out.println(err.getMessage());
               scanner.nextLine();
           }
           catch (InputMismatchException err)
           {
               System.out.println(err.getMessage());
               scanner.nextLine();
           }
        }
    }
}
