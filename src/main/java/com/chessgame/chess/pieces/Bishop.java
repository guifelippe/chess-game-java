package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class Bishop extends ChessPiece
{
    public Bishop(Board board, Color color)
    {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves()
    {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position positionAux = new Position(0, 0);

        //nw
        positionAux.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() - 1, positionAux.getColumn() - 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //ne
        positionAux.setValues(position.getRow() - 1, position.getColumn() + 1 );
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() - 1, positionAux.getColumn() + 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //sw
        positionAux.setValues(position.getRow() + 1, position.getColumn() - 1 );
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() + 1, positionAux.getColumn() - 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //se
        positionAux.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() + 1, positionAux.getColumn() + 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        return matrix;
    }
}
