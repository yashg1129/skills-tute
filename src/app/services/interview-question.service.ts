import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface InterviewQuestion {
  id: number;
  name: string;
  category: string;
  difficulty: string;
  question: string;
  answer: string;
}

@Injectable({
  providedIn: 'root'
})
export class InterviewQuestionService {
  private apiUrl = 'http://localhost:8080/api/topics';

  constructor(private http: HttpClient) {}

  getAllQuestions(): Observable<InterviewQuestion[]> {
    const q: Observable<InterviewQuestion[]> = this.http.get<InterviewQuestion[]>(this.apiUrl);
    console.log('Fetching interview questions from API:', this.apiUrl);
    console.log('Received Observable:', q);
    return q;
  }
}