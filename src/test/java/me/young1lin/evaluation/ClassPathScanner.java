//package me.young1lin.evaluation;
//
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.util.CollectionUtils;
//
//import java.io.IOException;
//import java.util.Set;
//
///**
// * @author young1lin
// * @version 1.0
// * @date 2020/10/12 7:51 上午
// */
//public class ClassPathScanner extends ClassPathBeanDefinitionScanner {
//
//    public ClassPathScanner(BeanDefinitionRegistry registry) {
//        super(registry,false);
//    }
//    public static void main(String[] args) throws IOException {
//        // 中间会将 . 替换成 /
//        String scannerPath = "me.young1lin.evaluation";
//
//        Resource[] resources =  new PathMatchingResourcePatternResolver().getResources("classpath*:cn/luckyray/evaluation/**/*.class");
//
//        // new Attribute.Class();
//        for(Resource resource : resources){
//            System.out.println(resource.getFilename());
//        }
//        //ClassPathScanner classPathScanner = new ClassPathScanner();
//        //classPathScanner.doScan(scannerPath);
//    }
//
//    @Override
//    public Set<BeanDefinitionHolder> doScan(String... scannerPath){
//        Set<BeanDefinitionHolder> beanDefinitions  = super.doScan(scannerPath);
//        if(CollectionUtils.isEmpty(beanDefinitions)){
//            System.out.println("你输入nm呢，输正确地址");
//        }
//        return beanDefinitions;
//    }
//}
