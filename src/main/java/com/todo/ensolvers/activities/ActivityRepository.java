package com.todo.ensolvers.activities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    //@Query("SELECT a FROM Activity a WHERE a.name = ?1")
    Optional<Activity> findActivityByName(String name);
}
