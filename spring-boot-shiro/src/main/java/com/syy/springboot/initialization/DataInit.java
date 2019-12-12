package com.syy.springboot.initialization;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * DataInit
 *
 * @author: shiyan
 * @version: 2019-11-13 16:26
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Component
@Order(1)
public class DataInit {

    /**
     * userInit
     *
     * @return
     */
    @Bean
    UserInit userInit() {
        return new UserInit();
    }
}
