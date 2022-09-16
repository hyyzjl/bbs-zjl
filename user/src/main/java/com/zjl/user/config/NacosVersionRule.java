package com.zjl.user.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NacosVersionRule extends AbstractLoadBalancerRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(NacosVersionRule.class);

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Resource
    private NacosServiceManager nacosServiceManager;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        String targetVersion = this.nacosDiscoveryProperties.getMetadata().get("targetVersion");

        DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer) getLoadBalancer();
        String name = loadBalancer.getName();
        String clusterName = this.nacosDiscoveryProperties.getClusterName();
        String group = this.nacosDiscoveryProperties.getGroup();
        NamingService namingService = this.nacosServiceManager.getNamingService(this.nacosDiscoveryProperties.getNacosProperties());
        try {
            List<Instance> instanceList = namingService.selectInstances(name, group, true);
            if (StringUtils.isNotBlank(targetVersion)) {
                instanceList = instanceList.stream()
                        .filter(e -> e.getMetadata().get("version").equals(targetVersion))
                        .collect(Collectors.toList());
            }
            List<Instance> instancesToChoose = instanceList;
            if (StringUtils.isNotBlank(clusterName)) {
                List<Instance> sameClusterInstances = instanceList.stream().filter((instancex) -> {
                    return Objects.equals(clusterName, instancex.getClusterName());
                }).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(sameClusterInstances)) {
                    instancesToChoose = sameClusterInstances;
                } else {
                    LOGGER.warn("A cross-cluster call occurs，name = {}, clusterName = {}, instance = {}", name, clusterName, instanceList);
                }
            }
            Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToChoose);
            return new NacosServer(instance);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
