package com.skills.tute.controller;

import com.skills.tute.dto.CodeRequest;
import com.skills.tute.service.JavaExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compiler")
public class CompilerController {

    @Autowired
    private JavaExecutionService javaExecutionService;

    @PostMapping("/java")
    public ResponseEntity<String> runJava(@RequestBody CodeRequest request) {
        try {
            String output = javaExecutionService.compileAndRun(request.getCode(), request.getInput());
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Server Error: " + e.getMessage());
        }
    }
}