import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterModule } from "@angular/router";

interface ForumQuestion {
  id: number;
  name: string;
  topic: string;
  company: string;
  question: string;
  createdAt: string;
}

@Component({
  selector: 'app-forum',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, RouterModule],
  templateUrl: './forum.component.html',
  styleUrl: './forum.component.scss'
})
export class ForumComponent {
  questions: ForumQuestion[] = [
    {
      id: 1,
      name: 'Yash',
      topic: 'Java',
      company: 'ABC Corp',
      question: 'What is Angular standalone component?',
      createdAt: '2 hours ago'
    },
    {
      id: 2,
      name: 'Rahul',
      topic: 'Angular',
      company: 'XYZ Inc',
      question: 'What is dependency injection?',
      createdAt: '5 hours ago'
    },
    {
      id: 3,
      name: 'Amit',
      topic: 'Java',
      company: 'DEF Ltd',
      question: 'Please explain with Angular example.',
      createdAt: '1 day ago'
    }
  ];

  form = {
    name: '',
    email: '',
    topic: '',
    company: '',
    question: ''
  };

  submitQuestion(): void {
    const name = this.form.name.trim();
    const email = this.form.email.trim();
    const topic = this.form.topic.trim();
    const company = this.form.company.trim();
    const question = this.form.question.trim();

    if (!name || !email || !topic || !company || !question) {
      alert('Please fill all fields');
      return;
    }

    const newQuestion: ForumQuestion = {
      id: Date.now(),
      name,
      topic,
      company,
      question,
      createdAt: 'Just now'
    };

    this.questions = [newQuestion, ...this.questions];

    this.form = {
      name: '',
      email: '',
      topic: '',
      company: '',
      question: ''
    };
  }
}