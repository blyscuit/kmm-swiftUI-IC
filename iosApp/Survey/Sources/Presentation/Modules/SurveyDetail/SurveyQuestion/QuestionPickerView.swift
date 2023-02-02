//
//  QuestionPickerView.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct QuestionPickerView: View {

    @State private var selectedInput = ""

    let ids: [String]

    var body: some View {
        Picker(String(describing: Self.self), selection: $selectedInput) {
            ForEach(ids, id: \.self) { id in
                let font = selectedInput == id ? Font.boldLarge : Font.regularLarge
                Text(id)
                    .font(font)
                    .foregroundColor(Color.white)
                if id != ids.last {
                    Divider()
                        .overlay(Color.white)
                        .frame(width: 215.0, height: 1.0)
                }
            }
        }
        .pickerStyle(.wheel)
        .padding(20.0)
        .onChange(of: selectedInput) {
            print($0)
        }
    }
}
