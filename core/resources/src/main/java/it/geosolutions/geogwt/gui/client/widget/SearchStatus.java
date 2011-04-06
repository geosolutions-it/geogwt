/*
 * $ Header: it.geosolutions.geogwt.gui.client.widget.SearchStatus,v. 0.1 25-gen-2011 11.24.44 created by afabiani <alessio.fabiani at geo-solutions.it> $
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
 * The Class SearchStatus.
 */
public class SearchStatus extends StatusWidget {

    /**
     * The Enum EnumSearchStatus.
     */
    public enum EnumSearchStatus {

        /** The STATU s_ search. */
        STATUS_SEARCH("x-status-ok"),

        /** The STATU s_ n o_ search. */
        STATUS_NO_SEARCH("x-status-not-ok"),

        /** The STATU s_ searc h_ error. */
        STATUS_SEARCH_ERROR("x-status-error"),

        /** The STATU s_ messag e_ search. */
        STATUS_MESSAGE_SEARCH("Search OK"),

        /** The STATU s_ messag e_ no t_ search. */
        STATUS_MESSAGE_NOT_SEARCH("No Results Found"),

        /** The STATU s_ messag e_ searc h_ error. */
        STATUS_MESSAGE_SEARCH_ERROR("Search Service Error"),

        /** The STATU s_ messag e_ use r_ detai l_ error. */
        STATUS_MESSAGE_USER_DETAIL_ERROR("User Detail Error"),

        /** The STATU s_ messag e_ use r_ detail. */
        STATUS_MESSAGE_USER_DETAIL("User Detail Ok"),

        /** The STATU s_ messag e_ ao i_ detai l_ error. */
        STATUS_MESSAGE_AOI_DETAIL_ERROR("AOI Deatil Error"),

        /** The STATU s_ messag e_ ao i_ detail. */
        STATUS_MESSAGE_AOI_DETAIL("AOI Deatil Ok"),

        /** The STATU s_ messag e_ ao i_ unshar e_ error. */
        STATUS_MESSAGE_AOI_UNSHARE_ERROR("Unshare AOI Error"),

        /** The STATU s_ messag e_ ao i_ unshare. */
        STATUS_MESSAGE_AOI_UNSHARE("Unshare AOI Ok"),

        /** The STATU s_ messag e_ membe r_ detail. */
        STATUS_MESSAGE_MEMBER_DETAIL("Member Detail Ok");

        /** The value. */
        private String value;

        /**
         * Instantiates a new enum search status.
         * 
         * @param value
         *            the value
         */
        EnumSearchStatus(String value) {
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
