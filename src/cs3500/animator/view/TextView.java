package cs3500.animator.view;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.util.ButtonListener;

/**
 * Represents a textual view of the animation.
 */
public class TextView implements IView {
  String filepath = "out";
  BufferedWriter write;

  /**
   * Constructs a text view.
   */
  public TextView(){
    //Needed for visability between packages
  }

  @Override
  public void drawShapes(ArrayList<ReadableShape> shape) throws IOException {
    for (ReadableShape s : shape) {
      String o = s.getCurrAnim() + "\n";
      if (filepath.equals("out")) {
        System.out.append(o);
      }
      else {
        write.append(o);
      }
    }
  }

  @Override
  public void drawString(String in) {
    if (filepath.equals("out")) {
      System.out.append(in);

    } else {
      try {
        write.append(in);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void export() {
    if (!filepath.equals("out")) {
      try {
        write.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void setFilePath(String filePath) {
    this.filepath = filePath;
    if (!filePath.equals("out")) {
      try {
        write = new BufferedWriter(new FileWriter(filePath));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void drawForSVG(List<IShape> shape) throws IOException {
    return;
  }

  @Override
  public void setFps(int fps){
    return;
  }

  @Override
  public void setButtonListener(ButtonListener buttonListener) {
    throw new UnsupportedOperationException("this doesnt need a button listner");
  }

  @Override
  public String getFinal() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLooping(double maxFrame) {
    throw new UnsupportedOperationException();

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
