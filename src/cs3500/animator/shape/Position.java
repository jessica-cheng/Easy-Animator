package cs3500.animator.shape;

/**
 * Represents a position.
 */
public final class Position {
  private final double x;

  /**
   * Gets the x variable.
   *
   * @return x
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y variable.
   *
   * @return y
   */
  public double getY() {
    return y;
  }

  private final double y;

  /**
   * Constructs a position.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   */
  public Position(Position v) {
    this(v.x, v.y);
  }

  /**
   * Gets a string of this position.
   *
   * @return the string of the position
   */
  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }
}
