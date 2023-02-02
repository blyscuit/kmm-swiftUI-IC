//
//  QuestionMultiFormView.swift
//  Survey
//
//  Created by Bliss on 2/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionMultiFormView: View {

    struct MultiFormAnswer {

        let question: Answer
        var input: String = ""
    }

    @State var multiFormAnswers = [MultiFormAnswer]()

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
    }

    init(answers: [Answer]) {
        _multiFormAnswers = .init(
            initialValue: answers.map {
                MultiFormAnswer(question: $0)
            }
        )
    }
}
