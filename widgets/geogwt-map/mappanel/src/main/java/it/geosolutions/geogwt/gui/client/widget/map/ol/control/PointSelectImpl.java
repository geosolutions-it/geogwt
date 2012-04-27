/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelectImpl,v. 0.1 18-apr-2012 16.59.50 created by tobia di pisa <tobia.dipisa at geo-solutions.it> $
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

import it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelect.PointSelectListener;

import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * @author Tobia Di Pisa at tobia.dipisa@geo-solutions.it
 *
 */
public class PointSelectImpl {
	
    /**
     * @param options
     * @return JSObject
     */
    public static native JSObject create(JSObject options) /*-{
	    $wnd.OpenLayers.Control.PointSelect = $wnd.OpenLayers.Class($wnd.OpenLayers.Control, {
			    type: $wnd.OpenLayers.Control.TYPE_TOOL,
			    
			    pointSelected: function() {},
			    
			    callbacks: null,
			    
			    handlerOptions: null,
			    
		        defaultHandlerOptions: {
		            'single': true,
		            'double': false,
		            'pixelTolerance': 0,
		            'stopSingle': false,
		            'stopDouble': false
		        },
			    
			    initialize: function(options) {
		            this.handlerOptions = $wnd.OpenLayers.Util.extend(
		                {}, this.defaultHandlerOptions
		            );
		            $wnd.OpenLayers.Control.prototype.initialize.apply(
		                this, arguments
		            ); 
		            this.handler = new $wnd.OpenLayers.Handler.Click(
		                this, {
		                    'click': this.trigger
		                }, this.handlerOptions
		            );
			    },
			    
		        trigger: function(e) {
		            var lonlat = this.map.getLonLatFromViewPortPx(e.xy);
		            //alert("You clicked near " + lonlat.lat + " N, " +
		            //                          + lonlat.lon + " E");
		            this.pointSelected(lonlat);
		        },
			    
			    CLASS_NAME: "OpenLayers.Control.PointSelect"
	    });
	    
	    return new $wnd.OpenLayers.Control.PointSelect(options);
	}-*/;
	
	/**
	 * @param listener
	 * @return JSObject
	 */
	public static native JSObject createPointSelectedCallback(PointSelectListener listener) /*-{
	    var callback = function(obj){
		    var lonlat = @org.gwtopenmaps.openlayers.client.LonLat::narrowToLonLat(Lorg/gwtopenmaps/openlayers/client/util/JSObject;)(obj);
		    listener.@it.geosolutions.geogwt.gui.client.widget.map.ol.control.PointSelect.PointSelectListener::onPointSelected(Lorg/gwtopenmaps/openlayers/client/LonLat;)(lonlat);
	    }
	    
	    return callback;
	}-*/;
}
