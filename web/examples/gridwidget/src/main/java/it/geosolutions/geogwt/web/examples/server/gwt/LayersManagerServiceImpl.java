/**
 * 
 */
package it.geosolutions.geogwt.web.examples.server.gwt;

import it.geosolutions.geogwt.gui.client.ApplicationException;
import it.geosolutions.geogwt.gui.spring.ApplicationContextUtil;
import it.geosolutions.geogwt.web.examples.client.model.Layer;
import it.geosolutions.geogwt.web.examples.client.service.LayersManagerServiceRemote;
import it.geosolutions.geogwt.web.examples.server.service.ILayersManagerService;

import java.util.logging.Logger;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Alessio
 *
 */
public class LayersManagerServiceImpl extends RemoteServiceServlet implements LayersManagerServiceRemote {

    /**
     * 
     */
    private static final long serialVersionUID = -8463230843282973229L;

    /** The logger. */
    @SuppressWarnings("unused")
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /** The gs profile manager service. */
    private ILayersManagerService layersManagerService;

    /**
     * Instantiates a new gs users manager service impl.
     */
    public LayersManagerServiceImpl() {
        this.layersManagerService = (ILayersManagerService) ApplicationContextUtil.getInstance().getBean(
                "layersManagerServiceGWT");
    }

    /* (non-Javadoc)
     * @see it.geosolutions.georepo.gui.client.service.GsUsersManagerServiceRemote#getGsUsers(com.extjs.gxt.ui.client.data.PagingLoadConfig)
     */
    public PagingLoadResult<Layer> getLayers(PagingLoadConfig config) throws ApplicationException {
        return layersManagerService.getLayers(config);
    }

}
