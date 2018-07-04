package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.List;

public class Worm {

    private int length;
    private List<Piece> pieces;
    private Direction direction;

    public Worm(int originalX, int originalY, Direction originalDirection){
        this.length = 3;
        this.pieces = new ArrayList<Piece>();
        pieces.add(new Piece(originalX, originalY));
        this.direction = originalDirection;
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction dir){
        direction = dir;
    }

    public int getLength(){
        return pieces.size();
    }

    public List<Piece> getPieces(){
        return pieces;
    }

    public void move(){
        // move the worm one piece forward in a certain direction
        if (direction == Direction.UP){
            pieces.add(new Piece(headPiece().getX(), headPiece().getY() - 1));
        } else if (direction == Direction.RIGHT){
            pieces.add(new Piece(headPiece().getX()+1, headPiece().getY()));
        } else if (direction == Direction.DOWN){
            pieces.add(new Piece(headPiece().getX(), headPiece().getY() + 1));
        } else if (direction == Direction.LEFT){
            pieces.add(new Piece(headPiece().getX() -1 , headPiece().getY()));
        }
        if (pieces.size() > length){
            pieces.remove(0);
        }
    }

    public void grow(){
        // grow the worm by one piece, but only on the following move
        if (pieces.size()>2) {
            this.length++;
        }
    }

    public boolean runsInto(Piece piece){
        // check whether the worm ran into a parameter piece
        for (Piece wormPiece : pieces){
            if (wormPiece.equals(piece)){
                return true;
            }
        }
        return false;
    }

    private Piece headPiece(){
        return pieces.get(pieces.size()-1);
    }

    public boolean runsIntoItself(){
        // check whether the worm ran into itself
        if (pieces.size()>2){
            for (int i = 0; i < pieces.size()-1; i++){
                if (headPiece().equals(pieces.get(i))) {

                    return true;
                }
            }
        }
        return false;
    }
}
