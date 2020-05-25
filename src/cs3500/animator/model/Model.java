package cs3500.animator.model;

import cs3500.animator.animation.ChangeColor;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.MoveAnimation;
import cs3500.animator.animation.Rotate;
import cs3500.animator.animation.Scale;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.util.TweenModelBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represents a model of the animator.
 */
public class Model implements IModel {
  private List<IShape> originalShapes;
  private List<IShape> listOfShapes;
  private double maxFrame;

  /**
   * Constructs a model.
   */
  public Model() {
    this.listOfShapes = new ArrayList<IShape>();
  }

  @Override
  public void addShapes(IShape shape) {
    if (listOfShapes.size() == 0) {
      this.listOfShapes.add(shape);
    } else if (listOfShapes.size() > 0) {
      for (int i = 0; i < listOfShapes.size(); i++) {
        if (listOfShapes.get(i).getName() == shape.getName()
                && listOfShapes.get(i).getType() == shape.getType()) {
          throw new IllegalArgumentException("Can't add same shapeDir type with same name.");
        }
      }
      this.listOfShapes.add(shape);
      if (shape.getEnd() > maxFrame) {
        maxFrame = shape.getEnd();
      }
    }
    set();
  }

  @Override
  public double getMaxFrame() {
    return this.maxFrame;
  }

  public void set(){
    this.originalShapes = getListOfShapes();
  }

  public void reset(){
    this.listOfShapes = this.originalShapes;
    this.listOfShapes = getListOfShapes();
  }

  @Override
  public ArrayList<ReadableShape> getShapesAtFrame(int frame) {
    ArrayList<ReadableShape> temp = new ArrayList<>();
    for (IShape s : this.listOfShapes) {
      if (s.isSeen(frame)) {
        temp.add(ReadableShape.generateTextReadableShape(tween(s, frame), null, 0));
      }
    }
    Collections.sort(temp);
    return temp;
  }

  @Override
  public void addAnimation(String name, IAnimation a) {
    for (IShape s : this.listOfShapes) {
      if (s.getName() == name) {
        s.addAnimation(a);
        return;
      }
    }
    throw new IllegalArgumentException("No shape with this name");
  }

  /**
   * Tweens a shape at a certain time.
   *
   * @param shape The shape to be tweened.
   * @param frame The frame it is going to be tweened to.
   */
  public IShape tween(IShape shape, int frame) {
    shape.shapeAtTime(frame);
    return shape;
  }


  @Override
  public String getShapeDescription() {
    String shapeDesc = "Shapes:\n";
    for (IShape shape : this.getListOfShapes()) {
      shapeDesc += shape.toString();
    }
    return shapeDesc;
  }

  @Override
  public ArrayList<ReadableShape> getAnimTextAtFrame(int frame, int fps) {
    ArrayList<ReadableShape> temp = new ArrayList<>();
    for (IShape s : this.listOfShapes) {
      for (IAnimation a : s.getAnimations()) {
        if (a.getStart() == frame) {
          temp.add(ReadableShape.generateTextReadableShape(s, a, fps));
        }
      }
    }
    return temp;
  }

  @Override
  public List<IShape> getListOfShapes() {
    ArrayList<IShape> temp = new ArrayList<>();
    for(IShape s : listOfShapes){
      temp.add(s.copyShape(s));
    }
    return temp;
  }

  public List<IShape> getOriginal() {
    ArrayList<IShape> temp = new ArrayList<>();
    for(IShape s : originalShapes){
      temp.add(s.copyShape(s));
    }
    return temp;
  }


  public static class Builder implements TweenModelBuilder<IModel> {

    List<IAnimation> animationForBuilder = new ArrayList<>();
    List<IShape> shapesForBuilder = new ArrayList<>();

    /**
     * Adds an oval to the list of shapes to be made.
     *
     * @param name        the unique name given to this shapeDir
     * @param cx          the x-coordinate of the center of the oval
     * @param cy          the y-coordinate of the center of the oval
     * @param xRadius     the x-radius of the oval
     * @param yRadius     the y-radius of the oval
     * @param red         the red component of the color of the oval
     * @param green       the green component of the color of the oval
     * @param blue        the blue component of the color of the oval
     * @param startOfLife the time tick at which this oval appears
     * @param endOfLife   the time tick at which this oval disappears
     */
    @Override
    public TweenModelBuilder<IModel> addOval(String name, float cx, float cy,
                                             float xRadius, float yRadius,
                                             float red, float green, float blue,
                                             int startOfLife, int endOfLife, int layer) {
      Oval temp = new Oval(name, new Position((double) cx, (double) cy),
              (double) xRadius, (double) yRadius,
              new Color((double) red, (double) green, (double) blue),
              startOfLife, endOfLife, layer);
      shapesForBuilder.add(temp);
      return this;
    }

