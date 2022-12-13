package team.comit.simtong.domain

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckWebAdapter {

    @GetMapping
    fun healthCheck() = "OK"

}