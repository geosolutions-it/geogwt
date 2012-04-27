/*
 * $ Header: it.geosolutions.geogwt.gui.server.gwt.GeoGWTRemoteServiceImpl,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.server.gwt;

import it.geosolutions.geogwt.gui.client.model.GetFeatureInfoDetails;
import it.geosolutions.geogwt.gui.client.service.GeoGWTRemoteService;
import it.geosolutions.geogwt.gui.service.IGeoGWTRemoteService;
import it.geosolutions.geogwt.gui.spring.ApplicationContextUtil;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The Class GeoGWTRemoteServiceImpl.
 * 
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 * 
 */
public class GeoGWTRemoteServiceImpl extends RemoteServiceServlet implements GeoGWTRemoteService {

    /** serialVersionUID */
    private static final long serialVersionUID = 3663716719924012867L;

    /** The logger. */
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private IGeoGWTRemoteService geoGWTRemoteService;

    /**
     * Instantiates a new remote service implementation.
     * 
     * @throws ServletException
     */
    public GeoGWTRemoteServiceImpl() throws ServletException {
        this.geoGWTRemoteService = (IGeoGWTRemoteService) ApplicationContextUtil.getInstance()
                .getBean("geoGWTRemoteService");

        logger.info("SPRING CONTEXT INITIALIZED" + this.geoGWTRemoteService);
    }

    /**
     * @param wkt
     * @param layersIDs
     * @param crsCode
     * @return GetFeatureInfoDetails
     * @throws IOException
     */
    public GetFeatureInfoDetails getFeatures(String wkt, List<String> layersIDs, String crsCode)
            throws Exception {
        return geoGWTRemoteService.getFeatures(wkt, layersIDs, crsCode);
    }

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
            List<String> layersIDs, String wkt, int x, int y, String crsCode, double resolution)
            throws Exception {
        return geoGWTRemoteService.getFeatureInfo(mapHeight, mapWidth, bbox, layersIDs, wkt, x, y,
                crsCode, resolution);
    }
}
