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
typealias SurveyAnswer = SurveySubmissionUiModel.Answer

struct SurveyQuestionView: View {

    let detail: SurveyDetailUiModel

    @Binding var questionIndex: Int
    @Binding var answers: [SurveyAnswer]

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
                    .onTapGesture {
                        hideKeyboard()
                    }
                Spacer()
                    .onTapGesture {
                        hideKeyboard()
                    }
                questionView(with: question)
            }
            Spacer()
                .onTapGesture {
                    hideKeyboard()
                }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    @ViewBuilder
    func questionView(with question: SurveyDetailUiModel.SurveyIncluded) -> some View {
        switch question.displayType {
        case .dropdown:
            QuestionPickerView(answers: $answers, options: question.answers)
        case .star:
            QuestionEmojiView(answers: $answers, type: .star, options: question.answers)
        case .smiley:
            QuestionEmojiView(answers: $answers, type: .smile, options: question.answers)
        case .heart:
            QuestionEmojiView(answers: $answers, type: .heart, options: question.answers)
        case .nps:
            QuestionRangePickerView(
                answers: $answers,
                options: question.answers,
                helpText: question.helpText.string
            )
        case .choice:
            QuestionMultiChoiceView(options: question.answers, answers: $answers)
        case .textfield:
            QuestionMultiFormView(answers: question.answers, currentAnswers: $answers)
        case .textarea:
            QuestionTextAreaView(
                id: question.id,
                placeholder: question.answers.first?.text,
                answers: $answers
            )
        default: VStack {}
        }
    }
}
