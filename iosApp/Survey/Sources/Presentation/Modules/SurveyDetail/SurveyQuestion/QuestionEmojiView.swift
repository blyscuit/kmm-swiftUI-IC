//
//  QuestionEmojiView.swift
//  Survey
//
//  Created by Bliss on 2/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionEmojiView: View {

    enum EmojiType: String {

        case like
        case smile
        case heart
        case star

        func items(count: Int) -> [String] {
            let items: [String]
            switch self {
            case .like:
                items = Array(repeatElement("👍🏻", count: count))
            case .smile:
                items = Array(["😡", "😕", "😐", "🙂", "😄"].prefix(count))
            case .heart:
                items = Array(repeatElement("❤️", count: count))
            case .star:
                items = Array(repeatElement("⭐️", count: count))
            }
            return items
        }
    }

    @State var rating: Int = 0
    @Binding var answers: [SurveyAnswer]

    let type: EmojiType
    let options: [Answer]

    var body: some View {
        let items = type.items(count: options.count)
        HStack(spacing: .itemSpacing) {
            Spacer()
            ForEach(0 ..< items.count, id: \.self) { index in
                Text(items[index])
                    .font(.boldTitle)
                    .opacity(getOpacity(index))
                    .onTapGesture {
                        rating = index
                    }
                    .frame(width: 28.0, height: 34.0)
                    .tag(index)
            }
            Spacer()
        }
        .onChange(of: rating) {
            answers = [.init(id: options[$0].id, answer: nil)]
        }
    }

    private func getOpacity(_ index: Int) -> Double {
        switch type {
        case .smile:
            return index == rating ? 1.0 : 0.5
        default:
            return index > rating ? 0.5 : 1.0
        }
    }
}
