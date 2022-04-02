package fr.univamu.annuaire.web;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.model.PersonUpdateFormModel;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import fr.univamu.annuaire.validators.PersonUpdateFormModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonUpdateFormModelValidator personFormValidator;

    @GetMapping()
    public ModelAndView getAll() {
        List<Person> persons = personRepository.findAll();

        return new ModelAndView("person_list", "persons", persons);
    }

    @GetMapping("/{id}")
    public ModelAndView getGroupById(@PathVariable Long id) {
        Optional<Person> personPromise = personRepository.findById(id);
        if (personPromise.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");

        ModelAndView result = new ModelAndView("person");
        Person person = personPromise.get();
        List<Group> groupsOfPerson = groupRepository.findByPersonsContaining(person);

        result.addObject("person", person);
        result.addObject("groups", groupsOfPerson);

        return result;
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("isAuthenticated() && #id == principal.person.id")
    public ModelAndView editProfile(@PathVariable Long id) {
        Optional<Person> personPromise = personRepository.findById(id);
        if (personPromise.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");

        ModelAndView result = new ModelAndView("person_edit");
        PersonUpdateFormModel person = new PersonUpdateFormModel(personPromise.get());

        result.addObject("person", person);

        return result;
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("isAuthenticated() && #id == principal.person.id")
    public String confirmEditProfilte(@PathVariable Long id, @ModelAttribute("person") PersonUpdateFormModel personUpdateForm, BindingResult result) {
        personFormValidator.validate(personUpdateForm, result);
        if (result.hasErrors())
            return "person_edit";

        personRepository.save(personUpdateForm.toPerson());
        return "redirect:/person/" + id;
    }
}
