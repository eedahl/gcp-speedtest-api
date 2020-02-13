package com.speedtest.api

import com.google.cloud.Timestamp
import com.google.gson.Gson
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/speedtest")
class SpeedTestResource(val pubSub: PubSubTemplate) {

    val gson: Gson = Gson()

    data class TestResult(val user: String,
                          val device: Int,
                          val timestamp: Long,
                          val data: Data)
    data class Data(val speeds: Speeds,
                    val client: Client,
                    val server: Server)
    data class Speeds(val upload: Double,
                      val download: Double)
    data class Client(val ip: String,
                      val lat: Double,
                      val lon: Double,
                      val isp: String,
                      val country: String)
    data class Server(val host: String,
                      val lat: Double,
                      val lon: Double,
                      val country: String,
                      val distance: Int,
                      val ping: Int,
                      val id: String)

    @PostMapping
    fun publishTestResult(@RequestBody testResult: TestResult) {
        this.pubSub.publish("speedtest",gson.toJson(testResult))
    }
}