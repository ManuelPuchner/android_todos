package com.example.hello_world.features.todo;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/todos")
public interface TodoClient {
    @GET
    List<Todo> getAll();

}
