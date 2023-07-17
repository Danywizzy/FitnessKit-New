package com.example.fitnesskit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fitnesskit.adapter.TrainingAdapter
import com.example.fitnesskit.api.ApiResult
import com.example.fitnesskit.data.Lesson
import com.example.fitnesskit.data.LessonEntity
import com.example.fitnesskit.data.Training
import com.example.fitnesskit.data.TrainingType
import com.example.fitnesskit.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private val viewModel by viewModels<ScheduleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val trainingAdapter = TrainingAdapter { viewModel.getTrainerById(it) }
        mBinding.trainingAdapter.adapter = trainingAdapter
        viewModel.liveData.observe(requireActivity()) {
            if (it is ApiResult.Success && it.data != null) {
                trainingAdapter.setData(sortLessons(it.data))
            } else {
                Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sortLessons(body: Training): ArrayList<LessonEntity> {
        val list = body.lessons.sortedByDescending {
            it.formatedDate
        }
        val map = emptyMap<String, ArrayList<Lesson>>().toMutableMap()
        list.forEach {
            if (map[it.dateWithDay] == null)
                map[it.dateWithDay] = ArrayList()
            map[it.dateWithDay]?.add(it)
        }

        val newList = ArrayList<LessonEntity>()
        map.forEach { entry ->
            newList.add(LessonEntity(type = TrainingType.HEADER, null, entry.key))
            entry.value.mapTo(newList) {
                LessonEntity(type = TrainingType.TRAIN, it, null)
            }
        }
        return newList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}