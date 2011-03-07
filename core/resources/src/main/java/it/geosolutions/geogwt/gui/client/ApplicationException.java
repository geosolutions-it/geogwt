/*
 * $ Header: it.geosolutions.geogwt.gui.client.ApplicationException,v. 0.1 25-gen-2011 11.24.45 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationException.
 */
public class ApplicationException extends RuntimeException implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5240255747375099784L;

    /** The message. */
    private String message;

    /**
     * Instantiates a new application exception.
     */
    public ApplicationException() {
    }

    /**
     * Instantiates a new application exception.
     * 
     * @param message
     *            the message
     */
    public ApplicationException(String message) {
        this.message = message;
    }

    /**
     * Instantiates a new application exception.
     * 
     * @param e
     *            the e
     */
    public ApplicationException(Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new application exception.
     * 
     * @param message
     *            the message
     * @param e
     *            the e
     */
    public ApplicationException(String message, Throwable e) {
        super(e);
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     * 
     * @param message
     *            the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the detailed message.
     * 
     * @return the detailed message
     */
    public String getDetailedMessage() {
        return super.getMessage();
    }
}
