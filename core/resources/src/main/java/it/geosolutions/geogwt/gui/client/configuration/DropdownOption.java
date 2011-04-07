/*
 * $ Header: it.geosolutions.geogwt.gui.client.configuration.DropdownOption,v. 0.1 7-apr-2011 16.58.11 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.58.11 $
 *
 * ====================================================================
 * GeoGWT 0.1-SNAPSHOT
 * 
 * Copyright (C) 2011 GeoSolutions S.A.S.
 * http://www.geo-solutions.it
 *
 * GPLv3 + Classpath exception
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. 
 *
 * ====================================================================
 *
 * This software consists of voluntary contributions made by developers
 * of GeoSolutions.  For more information on GeoSolutions, please see
 * <http://www.geo-solutions.it/>.
 *
 */
package it.geosolutions.geogwt.gui.client.configuration;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DropdownOption.
 */
public class DropdownOption implements Serializable {

    /** The Constant LABEL_KEY. */
    public static final String LABEL_KEY = "label";

    /** The Constant VALUE_KEY. */
    public static final String VALUE_KEY = "value";

    /** The label. */
    private String label;

    /** The value. */
    private String value;

    /**
     * Instantiates a new dropdown option.
     */
    public DropdownOption() {
    }

    /**
     * Instantiates a new dropdown option.
     * 
     * @param label
     *            the label
     * @param value
     *            the value
     */
    public DropdownOption(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**
     * Gets the label.
     * 
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the label.
     * 
     * @param label
     *            the new label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     * 
     * @param value
     *            the new value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
