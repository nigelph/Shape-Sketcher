/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nigel_Phan_17983161;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author khj8681
 */
public class ShapeSketcher extends JPanel implements ActionListener //extend Jpanel so MyGUI is-a JPanel and inherits all behaviours
{

    public final int PANEL_WIDTH = 480;
    public final int PANEL_HEIGHT = 400;
    private final JRadioButton lineRadioButton, ovalRadioButton, circleRadioButton, rectangleRadioButton, squareRadioButton;
    private final JButton colourButton, clearButton, undoButton, saveButton, openButton;
    private final JCheckBox fillCheckBox;
    private final DrawingPanel drawingPanel;
    private Color colour;
    private Shape currentShape;
    private Point startPoint;
    private Point controlPoint;
    private final ArrayList<Shape> storedShapeList = new ArrayList<>();
    private final ArrayList<Shape> currentShapeList = new ArrayList<>();
    private int mouseX;
    private int mouseY;
    private String mousePositionString = "";
    private boolean colourFlag = false;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    public ShapeSketcher() {
        super(new BorderLayout());   //invoke super class Jpanel constructor
        setPreferredSize(new Dimension(PANEL_WIDTH + 200, PANEL_HEIGHT + 200)); //set JPanel size

        //Colour Picker  
        colourButton = new JButton("Colour Picker");
        colourButton.addActionListener(this);

        //Misc Buttons
        fillCheckBox = new JCheckBox("Fill Shape");
        clearButton = new JButton("Clear Drawing");
        undoButton = new JButton("Undo");
        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        undoButton.addActionListener(this);
        clearButton.addActionListener(this);
        fillCheckBox.addActionListener(this);
        openButton.addActionListener(this);
        saveButton.addActionListener(this);

        //Create shape radio buttons
        lineRadioButton = new JRadioButton("Line", true);
        ovalRadioButton = new JRadioButton("Oval");
        circleRadioButton = new JRadioButton("Circle");
        rectangleRadioButton = new JRadioButton("Rectangle");
        squareRadioButton = new JRadioButton("Square");

        //Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(lineRadioButton);
        group.add(ovalRadioButton);
        group.add(circleRadioButton);
        group.add(rectangleRadioButton);
        group.add(squareRadioButton);

        //Position radio buttons at the top
        JPanel northPanel = new JPanel();
        northPanel.add(openButton);
        northPanel.add(saveButton);
        northPanel.add(lineRadioButton);
        northPanel.add(ovalRadioButton);
        northPanel.add(circleRadioButton);
        northPanel.add(rectangleRadioButton);
        northPanel.add(squareRadioButton);
        northPanel.add(undoButton);
        northPanel.add(clearButton);
        //Allign buttons in the north panel to the center
        northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(northPanel, BorderLayout.NORTH);

        //Center Drawing Panel
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);
        drawingPanel.addMouseListener(drawingPanel);

        //South panel for buttons
        JPanel southPanel = new JPanel();
        southPanel.add(fillCheckBox);
        southPanel.add(colourButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    //Innerclass that handles the drawings
    private class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, Serializable {

        public DrawingPanel() {

            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            this.addMouseMotionListener(this);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Shape s : storedShapeList) {
                s.draw(g);
            }
            for (Shape s : currentShapeList) {
                s.draw(g);
            }
            //Print out the co-ordinates of the mouse
            g.drawString(mousePositionString, mouseX, mouseY);
        }

        //MOUSEMOTIONLISTENER
        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
            //controlPoint = e.getPoint();
            Object source = e.getSource();

            if (source == drawingPanel) {
                if (lineRadioButton.isSelected()) {
                    currentShape = new Line(startPoint);

                } else if (ovalRadioButton.isSelected()) {
                    currentShape = new Oval(startPoint);

                } else if (circleRadioButton.isSelected()) {
                    currentShape = new Circle(startPoint);

                } else if (rectangleRadioButton.isSelected()) {
                    currentShape = new Rectangle(startPoint);

                } else if (squareRadioButton.isSelected()) {
                    currentShape = new Square(startPoint);
                }
            }
            currentShape.setColour(colour);
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            controlPoint = e.getPoint();

            if (lineRadioButton.isSelected()) {
                currentShape.setControlPoint(controlPoint);
            } else if (ovalRadioButton.isSelected()) {
                currentShape.setControlPoint(controlPoint);
            } else if (circleRadioButton.isSelected()) {
                currentShape.setControlPoint(controlPoint);
            } else if (rectangleRadioButton.isSelected()) {
                currentShape.setControlPoint(controlPoint);
            } else if (squareRadioButton.isSelected()) {
                currentShape.setControlPoint(controlPoint);
            }
            //Track mouse co-ordinates and save them
            mouseX = e.getX();
            mouseY = e.getY();
            //Position co-ordinate printed out when user drags the mouse
            mousePositionString = "H: " + mouseX + " V: " + mouseY;
            System.out.println(currentShape.toString());
            currentShapeList.add(currentShape);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            controlPoint = e.getPoint();

            currentShape.setControlPoint(controlPoint);

            currentShapeList.clear();
            mousePositionString = "";
            storedShapeList.add(currentShape);

            //Pass in the selected colour into the shape's class
            currentShape.setColour(colour);
            //Pass in true or false if the user chooses to fill the shape
            if (!(currentShape instanceof Line)) {
                currentShape.setFilled(colourFlag);
            }

            repaint();
        }

