//
//  QuestionRangePickerView.swift
//  Survey
//
//  Created by Bliss on 2/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionRangePickerView: View {

    @State var selectedId: String = ""
    @Binding var answers: [SurveyAnswer]

    let options: [Answer]
    let helpText: String

    var currentIndex: Int? {
        options.firstIndex { $0.id == selectedId }
    }

    var body: some View {
        VStack(spacing: .lineSpacing) {
            GeometryReader { geo in
                let itemSize = geo.size.width / CGFloat(options.count)
                HStack(spacing: 0.0) {
                    ForEach(Array(options.enumerated()), id: \.element.id) { index, option in
                        Button {
                            selectedId = option.id
                        } label: {
                            Text(option.text.string)
                                .font(
                                    isHighlight(for: index)
                                        ? .boldLarge
                                        : .regularLarge
                                )
                                .foregroundColor(Color.white)
                        }
                        .frame(width: itemSize)
                        .opacity(isHighlight(for: index) ? 1.0 : 0.5)
                        if index != options.count - 1 {
                            Divider()
                                .frame(minWidth: 0.5)
                                .overlay(Color.white)
                        }
                    }
                }
            }
            .frame(height: 56.0)
            .cornerRadius(10.0)
            .overlay(RoundedRectangle(cornerRadius: 10.0).stroke(.white, lineWidth: 1.0))

            HStack {
                Spacer()
                Text(helpText)
                    .foregroundColor(Color.white)
                    .font(.boldBody)
            }
        }
        .onChange(of: selectedId) {
            answers = [.init(id: $0, answer: nil)]
        }
    }

    func isHighlight(for index: Int) -> Bool {
        index <= (currentIndex ?? -1)
    }
}
