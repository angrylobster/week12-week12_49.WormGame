package wormgame.game;

import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);
        this.worm = new Worm(width/2, height/2, Direction.DOWN);
        this.width = width;
        this.height = height;
        this.apple = createNewApple();
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

    }

    private Apple createNewApple(){
        while (true){
            Apple newApple = new Apple((int) (Math.random() * width), (int) (Math.random() * height));
            for (Piece wormPiece : worm.getPieces()){
                if (!wormPiece.equals(newApple)) {
                    return newApple;
                }
            }
        }
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Worm getWorm(){
        return worm;
    }

    public void setWorm(Worm worm){
        this.worm = worm;
    }

    public Apple getApple(){
        return this.apple;
    }

    public void setApple(Apple apple){
        this.apple = apple;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        worm.move();
        if (worm.runsInto(apple)) {
            worm.grow();
            apple = createNewApple();
        }
        if (worm.runsIntoItself()) {
            continues = false;
            super.stop();
        }

        for (Piece wormPiece : worm.getPieces()){
            if (wormPiece.getX() >= width || wormPiece.getY() >= height || wormPiece.getX() <=0 || wormPiece.getY() <= 0){
                continues = false;
                super.stop();
            }
        }

        System.out.println(continues);
        updatable.update();

        setDelay(1000 / worm.getLength());
    }

}
