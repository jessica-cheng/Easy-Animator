package cs3500.animator.shape;


import cs3500.animator.animation.IAnimation;

/**
 * Represents a readable shape.
 */
public class ReadableShape implements Comparable{
  private double start;
  private double end;
  private double width;
  private double height;
  private double x;
  private double y;
  private Color col;
  private double rotation;
  private ShapeType type;
  private String currAnim = "";
  private String animSVG = "";
  private String shapeSVG = "";
  public int layer;

  /**
   * Constructs a Readable shape.
   *
   * @param width    width of the readable shape
   * @param height   height of the readable shape
   * @param x        x-coordinate of the readable shape
   * @param y        y-coordinate of the readable shape
   * @param col      color of the readable shape
   * @param type     type of the readable shape
   * @param start    start time of the readable shape
   * @param end      end time of the readable shape
   * @param anim     animation of the readable shape
   * @param fps      speed of the readable shape
   * @param shapeSVG SVG of the readable shape
   */
  private ReadableShape(double width, double height, double x, double y,
                        Color col, ShapeType type,
                        double start, double end,
                        IAnimation anim, int fps, String shapeSVG, double rotation, int layer) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.start = start;
    this.end = end;
    this.col = col;
    this.type = type;
    this.start = start;
    this.end = end;
    this.rotation = rotation;
    this.layer = layer;
    if (anim != null) {
      this.currAnim = anim.animString(fps);
    }
    this.shapeSVG = shapeSVG;
    if (anim != null) {
      this.animSVG += anim.generateSVG(fps, true);
    }
  }

  /**
   * Gets the width of the readable shape.
   *
   * @return width of readable shape
   */
  public double getWidth() {
    return width;
  }

  /**
   * Gets the height of the readable shape.
   *
   * @return height of the readable shape
   */
  public double getHeight() {
    return height;
  }

  /**
   * Gets the x-coordinate of the readable shape.
   *
   * @return x-coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y-coordinate of the readable shape.
   *
   * @return y-coordinate
   */
  public double getY() {
    return y;
  }

  /**
   * Gets the color of the readable shape.
   *
   * @return color of the readable shape
   */
  public Color getCol() {
    return col;
  }

  /**
   * Gets the type of the shape.
   *
   * @return type of the shape
   */
  public ShapeType getType() {
    return type;
  }

  /**
   * Gets the start time of the readable shape.
   *
   * @return start time
   */
  public double getStart() {
    return start;
  }

  /**
   * Gets the end time of the readable shape.
   *
   * @return end time
   */
  public double getEnd() {
    return end;
  }

  /**
   * Gets the current animation text.
   *
   * @return text description of current animation
   */
  public String getCurrAnim() {
    return currAnim;
  }

  /**
   * Gets the animation svg of the readable shape.
   *
   * @return text description of the animation svg
   */
  public String getAnimSVG() {
    return animSVG;
  }

  /**
   * Gets the shape svg of the readable shape.
   *
   * @return text description of the readable shape
   */
  public String getShapeSVG() {
    return shapeSVG;
  }

  /**
   * Generates a readable shape.
   *
   * @param s shape to be generated into text readable shape
   * @param a animation the text readable shape has
   */
  public static ReadableShape generateTextReadableShape(IShape s, IAnimation a, int fps) {
    return new ReadableShape(s.width(), s.height(), s.getPosition().getX(),
            (int) s.getPosition().getY(), s.getColor(), s.getType(),
            s.getStart(), s.getEnd(), a, fps, s.generateSVG(fps, true),
            s.getRotation(), s.getLayer());
  }

  public Double getRotation(){
    return this.rotation;
  }


  @Override
  public int compareTo(Object o) {
    if(o instanceof ReadableShape){
      ReadableShape temp = (ReadableShape)o;

      return this.layer - temp.layer;
    }
    throw new IllegalArgumentException();
  }
}