import { Routes } from '@angular/router';
import { JavaTutorialComponent } from './pages/java-tutorial/java-tutorial.component';
import { InterviewQuestionsComponent } from './pages/interview-questions/interview-questions.component';

export const routes: Routes = [
  { path: '', redirectTo: 'java-tutorial', pathMatch: 'full' },
  { path: 'interview-questions', component: InterviewQuestionsComponent },
  { path: 'java-tutorial', component: JavaTutorialComponent }
];