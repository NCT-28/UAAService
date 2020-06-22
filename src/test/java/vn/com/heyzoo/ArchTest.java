package vn.com.heyzoo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("vn.com.heyzoo");

        noClasses()
            .that()
                .resideInAnyPackage("vn.com.heyzoo.service..")
            .or()
                .resideInAnyPackage("vn.com.heyzoo.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..vn.com.heyzoo.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
