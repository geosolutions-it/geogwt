/*
 * $ Header: it.geosolutions.geogwt.gui.client.model.GetFeatureInfoDetails,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.model;

import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.data.BeanModel;

/**
 * The Class GetFeatureInfoDetails.
 * 
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class GetFeatureInfoDetails extends BeanModel{

    /** serialVersionUID */
    private static final long serialVersionUID = -1084240765571920099L;

    private String geometryWKT;
    
    private String geoserverURL;
    
    private Map<String, List<LayerFeature>> infoDetails;
    
    private boolean clientReprojectBounds = false;

    
    /**
     * The default constructor
     */
    public GetFeatureInfoDetails() {
        super();
    }


    /**
     * @param geometryWKT
     * @param geoserverURL
     * @param infoDetails
     */
    public GetFeatureInfoDetails(String geometryWKT, String geoserverURL,
            Map<String, List<LayerFeature>> infoDetails) {
        super();
        this.geometryWKT = geometryWKT;
        this.geoserverURL = geoserverURL;
        this.infoDetails = infoDetails;
    }


    /**
     * @return the geometryWKT
     */
    public String getGeometryWKT() {
        return geometryWKT;
    }


    /**
     * @param geometryWKT the geometryWKT to set
     */
    public void setGeometryWKT(String geometryWKT) {
        this.geometryWKT = geometryWKT;
    }


    /**
     * @return the geoserverURL
     */
    public String getGeoserverURL() {
        return geoserverURL;
    }


    /**
     * @param geoserverURL the geoserverURL to set
     */
    public void setGeoserverURL(String geoserverURL) {
        this.geoserverURL = geoserverURL;
    }


    /**
     * @return the infoDetails
     */
    public Map<String, List<LayerFeature>> getInfoDetails() {
        return infoDetails;
    }


    /**
     * @param infoDetails the infoDetails to set
     */
    public void setInfoDetails(Map<String, List<LayerFeature>> infoDetails) {
        this.infoDetails = infoDetails;
    }
    
    /**
     * @return the clientReprojectBounds
     */
    public boolean isClientReprojectBounds() {
        return clientReprojectBounds;
    }

    /**
     * @param clientReprojectBounds the clientReprojectBounds to set
     */
    public void setClientReprojectBounds(boolean clientReprojectBounds) {
        this.clientReprojectBounds = clientReprojectBounds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((geometryWKT == null) ? 0 : geometryWKT.hashCode());
        result = prime * result + ((geoserverURL == null) ? 0 : geoserverURL.hashCode());
        result = prime * result + ((infoDetails == null) ? 0 : infoDetails.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GetFeatureInfoDetails other = (GetFeatureInfoDetails) obj;
        if (geometryWKT == null) {
            if (other.geometryWKT != null)
                return false;
        } else if (!geometryWKT.equals(other.geometryWKT))
            return false;
        if (geoserverURL == null) {
            if (other.geoserverURL != null)
                return false;
        } else if (!geoserverURL.equals(other.geoserverURL))
            return false;
        if (infoDetails == null) {
            if (other.infoDetails != null)
                return false;
        } else if (!infoDetails.equals(other.infoDetails))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "GetFeatureInfoDetails [geometryWKT=" + geometryWKT + ", geoserverURL="
                + geoserverURL + ", infoDetails=" + infoDetails + "]";
    }


}
