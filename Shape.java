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
public abstract class Shape implements EnclosesRegion, Serializable {

    protected Point startPoint;
    protected Point controlPoint;
    private Color colour;

    public Shape() {

    }

    public Shape(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public void setControlPoint(Point controlPoint) {
        this.controlPoint = controlPoint;
    }

    public void draw(Graphics g) {

    }

    @Override
    public String toString() {
        return "Start Point at [" + startPoint.x + "," + startPoint.y + "], Control Point at [" + controlPoint.x + "," + controlPoint.y + "]";
    }

    @Override
    public void setFilled(boolean filled) {
    }
}
