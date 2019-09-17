package com.wby.spi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * Java SPI
 * ServiceLoader会加载META-INF/services文件夹下名称为Robot的全限定名com.wby.spi.Robot
 * <p>
 * Dubbo SPI
 * Dubbo并未使用Java SPI,而是重新实现了一套功能更强的SPI机制，Dubbo SPI的相关逻辑被封装在了ExtensionLoader类中。
 * Dubbo SPI是通过键值对的方式进行配置，这样我们可以按需加载指定的实现类，在测试Dubbo SPI时，需在Robot接口上标注@SPI注解，
 *
 * @author wubinyu
 * @date 2019/8/28 21:01.
 */
@SPI
public interface Robot {

    void init();
}
