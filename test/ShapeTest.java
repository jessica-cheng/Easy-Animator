import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.MoveAnimation;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Position;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.shape.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ShapeTest {
  Position p = new Position(50.0, 50.0);
  Color c = new Color(50.0, 50.0, 50.0);
  IShape oval;

  IShape rect = new Rectangle("R", p, 10.0, 20.0, c, 0,
          20, 0);


  @Test
  public void testPosnToString() {
    assertEquals("(50.0,50.0)", p.toString());
  }

  @Test
  public void testShapePosn() {
    assertEquals("(50.0,50.0)", rect.getPosition().toString());
  }

  @Test
  public void testSetPosn() {
    rect.setPosition(20.0, 10.0);
    assertEquals("(20.0,10.0)", rect.getPosition().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullShapePosn() {
    rect = new Rectangle("R", null,
            20.0, 20.0, c, 0, 20, 0);
  }

  @Test
  public void testGetColors() {
    Color c = new Color(1.0, 2.0, 3.0);
    assertEquals(1.0, c.getRed(), 0.01);
    assertEquals(2.0, c.getGreen(), 0.01);
    assertEquals(3.0, c.getBlue(), 0.01);
  }

  @Test
  public void testShapeColor() {
    assertEquals(c, rect.getColor());
    assertEquals("(50.0,50.0,50.0)", rect.strColor());
  }

  @Test
  public void testSetColor() {
    assertEquals(rect.getColor(), c);
    rect.setColor(100.0, 100.0, 100.0);
    assertEquals("(100.0,100.0,100.0)", rect.strColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    rect = new Rectangle("R", p, 20.0, 20.0,
            null, 0, 20, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegColor() {
    Color c = new Color(-1, 100.0, 100.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColor() {
    Color c = new Color(0.0, 270.0, 100.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidWidth() {
    rect = new Rectangle("R", p, 0.0, 20.0, c,
            0, 20,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidHeight() {
    rect = new Rectangle("R", p, 10.0, -20.0, c,
            0, 20, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBiggerAppearTime() {
    rect = new Rectangle("R", p, 10.0,
            20.0, c, 10, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegAppearTime() {
    rect = new Rectangle("R", p, 10.0,
            20.0, c, -1, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegDisappearTime() {
    rect = new Rectangle("R", p, 10.0, 20.0,
            c, 0, -2, 0);
  }

  @Test
  public void testShapeWidth() {
    assertEquals(10.0, rect.width(), 0.01);
    assertEquals(10.0, rect.width(), 0.01);
  }

  @Test
  public void testSetWidth() {
    assertEquals(rect.width(), 10, 0.001);
    rect.setWidth(20.0);
    assertEquals(20.0, rect.width(), 0.01);
  }

  @Test
  public void testShapeHeight() {
    assertEquals(20, rect.height(), 0.01);
  }

  @Test
  public void testSetHeight() {
    rect.setHeight(10.0);
    assertEquals(10.0, rect.height(), 0.01);
  }

  @Test
  public void testGetName() {
    assertEquals("R", rect.getName());
  }

  @Test
  public void testGetType() {
    assertEquals(ShapeType.rectangle, rect.getType());
  }

  @Test
  public void testGetStart() {
    assertEquals(0.0, rect.getStart(), 0.001);
  }

  @Test
  public void testGetEnd() {
    assertEquals(20.0, rect.getEnd(), 0.001);
  }

  @Test
  public void isSeen() {
    assertEquals(true, rect.isSeen(0));
  }

  @Test
  public void isNotSeen() {
    assertEquals(false, rect.isSeen(22));
  }


  @Test
  public void testShapeString() {
    oval = new Oval("O1", p, 20.0, 20.0, c, 1,
            20, 0);
    rect = new Rectangle("R1", p, 20.0, 20.0, c, 21,
            40, 0);

    assertEquals("Name: O1\n" +
            "Type: oval\n" +
            "Center: (50.0,50.0), X radius: 20.0, Y radius: 20.0, Color: (50.0,50.0,50.0)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=20.0s\n\n", oval.toString());
    assertEquals("Name: R1\n" +
            "Type: rectangle\n" +
            "Corner: (50.0,50.0), Width: 20.0, Height: 20.0, Color: (50.0,50.0,50.0)\n" +
            "Appears at t=21.0s\n" +
            "Disappears at t=40.0s\n\n", rect.toString());
  }

  @Test
  public void testGetAnimation() {
    rect = new Rectangle("R1", p, 20.0, 20.0, c, 21,
            40, 0);
    IAnimation move = new MoveAnimation(rect, 22, 30, p, p);
    IAnimation move2 = new MoveAnimation(rect, 32, 39, p, p);
    List<IAnimation> animations = new ArrayList<>();
    rect.getAnimations().add(move);
    animations.add(move);
    assertEquals(1, rect.getAnimations().size());
    assertEquals(animations, rect.getAnimations());
    rect.getAnimations().add(move2);
    animations.add(move2);
    assertEquals(2, rect.getAnimations().size());
    assertEquals(animations, rect.getAnimations());
    animations.add(move);
    assertNotEquals(animations, rect.getAnimations());
  }

  @Test
  public void testAddAnimation() {
    IAnimation move = new MoveAnimation(rect, 0, 10, p, p);
    IAnimation move2 = new MoveAnimation(rect, 10, 15, p, p);
    assertEquals(0, rect.getAnimations().size());
    rect.addAnimation(move);
    assertEquals(1, rect.getAnimations().size());
    rect.addAnimation(move2);
    assertEquals(2, rect.getAnimations().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictingAnimation() {
    IAnimation move = new MoveAnimation(rect, 0, 10, p, p);
    IAnimation move2 = new MoveAnimation(rect, 10, 15, p, p);
    assertEquals(0, rect.getAnimations().size());
    rect.addAnimation(move);
    assertEquals(1, rect.getAnimations().size());
    rect.addAnimation(move2);
    assertEquals(2, rect.getAnimations().size());
    rect.addAnimation(move);
  }

  @Test
  public void testGetSVG() {
    assertEquals(rect.generateSVG(10,false), "<rect id=\"R\" x=\"50.0\"" +
            " y=\"50.0\" width=\"10.0\" height=\"20.0\" " +
            "fill=\"rgb(12750.0,12750.0,12750.0)\" visability=\"hidden\" opacity=\"0\" >" +
            "<set attribute=\"xml\" attributeName=\"opacity\" to=\"1\" begin=\"0.0ms\" />" +
            "<set attribute=\"xml\" attributeName=\"opacity\" to=\"0\" begin=\"2000.0ms\" />");

    assertEquals(rect.generateSVG(20,false), "<rect id=\"R\" x=\"50.0\"" +
            " y=\"50.0\" width=\"10.0\" height=\"20.0\" fill=\"rgb(12750.0,12750.0,12750.0)\"" +
            " visability=\"hidden\" opacity=\"0\" ><set attribute=\"xml\" " +
            "attributeName=\"opacity\" to=\"1\" begin=\"0.0ms\" />" +
            "<set attribute=\"xml\" attributeName=\"opacity\" to=\"0\" begin=\"1000.0ms\" />");

    assertEquals(rect.generateSVG(10,true), "<rect id=\"R\" x=\"50.0\"" +
            " y=\"50.0\" width=\"10.0\" height=\"20.0\" " +
            "fill=\"rgb(12750.0,12750.0,12750.0)\" visability=\"hidden\" opacity=\"0\" >" +
            "<set attribute=\"xml\" " +
            "attributeName=\"opacity\" to=\"1\" begin=\"base.begin+0.0ms\" />" +
            "<set attribute=\"xml\" " +
            "attributeName=\"opacity\" to=\"0\" begin=\"base.begin+2000.0ms\" />");
    assertEquals(rect.generateSVG(20,true), "<rect id=\"R\" x=\"50.0\" " +
            "y=\"50.0\" width=\"10.0\" height=\"20.0\" " +
            "fill=\"rgb(12750.0,12750.0,12750.0)\"" +
            " visability=\"hidden\" opacity=\"0\" >" +
            "<set attribute=\"xml\" attributeName=\"opacity\" to=\"1\"" +
            " begin=\"base.begin+0.0ms\" />" +
            "<set attribute=\"xml\" attributeName=\"opacity\" to=\"0\" " +
            "begin=\"base.begin+1000.0ms\" />");
  }


  @Test
  public void testSvgLoop(){
    oval = new Oval("o",new Position(10,10),5,5,
            new Color(0,1,1),100,101, 0);
    assertEquals(rect.generateLoop(), "<animate attributeName=\"fill\" " +
            "attributeType=\"XML\" begin=\"base.end\" dur=\"1ms\"" +
            " to=\"rgb(12750.0,12750.0,12750.0)\" fill=\"freeze\"/>\n" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"x\" to=\"50.0\" fill=\"freeze\" />\n" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"y\" to=\"50.0\" fill=\"freeze\" />" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"width\" to=\"10.0\" fill=\"freeze\" />\n" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"height\" to=\"20.0\" fill=\"freeze\" /><set attribute=\"xml\" " +
            "attributeName=\"opacity\" to=\"1\" begin=\"base.end\" />");
    assertEquals(oval.generateLoop(),"<animate attributeName=\"fill\" " +
            "attributeType=\"XML\" begin=\"base.end\" dur=\"1ms\" " +
            "to=\"rgb(0.0,255.0,255.0)\" fill=\"freeze\"/>\n" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"cx\" to=\"10.0\" fill=\"freeze\" />\n" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"cy\" to=\"10.0\" fill=\"freeze\" />" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"rx\" to=\"5.0\" fill=\"freeze\" />\n" +
            "<animate attribute=\"xml\" begin=\"base.end\" dur=\"1ms\" " +
            "attributeName=\"ry\" to=\"5.0\" fill=\"freeze\" /><set attribute=\"xml\" " +
            "attributeName=\"opacity\" to=\"1\" begin=\"base.end\" />");
  }

}