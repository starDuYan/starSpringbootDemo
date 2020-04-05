package com.star.controller;

import com.star.annotation.LogMysql;
import com.star.annotation.LogOracle;
import com.star.bean.Student;
import com.star.service.StudentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author duxing
 * @version V1.0
 * @Package com.star.controller
 * @date 2020/04/01 22:22
 */
@Log4j
@RestController
public class StudentController {

    @Autowired
    private  StudentService studentService;
    @LogOracle("测试INDEX")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getIndex(){
        log.error("模拟测试成功！！！");
        return  "testduxing";
    }

    @LogMysql("通过ID查询")
    @RequestMapping(value = "/querystudent", method = RequestMethod.GET)
    public Student queryStudentBySno(Long id) {
        Student stu = this.studentService.queryStudentById(id);
        log.error("通过ID查询成功:"+stu.toString());
        return stu;
    }
    @LogOracle("查询所有数据成功")
    @RequestMapping(value = "/queryallstudent")
    public List<Map<String, Object>> queryAllStudent() {
        List<Map<String, Object>> list = this.studentService.queryStudentListMap();
        log.error("查询所有数据成功，条数:"+list.size());
        return list;
    }
    @LogMysql("新增数据")
    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public int saveStudent() {
        Student student = getStudentMybatis();
        log.error("新增数据："+student.toString());
        return this.studentService.add(student);
    }
    @LogMysql("删除数据")
    @RequestMapping(value = "/deletestudent", method = RequestMethod.GET)
    public int deleteStudentBySno(Long id) {
        log.error("删除数据："+id);
        return this.studentService.deleteById(id);
    }

    /**
     * 模拟新增数据
     * @return
     */
//	@Bean
    private Student getStudentMybatis() {
        Student stu = new Student();
        stu.setUsername("田"+(new Random().nextInt(100)));
        stu.setPassword("Ryx@"+(new Random().nextInt(9999)));
        stu.setIcon((new Random().nextInt(99))+".jpg");
        stu.setEmail((new Random().nextInt(99999))+"@qq.com");
        stu.setNickName((new Random().nextInt(9999))+"test");
        stu.setNote((new Random().nextInt(999))+"测试"+(new Random().nextInt(9944449)));
        stu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        stu.setLoginTime(new Timestamp(System.currentTimeMillis()));
        stu.setStatus(1);
        return stu;
    }
    /**
     * 模拟修改数据
     * @return
     */
//	@Bean
    private Student getUpdateStudentMybatis(Long id) {
        Student stu = new Student();
        stu.setId(id);
        stu.setUsername("user"+(new Random().nextInt(100)));
        stu.setPassword("CPww@"+(new Random().nextInt(9999)));
        stu.setIcon((new Random().nextInt(999999))+".jpg");
        stu.setEmail((new Random().nextInt(999999999))+"@qq.com");
        stu.setNickName((new Random().nextInt(999))+"test");
        stu.setNote((new Random().nextInt(999))+"测试"+(new Random().nextInt(9944449)));
        stu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        stu.setLoginTime(new Timestamp(System.currentTimeMillis()));
        stu.setStatus(1);
        return stu;
    }
}    