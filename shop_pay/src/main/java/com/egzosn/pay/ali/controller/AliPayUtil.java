package com.egzosn.pay.ali.controller;

import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.MethodType;
import com.egzosn.pay.common.bean.PayOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
public class AliPayUtil {

    @RequestMapping("/alipayTest")
    public String aliPay() {
        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
        aliPayConfigStorage.setPid("2088102175307622");
        aliPayConfigStorage.setAppId("2016091200494690");
        aliPayConfigStorage.setKeyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwt9rjnmpGrOQXsYzuNIs24cPgvFKpXRHRrvuKeViBDmsuk/xzhHGG3aFnN+TH2vXlqkzaDtjmohguRHRf1C8r27lPySmgb5SRoHBVSoxkxE5wt8GkItYfvAc2BtcFZtweWrkQaVVHC1smBxmfxhqyURZIObaAVrfzzEMBRiVy5pCugKhJB9YeIQsxnWTa8wwD9IiGWtfZJNKMmu95O0cpx1Vy0HRZEF0uABvhOnZxflqg4c1U9lffEQ/QWKEzFZKyBui/pNE9CR069eYjS4IIii0ZXQUewpCMDJB1xlTYCVl0a73eGnszl58KzFDWDGZkZeGqumvCCGZ3IK11YqXtQIDAQAB");
        aliPayConfigStorage.setKeyPrivate("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6lTOdLRRh1DdTmjpWQ/73VUgKzRSGbcLiJvQKDU3bwB+L9xQG5a4k75b0pCJliFcU2LNwyoXRixUGlaEYqhxmlTezc0iV6QfEz8ckGo+L0cCoT1cQVNBwPRr0FtXaNrkMzmDybokZmu3Et5Ekjv9/OEUrosFBMqE3qpYCGprMgynDNJQRH7u62B4JUY/GmF0gjUe8nj54HC3pVZM8WXoAdJLUOPDNzmMdUTOrgFUBADoxW9S+j82mNgnI6inJPogAo+JMCMOcwxcuCm+8eIhP63CcIsknamspdercxsOnyW0yGeVsU4HrFINFy5jQryGuWChZI+1zo4vFq+HBGLqnAgMBAAECggEAB7cfuUIh86Go4AYin2qUYnneTsQIMz7aritaMAg4zft10n3XECnzN4fIwDkBSx2GLFCBqwpU2kNcDTemv7RZFEzbTspXmAtYzOSioXWPtgJoaw1M1loLfHiqYqmHEkzs/gCStbOoa6yiZF4K3G4lVodnJNTIFQAY9opRRaj/+bDop3ooC6IlB4WgZEHm3MNOtfEboXIGa1TbwCkZEODbP/aF7kHPdCQQGwrtt/MY4TzK5KyDOPia0K/tlsFYZmSp+21RFCLthRN8nMDjxBBGfbDZ3ECoKRMPSaRPU9YMjs2kiiOxtA8xJ0m8xErf9Nb0RzgHOQY3sUrn7CUXBjTqMQKBgQDt1MV/5gBXJsJJBVCM4+BPkwSdVegbq4UvLge6Jx0IQKRXrnXEg7qY7rr4TNducOO7v+6Giv8maXAiqxGdnWTzZrspVqFEEHIYPI4dAlBSi0cWY6lSvoujrP2U6h6fNnW3T0I4qJf95rGSMbiD8HVzz2hwu5waYmKqNliZJXwuiwKBgQDI1iye3ONAJX/UdbZHNyeSQKgRgTG1eDTWlQ9ioEu8TJZttP7m9OpkDi6gXUKceIapALEkHmvVBnrfHfJSYilF/24/h0HvBDEYnXV4/WmHAtyyKF3W+mt/cAnou4xVGyfw2lGMq3cCLUzU7M+cLTkBvUcmavNCA/jItfXXgVsj1QKBgQCQMW5EqvgN3svwZa5+nTJETIiBs7H0BLvM7QV2UXyCol6BYp64NH2Pdz4YiM4cgYkxLZ66J8+mREreWgVmICUyhVh4KbPAhpAGLcCjyeFVCmhhJK9mIWycIafk/0TtlqEsMHufymfMYm/KiSarp8AuJNPXdYehX4EeXbC9uUJfHQKBgH4Zo+iXboRvRyTiR/HNGqaEvEJJaSm08RAuhXt6hDMVr3nwo//llWn/UZ1VESbnsj1Y9uBjzXby26FNz0GU6q9Noy52QZrOefknnIO8MU8ZJ/Mgz1LU5zwb38zmPUWOiHR8z6lQW6fdQ5mJmw1Hg7yo2IyxqE0x6zoyrcqDKPOFAoGACjEBXhG2mVcN2/qraMsiVvZ63urThYJONknUdu72uujg5J3BQc1j7gvk3SWdmQUeisEJ5FgRPnuyqRoDfDbjgPNTVYyb5PVPXvhxIvLYkwvTU0ArBdXrM/RA8WvMewJUOgfL/p5gBpH9fepkUlg3hJfAUUTRP1WMvJFDVyx0qtA=");
        aliPayConfigStorage.setNotifyUrl("http://k19847n443.51mypc.cn:37169/notifyalipay");
//        aliPayConfigStorage.setReturnUrl("http://k19847n443.51mypc.cn:37169/notify/a.html");
        aliPayConfigStorage.setSignType("RSA2");
        aliPayConfigStorage.setSeller("2088102175307622");
        aliPayConfigStorage.setInputCharset("utf-8");
        //是否为测试账号，沙箱环境
        aliPayConfigStorage.setTest(true);
        //支付服务
        PayService service = new AliPayService(aliPayConfigStorage);
        //支付订单基础信息
        PayOrder payOrder = new PayOrder("订单title", "摘要",  new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""));


        /*-----------即时到帐 WAP 网页支付-------------------*/
//        payOrder.setTransactionType(AliTransactionType.WAP); //WAP支付

        payOrder.setTransactionType(AliTransactionType.DIRECT); // 即时到帐 PC网页支付
        //获取支付所需的信息
        Map directOrderInfo = service.orderInfo(payOrder);
        //获取表单提交对应的字符串，将其序列化到页面即可,
        String directHtml = service.buildRequest(directOrderInfo, MethodType.POST);
        /*-----------/即时到帐 WAP 网页支付-------------------*/
        return directHtml;
    }


}
