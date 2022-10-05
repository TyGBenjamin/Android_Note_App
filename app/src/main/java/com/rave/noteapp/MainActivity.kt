package com.rave.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.rave.noteapp.utils.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        lifecycleScope.launch {
//            viewModel.noteList.collectLatest { noteList ->
//                Log.d(TAG, "Note List: $noteList")
//            }
//        }
        collectLatestLifecycleFlow(viewModel.noteList) { noteList ->
            Log.d(TAG, "Note List: $noteList")
        }
        lifecycleScope.launch {

            delay(1000L)
            viewModel.insertNote()
        }
    }
    companion object {
        const val TAG = "Logger"
    }
}