package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessMatch;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class Pawn extends ChessPiece
{
    private ChessMatch chessMatch;
    public Pawn(Board board, Color color, ChessMatch chessMatch)
    {
        super(board, color);
        this.chessMatch = chessMatch;
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

            //en passant white
            if(position.getRow() == 3)
            {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
                    matrix[left.getRow() - 1][left.getColumn()] = true;

                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
                    matrix[right.getRow() - 1][right.getColumn()] = true;
            }
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

            //en passant black
            if(position.getRow() == 4)
            {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable())
                    matrix[left.getRow() + 1][left.getColumn()] = true;

                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable())
                    matrix[right.getRow() + 1][right.getColumn()] = true;
            }
        }

        return matrix;
    }
}
