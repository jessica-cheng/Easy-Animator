package cs3500.animator.shape;

/**
 * Represents a rectangle shape.
 */
public class Rectangle extends AShape {

  /**
   * Constructs a rectangle shape.
   *
   * @param name          name of the shape
   * @param posn          position of the shape
   * @param width         width of the shape
   * @param height        height of the shape
   * @param col           color of the shape
   * @param appearTime    appearance time of the shape
   * @param disappearTime disappearance time of the shape
   */
  public Rectangle(String name, Position posn, double width, double height, Color col,
                   int appearTime, int disappearTime, int layer) {
    super(name, posn, width, height, col, appearTime, disappearTime,layer);
    this.type = ShapeType.rectangle;
  }


  private Rectangle(IShape s){
    super(s);
  }

  @Override
  public IShape copyShape(IShape s) {
    return new Rectangle(s);
  }
}
