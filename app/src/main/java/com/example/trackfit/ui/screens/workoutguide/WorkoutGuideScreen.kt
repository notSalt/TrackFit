import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import androidx.compose.foundation.lazy.LazyColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutGuideScreen(
                navController = rememberNavController(),
                onVideoClick = { videoUrl ->
                    openYouTubeVideo(videoUrl)
                }
            )
        }
    }

    private fun openYouTubeVideo(videoUrl: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutGuideScreen(
    navController: NavHostController,
    onVideoClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Workout Guide") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                VideoItem("FULL BODY YOGA Workout", "https://youtu.be/Eml2xnoLpYE", onVideoClick)
            }
            item {
                VideoItem("ABS Workout", "https://youtu.be/iD8F3D1JeZk", onVideoClick)
            }
            item {
                VideoItem("UPPER BODY & CARDIO Workout", "https://youtu.be/8Xlv99EGEEQ", onVideoClick)
            }
            item {
                VideoItem("LEG Workout", "https://youtu.be/e_CcHiwhGvY", onVideoClick)
            }
        }
    }
}

@Composable
fun VideoItem(videoTitle: String, videoUrl: String, onVideoClick: (String) -> Unit) {
    val videoId = videoUrl.substringAfterLast("v=", videoUrl.substringAfterLast("/"))
    val thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onVideoClick(videoUrl) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(thumbnailUrl),
            contentDescription = "Thumbnail for $videoTitle",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Crop
        )

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = videoTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutGuideScreen() {
    WorkoutGuideScreen(
        navController = rememberNavController(),
        onVideoClick = {}
    )
}
