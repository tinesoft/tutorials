package com.sfeir.spring.initializr.customizer;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.version.Version;
import io.spring.initializr.generator.version.VersionParser;
import io.spring.initializr.generator.version.VersionRange;

/**
 * {@link BuildCustomizer} that automatically add Spring EsData Loader when Spring Data Elasticsearch is selected
 *
 * @author tinesoft
 */
public class SpringDataElasticsearchTestBuildCustomizer implements BuildCustomizer<Build> {

  private static final VersionRange SPRING_BOOT_2_2_OR_LATER = VersionParser.DEFAULT.parseRange("2.2.0.M1");

  private ProjectDescription description;

  public SpringDataElasticsearchTestBuildCustomizer(ProjectDescription description){
    this.description = description;
  }

  @Override
  public void customize(Build build) {
    Version platformVersion = this.description.getPlatformVersion();
    if(SPRING_BOOT_2_2_OR_LATER.match(platformVersion))
      build.dependencies().add("spring-esdata-loader-junit-jupiter");
    else
      build.dependencies().add("spring-esdata-loader-junit4");
  }
}

