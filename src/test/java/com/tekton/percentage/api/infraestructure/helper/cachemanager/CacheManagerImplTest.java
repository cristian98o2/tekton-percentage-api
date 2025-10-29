package com.tekton.percentage.api.infraestructure.helper.cachemanager;

import com.tekton.percentage.api.domain.model.util.CustomException;
import com.tekton.percentage.api.domain.model.util.ResponseCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CacheManagerImplTest {

    @Mock
    private Environment env;
    @Mock
    private CacheManager cacheManager;
    @Mock
    private Cache cache;
    @InjectMocks
    private CacheManagerImpl cacheManagerImpl;

    @Test
    void shouldGetCache(){
        when(env.getProperty("cache.name")).thenReturn("percentCache");
        when(cacheManager.getCache("percentCache")).thenReturn(cache);
        when(cache.get("percentage")).thenReturn(mock(Cache.ValueWrapper.class));
        when(cache.get("percentage", Double.class)).thenReturn(42.0);

        Double result = cacheManagerImpl.getCache();

        assertEquals(42.0, result);
    }

    @Test
    void shouldGetCacheException(){
        when(env.getProperty("cache.name")).thenReturn("percentCache");
        when(cacheManager.getCache("percentCache")).thenReturn(null);

        CustomException response = assertThrows(CustomException.class, () -> cacheManagerImpl.getCache());

        assertEquals(ResponseCode.TP004.getHtmlMessage(), response.getMessage());
    }

    @Test
    void shouldSaveCache() {
        when(env.getProperty("cache.name")).thenReturn("percentCache");
        when(cacheManager.getCache("percentCache")).thenReturn(cache);

        cacheManagerImpl.saveCache(12.5);

        verify(cache, times(1)).put("percentage", 12.5);
    }
}
