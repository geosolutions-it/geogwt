/*
 * $ Header: it.geosolutions.geogwt.gui.spring.ApplicationContextUtil,v. 0.1 25-gen-2011 11.24.45 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.spring;

import org.springframework.context.ApplicationContext;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationContextUtil.
 */
public class ApplicationContextUtil {

    /** The INSTANCE. */
    private static ApplicationContextUtil INSTANCE;

    /** The spring context. */
    private ApplicationContext springContext;

    /**
     * Gets the single instance of ApplicationContextUtil.
     * 
     * @return single instance of ApplicationContextUtil
     */
    public static synchronized ApplicationContextUtil getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ApplicationContextUtil();
        return INSTANCE;
    }

    /**
     * Gets the bean.
     * 
     * @param beanName
     *            the bean name
     * @return the bean
     */
    public Object getBean(String beanName) {
        return springContext.getBean(beanName);
    }

    /**
     * Gets the spring context.
     * 
     * @return the spring context
     */
    public ApplicationContext getSpringContext() {
        return springContext;
    }

    /**
     * Sets the spring context.
     * 
     * @param springContext
     *            the new spring context
     */
    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }
}
