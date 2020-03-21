/*
 * @ProjectName: pes
 * @Copyright: 2013 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2018/12/13
 * @Description: 本内容仅限于杭州海康威视系统技术有限公司内部使用，禁止转发.
 */
package com.example.wby.common.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;

/**
 * <p>全局事务切面</p>
 *
 */
@Aspect
@Configuration
public class TransactionConfig {
    /**
     * 这里需要自己配置一下拦截路径
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.example.wby.*.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        final RuleBasedTransactionAttribute REQUIRED = new RuleBasedTransactionAttribute();
        REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        REQUIRED.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));

        final DefaultTransactionAttribute REQUIRED_READONLY = new DefaultTransactionAttribute();
        REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        //写事务
        source.addTransactionalMethod("add*", REQUIRED);
        source.addTransactionalMethod("save*", REQUIRED);
        source.addTransactionalMethod("delete*", REQUIRED);
        source.addTransactionalMethod("update*", REQUIRED);
        source.addTransactionalMethod("insert*", REQUIRED);
        //只读事务
        source.addTransactionalMethod("get*", REQUIRED_READONLY);
        source.addTransactionalMethod("query*", REQUIRED_READONLY);
        source.addTransactionalMethod("find*", REQUIRED_READONLY);
        source.addTransactionalMethod("list*", REQUIRED_READONLY);
        source.addTransactionalMethod("count*", REQUIRED_READONLY);
        source.addTransactionalMethod("exist*", REQUIRED_READONLY);
        source.addTransactionalMethod("search*", REQUIRED_READONLY);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}