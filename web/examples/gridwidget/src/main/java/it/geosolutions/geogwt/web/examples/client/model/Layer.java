/*
 * $ Header: it.geosolutions.geogwt.web.examples.client.model.Layer,v. 0.1 11-apr-2011 10.48.54 created by afabiani <alessio.fabiani at geo-solutions.it> $
 * $ Revision: 0.1-SNAPSHOT $
 * $ Date: 11-apr-2011 10.48.54 $
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
package it.geosolutions.geogwt.web.examples.client.model;

import java.util.Arrays;

import com.extjs.gxt.ui.client.data.BeanModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Layer.
 */
public class Layer extends BeanModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4808514123107414546L;

    /** The name. */
    private String name;
    
    /** The title. */
    private String title;
    
    /** The description. */
    private String description;
    
    /** The base_url. */
    private String base_url;
    
    /** The user. */
    private String user;
    
    /** The password. */
    private String password;
    
    /** The default_style. */
    private String default_style;
    
    /** The available_styles. */
    private String[] available_styles;
    
    /**
     * Instantiates a new layer.
     */
    public Layer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
        set(BeanKeyValue.LAYER_NAME.getValue(), this.name);
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            the new title
     */
    public void setTitle(String title) {
        this.title = title;
        set(BeanKeyValue.LAYER_TITLE.getValue(), this.title);
    }

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
        this.description = description;
        set(BeanKeyValue.LAYER_DESCRIPTION.getValue(), this.description);
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the base url.
     * 
     * @param base_url
     *            the new base url
     */
    public void setBaseUrl(String base_url) {
        this.base_url = base_url;
        set(BeanKeyValue.LAYER_BASE_URL.getValue(), this.base_url);
    }

    /**
     * Gets the base url.
     * 
     * @return the base url
     */
    public String getBaseUrl() {
        return base_url;
    }

    /**
     * Sets the user.
     * 
     * @param user
     *            the new user
     */
    public void setUser(String user) {
        this.user = user;
        set(BeanKeyValue.LAYER_USER.getValue(), this.user);
    }

    /**
     * Gets the user.
     * 
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the password.
     * 
     * @param password
     *            the new password
     */
    public void setPassword(String password) {
        this.password = password;
        set(BeanKeyValue.LAYER_PASSWORD.getValue(), this.password);
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the default style.
     * 
     * @param default_style
     *            the new default style
     */
    public void setDefaultStyle(String default_style) {
        this.default_style = default_style;
        set(BeanKeyValue.DEFAULT_STYLE.getValue(), this.default_style);
    }

    /**
     * Gets the default style.
     * 
     * @return the default style
     */
    public String getDefaultStyle() {
        return default_style;
    }

    /**
     * Sets the available styles.
     * 
     * @param available_styles
     *            the new available styles
     */
    public void setAvailableStyles(String[] available_styles) {
        this.available_styles = available_styles;
        set(BeanKeyValue.AVAILABLE_STYLES.getValue(), this.available_styles);
    }

    /**
     * Gets the available styles.
     * 
     * @return the available styles
     */
    public String[] getAvailableStyles() {
        return available_styles;
    }

    /**
     * The Enum BeanKeyValue.
     */
    public enum BeanKeyValue {
        
        /** The DEFAUL t_ style. */
        DEFAULT_STYLE("default_style"), 
        
        /** The LAYE r_ name. */
        LAYER_NAME("name"), 
        
        /** The AVAILABL e_ styles. */
        AVAILABLE_STYLES("available_styles"), 
        
        /** The LAYE r_ password. */
        LAYER_PASSWORD("password"), 
        
        /** The LAYE r_ user. */
        LAYER_USER("user"), 
        
        /** The LAYE r_ bas e_ url. */
        LAYER_BASE_URL("base_url"), 
        
        /** The LAYE r_ description. */
        LAYER_DESCRIPTION("description"), 
        
        /** The LAYE r_ tytle. */
        LAYER_TITLE("title");
        
        /** The value. */
        private String value;

        /**
         * Instantiates a new bean key value.
         * 
         * @param value
         *            the value
         */
        BeanKeyValue(String value) {
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(available_styles);
        result = prime * result + ((base_url == null) ? 0 : base_url.hashCode());
        result = prime * result + ((default_style == null) ? 0 : default_style.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Layer)) {
            return false;
        }
        Layer other = (Layer) obj;
        if (!Arrays.equals(available_styles, other.available_styles)) {
            return false;
        }
        if (base_url == null) {
            if (other.base_url != null) {
                return false;
            }
        } else if (!base_url.equals(other.base_url)) {
            return false;
        }
        if (default_style == null) {
            if (other.default_style != null) {
                return false;
            }
        } else if (!default_style.equals(other.default_style)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Layer [");
        if (available_styles != null)
            builder.append("available_styles=").append(Arrays.toString(available_styles)).append(
                    ", ");
        if (base_url != null)
            builder.append("base_url=").append(base_url).append(", ");
        if (default_style != null)
            builder.append("default_style=").append(default_style).append(", ");
        if (description != null)
            builder.append("description=").append(description).append(", ");
        if (name != null)
            builder.append("name=").append(name).append(", ");
        if (password != null)
            builder.append("password=").append(password).append(", ");
        if (title != null)
            builder.append("title=").append(title).append(", ");
        if (user != null)
            builder.append("user=").append(user);
        builder.append("]");
        return builder.toString();
    }
}
