package fr.univamu.annuaire.web;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.model.Person;
import fr.univamu.annuaire.repository.GroupRepository;
import fr.univamu.annuaire.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping()
    public ModelAndView search(@RequestParam("q") String query) {
        ModelAndView result = new ModelAndView("global_search");
        result.addObject("query", query);

        query = "%" + query + "%";

        List<Person> persons = personRepository.findByFirstNameLikeIgnoreCase(query);
        persons.addAll(personRepository.findByLastNameLikeIgnoreCase(query));
        List<Group> groups = groupRepository.findByNameLikeIgnoreCase(query);

        result.addObject("persons", new LinkedHashSet<>(persons));
        result.addObject("groups", new LinkedHashSet<>(groups));

        return result;
    }
}
