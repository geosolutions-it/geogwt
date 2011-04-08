/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomInAction,v. 0.1 8-apr-2011 15.48.16 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 8-apr-2011 15.48.16 $
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
package it.geosolutions.geogwt.gui.client.widget.map.action.toolbar;

import it.geosolutions.geogwt.gui.client.i18n.I18nProvider;
import it.geosolutions.geogwt.gui.client.model.Category;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarMapAction;

import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;

import com.extjs.gxt.ui.client.event.BaseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ZoomInAction.
 */
public class ZoomInAction extends ToolbarMapAction {

    /** The map widget. */
    private MapWidget mapWidget;

    /** The zoom factor. */
    private int zoomFactor = 1;

    /**
     * Instantiates a new zoom in action.
     * 
     * @param mapWidget
     *            the map widget
     */
    public ZoomInAction(MapWidget mapWidget) {
        super(I18nProvider.getMessages().zoomInToolTip(), Category.GEOGWT_ZOOM_IN);

        this.mapWidget = mapWidget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.extjs.gxt.ui.client.event.Listener#handleEvent(com.extjs.gxt.ui.client.event.BaseEvent)
     */
    public void handleEvent(BaseEvent baseEvent) {
        LonLat center = this.mapWidget.getMap().getCenter();
        int oldZoom = this.mapWidget.getMap().getZoom();
        this.mapWidget.getMap().setCenter(center, oldZoom + this.zoomFactor);
    }
}
