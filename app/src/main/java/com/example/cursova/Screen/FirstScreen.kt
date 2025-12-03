import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.Screen.Screens

@Composable
fun FirstScreen( navController: NavController) {

    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.фонпервогоэкрана))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()

            Spacer(modifier = Modifier.height(150.dp))



            ModulesSection(navController = navController)
        }

    }

}

@Composable
fun HeaderSection() {

    val gradient3 = Brush.linearGradient(
        colors = listOf(Color(0xFF5B00FF), Color(0xFF00E0FF))
    )



    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.boxes),
            contentDescription = "Module Icon",
            modifier = Modifier.size(48.dp)
                .background(gradient3, shape = RoundedCornerShape(7.dp))
                .padding(7.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                modifier = Modifier
                    .padding(start = 25.dp),
                text = "Учет склада",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Спортивный комплекс",

                )
        }
    }
}


@Composable
fun ModulesSection(navController: NavController) {


    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xEEFF9800), Color(0xFFFF3A00)),

        )
    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xD500FF07), Color(0xDF009306))
    )

    val gradient3 = Brush.linearGradient(
        colors = listOf(Color(0xDA008DFF), Color(0xE40029FF))
    )


    Column {
        ModuleCard(
            iconResId = R.drawable.wrench,
            title = "Управление ремонтом",
            description = "Виды ремонта, сдачи и возврат, сервисные центры",
            sections = 4,
            iconColor = gradient, // Цвет иконки для первой карточки
            textColor = colorResource(id = R.color.orangeFour), // Цвет текста с количеством разделов для первой карточки
            textBacColor = colorResource(id = R.color.orangeFourBacround),
            navController = navController
        )
        Spacer(modifier = Modifier.height(16.dp))
        ModuleCard(
            iconResId = R.drawable.packag,
            title = "Управление складом",
            description = "Залы, инвентаризация, основные средства",
            sections = 6,
            iconColor = gradient2, // Цвет иконки для второй карточки
            textColor = colorResource(id = R.color.greenFour), // Цвет текста с количеством разделов для второй карточки
            textBacColor = colorResource(id = R.color.greenFourBacround),
            navController = navController
        )
        Spacer(modifier = Modifier.height(16.dp))
        ModuleCard(
            iconResId = R.drawable.shoppingcart,
            title = "Управление закупками",
            description = "Документы закупок и номенклатура",
            sections = 2,
            iconColor = gradient3, // Цвет иконки для третьей карточки
            textColor = colorResource(id = R.color.blueFour),// Цвет текста с количеством разделов для третьей карточки
            textBacColor = colorResource(id = R.color.blueFourBacround),
            navController = navController
        )
    }
}

@Composable
fun ModuleCard(
    iconResId: Int,
    title: String,
    description: String,
    sections: Int,
    iconColor: Brush,
    textColor: Color,
    textBacColor: Color,
    navController: NavController
    ) {
    Card(
        modifier = Modifier

            .background(Color.White, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth(0.9F)
            .height(140.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Белый фон для содержимого внутри Card
                .clickable { // Здесь делаем карту кликабельной
                    when (title) {
                        "Управление ремонтом" -> navController.navigate(Screens.RepairManagment.route)
                        "Управление складом" -> navController.navigate(Screens.WarehouseManagement.route)
                        "Управление закупками" -> navController.navigate(Screens.ProcurementManagement .route)
                        // Добавляйте дополнительные условия для других карт
                    }
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp) // Паддинг внутри Card
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(iconColor, shape = RoundedCornerShape(7.dp)) // Фон для картинки
                ) {
                    Image(
                        painter = painterResource(id = iconResId),

                        contentDescription = "Module Icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(7.dp)


                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                    )
                    Box(
                        modifier = Modifier
                            .background(
                                textBacColor,
                                shape = RoundedCornerShape(15.dp)
                            ) // Фон для текста с количеством разделов
                            .padding(5.dp)
                    ) {
                        Text(
                            text = if (sections == 1) "$sections раздел" else if (sections in 2..4) "$sections раздела" else "$sections разделов",
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}