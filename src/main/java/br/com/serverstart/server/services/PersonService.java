package br.com.serverstart.server.services;

import br.com.serverstart.server.error.InternalServerErrorException;
import br.com.serverstart.server.error.NotFoundException;
import br.com.serverstart.server.model.Person;
import br.com.serverstart.server.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
@Transactional
public class PersonService {

    private PersonRespository personRespository;

    @Autowired
    PersonService(PersonRespository personRespository){
        this.personRespository = personRespository;
    }

    public ResponseEntity<?> getAllPersonInDataBase(Pageable pageable){
        return new ResponseEntity<Object>(personRespository.findAll(pageable), HttpStatus.OK);
    }

    public ResponseEntity<?> savePerson(Person person){
        personRespository.save(person);
        return new ResponseEntity<Object>(personRespository.save(person), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getPersonById(Long id){
        verifyIfObjectIsNull(id);
        return new ResponseEntity<>(personRespository.findOne(id), HttpStatus.OK);
    }

    public ResponseEntity<?> deletePersonById(Long id){
        if(Objects.isNull(personRespository.findOne(id))){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        personRespository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity <?> updatePerson(Person person){
        return new ResponseEntity<>(personRespository.save(person), HttpStatus.OK);
    }

    private void verifyIfObjectIsNull (Long id){
        if(Objects.isNull(personRespository.findOne(id)))
            throw new NotFoundException("Could not find user for id : " + id);
    }

}