    /**
     * Adds a rectangle to the list of shapes to be made.
     *
     * @param name        the unique name given to this shapeDir
     * @param lx          the minimum x-coordinate of a corner of the
     *                    rectangle
     * @param ly          the minimum y-coordinate of a corner of the
     *                    rectangle
     * @param width       the width of the rectangle
     * @param height      the height of the rectangle
     * @param red         the red component of the color of the rectangle
     * @param green       the green component of the color of the rectangle
     * @param blue        the blue component of the color of the rectangle
     * @param startOfLife the time tick at which this rectangle appears
     * @param endOfLife   the time tick at which this rectangle disappears
     */
    @Override
    public TweenModelBuilder<IModel> addRectangle(String name, float lx, float ly,
                                                  float width, float height,
                                                  float red, float green, float blue,
                                                  int startOfLife, int endOfLife, int layer) {
      Rectangle temp = new Rectangle(name, new Position((double) lx, (double) ly),
              (double) width, (double) height,
              new Color((double) red, (double) green, (double) blue),
              startOfLife, endOfLife, layer);
      shapesForBuilder.add(temp);
      return this;
    }

    /**
     * Adds a move animation to the list of animations to run.
     *
     * @param name      the unique name of the shapeDir to be moved
     * @param moveFromX the x-coordinate of the initial position of this shapeDir.
     *                  What this x-coordinate represents depends on the shapeDir.
     * @param moveFromY the y-coordinate of the initial position of this shapeDir.
     *                  what this y-coordinate represents depends on the shapeDir.
     * @param moveToX   the x-coordinate of the final position of this shapeDir. What
     *                  this x-coordinate represents depends on the shapeDir.
     * @param moveToY   the y-coordinate of the final position of this shapeDir. what
     *                  this y-coordinate represents depends on the shapeDir.
     * @param startTime the time tick at which this movement should start
     * @param endTime   the time tick at which this movement should end
     */
    @Override
    public TweenModelBuilder<IModel> addMove(String name,
                                             float moveFromX, float moveFromY,
                                             float moveToX, float moveToY,
                                             int startTime, int endTime) {

      IShape tempShape = null;
      for (IShape s : shapesForBuilder) {
        if (s.getName().equals(name)) {
          tempShape = s;
        }
      }
      IAnimation tempAnim = new MoveAnimation(tempShape, startTime, endTime,
              new Position((double) moveFromX, (double) moveFromY),
              new Position((double) moveToX, (double) moveToY));
      animationForBuilder.add(tempAnim);
      return this;
    }

    /**
     * Adds a color change animation to the list of animations to be made.
     *
     * @param name      the unique name of the shapeDir whose color is to be changed
     * @param oldR      the r-component of the old color
     * @param oldG      the g-component of the old color
     * @param oldB      the b-component of the old color
     * @param newR      the r-component of the new color
     * @param newG      the g-component of the new color
     * @param newB      the b-component of the new color
     * @param startTime the time tick at which this color change should start
     * @param endTime   the time tick at which this color change should end
     */
    @Override
    public TweenModelBuilder<IModel> addColorChange(String name,
                                                    float oldR, float oldG, float oldB,
                                                    float newR, float newG, float newB,
                                                    int startTime, int endTime) {
      IShape tempShape = null;
      for (IShape s : shapesForBuilder) {
        if (s.getName().equals(name)) {
          tempShape = s;
        }
      }
      IAnimation tempAnim = new ChangeColor(tempShape, startTime, endTime,
              new Color((double) oldR, (double) oldG, (double) oldB),
              new Color((double) newR, (double) newG, (double) newB));
      animationForBuilder.add(tempAnim);
      return this;
    }

    /**
     * Adds a scale change to the list of animations to be made.
     */
    @Override
    public TweenModelBuilder<IModel> addScaleToChange(String name,
                                                      float fromSx, float fromSy,
                                                      float toSx, float toSy,
                                                      int startTime, int endTime) {
      IShape tempShape = null;
      for (IShape s : shapesForBuilder) {
        if (s.getName().equals(name)) {
          tempShape = s;
        }
      }
      IAnimation tempAnim = new Scale(tempShape, startTime, endTime,
              (double) fromSx, (double) fromSy,
              (double) toSx, (double) toSy);
      animationForBuilder.add(tempAnim);
      return this;
    }


    /**
     * Adds a rotation change to the list of animations to be made.
     */
    public TweenModelBuilder<IModel> addRotate(String name, float fromDegree,float toDegree,
                                               int startTime, int endTime) {
      IShape tempShape = null;
      for (IShape s : shapesForBuilder) {
        if (s.getName().equals(name)) {
          tempShape = s;
        }
      }
      IAnimation tempAnim = new Rotate(tempShape,
              (double)fromDegree,(double)toDegree,startTime,endTime);
      animationForBuilder.add(tempAnim);
      return this;
    }

    /**
     * Builds the model from the list of animations.
     *
     * @return the built model.
     */
    @Override
    public IModel build() {
      IModel model = new Model();
      for (IShape s : shapesForBuilder) {
        model.addShapes(s);
      }
      for (IAnimation a : animationForBuilder) {
        model.addAnimation(a.getShapeName(), a);
      }
      return model;
    }
  }
}
