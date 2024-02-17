//package jpabook.jpashop.config;
//
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//
//public class DevCondition implements Condition {
//
//    @Override
//    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        String ip = context.getEnvironment().getProperty("myapp.profile.dev.ip");
//        return devEnabled && ip.equals("192.168.1.100");
//    }
//}
//
//// LocalCondition, ProdCondition 클래스도 유사하게 구현
