//
//  GestureVelocity.swift
//  Survey
//
//  Created by Bliss on 28/9/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import SwiftUI

@propertyWrapper
struct GestureVelocity: DynamicProperty {

    @State private var previous: DragGesture.Value?
    @State private var current: DragGesture.Value?

    var projectedValue: GestureVelocity {
        self
    }

    var wrappedValue: CGVector {
        value
    }

    private var value: CGVector {
        guard let previous = previous,
              let current = current
        else {
            return .zero
        }

        let timeDelta = current.time.timeIntervalSince(previous.time)

        let speedY = Double(
            current.translation.height - previous.translation.height
        ) / timeDelta

        let speedX = Double(
            current.translation.width - previous.translation.width
        ) / timeDelta

        return CGVector(dx: speedX, dy: speedY)
    }

    func update(_ value: DragGesture.Value) {
        if current != nil {
            previous = current
        }
        current = value
    }

    func reset() {
        previous = nil
        current = nil
    }
}

extension Gesture where Value == DragGesture.Value {

    func updatingVelocity(_ velocity: GestureVelocity) -> _EndedGesture<_ChangedGesture<Self>> {
        onChanged { value in
            velocity.update(value)
        }
        .onEnded { _ in
            velocity.reset()
        }
    }
}
