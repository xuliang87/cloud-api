package cn.dreampie.api.user.controller;

import cn.dreampie.api.user.User;
import cn.dreampie.service.user.UserConstants;
import cn.dreampie.service.user.UserService;
import cn.dreampie.service.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dreampie on 15/11/30.
 */
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = UserConstants.USERS_PATH, method = RequestMethod.GET)
  public List<User> findAll() {
    List<UserEntity> entities = userService.findAll();
    List<User> result = null;
    if (entities != null) {
      result = entities.stream().map(User::new).collect(Collectors.toList());
    }
    return result;
  }

  @RequestMapping(value = UserConstants.USERS_ID_PATH, method = RequestMethod.GET)
  public User findById(@PathVariable String id) {
    UserEntity entity = userService.findById(id);
    User result = null;
    if (entity != null) {
      result = new User(entity);
    }
    return result;
  }

  @RequestMapping(value = UserConstants.USERS_ID_PATH, method = RequestMethod.DELETE)
  public boolean deleteById(@PathVariable String id) {
    userService.deleteById(id);
    return true;
  }

  @RequestMapping(value = UserConstants.USERS_PATH, method = RequestMethod.POST)
  public User save(@RequestBody User user) {
    user.setId(null);
    UserEntity entity = userService.save(user.reverseEntity());
    User result = null;
    if (entity != null) {
      result = new User(entity);
    }
    return result;
  }

  @RequestMapping(value = UserConstants.USERS_ID_PATH, method = RequestMethod.PUT)
  public User update(@PathVariable String id, @RequestBody User user) {
    user.setId(id);
    UserEntity entity = userService.save(user.reverseEntity());
    User result = null;
    if (entity != null) {
      result = new User(entity);
    }
    return result;
  }

}
