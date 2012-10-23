/*
 * $ Header: it.geosolutions.geogwt.gui.client.mvc.MapView,v. 0.1 7-apr-2011 16.59.50 created by afabiani <alessio.fabiani at geo-solutions.it> $
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

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.model.GetFeatureInfoDetails;
import it.geosolutions.geogwt.gui.client.model.LayerFeature;
import it.geosolutions.geogwt.gui.client.widget.map.ButtonBar;
import it.geosolutions.geogwt.gui.client.widget.map.MapConfig;
import it.geosolutions.geogwt.gui.client.widget.map.MapLayoutWidget;
import it.geosolutions.geogwt.gui.client.widget.map.WMSLayer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.Control;
import org.gwtopenmaps.openlayers.client.control.ZoomBox;
import org.gwtopenmaps.openlayers.client.geometry.Geometry;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;

/**
 * The Class MapView.
 * 
 * @author Alessio Fabiani at alessio.fabiani@geo-solutions.it
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 * 
 */
public class MapView extends View {

    /** The map layout. */
    private MapLayoutWidget mapLayout;

    private Vector vector = new Vector("Vector Layer");

    /**
     * Instantiates a new map view.
     * 
     * @param controller the controller
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
            } else if (event.getData() instanceof MapConfig) {
                this.mapLayout = new MapLayoutWidget((MapConfig) event.getData());
            }
        }

        if (event.getType() == GeoGWTEvents.ATTACH_MAP_CONTROL) {
            Object control = event.getData();
            if ((this.mapLayout != null) && (control instanceof Control)) {
                if (this.mapLayout.getControls() == null) {
                    this.mapLayout.setControls(new LinkedList<Control>());
                }

                this.mapLayout.getControls().add((Control) control);
                this.mapLayout.getMap().addControl((Control) control);
                ((Control) control).activate();
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

        if (event.getType() == GeoGWTEvents.ACTIVATE_GET_FEATURE_INFO) {
            onActivateGetFeatureInfo(event);
        }

        if (event.getType() == GeoGWTEvents.DEACTIVATE_DRAW_FEATURES) {
            onDeactivateDrawFeature();
        }

        if (event.getType() == GeoGWTEvents.DEACTIVATE_GET_FEATURE_INFO) {
            onDeactivateGetFeatureInfo();
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

        /** Custom Controls **/
        if (event.getType() == GeoGWTEvents.ACTIVATE_BOX_SELECT) {
            onActivateBoxSelect();
        }

        if (event.getType() == GeoGWTEvents.DEACTIVATE_BOX_SELECT) {
            onDeactivateBoxSelect();
        }

        if (event.getType() == GeoGWTEvents.ACTIVATE_POINT_SELECT) {
            onActivatePointSelect();
        }

        if (event.getType() == GeoGWTEvents.DEACTIVATE_POINT_SELECT) {
            onDeactivatePointSelect();
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

        if (event.getType() == GeoGWTEvents.ZOOM_TO_EXTENT) {
            onZoomToExtent((Bounds) event.getData());
        }

        if (event.getType() == GeoGWTEvents.ACTIVATE_ZOOM_BOX) {
            onActivateZoomBox();
        }

        if (event.getType() == GeoGWTEvents.DEACTIVATE_ZOOM_BOX) {
            onDeactivateZoomBox();
        }

        if (event.getType() == GeoGWTEvents.ZOOM) {
            onZoom((Integer) event.getData());
        }

        if (event.getType() == GeoGWTEvents.SET_MAP_CENTER) {
            onSetMapCenter((Double[]) event.getData());
        }

        if (event.getType() == GeoGWTEvents.POINT_SELECTED) {
            onPointSelected(event);
        }

        if (event.getType() == GeoGWTEvents.GOT_FEATURE_INFO) {
            onGetFeatureInfotSelected(event);
        }

    }

    // ////////////////////////////////////////////////////////////////////////
    //
    // Methods Implementations
    //
    // ////////////////////////////////////////////////////////////////////////

