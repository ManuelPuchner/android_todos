package com.example.hello_world.features.todo;

public class Todo {
    public int userId;
    public int id;
    public String title;
    public boolean completed;

    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Todo() {

    }
}
