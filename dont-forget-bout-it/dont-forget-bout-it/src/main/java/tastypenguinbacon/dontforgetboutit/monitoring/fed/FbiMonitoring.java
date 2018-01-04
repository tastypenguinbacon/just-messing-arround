package tastypenguinbacon.dontforgetboutit.monitoring.fed;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@SpyedByTheFeds(agency = "fbi")
public class FbiMonitoring {
    @AroundInvoke
    public Object writeToScreen(InvocationContext context) throws Exception {
        System.out.println("The FEDs know everything");
        for (Object parameter : context.getParameters()) {
            System.out.println(parameter);
        }
        return context.proceed();
    }
}
