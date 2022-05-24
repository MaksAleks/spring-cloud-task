package ru.max.springcloudtask;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.max.springcloudtask.domain.Post;
import ru.max.springcloudtask.domain.repo.PostRepo;

@Service
@RequiredArgsConstructor
@Transactional
public class MaxJpaRunner implements ApplicationRunner {

    private final PostRepo postRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = Post.builder()
                .title("new post")
                .build();
        postRepo.saveAndFlush(post);
    }
}
