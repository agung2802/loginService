package firstspring.belajarspringrestfulapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import firstspring.belajarspringrestfulapi.entity.Contact;
import firstspring.belajarspringrestfulapi.entity.User;
import firstspring.belajarspringrestfulapi.model.ContactResponse;
import firstspring.belajarspringrestfulapi.model.CreateContactRequest;
import firstspring.belajarspringrestfulapi.model.WebResponse;
import firstspring.belajarspringrestfulapi.repository.ContactRepository;
import firstspring.belajarspringrestfulapi.repository.UserRepository;
import firstspring.belajarspringrestfulapi.security.Bcrypt;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
        user.setName("agung");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 10000000);

        userRepository.save(user);
    }

    // @Test
    // public void createError() throws Exception{
        

    //     CreateContactRequest request = new CreateContactRequest();
    //     request.setFirstName("");
    //     request.setEmail("salah");

    //     mockMvc.perform(
    //         post("/api/contacts")
    //         .accept(MediaType.APPLICATION_JSON)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(request))
    //         .header("X-API-TOKEN", "test")
    //     ).andExpectAll(
    //         status().isBadRequest()
    //     ).andDo( result -> {

    //         WebResponse<String> response= objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
                
    //         });

    //         assertNotNull(response.getErrors());
    //     });
    // }

    // @Test
    // public void createSuccess() throws Exception{
        

    //     CreateContactRequest request = new CreateContactRequest();
    //     request.setFirstName("rizki");
    //     request.setEmail("abcd@gmail.com");
    //     request.setLastName("rizki");
    //     request.setPhone("08912379872");

    //     mockMvc.perform(
    //         post("/api/contacts")
    //         .accept(MediaType.APPLICATION_JSON)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(objectMapper.writeValueAsString(request))
    //         .header("X-API-TOKEN", "test")
    //     ).andExpectAll(
    //         status().isOk()
    //     ).andDo( result -> {

    //         WebResponse<ContactResponse> response= objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
                
    //         });

    //         assertNull(response.getErrors());
    //         assertEquals(("Agung"), response.getData().getFirstName());
    //     });
    // }

    // @Test
    // public void getContactNotFound() throws Exception{

    //     // Contact contact = contactRepository.findById("7a9f2c6c-95da-4b7e-bc50-86913dc453bd").orElse(null);
        
    //     mockMvc.perform(
    //         get("/api/contacts/31231232")
    //         .accept(MediaType.APPLICATION_JSON)
    //         .header("X-API-TOKEN", "test")
    //     ).andExpectAll(
    //         status().isNotFound()
    //     ).andDo(result -> {
    //         WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){
    //         });
    //         assertNotNull(response.getErrors());

    //     });
    // }

    // @Test
    // public void getContactSuccess() throws Exception{
    //     User user = new User();
    //     user.setUsername("test");
    //     user.setPassword(Bcrypt.hashpw("test", Bcrypt.gensalt()));
    //     user.setName("agung");
    //     user.setToken("test");
    //     user.setTokenExpiredAt(System.currentTimeMillis() + 10000000);

    //     userRepository.save(user);

    //     Contact contact = new Contact();
    //     contact.setFirstName("agung");
    //     contact.setLastName("Rizki");
    //     contact.setEmail("asad@gmail.com");
    //     contact.setId("test");
    //     contact.setUser(user);

    //     contactRepository.save(contact);
        
    //     mockMvc.perform(
    //         get("/api/contacts/test")
    //         .accept(MediaType.APPLICATION_JSON)
    //         .header("X-API-TOKEN", "test")
    //     ).andExpectAll(
    //         status().isOk()
    //     ).andDo(result -> {
    //         WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){
    //         });
    //         assertNull(response.getErrors());

    //         assertEquals("agung", response.getData().getFirstName());

    //         assertEquals("Rizki", response.getData().getLastName());
    //     });
    // }

    @Test
    public void createError() throws Exception{
        
        User user = userRepository.findById("test").orElseThrow();

        Contact contact = new Contact();
        contact.setFirstName("agung");
        contact.setLastName("Rizki");
        contact.setEmail("asad@gmail.com");
        contact.setId("test");
        contact.setUser(user);

        contactRepository.save(contact);

        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("hermawan");
        request.setEmail("abs@gmail.com");

        mockMvc.perform(
            put("/api/contacts/test")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
            .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo( result -> {

            WebResponse<ContactResponse> response= objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
                
            });

            assertNull(response.getErrors());

        });
    }
}
