//
//  TranslateScreen.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 23/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TranslateScreen: View {
    private var historyRepository: HistoryRepository
    private var translateUseCase: Translate
    @ObservedObject var viewModel: IOSTranslateViewModel
    
    init(historyRepository: HistoryRepository, translateUseCase: Translate) {
        self.historyRepository = historyRepository
        self.translateUseCase = translateUseCase
        self.viewModel = IOSTranslateViewModel(
            historyRepository: historyRepository,
            translateUseCase: translateUseCase
        )
    }
    
    var body: some View {
        ZStack {
            List {
                HStack(alignment: .center) {
                    LanguageDropDown(
                        language: viewModel.state.fromLanguage,
                        isOpen: viewModel.state.isChoosingFromLanguage,
                        selectLanguage: { language in
                            viewModel.onEvent(event: TranslateEvent.ChooseFromLanguage(language: language))
                        }
                    )
                    Spacer()
                    SwapLanguageButton(
                        onClick: {
                            viewModel.onEvent(event: TranslateEvent.SwapLanguages())
                        }
                    )
                    Spacer()
                    LanguageDropDown(
                        language: viewModel.state.toLanguage,
                        isOpen: viewModel.state.isChoosingToLanguage,
                        selectLanguage: { language in
                            viewModel.onEvent(event: TranslateEvent.ChooseToLanguage(language: language))
                        }
                    )
                }
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
                
                TranslateTextField(
                    fromText: Binding(
                        get: {
                            viewModel.state.fromText
                        },
                        set: { value in
                            viewModel.onEvent(event: TranslateEvent.ChangeTranslation(text: value))
                        }
                    ),
                    toText: viewModel.state.toText,
                    isTranslating: viewModel.state.isTranslating,
                    fromLang: viewModel.state.fromLanguage,
                    toLang: viewModel.state.toLanguage,
                    onTranslateEvent: { viewModel.onEvent(event: $0) }
                )
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
                
                if !viewModel.state.history.isEmpty {
                    Text("History")
                        .font(.title)
                        .bold()
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .listRowSeparator(.hidden)
                        .listRowBackground(Color.background)
                }
                
                ForEach(viewModel.state.history, id: \.self.id){ item in
                    TranslateHistoryItem(
                        item: item,
                        onClick: {
                            viewModel.onEvent(
                                event: TranslateEvent.SelectHistoryItem(item: item)
                            )
                        }
                    )
                }
                .listRowSeparator(.hidden)
                .listRowBackground(Color.background)
            
            }
            .listStyle(.plain)
            .buttonStyle(.plain)
            
            
            VStack{
                Spacer()
                NavigationLink(
                    destination: Text("Voice to text screen")
                ){
                    ZStack {
                        Circle()
                            .foregroundColor(.primaryColor)
                            .padding()
                        Image(uiImage: UIImage(named: "mic")!)
                            .foregroundColor(.onPrimary)
                    }
                    .frame(maxWidth: 100, maxHeight: 100)
                }
            }
            
        }.onAppear{
            viewModel.startObserving()
        }.onDisappear{
            viewModel.dispose()
        }
    }
}

//#Preview {
//    TranslateScreen()
//}
