package com.example.aalizade.mbazar_base_app.utility;

/**
 * Created by aalizade on 4/10/2018.
 */
public class PaymentOptionsTranslator {
    public String Translate(String title){
        String temp = "";
        switch(title){
            case "SPECIALUSERCREDIT":temp = "اعتبارات خاص";break;
            case "SPECIALUSERGIFT":temp = "بن های هدیه خاص";break;
            case "PRODUCTCREDIT":temp = "نسیه های تامین کالا";break;
            case "WORKCREDIT":temp = "نسیه های کار و زندگی";break;
            case "CREDITFACILITY":temp = "نسیه اقساط";break;
            case "CREDIT":temp = "نسیه";break;
            case "USERGIFT":temp = "بن هدیه";break;
            case "DONATEUSERCREDIT":temp = "اعتبارات کاربری اهدایی";break;
            case "SELFUSERCREDIT":temp = "اعتبارات کاربری شخصی";break;
            case "BANKGATEWAY":temp = "نقد درگاه بانک";break;
            default:temp = "تعریف نشده";
        }
        return temp;
    }
}
