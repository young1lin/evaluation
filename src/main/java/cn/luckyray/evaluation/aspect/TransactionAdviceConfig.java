package cn.luckyray.evaluation.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 开启service事务
 * @author young1lin
 * @version 1.0
 * @date 2019/10/281:40 下午
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {
    /**
     * 切入点
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* cn.luckyray.evaluation.service.*.*(..))";

    @Autowired
    @Qualifier("mainDataSourceTransactionManager")
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        //设置事务传播常量,当前常量含义为：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //不设置事务隔离级别，采用数据库默认隔离级别
        DefaultTransactionAttribute readOnlyAttr = new DefaultTransactionAttribute();
        readOnlyAttr.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //设置只读属性为true
        readOnlyAttr.setReadOnly(true);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("insert*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", readOnlyAttr);
        source.addTransactionalMethod("query*", readOnlyAttr);
        source.addTransactionalMethod("find*", readOnlyAttr);
        source.addTransactionalMethod("list*", readOnlyAttr);
        source.addTransactionalMethod("count*", readOnlyAttr);
        source.addTransactionalMethod("is*", readOnlyAttr);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
