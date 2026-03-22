import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  topics = [
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
  ];
}