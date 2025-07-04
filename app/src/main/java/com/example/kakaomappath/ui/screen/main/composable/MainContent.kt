package com.example.kakaomappath.ui.screen.main.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kakaomappath.AppColors
import com.example.kakaomappath.api.map.remote.model.Location

@Composable
fun MainContent(modifier: Modifier = Modifier, locations: List<Location>) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        itemsIndexed(
            items = locations,
            key = { index, _ -> index }
        ) { _, location ->
            LocationItem(location = location)
        }
    }
}

@Composable
fun LocationItem(location: Location) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(width = 1.dp, color = AppColors.OrangeLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            LocationLabelItem(isOrigin = true, address = location.origin)
            VerticalDivider(
                modifier = Modifier
                    .height(20.dp)
                    .padding(start = 13.dp),
                thickness = 2.dp
            )
            LocationLabelItem(isOrigin = false, address = location.destination)
        }
    }
}

@Composable
fun LocationLabelItem(isOrigin: Boolean, address: String) {
    val (circleColor, label) = if (isOrigin) {
        AppColors.Black to "출발지"
    } else {
        AppColors.BlueMain to "도착지"
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.Start)
    ) {
        Canvas(modifier = Modifier.size(8.dp)) {
            drawCircle(
                color = circleColor,
                radius = size.minDimension / 2
            )
            if (isOrigin) {
                drawCircle(
                    color = AppColors.White,
                    radius = size.minDimension / 4
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${label} :",
                color = AppColors.Black,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = address,
                color = AppColors.Black,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    val sampleLocations = listOf(
        Location(origin = "서울시 강남구 역삼동", destination = "서울시 서초구 서초동"),
        Location(origin = "서울시 마포구 상수동", destination = "서울시 용산구 이태원동"),
        Location(origin = "서울시 종로구 관철동", destination = "서울시 중구 명동")
    )
    MainContent(locations = sampleLocations)
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationItem() {
    val sampleLocation = Location(origin = "서울시 강남구 역삼동", destination = "서울시 서초구 서초동")
    LocationItem(location = sampleLocation)
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationLabelItem() {
    LocationLabelItem(isOrigin = true, address = "서울시 강남구")
}
