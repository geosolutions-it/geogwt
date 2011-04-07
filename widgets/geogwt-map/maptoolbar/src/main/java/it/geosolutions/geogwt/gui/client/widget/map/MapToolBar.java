/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.map.MapToolBar,v. 0.1 7-apr-2011 17.01.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 17.01.44 $
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
package it.geosolutions.geogwt.gui.client.widget.map;

import it.geosolutions.geogwt.gui.client.GeoGWTUtils;
import it.geosolutions.geogwt.gui.client.configuration.ActionClientTool;
import it.geosolutions.geogwt.gui.client.configuration.DropdownClientTool;
import it.geosolutions.geogwt.gui.client.configuration.DropdownOption;
import it.geosolutions.geogwt.gui.client.configuration.GenericClientTool;
import it.geosolutions.geogwt.gui.client.configuration.MenuClientTool;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarActionRegistry;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarApplicationAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.ToolbarMapAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.menu.MenuAction;
import it.geosolutions.geogwt.gui.client.widget.map.action.menu.MenuActionRegistry;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;

// TODO: Auto-generated Javadoc
/**
 * The Class MapToolBar.
 */
public class MapToolBar extends ButtonBar {

    /**
     * Instantiates a new map tool bar.
     */
    public MapToolBar() {
        super();
    }
    
