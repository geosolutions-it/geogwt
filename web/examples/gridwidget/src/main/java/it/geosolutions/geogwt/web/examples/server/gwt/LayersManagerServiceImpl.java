/*
 * $ Header: it.geosolutions.geogwt.web.examples.server.gwt.LayersManagerServiceImpl,v. 0.1 14-apr-2011 10.00.37 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.web.examples.server.gwt;

import it.geosolutions.geogwt.gui.client.ApplicationException;
import it.geosolutions.geogwt.gui.spring.ApplicationContextUtil;
import it.geosolutions.geogwt.web.examples.client.model.Layer;
import it.geosolutions.geogwt.web.examples.client.service.LayersManagerServiceRemote;
import it.geosolutions.geogwt.web.examples.server.service.ILayersManagerService;

import java.util.logging.Logger;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class LayersManagerServiceImpl.
 */
public class LayersManagerServiceImpl extends RemoteServiceServlet implements LayersManagerServiceRemote {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8463230843282973229L;

    /** The logger. */
    @SuppressWarnings("unused")
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /** The layers manager service. */
    private ILayersManagerService layersManagerService;

    /**
     * Instantiates a new layers manager service impl.
     */
    public LayersManagerServiceImpl() {
        this.layersManagerService = (ILayersManagerService) ApplicationContextUtil.getInstance().getBean(
                "layersManagerServiceGWT");
    }

    /* (non-Javadoc)
     * @see it.geosolutions.georepo.gui.client.service.GsUsersManagerServiceRemote#getGsUsers(com.extjs.gxt.ui.client.data.PagingLoadConfig)
     */
    public PagingLoadResult<Layer> getLayers(PagingLoadConfig config) throws ApplicationException {
        return layersManagerService.getLayers(config);
    }

}
