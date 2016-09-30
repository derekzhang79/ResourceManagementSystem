package com.gits.rms.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PayPalReferenceTrans {
	
	static String gv_APIEndpoint;
    static String gv_APIUserName;
    static String gv_APIPassword;
    static String gv_APISignature;
    static String gv_BNCode;
    static String gv_Version;
    static String gv_nvpHeader;   
    static String PAYPAL_URL;
    static String amt = "0.00"; 
	
	public static String toGetLogindetail() {
       /* gv_APIUserName = Util.getPaypalSellerUname();
        gv_APIPassword = Util.getPaypalSellerpswd();
        gv_APISignature = Util.getPaypalSellerAPIcrdt();
        */
		gv_APIUserName = "rooster12-facilitator_api1.gmail.com";
		gv_APIPassword = "5MZ2GS4S3ZK66ZXS";
		gv_APISignature = "AQU0e5vuZCvSg-XJploSa.sGUDlpAgIle5i-88i6k3f6EqsVHUBErLED";
        gv_BNCode = "PP-ECWizard";
        gv_Version = "109";
        /*gv_APIEndpoint = Util.getPaypalEndpointurl();
        PAYPAL_URL = Util.getPaypalUrl();*/
        gv_APIEndpoint = "https://api-3t.sandbox.paypal.com/nvp";
        PAYPAL_URL = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&token=";
        System.out.println(gv_APIUserName + gv_APIPassword + gv_APISignature+ gv_APIEndpoint);

        return "";
    }

   /* public static HashMap callDoDirectPAyment(String amt, String crnccode, String pyaction, String successurl, String cancelurl,
            String descroption, String accno, String crdittype, String cvv2, String fname, String lname, String strt, String city, String state, String zip, String cntrycode, String crncycode, String expdate) {
        String toGetLogindetail = toGetLogindetail();
        String nvpstr = "&TRXTYPE=S&ACTION=X&DESC=" + descroption + "&PAYMENTACTION=" + pyaction + "&AMT=" + amt + "&ACCT=" + accno + "&CREDITCARDTYPE=" + crdittype + "&cvv2=" + cvv2 + "&FIRSTNAME=" + fname + "&LASTNAME=" + lname + "&STREET=" + strt + "&STATE=" + state + "&CITY=" + city + "&ZIP=" + zip + "&COUNTRYCODE=" + cntrycode + "&CURRENCYCODE=" + crncycode + "&EXPDATE=" + expdate;
        HashMap nvp = httpcall("DoDirectPayment", nvpstr);
        return nvp;
    }*/

    public HashMap CallShortcutExpressCheckout(String paymentAmount, String currencyCodeType, String paymentType,
            String returnURL, String cancelURL, String imgurl, String desc) {
        /*session.setAttribute("paymentType", paymentType);
        session.setAttribute("currencyCodeType", currencyCodeType);*/
//        String nvpstr = "&DESC=" + desc + "&AMT=" + paymentAmount + "&L_NUMBER0=1&L_QTY0=1&L_AMT0=" + paymentAmount + "&L_NAME0=Premium Subscription&NOSHIPPING=0&ALLOWNOTE=0&SOLUTIONTYPE=Sole&BRANDNAME=www.giggzo.com&PAYMENTACTION=" + paymentType + "&ITEMAMT=" + paymentAmount + "&RETURNURL=" + URLEncoder.encode(returnURL) + "&CANCELURL=" + URLEncoder.encode(cancelURL) + "&CURRENCYCODE=" + currencyCodeType + "";
        String nvpstr = "&TRXTYPE=S&ACTION=X&DESC=" + desc + "&AMT=" + paymentAmount + "&L_NUMBER0=&L_QTY0=&L_AMT0=&L_NAME0=&NOSHIPPING=0&ALLOWNOTE=0&SOLUTIONTYPE=Sole&BRANDNAME=www.giggzo.com&PAYMENTACTION=" + paymentType + "&ITEMAMT=" + paymentAmount + "&RETURNURL=" + URLEncoder.encode(returnURL) + "&CANCELURL=" + URLEncoder.encode(cancelURL) + "&CURRENCYCODE=" + currencyCodeType + "";
        nvpstr += "&L_BILLINGTYPE0=MerchantInitiatedBillingSingleAgreement";
        HashMap nvp = httpcall("SetExpressCheckout", nvpstr);
        String strAck = nvp.get("ACK").toString();
        if (strAck != null && strAck.equalsIgnoreCase("Success")) {
            System.out.println("\nToken :::" + nvp.get("TOKEN").toString());
           // session.setAttribute("TOKEN", nvp.get("TOKEN").toString());
        }
        return nvp;
    }

    public static HashMap GetShippingDetails(String token) {
        /*
         * Build a second API request to PayPal, using the token as the ID to
         * get the details on the payment authorization
         */
        String nvpstr = "&TOKEN=" + token;
        HashMap nvp = httpcall("GetExpressCheckoutDetails", nvpstr);
        String strAck = nvp.get("ACK").toString();
        if (strAck != null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
            //session.setAttribute("PAYERID", nvp.get("PAYERID").toString());
        }
        System.out.println("\nGetShippingDetails ::: " + nvp);
        return nvp;
    }

    public static HashMap ConfirmPaymentMulti(String token, String finalPaymentAmount, String currencyCodeType, String paymentType, String payerID, String serverName) {
        String nvpstr = "&TOKEN=" + token + "&PAYERID=" + payerID + "&PAYMENTREQUEST_0_PAYMENTACTION=" + paymentType + "&PAYMENTREQUEST_0_AMT=" + finalPaymentAmount;
        nvpstr = nvpstr + "&PAYMENTREQUEST_0_CURRENCYCODE=" + currencyCodeType + "&IPADDRESS=" + serverName;
        HashMap nvp = httpcall("DoExpressCheckoutPayment", nvpstr);
        return nvp;
    }

    public static HashMap callreferencetransaction(String pyaction, String amt, String crcode, String rfid) {
        toGetLogindetail();
        String nvpst = "&PAYMENTACTION=" + pyaction + "&AMT=" + amt + "&CURRENCYCODE=" + crcode + "&REFERENCEID=" + rfid;
        HashMap hmapp = httpcall("DoReferenceTransaction", nvpst);
        return hmapp;
    }

    public static HashMap updateReferenceTrans(String rfid) {
        String nvpst = "&REFERENCEID=" + rfid + "&BILLINGAGREEMENTSTATUS=Canceled";
        HashMap hmapp = httpcall("BillAgreementUpdate", nvpst);
        return hmapp;
    }

    public static HashMap httpcall(String methodName, String nvpStr) { 
        System.out.println("httpcall methodName  : "+methodName);
        System.out.println("httpcall nvpStr  : "+nvpStr);
        String respText = "";
        HashMap nvp = null;
        String encodedData = "METHOD=" + methodName + "&VERSION=" + gv_Version + "&PWD=" + gv_APIPassword + "&USER=" + gv_APIUserName + "&SIGNATURE=" + gv_APISignature + nvpStr;
        System.out.println("\nencodedData : " + encodedData);
        try {
            URL postURL = new URL(gv_APIEndpoint);
        	//URL postURL = new URL("https://svcs.sandbox.paypal.com/AdaptivePayments/Pay");
            HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();
            // Set connection parameters. We need to perform input and output, 
            // so set both as true. 
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // Set the content type we are POSTing. We impersonate it as 
            // encoded form data 
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.setRequestMethod("POST");
            // get the output stream to POST to. 
            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(encodedData);
            output.flush();
            output.close();
            // Read input from the input stream.
            DataInputStream in = new DataInputStream(conn.getInputStream());
            int rc = conn.getResponseCode();
            if (rc != -1) {
                BufferedReader is = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String _line = null;
                while (((_line = is.readLine()) != null)) {
                    respText = respText + _line;
                }
                System.out.println("\nrespText ::::::" + respText);
                nvp = deformatNVP(respText);
            }
            return nvp;
        } catch (IOException e) {
            // handle the error here
            return null;
        }
    }

    public static HashMap deformatNVP(String pPayload) {
        HashMap nvp = new HashMap();
        StringTokenizer stTok = new StringTokenizer(pPayload, "&");
        while (stTok.hasMoreTokens()) {
            StringTokenizer stInternalTokenizer = new StringTokenizer(stTok.nextToken(), "=");
            if (stInternalTokenizer.countTokens() == 2) {
                String key = URLDecoder.decode(stInternalTokenizer.nextToken());
                String value = URLDecoder.decode(stInternalTokenizer.nextToken());
                nvp.put(key.toUpperCase(), value);
            }
        }
        System.out.println("nvp : "+nvp);
        return nvp;
    }

    /**
     * *******************************************************************************
     * RedirectURL: Function to redirect the user to the PayPal site token is
     * the parameter that was returned by PayPal returns a HashMap object
     * containing all the name value pairs of the string.
     * *******************************************************************************
     */
   /* public void RedirectURL(String token) {
        String payPalURL = PAYPAL_URL + token;
        System.out.println("payPalURL : "+payPalURL);
        System.out.println((String) session.getAttribute("candidateid"));
        System.out.println("-------------RedirectURL----block");
        response1.setStatus(302);
        response1.setHeader("Location", payPalURL);
        response1.setHeader("Connection", "close");
    }
*/
   /* public static HashMap ConfirmPayment(String finalPaymentAmount) {
        String token = (String) session.getAttribute("TOKEN");
        String currencyCodeType = (String) session.getAttribute("currencyCodeType");
        String paymentType = (String) session.getAttribute("paymentType");
        String payerID = (String) session.getAttribute("PAYERID");
        String serverName = request1.getServerName();
        String nvpstr = "&TOKEN=" + token + "&PAYERID=" + payerID + "&PAYMENTACTION=" + paymentType + "&AMT=" + finalPaymentAmount;
        nvpstr = nvpstr + "&CURRENCYCODE=" + currencyCodeType + "&IPADDRESS=" + serverName;
        HashMap nvp = httpcall("DoExpressCheckoutPayment", nvpstr);
        return nvp;
    }*/
    public HashMap CallMarkExpressCheckout(String paymentAmount, String currencyCodeType, String paymentType, String returnURL,
            String cancelURL, String shipToName, String shipToStreet, String shipToCity, String shipToState,
            String shipToCountryCode, String shipToZip, String shipToStreet2, String phoneNum) {

        //session.setAttribute("paymentType", paymentType);
        //session.setAttribute("currencyCodeType", currencyCodeType);
        /*  
         * Construct the parameter string that describes the PayPal payment the
         * varialbes were set in the web form, and the resulting string is
         * stored in $nvpstr
         */     
        String nvpstr = "ADDROVERRIDE=1&Amt=" + paymentAmount + "&PAYMENTACTION=" + paymentType;
        nvpstr = nvpstr + "&CURRENCYCODE=" + currencyCodeType + "&ReturnUrl=" + URLEncoder.encode(returnURL) + "&CANCELURL=" + URLEncoder.encode(cancelURL);
        nvpstr = nvpstr + "&SHIPTONAME=" + shipToName + "&SHIPTOSTREET=" + shipToStreet + "&SHIPTOSTREET2=" + shipToStreet2;
        nvpstr = nvpstr + "&SHIPTOCITY=" + shipToCity + "&SHIPTOSTATE=" + shipToState + "&SHIPTOCOUNTRYCODE=" + shipToCountryCode;
        nvpstr = nvpstr + "&SHIPTOZIP=" + shipToZip + "&PHONENUM" + phoneNum;

        /*
         * Make the call to PayPal to set the Express Checkout token If the API
         * call succeded, then redirect the buyer to PayPal to begin to
         * authorize payment. If an error occured, show the resulting errors
         */

        HashMap nvp = httpcall("SetExpressCheckout", nvpstr);
        String strAck = nvp.get("ACK").toString();
       /* if (strAck != null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
            session.setAttribute("TOKEN", nvp.get("token").toString());
        }*/
        return nvp;
    }
    
    public static HashMap makePayment(String payPalEmail,String currenyCode, String paymentAmount,String returnUrl, String cancelUrl,String preApprovalKey){
    	String endPoint = "https://svcs.sandbox.paypal.com/AdaptivePayments/" + "Pay";
    	String senderEmail = "rooster12-facilitator@gmail.com";
    	String nvpstr = "actionType=PAY&cancelUrl="+cancelUrl+"&currencyCode="+currenyCode+"&senderEmail="+senderEmail+"&preapprovalKey="+preApprovalKey+"&receiverList.receiver(0).email="+payPalEmail+"&receiverList.receiver(0).amount="+paymentAmount+"&requestEnvelope.errorLanguage=en_US&returnUrl="+returnUrl;
    	HashMap nvp = httpcallToMakePayment(endPoint, nvpstr);
    	System.out.println("nvp :" + nvp);
    	return nvp;
    }
    
    public static HashMap httpcallToMakePayment(String endPoint, String nvpStr) { 
        String respText = "";
        HashMap nvp = null;
        String encodedData = nvpStr;
        System.out.println("\nencodedData : " + encodedData);
        try {
            //URL postURL = new URL(gv_APIEndpoint);
        	//String endPoint = PropertyUtil.getPropoerty("paypal.adaptive.payment.endpoint");
        	String applicationId = "APP-80W284485P519543T";
        	URL postURL = new URL(endPoint);
            HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();
            // Set connection parameters. We need to perform input and output, 
            // so set both as true. 
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // Set the content type we are POSTing. We impersonate it as 
            // encoded form data 
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            conn.setRequestProperty("X-PAYPAL-SECURITY-USERID", gv_APIUserName);
            conn.setRequestProperty("X-PAYPAL-SECURITY-PASSWORD",gv_APIPassword);
            conn.setRequestProperty("X-PAYPAL-SECURITY-SIGNATURE", gv_APISignature);
            conn.setRequestProperty("X-PAYPAL-APPLICATION-ID", applicationId);
            conn.setRequestProperty("X-PAYPAL-REQUEST-DATA-FORMAT", "NV");
            conn.setRequestProperty("X-PAYPAL-RESPONSE-DATA-FORMAT", "NV");
            conn.setRequestMethod("POST");
            // get the output stream to POST to. 
            DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            output.writeBytes(encodedData);
            output.flush();
            output.close();
            // Read input from the input stream.
            DataInputStream in = new DataInputStream(conn.getInputStream());
            int rc = conn.getResponseCode();
            if (rc != -1) {
                BufferedReader is = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String _line = null;
                while (((_line = is.readLine()) != null)) {
                    respText = respText + _line;
                }
                System.out.println("\nrespText ::::::" + respText);
                nvp = deformatNVP(respText);
            }
            return nvp;
        } catch (IOException e) {
            // handle the error here
            return null;
        }
    }
    
    public static HashMap validatePayment(String payKey){
    	String endPoint = "https://svcs.sandbox.paypal.com/AdaptivePayments/" + "PaymentDetails";
    	String nvpstr = "requestEnvelope.errorLanguage=en_US&payKey="+ payKey ;
    	HashMap nvp = httpcallToMakePayment(endPoint, nvpstr);
    	System.out.println("nvp :" + nvp);
    	return nvp;
    }

}
