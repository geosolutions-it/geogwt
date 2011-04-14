/*
 * $ Header: it.geosolutions.geogwt.web.examples.client.service.LayersManagerServiceRemote,v. 0.1 14-apr-2011 10.00.37 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 14-apr-2011 10.00.37 $
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
package it.geosolutions.geogwt.web.examples.client.service;

import it.geosolutions.geogwt.gui.client.ApplicationException;
import it.geosolutions.geogwt.web.examples.client.model.Layer;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

// TODO: Auto-generated Javadoc
/**
 * The Interface LayersManagerServiceRemote.
 */
public interface LayersManagerServiceRemote extends RemoteService {

    /**
     * The Class Util.
     */
    public static class Util {

        /** The instance. */
        private static LayersManagerServiceRemoteAsync instance;

        /**
         * Gets the instance.
         * 
         * @return the instance
         */
        public static LayersManagerServiceRemoteAsync getInstance() {
            if (instance == null) {
                instance = (LayersManagerServiceRemoteAsync) GWT.create(LayersManagerServiceRemote.class);
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint(GWT.getModuleBaseURL() + "LayersManagerServiceRemote");
            }
            return instance;
        }
    }

    /**
     * Gets the layers.
     * 
     * @param config
     *            the config
     * @return the layers
     * @throws ApplicationException
     *             the application exception
     */
    public PagingLoadResult<Layer> getLayers(PagingLoadConfig config) throws ApplicationException;
    
}
