package com.chessgame.chess.pieces;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.ChessMatch;
import com.chessgame.chess.ChessPiece;
import com.chessgame.chess.Color;

public class King extends ChessPiece
{
    private ChessMatch chessMatch;
    public King(Board board, Color color, ChessMatch chessMatch)
    {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean testRookCastling(Position position)
    {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);

        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
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

        //castling
        if(getMoveCount() == 0 && !chessMatch.isCheck())
        {
            Position positionRookRight = new Position(position.getRow(), position.getColumn() + 3);
            if(testRookCastling(positionRookRight))
            {
                Position position1 = new Position(position.getRow(), position.getColumn() + 1);
                Position position2 = new Position(position.getRow(), position.getColumn() + 2);
                if(getBoard().piece(position1) == null && getBoard().piece(position2) == null)
                {
                    matrix[position.getRow()][position.getColumn()+2] = true;
                }
            }

            Position positionRookLeft = new Position(position.getRow(), position.getColumn() - 4);
            if(testRookCastling(positionRookLeft))
            {
                Position position1 = new Position(position.getRow(), position.getColumn() - 1);
                Position position2 = new Position(position.getRow(), position.getColumn() - 2);
                Position position3 = new Position(position.getRow(), position.getColumn() - 3);
                if(getBoard().piece(position1) == null && getBoard().piece(position2) == null && getBoard().piece(position3) == null)
                {
                    matrix[position.getRow()][position.getColumn()-2] = true;
                }
            }
        }

        return matrix;
    }
}
