/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2011, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package it.geosolutions.geogwt.gui.client.widget.map;

import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;
import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * @author Tobia Di Pisa
 *
 */
public class WMSLayer extends WMS{
    
    private String crs;

    /**
     * @param wms
     */
    public WMSLayer(JSObject wms, String crs) {
        super(wms);
        this.crs = crs;
    }


    /**
     * @param name
     * @param url
     * @param params
     * @param layerParams
     */
    public WMSLayer(String name, String url, WMSParams params, WMSOptions layerParams, String crs) {
        super(name, url, params, layerParams);
        this.crs = crs;
    }


    /**
     * @param name
     * @param url
     * @param params
     */
    public WMSLayer(String name, String url, WMSParams params, String crs) {
        super(name, url, params);
        this.crs = crs;    
    }


    /**
     * @param name
     * @param urls
     * @param params
     * @param layerParams
     */
    public WMSLayer(String name, String[] urls, WMSParams params, WMSOptions layerParams, String crs) {
        super(name, urls, params, layerParams);
        this.crs = crs;
    }


    /**
     * @param name
     * @param urls
     * @param params
     */
    public WMSLayer(String name, String[] urls, WMSParams params, String crs) {
        super(name, urls, params);
        this.crs = crs;
    }


    /**
     * @return the crs
     */
    public String getCrs() {
        return crs;
    }

    /**
     * @param crs the crs to set
     */
    public void setCrs(String crs) {
        this.crs = crs;
    }
   
}
