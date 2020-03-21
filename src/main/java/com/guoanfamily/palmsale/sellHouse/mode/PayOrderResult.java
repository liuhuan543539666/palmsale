package com.guoanfamily.palmsale.sellHouse.mode;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import com.thoughtworks.xstream.annotations.XStreamAlias;
//import me.chanjar.weixin.common.util.ToStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author AsherLi0103
 * @version 0.0.01
 */
@XStreamAlias("xml")
public class PayOrderResult implements Serializable {

    @XStreamAlias("appid")
    private String appid;
    @XStreamAlias("bank_type")
    private String bankType;
    @XStreamAlias("cash_fee")
    private String cashFee;
    @XStreamAlias("fee_type")
    private String feeType;
    @XStreamAlias("is_subscribe")
    private String isSubscribe;
    @XStreamAlias("mch_id")
    private String mchId;
    @XStreamAlias("nonce_str")
    private String nonceStr;
    @XStreamAlias("openid")
    private String openid;
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @XStreamAlias("result_code")
    private String resultCode;
    @XStreamAlias("return_code")
    private String returnCode;
    @XStreamAlias("sign")
    private String sign;
    @XStreamAlias("time_end")
    private String timeEnd;
    @XStreamAlias("total_fee")
    private Integer totalFee;
    @XStreamAlias("trade_type")
    private String tradeType;
    @XStreamAlias("transaction_id")
    private String transactionId;

    @XStreamAlias("return_msg")
    private String return_msg;
    @XStreamAlias("device_info")
    private String device_info;
    @XStreamAlias("err_code")
    private String err_code;
    @XStreamAlias("err_code_des")
    private String err_code_des;

    @XStreamAlias("settlement_total_fee")
    private String settlement_total_fee;

    @XStreamAlias("coupon_fee")
    private String coupon_fee;

    @XStreamAlias("cash_fee_type")
    private String cash_fee_type;

    @XStreamAlias("attach")
    private String attach;

    public String getAppid() {
        return appid;
    }

    public PayOrderResult setAppid(String appid) {
        this.appid = appid == null ? "" : appid;
        return this;
    }

    public String getBankType() {
        return bankType;
    }

    public PayOrderResult setBankType(String bankType) {
        this.bankType = bankType == null ? "" : bankType;
        return this;
    }

    public String getCashFee() {
        return cashFee;
    }

    public PayOrderResult setCashFee(String cashFee) {
        this.cashFee = cashFee == null ? "" : cashFee;
        return this;
    }

    public String getFeeType() {
        return feeType;
    }

    public PayOrderResult setFeeType(String feeType) {
        this.feeType = feeType == null ? "" : feeType;
        return this;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public PayOrderResult setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe == null ? "" : isSubscribe;
        return this;
    }

    public String getMchId() {
        return mchId;
    }

    public PayOrderResult setMchId(String mchId) {
        this.mchId = mchId == null ? "" : mchId;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public PayOrderResult setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr == null ? "" : nonceStr;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public PayOrderResult setOpenid(String openid) {
        this.openid = openid == null ? "" : openid;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public PayOrderResult setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? "" : outTradeNo;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public PayOrderResult setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? "" : resultCode;
        return this;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public PayOrderResult setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? "" : returnCode;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public PayOrderResult setSign(String sign) {
        this.sign = sign == null ? "" : sign;
        return this;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public PayOrderResult setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd == null ? "" : timeEnd;
        return this;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public PayOrderResult setTotalFee(Integer totalFee) {
        this.totalFee = totalFee == null ? 0 : totalFee;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public PayOrderResult setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? "" : tradeType;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PayOrderResult setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? "" : transactionId;
        return this;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

