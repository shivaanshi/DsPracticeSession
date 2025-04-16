package com.demo.dspracticesession

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.demo.dspracticesession.ui.theme.DsPracticeSessionTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val _counter = MutableStateFlow(0)
    private val counter: StateFlow<Int> = _counter

    private val _toastMessage = MutableSharedFlow<String>()
    private val toastMessage: SharedFlow<String> = _toastMessage
    private var response = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DsPracticeSessionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = response.value,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        shareFlowVsStateFlow()
        incrementCounter()
        sendToastMessage("Counter updated")
        shareFlowVsStateFlow()
        lifecycleScope.launch {MergeSortedArray.asyncWay()}
        integrateAI()
    }

    private fun integrateAI() {
        val chatGPTService = ChatGPTService()
        chatGPTService.sendMessage("Hello ChatGPT!") { response ->
            runOnUiThread {
                if (response != null) {
                    this.response.value = response
                }
            }
        }
    }

    private fun incrementCounter() {
        _counter.value += 1
    }

    // Function to send a toast message
    private fun sendToastMessage(message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _toastMessage.emit(message) // Emit one-time event
            _counter.emit(10)
        }
    }

    private fun shareFlowVsStateFlow() {
        lifecycleScope.launch {
            counter.collect { count ->
                // Update the counter UI
                println("Flow Example: StateFlow: $count")
            }
        }

        // Collecting SharedFlow to handle one-time events
        lifecycleScope.launch {
            toastMessage.collect { message ->
                // Show a toast message
                println("Flow Example: SharedFlow: $message")
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DsPracticeSessionTheme {
        Greeting("Android")
    }
}