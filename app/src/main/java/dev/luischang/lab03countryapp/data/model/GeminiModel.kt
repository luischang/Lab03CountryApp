package dev.luischang.lab03countryapp.data.model
//Gemini request
data class GeminiRequest (
    val contents: List<Content>
)

data class Content(
    val parts: List<Part>
)

data class Part (
    val text: String
)

//Gemini response
data class GeminiResponse (
    val candidates: List<Candidate>
)

data class Candidate (
    val content: Content
)
