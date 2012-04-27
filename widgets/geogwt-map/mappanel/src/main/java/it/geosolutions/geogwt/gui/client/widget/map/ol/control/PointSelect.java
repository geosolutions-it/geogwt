/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelect,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget.map.ol.control;

import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.control.Control;
import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class PointSelect  extends Control
{
    /**
     * @param element
     */
    protected PointSelect(JSObject element)
    {
        super(element);
    }

    /**
     * 
     */
    public PointSelect()
    {
        this(PointSelectImpl.create(null));
    }

    /**
     * @param options
     */
    public PointSelect(PointSelectOptions options)
    {
        this(PointSelectImpl.create(options.getJSObject()));
    }

    /**
     * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
     *
     */
    public interface PointSelectListener
    {
        /**
         * @param lonlat
         */
        void onPointSelected(LonLat lonlat);
    }
}
