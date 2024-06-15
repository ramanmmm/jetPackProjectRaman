package com.example.testcompose

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcompose.ui.theme.TestcomposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestcomposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navItems = listOf("Home", "Search", "More")
        var selectedItem by remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.Black
                ) {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = when (item) {
                                        "Home" -> Icons.Default.Home
                                        "Search" -> Icons.Default.Search
                                        "More" -> Icons.Default.MoreVert
                                        else -> Icons.Default.Home
                                    },
                                    contentDescription = item
                                )
                            },
                            label = {
                                Text(
                                    text = item,
                                    style = TextStyle(color = if (selectedItem == index) Color.White else Color.Gray)
                                )
                            },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (selectedItem) {
                    0 -> HomeScreen()
                    1 -> SearchScreen()
                    2 -> MoreScreen()
                }
            }
        }
    }

    @Composable
    fun HomeScreen() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item {
                // Top App Logo and Name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "MERSION",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "MERSION",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    )
                }
            }

            // Horizontal RecyclerViews for each section
            val sections = listOf(
                "Must Watch Lessons", "Trending in Career Discovery",
                "Entertain Your Brain", "Career Insights",
                "Lessons from Cinema", "Liberal Arts", "Liberal Arts Part-2"
            )
            items(sections) { section ->
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = section,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // RecyclerView for each section
                    HorizontalRecyclerView(section)
                }
            }
        }
    }

    @Composable
    fun HorizontalRecyclerView(section: String) {
        val items = when (section) {
            "Must Watch Lessons" -> listOf(
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8,
                R.drawable.i9,
                R.drawable.i10
            )

            "Trending in Career Discovery" -> listOf(
                R.drawable.i10,
                R.drawable.i9,
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8

            )

            "Entertain Your Brain" -> listOf(
                R.drawable.i7,
                R.drawable.i8,
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i9,
                R.drawable.i10
            )

            "Career Insights" -> listOf(
                R.drawable.i4,
                R.drawable.i5,
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i3,
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8,
                R.drawable.i9,
                R.drawable.i10
            )

            "Lessons from Cinema" -> listOf(
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8,
                R.drawable.i9,
                R.drawable.i10
            )

            "Liberal Arts Part-2" -> listOf(
                R.drawable.i2,
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8,
                R.drawable.i9,
                R.drawable.i10,
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i1

            )

            "Liberal Arts" -> listOf(
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i8,
                R.drawable.i9,
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i7,
                R.drawable.i10
            )

            else -> emptyList()
        }

        LazyRow {
            items(items) { item ->
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = item),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                    )
                }
            }
        }
    }
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun SearchScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            // Search Bar
            val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1976D2), shape = RoundedCornerShape(25.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(color = Color.White)
                    ) {
                        if (searchQuery.value.text.isEmpty()) {
                            Text("Search...", color = Color.White.copy(alpha = 0.5f))
                        }
                        it()
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Genres Section
            Text("Genres", color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Row {
                GenreButton("Nation", Color(0xFF00897B))
                Spacer(modifier = Modifier.width(30.dp).height(60.dp))
                GenreButton("Career", Color(0xFF512DA8))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Trending Section
            Text("Trending", color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

            // Make the trending items scrollable
            LazyColumn {
                items(listOf(
                    "Lesson 10 - National security in the modern age",
                    "Lesson 8 - Dharmonomics",
                    "Advertising",
                    "Lesson 3 - Bias in AI – the COMPAS case study - pa",
                    "Design Thinking in Netflix _ Case Studio - 04",
                    "Actor Vipin Sharma",
                    "Actor Raman Mishra",
                    "Welcome In india",
                    "King Kong Poster",
                    "Its coool..."
                )) { item ->
                    TrendingItem(item)
                }
            }
        }
    }

    @Composable
    fun GenreButton(text: String, color: Color) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text, color = Color.White, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text, color = Color.White, fontSize = 14.sp)
        }
    }

    @Composable
    fun TrendingItem(text: String) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.i5),
                    contentDescription = text,
                    modifier = Modifier
                        .width(120.dp)
                        .height(60.dp),
                    contentScale = ContentScale.FillWidth // Adjust as needed (e.g., FillBounds, Fit, Crop, etc.)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text, color = Color.White, fontSize = 16.sp)
            }
        }
    }


    @Composable
    fun MoreScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MoreScreenButton("Terms and conditions", ImageVector.vectorResource(R.drawable.ic_book), Color(0xFF1976D2))
            MoreScreenButton("About us", ImageVector.vectorResource(R.drawable.ic_person), Color(0xFF1976D2))
            MoreScreenButton("Contact Us", ImageVector.vectorResource(R.drawable.ic_contact), Color(0xFF1976D2))
            MoreScreenButton("Privacy policy", ImageVector.vectorResource(R.drawable.ic_security), Color(0xFF1976D2))
            MoreScreenButton("Refund policy", ImageVector.vectorResource(R.drawable.ic_refund), Color(0xFF1976D2))
            MoreScreenButton("Login", ImageVector.vectorResource(R.drawable.ic_login), Color(0xFF1976D2))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "2.0.13", color = Color.White, fontSize = 14.sp)
        }
    }

    @Composable
    fun MoreScreenButton(text: String, icon: ImageVector, buttonColor: Color) {
        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = Color.White),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .height(50.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = text, tint = Color.White, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text, textAlign = TextAlign.Center)
            }
        }
    }

    @Composable
    fun MainScreenPreview() {
        TestcomposeTheme {
            MainScreen()
        }
    }

    sealed class BackPress {
        object Idle : BackPress()
        object InitialTouch : BackPress()
    }

    @Composable
    fun BackPressSample() {
        var showToast by remember { mutableStateOf(false) }

        var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
        val context = LocalContext.current

        if (showToast) {
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
            showToast = false
        }

        LaunchedEffect(key1 = backPressState) {
            if (backPressState == BackPress.InitialTouch) {
                delay(2000)
                backPressState = BackPress.Idle
            }
        }

        BackHandler(backPressState == BackPress.Idle) {
            backPressState = BackPress.InitialTouch
            showToast = true
        }
    }
}
