package team.comit.simtong

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimtongApplication

fun main(args: Array<String>) {
    println("hello world")
    runApplication<SimtongApplication>(*args)
}
