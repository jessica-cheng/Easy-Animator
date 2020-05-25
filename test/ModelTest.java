import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.animator.animation.ChangeColor;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.animation.MoveAnimation;
import cs3500.animator.animation.Scale;
import cs3500.animator.model.Model;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.Oval;
import cs3500.animator.shape.Position;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelTest {
  IAnimation move;
  IAnimation move2;
  IAnimation move3;
  IAnimation color;
  IAnimation scale;
  Position p1 = new Position(200.0, 200.0);
  Position p2 = new Position(300.0, 300.0);
  Position p3 = new Position(500.0, 100.0);
  Position p4 = new Position(500.0, 400.0);
  Color c1 = new Color(1.0, 0.0, 0.0);
  Color c2 = new Color(0.0, 0.0, 1.0);
  Color c3 = new Color(0.0, 1.0, 0.0);
  IShape r;
  IShape o;
  IShape oval = new Oval("C", p3, 60.0, 30.0, c2,
          6, 100, 0);
  IShape rect = new Rectangle("R", p1, 50.0, 100.0, c1,
          1, 100, 0);
  Model m = new Model();

  // tests addShapes and getMaxFrame
  @Test
  public void testAddShapes() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    m.addShapes(r);
    assertEquals("R1", m.getListOfShapes().get(0).getName());
    m.addShapes(o);
    assertEquals("O1", m.getListOfShapes().get(1).getName());
    m.addShapes(rect);
    assertEquals("R", m.getListOfShapes().get(2).getName());
    List<IShape> result = new ArrayList<>();
    result.add(r);
    result.add(o);
    result.add(rect);
    assertEquals(result, m.getListOfShapes());
    assertEquals(100, m.getMaxFrame(), 0.01);
  }

  // if shape to be added has same name and shape type in the list of shapes
  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidShape() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1,
            0, 40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    IShape o2 = new Oval("O1", p2, 30.0, 30.0, c1, 2,
            5, 0);
    List<IShape> result = new ArrayList<>();
    assertEquals(result, m.getListOfShapes());
    m.addShapes(r);
    result.add(r);
    assertEquals(result, m.getListOfShapes());
    m.addShapes(o);
    m.addShapes(o2);
  }

  @Test
  public void testShapeText() {
    move = new MoveAnimation(rect, 10, 50, p1, p2);
    move2 = new MoveAnimation(oval, 20, 70, p3, p4);
    move3 = new MoveAnimation(rect, 70, 100, p2, p1);
    color = new ChangeColor(oval, 50, 80, c2, c3);
    scale = new Scale(rect, 51, 70, 50.0, 100.0, 25.0, 100.0);
    m.addShapes(rect);
    m.addShapes(oval);
    m.addAnimation("R", move);
    m.addAnimation("R", scale);
    m.addAnimation("R", move3);
    m.addAnimation("C", color);
    m.addAnimation("C", move2);


    assertEquals("Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=1.0s\n" +
                    "Disappears at t=100.0s\n" +
                    "\n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n" +
                    "Disappears at t=100.0s\n" +
                    "\n"

            , m.getShapeDescription());
  }

  @Test
  public void testGetShapesAtFrame() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 0,
            20, 0);
    move = new MoveAnimation(rect, 10, 50, p1, p2);
    m.addShapes(r);
    m.addShapes(o);
    ReadableShape r1 = m.getShapesAtFrame(0).get(0);
    ReadableShape r2 = m.getShapesAtFrame(0).get(1);

    assertEquals(r1.getShapeSVG(), r.generateSVG(10, false));
    assertEquals(r1.getCol(), r.getColor());
    assertEquals(r1.getX(), r.getPosition().getX(), 0.0001);
    assertEquals(r1.getX(), r.getPosition().getX(), 0.0001);
    assertEquals(r1.getY(), r.getPosition().getY(), 0.0001);
    assertEquals(r2.getCol(), o.getColor());
    assertEquals(r2.getShapeSVG(), o.generateSVG(10, false));
  }

  @Test
  public void testListOfShapes() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    m.addShapes(r);
    m.addShapes(o);
    m.addShapes(oval);
    List<IShape> result = new ArrayList<>();
    result.add(r);
    result.add(o);
    result.add(oval);
    assertEquals(3, m.getListOfShapes().size());
    assertEquals(result, m.getListOfShapes());
  }

  @Test
  public void testAddAnimation() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    m.addShapes(r);
    m.addShapes(o);
    m.addShapes(oval);

    move = new MoveAnimation(rect, 10, 50, p1, p2);
    move2 = new MoveAnimation(oval, 20, 70, p3, p4);
    color = new ChangeColor(oval, 50, 80, c2, c3);
    List<IAnimation> result = new ArrayList<>();
    result.add(move2);
    result.add(color);

    m.addAnimation("C", color);
    assertEquals(1, oval.getAnimations().size());
    m.addAnimation("C", move2);
    assertEquals(2, oval.getAnimations().size());
    assertEquals(result, oval.getAnimations());

  }


  // tests when animation is added to shape without that name
  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimation2() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    m.addShapes(r);
    m.addShapes(o);
    m.addShapes(oval);

    move = new MoveAnimation(rect, 10, 50, p1, p2);
    move2 = new MoveAnimation(oval, 20, 70, p3, p4);
    color = new ChangeColor(oval, 50, 80, c2, c3);
    List<IAnimation> result = new ArrayList<>();
    result.add(move2);
    result.add(color);

    m.addAnimation("C", color);
    assertEquals(1, oval.getAnimations().size());
    m.addAnimation("R", move2);
  }

  @Test
  public void testTweenShape() {
    move = new MoveAnimation(rect, 1, 20, p1, p2);
    color = new ChangeColor(rect, 1, 20, c1, c2);
    rect.addAnimation(move);
    rect.addAnimation(color);
    rect.shapeAtTime(0);
    assertEquals(rect, m.tween(rect, 0));
    rect.shapeAtTime(5);
    assertEquals(rect, m.tween(rect, 5));
    rect.shapeAtTime(10);
    assertEquals(rect, m.tween(rect, 10));
  }

  @Test
  public void testAnimText() {
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    m.addShapes(r);
    m.addShapes(o);
    m.addShapes(oval);

    move = new MoveAnimation(rect, 10, 50, p1, p2);
    r.addAnimation(move);
    ReadableShape r1 = m.getAnimTextAtFrame(10, 10).get(0);
    ReadableShape rtest = ReadableShape.generateTextReadableShape(r, move, 10);
    assertEquals(r1.getAnimSVG(), rtest.getAnimSVG());
    assertEquals(r1.getShapeSVG(), rtest.getShapeSVG());
  }

  @Test
  public void reset() {
    Model m = new Model();
    r = new Rectangle("R1", p1, 30.0, 30.0, c1, 0,
            40, 0);
    o = new Oval("O1", p1, 20.0, 20.0, c1, 1,
            20, 0);
    m.addShapes(r);
    m.addShapes(o);
    m.addShapes(oval);
    Scale s = new Scale(r, 1, 10, 30, 30, 50, 40);
    Scale s1 = new Scale(r, 30, 40, 30, 30, 60, 60);
    r.addAnimation(s1);
    r.addAnimation(s);
    m.set();
    //original list and current list are the same
    assertEquals(m.getOriginal().get(0).height(), m.getListOfShapes().get(0).height(), 0.001);
    assertEquals(m.getOriginal().get(0).width(), m.getListOfShapes().get(0).width(), 0.001);
    assertEquals(m.getOriginal().get(0).getName(), m.getListOfShapes().get(0).getName());
    //edits one shape, so the lists are different
    m.getShapesAtFrame(9);
    assertNotEquals(m.getOriginal().get(0).height(),
            m.getListOfShapes().get(0).height(), 0.001);
    assertNotEquals(m.getOriginal().get(0).width(),
            m.getListOfShapes().get(0).width(), 0.001);
    assertEquals(m.getOriginal().get(0).getName(), m.getListOfShapes().get(0).getName());
    //resets and shapes are back to normal.
    m.reset();
    assertEquals(m.getOriginal().get(0).height(), m.getListOfShapes().get(0).height(), 0.001);
    assertEquals(m.getOriginal().get(0).width(), m.getListOfShapes().get(0).width(), 0.001);
    assertEquals(m.getOriginal().get(0).getName(), m.getListOfShapes().get(0).getName());
    m.getShapesAtFrame(32);
    assertNotEquals(m.getOriginal().get(0).height(),
            m.getListOfShapes().get(0).height(), 0.001);
    assertNotEquals(m.getOriginal().get(0).width(),
            m.getListOfShapes().get(0).width(), 0.001);
    assertEquals(m.getOriginal().get(0).getName(), m.getListOfShapes().get(0).getName());
    m.reset();
    assertEquals(m.getOriginal().get(0).height(), m.getListOfShapes().get(0).height(), 0.001);
    assertEquals(m.getOriginal().get(0).width(), m.getListOfShapes().get(0).width(), 0.001);
    assertEquals(m.getOriginal().get(0).getName(), m.getListOfShapes().get(0).getName());
  }
}
