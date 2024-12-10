package com.example.hello_world;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.compose.ui.platform.ComposeView;

import com.example.hello_world.features.todo.TodoService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {

    @Inject
    MainView mainView;

    @Inject
    TodoService todoService;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    Store store;

    @Inject
    Model model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enableEdgeToEdge()
        var view = new ComposeView(this);
        mainView.configureContentView(view);
        setContentView(view);
        Log.i(TAG, String.format("im onCreate %s %d", model.greeting, model.count));

        store.subject.subscribe(model -> {
            Log.d(TAG, String.format("Todos recieved %d",model.todos.length));
        });

        todoService.getAll();
    }


    @Override
    protected void onPause() {
        super.onPause();
        model.count++;
        Log.i(TAG, String.format("im onPause %d", model.count));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "im onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "im onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "im onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "im onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "im onResume");
    }
}

