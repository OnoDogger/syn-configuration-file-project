package org.ono.services;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Map;

/**
 * Created by ono on 2018/11/9.
 */
public interface IContextType {

    public Map<String,String> resolve(byte[] bytes, String encoding) throws InvalidPathException, IOException;
}
