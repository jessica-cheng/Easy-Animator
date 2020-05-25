package cs3500.animator.view;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.util.ButtonListener;

/**
 * Represents the visual view of the animation.
 */
public class GuiView extends JFrame implements IView {
  MyPanel myPanel;
  JScrollPane jScrollPane;

  /**
   * Constructs a GUI view.
   */
  public GuiView() {
    setSize(800,800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    myPanel = new MyPanel();
    jScrollPane = new JScrollPane(myPanel);
    add(jScrollPane);
    setVisible(true);
  }

  @Override
  public void drawShapes(ArrayList<ReadableShape> shape) {
    myPanel.setShapes(shape);
    myPanel.repaint();
  }

  @Override
  public void drawString(String in) {
    return;
  }

  @Override
  public void export() {
    return;
  }

  @Override
  public void setFilePath(String filePath) {
    return;
  }

  @Override
  public void drawForSVG(List<IShape> shape) throws IOException {
    return;
  }

  public void setFps(int fps){
    return;
  }

  @Override
  public void setButtonListener(ButtonListener buttonListener) {
    throw new UnsupportedOperationException("this doesnt need a button listner");
  }

  @Override
  public String getFinal() {
    throw new UnsupportedOperationException();

  }

  @Override
  public void setLooping(double maxFrame) {
    throw new UnsupportedOperationException();

  }

  @Override
  public int getEnd() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setShapes(ArrayList<ReadableShape> shapes) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean scrubContains(Point e) {
    throw new UnsupportedOperationException();
  }

}