package it.geosolutions.geogwt.gui.client;

public class Constants {

    public final static int DEFAULT_PAGESIZE = 25;

    public static final int NORTH_PANEL_DIMENSION = 30;

    public static final int SOUTH_PANEL_DIMENSION = 400;

    public static final int EASTH_PANEL_DIMENSION = 250;

    public static final int WEST_PANEL_DIMENSION = 0;

    public static Constants instance = new Constants();

    public static Constants getInstance() {
        return instance;
    }
}
