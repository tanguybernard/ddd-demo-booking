package com.example.training

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container

abstract class AbstractTestcontainersIntegrationTest {

    companion object {

        @JvmStatic
        @Container
        private val redisContainer: GenericContainer<Nothing> = GenericContainer<Nothing>("redis:latest")
            .withExposedPorts(6379)

        @JvmStatic
        @DynamicPropertySource
        fun redisProperties(registry: DynamicPropertyRegistry) {
            redisContainer.start()
            registry.add("spring.data.redis.host", redisContainer::getContainerIpAddress)
            registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort)
        }

    }





    /*@Container
    var postgreSQLContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:12")
        .withPassword("inmemory")
        .withUsername("inmemory")

    @DynamicPropertySource
    fun postgresqlProperties(registry: DynamicPropertyRegistry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl)
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword)
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername)
    }*/
}