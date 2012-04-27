/*
 * $ Header: it.geosolutions.geogwt.gui.client.mvc.MapController,v. 0.1 7-apr-2011 16.59.50 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.59.50 $
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

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;


// TODO: Auto-generated Javadoc
/**
 * The Class MapController.
 * 
 * @author Alessio Fabiani at alessio.fabiani@@geo-solutions.it
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class MapController extends Controller
{

    /** The map view. */
    private MapView mapView;

    /**
     * Instantiates a new map controller.
     */
    public MapController()
    {
        registerEventTypes(
            GeoGWTEvents.INIT_MAPS_UI_MODULE,
            GeoGWTEvents.ATTACH_MAP_CONTROL,
            GeoGWTEvents.ATTACH_MAP_WIDGET,




            GeoGWTEvents.UPDATE_MAP_SIZE,




            GeoGWTEvents.INIT_TOOLBAR,
            GeoGWTEvents.ATTACH_TOOLBAR,




            GeoGWTEvents.ACTIVATE_DRAW_FEATURES,
            GeoGWTEvents.DEACTIVATE_DRAW_FEATURES,
            GeoGWTEvents.DRAW_WKT_ON_MAP,
            GeoGWTEvents.ERASE_FEATURES,
            GeoGWTEvents.ENABLE_DRAW_BUTTON,
            GeoGWTEvents.DISABLE_DRAW_BUTTON,




            GeoGWTEvents.ACTIVATE_BOX_SELECT,
            GeoGWTEvents.DEACTIVATE_BOX_SELECT,




            GeoGWTEvents.ACTIVATE_ZOOM_BOX,
            GeoGWTEvents.DEACTIVATE_ZOOM_BOX,




            GeoGWTEvents.ADD_LAYER,
            GeoGWTEvents.REMOVE_LAYER,
            GeoGWTEvents.ZOOM_TO_EXTENT,
            GeoGWTEvents.ZOOM_TO_MAX_EXTENT,
            GeoGWTEvents.ZOOM_TO_CENTER,
            GeoGWTEvents.ZOOM,
            GeoGWTEvents.SET_MAP_CENTER,
            
            GeoGWTEvents.POINT_SELECTED,
            
            GeoGWTEvents.ACTIVATE_POINT_SELECT,
            GeoGWTEvents.DEACTIVATE_POINT_SELECT, 
            
            GeoGWTEvents.ACTIVATE_GET_FEATURE_INFO,
            GeoGWTEvents.DEACTIVATE_GET_FEATURE_INFO,
            
            GeoGWTEvents.GOT_FEATURE_INFO);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.extjs.gxt.ui.client.mvc.Controller#initialize()
     */
    @Override
    public void initialize()
    {
        this.mapView = new MapView(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.extjs.gxt.ui.client.mvc.Controller#handleEvent(com.extjs.gxt.ui.client
     * .mvc.AppEvent)
     */
    @Override
    public void handleEvent(AppEvent event)
    {
        forwardToView(mapView, event);
    }

}
