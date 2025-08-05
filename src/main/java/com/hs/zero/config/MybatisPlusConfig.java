package com.hs.zero.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fq
 * 配置mybatisPlus
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * redis的默配置
     */
//    @Configuration
//    @PropertySource(value = "classpath:redis-configure.properties")
//    public static class RedisConfiguration {
//
//        @Value("${redis.server.host}")
//        private String host;
//
//        @Value("${redis.server.port}")
//        private int port;
//
//        @Value("${redis.server.password}")
//        private String password;
//
//        @Value("${redis.server.database}")
//        private int database;
//
//        @Bean("cacheJedisConnectionFactory")
//        public JedisConnectionFactory cacheJedisConnectionFactory() {
//            // 单节点模式
//            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host,port);
//            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//            redisStandaloneConfiguration.setDatabase(database);
//            return new JedisConnectionFactory(redisStandaloneConfiguration);
//        }
//
//        @Bean(name = "redisTemplate")
//        @Autowired
//        public RedisTemplate<String, Object> redisCacheTemplate(
//                @Qualifier("cacheJedisConnectionFactory") JedisConnectionFactory cacheJedisConnFactory) {
//            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//            redisTemplate.setConnectionFactory(cacheJedisConnFactory);
//
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
//            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//            GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(mapper);
//            redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
//
//            // 使用StringRedisSerializer来序列化和反序列化redis的key值
//            StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//            redisTemplate.setKeySerializer(stringRedisSerializer);
//            redisTemplate.setHashKeySerializer(stringRedisSerializer);
//            // value
//            redisTemplate.setValueSerializer(stringRedisSerializer);
//            redisTemplate.setHashValueSerializer(stringRedisSerializer);
//
//            redisTemplate.afterPropertiesSet();
//            return redisTemplate;
//        }
//
//        @Bean
//        public CacheManager cacheManager(
//                @Qualifier("cacheJedisConnectionFactory") JedisConnectionFactory cacheJedisConnFactory) {
//            // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//            RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//            // 设置缓存的默认过期时间，也是使用Duration设置
//            config = config.entryTtl(Duration.ofSeconds(3600)).disableCachingNullValues(); // 不缓存空值
//
//            // 设置一个初始化的缓存空间set集合
//            Set<String> cacheNames = new HashSet<>();
//            cacheNames.add("my-redis-cache1");
//            cacheNames.add("my-redis-cache2");
//
//            // 对每个缓存空间应用不同的配置
//            Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//            configMap.put("my-redis-cache1", config);
//            configMap.put("my-redis-cache2", config.entryTtl(Duration.ofSeconds(1800)));
//            // 使用自定义的缓存配置初始化一个cacheManager
//            RedisCacheManager cacheManager = RedisCacheManager.builder(cacheJedisConnFactory)
//                    // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                    .initialCacheNames(cacheNames).withInitialCacheConfigurations(configMap).build();
//            return cacheManager;
//        }

//    }
}
