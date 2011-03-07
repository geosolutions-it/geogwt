/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.SaveStaus,v. 0.1 25-gen-2011 11.24.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
package it.geosolutions.geogwt.gui.client.widget;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveStaus.
 */
public class SaveStaus extends StatusWidget {

    /**
     * The Enum EnumSaveStatus.
     */
    public enum EnumSaveStatus {

        /** The STATU s_ save. */
        STATUS_SAVE("x-status-ok"),

        /** The STATU s_ n o_ save. */
        STATUS_NO_SAVE("x-status-not-ok"),

        /** The STATU s_ sav e_ error. */
        STATUS_SAVE_ERROR("x-status-error"),

        /** The STATU s_ messag e_ save. */
        STATUS_MESSAGE_SAVE("Operation Ok"),

        /** The STATU s_ messag e_ no t_ save. */
        STATUS_MESSAGE_NOT_SAVE("Operation Failed"),

        /** The STATU s_ messag e_ sav e_ error. */
        STATUS_MESSAGE_SAVE_ERROR("Service Error");

        /** The value. */
        private String value;

        /**
         * Instantiates a new enum save status.
         * 
         * @param value
         *            the value
         */
        EnumSaveStatus(String value) {
            this.value = value;
        }

        /**
         * Gets the value.
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

}
