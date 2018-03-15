package queryProduct.controller;

import com.egzosn.pay.ali.controller.AliPayUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("PayController")
public class PayController {

    /**
     * 阿里支付
     * @return  支付页面的html形式
     */
    @RequestMapping("/alipay")
    public String alipay() {
        AliPayUtil aliPayUtil=new AliPayUtil();
        String aliPayHtml = aliPayUtil.aliPay();
        return aliPayHtml;
    }
}
