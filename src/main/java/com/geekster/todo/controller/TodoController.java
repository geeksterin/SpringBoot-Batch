package com.geekster.todo.controller;

import com.geekster.todo.model.ToDo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @GetMapping("/getList")
    public ToDo getTodoTasks() {

        ToDo toDo = new ToDo();
        toDo.setId(1);
        toDo.setTitle("Lunch");
        toDo.setStatus(true);

        return toDo;
    }

}
