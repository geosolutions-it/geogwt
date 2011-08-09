/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2011, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package it.geosolutions.geogwt.web.examples.server.gwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import it.geosolutions.geogwt.web.examples.client.service.SessionControllerRemoteService;


/**
 * @author Alessio
 *
 */
public class SessionControllerRemoteServiceImpl extends RemoteServiceServlet implements SessionControllerRemoteService
{
    /** serialVersionUID */
    private static final long serialVersionUID = 523698438299277368L;

    public String getLoggedInUser()
    {
        // TODO
        String u = (String) getThreadLocalRequest().getSession().getAttribute("ServletConstants.USER");

        return u;
    }

    public void keepUserSessionAlive()
    {
        getLoggedInUser();
    }

    /**
     * Should be the first RPC call from all UIDef*.onModuleLoad()
     *
     * @return java.lang.Integer (-1 if the user session has already timed out, otherwise, the
     *         number of milliseconds)
     */
    public Integer getUserSessionTimeoutMillis()
    {
        Integer returnObj = null;

        if (getLoggedInUser() != null)
        {
            // NOTE: "Repository Properties" is a static map in application scope.
            // When a user logs in, the session time for the user is set like this:
            // getSession().setMaxInactiveInterval(Integer.parseInt(sessionTimeout) * 60);
            // This is an alternative to using the web.xml session timeout value.

            // minutes
            int sessionTimeout = /*
                                  * RepositoryPropertiesFactory.getRepositoryProperties().
                                  * getPropertyAsInt(RepositoryPropertiesConstants.SESSION_TIMEOUT);
                                  */
                1;
            returnObj = new Integer(sessionTimeout * 60 * 1000); // milliseconds
        }
        else
        {
            returnObj = new Integer( /*-1*/1 * 60 * 1000);
        }

        return returnObj;
    }

}
