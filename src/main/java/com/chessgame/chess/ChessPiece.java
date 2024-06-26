package com.chessgame.chess;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Piece;
import com.chessgame.boardgame.Position;

public abstract class ChessPiece extends Piece
{
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color)
    {
        super(board);
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public int getMoveCount()
    {
        return this.moveCount;
    }

    public void increaseMoveCount()
    {
        moveCount++;
    }

    public void decreaseMoveCount()
    {
        moveCount--;
    }

    public ChessPosition getChessPosition()
    {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position)
    {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);

        return piece != null && piece.getColor() != color;
    }
}
