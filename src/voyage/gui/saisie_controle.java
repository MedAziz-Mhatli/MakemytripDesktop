/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.gui;

import voyage.utils.MyConnectionn;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MSI GAMMING
 */
public class saisie_controle {
    
    MyConnectionn cn =MyConnectionn.getInstance();
    
         public  boolean testnomprenom(String nom) {
		//************************ nom et prenom contiennent que des alphabets ***************************
		String masque = "^[a-zA-Z]+[a-z]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(nom);
		return controler.matches();
	}
    
}
