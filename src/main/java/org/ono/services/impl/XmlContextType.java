package org.ono.services.impl;

import org.ono.services.IContextType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ono on 2018/11/21.
 */
public class XmlContextType extends AbstractContextType {
    @Override
    public Map<String, String> resolve(byte[] bytes, String encoding) throws InvalidPathException, IOException {
        Properties properties = new Properties();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        properties.loadFromXML(inputStream);
        return convertProp2Map(properties);
    }
}
