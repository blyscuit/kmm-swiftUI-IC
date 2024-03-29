//
//  QuestionTextAreaView.swift
//  Survey
//
//  Created by Bliss on 2/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionTextAreaView: View {

    let id: String
    let placeholder: String?

    @State var text = ""
    @Binding var answers: [SurveyAnswer]

    var body: some View {
        ZStack(alignment: .topLeading) {
            if text.isEmpty {
                Text(placeholder ?? "")
                    .foregroundColor(.white.opacity(0.25))
                    .padding()
                    .padding(.leading, 2.0)
                    .padding(.top, 4.0)
            }
            if #available(iOS 16.0, *) {
                TextEditor(text: $text)
                    .scrollContentBackground(.hidden)
                    .primaryTextField()
            } else {
                TextEditor(text: $text)
                    .primaryTextField()
            }
        }
        .onChange(of: text) {
            answers = [.init(id: id, answer: $0)]
        }
        .onAppear {
            UITextView.appearance().backgroundColor = .clear
        }
        .onDisappear {
            UITextView.appearance().backgroundColor = nil
        }
    }
}
