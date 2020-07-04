package com.sfeir.spring.initializr.contributor;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@link ProjectContributor} that creates the "esdata" test resources
 * when Spring EsData Loader library  is requested
 *
 * @author tinesoft
 */
public class SpringEsDataLoaderProjectContributor implements ProjectContributor {
  @Override
  public void contribute(Path projectRoot) throws IOException {

    Path esdataDirectory = projectRoot.resolve("src/test/resources/esdata");
    Files.createDirectories(esdataDirectory);
  }
}
