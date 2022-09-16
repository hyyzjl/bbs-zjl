package com.zjl.user.config;


import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@NacosConfigurationProperties(dataId = "user-service-datasource", groupId = "datasource", type = ConfigType.JSON, autoRefreshed = true)
@Component
public class DemoConfig {

    private String name;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}


