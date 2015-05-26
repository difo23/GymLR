package com.smartphone.lizandro.gymlr;

import android.app.Application;
import com.smartphone.lizandro.gymlr.contexts.ContextModule;
import com.smartphone.lizandro.gymlr.contexts.GlobalModule;
import com.smartphone.lizandro.gymlr.rest.RestClient;
import dagger.ObjectGraph;

import java.util.Arrays;
import java.util.List;

public class ApplicationClass extends Application {
 
    private ObjectGraph objectGraph;
 
    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
    }
 
    protected List<Object> getModules() {
        return Arrays.asList(
                new GlobalModule(),
                new ContextModule(this),
                new RestClient()
        );
    }
 
    public void inject(Object className) {
        objectGraph.inject(className);
    }
}
