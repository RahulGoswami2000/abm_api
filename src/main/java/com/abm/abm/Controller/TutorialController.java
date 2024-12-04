package com.abm.abm.Controller;

import com.abm.abm.Entity.Contact;
import com.abm.abm.Services.TutorialService;
import com.abm.abm.Utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @PostMapping(value = "/contact/send-message")
    public ResponseEntity<Map<String, Object>> sendMessage(HttpServletRequest request, @RequestBody Contact contact) {
        try {
            Map<String, Object> data = tutorialService.contact(request, contact);
            return ResponseUtil.successResponse(data);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
