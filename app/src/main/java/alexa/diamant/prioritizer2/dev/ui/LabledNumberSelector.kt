package alexa.diamant.prioritizer2.dev.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LabeledNumberSelector(
    label: String,
    range: IntRange,
    selected: Int,
    onSelect: (Int) -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(range.toList()) { number ->
                val isSelected = number == selected
                OutlinedButton(
                    onClick = { onSelect(number) },
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) Color.LightGray else Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(50.dp)
                ) {
                    Text(
                        text = number.toString(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}