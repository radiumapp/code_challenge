//  
//  ChatServiceProtocol.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import Foundation

protocol ChatServiceProtocol {

    
    /// DID FETCH CHAT DATA
    ///
    /// - Parameters:
    ///   - success: success response with json data of chat
    ///   - failure: failure with alert message response
    func didFetchChat(success: @escaping(_ chat: Chats) -> Void, failure: @escaping(String) -> Void)
    
}
