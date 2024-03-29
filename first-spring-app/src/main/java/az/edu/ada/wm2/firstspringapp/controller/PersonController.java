package az.edu.ada.wm2.firstspringapp.controller;

import az.edu.ada.wm2.firstspringapp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import az.edu.ada.wm2.firstspringapp.model.Person;

@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping({"", "/"})
  public String listPersons(Model model) {
    model.addAttribute("persons", personService.listPersons());
    return "person_list";
  }

  @GetMapping("/new")
  public String newForm(Model model){
    model.addAttribute("person", new Person("", ""));
    return "new_person_form";
  }

  @PostMapping("/save")
  public String savePerson(Model model, @ModelAttribute("person") Person newPerson) {
    personService.addPerson(newPerson);
    return "redirect:/persons";
  }

  @GetMapping("/delete/{idx}")
  public String deletePerson(@PathVariable Integer idx){
    personService.deletePerson(idx);
    return "redirect:/persons";
  }
}
