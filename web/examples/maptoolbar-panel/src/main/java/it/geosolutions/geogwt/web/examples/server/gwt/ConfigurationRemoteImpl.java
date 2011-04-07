/*
 * $ Header: it.geosolutions.geogwt.web.examples.server.gwt.ConfigurationRemoteImpl,v. 0.1 7-apr-2011 17.02.22 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.web.examples.server.gwt;

import it.geosolutions.geogwt.gui.client.service.IGeoGWTConfigurationRemote;
import it.geosolutions.geogwt.gui.service.IGeoGWTStartupService;
import it.geosolutions.geogwt.gui.spring.ApplicationContextUtil;
import it.geosolutions.geogwt.web.examples.client.configuration.GeoGWTGlobalConfiguration;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationRemoteImpl.
 */
public class ConfigurationRemoteImpl extends RemoteServiceServlet implements
        IGeoGWTConfigurationRemote {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6320939080552026131L;

    /** The logger. */
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /** The startup service. */
    private IGeoGWTStartupService startupService;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());

        ApplicationContextUtil.getInstance().setSpringContext(context);

        this.startupService = (IGeoGWTStartupService) ApplicationContextUtil.getInstance().getBean(
                "geogwtStartupService");

        logger.info("SPRING CONTEXT INITIALIZED" + this.startupService);
    }

    /*
     * (non-Javadoc)
     * 
     * @see it.geosolutions.georepo.gui.client.service.ConfigurationRemote#
     * initServerConfiguration()
     */
    public GeoGWTGlobalConfiguration initServerConfiguration() {
        return (GeoGWTGlobalConfiguration) startupService.initServerConfiguration();
    }

}
