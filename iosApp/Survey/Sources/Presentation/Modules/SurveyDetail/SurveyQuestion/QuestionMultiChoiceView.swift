//
//  QuestionMultiChoiceView.swift
//  Survey
//
//  Created by Bliss on 2/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionMultiChoiceView: View {

    let options: [Answer]

    @State var inputs = Set<String>()
    @Binding var answers: [String]

    var body: some View {
        VStack {
            ForEach(Array(options.enumerated()), id: \.element.id) { index, option in
                let isSelected = inputs.contains(option.id)
                Button {
                    didPress(id: option.id)
                } label: {
                    HStack {
                        Text(option.text)
                            .lineLimit(1)
                            .foregroundColor(isSelected ? .white : .white.opacity(0.5))
                            .font(isSelected ? .boldLarge : .regularLarge)
                        Spacer()
                        if isSelected {
                            Image(systemName: .checkmarkCircleFill)
                                .foregroundColor(.white)
                                .font(.system(size: 24.0))
                        } else {
                            Image(systemName: .circle)
                                .foregroundColor(.white.opacity(0.5))
                                .font(.system(size: 24.0))
                        }
                    }
                }
                .frame(height: 56.0)
                if index != options.count - 1 {
                    Divider()
                        .frame(height: 0.5)
                        .overlay(Color.white)
                }
            }
        }
        .padding(.horizontal, .extraLargePadding)
    }

    func didPress(id: String) {
        if inputs.contains(id) {
            inputs.remove(id)
        } else {
            inputs.insert(id)
        }
        answers = Array(inputs)
    }
}
