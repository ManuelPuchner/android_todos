package com.example.hello_world

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hello_world.features.todo.Todo
import com.example.hello_world.ui.theme.HelloworldTheme
import java.util.Arrays
import javax.inject.Inject
import javax.inject.Singleton
import androidx.compose.foundation.lazy.items

@Singleton
class MainView @Inject constructor(){

    @Inject
    lateinit var store: Store

    fun configureContentView(view: ComposeView) {
        view.setContent {
            HelloworldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        store
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, store: Store) {
    var model = store.subject.subscribeAsState(Model()).value

    var todos = model.todos

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(todos.size) { ToDoRow(todos[it]) }

    }
}

@Composable
fun ToDoRow(toDo: Todo) {

    Row(modifier = Modifier.padding(4.dp)) {
        Text(toDo.id.toString(), modifier = Modifier.padding(horizontal = 6.dp))
        Text(text = toDo.title, overflow = TextOverflow.Ellipsis, maxLines = 1)
    }
}



