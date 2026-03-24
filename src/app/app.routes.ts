import { Routes } from '@angular/router';
import { JavaTutorialComponent } from './pages/java-tutorial/java-tutorial.component';
import { SpringBootTutorialComponent } from './pages/spring-boot-tutorial/spring-boot-tutorial.component';
import { InterviewQuestionsComponent } from './pages/interview-questions/interview-questions.component';
import { InterviewTopicComponent } from './pages/interview-topic/interview-topic.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'java-tutorial',
    pathMatch: 'full'
  },
  {
    path: 'java-tutorial',
    component: JavaTutorialComponent,
    data: {
      sidebarTitle: 'Java Tutorial',
      sidebarTopics: [
        'Java Tutorial',
        'History of Java',
        'Features of Java',
        'Difference between C++ and Java',
        'Java Hello World Program',
        'Program Internal',
        'How to set path in Java',
        'Difference between JDK, JRE and JVM',
        'JVM: Java Virtual Machine',
        'Java Variables',
        'Data Types in Java',
        'Unicode System in Java',
        'Java Operators',
        'Java Keywords'
      ]
    }
  },
  {
    path: 'spring-boot-tutorial',
    component: SpringBootTutorialComponent,
    data: {
      sidebarTitle: 'Spring Boot Tutorial',
      sidebarTopics: [
        'Spring Boot Tutorial',
        'Spring Boot Introduction',
        'Spring Boot Features',
        'Spring Boot Architecture',
        'Spring Boot Application',
        'Spring Boot Annotations',
        'Spring Boot REST API',
        'Spring Boot Dependency Injection',
        'Spring Boot Starter Project',
        'Spring Boot Properties',
        'Spring Boot with MySQL',
        'Spring Boot JPA Example',
        'Spring Boot Microservices',
        'Spring Boot Interview Questions'
      ]
    }
  },
  {
    path: 'interview-questions',
    component: InterviewQuestionsComponent,
    data: {
      sidebarTitle: 'All Interview',
      sidebarTopics: [
        'Top 50 HR Interview Questions and Answers (2025)',
        'Interview Tips (2025)',
        'Top 12 Competency based Interview Questions (2025)',
        'Top 30+ Business Analyst Interview Questions and Answers (2025)',
        'Behavioral Interview Questions (2025)',
        'Interview Questions and Answers (2025)',
        'Managerial Round Interview Questions',
        'Product Manager Interview Questions',
        'Project Manager Interview Questions'
      ]
    }
  },
  {
    path: 'interview-topics',
    component: InterviewTopicComponent
  }
];