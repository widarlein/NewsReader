package com.example.newsreader.data.model.exceptions

class UnsuccessfulRequestException(message: String, val requestSubject: String) : RuntimeException(message)