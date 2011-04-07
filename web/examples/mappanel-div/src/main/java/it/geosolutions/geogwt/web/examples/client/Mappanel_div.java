/*
 * $ Header: it.geosolutions.geogwt.web.examples.client.Mappanel_div,v. 0.1 7-apr-2011 17.00.57 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 17.00.57 $
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
package it.geosolutions.geogwt.web.examples.client;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;

import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapUnits;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class Mappanel_div.
 */
public class Mappanel_div implements EntryPoint {

    /* (non-Javadoc)
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    public void onModuleLoad() {
        GXT.hideLoadingPanel("loading");

        /**
         * Adding widgets to Viewport panels
         */
        ContentPanel mappanel_div = new ContentPanel();
        mappanel_div.setLayout(new FitLayout());
        mappanel_div.setHeaderVisible(true);
        mappanel_div.setHeading("GeoGWT MapPanel Example");
        mappanel_div.addListener(Events.Resize, new Listener<BaseEvent>() {

            public void handleEvent(BaseEvent be) {
                Dispatcher.forwardEvent(GeoGWTEvents.UPDATE_MAP_SIZE);
            }
        });
        mappanel_div.addListener(Events.Move, new Listener<BaseEvent>() {

            public void handleEvent(BaseEvent be) {
                Dispatcher.forwardEvent(GeoGWTEvents.UPDATE_MAP_SIZE);
            }
        });
        mappanel_div.setMonitorWindowResize(true);
        mappanel_div.setLayoutOnChange(true);

        RootPanel.get("mappanel").add(mappanel_div);
        
        // Example initialization with default base layer
        /** Dispatcher.forwardEvent(GeoGWTEvents.INIT_MAPS_UI_MODULE, true); **/

        // Example initialization with map options and a default layer
        /* map options */
        MapOptions mapOptions = new MapOptions();
        mapOptions.setUnits(MapUnits.DEGREES);
        mapOptions.setProjection("EPSG:4326");

        Dispatcher.forwardEvent(GeoGWTEvents.INIT_MAPS_UI_MODULE, mapOptions);
        
        /* base layer */
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("GeoSolutions:ne_shaded");
        wmsParams.setStyles("");

        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);

        WMS layer = new WMS("GeoSolutions Natural Earth", "http://demo1.geo-solutions.it/playground/wms", wmsParams, wmsLayerParams);
        Dispatcher.forwardEvent(GeoGWTEvents.ADD_LAYER, layer);

        
        // Attaching the map to the panel
        Dispatcher.forwardEvent(GeoGWTEvents.ATTACH_MAP_WIDGET, mappanel_div);

        // Adjusting the Zoom level
        // Dispatcher.forwardEvent(GeoGWTEvents.ZOOM_TO_MAX_EXTENT);
        Dispatcher.forwardEvent(GeoGWTEvents.SET_MAP_CENTER, new Double[] {13.0, 42.0});
        Dispatcher.forwardEvent(GeoGWTEvents.ZOOM, 5);
        
        /**
         * Must be done after the dispatch...
         */
        mappanel_div.setWidth(558);
        mappanel_div.setHeight(333);

    }
}
