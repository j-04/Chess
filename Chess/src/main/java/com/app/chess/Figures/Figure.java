package com.app.chess.Figures;

import javax.swing.*;
import java.awt.*;

public abstract class Figure extends JPanel implements IFigure {

    private int positionOnBoardX;
    private int positionOnBoardY;

    private int positionInArrayX;
    private int positionInArrayY;

    protected boolean firstStep = true;

    protected boolean[][] steps = new boolean[8][8];

    private Color color = null;

    public void setColor(Color color){

        this.color = color;

        if(!color.equals(Color.WHITE))
            setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
        else
            setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
    }

    public int getPositionOnBoardX() {
        return positionOnBoardX;
    }

    public int getPositionOnBoardY() {
        return positionOnBoardY;
    }

    public int getPositionInArrayX() {
        return positionInArrayX;
    }

    public int getPositionInArrayY() {
        return positionInArrayY;
    }

    public boolean isFirstStep(){
        return firstStep;
    }

    public void setFirstStep(boolean firstStep){
        this.firstStep = firstStep;
    }

    public Color getColor(){
        return color;
    }

    public boolean[][] getSteps(){
        return steps;
    }

    @Override
    public int getWidth(){
        return 100;
    }

    @Override
    public int getHeight(){
        return 100;
    }


    @Override
    public void moveTo(int x, int y) {
        this.positionInArrayX = x;
        this.positionInArrayY = y;
        this.positionOnBoardX = x * 100;
        this.positionOnBoardY = y * 100;
    }
}