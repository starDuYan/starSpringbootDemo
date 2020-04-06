package com.star.controller;

import com.star.bean.SysUser;
import com.star.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2020-04-06 20:07:03
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/selectOne",method = RequestMethod.GET)
    public SysUser selectOne(Long id) {
        return this.sysUserService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param
     * @return 单条数据
     */
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public SysUser insert() {
        SysUser user = new SysUser();
        user.setUsername("张"+new Random().nextInt(9999));
        user.setPassword("ryx@"+new Random().nextInt(99099));
        Date date = new Date();
        user.setCreateTime(date);
        return this.sysUserService.insert(user);
    }

}