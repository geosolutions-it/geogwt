/*
 * $ Header: com.digitalglobe.dgwatch.gui.client.service.LoginRemoteService,v. 2011-04.SNAPSHOT 20-mag-2011 13.04.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 2011-04.SNAPSHOT $
 * $ Date: 20-mag-2011 13.04.44 $
 *
 * ====================================================================
 * DGWatch 2011-04.SNAPSHOT
 *
 * Copyright (C) 2011 DigitalGlobe
 * http://www.digitalglobe.com
 *
 * ====================================================================
 *
 */
package it.geosolutions.geogwt.web.examples.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


// TODO: Auto-generated Javadoc
/**
 * The Interface ISessionControllerRemoteService.
 */
@RemoteServiceRelativePath("SessionControllerRemoteService")
public interface SessionControllerRemoteService extends RemoteService
{
    /**
     *
     */
    public void keepUserSessionAlive();

    /**
     *
     * @return
     */
    public Integer getUserSessionTimeoutMillis();

    /**
     * The Class Util.
     */
    public static class Util
    {
        /** The instance. */
        private static SessionControllerRemoteServiceAsync instance;

        /**
         * Gets the instance.
         *
         * @return the instance
         */
        public static SessionControllerRemoteServiceAsync getInstance()
        {
            if (instance == null)
            {
                instance = (SessionControllerRemoteServiceAsync) GWT.create(SessionControllerRemoteService.class);

                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint(GWT.getModuleBaseURL() +
                    "SessionControllerRemoteService");
            }

            return instance;
        }
    }

}
