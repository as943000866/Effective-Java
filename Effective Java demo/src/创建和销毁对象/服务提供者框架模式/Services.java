package 创建和销毁对象.服务提供者框架模式;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Noninstantiable class for service registration and access (用于服务注册和访问的非实例化类)
public class Services {
    private Services(){} //Prevents instantiation(防止实例化)

    //Maps Service names to Services (将服务名称映射到服务)
    private  static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();

    public static  final  String DEFAULT_PROVIDER_NAME = "<def>";

    //Provider registration API (API提供者注册)
    public static  void  registerDefaultProvider(Provider p){
        regiterProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void regiterProvider(String name, Provider p){
        providers.put(name,p);
    }

    //Service access API(服务访问API)
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    private static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p == null)
            throw new IllegalArgumentException("No provider registerd with name: " + name);
        return p.newService();
    }
}
