package cs3500.animator.shape;

/**
 * Represents a color.
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * Constructs a color.
   *
   * @param red   the value of the red color
   * @param green the value of the green color
   * @param blue  the value of the blue color
   */
  public Color(double red, double green, double blue) {
    if (red < 0 || red > 255.0 || green < 0 || green > 255.0 || blue < 0 || blue > 255.0) {
      throw new IllegalArgumentException("Invalid color arguments.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  public Color(Color that){
   this.blue = that.blue;
   this.green = that.green;
   this.red = that.red;
  }

  /**
   * Gets the red component.
   *
   * @return the red color
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Gets the blue component.
   *
   * @return the blue color
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Gets the red component.
   *
   * @return the red color.
   */
  public double getBlue() {
    return this.blue;
  }
}
