/*
 * $ Header: it.geosolutions.geogwt.web.examples.client.configuration.GeoGWTGlobalConfiguration,v. 0.1 7-apr-2011 17.02.22 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 17.02.22 $
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
package it.geosolutions.geogwt.web.examples.client.configuration;

import it.geosolutions.geogwt.gui.client.configuration.IGeoGWTConfiguration;
import it.geosolutions.geogwt.gui.client.configuration.IToolbarItemManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTGlobalConfiguration.
 */
@Component("geoGWTGlobalConfiguration")
public class GeoGWTGlobalConfiguration implements IGeoGWTConfiguration {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3377836318526396981L;

    /** The toolbar item manager. */
    @Autowired
    private IToolbarItemManager toolbarItemManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * it.geosolutions.georepo.gui.client.configuration.GeoRepoConfiguration#getToolbarItemManager
     * ()
     */
    public IToolbarItemManager getToolbarItemManager() {
        return toolbarItemManager;
    }

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.client.configuration.IGeoGWTConfiguration#setToolbarItemManager(it.geosolutions.geogwt.gui.client.configuration.IToolbarItemManager)
     */
    public void setToolbarItemManager(IToolbarItemManager toolbarItemManager) {
        this.toolbarItemManager = toolbarItemManager;
    }

}
