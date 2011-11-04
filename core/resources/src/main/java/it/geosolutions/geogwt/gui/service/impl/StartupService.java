/*
 * $ Header: it.geosolutions.geogwt.web.examples.service.impl.StartupService,v. 0.1 7-apr-2011 17.02.22 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.service.impl;

import it.geosolutions.geogwt.gui.client.GeoGWTUtils;
import it.geosolutions.geogwt.gui.client.configuration.GeoGWTConfiguration;
import it.geosolutions.geogwt.gui.service.IGeoGWTStartupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// TODO: Auto-generated Javadoc
/**
 * The Class StartupService.
 */
@Component("geogwtStartupService")
public class StartupService extends IGeoGWTStartupService
{

    /** The geo gwt global configuration. */
    @Autowired
    protected GeoGWTConfiguration geoGWTGlobalConfiguration;

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.service.IGeoGWTStartupService#initServerConfiguration()
     */
    public GeoGWTConfiguration initServerConfiguration()
    {
        return this.geoGWTGlobalConfiguration;
    }

    public void afterPropertiesSet() throws Exception
    {
        if (this.geoGWTGlobalConfiguration != null)
        {
            GeoGWTUtils.getInstance().setGlobalConfiguration(this.geoGWTGlobalConfiguration);
        }
    }

}
