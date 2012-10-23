/*
 * $ Header: it.geosolutions.geogwt.gui.service.impl.GeoGWTRemoteServiceImpl,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.service.impl;

import it.geosolutions.geogwt.gui.client.model.GetFeatureInfoDetails;
import it.geosolutions.geogwt.gui.client.model.LayerFeature;
import it.geosolutions.geogwt.gui.service.IGeoGWTRemoteService;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wfs.WFSDataStore;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.geotools.data.wms.WMSUtils;
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.request.GetFeatureInfoRequest;
import org.geotools.data.wms.request.GetMapRequest;
import org.geotools.data.wms.response.GetFeatureInfoResponse;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.gml2.GMLConfiguration;
import org.geotools.ows.ServiceException;
import org.geotools.referencing.CRS;
import org.geotools.xml.Configuration;
import org.geotools.xml.Parser;
import org.opengis.feature.Feature;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.spatial.BBOX;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;

/**
 * The Class GeoGWTRemoteServiceImpl.
 * 
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 * 
 */
public class GeoGWTRemoteServiceImpl implements IGeoGWTRemoteService {

    /** The logger. */
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private String geoServerUrl;

    private String geoServerUser;

    private String geoServerPassword;

    private String wmsVersion = "1.0.0";

    private String wfsVersion = "1.1.1";

    private int featureInfoCount = 25;

    private final WFSDataStoreFactory dsf;

    private final WKTReader reader;
    
    private int bufferRange = 2;
    
    /*
     * Use "EPSG:3857" for Google CRS 900913
     */
    private String getFeatureCRS = "EPSG:4326";
    
    private String propertyNames;

    /**
     * 
     */
    public GeoGWTRemoteServiceImpl() {
        super();
        this.dsf = new WFSDataStoreFactory();
        this.reader = new WKTReader();
    }