    /**
     * @param event
     */
    private void onGetFeatureInfotSelected(AppEvent event) {

        this.removeGetFeatureInfoLayer();

        GetFeatureInfoDetails details = (GetFeatureInfoDetails) event.getData();

        
        if (details != null) {
            
            //
            // Filter WMS here using SLD
            //
            Set<String> keyNames = details.getInfoDetails().keySet();
            Iterator<String> iterator = keyNames.iterator();
            
            int size = keyNames.size();
            if (size > 0) {
                String selectionWKT = details.getGeometryWKT();
                Geometry geometry = Geometry.fromWKT(selectionWKT);
                Bounds bounds = geometry.getBounds();

                while (iterator.hasNext()) {
                    String layerName = iterator.next();
                    
                    WMSLayer layer = getLayerByName(layerName);
                  
                    String style = null;
                    if(layer != null)
                        style = layer.getParams().getStyles();
                    
                    WMSParams wmsParams = new WMSParams();
                    
                    String proj = this.mapLayout.getMap().getProjection();
                    
                    String layerCRS = layer.getCrs();
                    if(layerCRS == null)
                        layerCRS = "EPSG:4326";
                    
                    if(!proj.contains(layerCRS) && details.isClientReprojectBounds())
                        bounds.transform(new Projection(proj), new Projection(layerCRS));
                    
                    if(style != null && !style.isEmpty()){
                        String bound = bounds.toBBox(null);
                        
                        //
                        // Build CQL_FILTER
                        //
                        String cql = getCQLFilter(bound);
                        
                        wmsParams.setParameter("CQL_FILTER", cql);
                        wmsParams.setFormat("image/gif");
                        
                        //
                        // For this case we have to provide a selection SLD in GeoServer
                        //
                        wmsParams.setStyles(style + "_selected");
                        wmsParams.setLayers(layerName);
                        wmsParams.setTransparent(true);
                    }else{     
                        String upper = bounds.getUpperRightX() + "," + bounds.getUpperRightY();
                        String lower = bounds.getLowerLeftX() + "," + bounds.getLowerLeftY();
                        
                        //
                        // Build SLD_BODY
                        //
                        String sldBody = getSLDBody(layerName, upper, lower, details, layer.getCrs());
                        
                        wmsParams.setParameter("SLD_BODY", sldBody);
                        wmsParams.setFormat("image/gif");
                        wmsParams.setLayers(layerName);
                        wmsParams.setTransparent(true);
                    }
                    
                    WMSOptions wmsOptions = new WMSOptions();
                    
                    Bounds b = this.mapLayout.getMap().getExtent();
                    wmsOptions.setMaxExtent(b);
                    
                    wmsOptions.setTransitionEffect(TransitionEffect.RESIZE);
                    wmsOptions.setIsBaseLayer(false);

                    WMS wmsLayer = new WMS(layerName + "_selection", details.getGeoserverURL()
                            + "/wms", wmsParams, wmsOptions);

                    this.mapLayout.getMap().addLayer(wmsLayer);
                }
            }
        } else {
            MessageBox.alert("Info Details", "The get feature information object details is null", null);
        }
    }

    /**
     * @param string
     * @return WMS
     */
    private WMSLayer getLayerByName(String layerName) {
        List<Layer> layers = this.mapLayout.getLayers();
        Iterator<Layer> iterator = layers.iterator();
        
        WMSLayer match = null;
        while(iterator.hasNext()){
            Layer l = iterator.next();
            
            if (l instanceof WMS || l instanceof WMSLayer) {
                WMSLayer wmsLayer = (WMSLayer) l;                
                String wms = wmsLayer.getParams().getLayers();
                
                if(!wms.contains(",") && wms.equals(layerName))
                    match = wmsLayer;
            }
        }
        
        return match;
    }

    /**
     * @param upper
     * @param lower
     * @return String
     */
    private String getCQLFilter(String bound) {
        
        String cql = "BBOX(the_geom, " + bound  + ")";
        return cql;
    }

