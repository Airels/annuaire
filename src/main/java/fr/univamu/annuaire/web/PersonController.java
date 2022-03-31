package fr.univamu.annuaire.web;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
