/*
 * $ Header: it.geosolutions.geogwt.gui.client.mvc.MessagesView,v. 0.1 25-gen-2011 11.24.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1 $
 * $ Date: 25-gen-2011 11.24.44 $
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
package it.geosolutions.geogwt.gui.client.mvc;

import it.geosolutions.geogwt.gui.client.GeoGWTEvents;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;

// TODO: Auto-generated Javadoc
/**
 * The Class MessagesView.
 */
public class MessagesView extends View {

    /**
     * Instantiates a new messages view.
     * 
     * @param controller
     *            the controller
     */
    public MessagesView(Controller controller) {
        super(controller);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.extjs.gxt.ui.client.mvc.View#handleEvent(com.extjs.gxt.ui.client. mvc.AppEvent)
     */
    @Override
    protected void handleEvent(AppEvent event) {
        // TODO Auto-generated method stub
        if (event.getType() == GeoGWTEvents.SEND_ALERT_MESSAGE) {
            onSendAlertMessage(event);
            return;
        }

        if (event.getType() == GeoGWTEvents.SEND_INFO_MESSAGE) {
            onSendInfoMessage(event);
            return;
        }

        if (event.getType() == GeoGWTEvents.SEND_ERROR_MESSAGE)
            onSendErrorMessage(event);

    }

    /**
     * On send error message.
     * 
     * @param event
     *            the event
     */
    private void onSendErrorMessage(AppEvent event) {
        String[] message = (String[]) event.getData();
        MessageBox box = new MessageBox();
        box.setIcon(MessageBox.ERROR);
        box.setTitle(message[0]);
        box.setMessage(message[1]);
        box.show();
    }

    /**
     * On send info message.
     * 
     * @param event
     *            the event
     */
    private void onSendInfoMessage(AppEvent event) {
        String[] message = (String[]) event.getData();
        Info.display(message[0], message[1]);
    }

    /**
     * On send alert message.
     * 
     * @param event
     *            the event
     */
    private void onSendAlertMessage(AppEvent event) {
        String[] message = (String[]) event.getData();
        MessageBox.alert(message[0], message[1], new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {
                // TODO Auto-generated method stub

            }
        });
    }

}
