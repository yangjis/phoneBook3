package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/insert2")
	public String writeForm2(@RequestParam("name") String name,
							 @RequestParam("hp") String hp,
							 @RequestParam("company") String company) {
		System.out.println("insert4");
		
		phoneDao.personInsert2(name, hp, company);
		return "redirect:/phone/list";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list");

		List<PersonVo> pList = phoneDao.getPersonList();
		model.addAttribute("pList", pList);

		return "list";
	}

	
	  @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST}) 
	  public String insert(@ModelAttribute PersonVo personVo) {
		  System.out.println("insert");
		  
		  phoneDao.personInsert(personVo);
		  return "redirect:/phone/list"; 
	  }
	  
	  @RequestMapping("/updateForm")
	  public String updateForm(@RequestParam("pid") int person_id, Model model) {
		  System.out.println("updateForm");
		  
		  PersonVo vo = phoneDao.getData(person_id);
		  model.addAttribute("person", vo);
		  return "updateForm";
	  }
	  
	  @RequestMapping("/update")
	  public String update(@ModelAttribute PersonVo personVo) {
		  System.out.println("update");
		  
		  phoneDao.personUpdate(personVo);
		  return "redirect:/phone/list";
	  }
	
	  @RequestMapping("/delete")
	  public String delete(@RequestParam("pid") int person_id) {
		  System.out.println("delete");
		  
		  phoneDao.personDelet(person_id);
		  return "redirect:/phone/list";
	  }

	 
	 
}
