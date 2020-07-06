package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
public class PhoneBookController {
	
	@Autowired
	PhoneDao phoneDao;
	
	@RequestMapping("/writeForm")
	public String writeForm() {

		return "writeForm";
	}

	@RequestMapping("/list")
	public String list(Model model) {

		List<PersonVo> pList = phoneDao.getPersonList();
		System.out.println(pList.toString());

		model.addAttribute("pList", pList);

		return "list";
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

		return "updateForm";
	}

	// @PathVariable("no") int personId 사용방법!!!!!!
	/*
	 * @RequestMapping(value = "/updateForm/{no}", method =
	 * {RequestMethod.GET,RequestMethod.POST}) public String updateForm(Model
	 * model,@PathVariable("no") int personId) {
	 * System.out.println("/phone/updateForm"); System.out.println(personId);
	 * 
	 * PersonVo person = phoneDao.getPerson(personId);
	 * System.out.println(person.toString());
	 * 
	 * model.addAttribute("person", person);
	 * 
	 * return "/WEB-INF/views/updateForm.jsp"; }
	 */

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

	@RequestMapping("/test/{no}")
	public String test(@PathVariable("no") int personId) {
		System.out.println(personId);
		return "index";
	}

}
