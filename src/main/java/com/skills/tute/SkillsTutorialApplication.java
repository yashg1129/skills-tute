package com.skills.tute;

import com.skills.tute.config.DdlAutoValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
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
