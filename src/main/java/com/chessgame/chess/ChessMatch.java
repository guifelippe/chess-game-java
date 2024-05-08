package com.chessgame.chess;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Piece;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.pieces.King;
import com.chessgame.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch
{
    private int turn;
    private Color currentPlayer;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch()
    {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn()
    {
        return this.turn;
    }

    public Color getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public ChessPiece[][] getPieces()
    {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++)
        {
            for(int j = 0; j < board.getColumns(); j++)
            {
                mat[i][j] = (ChessPiece)board.piece(i, j);
            }
        }

        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition)
    {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition)
    {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position position)
    {
        if(!board.thereIsAPiece(position))
            throw new ChessException("There is no piece on source position");

        if(!board.piece(position).isThereAnyPossibleMove())
            throw new ChessException("There is no possible moves for the chosen piece");

        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor())
            throw new ChessException("The chosen piece is not yours");
    }

    private void validateTargetPosition(Position source, Position target)
    {
        if(!board.piece(source).possibleMove(target))
            throw new ChessException("The chosen piece can't move to target position");
    }

    private Piece makeMove(Position source, Position target)
    {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);

        if(capturedPiece != null)
        {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        board.placePiece(piece, target);

        return capturedPiece;
    }

    private void nextTurn()
    {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece)
    {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup()
    {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('h', 2, new King(board, Color.BLACK));
    }
}
