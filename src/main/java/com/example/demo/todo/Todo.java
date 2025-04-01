package com.example.demo.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@Setter
@ToString
public class Todo {
  private int tno;
  private String title;
  private String content;
  private LocalDate regDate = LocalDate.now();
  @DateTimeFormat(pattern="yyyy-mm-dd")
  private LocalDate deadline;
  private boolean finish = false;
}
