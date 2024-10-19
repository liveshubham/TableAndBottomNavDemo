package com.app.learningcomposedemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.learningcomposedemo.ui.theme.LearningComposeDemoTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LearningComposeDemoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Top App Bar") },
                            Modifier.background(Color.Blue)
                        )
                    },
                    content = { Text("Content") },
                    bottomBar = {
                        BottomNav()
                    })
            }
        }
    }
}

@Composable
fun BottomNav() {
    val selectedIndex = remember { mutableIntStateOf(0) }
    BottomAppBar(tonalElevation = 10.dp) {
        NavigationBarItem(icon = {
            Icon(imageVector = Icons.Default.Home, "")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        NavigationBarItem(icon = {
            Icon(imageVector = Icons.Default.Favorite, "")
        },
            label = { Text(text = "Favorite") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        NavigationBarItem(icon = {
            Icon(imageVector = Icons.Default.Person, "")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Spacer(modifier = Modifier.heightIn(10.dp))
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

data class Data(
    val no: String,
    val name: String,
    val address: String
)

@Composable
fun TableScreen() {
    // Just a fake data... a Pair of Int and String
    val tableData = (1..100).mapIndexed { index, item ->
        index to "Item $index"
    }
    val data = mutableListOf<Data>()
    data.add(Data("1", "Lavkush", "Amethi"))
    data.add(Data("2", "Vipin", "Sultanpur"))
    data.add(Data("3", "Shubham", "Amethi"))
    // Each cell of a column must have the same weight.
    val column1Weight = .2f // 30%
    val column2Weight = .3f // 70%
    val column3Weight = .5f // 70%

    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Here is the header
        item {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color.Cyan),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color.Red),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    hoveredElevation = 10.dp,
                    focusedElevation = 10.dp,
                    disabledElevation = 10.dp,
                    pressedElevation = 10.dp
                ), contentPadding = PaddingValues(10.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Cart button icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Add to cart", Modifier.padding(start = 10.dp))
            }
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "S.No.", weight = column1Weight)
                TableCell(text = "Name", weight = column2Weight)
                TableCell(text = "Address", weight = column3Weight)
            }
        }
        // Here are all the lines of your table.
        items(data) {
            val (id, text) = it

            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.no, weight = column1Weight)
                TableCell(text = it.name, weight = column2Weight)
                TableCell(text = it.address, weight = column3Weight)
            }
        }
    }
}

@Composable
fun FirstRow() {
    Column() {
        RowDesign(serialNo = "S.no", name = "Name", address = "Address")
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .width(200.dp)
        )
        RowDesign(serialNo = "1", name = "Name", address = "Address")
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .width(150.dp)
        )
        RowDesign(serialNo = "2", name = "Name", address = "Address")
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .wrapContentWidth()
        )
        RowDesign(serialNo = "3", name = "Name", address = "Address")
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .wrapContentWidth()
        )
    }
}

@Composable
fun RowDesign(serialNo: String, name: String, address: String) {
    Row() {
        ReuseForTableDesignTextHeading(serialNo, FontWeight.Bold, Color.Blue)
        VerticalDivider(modifier = Modifier.height(40.dp))
        ReuseForTableDesignTextHeading(name, FontWeight.Bold, Color.Blue)
        VerticalDivider(modifier = Modifier.height(40.dp))
        ReuseForTableDesignTextHeading(address, FontWeight.Bold, Color.Blue)
        VerticalDivider(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun ReuseForTableDesignTextHeading(string: String, fontWeight: FontWeight, color: Color) {
    Text(
        text = string, modifier = Modifier.padding(8.dp),
        color = color,
        fontWeight = fontWeight
    )
}

@Composable
fun ReuseForTableDesignText(string: String) {
    Text(
        text = string, modifier = Modifier.padding(8.dp),
        color = Color.Red,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun GreetingPreview() {
    LearningComposeDemoTheme {
        BottomNav()
    }
}