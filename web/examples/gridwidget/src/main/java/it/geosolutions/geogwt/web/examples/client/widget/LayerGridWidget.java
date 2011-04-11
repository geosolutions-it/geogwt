/**
 * 
 */
package it.geosolutions.geogwt.web.examples.client.widget;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;
import it.geosolutions.geogwt.gui.client.Resources;
import it.geosolutions.geogwt.gui.client.i18n.I18nProvider;
import it.geosolutions.geogwt.gui.client.widget.GeoGWTGridWidget;
import it.geosolutions.geogwt.gui.client.widget.SearchFilterField;
import it.geosolutions.geogwt.web.examples.client.model.Layer;
import it.geosolutions.geogwt.web.examples.client.service.LayersManagerServiceRemoteAsync;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Alessio
 * 
 */
public class LayerGridWidget extends GeoGWTGridWidget<Layer> {

    /** The gs users service. */
    private LayersManagerServiceRemoteAsync layersService;

    /** The proxy. */
    private RpcProxy<PagingLoadResult<Layer>> proxy;

    /** The loader. */
    private PagingLoader<PagingLoadResult<ModelData>> loader;

    public LayerGridWidget(int pageSize, int gridDimension, 
            LayersManagerServiceRemoteAsync layersService) {
        super(pageSize, gridDimension);
        
        this.layersService = layersService;
    }

    @Override
    public void createStore() {
        // Loader fro gsUsersService
        this.proxy = new RpcProxy<PagingLoadResult<Layer>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Layer>> callback) {
                layersService.getLayers((PagingLoadConfig) loadConfig, callback);
            }

        };
        loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
        loader.setRemoteSort(false);
        store = new ListStore<Layer>(loader);

        // Search tool
        SearchFilterField<Layer> filter = new SearchFilterField<Layer>() {

            @Override
            protected boolean doSelect(Store<Layer> store, Layer parent, Layer record,
                    String property, String filter) {

                String name = parent.get(Layer.BeanKeyValue.LAYER_NAME.getValue());
                name = name.toLowerCase();
                if (name.indexOf(filter.toLowerCase()) != -1) {
                    return true;
                }
                return false;
            }

        };
        filter.setWidth(130);
        filter.setIcon(Resources.ICONS.search());
        // Bind the filter field to your grid store (grid.getStore())
        filter.bind(store);

        getToolBar().bind(loader);
        getToolBar().add(new SeparatorToolItem());
        getToolBar().add(filter);
        getToolBar().add(new SeparatorToolItem());

        setUpLoadListener();
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig layerNameColumn = new ColumnConfig();
        layerNameColumn.setId(Layer.BeanKeyValue.LAYER_NAME.getValue());
        layerNameColumn.setHeader("Layer Name");
        layerNameColumn.setWidth(80);
        layerNameColumn.setMenuDisabled(false);
        layerNameColumn.setSortable(true);
        configs.add(layerNameColumn);
        
        ColumnConfig layerTitleColumn = new ColumnConfig();
        layerTitleColumn.setId(Layer.BeanKeyValue.LAYER_TITLE.getValue());
        layerTitleColumn.setHeader("Layer Title");
        layerTitleColumn.setWidth(80);
        layerTitleColumn.setMenuDisabled(false);
        layerTitleColumn.setSortable(true);
        configs.add(layerTitleColumn);
        
        ColumnConfig layerDescriptionColumn = new ColumnConfig();
        layerDescriptionColumn.setId(Layer.BeanKeyValue.LAYER_DESCRIPTION.getValue());
        layerDescriptionColumn.setHeader("Description");
        layerDescriptionColumn.setWidth(200);
        layerDescriptionColumn.setMenuDisabled(false);
        layerDescriptionColumn.setSortable(true);
        configs.add(layerDescriptionColumn);
        
        ColumnConfig layerURLColumn = new ColumnConfig();
        layerURLColumn.setId(Layer.BeanKeyValue.LAYER_BASE_URL.getValue());
        layerURLColumn.setHeader("Base URL");
        layerURLColumn.setWidth(80);
        layerURLColumn.setMenuDisabled(false);
        layerURLColumn.setSortable(true);
        configs.add(layerURLColumn);
        
        return new ColumnModel(configs);
    }

    @Override
    public void setUpLoadListener() {
        loader.addLoadListener(new LoadListener() {

            @Override
            public void loaderBeforeLoad(LoadEvent le) {
                if (!getToolBar().isEnabled())
                    getToolBar().enable();
            }

            @Override
            public void loaderLoad(LoadEvent le) {

                // TODO: change messages here!!

                BasePagingLoadResult<?> result = le.getData();
                if (!result.getData().isEmpty()) {
                    int size = result.getData().size();
                    String message = "";
                    if (size == 1)
                        message = I18nProvider.getMessages().recordLabel();
                    else
                        message = I18nProvider.getMessages().recordPluralLabel();
                    Dispatcher.forwardEvent(GeoGWTEvents.SEND_INFO_MESSAGE, new String[] {
                            I18nProvider.getMessages().remoteServiceName(),
                            I18nProvider.getMessages().foundLabel() + " " + result.getData().size()
                                    + " " + message });
                } else {
                    Dispatcher.forwardEvent(GeoGWTEvents.SEND_INFO_MESSAGE, new String[] {
                            I18nProvider.getMessages().remoteServiceName(),
                            I18nProvider.getMessages().recordNotFoundMessage() });
                }
            }

        });
    }

    @Override
    public void setGridProperties() {
        grid.setHeight(getGridDimension() - 25);
        grid.setStyleAttribute("borderTop", "none");
        grid.setAutoExpandColumn(Layer.BeanKeyValue.LAYER_DESCRIPTION.getValue());
        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setLoadMask(true);
        
        if (grid.getStore() != null) {
            grid.getStore().setSortField(Layer.BeanKeyValue.LAYER_NAME.getValue());
            grid.getStore().setSortDir(SortDir.ASC);
        }
    }

    /**
     * @return the loader
     */
    public PagingLoader<PagingLoadResult<ModelData>> getLoader() {
        return loader;
    }

}
