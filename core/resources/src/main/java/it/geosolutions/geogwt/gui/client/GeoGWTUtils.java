/*
 * $ Header: it.geosolutions.geogwt.gui.client.GeoGWTUtils,v. 0.1 7-apr-2011 16.58.10 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client;

import it.geosolutions.geogwt.gui.client.configuration.GeoGWTConfiguration;

import org.springframework.beans.factory.annotation.Autowired;


// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTUtils.
 */
public class GeoGWTUtils
{

    /** The INSTANCE. */
    private static GeoGWTUtils INSTANCE;

    /**
     * Gets the single instance of GeoGWTUtils.
     *
     * @return single instance of GeoGWTUtils
     */
    public static GeoGWTUtils getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new GeoGWTUtils();
        }

        return INSTANCE;
    }

    /** The geo gwt global configuration. */
    @Autowired
    private GeoGWTConfiguration geoGWTGlobalConfiguration;

    /**
     * Gets the global configuration.
     *
     * @return the global configuration
     */
    public GeoGWTConfiguration getGlobalConfiguration()
    {
        return geoGWTGlobalConfiguration;
    }

    /**
     * Sets the global configuration.
     *
     * @param globalConfiguration
     *            the new global configuration
     */
    public void setGlobalConfiguration(GeoGWTConfiguration globalConfiguration)
    {
        this.geoGWTGlobalConfiguration = globalConfiguration;
    }

}
