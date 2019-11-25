package cn.gjw.mybatisplus2.controller;


import cn.gjw.mybatisplus2.domain.User;
import cn.gjw.mybatisplus2.result.Result;
import cn.gjw.mybatisplus2.result.ResultGenerator;
import cn.gjw.mybatisplus2.service.UserService;
import cn.gjw.mybatisplus2.utils.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/list")
    public Result test() {
        List<User> list = (List<User>) redisUtils.get("list", List.class);
        if (list == null) {
            list = userService.list();
            redisUtils.set("list", list);

        }
        return ResultGenerator.getSuccessResult(list);
    }

    @GetMapping("/page")
    public Result page(Long pageNum, Long pageSize) {
        Page<User> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        ArrayList<OrderItem> list = new ArrayList<>();
        list.add(OrderItem.asc("name"));
//        list.add("name");
        page.setOrders(list);
        IPage<User> page1 = userService.page(page);
        return ResultGenerator.getSuccessResult(page1);

    }


    @GetMapping("/listByIds")
    public Result listByIds() {
        Collection<User> users = userService.listByIds(Arrays.asList(1, 3));
        return ResultGenerator.getSuccessResult(users);
    }

    @GetMapping("/listByMap")
    public Result listByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        Collection<User> users = userService.listByMap(map);
        return ResultGenerator.getSuccessResult(users);
    }

    @GetMapping("/getOne")
    public Result getOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        User one = userService.getOne(wrapper);
        return ResultGenerator.getSuccessResult(one);
    }

    @GetMapping("/myGetById")
    public Result myGetById(Integer id) {
        User user = userService.myGetById(id);
        return ResultGenerator.getSuccessResult(user);
    }
}
