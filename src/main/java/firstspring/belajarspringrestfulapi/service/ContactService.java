package firstspring.belajarspringrestfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import firstspring.belajarspringrestfulapi.entity.Contact;
import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.ContactResponse;
import firstspring.belajarspringrestfulapi.model.CreateContactRequest;
import firstspring.belajarspringrestfulapi.model.UpdateContactRequest;
import firstspring.belajarspringrestfulapi.repository.ContactRepository;

@Service
public class ContactService {
    
    @Autowired
    private ValidationService validator;

    @Autowired
    private ContactRepository contactRepository;

    @Transactional
    public ContactResponse create(User user, CreateContactRequest request){
        validator.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    private ContactResponse toContactResponse(Contact contact){
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    @Transactional(readOnly = true)
    public ContactResponse get(User user, String id){

        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));
        
        return toContactResponse(contact);
    }

    @Transactional
    public ContactResponse update(User user, UpdateContactRequest request){
        validator.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return toContactResponse(contact);
    }
}
