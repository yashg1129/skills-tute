package com.skills.tute.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Service
public class JavaExecutionServiceImpl implements  JavaExecutionService {

    public String compileAndRun(String javaCode, String input) throws Exception {
        String className = "Main";

        Path tempDir = Files.createTempDirectory("java-runner");
        Path javaFile = tempDir.resolve(className + ".java");

        Files.writeString(javaFile, javaCode, StandardCharsets.UTF_8);

        Process compileProcess = new ProcessBuilder("javac", javaFile.toString())
                .redirectErrorStream(true)
                .start();

        boolean compileFinished = compileProcess.waitFor(10, TimeUnit.SECONDS);
        if (!compileFinished) {
            compileProcess.destroyForcibly();
            return "Compilation timed out";
        }

        String compileOutput = new String(
                compileProcess.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        if (compileProcess.exitValue() != 0) {
            return "Compilation Error:\n" + compileOutput;
        }

        Process runProcess = new ProcessBuilder("java", "-cp", tempDir.toString(), className)
                .redirectErrorStream(true)
                .start();

        // send input if provided
        if (input != null && !input.isBlank()) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(runProcess.getOutputStream(), StandardCharsets.UTF_8))) {
                writer.write(input);
                writer.newLine();
                writer.flush();
            }
        } else {
            runProcess.getOutputStream().close();
        }

        boolean runFinished = runProcess.waitFor(10, TimeUnit.SECONDS);
        if (!runFinished) {
            runProcess.destroyForcibly();
            return "Execution timed out";
        }

        String runOutput = new String(
                runProcess.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        if (runProcess.exitValue() != 0) {
            return "Runtime Error:\n" + runOutput;
        }

        return runOutput;
    }
}