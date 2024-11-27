package com.abm.abm.Services;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Entity.UserTask;
import com.abm.abm.Repository.UserTaskRepository;
import com.abm.abm.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserTaskService {

    private static int REWARD = 100;
    private static int COUNT = 1;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserTaskRepository userTaskRepository;

    public UserTask create(HttpServletRequest request, UserTask userTask) {
        MstUsers users = jwtUtil.getUser(request);
        Optional<UserTask> isTaskPresent = userTaskRepository.findUser(users.getUser_id());
//        System.out.println(isTaskPresent);
        if (isTaskPresent.isEmpty()) {
            userTask.setUser_id(users.getUser_id());
            userTask.setTime_on_negative((userTask.getTime_on_negative())/60);
            userTask.setTime_on_positive((userTask.getTime_on_positive())/60);
            userTask.setReward(REWARD);
            userTask.setCount(COUNT);
        } else {
            UserTask existingTask = isTaskPresent.get();
            REWARD += existingTask.getReward();
            COUNT += existingTask.getCount();
            userTask.setUser_task_id(existingTask.getUser_task_id());
            userTask.setUser_id(users.getUser_id());
            userTask.setTime_on_negative((userTask.getTime_on_negative())/60);
            userTask.setTime_on_positive((userTask.getTime_on_positive())/60);
            userTask.setReward(REWARD);
            userTask.setCount(COUNT);
        }
        userTaskRepository.save(userTask);
        return userTask;
    }

    public Map<String, Object> getTime(HttpServletRequest request) {
        MstUsers users = jwtUtil.getUser(request);
        Optional<UserTask> isTaskPresent = userTaskRepository.findUser(users.getUser_id());
        if(isTaskPresent.isEmpty()) {
            return null;
        }
        UserTask userTask = isTaskPresent.get();

        // Calculate percentage values
        double totalTime = userTask.getTime_on_negative() + userTask.getTime_on_positive();
        double negativePercentage = (userTask.getTime_on_negative() / totalTime) * 100;
        double positivePercentage = (userTask.getTime_on_positive() / totalTime) * 100;

        // Prepare response structure
        Map<String, Object> response = new HashMap<>();
        response.put("timeOnNegative", userTask.getTime_on_negative());
        response.put("timeOnPositive", userTask.getTime_on_positive());
        response.put("negativePercentage", Math.round(negativePercentage * 100.0) / 100.0); // Rounded to 2 decimals
        response.put("positivePercentage", Math.round(positivePercentage * 100.0) / 100.0);

        return response;
    }
}
