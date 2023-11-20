package com.technical.task.ui.employeeslist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.technical.task.core_android.annotation.LightDarkPreview
import com.technical.task.core_android.theme.AppTheme
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.ui.R
import com.technical.task.ui.employeeslist.model.EmployeesListPreviewParameterProvider
import com.technical.task.ui.navigation.EmployeeDetailsDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val EMPLOYEES_LIST = "EMPLOYEES_LIST"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmployeesList(
    list: List<EmployeeDomainModel>,
    refreshing: StateFlow<Boolean>,
    fnRefresh: () -> Unit,
    modifier: Modifier,
    navHostController: NavHostController
) {
    val isRefreshing by refreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, fnRefresh)

    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary)
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier
                .testTag(EMPLOYEES_LIST)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = list) { _, item ->
                EmployeeItem(item, navHostController)
            }
        }
        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }

}

@Composable
fun EmployeeItem(employee: EmployeeDomainModel, navHostController: NavHostController) {

    Card(
        modifier = Modifier
            .padding(4.dp, 4.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(4.dp, 4.dp)
                .clickable(onClick = {
                    navHostController.navigate(EmployeeDetailsDestination.route + "/${employee.uuid}") {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        ) {
            AsyncImage(
                model = employee.photoUrlSmall,
                contentDescription = stringResource(R.string.employee_image_description),
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 4.dp,
                        end = 12.dp,
                        bottom = 4.dp
                    )
                    .size(90.dp)
                    .border(1.dp, MaterialTheme.colorScheme.secondaryContainer),
                contentScale = ContentScale.FillWidth,
            )
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = employee.fullName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = employee.team,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(
                            start = 0.dp,
                            top = 4.dp,
                            end = 4.dp,
                            bottom = 4.dp
                        ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = employee.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(
                            start = 0.dp,
                            top = 4.dp,
                            end = 4.dp,
                            bottom = 4.dp
                        ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@LightDarkPreview
@Composable
fun EmployeeListPreview(
    @PreviewParameter(EmployeesListPreviewParameterProvider::class) list: List<EmployeeDomainModel>
) {
    val isRefreshing: StateFlow<Boolean> = MutableStateFlow(false)
    AppTheme {
        EmployeesList(list, isRefreshing, {}, Modifier, rememberNavController())
    }
}