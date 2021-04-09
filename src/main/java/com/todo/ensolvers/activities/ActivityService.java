package com.todo.ensolvers.activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void addActivity(Activity activity){
        Optional<Activity> activityOptional= activityRepository.findActivityByName(activity.getName());
        activity.setUser_id(1L);
        if(activityOptional.isPresent()){
            throw new IllegalStateException("activity already exists");
        }
        activityRepository.save(activity);
    }
    public List<Activity> getActivities(){
        List<Activity> activities = activityRepository.findAll();
        activities.removeIf(activity -> activity.getFolder_id()!=0);
        return activities;
    }

    public void deleteActivity(Long activityId) {
        boolean exists = activityRepository.existsById(activityId);

        if(!exists){
            throw new IllegalStateException("activity with id "+activityId+" does not exists");
        }else {
            activityRepository.deleteById(activityId);
        }
    }

    @Transactional
    public void updateActivity(Activity activity) {
        Activity oldActivity = activityRepository.
                findById(activity.getId()).
                orElseThrow(()-> new IllegalStateException(
                        "activity with the id "+ activity.getId()+" does not exists"
                ));
        if (activity.getName() != null &&
                activity.getName().length() > 0 &&
                !Objects.equals(oldActivity.getName(),activity.getName())
        ){
            oldActivity.setName(activity.getName());
        }

    }
    @Transactional
    public void updateActivityStatus(Activity activity){
        Activity oldActivity = activityRepository
                .findById(activity.getId())
                .orElseThrow(()-> new IllegalStateException(
                        "activity with the id "+ activity.getId()+" does not exists"
                ));
        oldActivity.setDone(activity.isDone());
    }
}
