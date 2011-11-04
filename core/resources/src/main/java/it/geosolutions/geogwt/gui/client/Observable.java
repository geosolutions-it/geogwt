/*
 * $ Header: it.geosolutions.geogwt.gui.client.Observable,v. 0.1 7-apr-2011 16.58.10 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 7-apr-2011 16.58.10 $
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
package it.geosolutions.geogwt.gui.client;

import java.util.Vector;


// TODO: Auto-generated Javadoc
/**
 * The Class Observable.
 */
public class Observable
{

    /** The changed. */
    private boolean changed = false;

    /** The obs. */
    private Vector obs;

    /**
     * Instantiates a new observable.
     */

    public Observable()
    {
        obs = new Vector();
    }

    /**
     * Adds the observer.
     *
     * @param o
     *            the o
     */
    public synchronized void addObserver(Observer o)
    {
        if (o == null)
        {
            throw new NullPointerException();
        }
        if (!obs.contains(o))
        {
            obs.addElement(o);
        }
    }

    /**
     * Delete observer.
     *
     * @param o
     *            the o
     */
    public synchronized void deleteObserver(Observer o)
    {
        obs.removeElement(o);
    }

    /**
     * Notify observers.
     */
    public void notifyObservers()
    {
        notifyObservers(null);
    }

    /**
     * Notify observers.
     *
     * @param arg
     *            the arg
     */
    public void notifyObservers(Object arg)
    {

        /*
         * a temporary array buffer, used as a snapshot of the state of current Observers.
         */
        Object[] arrLocal;

        synchronized (this)
        {

            /*
             * We don't want the Observer doing callbacks into arbitrary code while holding its own
             * Monitor. The code where we extract each Observable from the Vector and store the
             * state of the Observer needs synchronization, but notifying observers does not (should
             * not). The worst result of any potential race-condition here is that: 1) a newly-added
             * Observer will miss a notification in progress 2) a recently unregistered Observer
             * will be wrongly notified when it doesn't care
             */
            if (!changed)
            {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
        {
            ((Observer) arrLocal[i]).update(this, arg);
        }
    }

    /**
     * Delete observers.
     */
    public synchronized void deleteObservers()
    {
        obs.removeAllElements();
    }

    /**
     * Sets the changed.
     */
    protected synchronized void setChanged()
    {
        changed = true;
    }

    /**
     * Clear changed.
     */
    protected synchronized void clearChanged()
    {
        changed = false;
    }

    /**
     * Checks for changed.
     *
     * @return true, if successful
     */
    public synchronized boolean hasChanged()
    {
        return changed;
    }

    /**
     * Count observers.
     *
     * @return the int
     */
    public synchronized int countObservers()
    {
        return obs.size();
    }

}
