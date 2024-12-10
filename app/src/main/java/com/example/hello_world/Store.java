package com.example.hello_world;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

@Singleton
public class Store {
    public BehaviorSubject<Model> subject;

    @Inject
    Store() {
        subject = BehaviorSubject.createDefault(new Model());
    }
}
