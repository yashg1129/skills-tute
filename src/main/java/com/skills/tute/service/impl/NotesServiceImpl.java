package com.skills.tute.service.impl;

import com.skills.tute.entity.DefaultNotes;
import com.skills.tute.entity.Notes;
import com.skills.tute.entity.Topic;
import com.skills.tute.repository.DefaultNotesRepository;
import com.skills.tute.repository.NotesRepository;
import com.skills.tute.service.CommonService;
import com.skills.tute.service.NotesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotesServiceImpl implements NotesService {

    private final static Map<Integer, Topic> defaultNotesMap = new HashMap<>();

    @Autowired
    private NotesRepository repository;

    @Autowired
    private DefaultNotesRepository defaultNotesRepository;

    @Autowired
    private CommonService commonService;

    @Override
    @Transactional
    public Notes save(Notes notes) {

        Topic topic = commonService.getTopicForUpdate(notes.getTopic());
        notes.setTopic(topic);

        Topic defalutTopic = defaultNotesMap.get(notes.getUserId());
        if(!defalutTopic.equals(topic)) {
            saveDefaultNotes(topic, notes.getUserId());
        }

        return repository.save(notes);
    }

    @Override
    public Notes update(Notes notes) {
        return repository.save(notes);
    }

    @Override
    public Notes findByTopicAndUserId(Integer topicId, Integer userId) {

        Topic defalutTopic = defaultNotesMap.get(userId);
        if(defalutTopic == null) {
            DefaultNotes defaultNotes = defaultNotesRepository.findById(userId).orElse(new DefaultNotes());
            defalutTopic = defaultNotes.getTopic();
            defaultNotesMap.put(userId, defalutTopic);
        }

        Topic topic;
        if(topicId == 0) {
            topic = defalutTopic;
        } else {
            topic = new Topic(topicId);
            if(!defalutTopic.equals(topic)) {
                saveDefaultNotes(topic, userId);
            }

        }
        return repository.findByTopicAndUserId(topic, userId);
    }

    private void saveDefaultNotes(Topic topic, Integer userId) {
        DefaultNotes defaultNotes = defaultNotesRepository.findById(userId).orElse(null);
        if(defaultNotes == null) {
            defaultNotes = new DefaultNotes();
        }
        defaultNotes.setId(userId);
        defaultNotes.setTopic(topic);
        defaultNotesRepository.save(defaultNotes);
        defaultNotesMap.put(userId, topic);
    }
}
