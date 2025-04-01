package com.example.demo.supply;

import lombok.*;

import java.time.*;

@Getter
@Setter
@ToString
public class Supply {
	private int sno;
	private String name;
	private LocalDate regDate = LocalDate.now();
	private int count;
}
