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
public class Oval extends Shape implements EnclosesRegion, Serializable {

    protected Point ovalStartPoint;
    private boolean checkFlag = false;

    public Oval() {

    }

    public Oval(Point startPoint) {
        super.startPoint = startPoint;
        this.ovalStartPoint = startPoint;
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

        int x = Math.min(this.ovalStartPoint.x, this.controlPoint.x);
        int y = Math.min(this.ovalStartPoint.y, this.controlPoint.y);
        int x2 = Math.abs(this.ovalStartPoint.x - this.controlPoint.x);
        int y2 = Math.abs(this.ovalStartPoint.y - this.controlPoint.y);

        if (checkFlag == true) {
            g.setColor(this.getColour());
            g.fillOval(x, y, x2, y2);
        } else {
            g.setColor(this.getColour());
            g.drawOval(x, y, x2, y2);
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
