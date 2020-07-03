package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
public class PhoneBookController {
	PhoneDao phoneDao = new PhoneDao();
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<PersonVo> pList = phoneDao.getPersonList();
		System.out.println(pList.toString());
		
		model.addAttribute("pList", pList);
		
		return "/WEB-INF/views/list.jsp";
		
	}
	
	
	@RequestMapping("/write")
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/write");
		System.out.println(personVo.toString());
		
		phoneDao.personInsert(personVo);
		
		return "redirect:/phone/list";
		
	}
	
	@RequestMapping("/updateForm")
	public String updateForm(Model model, @RequestParam("pid") int personId) {
		System.out.println("/phone/updateForm");
		System.out.println(personId);
		
		PersonVo person = phoneDao.getPerson(personId);
		System.out.println(person.toString());
		
		model.addAttribute("person", person);
		
		return "/WEB-INF/views/updateForm.jsp";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/update");
		System.out.println(personVo.toString());
		phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("pid") int personId) {
		System.out.println("/phone/delete");
		System.out.println(personId);
		
		phoneDao.personDeletSe(personId);
		
		return "redirect:/phone/list";
	}
	
	
}
