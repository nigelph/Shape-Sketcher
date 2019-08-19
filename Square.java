/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nigel_Phan_17983161;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author khj8681
 */
public class Square extends Rectangle implements EnclosesRegion, Serializable {

    protected Point squareStartPoint;
    private boolean checkFlag = false;

    public Square() {

    }

    public Square(Point startPoint) {
        super.startPoint = startPoint;
        this.squareStartPoint = startPoint;
    }

    @Override
    public Color getColour() {
        return super.getColour();
    }

    @Override
    public void setColour(Color colour) {
        super.setColour(colour);
    }

    @Override

    public void setControlPoint(Point controlPoint) {
        super.setControlPoint(controlPoint);
    }

    @Override
    public void draw(Graphics g) {
        int x;
        int y;
        int width = Math.abs(squareStartPoint.x - this.controlPoint.x);
        int height = Math.abs(squareStartPoint.y - this.controlPoint.y);
        int side = Math.max(width, height);

        if (squareStartPoint.x > this.controlPoint.x) {
            x = squareStartPoint.x - side;
        } else {
            x = squareStartPoint.x;
        }

        if (squareStartPoint.y > this.controlPoint.y) {
            y = squareStartPoint.y - side;
        } else {
            y = squareStartPoint.y;
        }

        if (checkFlag == true) {
            g.setColor(this.getColour());
            g.fillRect(x, y, side, side);
        } else {
            g.setColor(this.getColour());
            g.drawRect(x, y, side, side);
        }
    }

    @Override
    public void setFilled(boolean filled) {
        if (filled) {
            this.setColour(this.getColour());
            checkFlag = true;
        } else {
            checkFlag = false;
        }
    }
}
