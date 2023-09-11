package firstspring.belajarspringrestfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.ContactResponse;
import firstspring.belajarspringrestfulapi.model.CreateContactRequest;
import firstspring.belajarspringrestfulapi.model.UpdateContactRequest;
import firstspring.belajarspringrestfulapi.model.WebResponse;
import firstspring.belajarspringrestfulapi.service.ContactService;

@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @PostMapping(path = "/api/contacts",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> create(User user, @RequestBody CreateContactRequest request){
        // log.info("USER"+  user);
        ContactResponse contactResponse =  contactService.create(user, request);

        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @GetMapping(path = "/api/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> get(User user,@PathVariable("contactId") String contactId){
        ContactResponse contactResponse = contactService.get(user, contactId);

        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }

    @PutMapping(path = "/api/contacts/{contactId}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ContactResponse> update(User user, @RequestBody UpdateContactRequest request, @PathVariable("contactId") String contactId){

        request.setId(contactId);
        ContactResponse contactResponse =  contactService.update(user, request);

        return WebResponse.<ContactResponse>builder().data(contactResponse).build();
    }
}
