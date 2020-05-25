package cs3500.animator.model;

import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.shape.IShape;

import java.util.ArrayList;
import java.util.List;


/**
 * This interface represents the model. An object of the model represents a model in the animator.
 */
public interface IModel {
  /**
   * Adds a shape in the list of shapes in the animator.
   *
   * @param shape the shape to be added in the list of shapes in the animator
   */
  void addShapes(IShape shape);

  /**
   * Adds an animation to a shape's list of animations by that shape's name.
   *
   * @param name name of the shape
   * @param a    animation to be added to the shape's list of animation
   * @throws IllegalArgumentException if name of shape does not exist
   */
  void addAnimation(String name, IAnimation a);

  /**
   * Produces a text description of the shapes and its animations with the animations ordered by its
   * start time.
   *
   * @return String description of the animation
   */
  String getShapeDescription();

  /**
   * Gets the list of shapes in the animation.
   *
   * @return list of shapes in the animation
   */
  List<IShape> getListOfShapes();


  /**
   * Returns the max time of the animation.
   *
   * @return max frame
   */
  double getMaxFrame();

  /**
   * Gets a list of shapes at with there current modifications.
   *
   * @param frame the frame to get shapes at
   * @return All of the shapes at that frame in a list.
   */
  ArrayList<ReadableShape> getShapesAtFrame(int frame);

  /**
   * Gets a list of all the shapes and there animations at the given time.
   *
   * @param frame The current frame to get text at.
   * @param fps   The fps of the animation.
   * @return A list of all the animations and there shapes at a certain frame.
   */
  ArrayList<ReadableShape> getAnimTextAtFrame(int frame, int fps);

  /**
   * Tweens a shape at a certian time.
   *
   * @param shape The shape to be tweened.
   * @param frame The frame it is going to be tweened to.
   */
  IShape tween(IShape shape, int frame);


  /**
   * Sets the current list of files to the original list.
   */
  void set();

  /**
   * Resets the current list to the origina/ list.
   */
  void reset();


  /**
   * Returns a duplicate of the original list.
   * @return the original list
   */
  List<IShape> getOriginal();
}


