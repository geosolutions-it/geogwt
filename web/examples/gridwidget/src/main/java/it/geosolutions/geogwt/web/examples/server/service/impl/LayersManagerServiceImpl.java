/*
 * $ Header: it.geosolutions.geogwt.web.examples.server.service.impl.LayersManagerServiceImpl,v. 0.1 14-apr-2011 10.00.37 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.web.examples.server.service.impl;

import it.geosolutions.geogwt.gui.client.ApplicationException;
import it.geosolutions.geogwt.web.examples.client.model.Layer;
import it.geosolutions.geogwt.web.examples.server.service.ILayersManagerService;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.decoder.RESTLayerList;
import it.geosolutions.geoserver.rest.decoder.utils.NameLinkElem;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

// TODO: Auto-generated Javadoc
/**
 * The Class LayersManagerServiceImpl.
 */
@Component("layersManagerServiceGWT")
public class LayersManagerServiceImpl implements ILayersManagerService {

    /** The logger. */
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /** The geo server url. */
    private String geoServerUrl;
    
    /** The geo server user. */
    private String geoServerUser;
    
    /** The geo server password. */
    private String geoServerPassword;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * it.geosolutions.georepo.gui.server.service.IFeatureService#loadFeature(com.extjs.gxt.ui.
     * client.data.PagingLoadConfig, java.lang.String)
     */
    public PagingLoadResult<Layer> getLayers(PagingLoadConfig config) throws ApplicationException {
        int start = config.getOffset();
        int offset = config.getLimit();
        Long t = 0L;
        
        List<Layer> layersListDTO = new ArrayList<Layer>();

        GeoServerRESTReader gs_reader = null;
        
        try {
            if (getGeoServerUser() != null && getGeoServerPassword() != null) {
                gs_reader = new GeoServerRESTReader(getGeoServerUrl(), getGeoServerUser(), getGeoServerPassword());
            } else {
                gs_reader = new GeoServerRESTReader(getGeoServerUrl());
            }
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        if (gs_reader != null) {
            RESTLayerList gsLayerList = gs_reader.getLayers();
            
            if (gsLayerList != null && !gsLayerList.isEmpty()) {
                t = new Long(gsLayerList.size());
                
                int counter = 0;
                for (NameLinkElem layerLink : gsLayerList) {
                    if (counter < start) {
                        counter++;
                    } else if (counter < start + offset) {
                        counter++;
                        
                        RESTLayer restLayer = gs_reader.getLayer(layerLink.getName());
                        
                        if (restLayer != null) {
                            Layer layer = new Layer();
                            layer.setName(restLayer.getName());
                            layer.setTitle(restLayer.getTitle());
                            layer.setDescription(restLayer.getAbstract());
                            layer.setDefaultStyle(restLayer.getDefaultStyle());
                            layer.setBaseUrl(restLayer.getResourceUrl());

                            layersListDTO.add(layer);
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return new BasePagingLoadResult<Layer>(layersListDTO, config.getOffset(), t.intValue());
    }

    /**
     * Sets the geo server url.
     * 
     * @param geoServerUrl
     *            the new geo server url
     */
    public void setGeoServerUrl(String geoServerUrl) {
        this.geoServerUrl = geoServerUrl;
    }

    /**
     * Gets the geo server url.
     * 
     * @return the geo server url
     */
    public String getGeoServerUrl() {
        return geoServerUrl;
    }

    /**
     * Sets the geo server user.
     * 
     * @param geoServerUser
     *            the new geo server user
     */
    public void setGeoServerUser(String geoServerUser) {
        this.geoServerUser = geoServerUser;
    }

    /**
     * Gets the geo server user.
     * 
     * @return the geo server user
     */
    public String getGeoServerUser() {
        return geoServerUser;
    }

    /**
     * Sets the geo server password.
     * 
     * @param geoServerPassword
     *            the new geo server password
     */
    public void setGeoServerPassword(String geoServerPassword) {
        this.geoServerPassword = geoServerPassword;
    }

    /**
     * Gets the geo server password.
     * 
     * @return the geo server password
     */
    public String getGeoServerPassword() {
        return geoServerPassword;
    }

}
