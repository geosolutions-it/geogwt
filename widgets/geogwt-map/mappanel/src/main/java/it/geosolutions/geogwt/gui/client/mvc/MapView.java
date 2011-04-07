/*
 * $ Header: it.geosolutions.geogwt.gui.client.mvc.MapView,v. 0.1 25-gen-2011 11.30.32 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.30.32 $
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
package it.geosolutions.geogwt.gui.client.mvc;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.widget.map.ButtonBar;
import it.geosolutions.geogwt.gui.client.widget.map.MapLayoutWidget;

import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class MapView.
 */
public class MapView extends View {

    /** The map layout. */
    private MapLayoutWidget mapLayout;

    /**
     * Instantiates a new map view.
     * 
     * @param controller
     *            the controller
     */
    public MapView(Controller controller) {
        super(controller);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.mvc.View#handleEvent(com.extjs.gxt.ui.client. mvc.AppEvent)
     */
    @Override
    protected void handleEvent(AppEvent event) {

        /** Mapping Initialization Events **/
        
        // this should be called first in order to set custom properties for the map
        if (event.getType() == GeoGWTEvents.INIT_MAPS_UI_MODULE) {
            if (event.getData() instanceof Boolean) {
                this.mapLayout = new MapLayoutWidget((Boolean) event.getData());
            } else if (event.getData() instanceof MapOptions) {
                this.mapLayout = new MapLayoutWidget((MapOptions) event.getData());
            }
        }

        if (event.getType() == GeoGWTEvents.ATTACH_MAP_WIDGET) {
            if (this.mapLayout == null) {
                this.mapLayout = new MapLayoutWidget();
            }
            
            this.mapLayout.onAddToPanel((ContentPanel) event.getData());
        }

        /** Mapping Options Events **/
        
        if (event.getType() == GeoGWTEvents.UPDATE_MAP_SIZE) {
            this.mapLayout.updateMapSize();
        }

         if (event.getType() == GeoGWTEvents.INIT_TOOLBAR) {
             onInitToolbar(event);
         }

        /** Mapping Feature Handling Events **/
        if (event.getType() == GeoGWTEvents.DRAW_WKT_ON_MAP) {
            onDrawWKTOnMap(event);
        }

        if (event.getType() == GeoGWTEvents.ACTIVATE_DRAW_FEATURES) {
            onActivateDrawFeature();
        }

        if (event.getType() == GeoGWTEvents.DEACTIVATE_DRAW_FEATURES) {
            onDeactivateDrawFeature();
        }

        if (event.getType() == GeoGWTEvents.ERASE_FEATURES) {
            onEraseAOIFeatures();
        }

        if (event.getType() == GeoGWTEvents.ENABLE_DRAW_BUTTON) {
            onEnableDrawButton();
        }

        if (event.getType() == GeoGWTEvents.DISABLE_DRAW_BUTTON) {
            onDisableDrawButton();
        }

        /** Mapping Utilities Events **/

        if (event.getType() == GeoGWTEvents.ADD_LAYER) {
            onAddLayerToMap((Layer) event.getData());
        }
        
        if (event.getType() == GeoGWTEvents.REMOVE_LAYER) {
            onRemoveLayerFromMap((Layer) event.getData());
        }

        if (event.getType() == GeoGWTEvents.ZOOM_TO_MAX_EXTENT) {
            onZoomToMaxExtent();
        }
        
        if (event.getType() == GeoGWTEvents.ZOOM_TO_CENTER) {
            onZoomToCenter();
        }

        if (event.getType() == GeoGWTEvents.ZOOM) {
            onZoom((Integer) event.getData());
        }
        
        if (event.getType() == GeoGWTEvents.SET_MAP_CENTER) {
            onSetMapCenter((Double[]) event.getData());
        }

    }

    // ////////////////////////////////////////////////////////////////////////
    //
    // Methods Implementations
    //
    // ////////////////////////////////////////////////////////////////////////

    private void onInitToolbar(AppEvent event) {
        ButtonBar buttonBar = (ButtonBar) event.getData();
        buttonBar.setMapLayoutWidget(this.mapLayout);
    }

    /**
     * Adding a Layer to the Map
     * 
     * @param layer
     */
    private void onAddLayerToMap(Layer layer) {
        this.mapLayout.addLayer(layer);
    }

    /**
     * Removing Layer from the map
     * 
     * @param layer
     */
    private void onRemoveLayerFromMap(Layer layer) {
        this.mapLayout.removeLayer(layer);
    }

    /**
     * Sets the center of the map.
     * Accepts a Double array representing the Longitude and the Latitude of the center point
     * 
     * @param centerCoords
     */
    private void onSetMapCenter(Double[] centerCoords) {
        LonLat lonlat = new LonLat(centerCoords[0], centerCoords[1]);
        this.mapLayout.getMap().setCenter(lonlat);
    }

    /**
     * Zooming to Max Extent
     */
    private void onZoomToMaxExtent() {
        this.mapLayout.getMap().zoomToMaxExtent();
    }

    /**
     * Sets the Zoom level of the map 
     */
    private void onZoom(Integer zoomLevel) {
        this.mapLayout.getMap().zoomTo(zoomLevel);
    }

    /**
     * On zoom to center.
     */
    private void onZoomToCenter() {
        LonLat center = this.mapLayout.getMap().getCenter();
        this.mapLayout.getMap().setCenter(center, 3);
    }

    /**
     * On disable draw button.
     */
    private void onDisableDrawButton() {
        this.mapLayout.deactivateDrawFeature();
    }

    /**
     * On enable draw button.
     */
    private void onEnableDrawButton() {
        this.mapLayout.activateDrawFeature();
    }

    /**
     * On erase aoi features.
     */
    private void onEraseAOIFeatures() {
        this.mapLayout.eraseFeatures();
    }

    /**
     * On draw aoi on map.
     * 
     * @param event
     *            the event
     */
    private void onDrawWKTOnMap(AppEvent event) {
        this.mapLayout.drawWKTOnMap((String) event.getData());
        // Dispatcher.forwardEvent(GeoRepoEvents.SEND_INFO_MESSAGE, new String[] { "AOI Service",
        // "Zoom to selected AOI." });
    }

    /**
     * On activate draw feature.
     */
    private void onActivateDrawFeature() {
        this.mapLayout.activateDrawFeature();
    }

    /**
     * On deactivate draw feature.
     */
    private void onDeactivateDrawFeature() {
        this.mapLayout.deactivateDrawFeature();
    }

}
