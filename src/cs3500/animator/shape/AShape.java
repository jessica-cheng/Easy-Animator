package cs3500.animator.shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cs3500.animator.animation.IAnimation;
import javafx.scene.transform.Rotate;

/**
 * Abstract base class for implementations of {@link IShape}.
 */
public abstract class AShape implements IShape {
  protected Color col;
  protected double width;
  protected double height;
  protected String name;
  protected ShapeType type;
  protected Position posn;



  protected int layer;
  protected Double rotation = 0.0;
  protected List<IAnimation> animations;
  protected double appearTime;
  protected double disappearTime;


  /**
   * Constructs a shape.
   *
   * @param name          name of the shapeDir
   * @param posn          position of the shapeDir
   * @param width         width of the shapeDir
   * @param height        height of the shapeDir
   * @param col           color of the shapeDir
   * @param appearTime    appearance time of the shapeDir
   * @param disappearTime disappearance time of the shapeDir
   */
  protected AShape(String name, Position posn, double width, double height, Color col,
                   double appearTime, double disappearTime, int layer) {
    if (disappearTime < appearTime || appearTime < 0 || disappearTime < 0) {
      throw new IllegalArgumentException("Not valid appear or disappear times");
    }
    if (posn == null || col == null) {
      throw new IllegalArgumentException("Can't be null");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Can't have a negative or zero dimension.");
    }

    this.name = name;
    this.posn = posn;
    this.width = width;
    this.height = height;
    this.col = col;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    this.animations = new ArrayList<>();
    this.layer = layer;
  }

  /**
   * Copy constructor.
   */
  public AShape(IShape a) {
    this(a.getName(), a.getPosition(), a.width(), a.height(),
            a.getColor(), a.getStart(), a.getEnd(), a.getLayer());
    this.animations = animCopy(a.getAnimations());
    this.type = a.getType();
  }


  private ArrayList<IAnimation> animCopy(List<IAnimation> l){
    ArrayList<IAnimation> temp = new ArrayList<>();
    for (IAnimation a : l) {
      IAnimation tempAnim = a.copyAnimation();
      tempAnim.setShape(this);
      temp.add(tempAnim);
    }
    return temp;
  }

  @Override
  public void shapeAtTime(int frame) {
    for (IAnimation a : animations) {
      a.applyAnimation(frame);
    }
  }

  public Double getRotation() {
    return rotation;
  }

  public void setRotation(Double rotation) {
    this.rotation = rotation;
  }

  /**
   * Checks if this shape is seen.
   *
   * @param frame frame to check visibility
   */
  @Override
  public boolean isSeen(int frame) {
    return frame >= appearTime && frame < disappearTime;
  }

  @Override
  public Color getColor() {
    return new Color(this.col);
  }

  @Override
  public void setColor(double red, double green, double blue) {
    this.col = new Color(red, green, blue);
  }

  @Override
  public String strColor() {
    return "(" + col.getRed() + "," + col.getGreen() + "," + col.getBlue() + ")";
  }

  @Override
  public Position getPosition() {
    return this.posn;
  }

  @Override
  public void setPosition(double x, double y) {
    this.posn = new Position(x, y);
  }

