/**
 * ﻿Copyright (C) 2013
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package org.n52.io.v1.data;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public abstract class ParameterSet {

    // XXX refactor ParameterSet, DesignedParameterSet, UndesingedParameterSet and QueryMap
    
    /**
     * The timespan of interest (as <a href="http://en.wikipedia.org/wiki/ISO_8601#Time_intervals">ISO8601
     * interval</a> excluding the Period only version).
     */
    private String timespan;
    
    /**
     * If image data shall be encoded in Base64 to be easily embedded in HTML by JS clients.
     */
    private boolean base64;

    /**
     * If timeseries data shall be generalized or not.
     */
    private boolean generalize;
    
    /**
     * If reference values shall be appended to the timeseries data.
     */
    private boolean expanded;

    /**
     * A language code to determine the requested locale. "en" is the default.
     */
    private String language = "en";
    
    protected ParameterSet() {
        timespan = createDefaultTimespan();
    }

    private String createDefaultTimespan() {
        DateTime now = new DateTime();
        DateTime lastWeek = now.minusWeeks(1);
        return new Interval(lastWeek, now).toString();
    }
    
    public boolean isGeneralize() {
        return generalize;
    }
    
    public void setGeneralize(boolean generalize) {
        this.generalize = generalize;
    }

    public String getTimespan() {
        return timespan;
    }
    
    public void setTimespan(String timespan) {
        if (timespan == null) {
            this.timespan = createDefaultTimespan();
        }
        else {
            this.timespan = validateTimespan(timespan);
        }
    }

    public boolean isBase64() {
		return base64;
	}

	public void setBase64(boolean base64) {
		this.base64 = base64;
	}

	public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String validateTimespan(String timespan) {
        return Interval.parse(timespan).toString();
    }

    public abstract String[] getTimeseries();

}
