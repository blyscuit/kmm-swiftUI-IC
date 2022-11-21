//
//  SurveyItemLoading.swift
//  Survey
//
//  Created by Bliss on 4/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SkeletonUI
import SwiftUI

struct SurveyItemLoading: View {

    var body: some View {
        GeometryReader { geo in
            VStack(alignment: .leading, spacing: .lineSpacing) {
                SkeletonTextView(width: 40.0)
                SkeletonTextView(width: 140.0, lines: 2)
                SkeletonTextView(width: min(geo.size.width - 40.0, 300.0), lines: 2)
            }
            .offset(y: geo.size.height - 180.0)
        }
    }
}
