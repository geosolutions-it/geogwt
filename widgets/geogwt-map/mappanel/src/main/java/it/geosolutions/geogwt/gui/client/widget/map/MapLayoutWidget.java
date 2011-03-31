/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.MapLayoutWidget,v. 0.1 25-gen-2011 11.30.33 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.30.33 $
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
package it.geosolutions.geogwt.gui.client.widget.map;

import it.geosolutions.geogwt.gui.client.configuration.GenericClientTool;

import java.util.Collections;
import java.util.List;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapUnits;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.OpenLayers;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.DrawFeatureOptions;
import org.gwtopenmaps.openlayers.client.control.DrawFeature.FeatureAddedListener;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.MultiPolygon;
import org.gwtopenmaps.openlayers.client.handler.PolygonHandler;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class MapLayoutWidget.
 */
public class MapLayoutWidget extends LayoutContainer {

    /** The map widget. */
    private MapWidget mapWidget;

    /** The default map options. */
    private MapOptions defaultMapOptions;

    /** The map. */
    private Map map;

    /** The layer. */
    private Layer layer;

    /** The osm. */
    private Layer osm;

    /** The center. */
    private ContentPanel center;

    /** The tools. */
    private List<GenericClientTool> tools;

    /** The draw polygon. */
    private DrawFeature drawPolygon;

    /** The vector. */
    private Vector vector;

    /**
     * Instantiates a new map layout widget.
     */
    public MapLayoutWidget() {
        super();
        this.createMapOption(false);
    }

    /**
     * Creates the map option.
     * 
     * @param isGoogle
     *            the is google
     */
    private void createMapOption(boolean isGoogle) {

        OpenLayers.setProxyHost("gwtOpenLayersProxy?targetURL=");
        this.defaultMapOptions = new MapOptions();
        this.defaultMapOptions.setNumZoomLevels(18);
        if (isGoogle) {
            this.defaultMapOptions.setProjection("EPSG:900913");
            this.defaultMapOptions.setDisplayProjection(new Projection("EPSG:4326"));
            this.defaultMapOptions.setUnits(MapUnits.METERS);
            this.defaultMapOptions.setMaxExtent(new Bounds(-20037508, -20037508, 20037508,
                    20037508.34));
            this.defaultMapOptions.setMaxResolution(new Double(156543.0339).floatValue());
        } else {
        	this.defaultMapOptions.setUnits(MapUnits.DEGREES);
            this.defaultMapOptions.setProjection("EPSG:4326");
        }

        initMapWidget(this.defaultMapOptions, isGoogle);

    }

    /**
     * Inits the map widget.
     * 
     * @param defaultMapOptions
     *            the default map options
     * @param isGoogle
     *            the is google
     */
    private void initMapWidget(MapOptions defaultMapOptions, boolean isGoogle) {
        mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
        this.map = mapWidget.getMap();
        // this.map.addControl(new LayerSwitcher());
        if (isGoogle) {
            this.createOSM();
            // this.createBaseGoogleLayer();
        } else {
            WMSParams wmsParams = new WMSParams();
            wmsParams.setFormat("image/jpeg");
            wmsParams.setLayers("world.topo.bathy.2004");
            wmsParams.setStyles("");

            WMSOptions wmsLayerParams = new WMSOptions();
            wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);
            wmsLayerParams.setBuffer(0);

//            layer = new WMS("Basic WMS", "http://labs.metacarta.com/wms/vmap0", wmsParams,
//                    wmsLayerParams);
            
            layer = new WMS("Blue Marble NG topographic & bathymetric shading", "http://demo1.geo-solutions.it/playground/wms",
            		wmsParams, wmsLayerParams);            
                        
            this.map.addLayer(layer);
        }

