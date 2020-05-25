import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.animation.ChangeColor;
import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.Color;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.Position;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.shape.Rectangle;
import cs3500.animator.view.SvgView;

import static org.junit.Assert.assertEquals;


public class SvgViewTest {
  Color c1 = new Color(1.0, 0.0, 0.0);
  Color c2 = new Color(0.0, 0.0, 1.0);
  Position p1 = new Position(200.0, 200.0);
  IShape rect = new Rectangle("R", p1, 50.0, 100.0, c1, 1,
          100, 0);
  IAnimation color = new ChangeColor(rect, 1, 20, c1, c2);
  ReadableShape s = ReadableShape.generateTextReadableShape(rect, color, 10);
  ReadableShape s1 = ReadableShape.generateTextReadableShape(rect, color, 20);

  @Test
  public void testSVG() {

    SvgView svg = new SvgView();
    svg.setFilePath("src/out.svg");
    ArrayList<ReadableShape> temp = new ArrayList<>();
    temp.add(s);
    try {
      svg.drawShapes(temp);
      svg.export();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(svg.getFinal(), "<svg width=\"1200\" height=\"1200\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">" +
            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" " +
            "fill=\"rgb(255.0,0.0,0.0)\" visability=\"hidden\" >" +
            "<animate attributeName=\"fill\" attributeType=\"XML\" begin=\"100.0ms\" " +
            "dur=\"1900.0ms\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\" " +
            "fill=\"freeze\"/>\n" +
            "</rect></svg>");
    temp.remove(s);
    temp.add(s1);
    try {
      svg.drawShapes(temp);
      svg.export();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(svg.getFinal(), "<svg width=\"1200\" height=\"1200\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\"><rect id=\"R\" x=\"200.0\" y=\"200.0\" " +
            "width=\"50.0\" height=\"100.0\" fill=\"rgb(255.0,0.0,0.0)\" visability=\"hidden\" >" +
            "<animate attributeName=\"fill\" attributeType=\"XML\" begin=\"100.0ms\" " +
            "dur=\"1900.0ms\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\"" +
            " fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" attributeType=\"XML\" begin=\"50.0ms\" " +
            "dur=\"950.0ms\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\" " +
            "fill=\"freeze\"/>\n" +
            "</rect></svg>");
    assertEquals(svg.getFinal(), "<svg width=\"1200\" height=\"1200\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\"><rect id=\"R\" x=\"200.0\" y=\"200.0\" " +
            "width=\"50.0\" height=\"100.0\" fill=\"rgb(255.0,0.0,0.0)\" visability=\"hidden\" >" +
            "<animate attributeName=\"fill\" attributeType=\"XML\" begin=\"100.0ms\" " +
            "dur=\"1900.0ms\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\"" +
            " fill=\"freeze\"/>\n" +
            "<animate attributeName=\"fill\" attributeType=\"XML\" begin=\"50.0ms\" " +
            "dur=\"950.0ms\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\" " +
            "fill=\"freeze\"/>\n" +
            "</rect></svg>");
  }
}
