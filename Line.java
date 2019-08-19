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
public class Line extends Shape implements Serializable {

    protected Point lineStartPoint;

    public Line() {

    }

    public Line(Point startPoint) {
        super.startPoint = startPoint;
        this.lineStartPoint = startPoint;
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
        //Pass in selected colour
        g.setColor(this.getColour());
        g.drawLine((int) lineStartPoint.getX(), (int) lineStartPoint.getY(), (int) this.controlPoint.getX(), (int) this.controlPoint.getY());
    }
}
