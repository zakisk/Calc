package com.flytbase.ui.login_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.flytbase.R
import com.flytbase.ui.CalculatorViewModel
import com.flytbase.ui.login_screen.components.FlytTextField
import com.flytbase.ui.theme.LightOrange
import com.flytbase.ui.theme.LocalSpacing
import com.flytbase.ui.theme.Onyx
import com.flytbase.util.Screen


@Composable
fun LoginScreen(navController: NavHostController, viewModel: CalculatorViewModel) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val hasFocus = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    BackHandler(hasFocus.value) { focusManager.clearFocus() }

    LaunchedEffect(key1 = viewModel.authenticationResult.value) {
        snapshotFlow { viewModel.authenticationResult }.collect {
            if (it.value) {
                navController.navigate(Screen.CalculatorScreen.route) {
                    navController.popBackStack()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .padding(spacing.small)
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .border(
                    width = .3.dp,
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            FlytTextField(
                modifier = Modifier
                    .padding(horizontal = spacing.large, vertical = spacing.medium)
                    .fillMaxWidth(),
                backgroundColor = Color.LightOrange.copy(alpha = .3f),
                textStyle = MaterialTheme.typography.bodyMedium,
                text = { viewModel.mobileNumber },
                onValueChange = { viewModel.mobileNumber = it },
                placeholderText = stringResource(id = R.string.mobile_number),
                hasFocus = hasFocus,
                shape = MaterialTheme.shapes.small,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            FlytTextField(
                modifier = Modifier
                    .padding(horizontal = spacing.large, vertical = spacing.medium)
                    .fillMaxWidth(),
                backgroundColor = Color.LightOrange.copy(alpha = .3f),
                textStyle = MaterialTheme.typography.bodyMedium,
                text = { viewModel.password },
                onValueChange = { viewModel.password = it },
                placeholderText = stringResource(id = R.string.password),
                hasFocus = hasFocus,
                shape = MaterialTheme.shapes.small,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = {
                    TransformedText(
                        AnnotatedString("*".repeat(viewModel.password.length)),
                        OffsetMapping.Identity
                    )
                }
            )

            Button(
                modifier = Modifier
                    .padding(spacing.small)
                    .fillMaxWidth(.6f),
                onClick = {
                    if (viewModel.validate(context)) {
                        viewModel.authenticate(context)
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Onyx
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
