package com.flytbase.ui.calculator_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import com.flytbase.ui.theme.LocalSpacing
import com.flytbase.ui.theme.Onyx


@Composable
fun ResultBoard(modifier: Modifier = Modifier, expression: String, result: String) {
    val spacing = LocalSpacing.current
    var width = 0
    val state = rememberLazyListState()
    LaunchedEffect(key1 = expression) {
        state.animateScrollToItem(0, width)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        LazyRow(state = state) {
            item {
                Text(
                    text = expression,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(
                            horizontal = spacing.small,
                            vertical = spacing.extraSmall
                        )
                        .onSizeChanged {
                            width = it.width
                        }
                )
            }
        }
        Text(
            text = result,
            color = Color.Onyx,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(horizontal = spacing.small, vertical = spacing.extraSmall)
        )
    }
}