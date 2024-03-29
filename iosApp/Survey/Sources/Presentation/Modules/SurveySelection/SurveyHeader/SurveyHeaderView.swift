//
//  SurveyHeaderView.swift
//  Survey
//
//  Created by Bliss on 5/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared
import SwiftUI

struct SurveyHeaderView: View {

    let surveyHeader: SurveyHeaderUiModel

    var onClickProfileImage: (() -> Void)?

    var body: some View {
        VStack(alignment: .leading, spacing: 4.0) {
            Text(surveyHeader.dateText)
                .font(.boldSmall)
                .accessibility(.surveySelection(.headerDateText))
            HStack {
                Text(surveyHeader.todayText())
                    .font(.boldLargeTitle)
                    .accessibility(.surveySelection(.headerTitleText))
                Spacer()
                Button {
                    onClickProfileImage?()
                } label: {
                    Image.url(surveyHeader.imageUrl.string)
                        .resizable()
                        .frame(width: 36.0, height: 36.0)
                        .cornerRadius(18.0)
                }
                .accessibility(.surveySelection(.headerProfileImage))
            }
        }
        .padding([.top, .horizontal], 20.0)
    }
}
