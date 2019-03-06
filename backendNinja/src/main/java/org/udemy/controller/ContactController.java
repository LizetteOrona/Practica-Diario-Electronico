package org.udemy.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.udemy.constant.ViewConstant;
import org.udemy.model.ContactModel;
import org.udemy.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", required=false) int id, Model model) {
		ContactModel contactModel = new ContactModel();
		if(id != 0 ) {
			contactModel = contactService.findContactbyIDModel(id);
		}
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}
	
	@PostMapping("/addcontact")
	public ModelAndView addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD: addContact() -- PARAMS: "+ contactModel.toString());
		
		if(null != contactService.addContact(contactModel)) {
			redirectAttributes.addFlashAttribute("result", 1);
		}else {
			redirectAttributes.addFlashAttribute("result", 0);
		}
		mav.setViewName("redirect:/contacts/showcontacts");	
		return mav;
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS_VIEW);
		mav.addObject("contacts", contactService.listAllContacts());
		return mav;
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id", required=true) int id) {
		contactService.removeContact(id);
		return showContacts();
	}
	
	
}
