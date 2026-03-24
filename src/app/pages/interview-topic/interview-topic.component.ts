import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  InterviewTopic,
  InterviewTopicService
} from '../../services/interview-topic.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-interview-topic',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './interview-topic.component.html',
  styleUrl: './interview-topic.component.scss'
})
export class InterviewTopicComponent implements OnInit {
  topics: InterviewTopic[] = [];
  loading = false;
  errorMessage = '';

  constructor(
    private topicService: InterviewTopicService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadTopics();
  }

  loadTopics(): void {
    console.log('Loading interview topics...');
    this.loading = true;
    this.errorMessage = '';

    this.topicService.getAllTopics().subscribe({
      next: (data: InterviewTopic[]) => {
        this.topics = data;
        this.loading = false;
        console.log('Topics loaded successfully:', this.topics);
        this.cdr.detectChanges();
      },
      error: (err: unknown) => {
        console.error('Error fetching topics', err);
        this.errorMessage = 'Failed to load topics.';
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }
}