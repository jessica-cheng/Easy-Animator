package cs3500.animator.controller;

import java.awt.*;

import cs3500.animator.model.IModel;

/**
 * This interface represents a hybrid controller which runs the animation. An object of the
 * controller represents a controller for a type of output of the animation. Includes functionality
 * from {@link IController} as well as additional functionality. Additional functionality include
 * start, pause, resume, and restart the animation with explicit user input. Also includes option to
 * enable looping for the animation, increase or decrease speed of the animation, and exporting the
 * animation to an SVG file named by the user.
 */
public interface IHybridController extends IController {
  /**
   * Stops the timer to set the speed to the given fps and starts the timer again so that it runs
   * at the given fps.
   *
   * @param fps the fps to be changed to
   */
  void setAndRestart(int fps);

  /**
   * Starts the timer with the given speed.
   *
   * @param i the speed
   */
  void timerRestart(int i);

  /**
   * Gets the boolean field of whether the animation is paused or not.
   *
   * @return true if animation is paused, else false
   */
  boolean getIsPaused();

  /**
   * Sets the boolean field to true when animation is paused.
   */
  void setPause();

  /**
   * Sets the boolean field to false so animation is not paused.
   */
  void resume();

  /**
   * Starts the animation over again from the beginning.
   */
  void restart();

  /**
   * Gets the boolean field of whether the animation has loops or not.
   *
   * @return true if animation loops, else false
   */
  boolean getHasLoop();

  /**
   * Sets the boolean field to true if animation loops, or false if animation does not loop.
   */
  void setLoop();

  /**
   * Loops the animation so that it restarts from the beginning once animation ends.
   */
  void loop();

  /**
   * Produces a deep copy of the model.
   *
   * @return deep copy of the model
   */
  IModel getModel();

  /**
   * Gets the fps.
   *
   * @return fps
   */
  int getFps();

  /**
   * Gets the frame count.
   *
   * @return the frame count
   */
  int getFrameCount();


  void scrub(int frame);

  boolean scurbContains(Point e);
}
