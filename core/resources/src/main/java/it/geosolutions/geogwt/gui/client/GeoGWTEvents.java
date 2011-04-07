/*
 * $ Header: it.geosolutions.geogwt.gui.client.GeoGWTEvents,v. 0.1 31-gen-2011 13.38.37 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 31-gen-2011 13.38.37 $
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
package it.geosolutions.geogwt.gui.client;

import com.extjs.gxt.ui.client.event.EventType;

// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTEvents.
 */
public class GeoGWTEvents {

    /** The Constant INIT. */
    public static final EventType INIT = new EventType();

    /** The Constant INIT_RESOURCES_MODULE. */
    public static final EventType INIT_RESOURCES_MODULE = new EventType();

    /** The Constant SEND_ALERT_MESSAGE. */
    public static final EventType SEND_ALERT_MESSAGE = new EventType();

    /** The Constant SEND_ERROR_MESSAGE. */
    public static final EventType SEND_ERROR_MESSAGE = new EventType();

    /** The Constant SEND_INFO_MESSAGE. */
    public static final EventType SEND_INFO_MESSAGE = new EventType();

    
    public static final EventType SAVE = new EventType();
    
    public static final EventType LOGOUT = new EventType();

  
    /** The Constant INIT_MAPS_UI_MODULE. */
    public static final EventType INIT_MAPS_UI_MODULE = new EventType();

    public static final EventType ATTACH_MAP_WIDGET = new EventType();

    public static final EventType UPDATE_MAP_SIZE = new EventType();

    public static final EventType INIT_TOOLBAR = new EventType();

    public static final EventType ATTACH_TOOLBAR = new EventType();

    public static final EventType ENABLE_DRAW_BUTTON = new EventType();

    public static final EventType DISABLE_DRAW_BUTTON = new EventType();

    public static final EventType ZOOM_TO_MAX_EXTENT = new EventType();

    public static final EventType ZOOM_TO_CENTER = new EventType();

    public static final EventType ZOOM = new EventType();

    public static final EventType SET_MAP_CENTER = new EventType();

    /** MAP LAYER MANAGEMENT EVENTS **/

    public static final EventType ADD_LAYER = new EventType();

    public static final EventType REMOVE_LAYER = new EventType();

    public static final EventType UPDATE_LAYER = new EventType();

    /** LAYER FEATURES MANAGEMENT EVENTS **/

    public static final EventType ACTIVATE_DRAW_FEATURES = new EventType();

    public static final EventType DEACTIVATE_DRAW_FEATURES = new EventType();

    public static final EventType ERASE_FEATURES = new EventType();

    public static final EventType DRAW_WKT_ON_MAP = new EventType();

}
