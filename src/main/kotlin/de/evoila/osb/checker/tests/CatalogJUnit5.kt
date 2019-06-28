package de.evoila.osb.checker.tests

import org.junit.jupiter.api.Test
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration
class CatalogJUnit5 : TestBase() {

  @Test
  fun validateCatalog() {
    catalogRequestRunner.correctRequestAndValidateResponse()
  }
}