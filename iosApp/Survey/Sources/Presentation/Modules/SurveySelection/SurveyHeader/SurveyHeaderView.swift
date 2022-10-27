//
//  SurveyHeaderView.swift
//  Survey
//
//  Created by Bliss on 5/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Kingfisher
import Shared
import SwiftUI

struct SurveyHeaderView: View {

    let surveyHeader: SurveyHeaderUiModel

    var imageAction: (() -> Void)?

    var body: some View {
        VStack(alignment: .leading, spacing: 4.0) {
            Text(surveyHeader.dateText)
                .font(.boldSmall)
            HStack {
                Text(surveyHeader.todayText())
                    .font(.boldLarge)
                Spacer()
                Button {
                    imageAction?()
                } label: {
                    KFImage(surveyHeader.imageUrl.string.asURL)
                        .resizable()
                        .frame(width: 36.0, height: 36.0)
                        .cornerRadius(18.0)
                }
                .accessibility(.surveySelection(.headerProfileImage))
            }
        }
        .padding([.top, .horizontal], 20.0)
        .accessibilityElement(children: .contain)
    }
}
