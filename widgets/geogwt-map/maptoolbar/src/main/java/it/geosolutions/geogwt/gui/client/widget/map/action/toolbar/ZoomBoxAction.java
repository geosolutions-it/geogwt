/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomBoxAction,v. 0.1 8-apr-2011 15.48.16 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 8-apr-2011 15.48.16 $
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
package it.geosolutions.geogwt.gui.client.widget.map.action.toolbar;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.Resources;
import it.geosolutions.geogwt.gui.client.i18n.I18nProvider;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarMapAction;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;

// TODO: Auto-generated Javadoc
/**
 * The Class ZoomOutAction.
 */
public class ZoomBoxAction extends ToolbarMapAction {

    /**
     * 
     */
    private static final long serialVersionUID = -4825830813729418173L;

    /**
     * Instantiates a new zoom out action.
     * 
     * @param mapWidget
     *            the map widget
     */
    public ZoomBoxAction() {
        super();
    }

    @Override
    public boolean initialize() {
        if (!isInitialized()) {
            setTooltip(I18nProvider.getMessages().zoomBoxToolTip());
            setIcon(Resources.ICONS.zoomBox());
            this.initialiazed = true;
        } 
        
        return isInitialized();
    }

    @Override
    public void performAction(Button button) {
        if (((ToggleButton)button).isPressed()) {
            Dispatcher.forwardEvent(GeoGWTEvents.ACTIVATE_ZOOM_BOX);
        } else {
            Dispatcher.forwardEvent(GeoGWTEvents.DEACTIVATE_ZOOM_BOX);
        }
    }
}