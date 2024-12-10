package com.example.hello_world.features.todo;

import android.util.Log;

import com.example.hello_world.Model;
import com.example.hello_world.Store;
import com.example.hello_world.features.RestApiClientBuilder;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TodoService {

    private static final String TAG = TodoService.class.getSimpleName();
    public final TodoClient todoClient;

    @Inject
    Store store;

    @Inject
    TodoService(RestApiClientBuilder builder) {
        final String baseUrl = "https://jsonplaceholder.typicode.com/";
        todoClient = builder.build(TodoClient.class, baseUrl);
    }

    public void getAll() {
        CompletableFuture
                .supplyAsync(todoClient::getAll)
                .thenAccept(todos -> {
                    var model = new Model();
                    model.todos = todos;

                    store.subject.onNext(model);
                });
    }
}
