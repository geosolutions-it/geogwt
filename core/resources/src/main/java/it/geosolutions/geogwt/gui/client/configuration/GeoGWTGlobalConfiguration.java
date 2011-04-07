/*
 * $ Header: it.geosolutions.georepo.gui.client.configuration.GeoRepoGlobalConfiguration,v. 0.1 14-gen-2011 19.27.01 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 14-gen-2011 19.27.01 $
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTGlobalConfiguration.
 */
@Component("georepoGlobalConfiguration")
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

}
