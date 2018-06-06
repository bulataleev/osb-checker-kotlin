package de.evoila.osb.checker.config

import de.evoila.osb.checker.response.Catalog
import de.evoila.osb.checker.response.Plan
import de.evoila.osb.checker.response.Service
import java.util.*
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import kotlin.collections.HashMap

@Component
@ConfigurationProperties(prefix = "config")
class Configuration {

  lateinit var url: String

  var port: Int = 80
  lateinit var apiVersion: String
  lateinit var user: String
  lateinit var password: String
  lateinit var token: String
  var usingAppGuid: Boolean = true
  val parameters: HashMap<String, HashMap<String, Any>> = hashMapOf()
  final var services = mutableListOf<CustomServices>()


  fun initCustomCatalog(): Catalog? {

    return if (services.isNotEmpty()) {

      Catalog(
          services.map { customService ->
            Service(
                id = customService.id,
                name = "Service-Name",
                dashboardClient = null,
                bindable = customService.bindable,
                tags = null,
                metadata = null,
                planUpdatable = null,
                description = "Service-Description",
                requires = null,
                plans = customService.plans.map { customPlan ->
                  Plan(
                      id = customPlan.id,
                      name = "Plan-Name",
                      bindable = customPlan.bindable,
                      description = "Plan-Description",
                      metadata = null,
                      plan_updatable = null
                  )
                }
            )
          }
      )
    } else {

      null
    }
  }

  class CustomServices {
    lateinit var id: String
    var plans = mutableListOf<CustomPlan>()
    var bindable = true

    class CustomPlan {
      lateinit var id: String
      var bindable: Boolean? = null
    }
  }

  companion object {
    val NOT_AN_ID = UUID.randomUUID().toString()
  }
}