/*
 * $ Header: it.geosolutions.geogwt.gui.client.configuration.DropdownClientTool,v. 0.1 7-apr-2011 16.58.11 created by afabiani <alessio.fabiani at geo-solutions.it> $
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

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DropdownClientTool.
 */
public class DropdownClientTool extends GenericClientTool {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8377394857738349837L;

    /** The label. */
    private String label;

    /** The enabled. */
    private boolean enabled = true;

    /** The default value. */
    private String defaultValue;

    /** The allow blank. */
    private boolean allowBlank = false;

    /** The dropdown options. */
    private List<DropdownOption> dropdownOptions = new ArrayList<DropdownOption>();

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
     * Checks if is the enabled.
     * 
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled.
     * 
     * @param enabled
     *            the new enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Sets the dropdown options.
     * 
     * @param options
     *            the new dropdown options
     */
    public void setDropdownOptions(List<DropdownOption> options) {
        this.dropdownOptions = options;
    }

    /**
     * Gets the dropdown options.
     * 
     * @return the dropdown options
     */
    public List<DropdownOption> getDropdownOptions() {
        return this.dropdownOptions;
    }

    /**
     * Adds the option.
     * 
     * @param d
     *            the d
     */
    public void addOption(DropdownOption d) {
        this.dropdownOptions.add(d);
    }

    /**
     * Gets the default value.
     * 
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value.
     * 
     * @param defaultValue
     *            the new default value
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Checks if is the allow blank.
     * 
     * @return the allow blank
     */
    public boolean isAllowBlank() {
        return allowBlank;
    }

    /**
     * Sets the allow blank.
     * 
     * @param allowBlank
     *            the new allow blank
     */
    public void setAllowBlank(boolean allowBlank) {
        this.allowBlank = allowBlank;
    }

    /**
     * Gets the dropdown option display value.
     * 
     * @param dataValue
     *            the data value
     * @return the dropdown option display value
     */
    public String getDropdownOptionDisplayValue(String dataValue) {
        return null;
    }

}