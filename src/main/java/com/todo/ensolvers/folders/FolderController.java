package com.todo.ensolvers.folders;

import com.todo.ensolvers.activities.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService){
        this.folderService = folderService;
    }

    @GetMapping
    public ResponseEntity<List<Folder>> getFolders(){
        List<Folder> folders = folderService.getFolders();
        return new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @PostMapping
    public void registerNewFolder(@RequestBody Folder folder){
        folderService.addFolder(folder);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFolder(@PathVariable("id") Long id){
        folderService.deleteFolder(id);
    }

    @PutMapping
    public void updateFolder(@RequestBody Folder folder){
        folderService.updateFolder(folder);
    }
}
