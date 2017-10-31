package br.com.serverstart.server.controller;

import br.com.serverstart.server.model.Person;
import br.com.serverstart.server.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/persons")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity <?> getPersons(Pageable pageable){
        return personService.getAllPersonInDataBase(pageable);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity <?> savePerson(@Valid @RequestBody Person person){
        return personService.savePerson(person);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getPersonById(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> deletePerson(@PathVariable Long id){
       return personService.deletePersonById(id);
    }

    @PutMapping
    public ResponseEntity <?> updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

}
