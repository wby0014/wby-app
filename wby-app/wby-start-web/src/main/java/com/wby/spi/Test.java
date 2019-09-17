package com.wby.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.wby.web.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

/**
 * @author wubinyu
 * @date 2019/8/22 15:21.
 */
@Component
public class Test {

    @Autowired
    private WebClient webClient;

    public void test() {
        String url = webClient.getUrl();
        System.out.println(url);
        String html = webClient.getHtml();
        System.out.println(html);
    }

    public static void javaSpi() {
        ServiceLoader<Robot> load = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        load.forEach(Robot::init);
    }

    public static void dubboSpi() {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.init();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.init();
    }

    public static void main(String[] args) {
        javaSpi();
        dubboSpi();
    }
}
