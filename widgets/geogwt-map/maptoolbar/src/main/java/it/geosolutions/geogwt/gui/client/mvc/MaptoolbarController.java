/*
 * $ Header: it.geosolutions.geogwt.gui.client.mvc.MaptoolbarController,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 17.01.44 $
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
package it.geosolutions.geogwt.gui.client.mvc;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class MaptoolbarController.
 * 
 * @author Alessio Fabiani at alessio.fabiani@@geo-solutions.it
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class MaptoolbarController extends Controller {

    /** The maptoolbar view. */
    private MaptoolbarView maptoolbarView;

    /**
     * Instantiates a new maptoolbar controller.
     */
    public MaptoolbarController() {
        registerEventTypes(
                GeoGWTEvents.INIT_MAPS_UI_MODULE, 
                
                GeoGWTEvents.ATTACH_TOOLBAR,
                GeoGWTEvents.TOGGLE_BUTTON_PRESSED,
                
                GeoGWTEvents.ENABLE_DRAW_BUTTON, 
                GeoGWTEvents.DISABLE_DRAW_BUTTON,
                
                GeoGWTEvents.ACTIVATE_BOX_SELECT, 
                GeoGWTEvents.DEACTIVATE_BOX_SELECT,
                GeoGWTEvents.BOUNDS_SELECTED,
                
                GeoGWTEvents.POINT_SELECTED,
                
                GeoGWTEvents.ACTIVATE_POINT_SELECT,
                GeoGWTEvents.DEACTIVATE_POINT_SELECT,
                
                GeoGWTEvents.ACTIVATE_GET_FEATURE_INFO,
                GeoGWTEvents.DEACTIVATE_GET_FEATURE_INFO,
                
                GeoGWTEvents.GOT_FEATURE_INFO
                
        );
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.mvc.Controller#initialize()
     */
    @Override
    public void initialize() {
        this.maptoolbarView = new MaptoolbarView(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.mvc.Controller#handleEvent(com.extjs.gxt.ui.client
     * .mvc.AppEvent)
     */
    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(maptoolbarView, event);
    }

}
