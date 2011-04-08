/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarActionRegistry,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 17.01.44 $
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
package it.geosolutions.geogwt.gui.client.widget.map.action;

import it.geosolutions.geogwt.gui.client.widget.map.action.app.AppInfoAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.DrawFeatureAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomBoxAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomInAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomOutAction;

import java.util.HashMap;
import java.util.Map;

import org.gwtopenmaps.openlayers.client.MapWidget;

// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarActionRegistry.
 */
public final class ToolbarActionRegistry {

    /** The Constant REGISTRY. */
    private static final Map<String, ToolActionCreator> REGISTRY;

    static {
        REGISTRY = new HashMap<String, ToolActionCreator>();

        REGISTRY.put("appInfo", new ToolActionCreator() {

            public ToolbarAction createActionTool(MapWidget mapWidget) {
                return new AppInfoAction();
            }
        });

        REGISTRY.put("zoomBox", new ToolActionCreator() {

            public ToolbarAction createActionTool(MapWidget mapWidget) {
                return new ZoomBoxAction(mapWidget);
            }
        });
        
        REGISTRY.put("zoomIn", new ToolActionCreator() {

            public ToolbarAction createActionTool(MapWidget mapWidget) {
                return new ZoomInAction(mapWidget);
            }
        });

        REGISTRY.put("zoomOut", new ToolActionCreator() {

            public ToolbarAction createActionTool(MapWidget mapWidget) {
                return new ZoomOutAction(mapWidget);
            }
        });
        
        REGISTRY.put("drawFeature", new ToolActionCreator() {

            public ToolbarAction createActionTool(MapWidget mapWidget) {
                return new DrawFeatureAction();
            }
        });

    }

    /**
     * Put.
     * 
     * @param key
     *            the key
     * @param toolActionCreator
     *            the tool action creator
     */
    public static void put(String key, ToolActionCreator toolActionCreator) {
        if (key != null && toolActionCreator != null)
            REGISTRY.put(key, toolActionCreator);
    }

    /**
     * Gets the.
     * 
     * @param key
     *            the key
     * @param mapWidget
     *            the map widget
     * @return the toolbar action
     */
    public static ToolbarAction get(String key, MapWidget mapWidget) {
        ToolActionCreator toolActionCreator = REGISTRY.get(key);
        if (toolActionCreator == null)
            return null;
        return toolActionCreator.createActionTool(mapWidget);
    }
}
