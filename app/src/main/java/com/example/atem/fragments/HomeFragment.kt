package com.example.atem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.atem.R
import com.example.atem.databinding.FragmentHomeBinding
import com.example.atem.pojo.Meal
import com.example.atem.pojo.mealList
import com.example.atem.retrofit.retrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitObject.apiInterface.getRandomMealInterface().enqueue(object : Callback<mealList>{
            override fun onResponse(call: Call<mealList>, response: Response<mealList>) {
                if(response.body() != null){
                    val randomMeal : Meal = response.body()!!.meals[0]
                    Log.d("Rao","Meal Id : ${randomMeal.idMeal} name : ${randomMeal.strMeal}")
                    Glide.with(this@HomeFragment)
                        .load(randomMeal.strMealThumb)
                        .into(binding.ivRandomMeal)

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<mealList>, t: Throwable) {
                Log.d("Rao","Failure of Api ${t.message.toString()}")
            }

        })
    }

}