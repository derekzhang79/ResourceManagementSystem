
package com.gits.rms.action.jfreechart;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

import com.DemoDatasetFactory;
import com.opensymphony.xwork2.ActionSupport;

public class TimerollGraphAction extends ActionSupport {

    private JFreeChart chart;

    @Override
    public String execute() throws Exception {
        CategoryDataset dataset = createDataset();
        chart = createChart(dataset);

        return super.SUCCESS;
    }

    public JFreeChart getChart() {
        return chart;
    }

    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private CategoryDataset createDataset() {
        return DemoDatasetFactory.createTimerollDataset();
    }

    /**
     * Creates a sample chart.
     * 
     * @param dataset
     *            the dataset for the chart.
     * 
     * @return a sample chart.
     */
    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedBarChart3D("Time roll", // chart
                                                                             // title
            "Month", // domain axis label
            "Hours", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL, // the plot orientation
            true, // include legend
            true, // tooltips
            false // urls
        );
        CategoryPlot plot = chart.getCategoryPlot();
        // CategoryItemRenderer renderer = new ExtendedStackedBarRenderer();
        // renderer.setToolTipGenerator((CategoryToolTipGenerator) new
        // StandardCategoryItemLabelGenerator());
        // plot.setRenderer(renderer);

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setItemMargin(0.10);

        renderer.setSeriesPaint(0, new Color(20, 133, 237));
        renderer.setSeriesPaint(1, new Color(36, 36, 188));

        renderer.setItemLabelsVisible(true);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);
        return chart;

    }

}
