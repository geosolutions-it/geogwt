/*
 * $ Header: it.geosolutions.geogwt.gui.client.mvc.MaptoolbarView,v. 0.1 25-gen-2011 11.30.32 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
import it.geosolutions.geogwt.gui.client.widget.map.MapLayoutWidget;
import it.geosolutions.geogwt.gui.client.widget.map.MapToolBar;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class MaptoolbarView.
 */
public class MaptoolbarView extends View {

    /** The button bar. */
    private MapToolBar mapToolBar;
    
    /** The map widget **/
    private MapLayoutWidget map;

    /**
     * Instantiates a new map view.
     * 
     * @param controller
     *            the controller
     */
    public MaptoolbarView(Controller controller) {
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
        if (event.getType() == GeoGWTEvents.ATTACH_TOOLBAR) {
            onAttachToolbar(event);
        }

        /** Mapping Feature Handling Events **/
        if (event.getType() == GeoGWTEvents.ENABLE_DRAW_BUTTON) {
            onEnableDrawButton();
        }

        if (event.getType() == GeoGWTEvents.DISABLE_DRAW_BUTTON) {
            onDisableDrawButton();
        }

    }

    // ////////////////////////////////////////////////////////////////////////
    //
    // Methods Implementations
    //
    // ////////////////////////////////////////////////////////////////////////

    /**
     * On disable draw button.
     */
    private void onDisableDrawButton() {
        this.getButtonBar().changeButtonState("drawFeature", false);
    }

    /**
     * On enable draw button.
     */
    private void onEnableDrawButton() {
        this.getButtonBar().changeButtonState("drawFeature", true);
    }

    /**
     * On attach toolbar.
     * 
     * @param event
     *            the event
     */
    private void onAttachToolbar(AppEvent event) {
        if (this.getMap() != null) {
            this.setButtonBar(new MapToolBar(getMap()));
        } else {
            this.setButtonBar(new MapToolBar());
            Dispatcher.forwardEvent(GeoGWTEvents.INIT_TOOLBAR, this.getButtonBar());
        }

        ContentPanel north = (ContentPanel) event.getData();
        north.add(getButtonBar().getToolBar());

        north.layout();
    }

    /**
     * @param map the map to set
     */
    public void setMap(MapLayoutWidget map) {
        this.map = map;
    }

    /**
     * @return the map
     */
    public MapLayoutWidget getMap() {
        return map;
    }

    /**
     * @param mapToolBar the mapToolBar to set
     */
    public void setButtonBar(MapToolBar mapToolBar) {
        this.mapToolBar = mapToolBar;
    }

    /**
     * @return the mapToolBar
     */
    public MapToolBar getButtonBar() {
        return mapToolBar;
    }
}
