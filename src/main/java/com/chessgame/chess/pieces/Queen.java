package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class Queen extends ChessPiece
{
    public Queen(Board board, Color color)
    {
        super(board, color);
    }

    @Override
    public String toString()
    {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves()
    {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position positionAux = new Position(0, 0);

        //above
        positionAux.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setRow(positionAux.getRow() - 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //left
        positionAux.setValues(position.getRow(), position.getColumn() - 1 );
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setColumn(positionAux.getColumn() - 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //rigth
        positionAux.setValues(position.getRow(), position.getColumn() + 1 );
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setColumn(positionAux.getColumn() + 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

        //below
        positionAux.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
        {
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setRow(positionAux.getRow() + 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
            matrix[positionAux.getRow()][positionAux.getColumn()] = true;

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
