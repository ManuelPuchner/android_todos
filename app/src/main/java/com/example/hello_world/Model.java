package com.example.hello_world;

import com.example.hello_world.features.todo.Todo;

import java.lang.reflect.Array;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Model {
    public String greeting = "Hallo Welt!";
    public int count = 0;

    public Todo[] todos = {
            new Todo(1,1,"Hallo",true)
    };

    @Inject
    public Model() {

    }
}
