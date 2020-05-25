package cs3500.animator.view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.util.ButtonListener;

/**
 * This interface represents how the animation is viewed. An object of a view represents the type of
 * view for the animation.
 */
public interface IView {
  /**
   * Shows the shape and its animations.
   *
   * @param shape the shape to be shown
   * @throws IOException With a bad list of shapes.
   */
  void drawShapes(ArrayList<ReadableShape> shape) throws IOException;

  /**
   * Shows the shape and its animation in SVG.
   *
   * @param shape the shape to be shown
   * @throws IOException with bad list of shapes
   */
  void drawForSVG(List<IShape> shape) throws IOException;

  /**
   * Shows the string of the animation.
   *
   * @param in the string of the animation
   */
  void drawString(String in);


  /**
   * Send or enters the finished frames.
   */
  void export();

  /**
   * Sets the filepath to output if applicable.
   *
   * @param filePath the path to set
   */
  void setFilePath(String filePath);

  /**
   * Sets the fps.
   *
   * @param fps fps to be set to
   */
  void setFps(int fps);

  /**
   * Sets the listener to the given button listener.
   *
   * @param buttonListener button listener to be set to
   */
  void setButtonListener(ButtonListener buttonListener);

  /**
   * Gets the SVG string for finished frames of animations.
   *
   * @return SVG string
   */
  String getFinal();

  /**
   * Sets looping at last frame.
   *
   * @param maxFrame the max frame
   */
  void setLooping(double maxFrame);

  /**
   * Gets the time of the last frame.
   * @return int
   */
  int getEnd();

  /**
   * Sets the shapes to be drawn.
   * @param shapes to draw.
   */
  void setShapes(ArrayList<ReadableShape> shapes);

  /**
   * Checks if the given point is in the scurbContainer.
   * @param e a point
   * @return boolean if true.
   */
  boolean scrubContains(Point e);

}
