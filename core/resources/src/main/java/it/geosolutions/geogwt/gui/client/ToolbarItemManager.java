/*
 * $ Header: it.geosolutions.georepo.gui.client.ToolbarItemManager,v. 0.1 25-gen-2011 11.30.33 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.30.33 $
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
package it.geosolutions.geogwt.gui.client;

import it.geosolutions.geogwt.gui.client.configuration.GenericClientTool;
import it.geosolutions.geogwt.gui.client.configuration.IToolbarItemManager;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarItemManager.
 */
public class ToolbarItemManager implements IToolbarItemManager {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1501328199018303376L;

    /** The client tools. */
    private List<GenericClientTool> clientTools;

    /*
     * (non-Javadoc)
     * 
     * @see it.geosolutions.georepo.gui.client.configuration.IToolbarItemManager#getClientTools()
     */
    public List<GenericClientTool> getClientTools() {
        return clientTools;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * it.geosolutions.georepo.gui.client.configuration.IToolbarItemManager#setClientTools(java.
     * util.List)
     */
    public void setClientTools(List<GenericClientTool> clientTools) {
        this.clientTools = clientTools;
    }

}
