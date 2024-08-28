//
//  IOSTranslateViewModel.swift
//  iosApp
//
//  Created by Sebastian Cardona Henao on 23/8/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension TranslateScreen {
    @MainActor class IOSTranslateViewModel: ObservableObject {
        private var historyRepository: HistoryRepository
        private var translateUseCase: Translate
        
        private let viewModel: TranslateViewModel
        
        @Published var state: TranslateState = TranslateState(
            fromText: "",
            toText: nil,
            fromLanguage: LanguageDecorator(language: .spanish, imageName: "spanish"),
            toLanguage: LanguageDecorator(language: .english, imageName: "english"),
            isChoosingFromLanguage: false,
            isChoosingToLanguage: false,
            isTranslating: false,
            error: nil,
            history: []
        )
        
        private var handle: DisposableHandle?
        
        init(historyRepository: HistoryRepository, translateUseCase: Translate) {
            self.historyRepository = historyRepository
            self.translateUseCase = translateUseCase
            self.viewModel = TranslateViewModel(
                translate: translateUseCase,
                historyRepository: historyRepository,
                coroutineScope: nil
            )
        }
        
        
        func onEvent(event: TranslateEvent) {
            self.viewModel.onEvent(event: event)
        }
        
        func startObserving(){
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
