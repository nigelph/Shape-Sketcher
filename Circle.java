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
public class Circle extends Shape implements EnclosesRegion, Serializable{
    
    protected Point circleStartPoint;
    private boolean checkFlag = false;
    
    public Circle() {

    }
   
    public Circle(Point startPoint) {
        super.startPoint = startPoint;
        this.circleStartPoint = startPoint;
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

        int x = Math.abs(circleStartPoint.x - this.controlPoint.x);
        int y = Math.abs(circleStartPoint.y - this.controlPoint.y);
        int radius = Math.min(x, y);
        
        if (checkFlag == true) {
            g.setColor(this.getColour());
            g.fillOval(circleStartPoint.x - radius, circleStartPoint.y - radius, 2 * radius, 2 * radius);
        } else {
            g.setColor(this.getColour());
            g.drawOval(circleStartPoint.x - radius, circleStartPoint.y - radius, 2 * radius, 2 * radius);
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
