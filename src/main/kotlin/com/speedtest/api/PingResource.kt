package com.speedtest.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
class PingResource {

    @GetMapping(path = ["/"])
    fun getHello(): String = "pong"
}