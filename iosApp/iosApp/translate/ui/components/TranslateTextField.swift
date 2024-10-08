//
//  TranslateTextField.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 27/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import UniformTypeIdentifiers

struct TranslateTextField: View {
    @Binding var fromText: String
    var toText: String?
    var isTranslating: Bool
    var fromLang: LanguageDecorator
    var toLang: LanguageDecorator
    var onTranslateEvent: (TranslateEvent) -> Void
    
    var body: some View {
        let isTranslated = if toText != nil {
            if toText!.isEmpty{
               false
            } else {
                true
            }
        } else {
            false
        }
        if !isTranslated || isTranslating {
            IdleTextField(
                fromText: $fromText,
                isTranslating: isTranslating,
                onTranslateEvent: onTranslateEvent
            )
            .gradientSurface()
            .cornerRadius(15)
            .animation(.easeInOut, value: isTranslating)
            .shadow(radius: 4)
        } else {
            TranslatedTextView(
                fromText: fromText,
                toText: toText ?? "",
                fromLanguage: fromLang,
                toLanguage: toLang,
                onTranslatEvent: onTranslateEvent
            )
            .padding()
            .gradientSurface()
            .cornerRadius(15)
            .animation(.easeInOut, value: isTranslating)
            .shadow(radius: 4)
            .onTapGesture {
                onTranslateEvent(TranslateEvent.EditTranslation())
            }
        }
    }
}

#Preview {
    TranslateTextField(
        fromText: Binding(
            get : {"Hola"},
            set: { value in }
        ),
        toText: "Hi",
        isTranslating: false,
        fromLang: LanguageDecorator.companion.byCode(langCode: "es"),
        toLang: LanguageDecorator.companion.byCode(langCode: "en"),
        onTranslateEvent: { event in }
    )
}

#Preview {
    TranslateTextField(
        fromText: Binding(
            get : {"Hola"},
            set: { value in }
        ),
        toText: "Hi",
        isTranslating: false,
        fromLang: LanguageDecorator.companion.byCode(langCode: "es"),
        toLang: LanguageDecorator.companion.byCode(langCode: "en"),
        onTranslateEvent: { event in }
    )
}


private extension TranslateTextField {
    
    struct IdleTextField: View {
        @Binding var fromText: String
        var isTranslating: Bool
        var onTranslateEvent: (TranslateEvent) -> Void
        
        var body: some View{
            TextEditor(text: $fromText)
                .frame(
                    maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,
                    minHeight: 200,
                    alignment: .topLeading
                )
                .padding()
                .foregroundColor(Color.onSurface)
                .overlay(alignment: .bottomTrailing){
                    ProgressButton(
                        text: "Translate",
                        isLoading: isTranslating,
                        onClick: {
                            onTranslateEvent(TranslateEvent.Translate())
                        }
                    )
                    .padding(.trailing)
                    .padding(.bottom)
                }
                .onAppear{
                    UITextView.appearance().backgroundColor = .clear
                }
        }
    }
    
    struct TranslatedTextView: View {
        let fromText: String
        let toText: String
        let fromLanguage: LanguageDecorator
        let toLanguage: LanguageDecorator
        let onTranslatEvent: (TranslateEvent) -> Void
        
        private let tts = TextToSpeech()
        
        var body: some View {
            VStack(alignment: .leading, content: {
                LanguageDisplay(language: fromLanguage)
                Text(fromText)
                    .foregroundColor(.onSurface)
                HStack {
                    Spacer()
                    Button(action: {
                        UIPasteboard.general.setValue(
                            fromText,
                            forPasteboardType: UTType.plainText.identifier
                        )
                    }) {
                            Image(uiImage: UIImage(named: "copy")!)
                                .renderingMode(/*@START_MENU_TOKEN@*/.template/*@END_MENU_TOKEN@*/)
                                .foregroundColor(.lightBlue)
                    }
                    Button(action: {
                        onTranslatEvent(TranslateEvent.CloseTranslation())
                    }) {
                            Image(systemName: "xmark")
                                .foregroundColor(.lightBlue)
                    }
                }
                Divider()
                    .padding()
                LanguageDisplay(language: toLanguage)
                    .padding(.bottom)
                Text(toText)
                    .foregroundColor(.onSurface)
                HStack {
                    Spacer()
                    Button(action: {
                        UIPasteboard.general.setValue(
                            toText,
                            forPasteboardType: UTType.plainText.identifier
                        )
                    }) {
                        Image(uiImage: UIImage(named: "copy")!)
                            .renderingMode(.template)
                            .foregroundColor(.lightBlue)
                        }
                        Button(action: {
                            tts.speak(
                                text: toText,
                                language: toLanguage.language.langCode
                            )
                        }) {
                            Image(systemName: "speaker.wave.2")
                                .foregroundColor(.lightBlue)
                        }
                    }
            })
        }
    }
}
