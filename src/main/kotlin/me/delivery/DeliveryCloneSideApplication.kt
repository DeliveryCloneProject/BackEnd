package me.delivery

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
class DeliveryCloneSideApplication

fun main(args: Array<String>) {
    SpringApplication.run(DeliveryCloneSideApplication::class.java, *args)
}