  @Override
  public double width() {
    return this.width;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public double height() {
    return this.height;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public void addAnimation(IAnimation anim) {
    if (animations.size() == 0) {
      animations.add(anim);
    } else {
      for (int i = 0; i < animations.size(); i++) {
        if (anim.isConflicting(animations.get(i))) {
          throw new IllegalArgumentException("conflicting animation");
        }
      }
      animations.add(anim);
    }
    Collections.sort(animations);
  }

  /**
   * Gives information about the shape properties.
   *
   * @return String of the shape properties
   */
  public String toString() {
    String shapeInfo = "";
    shapeInfo += "Name: " + this.name + "\n";
    shapeInfo += "Type: " + this.type + "\n";

    if (type == ShapeType.rectangle) {
      shapeInfo += "Corner: " + posn.toString()
              + ", Width: " + width + ", Height: " + height
              + ", Color: " + this.strColor() + "\n";
    }
    if (type == ShapeType.oval) {
      shapeInfo += "Center: " + posn.toString()
              + ", X radius: " + width + ", Y radius: " + height
              + ", Color: " + this.strColor() + "\n";
    }
    shapeInfo += "Appears at t=" + appearTime + "s" + "\n";
    shapeInfo += "Disappears at t=" + disappearTime + "s" + "\n\n";

    return shapeInfo;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public double getStart() {
    return this.appearTime;
  }

  @Override
  public double getEnd() {
    return this.disappearTime;
  }

  @Override
  public List<IAnimation> getAnimations() {
    return animations;
  }

  /**
   * @param fps current tick speed
   */
  @Override
  public String generateSVG(int fps, boolean loop) {
    String color = " fill=\"rgb("
            + Double.toString(col.getRed() * 255) + ","
            + Double.toString(col.getGreen() * 255) + ","
            + Double.toString(col.getBlue() * 255) + ")\"";
    String type;
    String xDim;
    String yDim;
    String width;
    String height;
    String start = "";
    String end = "";
    if (loop){
      start += "base.begin+";
      end += "base.begin+";
    }
    start += Double.toString((this.appearTime / (double)fps ) * 1000);
    end += Double.toString((this.disappearTime / (double)fps)* 1000);

    if (this.type == ShapeType.rectangle) {
      type = "rect";
      xDim = " x=\"" + Double.toString(this.posn.getX()) + "\"";
      yDim = " y=\"" + Double.toString(this.posn.getY()) + "\"";
      width = " width=\"" + Double.toString(this.width) + "\"";
      height = " height=\"" + Double.toString(this.height) + "\"";
    } else {
      type = "ellipse";
      xDim = " cx=\"" + Double.toString(this.posn.getX()) + "\"";
      yDim = " cy=\"" + Double.toString(this.posn.getY()) + "\"";
      width = " rx=\"" + Double.toString(this.width) + "\"";
      height = " ry=\"" + Double.toString(this.height) + "\"";
    }
    String output = "";
    output += "<" + type + " id=\"" + this.name + "\"" + xDim + yDim + width + height + color
            + " visability=\"hidden\" " +
            "opacity=\"0\" >";

    String startSvg = "";
    startSvg += "<set attribute=\"xml\" "
            + "attributeName=\"opacity\" "
            + "to=\"1\" "
            + "begin=\"" + start + "ms\" />";
    String endSvg = "";
    endSvg += "<set attribute=\"xml\" "
            + "attributeName=\"opacity\" "
            + "to=\"0\" "
            + "begin=\"" + end + "ms\" />";


    output += startSvg;
    output += endSvg;
    return output;
  }


  /**
   * Generates the looping part of the svg file.
   * @return the formatted string.
   */
  public String generateLoop() {
    String output = "";
    String xDim = "x";
    String yDim = "y";
    String xWid = "width";
    String yHei = "height";
    if (getType() == ShapeType.oval) {
      xDim = "cx";
      yDim = "cy";
    }
    if (getType() == ShapeType.oval) {
      xWid = "rx";
      yHei = "ry";
    }
    output += "<animate attributeName=\"fill\" attributeType=\"XML\""
            + " begin=\"base.end\""
            + " dur=\"1ms\" "
            + "to=\"rgb("
            + Double.toString(col.getRed() * 255) + ","
            + Double.toString(col.getGreen() * 255) + ","
            + Double.toString(col.getBlue() * 255) + ")\" "
            + "fill=\"freeze\"/>\n";
    output += "<animate attribute=\"xml\" begin=\""
            + "base.end\" dur=\"1ms\" attributeName=\""
            + xDim + "\" to=\""
            + Double.toString(posn.getX()) + "\" fill=\"freeze\" />\n"
            + "<animate attribute=\"xml\" begin=\""
            + "base.end\" dur=\"1ms\" attributeName=\""
            + yDim + "\" to=\""
            + Double.toString(posn.getY()) + "\" fill=\"freeze\" />";
    output += "<animate attribute=\"xml\" begin=\""
            + "base.end\" dur=\"1ms\" attributeName=\""
            + xWid + "\" to=\""
            + Double.toString(width) + "\" fill=\"freeze\" />\n"
            + "<animate attribute=\"xml\" begin=\""
            + "base.end\" dur=\"1ms\" attributeName=\""
            + yHei + "\" to=\""
            + Double.toString(height) + "\" fill=\"freeze\" />";
    output += "<set attribute=\"xml\" "
            + "attributeName=\"opacity\" "
            + "to=\"1\" "
            + "begin=\"base.end\" />";
    return output;
  }

  @Override
  public int getLayer() {
    return layer;
  }

}
