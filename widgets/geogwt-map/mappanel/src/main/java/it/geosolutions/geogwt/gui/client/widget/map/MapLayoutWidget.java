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

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.configuration.GenericClientTool;
import it.geosolutions.geogwt.gui.client.model.GetFeatureInfoDetails;
import it.geosolutions.geogwt.gui.client.model.PointSelectDetails;
import it.geosolutions.geogwt.gui.client.service.GeoGWTRemoteService;
import it.geosolutions.geogwt.gui.client.widget.map.ol.control.BoxSelect;
import it.geosolutions.geogwt.gui.client.widget.map.ol.control.BoxSelect.BoxSelectListener;
import it.geosolutions.geogwt.gui.client.widget.map.ol.control.BoxSelectOptions;
import it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelect;
import it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelect.PointSelectListener;
import it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelectOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapUnits;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.OpenLayers;
import org.gwtopenmaps.openlayers.client.Pixel;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.Style;
import org.gwtopenmaps.openlayers.client.control.Control;
import org.gwtopenmaps.openlayers.client.control.DrawFeature;
import org.gwtopenmaps.openlayers.client.control.DrawFeature.FeatureAddedListener;
import org.gwtopenmaps.openlayers.client.control.DrawFeatureOptions;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.geometry.Point;
import org.gwtopenmaps.openlayers.client.handler.PolygonHandler;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.VectorOptions;
import org.gwtopenmaps.openlayers.client.layer.WMS;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The Class MapLayoutWidget.
 * 
 * @author Alessio Fabiani at alessio.fabiani@@geo-solutions.it
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 * 
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

    /** The layers IDs */
    private List<String> layersIDs = null;

    /** The controls **/
    private List<Control> controls;

    /** The center. */
    private ContentPanel center;

    /** The tools. */
    private List<GenericClientTool> tools;

    /** The draw polygon. */
    private DrawFeature drawPolygon;

    /** **/
    private BoxSelect boxSelect;

    /** **/
    private PointSelect pointSelect;

    private boolean featureInfoActivated = false;

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
     * @param isGoogle the is google
     */
    public MapLayoutWidget(boolean isGoogle) {
        super();
        this.createMapOption(isGoogle);
    }

    /**
     * Instantiates a new map layout widget.
     * 
     * @param mapOptions the map options
     */
    public MapLayoutWidget(MapOptions mapOptions) {
        super();
        this.initMapWidget(mapOptions, false);
    }

    /**
     * Instantiates a new map layout widget.
     * 
     * @param mapConfig the map configs
     */
    public MapLayoutWidget(MapConfig mapConfig) {
        super();
        this.layersIDs = mapConfig.getLayersIDs();
        this.initMapWidget(mapConfig.getMapOptions(), false);
    }

    /**
     * Creates the map option.
     * 
     * @param isGoogle the is google
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
     * @param defaultMapOptions the default map options
     * @param isGoogle the is google
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
        this.getLayers().add(
                OSM.THIS("OpenStreetMap", OpenLayers.getProxyHost()
                        + "http://tile.openstreetmap.org/${z}/${x}/${y}.png", osmOption)); // OSM.Mapnik("OpenStreetMap",
        // osmOption);

        this.getMap().addLayers(this.getLayers().toArray(new Layer[1]));
    }

    /**
     * On add to panel.
     * 
     * @param center the center
     */
    public void onAddToPanel(ContentPanel center) {
        this.center = center;
        center.add(mapWidget);
        center.layout();

        if ((this.getMap() != null) && (this.getLayers() != null) && (this.getLayers().size() > 0)) {
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
     * @param layer the layer
     */
    public void addLayer(Layer layer) {
        if (this.map != null) {
            if (this.layers == null) {
                this.layers = new LinkedList<Layer>();
            }

            boolean present = false;
            for (Layer l : this.layers) {
                if (l.getId().equals(layer.getId()) || l.getName().equals(layer.getName())) {
                    present = true;

                    break;
                }
            }

            if (!present) {
                this.layers.add(layer);
                this.map.addLayers(new Layer[] { layer });
            }
        }
    }

    /**
     * Removes the layer.
     * 
     * @param layer the layer
     */
    public void removeLayer(Layer layer) {
        if (this.map != null) {
            if (this.layers != null) {
                for (Layer l : this.layers) {
                    if (l.getId().equals(layer.getId()) || l.getName().equals(layer.getName())) {
                        this.map.removeLayer(l);
                        this.layers.remove(l);

                        break;
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
     * @param isGoogle the is google
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
     * 
     * @param isGoogle the is google
     */
    private void initBoxSelectControl(final boolean isGoogle) {
        BoxSelectListener listener = new BoxSelectListener() {
            public void onBoxSelected(Bounds bounds) {

                if (!Double.valueOf(bounds.getLowerLeftX()).isNaN()
                        && !Double.valueOf(bounds.getLowerLeftY()).isNaN()
                        && !Double.valueOf(bounds.getUpperRightX()).isNaN()
                        && !Double.valueOf(bounds.getUpperRightY()).isNaN()) {

                    // MessageBox.alert("", bounds.toBBox(4).toString(), null);

                    if (isGoogle) {
                        bounds.transform(new Projection("EPSG:900913"), new Projection("EPSG:4326"));
                    }

                    List<String> layersToQuery = getQueryLayerList();

                    String crsCode = map.getProjection();
                    GeoGWTRemoteService.Util.getInstance().getFeatures(
                            bounds.toGeometry().toString(), layersToQuery, crsCode,
                            new AsyncCallback<GetFeatureInfoDetails>() {

                                public void onFailure(Throwable caught) {
                                    MessageBox.alert("Service Failure",
                                            caught.getLocalizedMessage(), null);
                                }

                                public void onSuccess(GetFeatureInfoDetails result) {
                                    if (result != null) {
                                        Dispatcher.forwardEvent(GeoGWTEvents.GOT_FEATURE_INFO,
                                                result);
                                    }
                                }

                            });
                }
            }
        };

        BoxSelectOptions boxSelectOptions = new BoxSelectOptions();
        boxSelectOptions.onBoxSelected(listener);

        this.boxSelect = new BoxSelect(boxSelectOptions);

        this.getMap().addControl(this.boxSelect);
    }

    /**
     * @return List<String>
     */
    public List<String> getQueryLayerList() {
        List<String> layersToQuery;
        if (layersIDs == null) {
            int size = layers.size();
            layersToQuery = new ArrayList<String>();

            for (int i = 0; i < size; i++) {
                Layer l = layers.get(i);

                if (l instanceof WMS) {
                    WMS wmsLayer = (WMS) l;

                    //
                    // Only the visible layers should be taken
                    //
                    if (wmsLayer.isVisible() && !wmsLayer.isBaseLayer()) {
                        String wms = wmsLayer.getParams().getLayers();

                        if (wms.indexOf(",") != -1) {
                            String[] layer = wms.split(",");
                            for (int y = 0; y < layer.length; y++) {
                                layersToQuery.add(layer[y]);
                            }
                        } else {
                            layersToQuery.add(wms);
                        }
                    }
                }
            }
        } else {
            layersToQuery = layersIDs;
        }

        if (layersToQuery.size() < 1)
            layersToQuery = null;

        return layersToQuery;
    }

    /**
     * @param isGoogle the is google
     */
    private void initPointSelectControl(final boolean isGoogle) {

        PointSelectListener listener = new PointSelectListener() {

            public void onPointSelected(LonLat lonlat) {

                // MessageBox.alert("", "Lon: " + lonlat.lon() + "  Lat: " + lonlat.lat(), null);

                if (isGoogle) {
                    lonlat.transform("EPSG:900913", "EPSG:4326");
                }

                Pixel pixel = map.getPixelFromLonLat(lonlat);

                PointSelectDetails psd = new PointSelectDetails(lonlat.lon(), lonlat.lat(),
                        pixel.x(), pixel.y(), map.getScale());

                Dispatcher.forwardEvent(GeoGWTEvents.POINT_SELECTED, psd);

                if (featureInfoActivated) {
                    Point selectedPoint = new Point(lonlat.lon(), lonlat.lat());
                    
                    List<String> layersToQuery = getQueryLayerList();

                    String crsCode = map.getProjection();

                    Bounds bounds = map.getExtent();
                    String bbox = bounds.getLowerLeftX() + "," + bounds.getLowerLeftY() + ","
                            + bounds.getUpperRightX() + "," + bounds.getUpperRightY();

                    int mapHeight = (int) map.getSize().getHeight();
                    int mapWidth = (int) map.getSize().getWidth();

                    double resolutions = map.getResolution();

                    GeoGWTRemoteService.Util.getInstance().getFeatureInfo(mapHeight, mapWidth,
                            bbox, layersToQuery, selectedPoint.toString(), pixel.x(), pixel.y(),
                            crsCode, resolutions, new AsyncCallback<GetFeatureInfoDetails>() {

                                public void onFailure(Throwable caught) {
                                    MessageBox.alert("Service Failure",
                                            caught.getLocalizedMessage(), null);
                                }

                                public void onSuccess(GetFeatureInfoDetails result) {
                                    if (result != null) {
                                        Dispatcher.forwardEvent(GeoGWTEvents.GOT_FEATURE_INFO,
                                                result);
                                    }
                                }
                            });
                }
            }
        };

        PointSelectOptions pointSelectOptions = new PointSelectOptions();
        pointSelectOptions.onPointSelected(listener);

        this.pointSelect = new PointSelect(pointSelectOptions);

        this.getMap().addControl(this.pointSelect);
    }

    /**
     * Inits the draw features control.
     * 
     * @param isGoogle the is google
     */
    private void initDrawFeaturesControl(final boolean isGoogle) {
        FeatureAddedListener listener = new FeatureAddedListener() {
            public void onFeatureAdded(VectorFeature vf) {
                org.gwtopenmaps.openlayers.client.geometry.Polygon aoi = org.gwtopenmaps.openlayers.client.geometry.Polygon
                        .narrowToPolygon(vf.getGeometry().getJSObject());

                if (isGoogle) {
                    aoi.transform(new Projection("EPSG:900913"), new Projection("EPSG:4326"));
                }

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
     * Activate GetFeatureInfo tool.
     * 
     * @param data LayerId or null
     */
    public void activateGetFeatureInfo(List<String> data) {

        if (data != null) {
            //
            // Case with Layers IDs
            //
            this.setLayersIDs(data);
        }

        Dispatcher.forwardEvent(GeoGWTEvents.ACTIVATE_BOX_SELECT);
        Dispatcher.forwardEvent(GeoGWTEvents.ACTIVATE_POINT_SELECT);

        if (this.boxSelect.isActive() && this.pointSelect.isActive()) {
            this.featureInfoActivated = true;
        }
    }

    /**
     * Deactivate GetFeatureInfo tool.
     */
    public void deactivateGetFeatureInfo() {
        Dispatcher.forwardEvent(GeoGWTEvents.DEACTIVATE_BOX_SELECT);
        Dispatcher.forwardEvent(GeoGWTEvents.DEACTIVATE_POINT_SELECT);

        this.featureInfoActivated = false;
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
     * @param wkt the wkt
     */
    public void drawWKTOnMap(String wkt) {
        if (this.vector != null) {
            this.eraseFeatures();

            Geometry geom = Geometry.narrowToGeometry(Geometry.fromWKT(wkt).getJSObject());
            // geom.transform(new Projection("EPSG:4326"), new Projection("EPSG:900913"));
            VectorFeature vectorFeature = new VectorFeature(geom);
            this.vector.addFeature(vectorFeature);
            if ((this.layers != null) && (this.layers.size() > 0)) {
                this.getMap().raiseLayer(this.vector, this.layers.size());
            }
            // this.getMap().zoomToExtent(geom.getBounds());
        }
    }

    /**
     *
     */
    public void activateBoxSelect() {
        if (this.boxSelect == null) {
            initBoxSelectControl(false);
        }

        this.boxSelect.activate();
    }

    /**
     *
     */
    public void deactivateBoxSelect() {
        if (this.boxSelect == null) {
            initBoxSelectControl(false);
        }

        this.boxSelect.deactivate();
    }

    /**
     * 
     */
    public void activatePointSelect() {
        if (this.pointSelect == null) {
            initPointSelectControl(false);
        }

        this.pointSelect.activate();
    }

    /**
     * 
     */
    public void deactivatePointSelect() {
        if (this.pointSelect == null) {
            initPointSelectControl(false);
        }

        this.pointSelect.deactivate();
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
     * @param tools the new tools
     */
    public void setTools(List<GenericClientTool> tools) {
        Collections.sort(tools);
        this.tools = tools;
    }

    /**
     * Sets the map.
     * 
     * @param map the new map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Sets the layer.
     * 
     * @param layers the new layer
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

    /**
     * @param controls the controls to set
     */
    public void setControls(List<Control> controls) {
        this.controls = controls;
    }

    /**
     * @return the controls
     */
    public List<Control> getControls() {
        return controls;
    }

    /**
     * @return the layersIDs
     */
    public List<String> getLayersIDs() {
        return layersIDs;
    }

    /**
     * @param layersIDs the layersIDs to set
     */
    public void setLayersIDs(List<String> layersIDs) {
        this.layersIDs = layersIDs;
    }

}
