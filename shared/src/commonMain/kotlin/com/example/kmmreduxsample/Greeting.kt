package com.example.kmmreduxsample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}