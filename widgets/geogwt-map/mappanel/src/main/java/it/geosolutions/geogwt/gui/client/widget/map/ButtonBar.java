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

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


// TODO: Auto-generated Javadoc
/**
 * The Class ButtonBar.
 */
public abstract class ButtonBar extends LayoutContainer
{
    /** The Constant TOOLBAR_SEPARATOR. */
    public static final String TOOLBAR_SEPARATOR = "ToolbarSeparator";

    public static final String FILL_ITEM = "FillItem";

    public static final String POWERED_BY = "PoweredBy";

    /** The vp. */
    protected VerticalPanel vp;

    /** The tool bar. */
    private ToolBar toolBar;

    /** The map layout widget. */
    private MapLayoutWidget mapLayoutWidget;

    private ButtonBarObserver buttonBarObserver;

    /**
     * Instantiates a new button bar.
     */
    public ButtonBar()
    {
        super();
        this.vp = new VerticalPanel();
        this.setToolBar(new ToolBar());
        this.getToolBar().setHeight(60);
        this.setButtonBarObserver(new ButtonBarObserver());
    }

    /**
     * Instantiates a new button bar.
     *
     * @param mapLayoutWidget
     *            the map layout widget
     * @throws ClassNotFoundException
     */
    public ButtonBar(MapLayoutWidget mapLayoutWidget)
    {
        this();
        this.setMapLayoutWidget(mapLayoutWidget);
    }

    /**
     * Initialize.
     * @throws ClassNotFoundException
     */
    protected abstract void initialize();

    /**
     * Gets the tool bar.
     *
     * @return the tool bar
     */
    public ToolBar getToolBar()
    {
        return toolBar;
    }

    /**
     * Sets the map layout widget.
     *
     * @param mapLayoutWidget
     *            the new map layout widget
     * @throws ClassNotFoundException
     */
    public void setMapLayoutWidget(MapLayoutWidget mapLayoutWidget)
    {
        if (this.mapLayoutWidget == null)
        {
            this.mapLayoutWidget = mapLayoutWidget;
            initialize();
        }
    }

    /**
     * Gets the map layout widget.
     *
     * @return the map layout widget
     */
    public MapLayoutWidget getMapLayoutWidget()
    {
        return mapLayoutWidget;
    }

    /**
     * Sets the tool bar.
     *
     * @param toolBar
     *            the new tool bar
     */
    public void setToolBar(ToolBar toolBar)
    {
        this.toolBar = toolBar;
    }

    /**
     * @param buttonBarObserver the buttonBarObserver to set
     */
    public void setButtonBarObserver(ButtonBarObserver buttonBarObserver)
    {
        this.buttonBarObserver = buttonBarObserver;
    }

    /**
     * @return the buttonBarObserver
     */
    public ButtonBarObserver getButtonBarObserver()
    {
        return buttonBarObserver;
    }
}
