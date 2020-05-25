package cs3500.animator.animation;

import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IShape;

/**
 * Abstract base class for implementations of {@link IAnimation}.
 */
public abstract class Animation implements IAnimation, Comparable<IAnimation> {
  protected int startTick;
  protected int endTick;
  protected IShape shape;
  protected AnimationType animationType;

  /**
   * Constructs an animation.
   *
   * @param shape     shapeDir the animation acts on
   * @param startTick start time of the animation on the shapeDir
   * @param endTick   end time of the animation on the shapeDir
   */
  protected Animation(IShape shape, int startTick, int endTick) {
    if (shape != null) {
      if (startTick < shape.getStart() || endTick > shape.getEnd() || endTick < startTick) {
        throw new IllegalArgumentException("Invalid animation times");
      }
    }

    this.shape = shape;
    this.startTick = startTick;
    this.endTick = endTick;
  }


  /**
   * Checks if the given tick is a valid tick.
   *
   * @param tick the tick to check.
   * @return a boolean of if the current animation is happening.
   */
  protected boolean validTick(int tick) {
    return !(tick < startTick || tick > endTick);
  }

  @Override
  public AnimationType getAnimationType() {
    return this.animationType;
  }

  @Override
  public boolean isConflicting(IAnimation other) {
    if (other.getAnimationType().equals(this.animationType)) {
      if (other.getStart() >= this.startTick && other.getStart() <= this.endTick) {
        return true;
      }
    }
    return false;
  }

  @Override
  public abstract void applyAnimation(int tick);

  /**
   * Compares the start time of this animation and another animation.
   *
   * @param other the other animation to be compared to
   * @return int based comparison, between 1 and -1;
   */
  @Override
  public int compareTo(IAnimation other) {
    if (this.getStart() > other.getStart()) {
      return 1;
    } else if (this.startTick == other.getStart()) {
      return 0;
    } else {
      return -1;
    }
  }

  @Override
  public abstract String animString(int fps);

  @Override
  public String getShapeName() {
    return this.shape.getName();
  }

  @Override
  public int getStart() {
    return startTick;
  }


  /**
   * Calculates how the animation of the shape is when it starts to how it is after it ends.
   *
   * @param from original value
   * @param to   change to value
   * @param t    at time t
   * @return the calculated value at time t
   */
  public double tweening(double from, double to, int t) {
    double newTerm1 = from * (endTick - t) / (endTick - startTick);
    double newTerm2 = to * (t - startTick) / (endTick - startTick);
    return newTerm1 + newTerm2;

  }

  public void setShape(IShape a) {
    if (startTick < a.getStart() || endTick > a.getEnd() || endTick < startTick) {
      throw new IllegalArgumentException("Invalid animation times");
    }
    this.shape = a;
  }

}
