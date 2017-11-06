package com.xsty.telegram.web;

import com.xsty.scheduler.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getTasks(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTask(@PathVariable("id") Long id){
        return ResponseEntity.ok(taskService.findOne(id));
    }

    @GetMapping(path = "/{id}/cancel")
    public ResponseEntity<?> cancelTask(@PathVariable("id") Long id){
        return ResponseEntity.ok(taskService.cancelTask(id));
    }
}
