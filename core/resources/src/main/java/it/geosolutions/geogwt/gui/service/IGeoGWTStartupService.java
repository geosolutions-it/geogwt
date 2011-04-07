/*
 * $ Header: it.geosolutions.geogwt.gui.service.IGeoGWTStartupService,v. 0.1 7-apr-2011 16.58.11 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.service;

import it.geosolutions.geogwt.gui.client.configuration.GeoGWTConfiguration;

import org.springframework.beans.factory.InitializingBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGeoGWTStartupService.
 */
public abstract class IGeoGWTStartupService implements InitializingBean {

    /**
     * Inits the server configuration.
     * 
     * @return the i geo gwt configuration
     */
    public abstract GeoGWTConfiguration initServerConfiguration();

}
