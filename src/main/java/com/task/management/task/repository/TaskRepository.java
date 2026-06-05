package com.task.management.task.repository;

import com.task.management.auth.entity.User;
import com.task.management.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //Even if it is not defined, by default, a search by id(default) returns an Optional<>
    Page<Task> findByUser(User user, Pageable pageable);

    //Retrieve a specific user's tasks to prevent them from accessing other users tasks
    Optional<Task> findByIdAndUser(long id, User user);
}

