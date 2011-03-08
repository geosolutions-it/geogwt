package it.geosolutions.geogwt.web.examples.client;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;

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
     * 
     */
    private Dispatcher dispatcher;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        GXT.hideLoadingPanel("loading");

        dispatcher = Dispatcher.get();

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

        Dispatcher.forwardEvent(GeoGWTEvents.ATTACH_MAP_WIDGET, mappanel_div);

        /**
         * Must be done after the dispatch...
         */
        mappanel_div.setWidth(558);
        mappanel_div.setHeight(333);

    }
}
