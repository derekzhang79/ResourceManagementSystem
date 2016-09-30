package com.gits.rms.action.jfreechart;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.persistence.EmployeesHibernateDao;
import com.gits.rms.vo.EmployeesCountVO;

public class DBEmpPerDeptGraphAction extends ActionSupport {

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
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset() {

        EmployeesHibernateDao empDao = new EmployeesHibernateDao();
        List<EmployeesCountVO> empCountList = empDao.getEmpCountListPerDept();

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (EmployeesCountVO empCountVO : empCountList) {
            dataset.addValue(empCountVO.getCount(), new String("Head Count"), empCountVO.getCategory());
        }

        return dataset;

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

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart("Head Count", // chart
                                                                     // title
            new String(), // domain axis label
            new String(), // range axis label
            dataset, // data
            PlotOrientation.HORIZONTAL, // orientation
            true, // include legend
            true, // tooltips?
            false // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        renderer.setSeriesPaint(0, new Color(3, 134, 191));

        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

}
