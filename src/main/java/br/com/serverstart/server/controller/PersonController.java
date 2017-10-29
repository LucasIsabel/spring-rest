package br.com.serverstart.server.controller;

import br.com.serverstart.server.model.Person;
import br.com.serverstart.server.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/persons")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity <?> getPersons(){
        return personService.getAllPersonInDataBase();
    }

    @PostMapping
    public ResponseEntity <?> savePerson(@RequestBody Person person){
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
