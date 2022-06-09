/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyage.entities;


import java.util.EnumSet;

/**
 *
 * @author siwar
 */
/*public enum Vue {
    Sur_mer, Sur_piscine, Normale;
}*/
public class Vue {
    public enum Veus{
        Sur_mer, Sur_piscine, Normale;
    }
    public static Veus find(String abbr){
 
    for(Veus v :EnumSet.allOf(Veus.class)){
        if( v.toString().equals(abbr)){
            return v;
        }
    }
    return null;
}
}
