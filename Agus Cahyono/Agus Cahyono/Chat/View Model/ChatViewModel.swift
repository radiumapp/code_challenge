//  
//  ChatViewModel.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import Foundation

class ChatViewModel {

    private let service: ChatServiceProtocol

    private var model: [Chat] = [Chat]() {
        didSet {
            self.count = self.model.count
        }
    }

    /// Count your data in model
    var count: Int = 0
    
    // Get single data
    func chat(_ index: Int) -> Chat {
        return self.model[index]
    }

    //MARK: -- UI Status

    /// Update the loading status, use HUD or Activity Indicator UI
    var isLoading: Bool = false {
        didSet {
            self.updateLoadingStatus?()
        }
    }

    /// Showing alert message, use UIAlertController or other Library
    var alertMessage: String? {
        didSet {
            self.showAlertClosure?()
        }
    }

    /// Define selected model
    var selectedObject: Chat?

    //MARK: -- Closure Collection
    var showAlertClosure: (() -> ())?
    var updateLoadingStatus: (() -> ())?
    var didGetData: (() -> ())?

    init(withChat serviceProtocol: ChatServiceProtocol = ChatService() ) {
        self.service = serviceProtocol
    }
    
    func didFetchChat(completion: @escaping () -> Void) {
        self.isLoading = true
        self.service.didFetchChat(success: { chats in
            self.isLoading = false
            if let data = chats.data {
                self.model = data
                
                var mock = [MockChat]()
                for i in data {
                    let mocks = MockChat(id: i.id, timestamp: i.timestamp, body: i.body, attachment: i.attachment, from: i.from, to: i.to)
                    
                    if !mock.contains(mocks) {
                        mock.append(mocks)
                    }
                    
                }
                
                print("DDD \(mock)")
                
            }
            completion()
        }) { error in
            self.isLoading = false
            self.alertMessage = error
        }
    }

}
