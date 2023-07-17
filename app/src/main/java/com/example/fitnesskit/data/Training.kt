package com.example.fitnesskit.data


import com.example.fitnesskit.data.Lesson
import com.example.fitnesskit.data.Trainer
import com.google.gson.annotations.SerializedName

data class Training(
    @SerializedName("lessons")
    val lessons: List<Lesson>,
    @SerializedName("option")
    val option: Option,
    @SerializedName("tabs")
    val tabs: List<Tab>,
    @SerializedName("trainers")
    val trainers: List<Trainer>
)