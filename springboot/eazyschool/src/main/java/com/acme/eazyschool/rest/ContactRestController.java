package com.acme.eazyschool.rest;

import com.acme.eazyschool.constants.EazySchoolConstants;
import com.acme.eazyschool.model.Contact;
import com.acme.eazyschool.model.Response;
import com.acme.eazyschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/contact")
@RestController
public class ContactRestController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMessagesByStatus")
    public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact) {
        System.out.println(contact.toString());
        return contactRepository.findByStatus(contact.getStatus());
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(
            @RequestHeader("invocationFrom") String invocationFrom,
            @Valid @RequestBody Contact contact) {

        // Log to see content of header
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        System.out.println(contact.toString());

        // Save contact
        contactRepository.save(contact);

        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMessageSaved", "true")
                .body(response);

    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        // Get headers information
        HttpHeaders httpHeaders = requestEntity.getHeaders();
        httpHeaders.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, String.join("|", value)));
        });

        // Get payload
        Contact contact = requestEntity.getBody();

        // Delete message
        contactRepository.deleteById(contact.getContactId());

        // Set response
        Response response = new Response("200", "Message deleted successful");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contact) {
        Response response = new Response();

        int rowsAffected = contactRepository.updateMsgStatus(EazySchoolConstants.CLOSE, contact.getContactId());

        // Successful patch
        if (rowsAffected > 0) {

            response.setStatusCode("200");
            response.setStatusMsg("Message status successfully updated");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }

        response.setStatusCode("400");
        response.setStatusMsg("Invalid ContactID received");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
