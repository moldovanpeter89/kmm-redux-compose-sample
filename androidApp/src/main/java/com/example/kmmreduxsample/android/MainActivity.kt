package com.example.kmmreduxsample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kmmreduxsample.android.common.viewmodel.StoreViewModel
import com.example.kmmreduxsample.common.app.AppState
import com.example.kmmreduxsample.common.login.LoginAction
import com.example.kmmreduxsample.common.login.SignedInSideEffect
import com.example.kmmreduxsample.common.login.SignedOutSideEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.getViewModel
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable(route = "login") {
                        LoginPage(navController = navController)
                    }
                    composable(route = "home") {
                        HomePage(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun LoginPage(
    viewModel: StoreViewModel = getViewModel(),
    navController: NavController
) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val state by stateFlow.collectAsState(Dispatchers.Main)
    val loginState = state.getLoginState()
    Scaffold { contentPadding ->
        // Screen content
        val sideEffects = viewModel.store.observeSideEffect()
        LaunchedEffect(key1 = LocalLifecycleOwner.current) {
            sideEffects.collect {
                if (it is SignedInSideEffect) {
                    navController.navigate("home")
                }
            }
        }

        Box(modifier = Modifier.padding(contentPadding)) {
            val usernameState = remember { mutableStateOf(TextFieldValue("")) }
            val passwordState = remember { mutableStateOf(TextFieldValue("")) }
            val showProgressState = remember { mutableStateOf(false) }
            val showVerifyButtonState = remember { mutableStateOf(false) }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                ) {
                                    append("enabl")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic,
                                        color = Color.Blue
                                    )
                                ) {
                                    append("on")
                                }
                            },
                            fontSize = 50.sp
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            TextInputField("Email", usernameState)
                            TextInputField("Password", passwordState)
                        }
                    }
                }

                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (showProgressState.value)
                            CircularProgressIndicator(
                                modifier = Modifier.padding(16.dp),
                            )
                        else
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Blue,
                                    contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                                ),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(32.dp),
                                enabled = !showVerifyButtonState.value,
                                onClick = {
                                    viewModel.dispatch(
                                        LoginAction.SignIn(
                                            usernameState.value.text,
                                            passwordState.value.text
                                        )
                                    )
                                }
                            ) {
                                Text(text = "SIGN IN", fontSize = 18.sp)
                            }
                    }
                }

                if (loginState.error != null) {
                    item {
                        Text(
                            loginState.error.toString(),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

}


@Composable
@OptIn(ExperimentalTime::class)
fun HomePage(
    viewModel: StoreViewModel = getViewModel(),
    navController: NavController
) {
    val stateFlow: StateFlow<AppState> = viewModel.store.observeState()
    val state by stateFlow.collectAsState(Dispatchers.Main)
    val loginState = state.getLoginState()
    Scaffold { contentPadding ->
        // Screen content
        val sideEffects = viewModel.store.observeSideEffect()
        LaunchedEffect(key1 = LocalLifecycleOwner.current) {
            sideEffects.collect {
                if (it is SignedOutSideEffect) {
                    navController.navigate("login")
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(text = "Home page ")
            }
            item {
                DefaultButton(
                    backgroundColor = Color.Blue,
                    icon = Icons.Filled.Done,
                    text = "Logout",
                    onClick = {
                        viewModel.dispatch(LoginAction.SignOut)
                    }
                )
            }
        }

    }
}

@Composable
fun TextInputField(
    label: String,
    state: MutableState<TextFieldValue>,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        value = state.value,
        singleLine = true,
        visualTransformation = visualTransformation,
        onValueChange = { state.value = it },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        label = { Text(label) },
        enabled = enabled
    )
}

@Composable
fun DefaultButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.primary
) {
    Button(
        modifier = Modifier
            .width(220.dp)
            .padding(8.dp),
        onClick = onClick,
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.White
        )
    ) {
        // Inner content including an icon and a text label
        Icon(
            icon,
            contentDescription = text,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text)
    }
}