//
//  SurveyQuestionView.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Shared
import SwiftUI

struct SurveyQuestionView: View {

    let detail: SurveyDetailUiModel

    @Binding var questionIndex: Int

    var body: some View {
        VStack(alignment: .leading) {
            Text("\(questionIndex + 1)/\(detail.questions.count)")
                .font(.boldMedium)
                .foregroundColor(.white)
                .opacity(0.5)
                .padding(.top, .largePadding)
                .accessibility(.surveyQuestion(.detailText))
            Text(detail.questions[questionIndex].text)
                .font(.boldLargeTitle)
                .foregroundColor(.white)
                .padding(.top, .tinyPadding)
                .accessibility(.surveyQuestion(.titleText))
            Spacer()
            // TODO: Show real questions
            switch detail.questions[questionIndex].displayType {
            case SurveyDetailUiModel.companion.Choice:
                QuestionPickerView(ids: ["A", "B", "C"])
            default: QuestionPickerView(ids: ["A", "B", "C"])
            }
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
