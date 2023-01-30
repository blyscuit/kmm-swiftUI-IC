//
//  SurveyQuestionView.swift
//  Survey
//
//  Created by Bliss on 11/1/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SurveyQuestionView: View {

    var body: some View {
        VStack(alignment: .leading) {
            // TODO: Use real data
            Text("1/5")
                .font(.boldMedium)
                .foregroundColor(.white)
                .opacity(0.5)
                .padding(.top, .largePadding)
                .accessibility(.surveyQuestion(.detailText))
            Text("How are you?")
                .font(.boldLarge)
                .foregroundColor(.white)
                .padding(.top, .tinyPadding)
                .accessibility(.surveyQuestion(.titleText))
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
