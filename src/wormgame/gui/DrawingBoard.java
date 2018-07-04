package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable{
    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame wormGame, int pieceLength){
        this.wormGame = wormGame;
        this.pieceLength = pieceLength;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        for (Piece piece : wormGame.getWorm().getPieces()){
            graphics.fill3DRect(piece.getX() * pieceLength, piece.getY() * pieceLength, pieceLength, pieceLength, true);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(wormGame.getApple().getX() * pieceLength, wormGame.getApple().getY()* pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update(){
        super.repaint();
    }
}
