package com.todo.ensolvers.folders;

import com.todo.ensolvers.activities.Activity;
import com.todo.ensolvers.activities.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FolderService {
    private final FolderRepository folderRepository;
    private final ActivityRepository activityRepository;
    @Autowired
    public FolderService(FolderRepository folderRepository, ActivityRepository activityRepository){
        this.folderRepository = folderRepository;
        this.activityRepository = activityRepository;
    }

    public List<Folder> getFolders(){
        List<Folder> folders = folderRepository.findAll();
        List<Activity> activities = activityRepository.findAll();
        folders.forEach(folder -> {
            folder.setActivities(activities.stream()
                    .filter(activity -> activity.getFolder_id().equals(folder.getId()))
                    .collect(Collectors.toList()));
        });

        return folders; //folderRepository.findAll();
    }

    public void addFolder(Folder folder){
        Optional<Folder> folderOptional = folderRepository.findFolderByName(folder.getName());
        folder.setUser_id(1L);
        if(folderOptional.isPresent()){
            throw new IllegalStateException("folder already exists");
        }
        folderRepository.save(folder);
    }

    @Transactional
    public void updateFolder(Folder folder){
        Folder oldFolder = folderRepository
                .findById(folder.getId())
                .orElseThrow(()-> new IllegalStateException(
                        "folder with the id " + folder.getId() +" does not exist"
                ));
        if(folder.getName() != null && folder.getName().length() > 0
                && !Objects.equals(oldFolder.getName(),folder.getName())){
            oldFolder.setName(folder.getName());
        }
    }

    public void deleteFolder(Long folderId){
        boolean exists = folderRepository.existsById(folderId);
        List<Activity> activities = activityRepository.findAll();
        if(!exists){
            throw new IllegalStateException("folder with id "+folderId+" does not exists");
        }else{
            folderRepository.deleteById(folderId);
            activities.forEach(activity -> {
                if(activity.getFolder_id().equals(folderId)){
                    activityRepository.deleteById(activity.getId());
                }
            });
        }
    }
}
