package com.skills.tute.service;

import com.skills.tute.dto.NotesRequest;
import com.skills.tute.entity.Notes;

public interface NotesService {

    Notes save(Notes notes);

    Notes update(Notes notes);

    Notes findByTopicAndUserId(Integer topicId, Integer userId);
}