        this.initVectorLayer(isGoogle);
    }

    /**
     * Creates the osm.
     */
    private void createOSM() {
        OSMOptions osmOption = new OSMOptions();
        // osmOption.setDisplayOutsideMaxExtent(true);
        // osmOption.setWrapDateLine(true);

        this.osm = OSM.THIS("OpenStreetMap", OpenLayers.getProxyHost()
                + "http://tile.openstreetmap.org/${z}/${x}/${y}.png", osmOption);// OSM.Mapnik("OpenStreetMap",
        // osmOption);
        this.map.addLayer(osm);
    }

    /**
     * Inits the vector layer.
     * 
     * @param isGoogle
     *            the is google
     */
    private void initVectorLayer(boolean isGoogle) {
        VectorOptions vectorOption = new VectorOptions();
        vectorOption.setStyle(this.createStyle());
        vectorOption.setDisplayInLayerSwitcher(false);
        this.vector = new Vector("GeoRepo Vector Layer", vectorOption);
        this.map.addLayer(vector);

        //this.initDrawFeatures(isGoogle);
    }

    /**
     * Inits the draw features.
     * 
     * @param isGoogle
     *            the is google
     */
    private void initDrawFeatures(final boolean isGoogle) {
        FeatureAddedListener listener = new FeatureAddedListener() {
            public void onFeatureAdded(VectorFeature vf) {
                org.gwtopenmaps.openlayers.client.geometry.Polygon aoi = org.gwtopenmaps.openlayers.client.geometry.Polygon
                        .narrowToPolygon(vf.getGeometry().getJSObject());

                if (isGoogle)
                    aoi.transform(new Projection("EPSG:900913"), new Projection("EPSG:4326"));

                // Dispatcher.forwardEvent(GeoGWTEvents.INJECT_WKT, aoi.toString());

                // Dispatcher.forwardEvent(GeoGWTEvents.DISABLE_DRAW_BUTTON);
            }
        };

        DrawFeatureOptions drawPolygonFeatureOptions = new DrawFeatureOptions();
        drawPolygonFeatureOptions.onFeatureAdded(listener);

        this.drawPolygon = new DrawFeature(vector, new PolygonHandler(), drawPolygonFeatureOptions);

        this.map.addControl(this.drawPolygon);
    }

    /**
     * Creates the style.
     * 
     * @return the style
     */
    private Style createStyle() {
        Style style = new Style();
        style.setStrokeColor("#000000");
        style.setStrokeWidth(1);
        style.setFillColor("#FF0000");
        style.setFillOpacity(0.5);
        style.setPointRadius(5);
        style.setStrokeOpacity(1.0);
        return style;
    }

    /**
     * On add to center panel.
     * 
     * @param center
     *            the center
     */
    public void onAddToPanel(ContentPanel center) {
        this.center = center;
        center.add(mapWidget);
        center.layout();

        //this.map.zoomToExtent(new Bounds(5, 35, 20 ,45));
        this.map.setCenter(new LonLat(10, 40), 5);
    }

    /**
     * Draw aoi on map.
     * 
     * @param wkt
     *            the wkt
     */
    public void drawAoiOnMap(String wkt) {
        this.eraseFeatures();
        Geometry geom = Geometry.narrowToGeometry(Geometry.fromWKT(wkt).getJSObject());
        //geom.transform(new Projection("EPSG:4326"), new Projection("EPSG:900913"));
        VectorFeature vectorFeature = new VectorFeature(geom);
        this.vector.addFeature(vectorFeature);
        this.map.zoomToExtent(geom.getBounds());
    }

    /**
     * Gets the map.
     * 
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Gets the map widget.
     * 
     * @return the map widget
     */
    public MapWidget getMapWidget() {
        return mapWidget;
    }

    /**
     * Update map size.
     */
    public void updateMapSize() {
        this.map.updateSize();
        this.center.layout();
    }

    // private void createBaseGoogleLayer() {
    // GoogleOptions option = new GoogleOptions();
    // option.setType(GMapType.G_NORMAL_MAP);
    // option.setSphericalMercator(true);
    //
    // layer = new Google("Google Normal", option);
    // this.map.addLayer(layer);
    // }

    /**
     * Erase features.
     */
    public void eraseFeatures() {
        this.vector.destroyFeatures();
    }

    /**
     * Gets the tools.
     * 
     * @return the tools
     */
    public List<GenericClientTool> getTools() {
        return tools;
    }

    /**
     * Sets the tools.
     * 
     * @param tools
     *            the new tools
     */
    public void setTools(List<GenericClientTool> tools) {
        Collections.sort(tools);
        this.tools = tools;
    }

    /**
     * Activate draw feature.
     */
    public void activateDrawFeature() {
        this.drawPolygon.activate();
    }

    /**
     * Deactivate draw feature.
     */
    public void deactivateDrawFeature() {
        this.drawPolygon.deactivate();
    }

}
