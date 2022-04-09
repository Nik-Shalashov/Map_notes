package ru.android.mapnotes.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import ru.android.mapnotes.R
import ru.android.mapnotes.presentation.viewmodels.MapViewModel

@ExperimentalPermissionsApi

@Composable
fun MapScreen() {
    GetPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        stringResource(R.string.sLocatePermission)
    ) {
        MapMainScreen()
    }
}

@Composable
fun MapMainScreen() {

    val viewModel: MapViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    GetLocation()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
    ) {

        val tags = viewModel.tags.collectAsState(initial = emptyList()).value

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.insertSpot(it)
            }
        ) {
            tags.forEach { tag ->
                Marker(
                    state = MarkerState(LatLng(tag.lat, tag.lng)),
                    title = stringResource(R.string.sTagInfo, tag.lat, tag.lng),
                    snippet = stringResource(R.string.sLongTap),
                    onInfoWindowLongClick = {
                        viewModel.deleteTag(tag = tag)
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_CYAN
                    )
                )
            }
        }
    }
}

@Composable
private fun GetLocation() {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    Log.e("my", "lat: ${it.latitude}")
                    Log.e("my", "long: ${it.longitude}")
                }
            }
        }
    }
}