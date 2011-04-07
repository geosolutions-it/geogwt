/**
 * 
 */
package it.geosolutions.geogwt.web.examples.service.impl;

import it.geosolutions.geogwt.gui.client.configuration.IGeoGWTConfiguration;
import it.geosolutions.geogwt.gui.service.IGeoGWTStartupService;
import it.geosolutions.geogwt.web.examples.client.configuration.GeoGWTGlobalConfiguration;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alessio
 *
 */
public class StartupService implements IGeoGWTStartupService {
    
    /** The georepo global configuration. */
    @Autowired
    private GeoGWTGlobalConfiguration geoGWTGlobalConfiguration;

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.service.IGeoGWTStartupService#initServerConfiguration()
     */
    public IGeoGWTConfiguration initServerConfiguration() {
        return geoGWTGlobalConfiguration;
    }

}
