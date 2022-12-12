//  swiftlint:disable:this file_name
//
//  Shared+AutoMockable.swift
//  Survey
//
//  Created by Bliss on 28/10/22.
//  Copyright © 2022 Nimble. All rights reserved.
//

import Shared

// sourcery: AutoMockable
protocol ResetPasswordUseCaseKMM: ResetPasswordUseCase {

    func invoke(email: String) -> Kotlinx_coroutines_coreFlow
    func invokeNative(email: String) -> (
        @escaping (String, KotlinUnit) -> KotlinUnit,
        @escaping (Error?, KotlinUnit) -> KotlinUnit
    ) -> () -> KotlinUnit
}

// sourcery: AutoMockable
protocol LogInUseCaseKMM: LogInUseCase {

    func invoke(email: String, password: String) -> Kotlinx_coroutines_coreFlow
    func invokeNative(email: String, password: String) -> (
        @escaping (Token, KotlinUnit) -> KotlinUnit,
        @escaping (Error?, KotlinUnit) -> KotlinUnit
    ) -> () -> KotlinUnit
}

// sourcery: AutoMockable
protocol CheckLoginUseCaseKMM: CheckLoginUseCase {

    func invoke() -> Kotlinx_coroutines_coreFlow
    func invokeNative()
        -> (
            @escaping (KotlinBoolean, KotlinUnit) -> KotlinUnit,
            @escaping (Error?, KotlinUnit) -> KotlinUnit
        ) -> () -> KotlinUnit
}
