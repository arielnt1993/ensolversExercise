package com.todo.ensolvers.activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/activities")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getActivities(){
        List<Activity> activities = activityService.getActivities();
        return  new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping
    public void registerNewActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActivity(@PathVariable("id") Long id){
        activityService.deleteActivity(id);
    }

    @PutMapping
    public void updateActivity(@RequestBody Activity activity){
        activityService.updateActivity(activity);
    }

    @PutMapping(path = "status")
    public void updateActivityStatus(@RequestBody Activity activity){ activityService.updateActivityStatus(activity);}
}
