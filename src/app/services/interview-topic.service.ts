import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface InterviewTopic {
  id: number;
  name: string;
  displayOrder: number;
}

@Injectable({
  providedIn: 'root'
})
export class InterviewTopicService {
  private apiUrl = 'http://localhost:8080/api/topics';

  constructor(private http: HttpClient) {}

  getAllTopics(): Observable<InterviewTopic[]> {
    const t = this.http.get<InterviewTopic[]>(this.apiUrl);
    console.log('Fetching interview topics from API:', this.apiUrl);
    console.log('Received Observable:', t);
    return t;
  }

  getTopicById(id: number): Observable<InterviewTopic> {
    return this.http.get<InterviewTopic>(`${this.apiUrl}/${id}`);
  }
}