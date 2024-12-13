package com.abm.abm.Controller;

import com.abm.abm.Entity.Task;
import com.abm.abm.Entity.UserTask;
import com.abm.abm.Services.UserTaskService;
import com.abm.abm.Utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserTaskController {
    @Autowired
    private UserTaskService userTaskService;

    @PostMapping(value = "task/create")
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest request, @RequestBody UserTask userTask) {
        try {
            UserTask task = userTaskService.create(request , userTask);

            return ResponseUtil.successResponse(task);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @GetMapping(value = "task/get-time")
    public ResponseEntity<Map<String, Object>> getTine(HttpServletRequest request) {
        try {
            Map<String, Object> data = userTaskService.getTime(request);
            return ResponseUtil.successResponse(data);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping(value = "/rewards")
    public ResponseEntity<Map<String, Object>> getRewards(HttpServletRequest request) {
        try {
            Map<String, Object> data = userTaskService.rewards(request);
            return ResponseUtil.successResponse(data);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    @PostMapping(value = "/get-feedback")
    public ResponseEntity<Map<String, Object>> getFeedback(@RequestBody Map<String, String> body) {
        try {
            String data = userTaskService.getFeedback(body);
            return ResponseUtil.successResponse(data);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping(value = "/save-individual")
    public ResponseEntity<Map<String, Object>> saveIndividualTask(HttpServletRequest request, @RequestBody Task task) {
        try {
            System.out.println(task.getNegative());
            Task data = userTaskService.saveIndividualTask(request, task);
            return ResponseUtil.successResponse(data);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    
    @GetMapping(value = "get-timings")
    public ResponseEntity<Map<String, Object>> getIndividualTimings(HttpServletRequest request) {
        try {
            List<Task> data = userTaskService.getIndividualTimings(request);
            return ResponseUtil.successResponse(data);
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Internal Server Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
