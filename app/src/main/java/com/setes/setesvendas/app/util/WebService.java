package com.setes.setesvendas.app.util;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.setes.setesvendas.app.pedidovenda.MainActivity;


public class WebService {
	
	public static String METHOD_NAME = "servico";
	public static String NAMESPACE = "SETES2013.webservice";

	
	public WebService()
	{
	
	}
	
	public boolean checkInternetConnection(Context c) 
	{
		boolean HaveConnectedWifi = false;
	    boolean HaveConnectedMobile = false;
	    boolean MOBILE_MMS = false;
	    boolean MOBILE_SUPL = false;
	    boolean MOBILE_DUN = false;
	    boolean MOBILE_HIPRI = false;
	    ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo.getTypeName().equalsIgnoreCase("WIFI")) {
			if (netInfo.isConnected())
				HaveConnectedWifi = true;
		}
		if (netInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
			if (netInfo.isConnected())
				HaveConnectedMobile = true;
		}
		if (netInfo.getTypeName().equalsIgnoreCase("MOBILE_MMS")) {
			if (netInfo.isConnected())
				MOBILE_MMS = true;
		}
		if (netInfo.getTypeName().equalsIgnoreCase("MOBILE_SUPL")) {
			if (netInfo.isConnected())
				MOBILE_SUPL = true;
		}
		if (netInfo.getTypeName().equalsIgnoreCase("MOBILE_DUN")) {
			if (netInfo.isConnected())
				MOBILE_DUN = true;
		}
		if (netInfo.getTypeName().equalsIgnoreCase("MOBILE_HIPRI")) {
			if (netInfo.isConnected())
				MOBILE_HIPRI = true;
		}
	    return HaveConnectedWifi || HaveConnectedMobile || MOBILE_MMS || MOBILE_SUPL || MOBILE_DUN || MOBILE_HIPRI;
	}
		
	public String callWebService(String area, String op, String xml,String chaves)
	{
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("token", "5b79117d73sfdfsdfdf92f809a0f789");
			request.addProperty("area", area);
			request.addProperty("op", op);
			request.addProperty("xml", xml);
			request.addProperty("chaves", chaves);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(MainActivity.gbWbsPath +"?wsdl");
			androidHttpTransport.call(MainActivity.gbWbsPath, envelope);
			return (String) envelope.getResponse();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}

