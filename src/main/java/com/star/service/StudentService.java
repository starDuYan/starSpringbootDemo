package com.star.service;

import com.star.bean.Student;

import java.util.List;
import java.util.Map;

//@CacheConfig(cacheNames = "student")
public interface StudentService {
	int add(Student student);
//    @CachePut(key = "#p0.id")
    int update(Student student);
//    @CacheEvict(key = "#p0", allEntries = true)
    int deleteById(Long id);
//    @CachePut(key = "#p0")
    List<Map<String, Object>> queryStudentListMap();
//    @CachePut(key = "#p0")
    Student queryStudentById(Long id);
}
