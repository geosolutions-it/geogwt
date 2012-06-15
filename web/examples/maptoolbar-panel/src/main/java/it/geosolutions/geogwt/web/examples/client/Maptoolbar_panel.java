/*
 * $ Header: it.geosolutions.geogwt.web.examples.client.Maptoolbar_panel,v. 0.1 7-apr-2011 17.02.22 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.web.examples.client;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.GeoGWTUtils;
import it.geosolutions.geogwt.gui.client.configuration.GeoGWTConfiguration;
import it.geosolutions.geogwt.gui.client.service.GeoGWTConfigurationRemote;
import it.geosolutions.geogwt.web.examples.client.service.SessionControllerRemoteService;
import it.geosolutions.geogwt.web.examples.client.service.SessionControllerRemoteServiceAsync;

import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapUnits;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.OSM;
import org.gwtopenmaps.openlayers.client.layer.OSMOptions;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class Maptoolbar_panel.
 */
public class Maptoolbar_panel implements EntryPoint {

    /*
     * Session Management section
     */
    private static final String THE_APP_WILL_CLOSE = " The application will close.";

    private static final int SESSION_WARNING_TIMEOUT_MILLIS = 10000; // 1 minute

    public static native void closeBrowser() /*-{
		$wnd.close();
    }-*/;

    public static native String getQueryString() /*-{
		return $wnd.location.search.substring(1);
    }-*/;

    public static native String getContextPath() /*-{
		return "/"
				+ $wnd.location.pathname.substring(1).substring(0,
						$wnd.location.pathname.substring(1).indexOf("/"));
    }-*/;

    private Timer sessionTimeoutTimer = null;

    private Timer sessionTimeoutResponseTimer = null;

    private int sessionTimeMillis;

    /** The main. */
    private ContentPanel main;

    /** The center. */
    protected ContentPanel center;

    /** The north. */
    protected ContentPanel north;

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    public void onModuleLoad() {
        GXT.hideLoadingPanel("loading");

        buildUI();
    }

    /**
     *
     */
    protected void buildUI() {

        /**
         * Adding widgets to Viewport panels
         */
        this.main = new ContentPanel();
        this.main.setLayout(new BorderLayout());
        this.main.setHeaderVisible(true);
        this.main.setHeading("GeoGWT MapToolbar Example");

        loadConfiguration();
    }

    /**
     * Load configuration.
     */
    private void loadConfiguration() {

        /** Example: trying to load toolbar components from applicationContext automatically **/
        GeoGWTConfigurationRemote.Util.getInstance().initServerConfiguration(
                new AsyncCallback<GeoGWTConfiguration>() {
                    public void onSuccess(GeoGWTConfiguration result) {
                        GeoGWTUtils.getInstance().setGlobalConfiguration(result);

                        createCenter();
                        createNorth();

                        main.setWidth(558);
                        main.setHeight(333);

                        RootPanel.get("maptoolbar").add(main);

                        // initializeIdleTimeoutSession(result);
                    }

                    public void onFailure(Throwable caught) {
                        Info.display("Configuration Service Error", caught.getMessage());
                    }
                });

        // /** Example: loading toolbar manually **/
        // ToolbarItemManager toolbarItemManager = new ToolbarItemManager();
        //
        // // defining toolbar tools
        // GenericClientTool toolbarSeparator = new GenericClientTool();
        // toolbarSeparator.setId(ButtonBar.TOOLBAR_SEPARATOR);
        // toolbarSeparator.setOrder(30);
        //
        // ActionClientTool zoomBox = new ActionClientTool();
        // zoomBox.setId("zoomBox");
        // zoomBox.setEnabled(true);
        // zoomBox.setType("toggle");
        // zoomBox.setOrder(0);
        //
        // ActionClientTool zoomIn = new ActionClientTool();
        // zoomIn.setId("zoomIn");
        // zoomIn.setEnabled(true);
        // zoomIn.setType("button");
        // zoomIn.setOrder(10);
        //
        // ActionClientTool zoomOut = new ActionClientTool();
        // zoomOut.setId("zoomOut");
        // zoomOut.setEnabled(true);
        // zoomOut.setType("button");
        // zoomOut.setOrder(20);
        //
        // ActionClientTool drawFeature = new ActionClientTool();
        // drawFeature.setId("drawFeature");
        // drawFeature.setEnabled(true);
        // drawFeature.setType("toggle");
        // drawFeature.setOrder(50);
        //
        // List<GenericClientTool> clientTools = new ArrayList<GenericClientTool>();
        // clientTools.add(zoomBox);
        // clientTools.add(zoomIn);
        // clientTools.add(zoomOut);
        // clientTools.add(toolbarSeparator);
        // clientTools.add(drawFeature);
        //
        // toolbarItemManager.setClientTools(clientTools);
        //
        // if (GeoGWTUtils.getInstance().getGlobalConfiguration() == null) {
        // GeoGWTUtils.getInstance().setGlobalConfiguration(new GeoGWTConfiguration());
        // }
        //
        // GeoGWTUtils.getInstance().getGlobalConfiguration().setToolbarItemManager(toolbarItemManager);
        //
        // createCenter();
        // createNorth();
        //
        // main.setWidth(558);
        // main.setHeight(333);
        //
        // RootPanel.get("maptoolbar").add(main);
    }

