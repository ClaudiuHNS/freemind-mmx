/*FreeMind - A Program for creating and viewing Mindmaps
*Copyright (C) 2000-2006 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitri Polivaev and others.
*
*See COPYING for Details
*
*This program is free software; you can redistribute it and/or
*modify it under the terms of the GNU General Public License
*as published by the Free Software Foundation; either version 2
*of the License, or (at your option) any later version.
*
*This program is distributed in the hope that it will be useful,
*but WITHOUT ANY WARRANTY; without even the implied warranty of
*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*GNU General Public License for more details.
*
*You should have received a copy of the GNU General Public License
*along with this program; if not, write to the Free Software
*Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/
/*
 * Created on 12.07.2005
 * Copyright (C) 2005 Dimitri Polivaev
 */
package freemind.main;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dimitri Polivaev
 * 12.07.2005
 */
public class Resources {
    private FreeMindMain main;
    static Resources resourcesInstance = null;
    private HashMap countryMap;
	private Logger logger = null;
    private Resources(FreeMindMain frame) {
        this.main = frame;  
		if (logger == null)
			logger = main.getLogger(this.getClass().getName());
    }
    
    static public void createInstance(FreeMindMain frame){
        if (resourcesInstance == null) resourcesInstance = new Resources(frame);
    }
    
    public URL getResource(String resource) {
        return main.getResource(resource);
    }
    
    public String getResourceString(String resource) {
        return main.getResourceString(resource);
    }
         
    static public Resources getInstance(){
        return resourcesInstance;
    }

    public String getFreemindDirectory() {
        return main.getFreemindDirectory();
    }

    public String getFreemindVersion() {
        return main.getFreemindVersion();
    }

    public int getIntProperty(String key, int defaultValue) {
        return main.getIntProperty(key, defaultValue);
    }

    public Properties getProperties() {
        return main.getProperties();
    }

    public String getProperty(String key) {
        return main.getProperty(key);
    }

    public ResourceBundle getResources() {
        return main.getResources();
    }

    public HashMap getCountryMap() {
        if(countryMap == null){
            String[] countryMapArray = new String[]{ 
                    "de", "DE", "en", "UK", "en", "US", "es", "ES", "es", "MX", "fi", "FI", "fr", "FR", "hu", "HU", "it", "CH",
                    "it", "IT", "nl", "NL", "no", "NO", "pt", "PT", "ru", "RU", "sl", "SI", "uk", "UA", "zh", "CN" };
            
            countryMap = new HashMap();
            for (int i = 0; i < countryMapArray.length; i = i + 2) {
                countryMap.put(countryMapArray[i],countryMapArray[i+1]); }
        }
        return countryMap;
    }
    
    /* To obtain a logging element, ask here. */
    public java.util.logging.Logger getLogger(String forClass){
        return main.getLogger(forClass);
    }
    
    public void logExecption(Throwable e) {
        e.printStackTrace();
		logger.log(Level.SEVERE, "An exception occured", e);
	}
    


}
