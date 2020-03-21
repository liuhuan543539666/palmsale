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

package com.guoanfamily.palmsale.common.abstractobj;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class IdEntity implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(IdEntity.class);

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    /**
     * 将javaBean转换为Map<String, Object>
     *
     * @return
     */
    public Map<String, Object> ToMap() {
        Map<String, Object> info = new HashMap<>();
        try {
            info.putAll(BeanUtils.describe(this));
            info.remove("class");
        } catch (Exception e) {
            logger.error("***--将JavaBean属性值拷贝到map对象发生错误--***", e);
        }
        return info;
    }

    /**
     * 将javaBean转换为Json
     *
     * @return
     */
    public JSONObject ToJson() {
        JSONObject info = new JSONObject(ToMap());
        return info;
    }

    /**
     * 将javaBean转换为Xml
     *
     * @return
     */
    public String ToXml() {
        StringBuffer sb = new StringBuffer("");
        sb.append("<xml>");

        Set<String> set = ToMap().keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = it.next();
            Object value = ToMap().get(key);
            sb.append("<").append(key).append(">");
            sb.append(value);
            sb.append("</").append(key).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 获取bean中指定属性名称的值
     *
     * @param key
     * @return
     */
    public Object getKey(String key) {
        return ToMap().get(key);
    }

}
