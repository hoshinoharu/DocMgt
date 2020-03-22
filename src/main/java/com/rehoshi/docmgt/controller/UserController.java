package com.rehoshi.docmgt.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rehoshi.docmgt.config.PageConfig;
import com.rehoshi.docmgt.domain.RespData;
import com.rehoshi.docmgt.domain.entities.User;
import com.rehoshi.docmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/***
 * 这是类的描述
 *
 *
 * ************
 *
 * 功能：用户的增删该查
 * 方法：
 *         addUser()增加用户
 *         deleteUser()删除用户
 *         updateUser()更新用户
 *         selectByID()根据id查询用户
 *         selectByName()根据姓名查询用户
 *         selectByAccount()根据账号查询用户
 *
 *
 *
 * 数据的返回:返回json格式
 * @Author:SQY
 * @Date:2020.3.21
 */

@RestController
@RequestMapping("/user")
public class UserController extends HoshiController {


    @Autowired
    private UserService userService;

    @Autowired
    private PageConfig config;

    /***
     * @description：添加用户
     * @param :
     * @author：SQY
     * @Date:2020.3.21
     *
     */
    @PostMapping("/addUser")
    public RespData<String> addUser(User user) {
        return $(respData -> {
            userService.save(user);
            String id = user.getId();
            if (id == null) {
                respData.success(false).msg("插入用户失败");
            } else {
                respData.success(true).data(id).msg("插入用户成功");
            }
        });
    }

    /***
     * 根据ID删除
     * @param id
     * @return
     * @author：SQY
     * @Date:2020.3.21
     */
    @DeleteMapping("/deleteUser")
    public RespData<Boolean> deleteUser(String id) {
        return $(booleanRespData -> {
            if (id == null) {
                booleanRespData.success(false).msg("未发现该ID");
            } else {
                userService.removeById(id);
                booleanRespData.success(true).msg("已删除");
            }


        });
    }

    /***
     * 根据ID更新用户
     * @param user
     * @return
     * @author：SQY
     * @Date:2020.3.21
     */
    public RespData<Boolean> updateUser(User user) {
        return $(booleanRespData -> {
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("id", user.getId());
            userService.update(user, queryWrapper);
        });
    }

    /***
     * 根据ID查询用户
     * @param key
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/selectByID/{pgaeIndex}/{pageSize}")
    public RespData<List<User>> selectByID(@RequestParam(required = false, defaultValue = "") String key,
                                           @PathVariable int pageIndex,
                                           @PathVariable int pageSize) {
        return $(listRespData -> {
            config.index(pageIndex).size(pageSize);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", key);
            List<User> list = userService.list(queryWrapper);
            listRespData.success(true).data(list);
        });
    }


    /***
     * 根据姓名查询
     * @param key
     * @param pageIndex
     * @param pageSize
     * @return
     * @author：SQY
     * @Date:2020.3.21
     */
    @GetMapping("/list/{pageIndex}/{pageSize}")
    public RespData<List<User>> list(@RequestParam(required = false, defaultValue = "") String key,
                                     @PathVariable int pageIndex,
                                     @PathVariable int pageSize) {
        return $(listRespData -> {
            config.index(pageIndex).size(pageSize);
            List<User> users = userService.selectByName(key);
            listRespData.success(true).data(users);
        });
    }

    /***
     * 根据账号查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @author：SQY
     * @Date:2020.3.21
     */
    @GetMapping("/selectByAccount/{pageIndex}/{pageSiz}")
    public RespData<List<User>> selectByAccount(@PathVariable Integer pageIndex,@PathVariable int pageSize){
        return $(listRespData -> {
            config.index(pageIndex).size(pageSize);
            List<User> list = userService.selectByAccount();
            listRespData.success(true).data(list);
        });

    }
}
