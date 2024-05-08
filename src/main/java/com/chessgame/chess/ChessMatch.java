package com.chessgame.chess;

import com.chessgame.boardgame.Board;
import com.chessgame.boardgame.Piece;
import com.chessgame.boardgame.Position;
import com.chessgame.chess.pieces.King;
import com.chessgame.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch
{
    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch()
    {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;
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

    public boolean getCheck()
    {
        return this.check;
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

        if(testCheck(currentPlayer))
        {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

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

    private void undoMove(Position source, Position target, Piece capturedPiece)
    {
        Piece pieceAux = board.removePiece(target);
        board.placePiece(pieceAux, source);

        if(capturedPiece != null)
        {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void nextTurn()
    {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color)
    {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color)
    {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece piece : list)
            if(piece instanceof King)
                return (ChessPiece) piece;

        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color)
    {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece piece : opponentPieces)
        {
            boolean[][] matrix = piece.possibleMoves();
            if(matrix[kingPosition.getRow()][kingPosition.getColumn()])
                return true;
        }

        return false;
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
        placeNewPiece('h', 8, new King(board, Color.WHITE));
    }
}
