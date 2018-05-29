package com.basic.core.datasource.aspect;

import com.basic.core.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: wangzw
 * @Date: 2017/7/18 11:11
 * @Description: 切换数据源
 * @Version: 1.0
 */
@Aspect
@Component
@Order(-1)//保证该AOP在@Transactional之前执行
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            DynamicDataSourceContextHolder.setDataSourceType(DynamicDataSourceContextHolder.DEFAULT_DATA_SOURCE);
            logger.info("数据源:{}不存在，使用默认数据源:{}", ds.name(), DynamicDataSourceContextHolder.getDataSourceType());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        logger.debug("Revert DataSource : {} >>> {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}
