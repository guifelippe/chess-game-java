package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class Knight extends ChessPiece
{
    public Knight(Board board, Color color)
    {
        super(board, color);
    }

    @Override
    public String toString()
    {
        return "N";
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

        positionAux.setValues(position.getRow() - 1, position.getColumn() - 2);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() - 2, position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() - 2, position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() - 1, position.getColumn() + 2);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() + 1, position.getColumn() + 2);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() + 2, position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() + 2, position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        positionAux.setValues(position.getRow() + 1, position.getColumn() - 2);
        if(getBoard().positionExists(positionAux) && canMove(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        return matrix;
    }
}
