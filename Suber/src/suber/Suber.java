/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suber;

import suber.backend.member.registration.RegisterPaymentDetails;
import suber.backend.member.registration.RegisterUser;
import suber.backend.member.session.LoginSession;
import suber.ui.login.LoginLauncher;
import suber.backend.member.session.RegisterUserSession;

/**
 *
 * @author Andrew
 */
public class Suber {
   
    public static LoginSession session = new LoginSession();
    public static RegisterUserSession registerSession = new RegisterUserSession();
    
    public static RegisterUser registration = new RegisterUser();
    public static RegisterPaymentDetails registrationPayments = new RegisterPaymentDetails();
    
    public static void main(String[] args) throws Exception {
        LoginLauncher.main(args);
    }
    
  
}
