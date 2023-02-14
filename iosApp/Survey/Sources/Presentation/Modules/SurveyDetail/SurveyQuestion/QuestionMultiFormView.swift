//
//  QuestionMultiFormView.swift
//  Survey
//
//  Created by Bliss on 2/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionMultiFormView: View {

    struct MultiFormAnswer: Equatable {

        let question: Answer
        var input: String = ""
    }

    @State var multiFormAnswers = [MultiFormAnswer]()
    @Binding var answers: [String]

    var body: some View {
        VStack(spacing: .lineSpacing) {
            ForEach(
                Array(multiFormAnswers.enumerated()),
                id: \.element.question.id
            ) { index, answer in
                TextField(
                    answer.question.text,
                    text: $multiFormAnswers[index].input
                )
                .primaryTextField()
            }
        }
        .onChange(of: multiFormAnswers) {
            answers = $0.map { $0.input }
        }
    }

    init(answers: [Answer], currentAnswers: Binding<[String]>) {
        _multiFormAnswers = .init(
            initialValue: answers.map {
                MultiFormAnswer(question: $0)
            }
        )
        _answers = currentAnswers
    }
}
