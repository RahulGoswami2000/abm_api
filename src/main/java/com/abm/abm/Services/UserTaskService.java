package com.abm.abm.Services;

import com.abm.abm.Entity.MstUsers;
import com.abm.abm.Entity.Survey;
import com.abm.abm.Entity.Task;
import com.abm.abm.Entity.UserTask;
import com.abm.abm.Repository.SurveyRepository;
import com.abm.abm.Repository.TaskRepository;
import com.abm.abm.Repository.UserTaskRepository;
import com.abm.abm.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserTaskService {

    private static String url = "https://api.openai.com/v1/chat/completions";

    private static Double REWARD = (double) 100;
    private static int COUNT = 1;

    @Autowired
    private JwtUtil jwtUtil;

    private String apiKey;

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public UserTask create(HttpServletRequest request, UserTask userTask) {
        MstUsers users = jwtUtil.getUser(request);
        Optional<UserTask> isTaskPresent = userTaskRepository.findUser(users.getUser_id());
        if (isTaskPresent.isEmpty()) {
            double totalTime = userTask.getTime_on_negative() + userTask.getTime_on_positive();
            Integer negativePercentage = (int) ((userTask.getTime_on_negative() / totalTime) * 100);
            REWARD = REWARD-negativePercentage;
            userTask.setUser_id(users.getUser_id());
            userTask.setTime_on_negative((userTask.getTime_on_negative())/60);
            userTask.setTime_on_positive((userTask.getTime_on_positive())/60);
            userTask.setReward(REWARD);
            userTask.setCount(COUNT);
        } else {
            UserTask existingTask = isTaskPresent.get();
            double negative_time = (existingTask.getTime_on_negative() + userTask.getTime_on_negative()/60);
            System.out.println("negative" + negative_time);
            System.out.println("Existing" + (existingTask.getTime_on_negative()));
            System.out.println(userTask.getTime_on_negative()/60 + "new");
            double positive_time = (existingTask.getTime_on_positive() + userTask.getTime_on_positive()/60);
            double totalTime = userTask.getTime_on_negative() + userTask.getTime_on_positive();
            Integer negativePercentage = (int) ((userTask.getTime_on_negative() / totalTime) * 100);
            REWARD = (100-negativePercentage) +existingTask.getReward();
            COUNT = 1+ existingTask.getCount();
            userTask.setUser_task_id(existingTask.getUser_task_id());
            userTask.setUser_id(users.getUser_id());
            userTask.setTime_on_negative(negative_time);
            userTask.setTime_on_positive(positive_time);
            userTask.setReward(REWARD);
            userTask.setCount(COUNT);
        }
        userTaskRepository.save(userTask);
        return userTask;
    }

    public Map<String, Object> getTime(HttpServletRequest request) {
        MstUsers users = jwtUtil.getUser(request);
        Optional<UserTask> isTaskPresent = userTaskRepository.findUser(users.getUser_id());
        Survey iSurvey = surveyRepository.findSurveys(users.getUser_id());
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
        response.put("survey", iSurvey);

        return response;
    }

    public Map<String, Object> rewards (HttpServletRequest request) {
        MstUsers users = jwtUtil.getUser(request);
        Optional<UserTask> isTaskPresent = userTaskRepository.findUser(users.getUser_id());

        if(isTaskPresent.isEmpty()) {
            return null;
        }
        UserTask data = isTaskPresent.get();
        Map<String, Object> response = new HashMap<>();
        response.put("completedTasks", data.getCount());
        response.put("totalPoints", data.getReward());
        return response;
    }


    public String getFeedback(@RequestBody Map<String, String> body) {
        apiKey = System.getenv("OPENAI_API_KEY");
        String prompt = body.get("prompt");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        String requestBody = String.format(
                "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}], \"max_tokens\": 250}",
                prompt
        );
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, entity, String.class);
        return response;
    }

    public Task saveIndividualTask(HttpServletRequest request, @RequestBody Task task) {
        MstUsers users = jwtUtil.getUser(request);
        task.setUser_id(users.getUser_id());
        task.setNegative((task.getNegative())/60);
        task.setPositive((task.getPositive())/60);
        task.setFeedback(task.getFeedback());
        taskRepository.save(task);
        return task;
    }

    public List<Task> getIndividualTimings(HttpServletRequest request) {
        MstUsers users = jwtUtil.getUser(request);
        List<Task> data = taskRepository.findAllByUserId(users.getUser_id());
        return data;
    }
}
