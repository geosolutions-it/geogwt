/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.SearchFilterField,v. 0.1 25-gen-2011 11.24.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.24.44 $
 *
 * ====================================================================
 *
 * Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
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

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.IconSupport;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchFilterField.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class SearchFilterField<T extends ModelData> extends StoreFilterField<T> implements
        IconSupport {

    /** The style. */
    private String style;

    /** The icon. */
    protected AbstractImagePrototype icon;

    /**
     * Instantiates a new search filter field.
     */
    public SearchFilterField() {
        this.style = "x-menu-item";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.extjs.gxt.ui.client.widget.form.StoreFilterField#doSelect(com.extjs.gxt.ui.client.store
     * .Store, com.extjs.gxt.ui.client.data.ModelData, com.extjs.gxt.ui.client.data.ModelData,
     * java.lang.String, java.lang.String)
     */
    @Override
    protected abstract boolean doSelect(Store<T> store, T parent, T record, String property,
            String filter);

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.widget.IconSupport#getIcon()
     */
    public AbstractImagePrototype getIcon() {
        return icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.extjs.gxt.ui.client.widget.IconSupport#setIcon(com.google.gwt.user.client.ui.
     * AbstractImagePrototype)
     */
    public void setIcon(AbstractImagePrototype icon) {
        if (rendered) {
            El oldIcon = el().selectNode(style);
            if (oldIcon != null) {
                oldIcon.remove();
            }
            if (icon != null) {
                Element e = icon.createElement().cast();
                El.fly(e).addStyleName(style);
                el().insertChild(e, 0);
            }
        }
        this.icon = icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.widget.IconSupport#setIconStyle(java.lang.String)
     */
    public void setIconStyle(String icon) {
        setIcon(IconHelper.create(icon));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.widget.form.TriggerField#afterRender()
     */
    @Override
    protected void afterRender() {
        super.afterRender();
        setIcon(icon);
    }

}