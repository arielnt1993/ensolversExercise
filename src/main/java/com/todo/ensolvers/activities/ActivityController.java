package com.todo.ensolvers.activities;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Activity> getActivities(){
        return  activityService.getActivities();
    }

    @PostMapping
    public void registerNewActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }

    @DeleteMapping
    public void deleteActivity(@RequestBody Activity activity){
        activityService.deleteActivity(activity.getId());
    }

    @PutMapping
    public void updateActivity(@RequestBody Activity activity){
        activityService.updateActivity(activity);
    }
}
