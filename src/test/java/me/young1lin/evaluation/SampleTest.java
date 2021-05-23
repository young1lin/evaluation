//package me.young1lin.evaluation;
//
//import me.young1lin.evaluation.api.domain.access.repository.AccessRecordMapper;
//import me.young1lin.evaluation.api.domain.access.entity.AccessRecord;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
///**
// * TODO
// *
// * @author 杨逸林
// * @version 1.0
// * @date 2019/7/4 15:43
// **/
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SampleTest {
//
//    @Autowired
//    private AccessRecordMapper accessRecordMapper;
//
//    @Test
//    public void testSelect(){
//        System.out.println("---------start select--------------");
//        List<AccessRecord> list = accessRecordMapper.selectList(null);
//        list.forEach(System.out::println);
//    }
//
//}
