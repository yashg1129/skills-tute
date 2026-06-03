package com.skills.tute.dto;

import com.skills.tute.entity.InterviewQuestion;

public record LikeRequest(InterviewQuestion interviewQuestion, Boolean userLike) {
}
