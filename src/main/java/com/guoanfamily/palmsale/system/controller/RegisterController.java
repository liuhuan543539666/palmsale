package com.guoanfamily.palmsale.system.controller;

import com.github.wenhao.jpa.Specifications;
import com.guoanfamily.palmsale.common.AjaxJson;
import com.guoanfamily.palmsale.common.abstractobj.ApiController;
import com.guoanfamily.palmsale.common.util.GlobalConstant;
import com.guoanfamily.palmsale.common.util.MessageUtils;
import com.guoanfamily.palmsale.common.util.PasswordUtil;
import com.guoanfamily.palmsale.system.entity.SmsIdentifyingCode;
import com.guoanfamily.palmsale.system.entity.User;
import com.guoanfamily.palmsale.system.repository.SmsIdentifyingCodeRepository;
import com.guoanfamily.palmsale.system.repository.UserRepository;
import com.guoanfamily.palmsale.system.service.DatabaseUserService;
import com.guoanfamily.palmsale.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by lenovo on 2017/4/25.
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;
    private final SmsIdentifyingCodeRepository smsIdentifyingCodeRepository;
    private final DatabaseUserService databaseUserService;
    private final UserService userService;
    private AjaxJson ajaxJson;
    @Autowired
    public RegisterController(UserService userService, UserRepository userRepository, SmsIdentifyingCodeRepository smsIdentifyingCodeRepository, DatabaseUserService databaseUserService, AjaxJson ajaxJson) {
        this.userRepository = userRepository;
        this.smsIdentifyingCodeRepository = smsIdentifyingCodeRepository;
        this.databaseUserService = databaseUserService;
        this.userService = userService;
        this.ajaxJson = ajaxJson;
    }

    @ApiOperation(value="合伙人save接口", notes="合伙人合夥人信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxJson save(@RequestBody User partnerInfo , HttpServletRequest request) {
        ajaxJson = new AjaxJson();
        if( null == partnerInfo ){
            return  ajaxJson.setSuccess(false).setStatus(1001).setMsg("参数异常！");
        }
        String password = PasswordUtil.encrypt(partnerInfo.getUsername(), partnerInfo.getPassword(), PasswordUtil.getStaticSalt());
        partnerInfo.setPassword(password);
        String platform = request.getHeader("platform");

        /*if(StringUtils.isNotEmpty(platform) && platform.equals("app")){
            SmsIdentifyingCode smsIdentifyingCode = smsIdentifyingCodeRepository.findByphonenum(partnerInfo.getUsername());
            if(null == smsIdentifyingCode){
                return  ajaxJson.setSuccess(false).setStatus(1001).setMsg("请先点击发送验证码");
            }
            if(null == partnerInfo.getSmsCode()){
                return  ajaxJson.setSuccess(false).setStatus(1001).setMsg("请输入验证码");
            }
            if(compareDate(smsIdentifyingCode.getCreatetime())){
                return  ajaxJson.setSuccess(false).setStatus(1001).setMsg("验证码超时或有误,请重试");
            }
            String smsCode = partnerInfo.getSmsCode().toString();
            if (!smsCode.equals(smsIdentifyingCode.getCode())){
                return  ajaxJson.setSuccess(false).setStatus(1001).setMsg("验证码超时或有误,请重试");
            }
        }*/

        List<User> usr = userRepository.findByGetUsername(partnerInfo.getUsername());
        if(usr.size() == 0) {
            userRepository.save(partnerInfo);
            partnerInfo = userService.userSaveToRole(partnerInfo);
            ajaxJson.setSuccess(true).setStatus(200).setData(partnerInfo);
        }else {
            String warn = "repeat";
            ajaxJson.setSuccess(true).setStatus(200).setData(warn);
        }
        return ajaxJson;
    }

    /**
     * 验证是否发送验证码
     * @param photoNum
     * @return
     */
    @GetMapping(value = "/loginCaptach")
    public AjaxJson loginCaptach(@RequestParam(value = "photoNum") String photoNum){

        AjaxJson ajaxJson = new AjaxJson();
        if(!StringUtils.isNotEmpty(photoNum)){
            ajaxJson.setMsg("请输入用户名");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        String random = MessageUtils.getRandomAuthenticationCode();
        String result = databaseUserService.loginCaptcha(random,photoNum);
        if (Objects.equals("00", result)) {
            try {
                SmsIdentifyingCode smsIdentifyingCode = smsIdentifyingCodeRepository.findByphonenum(photoNum);
                if(null == smsIdentifyingCode){
                    smsIdentifyingCode = new SmsIdentifyingCode();
                }
                smsIdentifyingCode.setCode(random);
                smsIdentifyingCode.setPhonenum(photoNum);
                smsIdentifyingCode.setCreatetime(new Timestamp(System.currentTimeMillis()));
                smsIdentifyingCodeRepository.save(smsIdentifyingCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ajaxJson.setMsg(GlobalConstant.SMS_SUCCESS);
            ajaxJson.setSuccess(true);
            return ajaxJson;
        } else if (Objects.equals("6", result)) {
            ajaxJson.setMsg(GlobalConstant.SMS_WORDS_SHIELD);
            ajaxJson.setSuccess(false);
            return ajaxJson;
        } else if (Objects.equals("7", result)) {
            ajaxJson.setMsg(GlobalConstant.SMS_LENGHT_MAX);
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        ajaxJson.setMsg(GlobalConstant.SMS_FAILURE);
        ajaxJson.setSuccess(false);
        return ajaxJson;
    }

    public boolean compareDate(Date codedate){
        long time = 90*1000;//90秒
        Date  nowdate = new Date(new Date().getTime()-time);
        if(nowdate.after(codedate)){
            return true;//超时
        } else{//相等
            return false;
        }
    }
}
