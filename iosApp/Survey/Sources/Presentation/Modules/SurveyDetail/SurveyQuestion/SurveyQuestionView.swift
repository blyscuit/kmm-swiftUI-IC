//
//  SurveyQuestionView.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Shared
import SwiftUI

typealias Answer = SurveyDetailUiModel.SurveyAnswer

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
            if let question = detail.questions[safe: questionIndex] {
                Text(question.text)
                    .font(.boldLargeTitle)
                    .foregroundColor(.white)
                    .padding(.top, .tinyPadding)
                    .accessibility(.surveyQuestion(.titleText))
                Spacer()
                questionView(with: question)
            }
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    @ViewBuilder
    func questionView(with question: SurveyDetailUiModel.SurveyIncluded) -> some View {
        // TODO: Show real questions
        switch question.displayType {
        case .choice:
            QuestionPickerView(ids: ["A", "B", "C"])
        case .star:
            QuestionEmojiView(type: .star, options: question.answers)
        case .smiley:
            QuestionEmojiView(type: .smile, options: question.answers)
        case .heart:
            QuestionEmojiView(type: .heart, options: question.answers)
        default:
            QuestionPickerView(ids: ["A", "B", "C"])
        }
    }
}
