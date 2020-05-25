import org.junit.Test;

import cs3500.animator.animation.AnimationType;
import cs3500.animator.animation.ChangeColor;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.MoveAnimation;
import cs3500.animator.animation.Scale;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Position;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.IShape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AnimationTest {
  IAnimation move;
  IAnimation move2;
  IAnimation color;
  IAnimation scale;
  Position p1 = new Position(200.0, 200.0);
  Position p2 = new Position(300.0, 300.0);
  Position p3 = new Position(500.0, 100.0);
  Position p4 = new Position(500.0, 400.0);
  Color c1 = new Color(1.0, 0.0, 0.0);
  Color c2 = new Color(0.0, 0.0, 1.0);
  IShape r;
  IShape o;
  IShape oval = new Oval("C", p3, 60.0, 30.0, c2, 6,
          100, 0);
  IShape rect = new Rectangle("R", p1, 50.0, 100.0, c1, 1,
          100, 0);

  @Test(expected = IllegalArgumentException.class)
  public void testNullMove() {
    move = new MoveAnimation(r, 0, 20, p1, p2);
    move2 = new MoveAnimation(r, 21, 30, p3, p4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAnimationTimes() {
    move = new MoveAnimation(rect, 20, 0, p1, p2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAnimationTimes2() {
    move = new MoveAnimation(rect, 0, 20, p1, p2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAnimationTimes3() {
    move = new MoveAnimation(oval, 20, 200, p1, p2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMovePosition() {
    move = new MoveAnimation(rect, 7, 10, null, p2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColorChange() {
    color = new ChangeColor(rect, 7, 10, null, c1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullScaleChange() {
    scale = new Scale(rect, 7, 10, 2, 2, 0, -1);
  }

  @Test
  public void testIsNotConflicting() {
    move = new MoveAnimation(rect, 7, 10, p1, p2);
    move2 = new MoveAnimation(rect, 11, 15, p3, p4);
    assertEquals(false, move.isConflicting(move2));
    assertEquals(-1, move.compareTo(move2));
    assertEquals(1, move2.compareTo(move));
  }

  @Test
  public void testIsConflicting() {
    move = new MoveAnimation(rect, 7, 10, p1, p2);
    move2 = new MoveAnimation(rect, 9, 15, p3, p4);
    assertEquals(true, move.isConflicting(move2));
  }

  @Test
  public void moveAnimationString() {
    o = new Oval("O1", p1, 20.0, 20.0, c1, 0,
            20, 0);
    move = new MoveAnimation(o, 0, 20, p1, p2);
    assertEquals("Shape O1 moves from (200.0,200.0) to"
            + " (300.0,300.0) from t=0.0s to t=20.0s", move.animString(1));
  }

  @Test
  public void changeColorAnimationString() {
    color = new ChangeColor(rect, 10, 20, c1, c2);
    assertEquals("Shape R changes color from (1.0,0.0,0.0) to (0.0,0.0,1.0) " +
            "from t=10.0s to t=20.0s", color.animString(1));
  }

  @Test
  public void scaleAnimationString() {
    scale = new Scale(rect, 10, 20, 10, 20, 30, 40);
    assertEquals("Shape R scales from Width: 10.0, Height: 20.0 to " +
            "Width: 30.0, Height: 40.0 from t=10.0s to t=20.0s", scale.animString(1));
  }


  @Test
  public void animationsInShape() {
    IShape r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    move = new MoveAnimation(r, 0, 20, p1, p2);
    color = new ChangeColor(r, 11, 20, c1, c2);
    r.getAnimations().add(move);
    r.getAnimations().add(color);
    assertEquals("Shape R1 moves from (200.0,200.0) to (300.0,300.0) from t=0.0s to t=20.0s",
            r.getAnimations().get(0).animString(1));
    assertEquals("Shape R1 changes color from (1.0,0.0,0.0) to (0.0,0.0,1.0) " +
            "from t=11.0s to t=20.0s", r.getAnimations().get(1).animString(1));
  }

  @Test
  public void moveString() {
    oval = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    move = new MoveAnimation(oval, 1, 10, p1, p2);
    assertEquals("Shape O1 moves from (200.0,200.0) to (300.0,300.0) from t=1.0s to t=10.0s",
            move.animString(1));
  }

  @Test
  public void testGetAnimationType() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    color = new ChangeColor(rect, 1, 20, c1, c2);
    scale = new Scale(rect, 1, 20, 5, 5, 5, 5);
    assertEquals(AnimationType.MOVE, move.getAnimationType());
    assertEquals(AnimationType.CHANGECOLOR, color.getAnimationType());
    assertEquals(AnimationType.SCALE, scale.getAnimationType());
    assertNotEquals(AnimationType.SCALE, move.getAnimationType());
  }

  @Test
  public void testMoveFromTo() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    assertEquals(p1.getX(), ((MoveAnimation) move).getFrom().getX(), 0.01);
    assertEquals(p1.getY(), ((MoveAnimation) move).getFrom().getY(), 0.01);
    assertEquals(p2.getX(), ((MoveAnimation) move).getTo().getX(), 0.01);
    assertEquals(p2.getY(), ((MoveAnimation) move).getTo().getY(), 0.01);
  }


  @Test
  public void testTweening() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    assertEquals(247.37, ((MoveAnimation) move).tweening(200, 300, 10), 0.01);
  }

  @Test
  public void testApplyMoveAnim() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    // if not valid tick, not change
    move.applyAnimation(0);
    assertEquals(p1, rect.getPosition());
    // if valid tick, change applied at given current time
    move.applyAnimation(1);
    assertEquals(p1.getX(), rect.getPosition().getX(), 0.01);
    assertEquals(p1.getY(), rect.getPosition().getY(), 0.01);
    move.applyAnimation(10);
    assertEquals(247.37, rect.getPosition().getX(), 0.01);
    assertEquals(247.37, rect.getPosition().getY(), 0.01);
    move.applyAnimation(20);
    assertEquals(p2.getX(), rect.getPosition().getX(), 0.01);
    assertEquals(p2.getY(), rect.getPosition().getY(), 0.01);
  }

  @Test
  public void testApplyColorAnim() {
    color = new ChangeColor(rect, 1, 20, c1, c2);
    // if not valid tick, no change
    color.applyAnimation(0);
    assertEquals(c1, rect.getColor());
    // if valid tick, change applied at given current time
    color.applyAnimation(1);
    assertEquals(c1.getRed(), rect.getColor().getRed(), 0.01);
    assertEquals(c1.getGreen(), rect.getColor().getGreen(), 0.01);
    assertEquals(c1.getBlue(), rect.getColor().getBlue(), 0.01);
    color.applyAnimation(10);
    assertEquals(0.53, rect.getColor().getRed(), 0.01);
    assertEquals(c1.getGreen(), rect.getColor().getGreen(), 0.01);
    assertEquals(0.47, rect.getColor().getBlue(), 0.01);
    color.applyAnimation(20);
    assertEquals(c2.getRed(), rect.getColor().getRed(), 0.01);
    assertEquals(c2.getGreen(), rect.getColor().getGreen(), 0.01);
    assertEquals(c2.getBlue(), rect.getColor().getBlue(), 0.01);
  }

  @Test
  public void testApplyScaleAnim() {
    scale = new Scale(rect, 1, 20,
            50.0, 100.0, 20, 30);
    //if not valid tick, no change
    scale.applyAnimation(0);
    assertEquals(50.0, rect.width(), 0.01);
    assertEquals(100.0, rect.height(), 0.01);
    //if valid tick, change applied at given current time
    scale.applyAnimation(1);
    assertEquals(50.0, rect.width(), 0.01);
    assertEquals(100.0, rect.height(), 0.01);
    scale.applyAnimation(10);
    assertEquals(35.79, rect.width(), 0.01);
    assertEquals(66.84, rect.height(), 0.01);
    scale.applyAnimation(20);
    assertEquals(20, rect.width(), 0.01);
    assertEquals(30, rect.height(), 0.01);
  }

  @Test
  public void testGetShapeName() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    assertEquals("R", move.getShapeName());
  }

  @Test
  public void testGetStart() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    assertEquals(1, move.getStart());
  }

  @Test
  public void testShapeAtTime() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    color = new ChangeColor(rect, 1, 20, c1, c2);
    rect.addAnimation(move);
    rect.addAnimation(color);
    rect.shapeAtTime(0);
    assertEquals(p1.getX(), rect.getPosition().getX(), 0.01);
    assertEquals(p1.getY(), rect.getPosition().getY(), 0.01);
    assertEquals(c1, rect.getColor());
    rect.shapeAtTime(12);
    assertEquals(257.89, rect.getPosition().getX(), 0.01);
    assertEquals(257.89, rect.getPosition().getY(), 0.01);
    assertEquals(0.42, rect.getColor().getRed(), 0.01);
    assertEquals(0, rect.getColor().getGreen(), 0.01);
    assertEquals(0.58, rect.getColor().getBlue(), 0.01);
    rect.shapeAtTime(20);
    assertEquals(p2.getX(), rect.getPosition().getX(), 0.01);
    assertEquals(p2.getY(), rect.getPosition().getY(), 0.01);
    assertEquals(c2.getRed(), rect.getColor().getRed(), 0.01);
    assertEquals(c2.getGreen(), rect.getColor().getGreen(), 0.01);
    assertEquals(c2.getBlue(), rect.getColor().getBlue(), 0.01);
  }

  @Test
  public void testSvg() {
    o = new Oval("O1", p1, 20.0, 20.0, c1, 0,
            20, 0);
    move = new MoveAnimation(o, 0, 20, p1, p2);
    assertEquals(move.generateSVG(10, false),
            "<animate attribute=\"xml\" begin=\"0.0ms\"" +
            " dur=\"2000.0ms\" " +
            "attributeName=\"cx\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />" +
            "<animate attribute=\"xml\" begin=\"0.0ms\" dur=\"2000.0ms\" attributeName=\"cy\" " +
            "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />");
    assertEquals(move.generateSVG(10,false),
            "<animate attribute=\"xml\" begin=\"0.0ms\"" +
            " dur=\"2000.0ms\" " +
            "attributeName=\"cx\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />" +
            "<animate attribute=\"xml\" begin=\"0.0ms\" dur=\"2000.0ms\" attributeName=\"cy\" " +
            "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />");
    assertEquals(move.generateSVG(20,false),
            "<animate attribute=\"xml\" begin=\"0.0ms\" " +
            "dur=\"1000.0ms\" attributeName=\"cx\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />" +
            "<animate attribute=\"xml\" begin=\"0.0ms\" dur=\"1000.0ms\" attributeName=\"cy\" " +
            "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />");
    assertEquals(move.generateSVG(10, true),
            "<animate attribute=\"xml\" begin=\"base.begin+0.0ms\"" +
                    " dur=\"2000.0ms\" " +
                    "attributeName=\"cx\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />" +
                    "<animate attribute=\"xml\" begin=\"base.begin+0.0ms\" " +
                    "dur=\"2000.0ms\" attributeName=\"cy\" " +
                    "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />");



    color = new ChangeColor(rect, 1, 20, c1, c2);
    assertEquals(color.generateSVG(20,false), "<animate attributeName=\"fill\" " +
            "attributeType=\"XML\" begin=\"50.0ms\" dur=\"950.0ms\" from=\"rgb(255.0,0.0,0.0)\" " +
            "to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\"/>");
    assertEquals(color.generateSVG(20,false), "<animate attributeName=\"fill\" " +
            "attributeType=\"XML\" begin=\"50.0ms\" dur=\"950.0ms\" from=\"rgb(255.0,0.0,0.0)\" " +
            "to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\"/>");
    assertEquals(color.generateSVG(20,true), "<animate attributeName=\"fill\" " +
            "attributeType=\"XML\" begin=\"base.begin+50.0ms\" " +
            "dur=\"950.0ms\" from=\"rgb(255.0,0.0,0.0)\" " +
            "to=\"rgb(0.0,0.0,255.0)\" fill=\"freeze\"/>");


  }


}