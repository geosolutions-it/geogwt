/*
 * $ Header: it.geosolutions.geogwt.gui.client.GeoGWTEvents,v. 0.1 7-apr-2011 16.58.10 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.58.10 $
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

    
    /** The Constant SAVE. */
    public static final EventType SAVE = new EventType();
    
    /** The Constant LOGOUT. */
    public static final EventType LOGOUT = new EventType();

  
    /** The Constant INIT_MAPS_UI_MODULE. */
    public static final EventType INIT_MAPS_UI_MODULE = new EventType();

    /** The Constant ATTACH_MAP_WIDGET. */
    public static final EventType ATTACH_MAP_WIDGET = new EventType();

    /** The Constant UPDATE_MAP_SIZE. */
    public static final EventType UPDATE_MAP_SIZE = new EventType();

    /** The Constant INIT_TOOLBAR. */
    public static final EventType INIT_TOOLBAR = new EventType();

    /** The Constant ATTACH_TOOLBAR. */
    public static final EventType ATTACH_TOOLBAR = new EventType();

    /** The Constant ENABLE_DRAW_BUTTON. */
    public static final EventType ENABLE_DRAW_BUTTON = new EventType();

    /** The Constant DISABLE_DRAW_BUTTON. */
    public static final EventType DISABLE_DRAW_BUTTON = new EventType();

    /** The Constant ZOOM_TO_MAX_EXTENT. */
    public static final EventType ZOOM_TO_MAX_EXTENT = new EventType();

    /** The Constant ZOOM_TO_CENTER. */
    public static final EventType ZOOM_TO_CENTER = new EventType();

    /** The Constant ZOOM. */
    public static final EventType ZOOM = new EventType();

    /** The Constant SET_MAP_CENTER. */
    public static final EventType SET_MAP_CENTER = new EventType();

    /** The Constant ADD_LAYER. */

    public static final EventType ADD_LAYER = new EventType();

    /** The Constant REMOVE_LAYER. */
    public static final EventType REMOVE_LAYER = new EventType();

    /** The Constant UPDATE_LAYER. */
    public static final EventType UPDATE_LAYER = new EventType();

    /** The Constant ACTIVATE_DRAW_FEATURES. */

    public static final EventType ACTIVATE_DRAW_FEATURES = new EventType();

    /** The Constant DEACTIVATE_DRAW_FEATURES. */
    public static final EventType DEACTIVATE_DRAW_FEATURES = new EventType();

    /** The Constant ERASE_FEATURES. */
    public static final EventType ERASE_FEATURES = new EventType();

    /** The Constant DRAW_WKT_ON_MAP. */
    public static final EventType DRAW_WKT_ON_MAP = new EventType();

}
