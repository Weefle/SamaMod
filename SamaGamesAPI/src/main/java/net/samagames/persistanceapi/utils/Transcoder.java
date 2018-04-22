package net.samagames.persistanceapi.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

/*
 * This file is part of PersistanceAPI.
 *
 * PersistanceAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PersistanceAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PersistanceAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Transcoder
{
    // Remove dash to insert in the database
    public static String encode(String uuid)
    {
        uuid = uuid.replace("-","");
        return uuid;
    }

    // Put lower case and add dash for uuid
    public static String decode(String uuid)
    {
        // Regexp to format uuid
        uuid = uuid.toLowerCase();
        uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
        return uuid;
    }

    // Get the permissions to a HashMap
    public static HashMap<String, Boolean> getHashMapPerm(Object permissions)
    {
        HashMap<String, Boolean> result = new HashMap<>();
        try {
            //Iterate class fields
            for (Field field : permissions.getClass().getDeclaredFields())
            {
                //Check if annotations present
                if (field.isAnnotationPresent(Perm.class))
                {
                    // Make the private field accessible
                    field.setAccessible(true);
                    // Add to HashMap with correct value
                    result.put(field.getAnnotation(Perm.class).value(), field.getBoolean(permissions));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    // Set the annotations values
    public static void setAnnotationValue(Object permissions, String key, Boolean value)
    {
        try
        {
            //Iterate class fields
            for (Field field : permissions.getClass().getDeclaredFields())
            {
                // Make the private field accessible
                field.setAccessible(true);
                // Check if annotations present and equal the key
                if (field.isAnnotationPresent(Perm.class) && field.getAnnotation(Perm.class).value().equals(key))
                {
                    field.setBoolean(permissions, value);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