        //Unused methods
        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    //ACTIONLISTENER
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        JFileChooser chooser = new JFileChooser(new File("."));

        if (source == openButton) {
            //Allows the user to open open
            int status = chooser.showOpenDialog(ShapeSketcher.this);
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    this.loadDrawingFromFile(chooser.getSelectedFile());
                } catch (IOException i) {
                    JOptionPane.showMessageDialog(null, e, "Error Opening File...", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, e, "ERROR WITH OBJECT CAST!", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Drawing Was Saved Successfully!");
                drawingPanel.repaint();
            }

        } else if (source == saveButton) {
            //Allows the user to save drawing
            int value = chooser.showSaveDialog(ShapeSketcher.this);
            if (value == JFileChooser.APPROVE_OPTION) {
                try {
                    this.saveDrawingToFile(storedShapeList, chooser.getSelectedFile());
                } catch (IOException t) {
                    System.out.println("Error Saving File...");
                } catch (ClassNotFoundException ty) {
                    System.out.println("ERROR WITH OBJECT CAST");
                }
                System.out.println("File Saved Successfully!");
            }
        } else if (source == colourButton) {

            colour = JColorChooser.showDialog(null, "Choose a Colour", Color.WHITE);
            colourButton.setBackground(colour);
            if (colour == Color.WHITE || colour == Color.white) {
                colourButton.setForeground(Color.BLACK);
            } else {
                colourButton.setForeground(Color.WHITE);
            }

        } else if (source == undoButton) {
            storedShapeList.remove(storedShapeList.size() - 1);
            repaint();
        } else if (source == clearButton) {
            Object[] options = {"Yes", "No", "Cancel"};
            int check = JOptionPane.showOptionDialog(null, "Are you sure you want to clear your drawing?", "Clear Drawing?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
            if (check == JOptionPane.YES_OPTION) {
                //Clear the drawings
                storedShapeList.clear();
            } else {
                //Leave the drawings as it is
            }
            repaint();

        } //Check if the shape needs to be filled in
        else if (fillCheckBox.isSelected()) {
            colourFlag = true;
        } else {
            colourFlag = false;
        }
    }

    public ArrayList<Shape> loadDrawingFromFile(File f) throws IOException, ClassNotFoundException {
        ArrayList<Shape> list;

        this.storedShapeList.clear();
        this.currentShapeList.clear();

        ois = new ObjectInputStream(new FileInputStream(f));

        list = (ArrayList<Shape>) ois.readObject();

        for (Shape s : list) {
            this.storedShapeList.add(s);
        }
        ois.close();
        return list;
    }

    public void saveDrawingToFile(ArrayList<Shape> list, File f) throws IOException, ClassNotFoundException {

        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(storedShapeList);

        for (Shape s : list) {
            oos.writeObject(s);
        }
        oos.flush();
        oos.close();
    }

    public static void main(String[] args) {
        ShapeSketcher myPanel = new ShapeSketcher();
        JFrame frame = new JFrame("Shape Sketcher"); //create frame to hold our JPanel subclass	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        //Position frame on center of screen 
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));
        //show the frame	
        frame.setVisible(true);
    }
}
