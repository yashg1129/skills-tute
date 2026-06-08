package com.skills.tute.service.impl;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.Like;
import com.skills.tute.repository.InterviewQuestionRepository;
import com.skills.tute.repository.LikeRepository;
import com.skills.tute.service.LikeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Value("${st.like.points}")
    private Integer likePoints;

    @Value("${st.dislike.points}")
    private Integer dislikePoints;

    private final LikeRepository repository;

    private final InterviewQuestionRepository questionRepository;

    public LikeServiceImpl(LikeRepository repository, InterviewQuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public Like save(Like like) {
        Like result = repository.findByInterviewQuestionAndUserId(like.getInterviewQuestion(), like.getUserId());
        updateInterviewQuestion(like, result);
        if(result != null) {
            result.setUserLike(like.getUserLike());
            like = result;
        }
        return repository.save(like);
    }

    private void updateInterviewQuestion(Like like, Like result) {
        InterviewQuestion question = questionRepository.findById(like.getInterviewQuestion().getId()).orElse(null);
        assert question != null;
        Integer points = question.getPoints();
        if(result != null) {//User is already like/unlike
            if(result.getUserLike() == null) {
                if(like.getUserLike()) {
                    points += likePoints;
                } else {
                    points -= dislikePoints;
                }
            } else if(result.getUserLike() && like.getUserLike() == null) {
                points -= likePoints;
            } else if(!result.getUserLike() && like.getUserLike() == null) {
                points += dislikePoints;
            } else if(result.getUserLike() && !like.getUserLike()) {
                points -= likePoints;
                points -= dislikePoints;
            } else if(!result.getUserLike() && like.getUserLike()) {
                points += dislikePoints;
                points += likePoints;
            }
        } else {
            if(like.getUserLike()) {
                points += likePoints;
            } else {
                points -= dislikePoints;
            }
        }
        question.setPoints(points);
        questionRepository.save(question);
    }

}
