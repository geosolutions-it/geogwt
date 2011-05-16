/**
 * 
 */
package it.geosolutions.geogwt.gui.client.widget.map.ol.control;

import it.geosolutions.geogwt.gui.client.widget.map.ol.control.BoxSelect.BoxSelectListener;

import org.gwtopenmaps.openlayers.client.control.ControlOptions;
import org.gwtopenmaps.openlayers.client.util.JSObject;

/**
 * @author Alessio
 *
 */
public class BoxSelectOptions extends ControlOptions {

    public void onBoxSelected(BoxSelectListener listener){
        JSObject callback = BoxSelectImpl.createBoxSelectedCallback(listener);
        getJSObject().setProperty("boxSelected", callback);
    }
    
}