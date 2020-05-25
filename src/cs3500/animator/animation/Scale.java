package cs3500.animator.animation;

import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ShapeType;

/**
 * Represents a scale change animation.
 */
public class Scale extends Animation {
  private double fromWidth;
  private double fromHeight;
  private double toWidth;
  private double toHeight;

  /**
   * Constructs a scale animation.
   *
   * @param shape      shape to be scaled
   * @param startTick  start time of the scale animation
   * @param endTick    end time of the scale animation
   * @param fromWidth  the original width of the shape
   * @param fromHeight the original height of the shape
   * @param toWidth    the width of the shape to be changed to
   * @param toHeight   the height of the shape to be changed to
   */
  public Scale(IShape shape, int startTick, int endTick,
               double fromWidth, double fromHeight, double toWidth, double toHeight) {
    super(shape, startTick, endTick);
    if (fromWidth <= 0 || fromHeight <= 0 || toWidth <= 0 || toHeight <= 0) {
      throw new IllegalArgumentException("Can't have negative dimensions");
    }
    this.animationType = AnimationType.SCALE;
    this.fromWidth = fromWidth;
    this.fromHeight = fromHeight;
    this.toWidth = toWidth;
    this.toHeight = toHeight;
  }

  @Override
  public IAnimation copyAnimation() {
    return new Scale(null, this.startTick, this.endTick, this.fromWidth, this.fromHeight,
            this.toWidth, this.toHeight);
  }

  /**
   * Gets original width of the shape.
   *
   * @return original width
   */
  public double getFromWidth() {
    return this.fromWidth;
  }

  /**
   * Gets the original height of the shape.
   *
   * @return original height
   */
  public double getFromHeight() {
    return this.fromHeight;
  }

  /**
   * Gets width to be changed to of the shape.
   *
   * @return new width
   */
  public double getToWidth() {
    return this.toWidth;
  }

  /**
   * Gets height to be changed to of the shape.
   *
   * @return new height
   */
  public double getToHeight() {
    return this.toHeight;
  }

  @Override
  public String animString(int fps) {
    return "Shape " + shape.getName() + " scales from Width: "
            + this.getFromWidth() + ", Height: " + this.getFromHeight()
            + " to Width: " + Double.toString(this.getToWidth())
            + ", Height: " + Double.toString(this.getToHeight())
            + " from t=" + Double.toString((double) this.startTick / (double) fps) + "s " + "to t="
            + Double.toString((double) this.endTick / (double) fps) + "s";
  }

  @Override
  public String generateSVG(int fps, boolean loop) {
    String begin = "";
    String duration = Double.toString(((double) endTick - (double) startTick) / fps * 1000);
    if (loop) {
      begin += "base.begin+";
    }
    begin += Double.toString((double) startTick / (double) fps * 1000);
    String xDim = "width";
    String yDim = "height";
    if (shape.getType() == ShapeType.oval) {
      xDim = "rx";
      yDim = "ry";
    }
    return "<animate attribute=\"xml\" begin=\"" + begin
            + "ms\" dur=\"" + duration + "ms\" attributeName=\""
            + xDim + "\" from=\"" + Double.toString(fromWidth) + "\" to=\""
            + Double.toString(toWidth) + "\" fill=\"freeze\" />"

            + "<animate attribute=\"xml\" begin=\"" + begin
            + "\" dur=\"" + duration + "ms\" attributeName=\""
            + yDim + "\" from=\"" + Double.toString(fromHeight) + "\" to=\""
            + Double.toString(toHeight) + "\" fill=\"freeze\" />";

  }

  @Override
  public void applyAnimation(int tick) {
    if (validTick(tick)) {
      double newWidth = tweening(getFromWidth(), getToWidth(), tick);
      double newHeight = tweening(getFromHeight(), getToHeight(), tick);
      shape.setWidth(newWidth);
      shape.setHeight(newHeight);
    }
  }
}
