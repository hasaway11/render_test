package com.example.demo.contact;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class ContactController {
	private final List<Contact> contacts = new ArrayList<>();
	private int cno = 1;
	
	@GetMapping("/contact/list")
	public ModelAndView list() {
		return new ModelAndView("contact/list").addObject("contacts", contacts);
	}
	
	@GetMapping("/contact/write")
	public void write() {
	}
	
	@PostMapping("/contact/write")
	public ModelAndView write(Contact contact) {
		contact.setCno(cno++);
		contacts.add(contact);
		return new ModelAndView("redirect:/contact/list");
	}
	
	@GetMapping("/contact/read")
	public ModelAndView read(Integer cno) {
		for (Contact contact : contacts) {
			if (contact.getCno() == cno)
				return new ModelAndView("contact/read").addObject("contact", contact);
		}
		return new ModelAndView("redirect:/contact/list");
	}
	
	@PostMapping("/contact/update")
	public ModelAndView update(Contact newContact) {
		System.out.println(newContact);
		for (Contact contact : contacts) {
			if (contact.getCno() == newContact.getCno()) {
				contact.setTel(newContact.getTel());
				contact.setAddress(newContact.getAddress());
				System.out.println("After: " + contacts);  // 수정된 내용 확인
				break;
			}
		}
		return new ModelAndView("redirect:/contact/list");
	}
	
	@PostMapping("/contact/delete")
	public ModelAndView delete(int cno) {
		for (int i = contacts.size() - 1; i >= 0; i--) {
			if (contacts.get(i).getCno() == cno) {
				contacts.remove(i);
			}
		}
		return new ModelAndView("redirect:/contact/list");
	}
}
