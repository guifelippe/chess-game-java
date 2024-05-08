package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class Pawn extends ChessPiece
{
    public Pawn(Board board, Color color)
    {
        super(board, color);
    }

    @Override
    public String toString()
    {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves()
    {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position positionAux = new Position(0, 0);

        if(getColor() == Color.WHITE)
        {
            positionAux.setValues(position.getRow() - 1, position.getColumn());
            if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow() - 2, position.getColumn());
            Position positionAux2 = new Position(position.getRow() - 1, position.getColumn());
            if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux) && getMoveCount() == 0
            && getBoard().positionExists(positionAux2) && !getBoard().thereIsAPiece(positionAux2))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow() - 1, position.getColumn() - 1);
            if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow() - 1, position.getColumn() + 1);
            if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        else
        {
            positionAux.setValues(position.getRow() + 1, position.getColumn());
            if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow() + 2, position.getColumn());
            Position positionAux2 = new Position(position.getRow() + 1, position.getColumn());
            if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux) && getMoveCount() == 0
                    && getBoard().positionExists(positionAux2) && !getBoard().thereIsAPiece(positionAux2))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow() + 1, position.getColumn() - 1);
            if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;

            positionAux.setValues(position.getRow() - 1, position.getColumn() + 1);
            if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux))
                matrix[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        return matrix;
    }
}
