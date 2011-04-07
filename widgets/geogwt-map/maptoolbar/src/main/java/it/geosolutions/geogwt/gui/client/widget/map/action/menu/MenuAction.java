/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.action.menu.MenuAction,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.map.action.menu;

import it.geosolutions.geogwt.gui.client.model.Category;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuAction.
 */
public abstract class MenuAction extends SelectionListener<MenuEvent> {

    /** The category. */
    private Category category;

    /** The title. */
    private String title;

    /** The id. */
    private String id;

    /** The enabled. */
    private boolean enabled;

    /**
     * Instantiates a new menu action.
     * 
     * @param title
     *            the title
     * @param category
     *            the category
     */
    public MenuAction(String title, Category category) {
        this.title = title;
        this.category = category;
    }

    /**
     * Gets the category.
     * 
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category.
     * 
     * @param category
     *            the new category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Checks if is the enabled.
     * 
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled.
     * 
     * @param enabled
     *            the new enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