    /**
     * @param layerName
     * @param upper
     * @param lower
     * @param details
     * @return String
     */
    private String getSLDBody(String layerName, String upper, String lower,
            GetFeatureInfoDetails details, String crs) {
     
        String sldBody = "";

        List<LayerFeature> list = details.getInfoDetails().get(layerName);
        if (list.size() > 0) {
            
            if(crs.contains(":"))
                crs = crs.split(":")[1];

            if (list.get(0).getGeomType().equals(Geometry.POLYGON_CLASS_NAME)) {
                sldBody = "<StyledLayerDescriptor version=\"1.0.0\"><UserLayer><Name>"
                        + layerName
                        + "</Name><UserStyle><Name>UserSelection</Name><FeatureTypeStyle><Rule>"
                        + "<Filter xmlns:gml=\"http://www.opengis.net/gml\">"
                        + "<BBOX><PropertyName>the_geom</PropertyName><Box srsName=\"http://www.opengis.net/gml/srs/epsg.xml#" + crs + "\">"
                        + "<coordinates>" + lower + " " + upper
                        + "</coordinates></Box></BBOX></Filter><PolygonSymbolizer><Fill>"
                        + "<CssParameter name=\"fill\">#FF0000</CssParameter>"
                        + "</Fill><Stroke><CssParameter name=\"stroke\">#000000</CssParameter>"
                        + "<CssParameter name=\"stroke-width\">1</CssParameter>"
                        + "</Stroke></PolygonSymbolizer></Rule>"
                        + "</FeatureTypeStyle></UserStyle></UserLayer></StyledLayerDescriptor>";

            } else if (list.get(0).getGeomType().equals(Geometry.POINT_CLASS_NAME)) {
                sldBody = "<StyledLayerDescriptor version=\"1.0.0\"><UserLayer><Name>"
                        + layerName
                        + "</Name><UserStyle><Name>UserSelection</Name><FeatureTypeStyle><Rule>"
                        + "<Filter xmlns:gml=\"http://www.opengis.net/gml\"><BBOX>"
                        + "<PropertyName>the_geom</PropertyName>"
                        + "<Box srsName=\"http://www.opengis.net/gml/srs/epsg.xml#" + crs + "\"><coordinates>"
                        + lower
                        + " "
                        + upper
                        + "</coordinates></Box></BBOX></Filter><PointSymbolizer><Graphic><Mark>"
                        + "<WellKnownName>circle</WellKnownName><Fill><CssParameter name=\"fill\">#FFEE00</CssParameter>"
                        + "</Fill>"
                        + "<Stroke><CssParameter name=\"stroke\">#000000</CssParameter>"
                        + "<CssParameter name=\"stroke-width\">2</CssParameter></Stroke>"
                        + "</Mark><Opacity>1.0</Opacity><Size>6</Size></Graphic></PointSymbolizer></Rule>"
                        + "</FeatureTypeStyle></UserStyle></UserLayer></StyledLayerDescriptor>";
            } else if (list.get(0).getGeomType().equals(Geometry.LINESTRING_CLASS_NAME)) {
                sldBody = "<StyledLayerDescriptor version=\"1.0.0\"><UserLayer><Name>"
                        + layerName
                        + "</Name><UserStyle><Name>UserSelection</Name><FeatureTypeStyle><Rule>"
                        + "<Filter xmlns:gml=\"http://www.opengis.net/gml\"><BBOX><PropertyName>the_geom</PropertyName>"
                        + "<Box srsName=\"http://www.opengis.net/gml/srs/epsg.xml#" + crs + "\"><coordinates>"
                        + lower + " " + upper
                        + "</coordinates></Box></BBOX></Filter><LineSymbolizer>"
                        + "<Stroke><CssParameter name=\"stroke\">#FFFFFF</CssParameter>"
                        + "<CssParameter name=\"stroke-width\">2</CssParameter></Stroke>"
                        + "</LineSymbolizer></Rule>"
                        + "</FeatureTypeStyle></UserStyle></UserLayer></StyledLayerDescriptor>";
            }
        }

        return sldBody;
    }

    /**
     * @param event
     */
    private void onPointSelected(AppEvent event) {
        /*PointSelectDetails psd = (PointSelectDetails)event.getData();
        
        LonLat lonlat = new LonLat(psd.getLon(), psd.getLat());

        if (this.mapLayout.getLayers().contains(vector)) {
            this.mapLayout.removeLayer(vector);
        }

        vector = new Vector("Vector Layer");

        Point point = new Point(lonlat.lon(), lonlat.lat());
        VectorFeature feature = new VectorFeature(point);

        vector.addFeature(feature);

        this.mapLayout.addLayer(vector);*/
    }

