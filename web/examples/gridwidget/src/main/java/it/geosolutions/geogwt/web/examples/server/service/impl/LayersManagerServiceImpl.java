/*
 * $ Header: it.geosolutions.georepo.gui.server.service.impl.GsUsersManagerServiceImpl,v. 0.1 10-feb-2011 11.10.03 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 10-feb-2011 11.10.03 $
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

    private String geoServerUrl;
    
    private String geoServerUser;
    
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
     * @param geoServerUrl the geoServerUrl to set
     */
    public void setGeoServerUrl(String geoServerUrl) {
        this.geoServerUrl = geoServerUrl;
    }

    /**
     * @return the geoServerUrl
     */
    public String getGeoServerUrl() {
        return geoServerUrl;
    }

    /**
     * @param geoServerUser the geoServerUser to set
     */
    public void setGeoServerUser(String geoServerUser) {
        this.geoServerUser = geoServerUser;
    }

    /**
     * @return the geoServerUser
     */
    public String getGeoServerUser() {
        return geoServerUser;
    }

    /**
     * @param geoServerPassword the geoServerPassword to set
     */
    public void setGeoServerPassword(String geoServerPassword) {
        this.geoServerPassword = geoServerPassword;
    }

    /**
     * @return the geoServerPassword
     */
    public String getGeoServerPassword() {
        return geoServerPassword;
    }

}
