package com.example.kakaomappath.ui.screen.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kakaomappath.AppColors
import com.example.kakaomappath.R
import com.example.kakaomappath.api.map.remote.model.Location

@Composable
fun MainContent(modifier: Modifier = Modifier, locations: List<Location>) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
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
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LocationLabelItem(isOrigin = true, label = location.origin)
            LocationLabelItem(isOrigin = false, label = location.destination)
        }
    }
}

@Composable
fun LocationLabelItem(isOrigin: Boolean, label: String) {
    val (icon, description) = if (isOrigin) {
        R.drawable.ic_launcher_background to "출발지"
    } else {
        R.drawable.ic_launcher_foreground to "목적지"
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

@Preview
@Composable
fun PreviewLocationLabelItem() {
    LocationLabelItem(isOrigin = true, label = "서울시 강남구")
}
