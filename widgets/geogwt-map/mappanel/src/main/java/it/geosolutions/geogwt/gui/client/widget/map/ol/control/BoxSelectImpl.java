/**
 * 
 */
package it.geosolutions.geogwt.gui.client.widget.map.ol.control;

import it.geosolutions.geogwt.gui.client.widget.map.ol.control.BoxSelect.BoxSelectListener;

import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * @author Alessio
 *
 */
public class BoxSelectImpl {

    public static native JSObject create(JSObject options)/*-{
        $wnd.OpenLayers.Control.BoxSelect = $wnd.OpenLayers.Class($wnd.OpenLayers.Control, {
            type: $wnd.OpenLayers.Control.TYPE_TOOL,
            
            boxSelected: function() {},
            
            callbacks: null,
            
            handlerOptions: null,
            
            initialize: function(options) {
                $wnd.OpenLayers.Control.prototype.initialize.apply(this, [options]);
                this.callbacks = $wnd.OpenLayers.Util.extend({done: this.notice},
                                                        this.callbacks);
                this.handler = new $wnd.OpenLayers.Handler.Box(this, this.callbacks, this.handlerOptions);
            },
            
            notice: function (bounds) {
                var ll = this.map.getLonLatFromPixel(new $wnd.OpenLayers.Pixel(bounds.left, bounds.bottom));
                var ur = this.map.getLonLatFromPixel(new $wnd.OpenLayers.Pixel(bounds.right, bounds.top));
                var bbox = new $wnd.OpenLayers.Bounds(ll.lon, ll.lat, ur.lon, ur.lat);
                this.boxSelected(bbox);
            },
            
            CLASS_NAME: "OpenLayers.Control.BoxSelect"
        });
        
        return new $wnd.OpenLayers.Control.BoxSelect(options);
    }-*/;
        
    public static native JSObject createBoxSelectedCallback(BoxSelectListener listener)/*-{
        var callback = function(obj){
            var bounds = @org.gwtopenmaps.openlayers.client.Bounds::narrowToBounds(Lorg/gwtopenmaps/openlayers/client/util/JSObject;)(obj);
            listener.@it.geosolutions.geogwt.gui.client.widget.map.ol.control.BoxSelect.BoxSelectListener::onBoxSelected(Lorg/gwtopenmaps/openlayers/client/Bounds;)(bounds);
        }
        return callback;
    }-*/;

}
