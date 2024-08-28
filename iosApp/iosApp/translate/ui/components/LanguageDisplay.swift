//
//  LanguageDisplay.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 27/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDisplay: View {
    var language: LanguageDecorator
    
    var body: some View {
        HStack {
            SmallLanguageIcon(language: language).padding(.trailing, 5)
            Text(language.language.langName)
                .foregroundColor(.lightBlue)
        }
    }
}

#Preview {
    LanguageDisplay(
        language: LanguageDecorator.companion.byCode(langCode: "es")
    )
}
