package com.chessgame.application;

import com.chessgame.chess.ChessException;
import com.chessgame.chess.ChessMatch;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.ChessPosition;

import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (!chessMatch.isCheckMate())
        {
           try
           {
               UI.clearScreen();
               UI.printMatch(chessMatch, captured);
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

               if(capturedPiece != null)
                   captured.add(capturedPiece);

               if(chessMatch.getPromoted() != null)
               {
                   System.out.print("Enter piece for promotion (B/N/R/Q): ");
                   String type = scanner.nextLine().toUpperCase();
                   while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q"))
                   {
                       System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                       type = scanner.nextLine().toUpperCase();
                   }
                   chessMatch.replacePromotedPiece(type);
               }
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

        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
