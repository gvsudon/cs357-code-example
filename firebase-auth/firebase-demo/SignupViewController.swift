//
//  SignupViewController.swift
//  firebase-demo
//
//  Created by Hans Dulimarta on 2/21/23.
//

import UIKit
import FirebaseAuth

class SignupViewController: UIViewController {

    @IBOutlet weak var userEmail: UITextField!
    @IBOutlet weak var userPassword1: UITextField!
    @IBOutlet weak var userPassword2: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.

    }
    
    @IBAction func doSignup(_ sender: Any) {
        guard let u = self.userEmail.text, let p1 = self.userPassword1.text, let p2 = self.userPassword2.text else {
            return
        }
        if p1 == p2 {
            Auth.auth().createUser(withEmail: u, password: p1) {authResult, error in
                if let e = error {
                    self.showError(msg: e.localizedDescription)
                } else {
                    print("New account created")
                    self.performSegue(withIdentifier: "signup2main", sender: self)
                }
            }
        } else {
            self.showError(msg: "Passwords must match")
        }
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
