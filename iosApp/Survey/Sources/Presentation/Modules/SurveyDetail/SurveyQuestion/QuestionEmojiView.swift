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

        var items: [String] {
            let items: [String]
            switch self {
            case .like:
                items = Array(repeatElement("👍🏻", count: 5))
            case .smile:
                items = ["😡", "😕", "😐", "🙂", "😄"]
            case .heart:
                items = Array(repeatElement("❤️", count: 5))
            case .star:
                items = Array(repeatElement("⭐️", count: 5))
            }
            return items
        }
    }

    @State var rating: Int = 0

    let type: EmojiType

    var body: some View {
        let items = type.items
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
            print($0)
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
