package com.example.sparring.profile.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sparring.model.BeltType
import com.example.sparring.model.GrauType
import com.example.sparring.model.PlayStyle
import com.seungsu.design.component.VerticalSeparator
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileInfo(
    modifier: Modifier = Modifier,
    userName: String,
    userNickName: String,
    gymName: String,
    currentBelt: BeltType,
    currentGrau: GrauType,
    currentPlayStyles: List<PlayStyle>,
    onClickEditProfile: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_default_profile),
                contentDescription = "profile",
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 12.dp),
                tint = DongsaniTheme.colors.label.onBgPrimary
            )
            if (userName.isEmpty()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.register_profile),
                    style = DongsaniTheme.typos.regular.font14,
                    color = DongsaniTheme.colors.label.onBgPrimary
                )
            } else {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = userName,
                            style = DongsaniTheme.typos.bold.font16,
                            color = DongsaniTheme.colors.label.onBgPrimary
                        )
                        VerticalSeparator(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            color = DongsaniTheme.colors.label.onBgTertiary
                        )
                        Text(
                            text = userNickName,
                            style = DongsaniTheme.typos.regular.font14,
                            color = DongsaniTheme.colors.label.onBgSecondary
                        )
                    }
                    Text(
                        text = gymName,
                        style = DongsaniTheme.typos.regular.font14,
                        color = DongsaniTheme.colors.label.onBgPrimary
                    )
                }
            }
            IconButton(
                onClick = onClickEditProfile
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "edit profile",
                    modifier = Modifier.size(16.dp),
                    tint = DongsaniTheme.colors.label.onBgPrimary
                )
            }
        }
        Belt(
            currentBeltType = currentBelt,
            currentGrauType = currentGrau,
            isBlack = currentBelt == BeltType.BLACK
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 18.dp)
        ) {
            repeat(currentPlayStyles.size) { index ->
                Text(
                    text = currentPlayStyles[index].styleName,
                    style = DongsaniTheme.typos.regular.font12,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp),
                            color = DongsaniTheme.colors.label.onBgTertiary
                        )
                        .padding(
                            vertical = 6.dp,
                            horizontal = 12.dp
                        ),
                    color = DongsaniTheme.colors.label.onBgPrimary
                )
            }
        }

    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ProfileInfoPreview() {
    DongsaniTheme {
        ProfileInfo(
            modifier = Modifier.padding(16.dp),
            userName = "name",
            userNickName = "nickName",
            gymName = "gymName",
            currentBelt = BeltType.PURPLE,
            currentGrau = GrauType.THREE,
            currentPlayStyles = listOf(
                PlayStyle(
                    id = 1,
                    styleName = "Guard"
                ),
                PlayStyle(
                    id = 2,
                    styleName = "Half-Guard"
                ),
                PlayStyle(
                    id = 3,
                    styleName = "Top"
                ),
            )
        )
    }
}