    /**
     * @param geometryWKT
     * @param layersIDs
     * @param crsCode
     * @return GetFeatureInfoDetails
     * @throws Exception
     */
    public GetFeatureInfoDetails getFeatures(String geometryWKT, List<String> layersIDs,
            String crsCode) throws Exception {
        String logMsg = "Starting GetFeature at: " + this.geoServerUrl;

        if(logger.isLoggable(Level.INFO))
            logger.info(logMsg);
 
        // ////////////////////////////////////////////////////////
        // Use GT compliant "EPSG:3857" for Google CRS 900913
        // ////////////////////////////////////////////////////////
        final CoordinateReferenceSystem crs = this.getFeatureCRS.equals("EPSG:900913") ? CRS.decode("EPSG:3857", true) : CRS.decode(this.getFeatureCRS, true);

        //
        // Build WFS capabilities url string.
        //
        String capabilitiesUrl = this.geoServerUrl + "/wfs?REQUEST=GetCapabilities&version="
                + this.wfsVersion;

        Map<String, Serializable> params = new HashMap<String, Serializable>();
        final URL url = new URL(capabilitiesUrl);
        params.put(WFSDataStoreFactory.URL.key, url);

        WFSDataStore data = null;

        //
        // Preparing feature info details
        //
        GetFeatureInfoDetails details = null;
        try {

            if (layersIDs != null && layersIDs.size() > 0) {

                data = dsf.createDataStore(params);

                Map<String, List<LayerFeature>> infoDetails = new HashMap<String, List<LayerFeature>>();

                //
                // Parsing the geometry and building the bbox.
                //
                Geometry geometry = reader.read(geometryWKT);
                
                if(!this.getFeatureCRS.equals(crsCode)){
                    boolean lenient = true; // allow for some error due to different datums
                    
                    crsCode = crsCode.equals("EPSG:900913") ? "EPSG:3857" : crsCode;
                    MathTransform transform = CRS.findMathTransform(CRS.decode(crsCode, true), crs, lenient);
                    
                    geometry = JTS.transform(geometry, transform);
                    
                    //
                    // take only the CRS integer code.
                    //
                    geometry.setSRID((this.getFeatureCRS.indexOf(":") != -1) ? Integer.valueOf(this.getFeatureCRS.split(":")[1]) : Integer.valueOf(this.getFeatureCRS));   
                    geometry = geometry.getEnvelope();
                }else{
                    //
                    // take only the CRS integer code.
                    //
                    geometry.setSRID((crsCode.indexOf(":") != -1) ? Integer.valueOf(crsCode.split(":")[1]) : Integer.valueOf(crsCode));                    
                }

                Envelope envelope = geometry.getEnvelopeInternal();
                ReferencedEnvelope bbox = new ReferencedEnvelope(envelope.getMinX(),
                        envelope.getMaxX(), envelope.getMinY(), envelope.getMaxY(), crs);
                
                // ///////////////////////////////////////////////////////////////////////////
                // ReferencedEnvelope bbox = new ReferencedEnvelope(envelope.getMinX(),
                // envelope.getMaxX(), envelope.getMinY(), envelope.getMaxY(),
                // DefaultGeographicCRS.WGS84);
                // ///////////////////////////////////////////////////////////////////////////

                //
                // One cycle for each layer
                //
                Iterator<String> itarator = layersIDs.iterator();
                while (itarator.hasNext()) {

                    //
                    // Get the feature type schema
                    //
                    String layerName = itarator.next();
                    SimpleFeatureType schema = data.getSchema(layerName);

                    //
                    // Get the source of the feature type
                    //
                    FeatureSource<SimpleFeatureType, SimpleFeature> source = data
                            .getFeatureSource(layerName);

                    //
                    // Preparing data for the geometry filter
                    //
                    String geomName = schema.getGeometryDescriptor().getLocalName();

                    FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(GeoTools
                            .getDefaultHints());

                    //
                    // Building the geometry filter and start the query to GeoServer WFS
                    //
                    if (geometry instanceof Polygon) {
                        BBOX filter = ff.bbox(ff.property(geomName), bbox);

                        //
                        // set properties to get
                        //
                        String[] properties = this.propertyNames.split(",");
                        int size = properties.length;
                        
                        String[] p = new String[size + 1];
                        p[0] = geomName;
                        
                        for(int i=1; i<size; i++){
                            if(properties[i] != null){
                                p[i] = properties[i];
                            }
                        }
                        
                        Query query = new Query(layerName, filter, p);
                        query.setCoordinateSystem(crs);
                        
                        // ///////////////////////////////////////////////////////////////////////////
                        // Query query = new Query(layerName, filter, new String[] { geomName });
                        // query.setCoordinateSystem(DefaultGeographicCRS.WGS84);
                        // ///////////////////////////////////////////////////////////////////////////

                        FeatureCollection<SimpleFeatureType, SimpleFeature> features = source
                                .getFeatures(query);
                        FeatureIterator<SimpleFeature> featureIterator = features.features();

                        //
                        // Getting layer features
                        //
                        try {
                            
                            if(logger.isLoggable(Level.INFO))
                                logger.info("Reading Features ...");
                            
                            List<LayerFeature> layerFeatures = new ArrayList<LayerFeature>();

                            while (featureIterator.hasNext()) {
                                Feature feature = (Feature) featureIterator.next();

                                LayerFeature layerFeature = makeLayerFeature(feature);
                                layerFeatures.add(layerFeature);

                                infoDetails.put(layerName, layerFeatures);
                            }
                        } finally {
                            if (featureIterator != null)
                                featureIterator.close();
                        }
                    }
                }

                //
                // Instantiate the info details
                //
                details = new GetFeatureInfoDetails(geometry.toText(), this.geoServerUrl, infoDetails);
                details.setClientReprojectBounds(false);
            }

        } catch (ParseException e) {
            logger.log(Level.FINER, e.getMessage(), e);
            throw new Exception(e.getMessage());
        } catch (MismatchedDimensionException e) {
            logger.log(Level.FINER, e.getMessage(), e);
            throw new Exception(e.getMessage());
        } finally {
            if (data != null)
                data.dispose();
        }

        return details;
    }

