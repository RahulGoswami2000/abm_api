package com.abm.abm.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

import com.abm.abm.Entity.Weed;
import com.abm.abm.Repository.WeedRepository;

@Service
public class CommonService {
     @Autowired
     private WeedRepository weedRepository;

     public List<Weed> getWeeList() {
          System.out.println("In service");
          List<Weed> weedList = new ArrayList<>();

          weedRepository.findAll()
          .forEach(weed -> weedList.add(weed));

          if(weedList.size() <=0) {
               return null;
          }
          return weedList;
     }
}
