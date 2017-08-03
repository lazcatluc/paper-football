package ro.contezi.paperfootball.draw;

import java.util.Arrays;
import java.util.stream.IntStream;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.Point;

public class AsciiCanvas implements Canvas {

    private char[][] drawMatrix;
    private final int originX;
    private final int originY;
    private final int scaleFactor = 3;
    
    public AsciiCanvas() {
        this(FootballField.DEFAULT_WIDTH + 2, FootballField.DEFAULT_HEIGHT + 4);
    }
    public AsciiCanvas(int width, int height) {
        int realWidth = scaleFactor * width;
        int realHeight = scaleFactor * height;
        drawMatrix = new char[realHeight][realWidth];
        Arrays.stream(drawMatrix).forEach(matrixRow -> Arrays.fill(matrixRow, ' '));
        originX = realWidth / 2;
        originY = realHeight / 2;
    }
    
    int getX(Point point) {
        return point.getX() * scaleFactor + originX;
    }
    
    int getY(Point point) {
        return point.getY() * scaleFactor + originY;
    }
    
    @Override
    public void draw(Point point) {
        drawMatrix[getY(point)][getX(point)] = 'o';
    }

    @Override
    public void drawCurrent(Point point) {
        drawMatrix[getY(point)][getX(point)] = '@';
    }

    @Override
    public void drawLine(Point start, Point end) {
        if (start.getY().equals(end.getY())) {
            drawHorizontalLine(start, end);
            return;
        }
        if (start.getX().equals(end.getX())) {
            drawVerticalLine(start, end);
            return;
        }
        if ((start.getX() - end.getX()) * (start.getY() - end.getY()) > 0){
            drawMainDiagonal(start, end);
            return;
        }
        drawOffDiagonal(start, end);
    }
    
    private void drawMainDiagonal(Point start, Point end) {
        int startX = Math.min(getX(start), getX(end));
        int startY = Math.min(getY(start), getY(end));
        
        IntStream.range(1, scaleFactor).forEach(i ->
            drawMatrix[startY + i][startX + i] = '/');
    }
    
    private void drawOffDiagonal(Point start, Point end) {
        int startX = Math.min(getX(start), getX(end));
        int startY = Math.max(getY(start), getY(end));
        
        IntStream.range(1, scaleFactor).forEach(i ->
            drawMatrix[startY - i][startX + i] = '\\');
    }
    
    private void drawVerticalLine(Point start, Point end) {
        int startY = Math.min(getY(start), getY(end));
        IntStream.range(startY + 1, startY + scaleFactor).forEach(y ->
            drawMatrix[y][getX(end)] = '|');
    }
    
    private void drawHorizontalLine(Point start, Point end) {
        int startX = Math.min(getX(start), getX(end));
        IntStream.range(startX + 1, startX + scaleFactor).forEach(x ->
            drawMatrix[getY(end)][x] = '-');
    }

    public char[][] getDrawMatrix() {
        return drawMatrix;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int h = drawMatrix.length - 1; h >= 0; h--) {
            sb.append(drawMatrix[h]).append("\n");
        }
        return sb.toString();
    }

}
