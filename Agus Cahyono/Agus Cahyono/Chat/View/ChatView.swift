//  
//  ChatView.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

class ChatView: UIViewController {

    // OUTLETS HERE
    @IBOutlet weak var tableView: UITableView!

    // VARIABLES HERE
    var viewModel = ChatViewModel()
    var imageCounter = 0
    var count: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        tableView.separatorStyle = .none
        tableView.contentInset = UIEdgeInsets(top: 0, left: 0, bottom: 100, right: 0)
        tableView.keyboardDismissMode = .onDrag
        
        tableView.register(GroupImageTableViewCell.reUse(), forCellReuseIdentifier: GroupImageTableViewCell.reusableIndentifer)
        tableView.register(GroupImageMessageOtherCell.reUse(), forCellReuseIdentifier: GroupImageMessageOtherCell.reusableIndentifer)
        tableView.register(MessageCell.reUse(), forCellReuseIdentifier: MessageCell.reusableIndentifer)
        tableView.register(ImageMessageCell.reUse(), forCellReuseIdentifier: ImageMessageCell.reusableIndentifer)
        tableView.register(DocumentMessageCell.reUse(), forCellReuseIdentifier: DocumentMessageCell.reusableIndentifer)
        tableView.register(ContactMessageCell.reUse(), forCellReuseIdentifier: ContactMessageCell.reusableIndentifer)
        tableView.register(ContactGroupMessageCell.reUse(), forCellReuseIdentifier: ContactGroupMessageCell.reusableIndentifer)
        tableView.register(ContactGroupMessageOtherCellTableViewCell.reUse(), forCellReuseIdentifier: ContactGroupMessageOtherCellTableViewCell.reusableIndentifer)
        
        
        self.generateHeader()
        self.setupViewModel()
    }
    
    
    func generateHeader() {
        
        let containView = UIView(frame: CGRect(x: 0, y: 0, width: navigationController?.navigationBar.frame.width ?? 0.0, height: navigationController?.navigationBar.frame.height ?? 0.0))
        let imageview = UIImageView(frame: CGRect(x: 0, y: 0, width: 40, height: 40))
        
        
        imageview.contentMode = UIView.ContentMode.scaleAspectFill
        imageview.layer.cornerRadius = 20
        imageview.layer.masksToBounds = true
        imageview.clipsToBounds = true
        imageview.image = UIImage(named: "me")
        containView.addSubview(imageview)
        
        let one = UILabel()
        one.font = UIFont.boldSystemFont(ofSize: 16)
        one.textColor = .white
        one.textAlignment = .left
        one.text = "Agus Cahyono"
        one.sizeToFit()
        
        let two = UILabel()
        two.font = UIFont.systemFont(ofSize: 12)
        two.textAlignment = .left
        two.textColor =  UIColor.white
        two.text = "Last seen today at 10:00"
        two.sizeToFit()
        
        let stackView = UIStackView(arrangedSubviews: [one, two])
        stackView.distribution = .equalSpacing
        stackView.axis = .vertical
        
        let width = max(one.frame.size.width, two.frame.size.width)
        stackView.frame = CGRect(x: imageview.bounds.size.width + 8, y: 0, width: width, height: 35)
        
        containView.addSubview(stackView)
        
        
        self.navigationItem.titleView = containView
        
        
    }
    
    fileprivate func setupViewModel() {

        self.viewModel.showAlertClosure = { [weak self] in
            let alert = self?.viewModel.alertMessage ?? ""
            print(alert)
        }
        
        self.viewModel.updateLoadingStatus = { [weak self] in
            if self?.viewModel.isLoading ?? true {
                print("LOADING...")
            } else {
                 print("DATA READY")
            }
        }
        
        self.viewModel.didFetchChat {
            self.count = self.viewModel.count
        }
        
    }
    
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
    
}


