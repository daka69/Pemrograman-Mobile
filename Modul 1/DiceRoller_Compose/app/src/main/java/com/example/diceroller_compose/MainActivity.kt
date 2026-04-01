package com.example.diceroller_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerApp()
        }
    }
}

fun getDiceImage(value: Int): Int = when (value) {
    1    -> R.drawable.dice_1
    2    -> R.drawable.dice_2
    3    -> R.drawable.dice_3
    4    -> R.drawable.dice_4
    5    -> R.drawable.dice_5
    6    -> R.drawable.dice_6
    else -> R.drawable.dice_0
}

@Composable
fun DiceRollerApp() {
    var diceResult1 by remember { mutableIntStateOf(0) }
    var diceResult2 by remember { mutableIntStateOf(0) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = getDiceImage(diceResult1)),
                    contentDescription = "Dadu 1",
                    modifier = Modifier.size(130.dp)
                )
                Image(
                    painter = painterResource(id = getDiceImage(diceResult2)),
                    contentDescription = "Dadu 2",
                    modifier = Modifier.size(130.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    diceResult1 = (1..6).random()
                    diceResult2 = (1..6).random()

                    val message = if (diceResult1 == diceResult2) {
                        "Selamat, anda dapat dadu double!"
                    } else {
                        "Anda belum beruntung!"
                    }

                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text(text = "Roll", fontSize = 16.sp)
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}