    /**
     * Creates the north.
     */
    private void createNorth() {
        north = new ContentPanel();
        north.setHeaderVisible(false);
        north.addListener(Events.Resize, new Listener<BaseEvent>() {
            public void handleEvent(BaseEvent be) {
                Dispatcher.forwardEvent(GeoGWTEvents.UPDATE_MAP_SIZE);
            }
        });

        BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 30);
        data.setMargins(new Margins(0, 5, 0, 5));
        data.setSplit(true);

        main.add(north, data);

        Dispatcher.forwardEvent(GeoGWTEvents.ATTACH_TOOLBAR, this.north);
    }

    /**
     * Creates the center.
     */
    private void createCenter()
    {
        center = new ContentPanel();
        center.setHeaderVisible(false);
        center.setLayout(new BorderLayout());

        ContentPanel maptoolbar_panel = new ContentPanel();
        maptoolbar_panel.setLayout(new FitLayout());
        maptoolbar_panel.setHeaderVisible(false);
        maptoolbar_panel.setHeading("GeoGWT MapView");
        maptoolbar_panel.addListener(Events.Resize, new Listener<BaseEvent>()
            {
                public void handleEvent(BaseEvent be)
                {
                    Dispatcher.forwardEvent(GeoGWTEvents.UPDATE_MAP_SIZE);
                }
            });
        maptoolbar_panel.addListener(Events.Move, new Listener<BaseEvent>()
            {
                public void handleEvent(BaseEvent be)
                {
                    Dispatcher.forwardEvent(GeoGWTEvents.UPDATE_MAP_SIZE);
                }
            });

        /* ********************************************* */

        /*MouseMoveHandler mouseMoveHandler = new MouseMoveHandler()
            {
                public void onMouseMove(MouseMoveEvent event)
                {
                    // Allow 30 seconds to get the RPC call constructed and called.
                    int x = (sessionTimeMillis - 30000) - SESSION_WARNING_TIMEOUT_MILLIS;

                    // User responded before the response timer elapsed, so keep session
                    // by calling the service.
                    sessionTimeoutResponseTimer.cancel();
                    sessionTimeoutTimer.cancel();
                    sessionTimeoutTimer.schedule(x);
                }

            };
        maptoolbar_panel.addMouseMoveHandler(mouseMoveHandler);*/

        /* ********************************************* */

        maptoolbar_panel.setMonitorWindowResize(true);
        maptoolbar_panel.setLayoutOnChange(true);

        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setCollapsible(false);
        data.setFloatable(false);
        data.setSplit(true);
        data.setMargins(new Margins(0));

        // add map to center region of center panel
        center.add(maptoolbar_panel, data);

        data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setMargins(new Margins(5, 5, 5, 5));
        data.setCollapsible(true);
        data.setSplit(true);

        // add center panel to center region of viewport
        main.add(center, data);

        /* map options */
        MapOptions mapOptions = new MapOptions();
        mapOptions.setUnits(MapUnits.DEGREES);
        mapOptions.setProjection("EPSG:4326");

        Dispatcher.forwardEvent(GeoGWTEvents.INIT_MAPS_UI_MODULE, mapOptions);

        /* base layer */
//        WMSParams wmsParams = new WMSParams();
//        wmsParams.setFormat("image/png");
//        wmsParams.setLayers("basic");
//        wmsParams.setStyles("");
//
//        WMSOptions wmsLayerParams = new WMSOptions();
//        wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);
//
//        String[] urlArray = new String[] {
//                "http://vmap0.tiles.osgeo.org/wms/vmap0?test=${x}",
//                "http://vmap0.tiles.osgeo.org/wms/vmap0?test=${y}",
//                "http://vmap0.tiles.osgeo.org/wms/vmap0?test=${z}"
//        };
//        
//        WMS layer = new WMS("OpenLayers Base Map", urlArray, wmsParams,
//                wmsLayerParams);
        
        String[] urlArray = new String[] {
                        "http://a.tile.openstreetmap.org/${z}/${x}/${y}.png",
                        "http://b.tile.openstreetmap.org/${z}/${x}/${y}.png",
                        "http://c.tile.openstreetmap.org/${z}/${x}/${y}.png"};
        
        OSMOptions osmOptions = new OSMOptions();
        osmOptions.setTransitionEffect(TransitionEffect.RESIZE);
        
        Layer layer = new OSM(
                "OSM", 
                urlArray, 
                osmOptions
        );
        
        /* overlay layer */
/*        wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers("topp:states");
        wmsParams.setStyles("polygon");
        wmsParams.setTransparent(true);

        wmsLayerParams = new WMSOptions();
        wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);
        wmsLayerParams.setIsBaseLayer(false);

        layer = new WMS("States", "http://localhost:8080/geoserver/wms", wmsParams,
                wmsLayerParams);
        Dispatcher.forwardEvent(GeoGWTEvents.ADD_LAYER, layer); */
        
        Dispatcher.forwardEvent(GeoGWTEvents.ADD_LAYER, layer);

        Dispatcher.forwardEvent(GeoGWTEvents.ATTACH_MAP_WIDGET, maptoolbar_panel);

        // Adjusting the Zoom level
        // Dispatcher.forwardEvent(GeoGWTEvents.ZOOM_TO_MAX_EXTENT);
        Dispatcher.forwardEvent(GeoGWTEvents.SET_MAP_CENTER, new Double[] { 13.0, 42.0 });
        Dispatcher.forwardEvent(GeoGWTEvents.ZOOM, 5);
    }

    /**
     * *********************************************************************** Session Timeout
     * utility methods
     **************************************************************************/
    /**
     * Called only once from <b>onModuleLoad</b>.
     * 
     * @sessionTimeInMillis Integer
     */
    private void initSessionTimers(int sessionTimeMillis) {
        // Allow 30 seconds to get the RPC call constructed and called.
        final int x = (sessionTimeMillis - 30000) - SESSION_WARNING_TIMEOUT_MILLIS;
        sessionTimeoutResponseTimer = new Timer() {
            public void run() {
                displaySessionTimedOut();
            }
        };
        sessionTimeoutTimer = new Timer() {
            public void run() {
                sessionTimeoutResponseTimer.schedule(SESSION_WARNING_TIMEOUT_MILLIS);
                MessageBox.alert("Session Timeout",
                        "Your session is about to timeout. Please press \"OK\" to keep working.",
                        new Listener<MessageBoxEvent>() {
                            public void handleEvent(MessageBoxEvent be) {
                                // User responded before the response timer elapsed, so keep session
                                // by calling the service.
                                sessionTimeoutResponseTimer.cancel();
                                sessionTimeoutTimer.cancel();
                                sessionTimeoutTimer.schedule(x);
                                keepUserSessionAlive();
                            }
                        });
            }
        };
        sessionTimeoutTimer.schedule(x);
    }

    /**
     *
     */
    private void keepUserSessionAlive() {
        SessionControllerRemoteServiceAsync service = SessionControllerRemoteService.Util
                .getInstance();
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {
            public void onSuccess(Void result) {
            }

            public void onFailure(final Throwable caught) {
                MessageBox.alert("Session Timeout", caught.toString() + THE_APP_WILL_CLOSE,
                        new Listener<MessageBoxEvent>() {
                            public void handleEvent(MessageBoxEvent be) {
                                closeBrowser();
                            }
                        });
            }
        };
        service.keepUserSessionAlive(callback);
    }

    /**
     *
     */
    private void displaySessionTimedOut() {
        MessageBox.alert("Session Timeout", "Your session has timed out." + THE_APP_WILL_CLOSE,
                new Listener<MessageBoxEvent>() {
                    public void handleEvent(MessageBoxEvent be) {
                        closeBrowser();
                    }
                });
    }

    /**
     * @param configuration
     * 
     */
    @SuppressWarnings("unused")
    private void initializeIdleTimeoutSession(GeoGWTConfiguration configuration) {

        /* *********************
         * Session Timeout initializer
         */
        SessionControllerRemoteServiceAsync service = SessionControllerRemoteService.Util
                .getInstance();
        AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
            public void onSuccess(Integer result) {
                sessionTimeMillis = result.intValue();

                if (sessionTimeMillis == -1) {
                    displaySessionTimedOut();
                } else {
                    initSessionTimers(sessionTimeMillis);
                }
            }

            public void onFailure(final Throwable caught) {
                MessageBox.alert("Error", caught.toString() + THE_APP_WILL_CLOSE,
                        new Listener<MessageBoxEvent>() {
                            public void handleEvent(MessageBoxEvent be) {
                                closeBrowser();
                            }
                        });
            }
        };
        service.getUserSessionTimeoutMillis(callback);
    }
}
