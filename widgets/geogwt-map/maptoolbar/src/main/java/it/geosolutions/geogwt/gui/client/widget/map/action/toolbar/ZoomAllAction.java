/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.toolbar.ZoomInAction,v. 0.1 8-apr-2011 15.48.16 created by afabiani <alessio.fabiani at geo-solutions.it> $
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

import it.geosolutions.geogwt.gui.client.Resources;
import it.geosolutions.geogwt.gui.client.i18n.I18nProvider;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarMapAction;

import com.extjs.gxt.ui.client.widget.button.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class ZoomInAction.
 */
public class ZoomAllAction extends ToolbarMapAction {
    /**
     * 
     */
    private static final long serialVersionUID = 2863609657536670127L;

    /**
     * Instantiates a new zoom in action.
     * 
     * @param mapWidget
     *            the map widget
     */
    public ZoomAllAction() {
        super();
    }

    @Override
    public boolean initialize() {
        if (!isInitialized()) {
            setTooltip(I18nProvider.getMessages().zoomAllToolTip());
            setIcon(Resources.ICONS.world());
            this.initialiazed = true;
        }

        return isInitialized();
    }

    @Override
    public void performAction(Button button) {
        this.getMapWidget().getMap().zoomToMaxExtent();
    }
}