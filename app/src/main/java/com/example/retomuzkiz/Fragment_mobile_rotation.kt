package com.example.retomuzkiz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.retomuzkiz.databinding.FragmentMobileRotationBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_mobile_rotation.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_mobile_rotation : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var param1: Context


    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
        var binding = FragmentMobileRotationBinding.inflate(layoutInflater)
       binding.mobileRotation.startAnimation(AnimationUtils.loadAnimation(param1, R.anim.mobile_rotation))
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_rotation, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_mobile_rotation.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(context: Context) =

            Fragment_mobile_rotation().apply {
                arguments = Bundle().apply {
                   param1 = context

                }
            }
    }
}