    /**
     * Instantiates a new map tool bar.
     * 
     * @param mapLayoutWidget
     *            the map layout widget
     */
    public MapToolBar(MapLayoutWidget mapLayoutWidget) {
        super(mapLayoutWidget);

        // Example Event Listener
        // this.addListener(GeoGWTEvents.LOGIN_SUCCESS, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.extjs.gxt.ui.client.event.Listener#handleEvent(com.extjs.gxt.ui.client.event.BaseEvent)
     */
    public void handleEvent(AppEvent e) {

        // Example Event Listener
        /*
         * if (e.getType() == GeoRepoEvents.LOGIN_SUCCESS) {
         * 
         * // reload the contents of any dropdowns (if necessary) based on the logged-in user User
         * user = e.getData(); for (Map.Entry<DropdownClientTool, ComboBox> entry :
         * this.dropdowns.entrySet()) { entry.getKey().injectSecurity(entry.getValue(),
         * user.getGrantedAuthorizations()); } }
         */
    }

    /* (non-Javadoc)
     * @see it.geosolutions.geogwt.gui.client.widget.map.ButtonBar#initialize()
     */
    protected void initialize() {
        if (this.getMapLayoutWidget().getTools() == null) {
            this.getMapLayoutWidget().setTools(GeoGWTUtils.getInstance().getGlobalConfiguration()
                    .getToolbarItemManager().getClientTools());
        }
        
        List<GenericClientTool> tools = this.getMapLayoutWidget().getTools();
        for (GenericClientTool tool : tools) {
            String id = tool.getId();
            if (id.equals(TOOLBAR_SEPARATOR)) {
                addSeparator();
            } else if (tool instanceof MenuClientTool) {
                addMenuButton((MenuClientTool) tool,
                        (ToolbarApplicationAction) ToolbarActionRegistry.get(id, getMapLayoutWidget()
                                .getMapWidget()));
            } else if (tool instanceof DropdownClientTool) {
                addDropdown((DropdownClientTool) tool,
                        (ToolbarApplicationAction) ToolbarActionRegistry.get(id, getMapLayoutWidget()
                                .getMapWidget()));
            } else {
                ToolbarAction action = ToolbarActionRegistry
                        .get(id, getMapLayoutWidget().getMapWidget());

                action.setEnabled(((ActionClientTool) tool).isEnabled());
                action.setId(id);

                if (action instanceof ToolbarApplicationAction)
                    addApplicationButton((ToolbarApplicationAction) action);

                if (action instanceof ToolbarMapAction) {
                    if (((ActionClientTool) tool).getType().equals("toggle")) {
                        addMapToogleButton((ToolbarMapAction) action);
                    } else {
                        addMapButton((ToolbarMapAction) action);
                    }
                }
            }
        }
        vp.add(getToolBar());
        add(vp);
    }

    /**
     * Adds the menu button.
     * 
     * @param tool
     *            the tool
     * @param action
     *            the action
     */
    public void addMenuButton(MenuClientTool tool, ToolbarApplicationAction action) {
        Button button = new Button();
        button.setId(tool.getId());
        button.setWidth(60);
        button.setText(action.getButtonName());
        setIcon(button, action.getCategory());
        button.setEnabled(tool.isEnabled());

        button.setMenu(createMenu(tool.getActionTools()));

        this.getToolBar().add(button);
    }

    /**
     * Creates the menu.
     * 
     * @param actionTools
     *            the action tools
     * @return the menu
     */
    private Menu createMenu(List<ActionClientTool> actionTools) {
        Menu menu = new Menu();
        for (ActionClientTool actionTool : actionTools) {
            MenuAction action = MenuActionRegistry.get(actionTool.getId());
            MenuItem item = new MenuItem(action.getTitle());
            item.addSelectionListener(action);
            setMenuIcon(item, action.getCategory());
            menu.add(item);
        }
        return menu;
    }

    /**
     * Adds the dropdown.
     * 
     * @param tool
     *            the tool
     * @param action
     *            the action
     */
    private void addDropdown(DropdownClientTool tool, ToolbarApplicationAction action) {

        // first, add the label if necessary
        if (tool.getLabel() != null) {
            LabelField labelField = new LabelField();
            labelField.setText(tool.getLabel());

            this.getToolBar().add(labelField);
        }

        // now, create the actual dropdown
        ComboBox<BaseModelData> dd = new ComboBox<BaseModelData>();
        dd.setId(tool.getId());
        dd.setWidth(170);
        dd.setEnabled(tool.isEnabled());
        dd.setVisible(true);

        // the following calls essentially turn this Combo into a real dropdown box
        // (as opposed to a combo box, which allows you to drop a list down or add
        // new data by typing).

        // Sencha docs/examples are less than comprehensive, so I'll document this here: the
        // setTriggerAction() call is needed to make sure that each time a "trigger action"
        // is sent (presumably any typing, or clicking the "down arrow" button), that the
        // full contents of the dropdown are returned every time.
        // Otherwise, it would only return entries from the drop down list that matched what
        // was typed in the text edit part of the combo box.
        dd.setTriggerAction(ComboBox.TriggerAction.ALL);
        dd.disableTextSelection(true);
        dd.setTypeAhead(false);
        dd.setAllowBlank(tool.isAllowBlank());
        dd.setEditable(false);

        dd.setDisplayField(DropdownOption.LABEL_KEY);
        dd.setValueField(DropdownOption.VALUE_KEY);
        ListStore<BaseModelData> modelData = createComboBaseModel(tool);
        dd.setStore(modelData);

        // set the configured default if it exists
        if ((tool.getDefaultValue() != null) && (!tool.getDefaultValue().isEmpty())) {
            BaseModelData defaultModel = getDefaultModelValue(modelData, tool.getDefaultValue());
            if (defaultModel != null) {
                dd.setValue(defaultModel);
            }
        }

        dd.addListener(Events.SelectionChange, action);

        this.getToolBar().add(dd);

        // keep track of dropdowns in case we need to refill the contents
        this.dropdowns.put(tool, dd);
    }

    /**
     * Gets the default model value.
     * 
     * @param modelData
     *            the model data
     * @param defaultValue
     *            the default value
     * @return the default model value
     */
    private BaseModelData getDefaultModelValue(ListStore<BaseModelData> modelData,
            String defaultValue) {
        List<BaseModelData> models = modelData.getModels();

        for (BaseModelData model : models) {
            if (model.get(DropdownOption.VALUE_KEY).equals(defaultValue)) {
                return model;
            }
        }

        return null;
    }

    /**
     * Creates the combo base model.
     * 
     * @param tool
     *            the tool
     * @return the list store
     */
    private ListStore<BaseModelData> createComboBaseModel(DropdownClientTool tool) {
        List<DropdownOption> options = tool.getDropdownOptions();
        ListStore<BaseModelData> store = new ListStore<BaseModelData>();

        for (DropdownOption option : options) {
            BaseModelData model = new BaseModelData();
            model.set(DropdownOption.VALUE_KEY, option.getValue());

            // use the i18n value if it exists, otherwise, use the value that's
            // already in the DropdownOption
            String i18nDisplay = tool.getDropdownOptionDisplayValue(option.getValue());
            model.set(DropdownOption.LABEL_KEY, (i18nDisplay != null ? i18nDisplay : option
                    .getLabel()));
            store.add(model);
        }

        return store;
    }

    /**
     * Adds the separator.
     */
    public void addSeparator() {
        this.getToolBar().add(new SeparatorToolItem());
    }

    /**
     * Adds the map button.
     * 
     * @param action
     *            the action
     */
    public void addMapButton(ToolbarMapAction action) {
        Button button = new Button();
        button.setId(action.getId());
        button.setWidth(24);
        button.setToolTip(action.getTooltip());
        setIcon(button, action.getCategory());
        button.addListener(Events.Select, action);
        button.setEnabled(action.isEnabled());

        // TODO: don't hard-code the "fill space" to be after cleanDGWMenu - it should be more
        // dynamic than this
        if (action.getId().equalsIgnoreCase("cleanDGWMenu")) {
            this.getToolBar().add(new FillToolItem());
        }
        this.getToolBar().add(button);

        REGISTRY_BUTTONS.put(button.getId(), button);
    }

    /**
     * Adds the application button.
     * 
     * @param action
     *            the action
     */
    public void addApplicationButton(ToolbarApplicationAction action) {
        Button button = new Button();
        button.setId(action.getId());
        button.setWidth(70);
        button.setText(action.getButtonName());
        setIcon(button, action.getCategory());
        button.addListener(Events.Select, action);
        button.setEnabled(action.isEnabled());

        if (action.getId().equalsIgnoreCase("logout")) {
            this.getToolBar().add(new FillToolItem());
        } else if (action.getId().equalsIgnoreCase("updateUsers")) {
            button.setWidth(100);
        }

        this.getToolBar().add(button);

        REGISTRY_BUTTONS.put(button.getId(), button);
    }

    /**
     * Adds the map toogle button.
     * 
     * @param action
     *            the action
     */
    public void addMapToogleButton(ToolbarMapAction action) {
        Button button = new ToggleButton();
        button.setId(action.getId());
        button.setWidth(24);
        button.setToolTip(action.getTooltip());
        setIcon(button, action.getCategory());
        button.addListener(Events.Select, action);
        // button.addListener((new ButtonEvent(button)).getType(), action);
        // button.addSelectionListener(action);
        button.setEnabled(action.isEnabled());
        this.getToolBar().add(button);
        REGISTRY_BUTTONS.put(button.getId(), button);
    }

}
