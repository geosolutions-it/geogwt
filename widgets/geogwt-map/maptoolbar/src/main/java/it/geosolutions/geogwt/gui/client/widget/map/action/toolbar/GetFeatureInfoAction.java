/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.GetFeatureInfoAction,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 18-apr-2012 16.59.50 $
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


/**
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class GetFeatureInfoAction extends ToolbarMapAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3379150418993037442L;

	/**
     * Instantiates a get feature info action.
     */
    public GetFeatureInfoAction() {
        super();
    }

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.client.configuration.ToolbarAction#initialize()
     */
    @Override
    public boolean initialize() {
        if (!isInitialized()) {
            setTooltip(I18nProvider.getMessages().featureInfoTip());
            setIcon(Resources.ICONS.info());
            this.initialiazed = true;
        } 
        
        return isInitialized();
    }

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.client.configuration.ToolbarAction#performAction(com.extjs.gxt.ui.client.widget.button.Button)
     */
    @Override
    public void performAction(Button button) {
        if (((ToggleButton)button).isPressed()) {
            Dispatcher.forwardEvent(GeoGWTEvents.ACTIVATE_GET_FEATURE_INFO);
        } else {
            Dispatcher.forwardEvent(GeoGWTEvents.DEACTIVATE_GET_FEATURE_INFO);
        }        
    }
}
