package com.star.service.impl;

import com.star.bean.Student;
import com.star.dao.StudentMysqlDao;
import com.star.dao.StudentOracleDao;
import com.star.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("studentService")
class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMysqlDao studentMysqlDao;

    @Autowired
    private StudentOracleDao studentOracleDao;

    @Override
    public int add(Student student) {
        return this.studentOracleDao.add(student);
    }


    @Override
    public int update(Student student) {
        return this.studentOracleDao.update(student);
    }

    @Override
    public int deleteById(Long id) {
        return this.studentOracleDao.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> queryStudentListMap() {
        return this.studentOracleDao.queryStudentsListMap();
    }

    @Override
    public Student queryStudentById(Long id) {
        return this.studentOracleDao.queryStudentById(id);
    }

}
