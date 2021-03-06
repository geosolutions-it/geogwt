/*
 * $ Header: it.geosolutions.geogwt.gui.client.configuration.IGeoGWTConfiguration,v. 0.1 7-apr-2011 16.58.10 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.58.10 $
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// TODO: Auto-generated Javadoc
/**
 * The Interface IGeoGWTConfiguration.
 */
@Component("geoGWTGlobalConfiguration")
public class GeoGWTConfiguration implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 5499778990303186671L;

    /** The toolbar item manager. */
    @Autowired
    private IToolbarItemManager toolbarItemManager;

    /**
     * Gets the toolbar item manager.
     *
     * @return the toolbar item manager
     */
    public IToolbarItemManager getToolbarItemManager()
    {
        return toolbarItemManager;
    }

    /**
     * Sets the toolbar item manager.
     *
     * @param toolbarItemManager
     *            the new toolbar item manager
     */
    public void setToolbarItemManager(IToolbarItemManager toolbarItemManager)
    {
        this.toolbarItemManager = toolbarItemManager;
    }

}
