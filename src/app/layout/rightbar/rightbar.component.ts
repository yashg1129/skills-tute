import { Component } from '@angular/core';

@Component({
  selector: 'app-rightbar',
  standalone: true,
  templateUrl: './rightbar.component.html',
  styleUrl: './rightbar.component.scss'
})
export class RightbarComponent {
  quickLinks = [
    'Popular Tutorials',
    'Interview Questions',
    'Spring Boot Notes',
    'Java Programs',
    'Microservices Guide'
  ];

  recentPosts = [
    'Java 8 Features',
    'Spring Boot REST API',
    'Difference between JDK and JRE',
    'Top Java Interview Questions'
  ];
}