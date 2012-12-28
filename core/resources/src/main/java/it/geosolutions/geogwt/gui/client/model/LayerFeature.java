/*
 * $ Header: it.geosolutions.geogwt.gui.client.model.LayerFeatures,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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

import java.util.Map;

import com.extjs.gxt.ui.client.data.BeanModel;


/**
 * The Class LayerFeatures.
 * 
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class LayerFeature  extends BeanModel{
    
    /** serialVersionUID */
    private static final long serialVersionUID = 5886690680009393319L;

    private String featureId;
    
    private String geomWKT;
    
    private String geomType;
    
    private Map<String, Object> featureProperties;

    
    /**
     * The default constructor
     */
    public LayerFeature() {
        super();
    }

    /**
     * @param featureId
     * @param geomWKT
     * @param geomType
     */
    public LayerFeature(String featureId, String geomWKT, String geomType) {
        super();
        this.featureId = featureId;
        this.geomWKT = geomWKT;
        this.geomType = geomType;
    }

    /**
     * @return the featureId
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * @param featureId the featureId to set
     */
    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    /**
     * @return the geomWKT
     */
    public String getGeomWKT() {
        return geomWKT;
    }

    /**
     * @param geomWKT the geomWKT to set
     */
    public void setGeomWKT(String geomWKT) {
        this.geomWKT = geomWKT;
    }

    /**
     * @return the geomType
     */
    public String getGeomType() {
        return geomType;
    }

    /**
     * @param geomType the geomType to set
     */
    public void setGeomType(String geomType) {
        this.geomType = geomType;
    }

    /**
     * @return the featureProperties
     */
    public Map<String, Object> getFeatureProperties() {
        return featureProperties;
    }

    /**
     * @param featureProperties the featureProperties to set
     */
    public void setFeatureProperties(Map<String, Object> featureProperties) {
        this.featureProperties = featureProperties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((featureId == null) ? 0 : featureId.hashCode());
        result = prime * result + ((geomType == null) ? 0 : geomType.hashCode());
        result = prime * result + ((geomWKT == null) ? 0 : geomWKT.hashCode());
        result = prime * result + ((featureProperties == null) ? 0 : featureProperties.hashCode());
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
        LayerFeature other = (LayerFeature) obj;
        if (featureId == null) {
            if (other.featureId != null)
                return false;
        } else if (!featureId.equals(other.featureId))
            return false;
        if (geomType == null) {
            if (other.geomType != null)
                return false;
        } else if (!geomType.equals(other.geomType))
            return false;
        if (geomWKT == null) {
            if (other.geomWKT != null)
                return false;
        } else if (!geomWKT.equals(other.geomWKT))
            return false;
        if (featureProperties == null) {
            if (other.featureProperties != null)
                return false;
        } else if (!featureProperties.equals(other.featureProperties))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LayerFeature [featureId=" + featureId + ", geomWKT=" + geomWKT + ", geomType="
                + geomType +  ", featureProperties=" + featureProperties.toString() + "]";
    }

}
