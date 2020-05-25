package cs3500.animator.controller;

/**
 * This interface represents a controller which runs the animation. An object of the controller
 * represents a controller for a type of output of the animation.
 */
public interface IController {
  /**
   * Runs the animation.
   */
  void run();

  /**
   * Sets this controllers fps.
   *
   * @param fps the fps to be set.
   */
  void setFPS(int fps);
}
