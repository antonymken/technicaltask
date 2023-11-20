package com.technical.task.ui.employeedetails.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.technical.task.domain.directory.model.EmployeeDomainModel
import com.technical.task.ui.employeedetails.EmployeeDetailsScreenParams

class EmployeeDetailsScreenPreviewParameterProvider :
    PreviewParameterProvider<EmployeeDetailsScreenParams> {
    override val values = sequenceOf(
        EmployeeDetailsScreenParams(
            Modifier,
            EmployeeDomainModel(
                "uuid1",
                "Justine Mason",
                "Engineer on the Point of Sale team.",
                "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                "Point of Sale"
            )
        )
    )
}