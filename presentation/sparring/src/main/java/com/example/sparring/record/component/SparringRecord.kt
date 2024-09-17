package com.example.sparring.record.component

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sparring.model.SparringResult
import com.example.sparring.profile.component.NameAndNickName
import com.seungsu.common.ext.noRippleClickable
import com.seungsu.common.getCurrentBitmap
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@Composable
fun SparringRecord(
    modifier: Modifier = Modifier,
    profileImagePath: String,
    profileImageUri: Uri,
    userName: String,
    userNickName: String,
    resultInfo: SparringResult,
    onClickProfile: () -> Unit = {}
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(8.dp),
                color = DongsaniTheme.colors.background.defaultElevated
            )
            .padding(16.dp)
            .noRippleClickable { onClickProfile() },
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when {
            profileImagePath.isNotEmpty() || profileImageUri != Uri.EMPTY -> {
                val resultBitmap = when {
                    profileImagePath.isNotEmpty() -> getCurrentBitmap(
                        context = context,
                        imagePath = profileImagePath
                    )

                    else -> getCurrentBitmap(
                        context = context,
                        imageUri = profileImageUri
                    )
                }
                Image(
                    bitmap = resultBitmap.asImageBitmap(),
                    contentDescription = "profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                )
            }

            else -> Icon(
                painter = painterResource(id = R.drawable.ic_default_profile),
                modifier = Modifier.size(45.dp),
                tint = DongsaniTheme.colors.system.inverse,
                contentDescription = "profile"
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            NameAndNickName(
                userName = userName,
                userNickName = userNickName
            )
            WinAndLearn(
                win = resultInfo.win,
                learn = resultInfo.learn,
                total = resultInfo.total
            )
        }
    }
}

@ThemePreview
@Composable
fun SparringRecordPreview() {
    DongsaniTheme {
        SparringRecord(
            modifier = Modifier.padding(16.dp),
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            userName = "name",
            userNickName = "nickName",
            resultInfo = SparringResult(
                win = 3,
                learn = 5
            )
        )
    }
}
