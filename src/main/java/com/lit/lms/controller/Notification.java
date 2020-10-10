
package com.lit.lms.controller;
import java.io.BufferedReader;

import java.io.DataOutputStream;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.InputStreamReader;

import java.io.PrintStream;

import java.net.HttpURLConnection;

import java.net.URL;



public class Notification {

      



      

      

      public static void GetPaymentNotification(String emailMsgTxtANV2, String emailSubjectTxt,String AB4, String DB5, String AB6,String AB7,String to,String from) throws FileNotFoundException {

            StringBuffer response = null;

        PrintStream o = new PrintStream(new File("ErrorLog.txt"));

        PrintStream console = System.out;



            boolean resp;

            try {

            String url = "http://10.100.5.195:1867/service.asmx?op=SendMail";

            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");

            String CC = "HCMAutomationProject@ubagroup.com";

            String sendermail = "TalentManagement@ubagroup.com";



            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + 

                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n" + 

                        "  <soap12:Body>\r\n" + 

                        "    <SendMail xmlns=\"http://tempuri.org/\">\r\n" + 

                        " <recipient>"+to+"</recipient>\r\n" + 

                        "      <sender>Talent.Management@ubagroup.com</sender>\r\n" + 

                        "      <subject>"+emailSubjectTxt+"</subject>\r\n" + 

                        "      <body><![CDATA[<span style=\"font-size:16px; font-family:century gothic;\">\r\n" + 

                        "                 \r\n  "

                        + emailMsgTxtANV2+"<br/><br/>\r\n"

                        + AB4+"<br/><br/>\r\n"

                        + " "+DB5+" <br/><br/>\r\n"

                        + ""+AB6+"<br/><br/>\r\n"

                        + ""+AB7+"<br/><br/><br/>\r\n"

                        + "<br/>"

                        + "</span>"

                        + "]]></body>\r\n" + 

                        "      <CCs>\r\n" + 

                        "        <string>"+CC+"</string>\r\n" + 

                        

                        "      </CCs>\r\n" + 

                        "    </SendMail>\r\n" + 

                        "  </soap12:Body>\r\n" + 

                        "</soap12:Envelope>";

      

 

             System.setOut(o);

            System.out.println(xml);

            System.setOut(console);

            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.writeBytes(xml);

            wr.flush();

            wr.close();

            String responseStatus = con.getResponseMessage();

            

            

            //System.out.println(responseStatus);

            

            BufferedReader in = new BufferedReader(new InputStreamReader(

            con.getInputStream()));

            String inputLine;

            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {

            response.append(inputLine);

            }

            in.close();

            

            //System.out.println("response:" + response.toString());

            

            

            resp=true;

            //return resp;

            //return Boolean.parseBoolean(response.toString());

            

            } catch (Exception e) {

                  resp=false;

                  

            System.out.println(e);

            //return resp;

            }

            //return ;

            }



}
