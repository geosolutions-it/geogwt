/*
 * $ Header: it.geosolutions.geogwt.gui.client.configuration.MenuClientTool,v. 0.1 25-gen-2011 11.24.45 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.24.45 $
 *
 * ====================================================================
 *
 * Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
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

import java.util.Collections;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuClientTool.
 */
public class MenuClientTool extends GenericClientTool {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8189124026216386133L;

    /** The enabled. */
    private boolean enabled;

    /** The action tools. */
    private List<ActionClientTool> actionTools;

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
     * Gets the action tools.
     * 
     * @return the action tools
     */
    public List<ActionClientTool> getActionTools() {
        return actionTools;
    }

    /**
     * Sets the action tools.
     * 
     * @param actionTools
     *            the new action tools
     */
    public void setActionTools(List<ActionClientTool> actionTools) {
        Collections.sort(actionTools);
        this.actionTools = actionTools;
    }

}
