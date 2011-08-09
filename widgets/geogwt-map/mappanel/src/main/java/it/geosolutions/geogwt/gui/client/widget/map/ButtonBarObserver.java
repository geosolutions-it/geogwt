/**
 *
 */
package it.geosolutions.geogwt.gui.client.widget.map;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.Observable;
import it.geosolutions.geogwt.gui.client.Observer;
import it.geosolutions.geogwt.gui.client.widget.GeoGWTButton;
import it.geosolutions.geogwt.gui.client.widget.GeoGWTButton.ButtonType;


/**
 * @author Alessio
 *
 */
public class ButtonBarObserver implements Observer
{
    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.client.Observer#update(it.geosolutions.geogwt.gui.client.Observable, java.lang.Object)
     */
    public void update(Observable o, Object arg)
    {
        GeoGWTButton button = (GeoGWTButton) o;

        if (button.getType() == ButtonType.TOGGLE)
        {
            Dispatcher.forwardEvent(GeoGWTEvents.TOGGLE_BUTTON_PRESSED, button);
        }
    }

}
