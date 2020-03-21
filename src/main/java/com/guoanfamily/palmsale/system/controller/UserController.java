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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by lenovo on 2017/4/25.
 */
@RestController
@RequestMapping(ApiController.USER_URL)
public class UserController extends ApiController{

    private final UserRepository userRepository;
    private final SmsIdentifyingCodeRepository smsIdentifyingCodeRepository;
    private final DatabaseUserService databaseUserService;
    private final UserService userService;
    private AjaxJson ajaxJson;
    @Autowired
    public UserController(UserService userService, UserRepository userRepository, SmsIdentifyingCodeRepository smsIdentifyingCodeRepository, DatabaseUserService databaseUserService, AjaxJson ajaxJson) {
        this.userRepository = userRepository;
        this.smsIdentifyingCodeRepository = smsIdentifyingCodeRepository;
        this.databaseUserService = databaseUserService;
        this.userService = userService;
        this.ajaxJson = ajaxJson;
    }

    @ApiOperation(value="用户列表分頁查", notes="分页显示用户数据")
    @RequestMapping(value = "/findPage", method= RequestMethod.GET)
    public AjaxJson getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "2") Integer size) {
        ajaxJson = new AjaxJson();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return ajaxJson.setSuccess(true).setStatus(200).setData( userRepository.findAll(pageable));
    }

    @ApiOperation(value="用户列表分頁查", notes="分页显示用户数据")
//    @RequestMapping(value = "/query", method= RequestMethod.GET)
    @GetMapping("/query")
    public AjaxJson getQueryPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "2") Integer size,
                                     @RequestParam(value = "userName") String userName ,
                                     @RequestParam(value = "realname") String realname) {
        ajaxJson = new AjaxJson();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<User> specification = Specifications.<User>and()
                .like(StringUtils.isNotBlank(userName),"username" , userName)
                .like(StringUtils.isNotBlank(realname),"realname" , realname)
                .build();
        return ajaxJson.setSuccess(true).setStatus(200).setData( userRepository.findAll(specification,pageable));
    }

    @ApiOperation(value="合伙人列表分頁查", notes="分頁顯示合夥人數據")
    @GetMapping(value = "/getUserInfo")
    public AjaxJson getUserInfo(HttpServletRequest request){
        ajaxJson = new AjaxJson();
        String userId = (String) request.getAttribute("userId");
        if(userId == null){
            return ajaxJson.setSuccess(true).setStatus(200);
        }
        User user = userRepository.findOne(userId);
        if(null != user){
            user.setPassword("");
            return ajaxJson.setSuccess(true).setStatus(200).setData(user);
        }else{
            return ajaxJson.setSuccess(false).setStatus(1050).setMsg("没有用户信息数据！");
        }

    }

    @ApiOperation(value="刪除該用戶", notes="刪除該用戶")
    @DeleteMapping(value="{id}")
    public AjaxJson delete(@PathVariable(value = "id") String id){
        ajaxJson = new AjaxJson();
        userRepository.delete(id);
        return ajaxJson.setSuccess(true).setStatus(200).setData("数据删除成功！");
    }
}
