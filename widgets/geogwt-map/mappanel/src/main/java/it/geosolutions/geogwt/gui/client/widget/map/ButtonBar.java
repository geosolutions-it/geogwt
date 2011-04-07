/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.ButtonBar,v. 0.1 7-apr-2011 16.59.50 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.map;

import it.geosolutions.geogwt.gui.client.Resources;
import it.geosolutions.geogwt.gui.client.configuration.DropdownClientTool;
import it.geosolutions.geogwt.gui.client.model.Category;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

// TODO: Auto-generated Javadoc
/**
 * The Class ButtonBar.
 */
public abstract class ButtonBar extends LayoutContainer implements Listener<AppEvent> {

    /** The Constant TOOLBAR_SEPARATOR. */
    public static final String TOOLBAR_SEPARATOR = "ToolbarSeparator";

    /** The vp. */
    protected VerticalPanel vp;

    /** The tool bar. */
    private ToolBar toolBar;

    /** The map layout widget. */
    private MapLayoutWidget mapLayoutWidget;

    /** The REGISTR y_ buttons. */
    protected Map<String, Button> REGISTRY_BUTTONS = new HashMap<String, Button>();

    /** The dropdowns. */
    protected Map<DropdownClientTool, ComboBox> dropdowns = new HashMap<DropdownClientTool, ComboBox>();

    /**
     * Instantiates a new button bar.
     */
    public ButtonBar() {
        super();
        this.vp = new VerticalPanel();
        this.setToolBar(new ToolBar());
        this.getToolBar().setHeight(60);
    }
    
    /**
     * Instantiates a new button bar.
     * 
     * @param mapLayoutWidget
     *            the map layout widget
     */
    public ButtonBar(MapLayoutWidget mapLayoutWidget) {
        this();
        this.setMapLayoutWidget(mapLayoutWidget);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.extjs.gxt.ui.client.event.Listener#handleEvent(com.extjs.gxt.ui.client.event.BaseEvent)
     */
    public void handleEvent(AppEvent e) {
    }

    /**
     * Initialize.
     */
    protected abstract void initialize();

    /**
     * Sets the icon.
     * 
     * @param button
     *            the button
     * @param cat
     *            the cat
     */
    protected void setIcon(Button button, Category cat) {
        switch (cat) {
        case GEOGWT_INFO:
            button.setIcon(Resources.ICONS.info());
            break;
        case GEOGWT_ZOOM_IN:
            button.setIcon(Resources.ICONS.zoomIn());
            break;
        case GEOGWT_ZOOM_OUT:
            button.setIcon(Resources.ICONS.zoomOut());
            break;
        case GEOGWT_DRAW:
            button.setIcon(Resources.ICONS.drawFeature());
            break;
        case GEOGWT_UPLOAD_SHP:
            button.setIcon(Resources.ICONS.uploadSHP());
            break;
        case LOGOUT:
            button.setIcon(Resources.ICONS.logout());
            break;
        case GEOGWT_CLEAN:
            button.setIcon(Resources.ICONS.cleanMenu());
            break;
        case SAVE:
            button.setIcon(Resources.ICONS.save());
            break;
        case DELETE_CONTENT:
            button.setIcon(Resources.ICONS.delete());
            break;
        case SYNCH:
            button.setIcon(Resources.ICONS.user());
            break;
        }
    }

    /**
     * Sets the menu icon.
     * 
     * @param item
     *            the item
     * @param cat
     *            the cat
     */
    protected void setMenuIcon(MenuItem item, Category cat) {
    }

    /**
     * Gets the tool bar.
     * 
     * @return the tool bar
     */
    public ToolBar getToolBar() {
        return toolBar;
    }

    /**
     * Change button state.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public void changeButtonState(String key, boolean value) {
        if (REGISTRY_BUTTONS.containsKey(key)) {
            REGISTRY_BUTTONS.get(key).setEnabled(value);
        }
    }

    /**
     * Sets the map layout widget.
     * 
     * @param mapLayoutWidget
     *            the new map layout widget
     */
    public void setMapLayoutWidget(MapLayoutWidget mapLayoutWidget) {
        if (this.mapLayoutWidget == null) {
            this.mapLayoutWidget = mapLayoutWidget;
            initialize();
        }
    }

    /**
     * Gets the map layout widget.
     * 
     * @return the map layout widget
     */
    public MapLayoutWidget getMapLayoutWidget() {
        return mapLayoutWidget;
    }

    /**
     * Sets the tool bar.
     * 
     * @param toolBar
     *            the new tool bar
     */
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }
}
