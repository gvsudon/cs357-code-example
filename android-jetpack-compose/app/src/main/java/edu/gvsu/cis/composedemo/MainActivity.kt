package edu.gvsu.cis.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.gvsu.cis.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    //    val vm: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row {
                        CountingWithViewModel()
                        CountingWithLocalState()
                        CountingWithBrokenLocalState()

                    }
                }
            }
        }
    }
}

@Composable
fun CountingWithViewModel(vm: MainActivityViewModel = viewModel()) {
    val countState by vm.counter.observeAsState(100)
    var active by rememberSaveable {
        mutableStateOf<Boolean>(true)

    }
    Counting(count = countState, enableFlag = active) { multipleSeven ->
        if (!multipleSeven)
            vm.countUp()
        else
            active = false
    }
}

@Composable
fun CountingWithLocalState() {
    var countState by rememberSaveable {
        mutableStateOf<Int>(1)
    }
    Counting(count = countState) { multipleSeven ->
        if (multipleSeven)
            countState += 3
        else
            countState++
    }
}

@Composable
fun CountingWithBrokenLocalState() {
    var countState by remember {
        mutableStateOf<Int>(1)
    }
    Counting(count = countState) { multipleSeven ->
        if (multipleSeven)
            countState += 3
        else
            countState++
    }
}

@Composable
fun Counting(count: Int, enableFlag:Boolean = true, action: (Boolean) -> Unit) {
    Column() {
        Text(text = "Hello $count")
        Button(onClick = {
            action(count % 7 == 0)
        }, enabled = enableFlag) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Row {
            CountingWithLocalState()
            CountingWithViewModel()
        }
    }
}