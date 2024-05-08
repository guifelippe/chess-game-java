package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class King extends ChessPiece
{
    public King(Board board, Color color)
    {
        super(board, color);
    }

    @Override
    public String toString()
    {
        return "K";
    }

    private boolean canMove(Position position)
    {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves()
    {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position positionAux = new Position(0, 0);

        //above
        positionAux.setValues(position.getRow() - 1, position.getColumn());
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //left
        positionAux.setValues(position.getRow(), position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //right
        positionAux.setValues(position.getRow(), position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //below
        positionAux.setValues(position.getRow() + 1, position.getColumn());
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //nw
        positionAux.setValues(position.getRow() - 1, position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //ne
        positionAux.setValues(position.getRow() - 1, position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //sw
        positionAux.setValues(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //se
        positionAux.setValues(position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        return matrix;
    }
}
