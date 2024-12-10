package com.example.hello_world;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Model {
    public String greeting = "Hallo Welt!";
    public int count = 0;

    @Inject
    public Model() {

    }
}
