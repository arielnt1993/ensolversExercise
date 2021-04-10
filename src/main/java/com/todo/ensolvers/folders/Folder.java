package com.todo.ensolvers.folders;

import com.todo.ensolvers.activities.Activity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Long user_id;
    @Transient
    private List<Activity> activities;
    public Folder() {
    }

    public Folder(Long id) {
        this.id = id;
    }

    public Folder(Long id, String name, Long user_id) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;

    }

    public Folder(Long id, String name, Long user_id, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
