package com.skills.tute.service.impl;

import com.skills.tute.entity.Like;
import com.skills.tute.repository.LikeRepository;
import com.skills.tute.service.LikeService;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;

    public LikeServiceImpl(LikeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Like save(Like like) {
        Like res = repository.findByInterviewQuestionAndUserId(like.getInterviewQuestion(), like.getUserId());
        if(res != null) {
            res.setUserLike(like.getUserLike());
            like = res;
        }
        return repository.save(like);
    }

}
