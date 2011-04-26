/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.MapToolBar,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.map;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.GeoGWTUtils;
import it.geosolutions.geogwt.gui.client.configuration.ActionClientTool;
import it.geosolutions.geogwt.gui.client.configuration.GenericClientTool;
import it.geosolutions.geogwt.gui.client.configuration.ToolbarAction;
import it.geosolutions.geogwt.gui.client.widget.GeoGWTButton;
import it.geosolutions.geogwt.gui.client.widget.GeoGWTButton.ButtonType;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarActionRegistry;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarMapAction;

import java.util.LinkedList;
import java.util.List;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.WidgetComponent;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class MapToolBar.
 */
public class MapToolBar extends ButtonBar {

    private List<ToolbarMapAction> actions = new LinkedList<ToolbarMapAction>();
    
    /**
     * Instantiates a new map tool bar.
     */
    public MapToolBar() {
        super();
    }
    
    /**
     * Instantiates a new map tool bar.
     * 
     * @param mapLayoutWidget
     *            the map layout widget
     * @throws ClassNotFoundException 
     */
    public MapToolBar(MapLayoutWidget mapLayoutWidget) {
        super(mapLayoutWidget);
    }

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.client.widget.map.ButtonBar#initialize()
     */
    protected void initialize() {
        if (this.getMapLayoutWidget().getTools() == null) {
            this.getMapLayoutWidget().setTools(GeoGWTUtils.getInstance().getGlobalConfiguration()
                    .getToolbarItemManager().getClientTools());
        }
        
        try {
            List<GenericClientTool> tools = this.getMapLayoutWidget().getTools();
            for (GenericClientTool tool : tools) {
                String id = tool.getId();
                if (id.equals(TOOLBAR_SEPARATOR)) {
                    addSeparator();
                } else if (id.equals(FILL_ITEM)) {
                    addFillItem();
                } else if (id.equals(POWERED_BY)) {
                    addPoweredBy();
                } else if (tool instanceof ActionClientTool) {
                    ActionClientTool actionTool = (ActionClientTool) tool;
                    ToolbarAction action = ToolbarActionRegistry.get(actionTool.getId());

                    if (action instanceof ToolbarMapAction) {
                        
                        GeoGWTButton button = new GeoGWTButton(ButtonType.getValue(((ActionClientTool) tool).getType()));
                        action.setButton(button);
                        action.setId(tool.getId());
                        
                        button.getButton().setId(tool.getId());
                        button.getButton().setWidth(24);
                        button.getButton().addListener(Events.Select, action);
                        
                        boolean enabled = ((ActionClientTool)tool).isEnabled();
                        button.getButton().setEnabled(enabled);
                        action.setEnabled(enabled);
                        ((ToolbarMapAction)action).setMapWidget(this.getMapLayoutWidget().getMapWidget());

                        button.getButton().setToolTip(((ToolbarMapAction)action).getTooltip());
                        button.getButton().setIcon(action.getIcon());

                        this.getToolBar().add(button.getButton());
                        button.addObserver(this.getButtonBarObserver());
                        
                        this.actions.add((ToolbarMapAction) action);
                    }
                }
            }
            vp.add(getToolBar());
            add(vp);
        } catch (Exception e) {
            Dispatcher.forwardEvent(GeoGWTEvents.SEND_ALERT_MESSAGE, new String[] {"GeoGWT Init", "Exception occurred creating ToolBar"});
        }
    }

    /**
     * Adds the separator.
     */
    public void addSeparator() {
        this.getToolBar().add(new SeparatorToolItem());
    }

    /**
     * 
     */
    private void addFillItem() {
        this.getToolBar().add(new FillToolItem());
    }

    private void addPoweredBy() {
        Image poweredByImage = new Image(GWT.getModuleBaseURL() +  "img/poweredby.png");
        WidgetComponent poweredByWidget = new WidgetComponent(poweredByImage);
        this.getToolBar().add(poweredByWidget);
    }

    /**
     * 
     * @param be
     */
    public void handleEvent(BaseEvent be) {
        if (be.getType() == GeoGWTEvents.TOGGLE_BUTTON_PRESSED) {
            GeoGWTButton toggled = ((AppEvent)be).getData();
            
            for (ToolbarMapAction a : this.actions) {
                if (!a.getId().equals(toggled.getButton().getId())
                        && a.getButton().getType() == ButtonType.TOGGLE) {
                    ToggleButton toggleBtn = (ToggleButton) a.getButton().getButton();
                    
                    toggleBtn.toggle(false);
                    a.performAction(toggleBtn);
                }
            }
        }
    };
        
}