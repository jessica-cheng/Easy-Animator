package cs3500.animator.view;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.animation.IAnimation;
import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.shape.ShapeType;
import cs3500.animator.util.ButtonListener;

public class SvgView implements IView {
  private String filePath = "out.svg";
  private List<IShape> shapes;
  private String finalString;
  private int fps = 1;
  private boolean loop = false;
  private double maxFrame = 0;

  public SvgView() {
    //to make the contructor public
  }




  @Override
  public void drawForSVG(List<IShape> shape) throws IOException {
    this.shapes = shape;
  }

  public void export() {
    String tempFinal = "";
    if(loop){
      String duration = Double.toString(maxFrame / fps * 1000);
      String fauxShape = "<rect>\n"
              + "<animate id=\"base\" begin=\"0;base.end\" "
              + "dur=\"" + duration +"ms\" "
              + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
              "</rect>\n";
      tempFinal += fauxShape;
    }
    for(IShape s : shapes) {
      String temp = "";
      temp += s.generateSVG(this.fps, loop)  + "\n";
      for (IAnimation a : s.getAnimations()) {
        temp += a.generateSVG(this.fps,loop) + "\n";
      }

      if(loop){
        temp += s.generateLoop();
      }

      if (s.getType().equals(ShapeType.rectangle)) {
        temp += "</rect>";
      }
      if (s.getType().equals(ShapeType.oval)) {
        temp += "</ellipse>";
      }
      tempFinal =  tempFinal + temp;
    }

    tempFinal = "<svg width=\"1200\" height=\"1200\" version=\"1.1\"" +
            " xmlns=\"http://www.w3.org/2000/svg\">" + tempFinal + "</svg>";

    this.finalString = tempFinal;
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
      writer.write(tempFinal);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  @Override
  public void drawShapes(ArrayList<ReadableShape> shape) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void drawString(String in) {
    return;
  }

  @Override
  public void setFps(int fps) {
    this.fps = fps;
  }

  @Override
  public void setButtonListener(ButtonListener buttonListener) {
    throw new UnsupportedOperationException("this doesnt have a buttonlistner");
  }

  @Override
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getFinal(){
    return this.finalString;
  }

  public void setLooping(double maxFrame){
    this.loop = true;
    this.maxFrame = maxFrame;
  }

  @Override
  public int getEnd() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setShapes(ArrayList<ReadableShape> shapes) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean scrubContains(Point e) {
    throw new UnsupportedOperationException();

  }
}
