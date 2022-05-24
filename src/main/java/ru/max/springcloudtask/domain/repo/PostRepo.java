package ru.max.springcloudtask.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.max.springcloudtask.domain.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
}
