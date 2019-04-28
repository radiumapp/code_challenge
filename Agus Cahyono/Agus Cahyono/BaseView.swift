//
//  BaseView.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

class BaseView: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

}

extension Date {
    
    func toMillis() -> Int64! {
        return Int64(self.timeIntervalSince1970 * 1000)
    }
    
    func backToDate(milliseconds: Int64) -> Date {
        return Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
    }
    
    func backToDateFromString(string: String) -> Date {
        if let convertString = Int64(string) {
            return Date(timeIntervalSince1970: TimeInterval(convertString))
        } else {
            return Date()
        }
    }
    
    func toString( dateFormat format  : String ) -> String
    {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        return dateFormatter.string(from: self)
    }
    
    func toString(milliseconds:Int64) -> String {
        let millisDate = Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
        return millisDate.toString(dateFormat: "MMMM dd")
    }
}
