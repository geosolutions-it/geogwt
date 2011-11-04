/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.binding.GeoGWTFieldBinding,v. 0.1 7-apr-2011 16.58.11 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.binding;

import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.widget.form.Field;


// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTFieldBinding.
 */
public class GeoGWTFieldBinding extends FieldBinding
{

    /** The old value. */
    private Object oldValue;

    /**
     * Instantiates a new geo gwt field binding.
     *
     * @param field
     *            the field
     * @param property
     *            the property
     */
    @SuppressWarnings("rawtypes")
    public GeoGWTFieldBinding(Field field, String property)
    {
        super(field, property);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.extjs.gxt.ui.client.binding.FieldBinding#updateField(boolean)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void updateField(boolean updateOriginalValue)
    {
        Object val = onConvertModelValue(model.get(property));

        if (oldValue == null)
        {
            oldValue = val;
        }

        field.setValue(val);
        if (updateOriginalValue)
        {
            field.setOriginalValue(val);
        }
    }

    /**
     * Reset value.
     */
    @SuppressWarnings("unchecked")
    public void resetValue()
    {
        oldValue = onConvertModelValue(oldValue);

        field.setValue(oldValue);

        model.set(property, oldValue.toString());
    }

}
