/**
 *
 */
package it.geosolutions.geogwt.gui.client.widget;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;

import it.geosolutions.geogwt.gui.client.Observable;


/**
 * @author Alessio
 *
 */
public class GeoGWTButton extends Observable
{
    public enum ButtonType
    {
        BUTTON, TOGGLE;

        public static ButtonType getValue(String value)
        {
            if (value.equalsIgnoreCase("button"))
            {
                return BUTTON;
            }
            else if (value.equalsIgnoreCase("toggle"))
            {
                return TOGGLE;
            }

            return null;
        }
    }

    private Button button;

    private final ButtonType type;

    public GeoGWTButton(ButtonType type)
    {
        this.type = type;

        switch (this.getType())
        {
        case BUTTON:
            this.button = new Button();

            break;

        case TOGGLE:
            this.button = new ToggleButton();

            break;
        }
    }

    /**
     *
     * @return
     */
    public Button getButton()
    {
        return this.button;
    }

    /**
     *
     */
    public void isPressed()
    {
        setChanged();
    }

    /**
     * @return the type
     */
    public ButtonType getType()
    {
        return type;
    }

}
