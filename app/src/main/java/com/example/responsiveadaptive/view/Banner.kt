package com.example.responsiveadaptive.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.responsiveadaptive.R

@Composable
fun AppBanner(
    widthSizeClass: WindowWidthSizeClass,
    title: String,
    subtitle: String
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Altura adaptativa
    val bannerHeight = when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> if (isLandscape) 90.dp else 120.dp
        WindowWidthSizeClass.Medium -> if (isLandscape) 120.dp else 160.dp
        else -> if (isLandscape) 140.dp else 200.dp
    }

    val logoSize = when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> 56.dp
        WindowWidthSizeClass.Medium -> 72.dp
        else -> 96.dp
    }

    val titleStyle = when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> MaterialTheme.typography.titleLarge
        WindowWidthSizeClass.Medium -> MaterialTheme.typography.headlineSmall
        else -> MaterialTheme.typography.headlineMedium
    }

    val subtitleStyle = when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> MaterialTheme.typography.bodySmall
        WindowWidthSizeClass.Medium -> MaterialTheme.typography.bodyMedium
        else -> MaterialTheme.typography.bodyLarge
    }

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(gradient)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Icon(
                imageVector = Icons.Filled.FitnessCenter,
                contentDescription = "Logo gimnasio",
                modifier = Modifier.size(logoSize),
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(Modifier.width(16.dp))


            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = titleStyle,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = subtitleStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            if (widthSizeClass != WindowWidthSizeClass.Compact) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}
