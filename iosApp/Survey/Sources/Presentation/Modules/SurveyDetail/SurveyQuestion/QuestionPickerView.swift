//
//  QuestionPickerView.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionPickerView: View {

    @State private var selectedId = ""
    @Binding var answers: [SurveyAnswer]

    let options: [Answer]

    var body: some View {
        Picker(String(describing: Self.self), selection: $selectedId) {
            ForEach(Array(options.enumerated()), id: \.element.id) { index, option in
                let font: Font = selectedId == option.id ? .boldLarge : .regularLarge
                Text(option.text)
                    .font(font)
                    .foregroundColor(Color.white)
                if index != options.count - 1 {
                    Divider()
                        .overlay(Color.white)
                        .frame(width: 215.0, height: 1.0)
                }
            }
        }
        .pickerStyle(.wheel)
        .onChange(of: selectedId) {
            answers = [.init(id: $0, answer: nil)]
        }
    }
}
