package cs3500.animator.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.shape.IShape;
import cs3500.animator.shape.ReadableShape;
import cs3500.animator.util.ButtonListener;

/**
 * A view that will interact with the user and will have capabilities of the visual view,
 * in that it will actually show the animation being played. It will also have capabilities of the
 * SVG view, in that it will be able to create and export the animation in SVG format. In this
 * sense, this will be a hybrid of two existing views.
 */
public class HybridView extends JFrame
        implements IView, ActionListener, ChangeListener, ItemListener {
  private MyPanel myPanel = new MyPanel();
  static final int FPS_MIN = 1;
  static final int FPS_MAX = 300;
  JLabel actionDisplay;
  JSlider scrubber;
  private ButtonListener buttonListener;
  private ArrayList<ReadableShape> listOfShapes;

  /**
   * Constructs a HybridView.
   */
  public HybridView() {
    //to make public
    this.listOfShapes = new ArrayList<>();
  }

  @Override
  public void export() {
    setSize(800, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane jScrollPane = new JScrollPane(myPanel);
    add(jScrollPane);
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setSize(800, 100);
    this.add(buttonsPanel, BorderLayout.SOUTH);


    JButton startButton = new JButton("Start");
    startButton.setActionCommand("Start animation");
    startButton.addActionListener(buttonListener);
    startButton.addActionListener(this);
    buttonsPanel.add(startButton);

    JButton pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause animation");
    pauseButton.addActionListener(buttonListener);
    pauseButton.addActionListener(this);
    buttonsPanel.add(pauseButton);

    JButton resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("Resume animation");
    resumeButton.addActionListener(buttonListener);
    resumeButton.addActionListener(this);
    buttonsPanel.add(resumeButton);

    JButton restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart animation");
    restartButton.addActionListener(buttonListener);
    restartButton.addActionListener(this);
    buttonsPanel.add(restartButton);


    JLabel fpsSliderLabel = new JLabel("FPS Speed", JLabel.CENTER);
    fpsSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    JSlider fpsControl = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, 20);
    fpsControl.setName("FPS");
    fpsControl.addChangeListener(buttonListener);
    fpsControl.addChangeListener(this);

    //Turn on labels at major tick marks.

    fpsControl.setMajorTickSpacing(100);
    fpsControl.setMinorTickSpacing(10);
    fpsControl.setPaintTicks(false);
    fpsControl.setPaintLabels(false);
    buttonsPanel.add(fpsSliderLabel);
    buttonsPanel.add(fpsControl);

    JButton exportToSVGButton = new JButton("Export to SVG");
    exportToSVGButton.setActionCommand("Export to SVG");
    exportToSVGButton.addActionListener(this);
    exportToSVGButton.addActionListener(buttonListener);
    buttonsPanel.add(exportToSVGButton);

    JCheckBox loopButton = new JCheckBox("Loop");
    loopButton.setSelected(false);
    loopButton.setActionCommand("Loop animation");
    loopButton.addItemListener(buttonListener);
    loopButton.addItemListener(this);
    loopButton.addActionListener(buttonListener);
    loopButton.addActionListener(this);
    buttonsPanel.add(loopButton);

    JPanel actionPanel = new JPanel();
    this.add(actionPanel, BorderLayout.NORTH);
    actionDisplay = new JLabel("Ready to start");
    actionPanel.add(actionDisplay);

    // SCRUBBING
    JPanel scrubPanel = new JPanel(new BorderLayout());
    scrubPanel.setPreferredSize(new Dimension(770, 90));
    buttonsPanel.add(scrubPanel, BorderLayout.SOUTH);

    // trying to set max to max frame
    int max = this.getEnd();
    scrubber = new JSlider(JSlider.HORIZONTAL, 0, max, 0);
    scrubber.setName("Scrub");
    scrubber.addMouseListener(buttonListener);
    scrubber.addChangeListener(buttonListener);
    scrubber.setMajorTickSpacing(50);
    scrubber.setMinorTickSpacing(1);
    scrubber.setPaintTicks(true);
    scrubber.setPaintLabels(true);
    scrubPanel.add(scrubber);

    setVisible(true);
  }

  @Override
  public void drawShapes(ArrayList<ReadableShape> shape) throws IOException {
    myPanel.setShapes(shape);
    myPanel.repaint();
  }

  @Override
  public void drawForSVG(List<IShape> shape) throws IOException {
    throw new UnsupportedOperationException("This doesnt need a list of IShapes");
  }

  @Override
  public void drawString(String in) {
    throw new UnsupportedOperationException("This doesnt need to draw strings");
  }


  @Override
  public void setFilePath(String filePath) {
    //doesnt throw and error, but doesnt return anything
    return;
  }


  @Override
  public void setFps(int fps) {
    throw new UnsupportedOperationException("Doesnt use the setFPS method");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start animation":
        actionDisplay.setText("Animation was started");
        break;
      case "Pause animation":
        actionDisplay.setText("Animation was paused");
        break;
      case "Resume animation":
        actionDisplay.setText("Animation was resumed");
        break;
      case "Restart animation":
        actionDisplay.setText("Animation was restarted");
        break;
      default:
    }
  }


  @Override
  public void setButtonListener(ButtonListener buttonListener) {
    this.buttonListener = buttonListener;
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
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    if (!source.getValueIsAdjusting()) {
      int fps = (int) source.getValue();
      actionDisplay.setText("Speed changed to " + Integer.toString(fps) + " fps.");
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String s = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    if (s.equals("Loop animation")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        actionDisplay.setText("Looping...");
      } else {
        actionDisplay.setText("");
      }
    }
  }

  public String getActionText() {
    return actionDisplay.getText();
  }

  // getting the max frame
  @Override
  public int getEnd() {
    int maxTime = 0;
    for (int i = 0; i < this.listOfShapes.size(); i++) {
      int endTime = (int) listOfShapes.get(i).getEnd();
      if (endTime > maxTime) {
        maxTime = endTime;
      }
    }
    return maxTime;
  }

  @Override
  public void setShapes(ArrayList<ReadableShape> shapes) {
    this.listOfShapes = shapes;
  }

  @Override
  public boolean scrubContains(Point e) {
    return scrubber.contains(e);
  }
}
