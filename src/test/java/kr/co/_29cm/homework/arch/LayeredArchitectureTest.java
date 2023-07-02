package kr.co._29cm.homework.arch;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "kr.co._29cm.homework", importOptions = ImportOption.DoNotIncludeTests.class)
class LayeredArchitectureTest {

  @ArchTest
  static final ArchRule layer_dependencies_are_respected = layeredArchitecture().consideringAllDependencies()
      .layer("Presentation").definedBy("kr.co._29cm.homework.presentation..")
      .layer("Controller").definedBy("kr.co._29cm.homework.domain.controller..")
      .layer("Service").definedBy("kr.co._29cm.homework.domain.service..")
      .layer("Dao").definedBy("kr.co._29cm.homework.domain.persistence.dao..")
      .layer("Repository").definedBy("kr.co._29cm.homework.domain.persistence.repository..")
      .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
      .whereLayer("Controller").mayOnlyBeAccessedByLayers("Presentation")
      .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
      .whereLayer("Dao").mayOnlyBeAccessedByLayers("Service")
      .whereLayer("Repository").mayOnlyBeAccessedByLayers("Dao");

}
