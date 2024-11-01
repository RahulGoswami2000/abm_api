package com.abm.abm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.abm.abm.Entity.Weed;
import com.abm.abm.Services.CommonService;
import com.abm.abm.Utils.ResponseUtil;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.List;


@RestController
public class CommonController {
     @Autowired
     private CommonService commonService;

     @GetMapping(value = "weed-list")
     public ResponseEntity<Map<String, Object>> getWeedList() {
     System.out.println('1');
         List<Weed> weeds = commonService.getWeeList();
         System.out.println(weeds);
         if(weeds.isEmpty()) {
               return ResponseUtil.errorResponse("No weed list", HttpStatus.NOT_FOUND.value());
         }
         return ResponseUtil.successResponse(weeds);
     }
     
}
