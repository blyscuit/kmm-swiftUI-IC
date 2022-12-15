//
//  SurveyLoading.swift
//  Survey
//
//  Created by Bliss on 4/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

struct SurveyLoading: View {

    var body: some View {
        VStack {}
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color.black.ignoresSafeArea())
        VStack {
            SurveyHeaderLoading()
            SurveyItemLoading()
        }
        .padding(.horizontal, .smallPadding)
        .accessibility(.surveyLoading(.view))
    }
}
