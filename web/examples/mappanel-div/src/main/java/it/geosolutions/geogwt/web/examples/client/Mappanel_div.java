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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Mappanel_div implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        GXT.hideLoadingPanel("loading");

        /**
         * Adding widgets to Viewport panels
         */
        ContentPanel mappanel_div = new ContentPanel();
        mappanel_div.setLayout(new FitLayout());
        mappanel_div.setHeaderVisible(true);
        mappanel_div.setHeading("GeoGWT MapPanel");
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
        //wmsParams.setLayers("basic");
        wmsParams.setLayers("GeoSolutions:ne_shaded");
        wmsParams.setStyles("");

        WMSOptions wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);

        //WMS layer = new WMS("Basic WMS", "http://labs.metacarta.com/wms/vmap0", wmsParams, wmsLayerParams);
        WMS layer = new WMS("Basic WMS", "http://demo1.geo-solutions.it/playground/wms", wmsParams, wmsLayerParams);
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
