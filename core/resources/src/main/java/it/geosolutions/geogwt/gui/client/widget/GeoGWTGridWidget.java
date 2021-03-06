/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.GeoGWTGridWidget,v. 0.1 7-apr-2011 16.58.11 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.58.11 $
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
package it.geosolutions.geogwt.gui.client.widget;

import java.util.List;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;


// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTGridWidget.
 *
 * @param <T>
 *            the generic type
 */
public abstract class GeoGWTGridWidget<T extends BaseModel>
{

    /** The store. */
    protected ListStore<T> store;

    /** The tool bar. */
    private PagingToolBar toolBar;

    /** The grid. */
    protected Grid<T> grid;

    /** */
    private int gridDimension;

    /**
     * Instantiates a new geo gwt grid widget.
     *
     * @param pageSize
     */
    public GeoGWTGridWidget(int pageSize, int gridDimension)
    {
        this.toolBar = new PagingToolBar(pageSize);
        this.setGridDimension(gridDimension);

        createStore();
        initGrid();
    }

    /**
     * Instantiates a new geo gwt grid widget.
     *
     * @param models
     *            the models
     */
    public GeoGWTGridWidget(List<T> models, int pageSize, int gridDimension)
    {
        this.toolBar = new PagingToolBar(pageSize);
        this.setGridDimension(gridDimension);

        createStore();
        this.store.add(models);
        initGrid();
    }

    /**
     * Inits the grid.
     */
    protected void initGrid()
    {
        ColumnModel cm = prepareColumnModel();

        grid = new Grid<T>(store, cm);
        grid.setBorders(true);

        grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        grid.setAutoWidth(true);

        grid.setLoadMask(true);

        grid.setLazyRowRender(0);

        setGridProperties();
    }

    /**
     * Clear grid elements.
     */
    public void clearGridElements()
    {
        this.store.removeAll();
        this.toolBar.clear();
        this.toolBar.disable();
    }

    /**
     * Sets the grid properties.
     */
    public abstract void setGridProperties();

    /**
     * Prepare column model.
     *
     * @return the column model
     */
    public abstract ColumnModel prepareColumnModel();

    /**
     * Sets the up load listener.
     */
    public abstract void setUpLoadListener();

    /**
     * Creates the store.
     */
    public abstract void createStore();

    /**
     * Gets the grid.
     *
     * @return the grid
     */
    public Grid<T> getGrid()
    {
        return grid;
    }

    /**
     * Gets the store.
     *
     * @return the store
     */
    public ListStore<T> getStore()
    {
        return store;
    }

    /**
     * @return the toolBar
     */
    public PagingToolBar getToolBar()
    {
        return toolBar;
    }

    /**
     * @param gridDimension the gridDimension to set
     */
    public void setGridDimension(int gridDimension)
    {
        this.gridDimension = gridDimension;
    }

    /**
     * @return the gridDimension
     */
    public int getGridDimension()
    {
        return gridDimension;
    }
}
