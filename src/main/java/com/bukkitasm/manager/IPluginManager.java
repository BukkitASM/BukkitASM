package com.bukkitasm.manager;

import com.bukkitasm.plugin.ILoadingPlugin;
import com.bukkitasm.plugin.manager.PluginManager;
import com.google.common.io.Files;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;


/**
 * Created by Jasper on 4-10-2017.
 */
public class IPluginManager extends PluginManager {

    private ArrayList<URL> pluginUrls;
    private HashSet<String> pluginClasses;


    public IPluginManager(File pluginsDir) {
        super(pluginsDir);
    }

    @Override
    public void initPluginManager() {
        setPlugins(new ArrayList<ILoadingPlugin>());
        System.out.println(getPluginsDir().getAbsolutePath());

        pluginUrls = new ArrayList<URL>();
        pluginClasses = new HashSet<String>();
        searchPlugins(getPluginsDir());

        preSetupPlugins();


    }
    public void searchPlugins(File pluginDir) {
        String pluginClassString = null;
        File pluginFile = null;
        for(File file : pluginDir.listFiles()) {
            String extension = Files.getFileExtension(file.getAbsolutePath());


            if(extension.equals("jar")) {

                try {

                    JarFile jarFile = new JarFile(file);
                    Manifest manifest = null;
                    manifest = jarFile.getManifest();

                    Attributes attributes = manifest.getMainAttributes();

                    if(attributes != null) {
                        String pluginClassPath = attributes.getValue("Plugin-Class");
                        if (pluginClassPath != null ) {
                            System.out.println("[PluginManager] Plugin jar file found :" + file.getAbsolutePath());


                            pluginFile = file;
                            pluginClassString = pluginClassPath;
                            pluginUrls.add(file.toURI().toURL());
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                URL[] list = pluginUrls.toArray(new URL[]{});
                URLClassLoader urlClassLoader = new URLClassLoader(list, getClass().getClassLoader());
                try {
                    Class clazz = Class.forName(pluginClassString, true, urlClassLoader);
                    if(clazz.getSuperclass().equals(ILoadingPlugin.class)) {
                        ILoadingPlugin plugin = (ILoadingPlugin) clazz.newInstance();
                        plugin.setPluginFile(pluginFile);
                        getPlugins().add(plugin);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

    }
    public void preSetupPlugins() {
        for(ILoadingPlugin plugin : getPlugins()) {
            plugin.preSetup();
        }
    }

}
