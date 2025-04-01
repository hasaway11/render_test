package com.example.demo.todo;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class TodoController {
  private final List<Todo> todos = new ArrayList<>();
  private int tno = 1;

  @GetMapping("/todo/list")
  public ModelAndView list() {
    return new ModelAndView("todo/list").addObject("todos", todos);
  }

  @GetMapping("/todo/write")
  public void write() {
  }

  @PostMapping("/todo/write")
  public ModelAndView write(@ModelAttribute Todo todo) {
    todo.setTno(tno++);
    todos.add(todo);
    return new ModelAndView("redirect:/todo/list");
  }

  @GetMapping("/todo/read")
  public ModelAndView read(@RequestParam int tno) {
    for(Todo todo:todos) {
      if(todo.getTno()==tno)
        return new ModelAndView("todo/read").addObject(todo);
    }
    return new ModelAndView("redirect:/todo/list");
  }

  @PostMapping("/todo/finish")
  public ModelAndView finish(Integer tno) {
    for (Todo todo:todos) {
      if(todo.getTno()==tno) {
        todo.setFinish(true);
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }

  @PostMapping("/todo/delete")
  public ModelAndView delete(Integer tno) {
    for (int i = todos.size() - 1; i >= 0; i--) {
      if (todos.get(i).getTno() == tno) {
        todos.remove(i);
      }
    }
    return new ModelAndView("redirect:/todo/list");
  }
}
