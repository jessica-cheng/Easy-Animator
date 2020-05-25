package cs3500.animator.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import javax.swing.JPanel;
import cs3500.animator.shape.ReadableShape;

public class MyPanel extends JPanel {
  ArrayList<ReadableShape> shapes = new ArrayList<>();

  public MyPanel() {
    setPreferredSize(new Dimension(1000,1000));
  }


  public void remove(){
    super.removeAll();
    super.revalidate();
    super.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    super.repaint();
    Graphics2D g2d = (Graphics2D) g;
    for (ReadableShape s : shapes) {
      AffineTransform transform = new AffineTransform();
      RectangularShape shape = null;
      switch (s.getType()) {
        case oval:
          shape = new Ellipse2D.Double(0,0,s.getWidth(),s.getHeight());
          break;

        case rectangle:
          shape = new Rectangle2D.Double(0,0,s.getWidth(),s.getHeight());
          break;

        default:
          //no default
      }
      g.setColor(new Color(
              (int) (s.getCol().getRed() * 255),
              (int) (s.getCol().getGreen() * 255),
              (int) (s.getCol().getBlue() * 255)));
      transform.translate(s.getX() + s.getWidth()/2,s.getY() + s.getHeight()/2);
      transform.rotate(Math.toRadians(s.getRotation()));
      transform.translate(-1 * s.getWidth()/2, -1 * s.getHeight()/2);
      Shape a = transform.createTransformedShape(shape);
      g2d.fill(a);
      g2d.draw(a);
      }
    }




  public void setShapes(ArrayList<ReadableShape> shapes) {
    this.shapes = shapes;
  }
}
