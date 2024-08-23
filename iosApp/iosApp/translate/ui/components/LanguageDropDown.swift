//
//  LanguageDropDown.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 23/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDropDown: View {
    var language: LanguageDecorator
    var isOpen: Bool
    var selectLanguage: (LanguageDecorator) -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(LanguageDecorator.Companion().allLanguages, id: \.self.language.langCode){ language in
                    LanguageDropDownItem(
                        language: language,
                        onClick: {
                            selectLanguage(language)
                        }
                    )
                }
            }
        } label: {
            HStack{
                SmallLanguageIcon(language: language)
                Text(language.language.langName)
                    .foregroundColor(.lightBlue)
                Image(systemName: isOpen ? "chevron.up" : "chevron.down")
                    .foregroundColor(.lightBlue)
            }
        }
    }
}

#Preview {
    LanguageDropDown(language: LanguageDecorator.companion.byCode(langCode: "es"), isOpen: true) { LanguageDecorator in
        
    }
}
