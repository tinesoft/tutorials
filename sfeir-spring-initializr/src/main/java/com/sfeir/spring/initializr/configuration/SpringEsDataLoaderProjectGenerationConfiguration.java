package com.sfeir.spring.initializr.configuration;

import com.sfeir.spring.initializr.contributor.SpringEsDataLoaderProjectContributor;
import com.sfeir.spring.initializr.customizer.SpringDataElasticsearchTestBuildCustomizer;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuration for generation projects that depends on Spring EsData Loader.
 *
 * @author tinesoft
 */
@ProjectGenerationConfiguration
public class SpringEsDataLoaderProjectGenerationConfiguration {

  private final ProjectDescription description;

  public SpringEsDataLoaderProjectGenerationConfiguration(ProjectDescription description){
    this.description = description;
  }

  @Bean
  @ConditionalOnRequestedDependency("data-elasticsearch")
  public SpringDataElasticsearchTestBuildCustomizer springEsDataLoaderTestBuildCustomizer() {
    return new SpringDataElasticsearchTestBuildCustomizer(this.description);
  }

  @Bean
  @ConditionalOnRequestedDependency("spring-esdata-loader-junit-jupiter")
  public SpringEsDataLoaderProjectContributor springEsDataLoaderJupiterProjectContributor() {
    return new SpringEsDataLoaderProjectContributor();
  }

  @Bean
  @ConditionalOnRequestedDependency("spring-esdata-loader-junit4")
  public SpringEsDataLoaderProjectContributor springEsDataLoaderJUnit4ProjectContributor() {
    return new SpringEsDataLoaderProjectContributor();
  }
}
