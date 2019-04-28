//
//  ActionSheetViewController.swift
//  HiApp
//
//  Created by Agus Cahyono on 14/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

extension UIAlertAction{
    var mode : ActionSheetViewController.Mode?{
        set{
            let vc = ActionSheetViewController.viewController(with: newValue)
            self.setValue(vc, forKey: "contentViewController")
        }
        get{
            if let vc = value(forKey: "contentViewController") as? ActionSheetViewController{
                return vc.mode
            }
            
            return nil
        }
    }
}

class ActionSheetViewController: UIViewController {
    
    enum Mode{
        case camera
        case photovideo
        case document
        case location
        case contact
        
        var image : UIImage{
            get{
                switch self {
                    case .camera: return UIImage(named: "camera_chat")!
                    case .photovideo: return UIImage(named: "camera_chat")!
                    case .document: return UIImage(named: "document_chat")!
                    case .location: return UIImage(named: "document_chat")!
                    case .contact: return UIImage(named: "document_chat")!
                }
            }
        }
        
        var title : String{
            get{
                switch self {
                case .camera: return NSLocalizedString("Camera", comment: "camera")
                case .photovideo: return NSLocalizedString("Photo & Video Library", comment: "photovideo")
                case .document: return NSLocalizedString("Document", comment: "document")
                case .location: return NSLocalizedString("Location", comment: "location")
                case .contact: return NSLocalizedString("Contact", comment: "contact")
                }
            }
        }
    }
    
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var imaegView: UIImageView!

    var mode : Mode?

    override func viewDidLoad() {
        super.viewDidLoad()

        label.text = mode?.title
//        imaegView.image = mode?.image
        
    }

    class func viewController(with mode : Mode?) -> UIViewController{
        
        let vc = ActionSheetViewController(nibName: "ActionSheetViewController", bundle: Bundle.main)
        vc.mode = mode
        return vc
        
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
