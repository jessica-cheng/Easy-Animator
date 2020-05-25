package cs3500.animator.animation;

import cs3500.animator.shape.IShape;

/**
 * This interface represents types of animations. An object of the animation represents a
 * type of animation.
 */
public interface IAnimation extends Comparable<IAnimation> {
  /**
   * Checks if same animation type and times are overlapping on same shape. If so, then conflicting.
   *
   * @param other the other animation compared to this animation
   * @return true if other animation conflicts with this animation; else return false
   */
  boolean isConflicting(IAnimation other);

  /**
   * Runs the animation at the given tick by tweening.
   *
   * @param tick current tick the animation is at
   */
  void applyAnimation(int tick);

  /**
   * Gets the name of the animations shape.
   *
   * @return the name of this animations shape.
   */
  String getShapeName();

  /**
   * Gets the time this animation starts.
   *
   * @return start time
   */
  int getStart();

  /**
   * Gets this animations type.
   *
   * @return the type of this animation.
   */
  AnimationType getAnimationType();

  /**
   * Generates the text output of this animation at the given fps.
   *
   * @param fps the fps to format the string to.
   * @return the formatted animation.
   */
  String animString(int fps);

  /**
   * Generates the svg formatted string for each animation.
   *
   * @param fps  the fps to format the string to
   * @param loop if the svg should loop
   * @return the formatted string for an svg
   */
  String generateSVG(int fps, boolean loop);

  IAnimation copyAnimation();

  void setShape(IShape a);
}
