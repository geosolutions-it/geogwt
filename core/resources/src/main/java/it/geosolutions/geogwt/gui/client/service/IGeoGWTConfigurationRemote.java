/*
 * $ Header: it.geosolutions.georepo.gui.client.service.ConfigurationRemote,v. 0.1 14-gen-2011 19.27.37 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 14-gen-2011 19.27.37 $
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
package it.geosolutions.geogwt.gui.client.service;

import it.geosolutions.geogwt.gui.client.configuration.IGeoGWTConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConfigurationRemote.
 */
public interface IGeoGWTConfigurationRemote extends RemoteService {

    /**
     * The Class Util.
     */
    public static class Util {

        /** The instance. */
        private static IGeoGWTConfigurationRemoteAsync instance;

        /**
         * Gets the instance.
         * 
         * @return the instance
         */
        public static IGeoGWTConfigurationRemoteAsync getInstance() {
            if (instance == null) {
                instance = (IGeoGWTConfigurationRemoteAsync) GWT.create(IGeoGWTConfigurationRemote.class);
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint(GWT.getModuleBaseURL() + "ConfigurationRemote");
            }
            return instance;
        }
    }

    /**
     * Inits the server configuration.
     * 
     * @return the geo repo global configuration
     */
    public IGeoGWTConfiguration initServerConfiguration();

}
