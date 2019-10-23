/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Phenom
 */
public interface Operation {
    //Ovde ubacis svaku operaciju koju implementiras
    public static int OPERATION_LOGIN = 1;
    public static int OPERATION_REGISTER = 2;
    public static int OPERATION_LOGOUT = 3;
    public static int OPERATION_EDIT_USER = 4;
    public static int OPERATION_SEARCH_USERS = 5;
    public static int OPERATION_DELETE_USER = 6;
    public static int OPERATION_FIND_AUTORS = 7;
    public static int OPERATION_SEARCH_AUTORS = 8;
    public static int OPERATION_NEW_AUTOR = 9;
    public static int OPERATION_DELETE_AUTOR = 10;
    public static int OPERATION_SAVE_DELO = 11;
    public static int OPERATION_SAVE_AUTOR = 12;
    public static int OPERATION_SEARCH_DELO = 13;
    public static int OPERATION_NEW_KRITIKA = 14;
    public static int OPERATION_APPROVE_KRITIKA = 15;
    public static int OPERATION_DELETE_KRITIKA = 16;
    public static int OPERATION_GET_KRITIKE = 17;
    public static int OPERATION_GET_KRITIKE_ZA_ODOBRENJE = 18;
    public static int OPERATION_DELETE_DELO = 19;
    public static int OPERATION_UPDATE_DELO = 20;
    
    
    
    
    
}
