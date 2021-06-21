package com.setes.setesvendas.app.util;

import java.util.regex.Pattern;


public class Validacao {
	public static Pattern patternEmail = Pattern.compile(
			"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	          "\\@" +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	          "(" +
	          "\\." +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	          ")+"
	);
	
	public static boolean validaEmail(String email){
		return patternEmail.matcher(email).matches();
	}
}
