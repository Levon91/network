package com.example.demo.service;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Sms service.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@Service
@Scope(value = "singleton")
public final class SmsService {

    public static void sendSms(String message, String phoneNumber) {
        AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new ProfileCredentialsProvider());

        AmazonSNSClient snsClient = (AmazonSNSClient) snsClientBuilder.build();
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        //<set SMS attributes>
//        setSmsAttributes(smsAttributes);
        setDefaultSmsAttributes(snsClient);
        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
    }

    private static void sendSMSMessage(AmazonSNSClient snsClient, String message,
                                       String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber)
                .withMessageAttributes(smsAttributes));
        System.out.println(result); // Prints the message ID.
    }

//    private static void setSmsAttributes(Map<String, MessageAttributeValue> smsAttributes) {
//        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
//                .withStringValue("Network") //The sender ID shown on the device.
//                .withDataType("String"));
//        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
//                .withStringValue("0.50") //Sets the max price to 0.50 USD.
//                .withDataType("Number"));
//        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
//                .withStringValue("Promotional") //Sets the type to promotional.
//                .withDataType("String"));
//    }

    private static void setDefaultSmsAttributes(AmazonSNSClient snsClient) {
        SetSMSAttributesRequest setRequest = new SetSMSAttributesRequest()
                .addAttributesEntry("DeliveryStatusIAMRole", "arn:aws:iam::178519313808:role/role-sns")
                .addAttributesEntry("DefaultSenderID", "Network")
                .addAttributesEntry("DefaultSMSType", "Promotional");
        snsClient.setSMSAttributes(setRequest);
        Map<String, String> myAttributes = snsClient.getSMSAttributes(new GetSMSAttributesRequest())
                .getAttributes();
        System.out.println("My SMS attributes:");
        for (String key : myAttributes.keySet()) {
            System.out.println(key + " = " + myAttributes.get(key));
        }
    }
}
