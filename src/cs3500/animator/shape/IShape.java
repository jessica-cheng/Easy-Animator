package cs3500.animator.shape;


import java.util.List;

import cs3500.animator.animation.IAnimation;

/**
 * This interface represents the shapes of the animation. An object of the shape represents
 * a shape in the animator.
 */
public interface IShape {
  /**
   * Gets the color of the shape.
   *
   * @return Color of the shape.
   */
  Color getColor();

  /**
   * Sets the color of the shape to a new color.
   *
   * @param red   the red
   * @param green the green
   * @param blue  the blue
   */
  void setColor(double red, double green, double blue);

  /**
   * Produces the string of the shape Color.
   *
   * @return String of the color in (red, green, blue) format
   */
  String strColor();

  /**
   * Gets the position of the shape.
   *
   * @return Position of the shape
   */
  Position getPosition();

  /**
   * Sets the position of the shape.
   *
   * @param x the x coordinate of the shape
   * @param y the y coordinate of the shape
   */
  void setPosition(double x, double y);

  /**
   * Gets the width of the shape.
   *
   * @return width of the shape
   */
  double width();

  /**
   * Sets the width of the shape.
   *
   * @param width of the shape
   */
  void setWidth(double width);

  /**
   * Gets the height of the shape.
   *
   * @return height of the shape
   */
  double height();

  /**
   * Sets the height of the shape.
   *
   * @param height of the shape
   */
  void setHeight(double height);

  /**
   * Adds an animation to the shape's list of animations.
   *
   * @param anim animation to be added to list of animations
   */
  void addAnimation(IAnimation anim);

  /**
   * Runs all the animations at the given tick.
   *
   * @param frame the current tick
   */
  void shapeAtTime(int frame);

  /**
   * Checks if this shape is visible.
   *
   * @param frame frame to check visibility
   * @return true if shape is visible; else false
   */
  boolean isSeen(int frame);

  /**
   * Gets name of this shape.
   *
   * @return this shape's name.
   */
  String getName();

  /**
   * Gets type of this shape.
   *
   * @return this shape's type.
   */
  ShapeType getType();

  /**
   * Gets start time of the shape.
   *
   * @return this shape's start time.
   */
  double getStart();

  /**
   * Gets end time of the shape.
   *
   * @return this shape's end time.
   */
  double getEnd();

  /**
   * Gets a list of all this shape's animations.
   *
   * @return a list of all the shape's animations
   */
  List<IAnimation> getAnimations();

  /**
   * Generates a formatted String for an svg at the given fps.
   *
   * @param fps current tick speed
   * @return a formatted String
   */
  String generateSVG(int fps, boolean loop);

  IShape copyShape(IShape s);

  String generateLoop();

  Double getRotation();

  void setRotation(Double rotation);

  int getLayer();
}
