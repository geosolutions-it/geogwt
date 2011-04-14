/*
 * $ Header: it.geosolutions.geogwt.web.examples.client.Gridwidget,v. 0.1 14-apr-2011 10.00.37 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 14-apr-2011 10.00.37 $
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
package it.geosolutions.geogwt.web.examples.client;

import it.geosolutions.geogwt.gui.client.GeoGWTUtils;
import it.geosolutions.geogwt.gui.client.configuration.GeoGWTConfiguration;
import it.geosolutions.geogwt.gui.client.service.GeoGWTConfigurationRemote;
import it.geosolutions.geogwt.web.examples.client.service.LayersManagerServiceRemote;
import it.geosolutions.geogwt.web.examples.client.widget.LayerGridWidget;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class Gridwidget.
 */
public class Gridwidget implements EntryPoint {

    /** The main. */
    private ContentPanel main;

    /** The layer grid widget. */
    private LayerGridWidget layerGridWidget;

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    public void onModuleLoad() {
        GXT.hideLoadingPanel("loading");

        GeoGWTConfigurationRemote.Util.getInstance().initServerConfiguration(
        new AsyncCallback<GeoGWTConfiguration>() {

            public void onSuccess(GeoGWTConfiguration result) {
                GeoGWTUtils.getInstance().setGlobalConfiguration(result);
                
                /**
                 * Adding widgets to Viewport panels
                 */
                main = new ContentPanel();
                main.setLayout(new BorderLayout());
                main.setHeaderVisible(true);
                main.setHeading("GeoGWT GridWidget Example");

                createCenter();

                main.setWidth(558);
                main.setHeight(333);

                RootPanel.get("gridpanel").add(main);

            }

            public void onFailure(Throwable caught) {
                Info.display("Configuration Service Error", caught.getMessage());
            }
        });
    }

    /**
     * Creates the center.
     */
    private void createCenter() {
        ContentPanel layerGridPanel = new ContentPanel();

        layerGridPanel.setMonitorWindowResize(true);
        layerGridPanel.setHeaderVisible(false);
        layerGridPanel.setFrame(true);
        layerGridPanel.setLayout(new FitLayout());
        layerGridPanel.setScrollMode(Scroll.NONE);
        layerGridPanel.setAutoWidth(true);
        layerGridPanel.setHeight(333 - 25);

        this.layerGridWidget = new LayerGridWidget(10, 333, LayersManagerServiceRemote.Util
                .getInstance());

        layerGridPanel.setBottomComponent(this.layerGridWidget.getToolBar());
        layerGridPanel.add(this.layerGridWidget.getGrid());

        this.layerGridWidget.getLoader().load();
        
        main.add(layerGridPanel);
    }
}
