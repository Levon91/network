package com.example.demo.service;

import com.amazonaws.auth.BasicAWSCredentials;
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

    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    private static AmazonSNSClient snsClient;

    private SmsService() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        snsClient = new AmazonSNSClient(credentials);
        setDefaultSmsAttributes(snsClient);
    }

    public static void sendSms(String message, String phoneNumber) {
        AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new ProfileCredentialsProvider());

        AmazonSNSClient snsClient = (AmazonSNSClient) snsClientBuilder.build();
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
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

    private static void setDefaultSmsAttributes(AmazonSNSClient snsClient) {
        SetSMSAttributesRequest setRequest = new SetSMSAttributesRequest()
                .addAttributesEntry("DefaultSenderID", "Network")
                .addAttributesEntry("MonthlySpendLimit", "1")
                .addAttributesEntry("DeliveryStatusIAMRole", "arn:aws:iam::178519313808:role/role-sns")
                .addAttributesEntry("DeliveryStatusSuccessSamplingRate", "10")
                .addAttributesEntry("DefaultSMSType", "Promotional")
                .addAttributesEntry("UsageReportS3Bucket", "network-app-bucket");
        snsClient.setSMSAttributes(setRequest);
        Map<String, String> myAttributes = snsClient.getSMSAttributes(new GetSMSAttributesRequest())
                .getAttributes();
        System.out.println("My SMS attributes:");
        for (String key : myAttributes.keySet()) {
            System.out.println(key + " = " + myAttributes.get(key));
        }
    }
}
