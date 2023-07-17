package com.example.fitnesskit.data

import com.example.fitnesskit.data.Lesson

data class LessonEntity(
    val type: TrainingType,
    val lesson: Lesson?,
    val header: String?
)

enum class TrainingType {
    TRAIN, // тренировка в списке
    HEADER // заголовок с датой
}