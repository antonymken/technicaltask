package com.technical.task.ui.employeedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.technical.task.core_android.annotation.LightDarkPreview
import com.technical.task.core_android.theme.AppTheme
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.ui.R
import com.technical.task.ui.employeedetails.model.EmployeeDetailsScreenPreviewParameterProvider

data class EmployeeDetailsScreenParams(val modifier: Modifier, val employeeDetails: EmployeeDomainModel)

@Composable
fun EmployeeDetailsScreen(employeeDetailsScreenParams: EmployeeDetailsScreenParams) {
    Box(
        modifier = employeeDetailsScreenParams.modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .testTag(EMPLOYEES_DETAILS)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = employeeDetailsScreenParams.employeeDetails.photoUrlLarge,
                contentDescription = stringResource(R.string.employee_image_description),
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 4.dp,
                        end = 12.dp,
                        bottom = 8.dp
                    )
                    .size(150.dp)
                    .border(1.dp, MaterialTheme.colorScheme.secondaryContainer),
                contentScale = ContentScale.FillWidth,
            )
            Text(
                text = employeeDetailsScreenParams.employeeDetails.fullName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        start = 4.dp
                    )
            )
            Text(
                text = employeeDetailsScreenParams.employeeDetails.team,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 4.dp,
                        end = 4.dp,
                        bottom = 4.dp
                    )
            )
            Text(
                text = employeeDetailsScreenParams.employeeDetails.summary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 4.dp,
                        end = 4.dp,
                        bottom = 4.dp
                    ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@LightDarkPreview
@Composable
fun EmployeeDetailsScreenPreview(
    @PreviewParameter(EmployeeDetailsScreenPreviewParameterProvider::class) param: EmployeeDetailsScreenParams
) {
    AppTheme {
        EmployeeDetailsScreen(param)
    }
}