    /**
	 * 
	 */
    private void onDeactivatePointSelect() {
        /*if (this.mapLayout.getLayers().contains(vector)) {
            this.mapLayout.removeLayer(vector);
        }*/
        this.mapLayout.deactivatePointSelect();
    }

    /**
	 * 
	 */
    private void onActivatePointSelect() {
        this.mapLayout.activatePointSelect();
    }

    /**
     * On init toolbar.
     * 
     * @param event the event
     */
    private void onInitToolbar(AppEvent event) {
        ButtonBar buttonBar = (ButtonBar) event.getData();
        buttonBar.setMapLayoutWidget(this.mapLayout);
    }

    /**
     * On add layer to map.
     * 
     * @param layer the layer
     */
    private void onAddLayerToMap(Layer layer) {
        this.mapLayout.addLayer(layer);
    }

    /**
     * On remove layer from map.
     * 
     * @param layer the layer
     */
    private void onRemoveLayerFromMap(Layer layer) {
        this.mapLayout.removeLayer(layer);
    }

    /**
     * On set map center.
     * 
     * @param centerCoords the center coords
     */
    private void onSetMapCenter(Double[] centerCoords) {
        LonLat lonlat = new LonLat(centerCoords[0], centerCoords[1]);
        this.mapLayout.getMap().setCenter(lonlat);
    }

    /**
     * On zoom to box.
     * 
     * @param bounds
     */
    private void onZoomToExtent(Bounds bounds) {
        this.mapLayout.getMap().zoomToExtent(bounds);
    }

    /**
     * On zoom to max extent.
     */
    private void onZoomToMaxExtent() {
        this.mapLayout.getMap().zoomToMaxExtent();
    }

    /**
     * On zoom.
     * 
     * @param zoomLevel the zoom level
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
     * On deactivate ZoomBox
     */
    private void onDeactivateZoomBox() {
        boolean deactivated = false;

        if (this.mapLayout.getControls() != null) {
            for (Control zoomBox : this.mapLayout.getControls()) {
                if (zoomBox instanceof ZoomBox) {
                    deactivated = zoomBox.deactivate();
                }
            }
        }
    }

    /**
     * On activate ZoomBox
     */
    private void onActivateZoomBox() {
        boolean activated = false;

        if (this.mapLayout.getControls() != null) {
            for (Control zoomBox : this.mapLayout.getControls()) {
                if (zoomBox instanceof ZoomBox) {
                    activated = zoomBox.activate();
                }
            }
        }

        if (!activated) {
            if (this.mapLayout.getControls() == null) {
                this.mapLayout.setControls(new LinkedList<Control>());
            }

            Control zoomBox = new ZoomBox();
            this.mapLayout.getControls().add(zoomBox);
            this.mapLayout.getMap().addControl(zoomBox);
            zoomBox.activate();
        }
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
     * On draw wkt on map.
     * 
     * @param event the event
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

    /**
     *
     */
    private void onActivateBoxSelect() {
        this.mapLayout.activateBoxSelect();
    }

    /**
     *
     */
    private void onDeactivateBoxSelect() {
        this.mapLayout.deactivateBoxSelect();
    }

    /**
     * 
     * @param data
     */
    private void onActivateGetFeatureInfo(AppEvent event) {
        List<String> data = event.getData();
        this.mapLayout.activateGetFeatureInfo(data);
    }

    /**
     * 
     */
    private void onDeactivateGetFeatureInfo() {
        this.removeGetFeatureInfoLayer();
        this.mapLayout.deactivateGetFeatureInfo();
    }

    /**
     * 
     */
    private void removeGetFeatureInfoLayer() {
        //
        // Removig old selection
        //
        Layer[] layers = this.mapLayout.getMap().getLayers();
        for (int y = 0; y < layers.length; y++) {
            if (layers[y].getName().indexOf("_selection") != -1)
                this.mapLayout.getMap().removeLayer(layers[y]);
        }
    }
}
