package com.degloba.converters;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converter for Google Key.
 * @author Joel.Weight
 */

@FacesConverter( value="keyConverter" )
public class KeyConverter implements Converter {

    /**
     * converts the String representation of the key back to the Object
     */
    public Object getAsObject( FacesContext context, UIComponent component,
                               String value )
    {
        // will throw new IllegalArgumentException if it can't parse.
        return KeyFactory.stringToKey( value );
    }

    /**
     * converts the Key object into its String representation.
     */
    public String getAsString( FacesContext context, UIComponent component,
                               Object value )
    {
        if ( value instanceof Key )
        {
            return KeyFactory.keyToString( (Key)value );
        }
        else
        {
            throw new IllegalArgumentException( "Cannot convert non-key object in KeyConverter" );
        }
    }

}

