package com.star.controller;

import com.star.annotation.LogMysql;
import com.star.bean.ResponseBo;
import com.star.bean.SysUser;
import com.star.service.SysUserService;
import com.star.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.Random;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2020-04-06 20:07:03
 */
@EnableSwagger2
@Controller
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

    //以下测试 shiro 用户认证

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @LogMysql("登录操作")
    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username, String password) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseBo.ok();
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @LogMysql("登录成功并返回信息")
    @RequestMapping("/index")
    public String index(Model model) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }

}