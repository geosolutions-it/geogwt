/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.MapLayoutWidget,v. 0.1 7-apr-2011 16.59.50 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.map;

import it.geosolutions.geogwt.gui.client.configuration.GenericClientTool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.gwtopenmaps.openlayers.client.Bounds;
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
import org.gwtopenmaps.openlayers.client.handler.PolygonHandler;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;

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

    /** The layers. */
    private List<Layer> layers;

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
        this(false);
    }

    /**
     * Instantiates a new map layout widget.
     * 
     * @param isGoogle
     *            the is google
     */
    public MapLayoutWidget(boolean isGoogle) {
        super();
        this.createMapOption(isGoogle);
    }
    
    /**
     * Instantiates a new map layout widget.
     * 
     * @param mapOptions
     *            the map options
     */
    public MapLayoutWidget(MapOptions mapOptions) {
        super();
        this.initMapWidget(mapOptions, false);
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
        this.setMap(mapWidget.getMap());
        // this.map.addControl(new LayerSwitcher());
        if (isGoogle) {
            this.createOSM();
            // this.createBaseGoogleLayer();
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

        if (this.getLayers() == null) {
            this.setLayers(new LinkedList<Layer>());
        }
        this.getLayers().add(OSM.THIS("OpenStreetMap", OpenLayers.getProxyHost()
                + "http://tile.openstreetmap.org/${z}/${x}/${y}.png", osmOption));// OSM.Mapnik("OpenStreetMap",
        // osmOption);
        
        this.getMap().addLayers(this.getLayers().toArray(new Layer[1]));
    }

    /**
     * On add to panel.
     * 
     * @param center
     *            the center
     */
    public void onAddToPanel(ContentPanel center) {
        this.center = center;
        center.add(mapWidget);
        center.layout();

        if (this.getMap() != null && this.getLayers() != null && this.getLayers().size() > 0) {
            // this.map.zoomToExtent(new Bounds(5, 35, 20 ,45));
            // this.map.setCenter(new LonLat(10, 40));
            // this.map.zoomTo(5);
        }
    }
    
    // ////////////////////////////////////////////////////////////////////////
    //
    // Mapping Utility Methods
    //
    // ////////////////////////////////////////////////////////////////////////
    
    /**
     * Update map size.
     */
    public void updateMapSize() {
        this.getMap().updateSize();
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
     * Adds the layer.
     * 
     * @param layer
     *            the layer
     */
    public void addLayer(Layer layer) {
        if (this.map != null) {
            if (this.layers == null) {
                this.layers = new LinkedList<Layer>();
            }
            
            this.layers.add(layer);
            
            this.map.addLayer(layer);
        }
    }

    /**
     * Removes the layer.
     * 
     * @param layer
     *            the layer
     */
    public void removeLayer(Layer layer) {
        if (this.map != null) {
            if (this.layers != null) {
                for (Layer l : this.layers) {
                    if (l.getId().equals(layer.getId()) || 
                            l.getName().equals(layer.getName())) {
                        this.map.removeLayer(l);
                        this.layers.remove(l);
                    }
                }
            }
        }
    }

    /**
     * Creates the default style.
     * 
     * @return the style
     */
    private Style createDefaultStyle() {
        Style style = new Style();
        style.setStrokeColor("#000000");
        style.setStrokeWidth(1);
        style.setFillColor("#FF0000");
        style.setFillOpacity(0.5);
        style.setPointRadius(5);
        style.setStrokeOpacity(1.0);
        return style;
    }
    
    // ////////////////////////////////////////////////////////////////////////
    //
    // Mapping Vectorial Methods
    //
    // ////////////////////////////////////////////////////////////////////////

    /**
     * Inits the vector layer.
     * 
     * @param isGoogle
     *            the is google
     */
    private void initVectorLayer(boolean isGoogle) {
        VectorOptions vectorOption = new VectorOptions();
        vectorOption.setStyle(this.createDefaultStyle());
        vectorOption.setDisplayInLayerSwitcher(false);
        this.vector = new Vector("GeoRepo Vector Layer", vectorOption);
        this.getMap().addLayer(vector);

        // this.initDrawFeatures(isGoogle);
    }

    /**
     * Inits the draw features control.
     * 
     * @param isGoogle
     *            the is google
     */
    private void initDrawFeaturesControl(final boolean isGoogle) {
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

        this.getMap().addControl(this.drawPolygon);
    }
    
    /**
     * Activate draw feature.
     */
    public void activateDrawFeature() {
        if (this.drawPolygon == null) {
            initDrawFeaturesControl(false);
        }
        
        this.drawPolygon.activate();
    }

    /**
     * Deactivate draw feature.
     */
    public void deactivateDrawFeature() {
        if (this.drawPolygon == null) {
            initDrawFeaturesControl(false);
        }
        
        this.drawPolygon.deactivate();
    }

    /**
     * Erase features.
     */
    public void eraseFeatures() {
        this.vector.destroyFeatures();
    }

    /**
     * Draw wkt on map.
     * 
     * @param wkt
     *            the wkt
     */
    public void drawWKTOnMap(String wkt) {
        if (this.vector != null) {
            this.eraseFeatures();
            Geometry geom = Geometry.narrowToGeometry(Geometry.fromWKT(wkt).getJSObject());
            // geom.transform(new Projection("EPSG:4326"), new Projection("EPSG:900913"));
            VectorFeature vectorFeature = new VectorFeature(geom);
            this.vector.addFeature(vectorFeature);
            this.getMap().zoomToExtent(geom.getBounds());
        }
    }
    
    // ////////////////////////////////////////////////////////////////////////
    //
    // Getters and Setters
    //
    // ////////////////////////////////////////////////////////////////////////

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
     * Sets the map.
     * 
     * @param map
     *            the new map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Sets the layer.
     * 
     * @param layers
     *            the new layer
     */
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    /**
     * Gets the layer.
     * 
     * @return the layer
     */
    public List<Layer> getLayers() {
        return layers;
    }

}
