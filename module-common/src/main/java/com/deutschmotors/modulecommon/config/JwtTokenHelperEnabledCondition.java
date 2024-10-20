package com.deutschmotors.modulecommon.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JwtTokenHelperEnabledCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment env = context.getEnvironment();

        boolean enabledRedis = "true".equals(env.getProperty("spring.data.redis.enabled"));
        boolean enabledJwtHelper = "true".equals(env.getProperty("jwt.helper-enabled"));

        return enabledRedis && enabledJwtHelper;
    }

}

/*
* common 모듈을 의존하는 다른 모듈에서 특정 bean을 필요로 하지 않을때 사용.
*
* 예를 들면 common 모듈에 RedisCacheHelper가 존재하고 api 모듈에서 사용중이고,
* batch 모듈에서는 RedisCacheHelper 클래스를 사용하지 않아도 된다고 가정.
*
* @Value에 의해 application.yml 에 존재하는 값을 참조해서 사용하기 때문에
* RedisCacheHelper를 사용하지 않는 batch 모듈에서까지 RedisConfig의 접속정보인 spring.data.redis.host 값을 강제하게 됨.
*
*  따라서 아래와 같이 2가지 방법을 선택적으로 활용한다.
*  1. 단독 사용 case

    @Configuration
    public class RedisConfig {
        @Value("${spring.data.redis.host}")
        private String redisHost;
        ...
        @Bean
        public RedisConnectionFactory redisConnectionFactory() {
            return new LettuceConnectionFactory(redisHost, redisPort);
        }
    }

    @Component
    public class RedisCacheHelper {
        private final RedisTemplate<String, Object> redisTemplate;
    }

    ### @ConditionalOnProperty 어노테이션 사용(상황에 따라 다른 방법으로 대체)

*  2. 중첩 사용 case

    @Component
    @RequiredArgsConstructor
    public class TokenHelper {
        private final RedisCacheHelper redisCacheHelper;
    }

    ### @Conditional 어노테이션 사용

*
*/
