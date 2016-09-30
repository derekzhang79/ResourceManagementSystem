/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -----------------------
 * DemoDatasetFactory.java
 * -----------------------
 * (C) Copyright 2001-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Bryan Scott;
 *                   Bill Kelemen;
 *                   David Browning;
 *                   Robert Redburn;
 *
 * $Id: DemoDatasetFactory.java,v 1.1 2005/04/28 16:29:17 harrym_nu Exp $
 *
 * Changes
 * -------
 * 10-Dec-2001 : Version 1 (DG);
 * 15-Mar-2002 : Added createHighLowOpenCloseDataset() method (DG);
 * 20-Jun-2002 : Added createMeterDataset() method (BRS);
 * 24-Jun-2002 : Moved createGanttDataset() method from GanttDemo (BRS);
 * 10-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-May-2003 : Added createSegmentedHighLowDataset(..) (BK);
 * 05-Aug-2003 : Added createBoxAndWhiskerDataset() method (DB);
 * 08-Aug-2003 : Refined createBoxAndWhiskerDataset() method (DB);
 * 19-Jan-2004 : Added createWaferMapDataset() and
 *                     createRandomWaferMapDataset() methods (RR);
 *
 */

package com;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * A utility class for generating sample datasets for the demos.
 * <p>
 * These datasets are hard-coded so that they are easily accessible for the
 * demonstration applications. In a real application, you would create datasets
 * dynamically by reading data from a file, a database, or some other source.
 * 
 * @author David Gilbert
 */
public abstract class DemoDatasetFactory {

    /**
     * Creates and returns a {@link CategoryDataset} for the demo charts.
     * 
     * @return a sample dataset.
     */
    public static CategoryDataset createPayrollDataset() {

        double[][] data = new double[][] { { 100000, 140000, 240000, 150000, 165000 },
            { 10000, 20000, 40000, 0, 5000 }, { 20000, 35000, 80000, 20000, 30000 } };

        Comparable[] c = new Comparable[5];
        c[0] = "2005";
        c[1] = "2006";
        c[2] = "2007";
        c[3] = "2008";
        c[4] = "2009";

        Comparable[] r = new Comparable[3];
        r[0] = "Salary";
        r[1] = "Bonus";
        r[2] = "Tax";

        return DatasetUtilities.createCategoryDataset(r, c, data);

    }

    public static CategoryDataset createTimerollDataset() {

        double[][] data = new double[][] { { 150, 140, 155, 160, 145, 178, 165 },
            { 10, 20, 5, 10, 25, 0, 10 } };

        Comparable[] c = new Comparable[7];
        c[0] = "January";
        c[1] = "February";
        c[2] = "March";
        c[3] = "April";
        c[4] = "May";
        c[5] = "June";
        c[6] = "July";

        Comparable[] r = new Comparable[2];
        r[0] = "Billable Hours";
        r[1] = "Leave";

        return DatasetUtilities.createCategoryDataset(r, c, data);

    }

}
