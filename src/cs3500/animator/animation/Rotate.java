package cs3500.animator.animation;

import cs3500.animator.shape.IShape;

public class Rotate extends Animation {
  private double fromDegree;
  private double toDegree;

  public Rotate(IShape shape, double fromDegree, double toDegree, int start, int end) {
    super(shape, start, end);
    this.fromDegree = fromDegree;
    this.toDegree = toDegree;
    this.animationType = AnimationType.ROTATE;

  }

  @Override
  public void applyAnimation(int tick) {
    if (validTick(tick)) {
      shape.setRotation(tweening(fromDegree, toDegree, tick));
    }
  }

  @Override
  public String animString(int fps) {
    return "Shape " + shape.getName() + " rotates from degree: "
            + Double.toString(fromDegree) + " to degree: " + Double.toString(toDegree)
            + " from t=" + Double.toString((double) this.startTick / (double) fps)
            + "s " + "to t="
            + Double.toString((double) this.endTick / (double) fps) + "s";
  }

  @Override
  public String generateSVG(int fps, boolean loop) {
    return "";
  }

  @Override
  public IAnimation copyAnimation() {
    return new Rotate(null, fromDegree, toDegree, this.startTick, this.endTick);
  }
}
