package kr.co._29cm.homework.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "kr.co._29cm.homework", importOptions = ImportOption.DoNotIncludeTests.class)
class LayeredDependencyRulesTest {

  @ArchTest
  static final ArchRule controller_should_not_depend_on_entity =
      noClasses().that()
          .resideInAPackage("kr.co._29cm.homework.domain.controller..")
          .should()
          .dependOnClassesThat()
          .resideInAPackage("kr.co._29cm.homework.domain.persistence.entity..");

  @ArchTest
  static final ArchRule common_should_not_access_domain =
      noClasses().that()
          .resideInAPackage("kr.co._29cm.homework.common..")
          .should()
          .accessClassesThat()
          .resideInAPackage("kr.co._29cm.homework.domain..");

  @ArchTest
  static final ArchRule common_should_not_access_presentation =
      noClasses().that()
          .resideInAPackage("kr.co._29cm.homework.common..")
          .should()
          .accessClassesThat()
          .resideInAPackage("kr.co._29cm.homework.presentation..");

  @ArchTest
  static final ArchRule domain_should_not_access_presentation =
      noClasses().that()
          .resideInAPackage("kr.co._29cm.homework.domain..")
          .should()
          .dependOnClassesThat()
          .resideInAPackage("kr.co._29cm.homework.domain.presentation..");
}
