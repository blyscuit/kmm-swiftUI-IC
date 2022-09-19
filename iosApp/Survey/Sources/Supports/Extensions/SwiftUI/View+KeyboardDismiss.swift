//
//  View+KeyboardDismiss.swift
//  Survey
//
//  Created by Bliss on 19/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

extension View {

    func hideKeyboard() {
        UIApplication.shared.sendAction(
            #selector(UIResponder.resignFirstResponder),
            to: nil,
            from: nil,
            for: nil
        )
    }
}
