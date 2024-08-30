package com.example.sparring.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.sparring.model.BeltType
import com.example.sparring.model.GrauType
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.BROWN
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.design.theme.Purple

@Composable
fun Belt(
    modifier: Modifier = Modifier,
    currentBeltType: BeltType,
    currentGrauType: GrauType,
    isBlack: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = when (currentBeltType) {
                        BeltType.WHITE -> DongsaniTheme.colors.system.white.copy(
                            red = 0.1f,
                            blue = 0.1f,
                            green = 0.1f,
                            alpha = 0.1f
                        )
                        BeltType.BLUE -> DongsaniTheme.colors.system.blue
                        BeltType.PURPLE -> Purple
                        BeltType.BROWN -> BROWN
                        BeltType.BLACK -> DongsaniTheme.colors.system.black
                    },
                    shape = RectangleShape
                )
        )
        when (currentGrauType) {
            GrauType.ZERO -> Grau(
                repeatSize = 0,
                isBlack = isBlack,
                modifier = Modifier.padding(end = 40.dp)
            )

            GrauType.ONE -> Grau(
                repeatSize = 1,
                isBlack = isBlack,
                modifier = Modifier.padding(end = 40.dp)
            )

            GrauType.TWO -> Grau(
                repeatSize = 2,
                isBlack = isBlack,
                modifier = Modifier.padding(end = 40.dp)
            )

            GrauType.THREE -> Grau(
                repeatSize = 3,
                isBlack = isBlack,
                modifier = Modifier.padding(end = 40.dp)
            )

            GrauType.FOUR -> Grau(
                repeatSize = 4,
                isBlack = isBlack,
                modifier = Modifier.padding(end = 40.dp)
            )
        }
    }
}


@ThemePreview
@Composable
fun BeltPreview() {
    DongsaniTheme {
        Column {
            Belt(
                modifier = Modifier.padding(16.dp),
                currentBeltType = BeltType.WHITE,
                currentGrauType = GrauType.ZERO,
                isBlack = false
            )
            Belt(
                modifier = Modifier.padding(16.dp),
                currentBeltType = BeltType.BLUE,
                currentGrauType = GrauType.TWO,
                isBlack = false
            )
            Belt(
                modifier = Modifier.padding(16.dp),
                currentBeltType = BeltType.PURPLE,
                currentGrauType = GrauType.THREE,
                isBlack = false
            )
            Belt(
                modifier = Modifier.padding(16.dp),
                currentBeltType = BeltType.BROWN,
                currentGrauType = GrauType.FOUR,
                isBlack = false
            )
            Belt(
                modifier = Modifier.padding(16.dp),
                currentBeltType = BeltType.BLACK,
                currentGrauType = GrauType.TWO,
                isBlack = true
            )
        }
    }
}
