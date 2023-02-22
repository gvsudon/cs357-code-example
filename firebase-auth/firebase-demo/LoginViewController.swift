//
//  ViewController.swift
//  firebase-demo
//
//  Created by Hans Dulimarta on 2/21/23.
//

import UIKit
import FirebaseAuth

class LoginViewController: UIViewController {

    @IBOutlet weak var userEmail: UITextField!
    @IBOutlet weak var userPassword: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.navigationController?.delegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.userEmail.text = "hans@example.com"
        self.userPassword.text = "1234567"
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let backItem = UIBarButtonItem()
        if segue.identifier == "login2main" {
            backItem.title = "Logout"
        } else {
            backItem.title = "Cancel"

        }
        navigationItem.backBarButtonItem = backItem
    }

    @IBAction func doSignin(_ sender: Any) {
        guard let u = self.userEmail.text, let p = self.userPassword.text else {
            return
        }
        
        Auth.auth().signIn(withEmail: u, password: p) { authResult, error in
            if let e = error {
                self.showError(msg: e.localizedDescription)
            } else {
//                print("User is logged in")
                self.performSegue(withIdentifier: "login2main", sender: self)
            }
            
        }
    }
    
    @IBAction func logoutApp (segue: UIStoryboardSegue) {
        do {
            try Auth.auth().signOut()
        } catch  {
           print("Failed to logout \(error)")
        }
    }
}

extension LoginViewController: UINavigationControllerDelegate {
    func navigationController(_ navigationController: UINavigationController, willShow viewController: UIViewController, animated: Bool) {
//        var backButtonText = ""
//        if viewController is MainViewController {
//            backButtonText = "Cancel"
//        } else if viewController is SignupViewController {
//            backButtonText = "Logout"
//        }
//        let item = UIBarButtonItem(title: backButtonText, style: .plain, target: nil, action: nil)
//        viewController.navigationItem.backBarButtonItem = item
    }
}

extension UIViewController {
    func showError(msg:String) {
        let alert = UIAlertController(title: "Failed", message: msg, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default))
        self.present(alert, animated: true)
    }
}
