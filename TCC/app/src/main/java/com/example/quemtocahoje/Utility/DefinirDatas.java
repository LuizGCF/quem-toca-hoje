package com.example.quemtocahoje.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DefinirDatas {

    //retorna data atual do sistema
    public static String dataAtual(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(c);
    }

}
