package com.tekton.percentage.api.infraestructure.helper.cachemanager;

import com.tekton.percentage.api.domain.model.util.CustomException;
import com.tekton.percentage.api.domain.model.util.ResponseCode;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class CacheManagerImpl {

    private static final Logger log = LoggerFactory.getLogger(CacheManagerImpl.class);
    private final Environment env;
    private final CacheManager cacheManager;

    public Double getCache() {
        Cache cache = cacheManager.getCache(Objects.requireNonNull(env.getProperty("cache.name")));
        if(cache != null && cache.get("percentage") != null) {
                log.info("Api will get percentage from cache. {}", cache.get("percentage"));
                return cache.get("percentage", Double.class);
        }
        throw new CustomException(ResponseCode.TP004, ResponseCode.TP004.getHtmlMessage());
    }

    public void saveCache(Double percentage) {
        Cache cache = cacheManager.getCache(Objects.requireNonNull(env.getProperty("cache.name")));
        log.info("Api will save cache percentage {}", percentage);
        if (cache != null) cache.put("percentage", percentage);
    }

}
