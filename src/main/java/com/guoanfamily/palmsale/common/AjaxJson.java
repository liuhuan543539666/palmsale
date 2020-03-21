/*
 * Copyright  (c) 2017. By AsherLi0103
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.guoanfamily.palmsale.common;

import com.guoanfamily.palmsale.common.error.BusinessErrorCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * Ajax返回信息实体
 *
 * @author AsherLi
 * @version V1.0.00
 */
@ToString
@Component
public class AjaxJson implements Serializable {

    /**
     * 错误码
     */
    private Integer status = 200;
    /**
     * 操作是否成功
     */
    private boolean success = true;
    /**
     * 提示信息
     */
    private String msg = "操作成功";
    /**
     * 返回数据
     */
    private Object data = null;

    private Object obj =null;
    /**
     * 返回其他参数
     */
    private Map<String, Object> attributes;

    public Integer getStatus() {
        return status;
    }

    public AjaxJson setStatus(Integer status) {
        this.status = status == null ? 0 : status;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public AjaxJson setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxJson setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AjaxJson setData(Object data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public AjaxJson setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public AjaxJson setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public AjaxJson setMsgAndCode(BusinessErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.msg = errorCode.getMessage();
        this.success = errorCode.isSuccess();
        return this;
    }


}
