//
//  MainViewController.swift
//  firebase-demo
//
//  Created by Hans Dulimarta on 2/21/23.
//

import UIKit

class MainViewController: UIViewController {

    @IBOutlet weak var userIdentity: UILabel!
    var whoami = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func doLogout(_ sender: Any) {
        self.performSegue(withIdentifier: "logout2main", sender: self)
    }
}
