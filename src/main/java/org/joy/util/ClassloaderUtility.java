package org.joy.util;

import lombok.extern.slf4j.Slf4j;
import org.joy.generator.exception.GeneratorException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClassloaderUtility {
    private ClassloaderUtility(){
    }

    public static ClassLoader getCustomClassloader(String basePath, List<String> entries) {
        List<URL> urls = new ArrayList<URL>();
        File file;

        if (entries != null) {
            for (String classPathEntry : entries) {
                String jarPath = (basePath + classPathEntry).replaceAll("%20", " ");
                file = new File(jarPath);
                log.debug("Loading jar : " + file.getPath());
                if (!file.exists()) {
                    throw new GeneratorException( String.format("Cannot resolve classpath entry: {0}",classPathEntry));
                }
                try {
                    urls.add(file.toURI().toURL());
                } catch (MalformedURLException e) {
                    throw new GeneratorException(String.format("Cannot resolve classpath entry: {0}",classPathEntry));
                }
            }
        }
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        return new URLClassLoader(urls.toArray(new URL[urls.size()]), parent);
    }
}
