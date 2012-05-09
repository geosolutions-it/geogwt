/*
 * $ Header: it.geosolutions.geogwt.gui.client.service.GeoGWTRemoteService,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 18-apr-2012 16.59.50 $
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
package it.geosolutions.geogwt.gui.client.service;

import it.geosolutions.geogwt.gui.client.model.GetFeatureInfoDetails;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * The Interface GeoGWTRemoteService.
 * 
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 * 
 */
public interface GeoGWTRemoteService extends RemoteService {

    /**
     * The Class Util.
     */
    public static class Util {

        /** The instance. */
        private static GeoGWTRemoteServiceAsync instance;

        /**
         * Gets the instance.
         * 
         * @return the instance
         */
        public static GeoGWTRemoteServiceAsync getInstance() {
            if (instance == null) {
                instance = (GeoGWTRemoteServiceAsync) GWT.create(GeoGWTRemoteService.class);

                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint(GWT.getModuleBaseURL() + "GeoGWTRemoteService");
            }

            return instance;
        }
    }

    /**
     * @param wkt
     * @param layersIDs
     * @param crsCode
     * @return GetFeatureInfoDetails
     * @throws Exception
     */
    public GetFeatureInfoDetails getFeatures(String wkt, List<String> layersIDs, String crsCode)
            throws Exception;

    /**
     * @param mapHeight
     * @param mapWidth
     * @param bbox
     * @param layersIDs
     * @param wkt
     * @param x
     * @param y
     * @param crsCode
     * @param resolution
     * @return GetFeatureInfoDetails
     * @throws Exception
     */
    public GetFeatureInfoDetails getFeatureInfo(int mapHeight, int mapWidth, String bbox,
            List<String> layersIDs, String wkt, int x, int y, String crsCode, double resolution) throws Exception;
}
