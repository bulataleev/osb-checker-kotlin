package de.evoila.osb.checker.request.bodies

import com.fasterxml.jackson.annotation.JsonInclude
import de.evoila.osb.checker.response.Plan
import de.evoila.osb.checker.response.Service
import java.util.*
import javax.security.auth.login.Configuration
import kotlin.collections.HashMap

abstract class ProvisionBody : RequestBody {

    data class ValidProvisioning(
            var service_id: String?,
            var plan_id: String?,
            var organization_guid: String? = UUID.randomUUID().toString(),
            var space_guid: String? = UUID.randomUUID().toString(),

            @JsonInclude(JsonInclude.Include.NON_NULL)
            var parameters: Map<String, Any>? = null //!!! google how to parse app.yml
    ) : ProvisionBody() {

        constructor(service: Service, plan: Plan) : this(
                service_id = service.id,
                plan_id = plan.id

        )
    }

    data class ValidProvisioningWithParams(
            var service_id: String?,
            var plan_id: String?,
            var organization_guid: String? = UUID.randomUUID().toString(),
            var space_guid: String? = UUID.randomUUID().toString(),
            val parameters: HashMap<String, Any?> = hashMapOf("services" to
                    arrayOf(mapOf("applicationName" to "online-delivery",
                            "serviceName" to "order-service",
                            "serviceUrl" to "spring-app:8080",
                            "metricsPath" to "/actuator/prometheus",
                            "solutionName" to "myproject"
                    )))
    ) : ProvisionBody() {
        constructor(service: Service, plan: Plan) : this(
                service_id = service.id,
                plan_id = plan.id
        )
    }

    data class ServiceParams(
            var applicationName: String?,
            var serviceName: String?,
            var serviceUrl: String?,
            var metricsPath: String?,
            var solutionName: String?
    )


    data class NoServiceFieldProvisioning(
            var service_id: String?,
            var organization_guid: String? = UUID.randomUUID().toString(),
            var space_guid: String? = UUID.randomUUID().toString()
    ) : ProvisionBody() {

        constructor(service: Service) : this(
                service_id = service.id
        )
    }

    data class NoPlanFieldProvisioning(
            var plan_id: String?,
            var organization_guid: String? = UUID.randomUUID().toString(),
            var space_guid: String? = UUID.randomUUID().toString()
    ) : ProvisionBody() {

        constructor(plan: Plan) : this(
                plan_id = plan.id
        )
    }

    data class NoOrgFieldProvisioning(
            var service_id: String?,
            var plan_id: String?,
            var space_guid: String? = UUID.randomUUID().toString()
    ) : ProvisionBody() {

        constructor(service: Service, plan: Plan) : this(
                service_id = service.id,
                plan_id = plan.id
        )
    }

    data class NoSpaceFieldProvisioning(
            var service_id: String?,
            var plan_id: String?,
            var organization_guid: String? = UUID.randomUUID().toString()
    ) : ProvisionBody() {

        constructor(service: Service, plan: Plan) : this(
                service_id = service.id,
                plan_id = plan.id
        )
    }

}