    /**
     * @param mapHeight
     * @param mapWidth
     * @param bbox
     * @param layersIDs
     * @param wkt
     * @param x
     * @param y
     * @param crsCode
     * @param resolution
     * @return GetFeatureInfoDetails
     * @throws Exception
     */
    public GetFeatureInfoDetails getFeatureInfo(int mapHeight, int mapWidth, String bbox,
            List<String> layersIDs, String wkt, int x, int y, String crsCode, double resolution)
            throws Exception {
        String logMsg = "Starting GetFeatureInfo at: " + this.geoServerUrl;
        
        if(logger.isLoggable(Level.INFO))
            logger.info(logMsg);

        URL url = null;
        try {
            url = new URL(this.geoServerUrl + "/wms?REQUEST=GetCapabilities&VERSION="
                    + this.wmsVersion);
        } catch (MalformedURLException e) {
            logger.log(Level.FINER, e.getMessage(), e);
            throw new Exception(e.getMessage());
        }

        WebMapServer wms = null;
        GetFeatureInfoDetails details = null;

        try {
            if (layersIDs != null && layersIDs.size() > 0) {

                wms = new WebMapServer(url);
                WMSCapabilities capabilities = wms.getCapabilities();

                Map<String, List<LayerFeature>> infoDetails = new HashMap<String, List<LayerFeature>>();

                if (capabilities.getRequest().getGetFeatureInfo() != null) {

                    // This server supports GetFeatureInfo requests!
                    // We could make one if we wanted to.

                    Layer[] layers = WMSUtils.getNamedLayers(capabilities);

                    //
                    // One cycle for each layer
                    //
                    Iterator<String> itarator = layersIDs.iterator();

                    while (itarator.hasNext()) {
                        String layerName = itarator.next();

                        GetMapRequest request = wms.createGetMapRequest();

                        Set<Layer> queryLayers = new HashSet<Layer>();
                        for (int k = 0; k < layers.length; k++) {
                            if (layerName.equals(layers[k].getName())) {
                                request.addLayer(layers[k]);
                                request.setBBox(bbox);
                                request.setVersion(this.wmsVersion);
                                request.setSRS(crsCode);
                                request.setFormat("image/png");
                                request.setDimensions(mapWidth, mapHeight);

                                queryLayers.add(layers[k]);
                                break;
                            }
                        }

                        GetFeatureInfoRequest infoRequest = wms
                                .createGetFeatureInfoRequest(request);
                        infoRequest.setInfoFormat("application/vnd.ogc.gml");
                        infoRequest.setQueryPoint(x, y);
                        infoRequest.setQueryLayers(queryLayers);
                        infoRequest.setFeatureCount(featureInfoCount);

                        GetFeatureInfoResponse infoResponse = wms.issueRequest(infoRequest);

                        //
                        // Parse the returned GML format
                        //
                        FeatureIterator<?> featureIterator = null;
                        InputStream xml = null;

                        try {
                            
                            if(logger.isLoggable(Level.INFO))
                                logger.info("Parsing GML response ...");
                            
                            //
                            // the xml instance document above
                            //
                            xml = infoResponse.getInputStream();

                            //
                            // create the parser with the gml 2.0 configuration
                            //
                            Configuration configurationGML2 = new GMLConfiguration();
                            Parser parser = new Parser(configurationGML2);

                            //
                            // Parsing the collection
                            //
                            FeatureCollection<?, ?> fc = (FeatureCollection<?, ?>) parser
                                    .parse(xml);
                            featureIterator = fc.features();

                            List<LayerFeature> layerFeatures = new ArrayList<LayerFeature>();

                            if(logger.isLoggable(Level.INFO))
                                logger.info(logMsg);
                            
                            while (featureIterator.hasNext()) {
                                Feature feature = (Feature) featureIterator.next();

                                LayerFeature layerFeature = makeLayerFeature(feature);
                                layerFeatures.add(layerFeature);

                                infoDetails.put(layerName, layerFeatures);
                            }

                        } catch (Exception e) {
                            logger.log(Level.FINER, e.getMessage(), e);
                            throw new Exception(e.getMessage());
                        } finally {
                            if (featureIterator != null)
                                featureIterator.close();

                            if (xml != null)
                                xml.close();
                        }
                    }
                }

                //
                // Instantiate the info details
                //
                Geometry geometry = reader.read(wkt);
                geometry = geometry.buffer(bufferRange * resolution);

                details = new GetFeatureInfoDetails(geometry.toText(), this.geoServerUrl,
                        infoDetails);
                details.setClientReprojectBounds(true);
            }

        } catch (IOException e) {
            // There was an error communicating with the server
            // For example, the server is down
            logger.log(Level.FINER, e.getMessage(), e);
            throw new Exception(e.getMessage());
        } catch (ServiceException e) {
            // The server returned a ServiceException (unusual in this case)
            logger.log(Level.FINER, e.getMessage(), e);
            throw new Exception(e.getMessage());
        } catch (SAXException e) {
            // Unable to parse the response from the server
            // For example, the capabilities it returned was not valid
            logger.log(Level.FINER, e.getMessage(), e);
            throw new Exception(e.getMessage());
        }

        return details;
    }

