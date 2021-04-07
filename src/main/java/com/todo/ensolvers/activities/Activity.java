package com.todo.ensolvers.activities;

import javax.persistence.*;


@Entity
@Table
public class Activity {

    @Id
    @SequenceGenerator(
            name = "activity_sequence",
            sequenceName = "activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_sequence"
    )
    private Long id;
    private String name;

    public Activity(){

    }

    public Activity(String name){
        this.name = name;
    }
    public Activity(Long id){
        this.id = id;
    }
    public Activity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString(){
        return "Activity{" +
                "id= " + id +
                ", name = '" + name +'\'' +
                '}';
    }
}
