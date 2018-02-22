package qkd;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/**
 * @author imssbora
 */
public class XYLineChartExample extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;

  public XYLineChartExample(String title , ArrayList<Integer>round) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset(round);

    // Create chart
    JFreeChart chart = ChartFactory.createXYLineChart(
        "XY Line Chart Example",
        "X-Axis",
        "Y-Axis",
        dataset,
        PlotOrientation.VERTICAL,
        true, true, false);

    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  public XYDataset createDataset(ArrayList<Integer>round) {
    
	  
	  XYSeriesCollection dataset = new XYSeriesCollection();

    XYSeries series = new XYSeries("");
   
    int j=8;
    for(int i=0;i<round.size();i++){
    	series.add(j,round.get(i));
    	j=j+8;
    }
    //Add series to dataset
    dataset.addSeries(series);
    
    return dataset;
  }

  
  
}
