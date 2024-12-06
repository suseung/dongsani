package com.example.sparring.makeprofile

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sparring.makeprofile.component.LevelSelectBottomSheetContent
import com.example.sparring.makeprofile.component.SelectItem
import com.example.sparring.model.PLAYSTYLEs
import com.seungsu.common.INVALID_INT
import com.seungsu.common.component.CollectContent
import com.seungsu.common.createNewImageFile
import com.seungsu.common.eventbus.Event
import com.seungsu.common.eventbus.EventBusEntryPoint
import com.seungsu.common.ext.noRippleClickable
import com.seungsu.common.ext.toastS
import com.seungsu.common.getCurrentBitmap
import com.seungsu.common.model.ContentsType
import com.seungsu.common.model.DialogEvent
import com.seungsu.design.ThemePreview
import com.seungsu.design.component.DongsaniBottomSheet
import com.seungsu.design.component.DongsaniComposeDialog
import com.seungsu.design.component.DongsaniScaffold
import com.seungsu.design.component.DongsaniTextField
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R
import java.io.File

val CAMERA_PERMISSION = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
    listOf(Manifest.permission.CAMERA)
} else {
    listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
}

@Composable
fun SparringMakeProfileScreen(
    viewModel: SparringMakeProfileViewModel = hiltViewModel(),
    onRestart: () -> Unit,
    onNavPopback: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val action by remember { mutableStateOf(viewModel::dispatch) }
    val context = LocalContext.current
    var isMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var isRestartDialogVisible by rememberSaveable { mutableStateOf(false) }
    val selectBeltMessage = stringResource(id = R.string.no_belt_selected)
    val selectGrauMessage = stringResource(id = R.string.no_grau_selected)
    val eventBus = EventBusEntryPoint.resolve(context).getEventBus()

    CollectContent(
        viewModel = viewModel,
        processEffect = { effect ->
            when (effect) {
                SparringMakeProfileEffect.ShowRestartDialog -> isRestartDialogVisible = true
                SparringMakeProfileEffect.ShowSelectBeltToast -> context.toastS(selectBeltMessage)
                SparringMakeProfileEffect.ShowSelectGrauToast -> context.toastS(selectGrauMessage)
                SparringMakeProfileEffect.NavigateToProfile -> onNavPopback()
            }
        }
    )

    DisposableEffect(true) {
        onDispose { eventBus.publishEvent(Event.Sparring.OnProfileChanged) }
    }

    DongsaniScaffold(
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp)
                    .clickable { onNavPopback() },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = "back"
            )
        },
        titleContent = {
            Text(
                text = stringResource(id = R.string.sparring_profile_title),
                style = DongsaniTheme.typos.regular.font18,
                color = DongsaniTheme.colors.label.onBgPrimary,
                textAlign = TextAlign.Center
            )
        },
        actions = {
            IconButton(
                onClick = { isMenuExpanded = isMenuExpanded.not() }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "setting",
                )
            }
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "운동기록",
                            style = TextStyle(
                                color = DongsaniTheme.colors.label.onBgPrimary
                            )
                        )
                    },
                    onClick = {
                        action(SparringMakeProfileIntent.OnChangeContent(ContentsType.EXERCISE_RECORD))
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "스파링",
                            style = TextStyle(
                                color = DongsaniTheme.colors.label.onBgPrimary
                            )
                        )
                    },
                    onClick = {
                        action(SparringMakeProfileIntent.OnChangeContent(ContentsType.SPARRING))
                    }
                )
            }
        },
        containerColor = DongsaniTheme.colors.background.groupedUpperBase,
        bottomBar = {
            Button(
                onClick = { action(SparringMakeProfileIntent.OnClickSaveProfile) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DongsaniTheme.colors.system.inverse
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(4.dp),
                enabled = uiState.isSaveEnabled
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    style = DongsaniTheme.typos.bold.font14,
                    color = DongsaniTheme.colors.label.onTintPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        with(uiState) {
            SparringProfileLoaded(
                modifier = Modifier.padding(paddingValues),
                name = name,
                nickName = nickName,
                gymName = gymName,
                currentBeltId = currentBeltId,
                currentGrauId = currentGrauId,
                currentLevel = currentLevel,
                currentPlayStyleIds = currentPlayStyleIds,
                profileImagePath = profileImagePath,
                profileImageUri = profileImageUri,
                onUiAction = action
            )
        }
    }
    if (isRestartDialogVisible) {
        DongsaniComposeDialog(
            message = stringResource(id = R.string.app_restart),
            confirmText = stringResource(id = R.string.restart_ok),
            isCancellable = false,
            onClickConfirmed = onRestart
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SparringProfileLoaded(
    modifier: Modifier = Modifier,
    name: String,
    nickName: String,
    gymName: String,
    currentBeltId: Int,
    currentGrauId: Int,
    currentLevel: String,
    profileImagePath: String,
    profileImageUri: Uri,
    currentPlayStyleIds: List<Int>,
    onUiAction: (SparringMakeProfileIntent) -> Unit = {}
) {
    val context = LocalContext.current
    var isLevelBottomSheetOpen by rememberSaveable { mutableStateOf(false) }
    var isProfileImageSelectorOpen by rememberSaveable { mutableStateOf(false) }
    var dialogEvent by remember { mutableStateOf(DialogEvent.NONE) }
    var mediaPath by remember { mutableStateOf(File("")) }
    val cameraActivityResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                onUiAction(SparringMakeProfileIntent.OnChangeProfileImage(mediaPath.absolutePath, null))
            } else {
                return@rememberLauncherForActivityResult
            }
        }
    val galleryActivityResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    onUiAction(SparringMakeProfileIntent.OnChangeProfileImage(null, uri))
                }

            } else {
                return@rememberLauncherForActivityResult
            }
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allPermissionsGranted = permissions.values.all { it }
        if (allPermissionsGranted.not()) {
            dialogEvent = DialogEvent.PERMISSION_DENIED
        } else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                mediaPath = createNewImageFile(context)
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    mediaPath
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                cameraActivityResultLauncher.launch(intent)
            } catch (exception: PackageManager.NameNotFoundException) {
                context.toastS(exception.message.toString())
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.padding(top = 24.dp)
        )
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
                        .padding(bottom = 24.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .noRippleClickable { isProfileImageSelectorOpen = true }
                )
            }

            else -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_default_profile),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(104.dp)
                        .padding(bottom = 24.dp)
                        .noRippleClickable { isProfileImageSelectorOpen = true },
                    tint = DongsaniTheme.colors.system.inverse
                )
            }
        }
        Text(
            text = stringResource(id = R.string.sparring_name_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        DongsaniTextField(
            value = name,
            onValueChange = { onUiAction(SparringMakeProfileIntent.OnChangeName(it)) },
            textStyle = DongsaniTheme.typos.regular.font12.copy(
                color = DongsaniTheme.colors.label.onBgPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_name_placeholder),
                    style = DongsaniTheme.typos.regular.font12,
                    color = DongsaniTheme.colors.label.onBgTertiary
                )
            },
            trailingIcon = {
                if (name.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onUiAction(SparringMakeProfileIntent.OnClearName) },
                        tint = DongsaniTheme.colors.label.onBgSecondary,
                        contentDescription = "close"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Text(
            text = stringResource(id = R.string.sparring_nickname_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        DongsaniTextField(
            value = nickName,
            onValueChange = { onUiAction(SparringMakeProfileIntent.OnChangeNickName(it)) },
            textStyle = DongsaniTheme.typos.regular.font12.copy(
                color = DongsaniTheme.colors.label.onBgPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_nickname_placeholder),
                    style = DongsaniTheme.typos.regular.font12,
                    color = DongsaniTheme.colors.label.onBgTertiary
                )
            },
            trailingIcon = {
                if (nickName.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onUiAction(SparringMakeProfileIntent.OnClearNickName) },
                        tint = DongsaniTheme.colors.label.onBgSecondary,
                        contentDescription = "close"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Text(
            text = stringResource(id = R.string.sparring_gym_name_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        DongsaniTextField(
            value = gymName,
            onValueChange = { onUiAction(SparringMakeProfileIntent.OnChangeGymName(it)) },
            textStyle = DongsaniTheme.typos.regular.font12.copy(
                color = DongsaniTheme.colors.label.onBgPrimary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_gym_name_placeholder),
                    style = DongsaniTheme.typos.regular.font12,
                    color = DongsaniTheme.colors.label.onBgTertiary
                )
            },
            trailingIcon = {
                if (gymName.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onUiAction(SparringMakeProfileIntent.OnClearGymName) },
                        tint = DongsaniTheme.colors.label.onBgSecondary,
                        contentDescription = "close"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Text(
            text = stringResource(id = R.string.sparring_level_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { isLevelBottomSheetOpen = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(4.dp),
                    color = DongsaniTheme.colors.separator
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = DongsaniTheme.colors.label.onBgSecondary
            ),
            contentPadding = PaddingValues(16.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = currentLevel.takeIf { it.isNotEmpty() }
                    ?: stringResource(id = R.string.sparring_level_placeholder),
                color = if (currentLevel.isEmpty()) {
                    DongsaniTheme.colors.label.onBgTertiary
                } else {
                    DongsaniTheme.colors.label.onBgPrimary
                },
                style = DongsaniTheme.typos.regular.font14,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onUiAction(SparringMakeProfileIntent.OnClearGymName) },
                tint = DongsaniTheme.colors.label.onBgSecondary,
                contentDescription = "close"
            )
        }
        Text(
            text = stringResource(id = R.string.sparring_speciality_label),
            style = DongsaniTheme.typos.regular.font12,
            textAlign = TextAlign.Start,
            color = DongsaniTheme.colors.label.onBgPrimary,
            modifier = Modifier.fillMaxWidth()
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 14.dp, bottom = 24.dp)
        ) {
            repeat(PLAYSTYLEs.size) { index ->
                SelectItem(
                    content = PLAYSTYLEs[index].styleName,
                    isSelected = currentPlayStyleIds.contains(PLAYSTYLEs[index].id),
                    onClickItem = { onUiAction(SparringMakeProfileIntent.OnSelectPlayStyle(PLAYSTYLEs[index].id)) }
                )
            }
        }
    }

    if (isProfileImageSelectorOpen) {
        DongsaniBottomSheet(
            onDismissRequest = { isProfileImageSelectorOpen = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { isProfileImageSelectorOpen = false }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "close",
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.delete),
                    style = DongsaniTheme.typos.regular.font16,
                    color = DongsaniTheme.colors.label.onBgPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .noRippleClickable {
                            onUiAction(SparringMakeProfileIntent.OnClickDeleteProfile)
                            isProfileImageSelectorOpen = false
                        }
                )
                Divider()
                Text(
                    text = stringResource(id = R.string.take_photo),
                    style = DongsaniTheme.typos.regular.font16,
                    color = DongsaniTheme.colors.label.onBgPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .noRippleClickable {
                            val permissionNeeded = mutableListOf<String>()
                            CAMERA_PERMISSION.forEach { permission ->
                                if (ContextCompat.checkSelfPermission(
                                        context,
                                        permission
                                    ) != PackageManager.PERMISSION_GRANTED
                                ) {
                                    permissionNeeded.add(permission)
                                }
                            }
                            if (permissionNeeded.isNotEmpty()) {
                                permissionLauncher.launch(CAMERA_PERMISSION.toTypedArray())
                            } else {
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                try {
                                    mediaPath = createNewImageFile(context)
                                    val uri = FileProvider.getUriForFile(
                                        context,
                                        "${context.packageName}.provider",
                                        mediaPath
                                    )
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                                    cameraActivityResultLauncher.launch(intent)
                                } catch (exception: PackageManager.NameNotFoundException) {
                                    context.toastS(exception.message.toString())
                                }
                            }
                            isProfileImageSelectorOpen = false
                        }
                )
                Divider()
                Text(
                    text = stringResource(id = R.string.get_photo_from_gallery),
                    style = DongsaniTheme.typos.regular.font16,
                    color = DongsaniTheme.colors.label.onBgPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .noRippleClickable {
                            Intent()
                                .apply {
                                    type = "image/*"
                                    action = Intent.ACTION_GET_CONTENT
                                }
                                .let { galleryActivityResultLauncher.launch(it) }
                            isProfileImageSelectorOpen = false
                        }
                )
            }
        }
    }
    if (isLevelBottomSheetOpen) {
        DongsaniBottomSheet(
            onDismissRequest = { isLevelBottomSheetOpen = false }
        ) {
            LevelSelectBottomSheetContent(
                currentBeltId = currentBeltId,
                currentGrauId = currentGrauId,
                onClickClose = { isLevelBottomSheetOpen = false },
                onClickSave = { beltId, grauId ->
                    onUiAction(SparringMakeProfileIntent.OnChangeLevel(beltId, grauId))
                    if (beltId != INVALID_INT && grauId != INVALID_INT) isLevelBottomSheetOpen = false
                }
            )
        }
    }
    when (dialogEvent) {
        DialogEvent.PERMISSION_DENIED -> {
            DongsaniComposeDialog(
                title = stringResource(id = R.string.permission_request_title),
                message = stringResource(id = R.string.camera_permission_request_message),
                cancelText = stringResource(id = R.string.cancel),
                confirmText = stringResource(id = R.string.settings),
                onClickConfirmed = {
                    val action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val data = Uri.parse("package:${context.packageName}")
                    val intent = Intent(action, data).apply {
                        addCategory(Intent.CATEGORY_DEFAULT)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(context, intent, null)
                },
                isCancellable = false,
                onDismiss = { dialogEvent = DialogEvent.NONE }
            )
        }

        else -> Unit
    }
}

@ThemePreview
@Composable
private fun SparringProfileLoadedPreview() {
    DongsaniTheme {
        SparringProfileLoaded(
            name = "name",
            nickName = "",
            gymName = "gymName",
            currentBeltId = 3,
            currentGrauId = 3,
            currentLevel = "Purple, 3 Grau",
            currentPlayStyleIds = emptyList(),
            profileImagePath = "",
            profileImageUri = Uri.EMPTY
        )
    }
}
