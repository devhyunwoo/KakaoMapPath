package com.example.kakaomappath.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaomappath.AppColors
import com.example.kakaomappath.R

@Composable
fun CommonTopBar(
    title: String,
    isShowBack: Boolean,
    onBackClick: (() -> Unit) = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = AppColors.Black)
            .padding(horizontal = 20.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (isShowBack) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() },
                painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = "뒤로가기 버튼",
                tint = Color.Unspecified
            )
        }
        Text(
            text = title,
            fontWeight = FontWeight.W700,
            color = AppColors.White,
            fontSize = 25.sp,
        )
    }
}