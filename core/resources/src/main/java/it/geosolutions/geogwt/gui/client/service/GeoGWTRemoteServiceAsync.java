/*
 * $ Header: it.geosolutions.geogwt.gui.client.service.GeoGWTRemoteServiceAsync,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The Interface GeoGWTRemoteServiceAsync.
 * 
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 * 
 */
public interface GeoGWTRemoteServiceAsync {

    /**
     * GWT-RPC service asynchronous (client-side) interface.
     * 
     * @param wkt
     * @param layersIDs
     * @param crsCode
     * @param callback
     */
    public void getFeatures(String wkt, List<String> layersIDs, String crsCode,
            AsyncCallback<GetFeatureInfoDetails> callback);

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
    public void getFeatureInfo(int mapHeight, int mapWidth, String bbox, List<String> layersIDs,
            String wkt, int x, int y, String crsCode, double resolution,
            AsyncCallback<GetFeatureInfoDetails> callback);
}
