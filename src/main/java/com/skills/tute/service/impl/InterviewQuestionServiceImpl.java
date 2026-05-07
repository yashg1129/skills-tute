package com.skills.tute.service.impl;

import com.skills.tute.dto.InterviewQuestionResponse;
import com.skills.tute.entity.*;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.exception.DuplicateResourceException;
import com.skills.tute.exception.InvalidStateException;
import com.skills.tute.repository.*;
import com.skills.tute.service.AdminInterviewQuestionService;
import com.skills.tute.service.CommonService;
import com.skills.tute.service.InterviewQuestionService;

import com.skills.tute.service.ProgrammingInterviewQuestionService;
import com.skills.tute.utils.StConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.skills.tute.utils.StStringUtils.*;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    public static final String DUPLICATION_QUESTION = "Duplication question";

    @Value("${st.add.question.points}")
    private Integer addQuestionPoints;

    @Value("${st.update.question.points}")
    private Integer updateQuestionPoints;

    @Autowired
    private InterviewQuestionRepository repository;

    @Autowired
    private InterviewQuestionUserRepository questionUserRepository;

    @Autowired
    private AdminInterviewQuestionService adminInterviewQuestionService;

    @Autowired
    private InterviewQuestionUserRepository interviewQuestionUserRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CompanyRepository companyRepository1;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProgrammingInterviewQuestionRepository programmingQuestionRepository;

    @Autowired
    private ProgrammingInterviewQuestionService programmingService;

    @Autowired LikeRepository likeRepository;

    @Override
    @Transactional
    public InterviewQuestion save(InterviewQuestionUser request) {
        return copy(request);
    }

    private InterviewQuestion copy(InterviewQuestionUser questionUser) {
        InterviewQuestion request = questionUser.getInterviewQuestion();
        InterviewQuestion question = new InterviewQuestion();

        Topic topic = commonService.getTopicForUpdate(request.getTopic());
        question.setTopic(topic);

        saveQuestion(questionUser, topic);

        return question;
    }

    private void saveQuestion(InterviewQuestionUser questionUser, Topic topic) {
        InterviewQuestion interviewQuestion;
        InterviewQuestion request = questionUser.getInterviewQuestion();
        if (request.getId() != null && request.getProgrammingQuestion() == null) {
            interviewQuestion = repository.findById(request.getId()).orElse(null);
        } else {
            interviewQuestion = repository.findByTopicAndQuestionAndProgram(topic.getName(), request.getQuestion(), request.getProgrammingQuestion() != null ? request.getProgrammingQuestion().getProgram() : null);
        }

        if (interviewQuestion == null) {
            interviewQuestion = new InterviewQuestion();
            interviewQuestion.setApproveStatus(ApproveStatus.PENDING);
            interviewQuestion.setQuestion(request.getQuestion());
            interviewQuestion.setTopic(topic);
            interviewQuestion.setPoints(addQuestionPoints);
            interviewQuestion.setAskCount(1);
            interviewQuestion.setDate(LocalDateTime.now());
            interviewQuestion = repository.save(interviewQuestion);
            if (request.getProgrammingQuestion() != null) {
                ProgrammingInterviewQuestion programmingInterviewQuestion = new ProgrammingInterviewQuestion();
                programmingInterviewQuestion.setProgram(request.getProgrammingQuestion().getProgram());
                programmingInterviewQuestion.setInterviewQuestion(interviewQuestion);
                programmingQuestionRepository.save(programmingInterviewQuestion);
            }
        } else {
            interviewQuestion.setPoints(interviewQuestion.getPoints() + updateQuestionPoints);
            interviewQuestion.setAskCount(interviewQuestion.getAskCount() + 1);
            repository.save(interviewQuestion);
        }
        saveInterviewQuestionUser(interviewQuestion, questionUser);
    }

    private void saveInterviewQuestionUser(InterviewQuestion interviewQuestion, InterviewQuestionUser questionUserRequest) {
        InterviewQuestionUser interviewQuestionUser = new InterviewQuestionUser();
        interviewQuestionUser.setInterviewQuestion(interviewQuestion);
        interviewQuestionUser.setUserId(questionUserRequest.getUserId());

        Company company = commonService.getCompanyForUpdate(questionUserRequest.getCompany());
        interviewQuestionUser.setCompany(company);

        City city = questionUserRequest.getCity();
        if (city != null && city.getId() == null && isNotBlank(city.getName())) {
            city = new City();
            city.setName(firstCharCaps(city.getName()));
            city.setApproveStatus(ApproveStatus.PENDING);
            city = cityRepository.save(city);
        } else {
            city = null;
        }
        interviewQuestionUser.setCity(city);

        Country country = questionUserRequest.getCountry();
        if (country != null && country.getId() == null && isNotBlank(country.getName())) {
            country = new Country();
            country.setName(firstCharCaps(country.getName()));
            country.setApproveStatus(ApproveStatus.PENDING);
            countryRepository.save(country);
        } else {
            country = null;
        }
        interviewQuestionUser.setCountry(country);
        interviewQuestionUser.setDate(LocalDate.now());

        InterviewQuestionUser questionUser = interviewQuestionUserRepository.findByInterviewQuestionAndUserIdAndCompanyAndDate(interviewQuestion, interviewQuestionUser.getUserId(), interviewQuestionUser.getCompany(), LocalDate.now());
        if (questionUser != null) {
            throw new DuplicateResourceException(DUPLICATION_QUESTION);
        }
        interviewQuestionUserRepository.save(interviewQuestionUser);
    }

    @Override
    @Transactional
    public InterviewQuestionUser update(InterviewQuestionUser request) throws AccessDeniedException {
        InterviewQuestionUser questionUser = questionUserRepository.findById(request.getId()).orElse(null);

        assert questionUser != null;
        InterviewQuestion question = questionUser.getInterviewQuestion();

        if (ApproveStatus.APPROVED.equals(question.getApproveStatus())) {
            throw new AccessDeniedException(StConstant.FORBIDDEN_EXCEPTION);
        }
        InterviewQuestion interviewQuestionRequest = request.getInterviewQuestion();
        question.setQuestion(interviewQuestionRequest.getQuestion());
        Topic topic = commonService.getTopicForUpdate(interviewQuestionRequest.getTopic());
        question.setTopic(topic);
        question.setApproveStatus(ApproveStatus.PENDING);

        InterviewQuestionUser interviewQuestionUser = interviewQuestionUserRepository.findById(request.getId()).orElse(null);

        Company company = commonService.getCompanyForUpdate(request.getCompany());
        if (interviewQuestionUser == null) {
            interviewQuestionUser = new InterviewQuestionUser();
        }
        interviewQuestionUser.setCompany(company);
        interviewQuestionUser.setUserId(request.getUserId());
        interviewQuestionUser.setInterviewQuestion(question);

        repository.save(question);

        programmingService.saveOrUpdate(request.getInterviewQuestion().getProgrammingQuestion(), question);

        return interviewQuestionUserRepository.save(interviewQuestionUser);
    }

    @Override
    public InterviewQuestion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewQuestionUser> findAll(String approval, Integer userId) {
        return interviewQuestionUserRepository.findTop50ByUserIdOrderByIdDesc(userId);
    }

    @Override
    public List<InterviewQuestion> findByTopicId(Integer id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        return repository.findByTopic(topic);
    }

    @Override
    @Cacheable(value = "interview-questions", key = "#name")
    public List<InterviewQuestionResponse> findByTopicNameAndApproval(String name, Integer userId) {
        Topic topic = topicRepository.findByName(name);
        return copy(repository.findByTopicAndApproveStatusOrderByPointsDesc(topic, ApproveStatus.APPROVED), userId);
    }

    private List<InterviewQuestionResponse> copy(List<InterviewQuestion> questions, Integer userId) {
        return questions.stream().map(question -> {
            InterviewQuestionResponse res = new InterviewQuestionResponse();
            res.setId(question.getId());
            res.setQuestion(question.getQuestion());
            ProgrammingInterviewQuestion programming = question.getProgrammingQuestion();
            if(programming != null) {
                res.setProgram(programming.getProgram());
            }
            res.setAskCount(question.getAskCount());

            if(userId != null) {
                //res.setPostedBy(q.);
                Like like = likeRepository.findByInterviewQuestionAndUserId(question, userId);
                res.setUserLike(like != null ? like.getUserLike() : null);
            }

            Map<Boolean, Long> likes = question.getLikes().stream().filter(like -> like.getUserLike() != null).collect(
                    Collectors.groupingBy(Like::getUserLike, Collectors.counting()));
            res.setLikes(likes.get(Boolean.TRUE));
            res.setDislikes(likes.get(Boolean.FALSE));
            res.setTopicName(question.getTopic().getName());

            return res;
        }).toList();
    }

    @Override
    public void deleteById(Integer userQuestionId, Integer userId) {
        InterviewQuestionUser interviewQuestionUser = interviewQuestionUserRepository.findById(userQuestionId).orElse(null);
        assert interviewQuestionUser != null;
        InterviewQuestion question = repository.findById(interviewQuestionUser.getInterviewQuestion().getId()).orElse(null);
        assert question != null;
        if (ApproveStatus.APPROVED.equals(question.getApproveStatus())) {
            throw new InvalidStateException("You cannot delete an approved question.");
        }
        repository.deleteById(userQuestionId);
    }
}
