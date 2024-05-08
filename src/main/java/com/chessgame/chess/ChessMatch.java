package com.chessgame.chess;

import com.chessgame.boardgame.Board;
import com.chessgame.chess.pieces.King;
import com.chessgame.chess.pieces.Rook;

public class ChessMatch
{
    private Board board;

    public ChessMatch()
    {
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece[][] getPieces()
    {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++)
        {
            for(int j = 0; j < board.getColumns(); j++)
            {
                mat[i][j] = (ChessPiece)board.piece(i, j);
            }
        }

        return mat;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece)
    {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup()
    {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('h', 2, new King(board, Color.BLACK));
    }
}
