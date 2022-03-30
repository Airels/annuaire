package fr.univamu.annuaire.web;

import fr.univamu.annuaire.model.Group;
import fr.univamu.annuaire.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping()
    public ModelAndView getAll() {
        List<Group> groups = groupRepository.findAll();

        return new ModelAndView("group_list", "groups", groups);
    }

    @GetMapping("/{id}")
    public ModelAndView getGroupById(@PathVariable Long id) {
        Optional<Group> groupPromise = groupRepository.findById(id);
        if (groupPromise.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");

        return new ModelAndView("group", "group", groupPromise.get());
    }
}
