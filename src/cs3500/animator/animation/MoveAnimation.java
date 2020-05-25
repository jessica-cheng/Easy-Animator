package cs3500.animator.animation;

import java.security.PublicKey;

import cs3500.animator.shape.AShape;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position;

import cs3500.animator.shape.ShapeType;

/**
 * Represents a move animation.
 */
public class MoveAnimation extends Animation {
  private Position from;
  private Position to;

  /**
   * Constructs a move animation.
   *
   * @param shape     shape to be moved
   * @param startTick start time of the move animation
   * @param endTick   end time of the move animation
   * @param from      move from position
   * @param to        move to position
   */
  public MoveAnimation(IShape shape, int startTick, int endTick, Position from,
                       Position to) {
    super(shape, startTick, endTick);
    if (from == null || to == null) {
      throw new IllegalArgumentException("Move positions can't be null.");
    }
    this.animationType = AnimationType.MOVE;
    this.from = from;
    this.to = to;
  }


  @Override
  public IAnimation copyAnimation() {
    return new MoveAnimation(null, this.startTick, this.endTick, new Position(from),
            new Position(to));
  }

  /**
   * Gives the description of the move animation.
   *
   * @return the description of the move animation
   */
  @Override
  public String animString(int fps) {
    String to = "(" + Double.toString(this.getTo().getX())
            + "," + Double.toString(this.getTo().getY()) + ")";

    String from = "(" + Double.toString(this.getFrom().getX())
            + "," + Double.toString(this.getFrom().getY()) + ")";

    return "Shape " + shape.getName() + " moves from "
            + from + " to " + to +
            " from t=" + Double.toString((double) this.startTick / (double) fps) + "s " + "to t="
            + Double.toString((double) this.endTick / (double) fps) + "s";
  }

  @Override
  public void applyAnimation(int tick) {
    if (validTick(tick)) {
      double newX = tweening(from.getX(), to.getX(), tick);
      double newY = tweening(from.getY(), to.getY(), tick);
      shape.setPosition(newX, newY);
    }
  }

  @Override
  public String generateSVG(int fps, boolean loop) {
    String begin = "";
    String duration = Double.toString(((double) endTick - (double) startTick) / fps * 1000);
    if (loop) {
      begin += "base.begin+";
    }
    begin += Double.toString((double) startTick / (double) fps * 1000);
    String xDim = "x";
    String yDim = "y";
    if (shape.getType() == ShapeType.oval) {
      xDim = "cx";
      yDim = "cy";
    }
    return "<animate attribute=\"xml\" begin=\"" + begin
            + "ms\" dur=\"" + duration + "ms\" attributeName=\""
            + xDim + "\" from=\"" + Double.toString(from.getX()) + "\" to=\""
            + Double.toString(to.getX()) + "\" fill=\"freeze\" />"

            + "<animate attribute=\"xml\" begin=\"" + begin
            + "ms\" dur=\"" + duration + "ms\" attributeName=\""
            + yDim + "\" from=\"" + Double.toString(from.getY()) + "\" to=\""
            + Double.toString(to.getY()) + "\" fill=\"freeze\" />";

  }

  /**
   * Gets the position the shape is moving from.
   *
   * @return the move from position
   */
  public Position getFrom() {
    return new Position(from.getX(), from.getY());
  }

  /**
   * Gets the position the shape is moving to.
   *
   * @return the move to position
   */
  public Position getTo() {
    return new Position(to.getX(), to.getY());
  }
}
