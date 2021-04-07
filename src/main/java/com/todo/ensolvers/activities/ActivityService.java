package com.todo.ensolvers.activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void addActivity(Activity activity){
        Optional<Activity> activityOptional= activityRepository.findActivityByName(activity.getName());

        if(activityOptional.isPresent()){
            throw new IllegalStateException("activity already exists");
        }
        activityRepository.save(activity);
    }
    public List<Activity> getActivities(){
        return activityRepository.findAll();
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
        System.out.println(activity);
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
}
