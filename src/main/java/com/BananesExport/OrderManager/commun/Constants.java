package com.BananesExport.OrderManager.commun;

public class Constants {
    private Constants() throws IllegalAccessException {throw new IllegalAccessException();}
    public static int MAX_BANANAS_QUANTITY = 10000;
    public static double BANANAS_PRICE_KG = 2.5;
    public static String  INVALIDE_BANANAS_QUANTITY_MESSAGE = "Banana quantity should be between 0 and 10000, and must be a multiple of 25";


}
