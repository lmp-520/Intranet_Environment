package com.xdmd.IntranetEnvironment.user.service.impl;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.mapper.MenuMapper;
import com.xdmd.IntranetEnvironment.user.mapper.UserMapper;
import com.xdmd.IntranetEnvironment.user.pojo.Menu;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MenuMapper menuMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Override
    public ResultMap queryMenu(String token, HttpServletResponse response) {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer id = user.getId();

        //根据用户的id 查询对应的一级目录
        List<Menu> menus = menuMapper.queryRidByUid(id);
        for (Menu menu : menus) {
            //根据一级目录，获取二级目录
            List<Menu> childrenMenuList = menuMapper.queryChildrenMenu(menu.getId());
            menu.setChildrens(childrenMenuList);
        }
        return resultMap.success().message(menus);
    }
}
