/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.tab.TabWidget,v. 0.1 7-apr-2011 16.58.12 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.58.12 $
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
package it.geosolutions.geogwt.gui.client.widget.tab;

//import it.geosolutions.geogwt.gui.client.AdministrationMode;
import it.geosolutions.geogwt.gui.client.GeoGWTEvents;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class TabWidget.
 */
public class TabWidget extends TabPanel implements Listener {

    /**
     * Instantiates a new tab widget.
     */
    public TabWidget() {
        super();
        initTab();
        initTabItem();

        addListener(GeoGWTEvents.INIT, this);
    }

    /**
     * Inits the tab.
     */
    private void initTab() {
        setAutoWidth(true);
    }

    /**
     * Inits the tab item.
     */
    private void initTabItem() {
        super.doLayout();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.extjs.gxt.ui.client.event.Listener#handleEvent(com.extjs.gxt.ui.client.event.BaseEvent)
     */
    public void handleEvent(BaseEvent e) {
    }

    /**
     * Forward to all tabs.
     * 
     * @param event
     *            the event
     */
    private void forwardToAllTabs(AppEvent event) {
        for (TabItem tabItem : this.getItems()) {
            tabItem.fireEvent(event.getType(), event);
        }
    }

}
