package io.tastypenguinbacon.pinger.util.interceptor.cache;

import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@SingleCallCache
public class SingleCallCacheInterceptor {
    private Object cachedValue = null;

    public Object aroundInvoke(InvocationContext ic) throws Exception {
        if (cachedValue == null)
            cachedValue = ic.proceed();
        return cachedValue;
    }
}
