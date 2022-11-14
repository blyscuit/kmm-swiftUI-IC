//
//  View+HideBackButtonTitle.swift
//  Survey
//
//  Created by Bliss on 27/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

extension View {

    func hideBackButtonTitle() -> some View {
        return navigationTitle("")
    }
}
