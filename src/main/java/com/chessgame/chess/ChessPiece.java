package com.chessgame.chess;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Piece;

public class ChessPiece extends Piece
{
    private Color color;

    public ChessPiece(Board board, Color color)
    {
        super(board);
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}
