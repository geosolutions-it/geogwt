/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarAction,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.configuration;

import java.io.Serializable;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

import it.geosolutions.geogwt.gui.client.widget.GeoGWTButton;


// TODO: Auto-generated Javadoc
/**
 * The Class ToolbarAction.
 */
public abstract class ToolbarAction implements Listener<BaseEvent>, Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 3914442116826097873L;

    private AbstractImagePrototype icon;

    private GeoGWTButton button;

    /** The id. */
    private String id;

    /** The enabled. */
    private boolean enabled;

    protected boolean initialiazed = false;

    /**
     *
     */
    public ToolbarAction()
    {
        super();
    }

    /**
     *
     * @return
     */
    public boolean isInitialized()
    {
        return this.initialiazed;
    }

    public abstract boolean initialize();

    public abstract void performAction(Button button);

    /*
     * (non-Javadoc)
     *
     * @see
     * com.extjs.gxt.ui.client.event.Listener#handleEvent(com.extjs.gxt.ui.client.event.BaseEvent)
     */
    public void handleEvent(BaseEvent baseEvent)
    {
        getButton().isPressed();
        getButton().notifyObservers();

        Button button = (Button) baseEvent.getSource();

        performAction(button);
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Checks if is the enabled.
     *
     * @return the enabled
     */
    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled
     *            the new enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(AbstractImagePrototype icon)
    {
        this.icon = icon;
    }

    /**
     * @return the icon
     */
    public AbstractImagePrototype getIcon()
    {
        return icon;
    }

    /**
     * @param button the button to set
     */
    public void setButton(GeoGWTButton button)
    {
        this.button = button;
    }

    /**
     * @return the button
     */
    public GeoGWTButton getButton()
    {
        return button;
    }

}
