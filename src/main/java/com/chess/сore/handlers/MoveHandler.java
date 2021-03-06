package com.chess.сore.handlers;

import com.chess.сore.сhessboard.ChessBoardLogic;
import com.chess.сore.figures.abstrac.Figure;

import java.awt.*;

public class MoveHandler {
    private Figure figure;
    private boolean[][] figuresSteps;
    private ChessBoardLogic chessBoardLogic;

    public MoveHandler(Figure figure) {
        this.figure = figure;
        this.figuresSteps = figure.getSteps();
        this.chessBoardLogic = ChessBoardLogic.getInstance();
    }

    public void moveFigure(){
        Point point;
        point = figure.getLocation();

        double centerBoardX = point.getX() + (double) figure.getWidth() / 2;
        double centerBoardY = point.getY() + (double) figure.getHeight() / 2;

        int figureInArrayX = (int) (centerBoardX / 100);
        int figureInArrayY = (int) (centerBoardY / 100);

        if (figuresSteps[figureInArrayY][figureInArrayX]) {
            if (((int) centerBoardX > (figureInArrayX) * 100) && ((int) centerBoardX < figureInArrayX * 100 + 100)
                    && ((int) centerBoardY > figureInArrayY * 100) && ((int) centerBoardY < figureInArrayY * 100 + 100)) {

                // Клетка занята союзной фигурой
                if ((ChessBoardLogic.figuresArray[figureInArrayY][figureInArrayX] != null)
                        && (ChessBoardLogic.figuresArray[figureInArrayY][figureInArrayX].getColor().equals(figure.getColor()))) {

                    moveFigure(figure.getPositionInArrayX(), figure.getPositionInArrayY());

                } else {
                    // Удаление фигуры с доски
                    Figure figureToRemove = getFigure(figureInArrayX, figureInArrayY);
                    if ((figureToRemove != null) && (!figureToRemove.getColor().equals(figure.getColor()))) {
                        removeFigure(figureToRemove);
                        moveFigure(figureInArrayX, figureInArrayY);
                    } else {
                        moveFigure(figureInArrayX, figureInArrayY);
                    }
                }
            }
        } else {
            figure.setBounds(figure.getPositionOnBoardX(), figure.getPositionOnBoardY(), figure.getWidth(), figure.getHeight());
        }
        chessBoardLogic.checkPawnsToChange();
    }

    // Удаление фигуры
    private void removeFigure(Figure figureToRemove) {
        chessBoardLogic.removeFigure(figureToRemove);
    }

    // Сдвиг фигуры
    private void moveFigure(int x, int y) {
        figure.setBounds((x * 100), (y * 100), figure.getWidth(), figure.getHeight());

        ChessBoardLogic.figuresArray[figure.getPositionInArrayY()][figure.getPositionInArrayX()] = null;
        ChessBoardLogic.figuresArray[y][x] = null;
        ChessBoardLogic.figuresArray[y][x] = figure;

        figure.setPositionInArrayX(x);
        figure.setPositionInArrayY(y);
        figure.move();

        isFirstStep();
        predictStepsOfAllFigures();
        ChessBoardLogic.changeTurn();
    }

    private void isFirstStep() {
        if (figure.isFirstStep()) {
            figure.setFirstStep(false);
        }
    }

    private Figure getFigure(int x, int y) {
        return ChessBoardLogic.figuresArray[y][x];
    }

    private void predictStepsOfAllFigures() {
        chessBoardLogic.predictStepsOfAllFigures();
    }
}
