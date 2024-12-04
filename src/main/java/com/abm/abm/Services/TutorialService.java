package com.abm.abm.Services;

import com.abm.abm.Entity.Contact;
import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Utils.EmailSenderUtil;
import com.abm.abm.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class TutorialService {
    @Autowired
    private EmailSenderUtil emailSenderUtil;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, Object> contact (HttpServletRequest request, @RequestBody Contact contact) {
        MstUsers users = jwtUtil.getUser(request);
        String cc = users.getEmail();
        triggerEmail(cc, contact.getSubject(), contact.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("subject" , contact.getSubject());
        response.put("message", contact.getMessage());
        return response;
    }

    public void triggerEmail(String cc, String subject, String message) {
        emailSenderUtil.sendEmailWithoutAttachment(cc, subject, message);
    }
}
