/**
 *
 */
package it.geosolutions.geogwt.gui.client.widget.map.ol.control;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.control.Control;
import org.gwtopenmaps.openlayers.client.util.JSObject;


/**
 * @author Alessio
 *
 */
public class BoxSelect extends Control
{
    protected BoxSelect(JSObject element)
    {
        super(element);
    }

    public BoxSelect()
    {
        this(BoxSelectImpl.create(null));
    }

    public BoxSelect(BoxSelectOptions options)
    {
        this(BoxSelectImpl.create(options.getJSObject()));
    }

    public interface BoxSelectListener
    {
        void onBoxSelected(Bounds bounds);
    }
}
