/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarMapAction,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 17.01.44 $
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
package it.geosolutions.geogwt.gui.client.widget.map.action;

import it.geosolutions.geogwt.gui.client.model.Category;

// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarMapAction.
 */
public abstract class ToolbarMapAction extends ToolbarAction {

    /** The tooltip. */
    private String tooltip;

    /**
     * Instantiates a new toolbar map action.
     * 
     * @param tooltip
     *            the tooltip
     * @param category
     *            the category
     */
    public ToolbarMapAction(String tooltip, Category category) {
        super(category);
        this.tooltip = tooltip;
    }

    /**
     * Gets the tooltip.
     * 
     * @return the tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Sets the tooltip.
     * 
     * @param tooltip
     *            the new tooltip
     */
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
}
