package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class Rook extends ChessPiece
{
    public Rook(Board board, Color color)
    {
        super(board, color);
    }

    @Override
    public String toString()
    {
        return "R";
    }
}
