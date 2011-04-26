/*
 * $ Header: it.geosolutions.georepo.gui.client.action.ToolbarActionRegistry,v. 0.1 25-gen-2011 11.30.33 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.map.action;

import it.geosolutions.geogwt.gui.client.configuration.ToolbarAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.DrawFeatureAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.PanAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomAllAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomBoxAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomInAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomOutAction;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarActionRegistry.
 */
public final class ToolbarActionRegistry {

    /** The Constant REGISTRY. */
    private static final Map<String, ToolActionCreator> REGISTRY;

    static {
        REGISTRY = new HashMap<String, ToolActionCreator>();

        REGISTRY.put("pan", new ToolActionCreator() {

            public ToolbarAction createActionTool() {
                PanAction action = new PanAction();
                action.initialize();
                return action;
            }
        });
        
        REGISTRY.put("zoomAll", new ToolActionCreator() {

            public ToolbarAction createActionTool() {
                ZoomAllAction action = new ZoomAllAction();
                action.initialize();
                return action;
            }
        });
        
        REGISTRY.put("zoomIn", new ToolActionCreator() {

            public ToolbarAction createActionTool() {
                ZoomInAction action = new ZoomInAction();
                action.initialize();
                return action;
            }
        });

        REGISTRY.put("zoomOut", new ToolActionCreator() {

            public ToolbarAction createActionTool() {
                ZoomOutAction action = new ZoomOutAction();
                action.initialize();
                return action;
            }
        });
        
        REGISTRY.put("zoomBox", new ToolActionCreator() {

            public ToolbarAction createActionTool() {
                ZoomBoxAction action = new ZoomBoxAction();
                action.initialize();
                return action;
            }
        });

        REGISTRY.put("drawFeature", new ToolActionCreator() {

            public ToolbarAction createActionTool() {
                DrawFeatureAction action = new DrawFeatureAction();
                action.initialize();
                return action;
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
    public static ToolbarAction get(String key) {
        ToolActionCreator toolActionCreator = REGISTRY.get(key);
        if (toolActionCreator == null)
            return null;
        return toolActionCreator.createActionTool();
    }
}