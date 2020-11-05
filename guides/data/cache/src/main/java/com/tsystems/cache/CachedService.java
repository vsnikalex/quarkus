package com.tsystems.cache;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CachedService {

    @CacheResult(cacheName = "foo")
    public Object loadSimple(Object key) {
        // Call expensive service here.
        return new Object();
    }

    @CacheResult(cacheName = "foo")
    public String loadComposite(@CacheKey Object keyElement1, @CacheKey Object keyElement2, Object notPartOfTheKey) {
        // Call expensive service here.
        return new String();
    }

    @CacheResult(cacheName = "foo")
    public String load() {
        // Call expensive service here.
        return new String();
    }

    @CacheInvalidate(cacheName = "foo")
    public void invalidate(Object key) {
    }

    @CacheInvalidateAll(cacheName = "foo")
    public void invalidateAll() {
    }


    @CacheInvalidate(cacheName = "foo")
    @CacheResult(cacheName = "foo")
    public String forceCacheEntryRefresh(Object key) {
        // Call expensive service here.
        return new String();
    }

    @CacheInvalidateAll(cacheName = "foo")
    @CacheInvalidateAll(cacheName = "bar")
    public void multipleInvalidateAll(Object key) {
    }
}
