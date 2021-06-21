package com.setes.setesvendas.app.util;

import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class Convert {

	public static String floatToString(Double value) {

		DecimalFormat format = new DecimalFormat("0.00");
		format.setGroupingUsed(true);
		format.setMaximumIntegerDigits(8);
		format.setMaximumFractionDigits(2);
		return format.format(value);
	}

	public static String format(Object obj){
  		String real = obj.toString();
  		String formatado = "";
  		if(real != null && real.length() > 0){
  			NumberFormat formatMoney = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
  			formatado = formatMoney.format(Double.parseDouble(real));
  			return formatado.substring(2, formatado.length());
  		}
  		return "0,00";
  	}

	public static String StrDateToStrDateBR(String value){
        //2016-04-10 to 10/04/2016
        String newDate = "";
        //day / Month / Year (substring caracter inicial e caracter final com indice iniciando em "0" zero
        newDate = value.substring(8,10) + "/";
        newDate = newDate +  value.substring(5,7) + "/";
        newDate = newDate +  value.substring(0,4);
        return newDate;
	}

	public static String StrDateToStrDateTbl(String value){
		//2016-04-10 to 10/04/2016
		String newDate = "";
		//day / Month / Year (substring caracter inicial e caracter final com indice iniciando em "0" zero
		newDate = value.substring(6,10) + "-";
		newDate = newDate +  value.substring(3,5) + "-";
		newDate = newDate +  value.substring(0,2);
		return newDate;
	}
}
