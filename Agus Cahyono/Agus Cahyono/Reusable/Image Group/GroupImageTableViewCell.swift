//
//  GroupImageTableViewCell.swift
//  Agus Cahyono
//
//  Created by Agus Cahyono on 27/04/19.
//  Copyright © 2019 Agus Cahyono. All rights reserved.
//

import UIKit

protocol GroupImageDelegate {
    func didSelectedOn(_ index: Int, image: UIImage?)
}

class GroupImageTableViewCell: UITableViewCell {
    
    @IBOutlet weak var gridImage: DynamicCollectionView!
    
    var countData = 0
    
    class var reusableIndentifer: String { return String(describing: self) }
    
    static func reUse() -> UINib {
        return UINib(nibName: self.reusableIndentifer, bundle: Bundle.main)
    }
    
    var delegate: GroupImageDelegate?
    

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        gridImage.dataSource = self
        gridImage.delegate = self
        
        gridImage.register(GridImageCollectionViewCell.reUse(), forCellWithReuseIdentifier: GridImageCollectionViewCell.reusableIndentifer)
        
//        gridImage.backgroundColor = .clear
        gridImage.isUserInteractionEnabled = true
        gridImage.showsHorizontalScrollIndicator = false
        gridImage.showsVerticalScrollIndicator =  false
        gridImage.isScrollEnabled = false
        gridImage.clipsToBounds = true
        gridImage.contentInset = UIEdgeInsets.init(top: 0, left: 8, bottom: 0, right: 8)
        
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}



extension GroupImageTableViewCell: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        let padding: CGFloat =  18
        let collectionViewSize = collectionView.frame.size.width - padding
        
        return CGSize(width: collectionViewSize / 2, height: collectionViewSize/2)
        
    }
    
}

extension GroupImageTableViewCell: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       return countData
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        let colls = collectionView.dequeueReusableCell(withReuseIdentifier: GridImageCollectionViewCell.reusableIndentifer, for: indexPath) as! GridImageCollectionViewCell
        
        return colls
        
    }
    
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.delegate?.didSelectedOn(indexPath.row, image: UIImage(named: "me"))
    }
    
}
