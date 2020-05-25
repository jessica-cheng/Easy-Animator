package cs3500.animator.animation;

import cs3500.animator.shape.Color;
import cs3500.animator.shape.IShape;

/**
 * Represents a changing color animation.
 */
public class ChangeColor extends Animation {
  private Color fromCol;
  private Color toCol;

  /**
   * Constructs a changing color animation.
   *
   * @param shape     shape to change color
   * @param startTick start time of the change of color
   * @param endTick   end time of the change of color
   * @param fromCol   initial color of the shape
   * @param toCol     new color of the shape
   */
  public ChangeColor(IShape shape, int startTick, int endTick, Color fromCol, Color toCol) {
    super(shape, startTick, endTick);
    if (fromCol == null || toCol == null) {
      throw new IllegalArgumentException("Change color can't be null.");
    }
    this.animationType = AnimationType.CHANGECOLOR;
    this.fromCol = fromCol;
    this.toCol = toCol;
  }

  @Override
  public IAnimation copyAnimation() {
    return new ChangeColor(null,this.startTick,this.endTick, new Color(fromCol),
            new Color(toCol));
  }


  /**
   * Gets the color the shape is changing from.
   *
   * @return the color the shape is changing from
   */
  public Color getFromCol() {
    return new Color(fromCol.getRed(), fromCol.getGreen(), fromCol.getBlue());
  }

  /**
   * Gets the color the shape is changing to.
   *
   * @return the color the shape is changing to
   */
  public Color getToCol() {
    return new Color(toCol.getRed(), toCol.getGreen(), toCol.getBlue());
  }

  /**
   * Gives the description of the color change animation.
   *
   * @return the description of the color change animation
   */
  @Override
  public String animString(int fps) {
    return "Shape " + shape.getName() + " changes color from "
            + "(" + getFromCol().getRed() + "," + getFromCol().getGreen() + ","
            + getFromCol().getBlue() + ")"
            + " to " + "(" + getToCol().getRed() + "," + getToCol().getGreen() + ","
            + getToCol().getBlue() + ")"
            + " from t=" + Double.toString((double) this.startTick / (double) fps) + "s " + "to t="
            + Double.toString((double) this.endTick / (double) fps) + "s";

  }

  @Override
  public String generateSVG(int fps, boolean loop) {
    String begin = "";
    String duration = Double.toString(((double) endTick - (double) startTick) / fps * 1000);
    if(loop) {
      begin += "base.begin+";
    }
    begin += Double.toString((double) startTick / (double) fps * 1000);
    return "<animate attributeName=\"fill\" attributeType=\"XML\""
            + " begin=\"" + begin + "ms\""
            + " dur=\"" + duration + "ms\""
            + " from=\"rgb("
            + Double.toString(fromCol.getRed() * 255) + ","
            + Double.toString(fromCol.getGreen() * 255) + ","
            + Double.toString(fromCol.getBlue() * 255) + ")\" to=\"rgb("
            + Double.toString(toCol.getRed() * 255) + ","
            + Double.toString(toCol.getGreen() * 255) + ","
            + Double.toString(toCol.getBlue() * 255) + ")\" "
            + "fill=\"freeze\"/>";
  }


  @Override
  public void applyAnimation(int tick) {
    if (validTick(tick)) {
      double newRed = tweening(fromCol.getRed(), toCol.getRed(), tick);
      double newGreen = tweening(fromCol.getGreen(), toCol.getGreen(), tick);
      double newBlue = tweening(fromCol.getBlue(), toCol.getBlue(), tick);
      shape.setColor(newRed, newGreen, newBlue);
    }
  }
}
