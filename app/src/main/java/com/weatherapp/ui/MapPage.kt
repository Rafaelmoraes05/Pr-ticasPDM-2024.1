package com.weatherapp.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.weatherapp.R
import com.weatherapp.mainviewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context
) {
    val recife = LatLng(-8.05, -34.9)
    val caruaru = LatLng(-8.27, -35.98)
    val joaopessoa = LatLng(-7.12, -34.84)

    // Estado da posição da câmera
    val camPosState = rememberCameraPositionState ()

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = camPosState,
        onMapClick = { latLng ->
            viewModel.add("Nova cidade", location = latLng)
        }
    ) {
        // Adiciona os marcadores existentes
        viewModel.cities.forEach {
            if (it.location != null) {
                Marker( state = MarkerState(position = it.location!!),
                    title = it.name, snippet = "${it.location}")
            }
        }

        // Adiciona os marcadores estáticos
        Marker(
            state = MarkerState(position = recife),
            title = "Recife",
            snippet = "Marcador em Recife",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        )
        Marker(
            state = MarkerState(position = caruaru),
            title = "Caruaru",
            snippet = "Marcador em Caruaru",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
        )
        Marker(
            state = MarkerState(position = joaopessoa),
            title = "João Pessoa",
            snippet = "Marcador em João Pessoa",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        )
    }
}


@Preview
@Composable
fun PreviewMapPage() {
    // Mock ViewModel and Context for Preview
    val mockViewModel = MainViewModel()
    val mockContext = LocalContext.current

    MapPage(
        viewModel = mockViewModel,
        context = mockContext
    )
}