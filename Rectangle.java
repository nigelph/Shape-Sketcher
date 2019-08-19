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
public class Rectangle extends Shape implements EnclosesRegion, Serializable {

    protected Point rectangleStartPoint;
    private boolean checkFlag = false;

    public Rectangle() {

    }

    public Rectangle(Point startPoint) {
        super.startPoint = startPoint;
        this.rectangleStartPoint = startPoint;
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
        int x = Math.min(rectangleStartPoint.x, this.controlPoint.x);
        int y = Math.min(rectangleStartPoint.y, this.controlPoint.y);
        int x2 = Math.abs(rectangleStartPoint.x - this.controlPoint.x);
        int y2 = Math.abs(rectangleStartPoint.y - this.controlPoint.y);

        if (checkFlag == true) {
            g.setColor(this.getColour());
            g.fillRect(x, y, x2, y2);
        } else {
            g.setColor(this.getColour());
            g.drawRect(x, y, x2, y2);
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