    /**
     * @param feature
     * @return LayerFeature
     */
    private LayerFeature makeLayerFeature(Feature feature) {
        String featureId = feature.getIdentifier().getID();

        //
        // For performance reasons we save only the centroid.
        //
        String geomWKT = ((Geometry) feature.getDefaultGeometryProperty().getValue()).getCentroid()
                .toText();
        // String geomWKT = feature.getDefaultGeometryProperty().getValue()
        // .toString();

        GeometryType geomType = feature.getDefaultGeometryProperty().getType();

        String type = null;
        if (geomType.getBinding().equals(Point.class)
                || geomType.getBinding().equals(MultiPoint.class))
            type = "OpenLayers.Geometry.Point";
        else if (geomType.getBinding().equals(Polygon.class)
                || geomType.getBinding().equals(MultiPolygon.class))
            type = "OpenLayers.Geometry.Polygon";
        else if (geomType.getBinding().equals(LineString.class)
                || geomType.getBinding().equals(MultiLineString.class))
            type = "OpenLayers.Geometry.LineString";

        LayerFeature layerFeature = new LayerFeature(featureId, geomWKT, type);
        
        if(this.propertyNames != null){
            Map<String, Object> featureProperties = new HashMap<String, Object>();
            
            String[] pnames = this.propertyNames.split(",");
            int size = pnames.length;
            for(int i=0; i< size; i++){
                Property p = feature.getProperty(pnames[i]);
                if(p != null)
                    featureProperties.put(p.getName().toString(), p.getValue());
            } 
            
            if(featureProperties.size() > 0)
                layerFeature.setFeatureProperties(featureProperties); 
        }

        
        return layerFeature;
    }
    
    /**
     * @return the geoServerUrl
     */
    public String getGeoServerUrl() {
        return geoServerUrl;
    }

    /**
     * @param geoServerUrl the geoServerUrl to set
     */
    public void setGeoServerUrl(String geoServerUrl) {
        this.geoServerUrl = geoServerUrl;
    }

    /**
     * @return the geoServerUser
     */
    public String getGeoServerUser() {
        return geoServerUser;
    }

    /**
     * @param geoServerUser the geoServerUser to set
     */
    public void setGeoServerUser(String geoServerUser) {
        this.geoServerUser = geoServerUser;
    }

    /**
     * @return the geoServerPassword
     */
    public String getGeoServerPassword() {
        return geoServerPassword;
    }

    /**
     * @param geoServerPassword the geoServerPassword to set
     */
    public void setGeoServerPassword(String geoServerPassword) {
        this.geoServerPassword = geoServerPassword;
    }

    /**
     * @return the wmsVersion
     */
    public String getWmsVersion() {
        return wmsVersion;
    }

    /**
     * @param wmsVersion the wmsVersion to set
     */
    public void setWmsVersion(String wmsVersion) {
        this.wmsVersion = wmsVersion;
    }

    /**
     * @return the wfsVersion
     */
    public String getWfsVersion() {
        return wfsVersion;
    }

    /**
     * 
     * @param wfsVersion the wfsVersion to set
     */
    public void setWfsVersion(String wfsVersion) {
        this.wfsVersion = wfsVersion;
    }

    /**
     * @return the featureInfoCount
     */
    public int getFeatureInfoCount() {
        return featureInfoCount;
    }

    /**
     * @param featureInfoCount the featureInfoCount to set
     */
    public void setFeatureInfoCount(int featureInfoCount) {
        this.featureInfoCount = featureInfoCount;
    } 
    
    /**
     * @return the bufferRange
     */
    public int getBufferRange() {
        return bufferRange;
    }

    /**
     * @param bufferRange the bufferRange to set
     */
    public void setBufferRange(int bufferRange) {
        this.bufferRange = bufferRange;
    }

    /**
     * @return the getFeatureCRS
     */
    public String getGetFeatureCRS() {
        return getFeatureCRS;
    }

    /**
     * @param getFeatureCRS the getFeatureCRS to set
     */
    public void setGetFeatureCRS(String getFeatureCRS) {
        this.getFeatureCRS = getFeatureCRS;
    }

    /**
     * @return the propertyNames
     */
    public String getPropertyNames() {
        return propertyNames;
    }

    /**
     * @param propertyNames the propertyNames to set
     */
    public void setPropertyNames(String propertyNames) {
        this.propertyNames = propertyNames;
    }

}