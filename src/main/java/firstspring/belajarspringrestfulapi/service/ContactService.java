package firstspring.belajarspringrestfulapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import firstspring.belajarspringrestfulapi.entity.Contact;
import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.ContactResponse;
import firstspring.belajarspringrestfulapi.model.CreateContactRequest;
import firstspring.belajarspringrestfulapi.repository.ContactRepository;
import jakarta.transaction.Transactional;

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

        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    public ContactResponse get(User user, String id){

        Contact contact = contactRepository.getReferenceById(id);
        
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
