package com.skills.tute;

import com.skills.tute.config.DdlAutoValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillsTutorialApplication {

  public static void main(String[] args) {
    //SpringApplication.run(SkillsTutorialApplication.class, args);
    SpringApplication app =
            new SpringApplication(SkillsTutorialApplication.class);

    app.addInitializers(new DdlAutoValidator());

    app.run(args);
  }

}
