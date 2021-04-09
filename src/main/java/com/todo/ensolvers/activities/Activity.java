package com.todo.ensolvers.activities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="task")
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    private Long folder_id = 0L;
    private Long user_id;
    private boolean done;


    public Activity(){

    }

    public Activity(String name){
        this.name = name;
    }
    public Activity(Long id){
        this.id = id;
    }
    public Activity(Long id, String name, Long folder_id, Long user_id, boolean done) {
        this.id = id;
        this.name = name;
        this.folder_id = folder_id;
        this.user_id = user_id;
        this.done = done;
    }

    public Activity(Long id, boolean done) {
        this.id = id;
        this.done = done;
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
    public Long getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(Long folder_id) {
        this.folder_id = folder_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
