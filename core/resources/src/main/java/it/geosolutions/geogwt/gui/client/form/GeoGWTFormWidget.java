/*
 * $ Header: it.geosolutions.geogwt.gui.client.form.GeoGWTFormWidget,v. 0.1 25-gen-2011 11.24.45 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.24.45 $
 *
 * ====================================================================
 *
 * Copyright (C) 2007 - 2011 GeoSolutions S.A.S.
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
package it.geosolutions.geogwt.gui.client.form;

import it.geosolutions.geogwt.gui.client.widget.SaveStaus;
import it.geosolutions.geogwt.gui.client.widget.SaveStaus.EnumSaveStatus;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;

// TODO: Auto-generated Javadoc
/**
 * The Class GeoGWTFormWidget.
 */
public abstract class GeoGWTFormWidget extends Window implements IForm {

    /** The form panel. */
    public FormPanel formPanel = new FormPanel();

    /** The submit. */
    protected Button submit;

    /** The cancel. */
    protected Button cancel;

    /** The field set. */
    protected FieldSet fieldSet;

    /** The save status. */
    protected SaveStaus saveStatus;

    /**
     * Instantiates a new geo repo form widget.
     */
    public GeoGWTFormWidget() {
        this.initializeWindow();
        this.initializeFormPanel();
        this.add(this.formPanel);
    }

    /**
     * Initialize form panel.
     */
    public void initializeFormPanel() {
        this.formPanel.setFrame(true);
        this.formPanel.setLayout(new FlowLayout());

        initSizeFormPanel();
        addComponentToForm();
        addButtons();
    }

    /**
     * Initialize window.
     */
    private void initializeWindow() {
        initSize();
        setResizable(false);

        addWindowListener(new WindowListener() {

            @Override
            public void windowHide(WindowEvent we) {
                reset();
            }

        });

        setLayout(new FitLayout());
        setModal(true);
        setPlain(true);
    }

    /**
     * Adds the buttons.
     */
    public void addButtons() {
        formPanel.setButtonAlign(HorizontalAlignment.LEFT);

        this.saveStatus = new SaveStaus();
        saveStatus.setAutoWidth(true);

        formPanel.getButtonBar().add(saveStatus);

        formPanel.getButtonBar().add(new FillToolItem());

        this.submit = new Button("SUBMIT");

        this.submit.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (formPanel.isValid())
                    execute();
            }

        });

        submit.setIconStyle("x-georepo-submit");

        formPanel.addButton(submit);

        this.cancel = new Button("CANCEL");

        this.cancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                cancel();
            }
        });

        this.cancel.setIconStyle("x-georepo-cancel");

        formPanel.addButton(cancel);
    }

    /**
     * Sets the save status.
     * 
     * @param status
     *            the status
     * @param message
     *            the message
     */
    public void setSaveStatus(EnumSaveStatus status, EnumSaveStatus message) {
        this.saveStatus.setIconStyle(status.getValue());
        this.saveStatus.setText(message.getValue());
    }

    /**
     * Reset.
     */
    public void reset() {

    }

    /**
     * Adds the component to form.
     */
    public abstract void addComponentToForm();

    /**
     * Inits the size.
     */
    public abstract void initSize();

    /**
     * Inits the size form panel.
     */
    public abstract void initSizeFormPanel();

    /**
     * Cancel.
     */
    public abstract void cancel();

    public void injectEvent() {
        // TODO Auto-generated method stub
        
    }
}
