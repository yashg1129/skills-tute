import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  InterviewQuestion,
  InterviewQuestionService
} from '../../services/interview-question.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-interview-questions',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './interview-questions.component.html',
  styleUrl: './interview-questions.component.scss'
})
export class InterviewQuestionsComponent implements OnInit {
  questions: InterviewQuestion[] = [];
  loading = false;
  errorMessage = '';
  topicId = '';

  constructor(private service: InterviewQuestionService, private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.topicId = this.route.snapshot.paramMap.get('topicId') || '';
    console.log('Topic ID:', this.topicId);
    this.loadQuestions();
  }

  loadQuestions(): void {
    this.loading = true;
    this.errorMessage = '';

    this.service.getAllQuestions().subscribe({
      next: (data: InterviewQuestion[]) => {
        this.questions = data;
        this.loading = false;
      },
      error: (err: unknown) => {
        console.error('Error fetching questions', err);
        this.errorMessage = 'Failed to load interview questions.';
        this.loading = false;
      }
    });
  }
}