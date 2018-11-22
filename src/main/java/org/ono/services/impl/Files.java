package org.ono.services.impl;

import org.apache.commons.lang.StringUtils;
import org.ono.support.spring.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ono on 2018/11/20.
 */
public class Files implements ApplicationListener<ContextRefreshedEvent>{

    private String id;
    private String locations;
    private String excludes;
    private String type;
    private List<String> filePaths;
    private List<String> excludeFiles;

    public List<String> getExcludeFiles(){
        if (StringUtils.isBlank(excludes)){
            throw new IllegalArgumentException("exclude is required!");
        }
        String[] excludeArr = excludes.split(",");
        return  Arrays.asList(excludeArr);
    }

    public List<String> getFilePaths() {
        if (StringUtils.isBlank(locations)){
            throw new IllegalArgumentException("location is required!");
        }
        String[] pathArr = locations.split(",");
        return  Arrays.asList(pathArr);
    }

    public Files() {
    }

    public Files(String locations, String excludes, String type) {
        this.locations = locations;
        this.excludes = excludes;
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Files{" +
                "id='" + id + '\'' +
                ", locations='" + locations + '\'' +
                ", excludes='" + excludes + '\'' +
                ", type='" + type + '\'' +
                ", filePaths=" + filePaths +
                ", excludeFiles=" + excludeFiles +
                '}';
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ApplicationContext ac = event.getApplicationContext();
        if (null != ac.getParent()) return;
        AppContext.setApplicationContext(ac);

        System.out.println(id);
        System.out.println(excludes);


    }
}
