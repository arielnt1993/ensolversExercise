package com.todo.ensolvers.management;


import com.todo.ensolvers.activities.Activity;
import com.todo.ensolvers.activities.ActivityService;
import com.todo.ensolvers.folders.Folder;
import com.todo.ensolvers.folders.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/manager/")
public class ManageController {


    private final ActivityService activityService;
    private final FolderService folderService;

    @Autowired
    public ManageController(ActivityService activityService, FolderService folderService){
        this.activityService= activityService;
        this.folderService = folderService;
    }

    @GetMapping("activity")
    public ResponseEntity<List<Activity>> getActivities(){
        List<Activity> activities = activityService.getActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
    @PostMapping("activity")
    public void registerNewActivity(@RequestBody Activity activity){
        activityService.addActivity(activity);
    }

    @DeleteMapping("/activity/{id}")
    public void  deleteActivity(@PathVariable("id") Long id){
        activityService.deleteActivity(id);
    }

    @PutMapping("avtivity")
    public void updateActivity(@RequestBody Activity activity){
        activityService.updateActivity(activity);
    }


    @PutMapping(path = "activity/active")
    public void updateActivityStatus(@RequestBody Activity activity){ activityService.updateActivityStatus(activity);}
    //End of activity api's


    //Start of folder api's
    @GetMapping("folder")
    public ResponseEntity<List<Folder>> getFolders(){
        List<Folder> folders = folderService.getFolders();
        return new ResponseEntity<>(folders,HttpStatus.OK);
    }
    @PostMapping("folder")
    public void registerNewFolder(@RequestBody Folder folder){
        folderService.addFolder(folder);
    }
    @DeleteMapping("/folder/{id}")
    public void deleteFolder(@PathVariable("id") Long id){
        folderService.deleteFolder(id);
    }

    @PutMapping("folder")
    public void updateFolder(@RequestBody Folder folder){
        folderService.updateFolder(folder);
    }

}
