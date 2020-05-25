package cs3500.animator.shape;
/**
 * Represents an oval shape.
 */
public class Oval extends AShape {

  /**
   * Constructs an oval shape.
   *
   * @param name          name of the shape
   * @param posn          position of the shape
   * @param xRadius       X axis radius of the oval
   * @param yRadius       Y axis radius of the oval
   * @param col           color of the shape
   * @param appearTime    appearance time of the shape
   * @param disappearTime disappearance time of the shape
   */
  public Oval(String name, Position posn, double xRadius, double yRadius, Color col,
              int appearTime, int disappearTime, int layer) {
    super(name, posn, xRadius, yRadius, col, appearTime, disappearTime, layer);
    this.type = ShapeType.oval;
  }

  private Oval(IShape s){
    super(s);
  }

  @Override
  public IShape copyShape(IShape s) {
    return new Oval(s);
  }
}
