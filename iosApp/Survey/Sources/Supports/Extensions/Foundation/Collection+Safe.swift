//
//  Collection+Safe.swift
//  Survey
//
//  Created by Bliss on 6/2/23.
//  Copyright © 2023 Nimble. All rights reserved.
//

extension Collection {

    /// Returns the element at the specified index if it is within bounds, otherwise nil.
    subscript(safe index: Index) -> Element? {
        return indices.contains(index) ? self[index] : nil
    }
}
