package com.example.sparring.record.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sparring.model.BELTs
import com.example.sparring.model.BeltType
import com.example.sparring.model.GRAUs
import com.example.sparring.model.PLAYSTYLEs
import com.example.sparring.model.ProfileInfo
import com.example.sparring.profile.component.Belt
import com.example.sparring.profile.component.NameAndNickName
import com.seungsu.common.getCurrentBitmap
import com.seungsu.design.component.DongsaniBottomSheet
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OpponentProfileBottomSheet(
    modifier: Modifier = Modifier,
    profileInfo: ProfileInfo,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    DongsaniBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.exercise_record),
                style = DongsaniTheme.typos.bold.font16,
                color = DongsaniTheme.colors.label.onBgPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close",
                tint = DongsaniTheme.colors.label.onBgPrimary,
                modifier = Modifier
                    .padding(8.dp)
                    .size(16.dp)
                    .align(Alignment.TopEnd)
                    .clickable { onDismiss() }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                profileInfo.profileImagePath.isNotEmpty() || profileInfo.profileImageUri != Uri.EMPTY -> {
                    val resultBitmap = when {
                        profileInfo.profileImagePath.isNotEmpty() -> getCurrentBitmap(
                            context = context,
                            imagePath = profileInfo.profileImagePath
                        )

                        else -> getCurrentBitmap(
                            context = context,
                            imageUri = profileInfo.profileImageUri
                        )
                    }
                    Image(
                        bitmap = resultBitmap.asImageBitmap(),
                        contentDescription = "profile image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                    )
                }

                else -> Icon(
                    painter = painterResource(id = R.drawable.ic_default_profile),
                    modifier = Modifier.size(120.dp),
                    tint = DongsaniTheme.colors.system.inverse,
                    contentDescription = "profile"
                )
            }
            NameAndNickName(
                userName = profileInfo.name, 
                userNickName = profileInfo.nickName
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = profileInfo.gymName,
                style = DongsaniTheme.typos.bold.font24,
                color = DongsaniTheme.colors.label.onBgPrimary
            )
            Belt(
                modifier = Modifier.padding(top = 20.dp),
                currentBeltType = BELTs[profileInfo.beltId].belt,
                currentGrauType = GRAUs[profileInfo.grauId].grau,
                isBlack = BELTs[profileInfo.beltId].belt == BeltType.BLACK
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(top = 18.dp)
            ) {
                repeat(profileInfo.playStyleIds.size) { index ->
                    Text(
                        text = PLAYSTYLEs[profileInfo.playStyleIds[index]].styleName,
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
}