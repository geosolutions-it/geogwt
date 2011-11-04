/*
 * $ Header: it.geosolutions.geogwt.gui.client.ContentType,v. 0.1 7-apr-2011 16.58.12 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client;

import com.extjs.gxt.ui.client.data.BeanModel;


// TODO: Auto-generated Javadoc
/**
 * The Class ContentType.
 */
public class ContentType extends BeanModel
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5218642838039771231L;

    /**
     * The Enum ContentTypeEnum.
     */
    public enum ContentTypeEnum
    {

        /** The TYPE. */
        TYPE("contentType");

        /** The value. */
        private String value;

        /**
         * Instantiates a new content type enum.
         *
         * @param value
         *            the value
         */
        ContentTypeEnum(String value)
        {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue()
        {
            return value;
        }
    }

    /**
     * Instantiates a new content type.
     *
     * @param type
     *            the type
     */
    public ContentType(String type)
    {
        setType(type);
    }

    /**
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(String type)
    {
        set(ContentTypeEnum.TYPE.getValue(), type);
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType()
    {
        return get(ContentTypeEnum.TYPE.getValue());
    }
}
