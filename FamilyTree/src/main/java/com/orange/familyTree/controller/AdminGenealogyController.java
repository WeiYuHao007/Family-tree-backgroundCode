package com.orange.familyTree.controller;

import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AdminGenealogyController {

    // 权限条件：图谱管理员

    @Autowired
    private GenealogyService genealogyService;

    // 查看申请关注的用户名单
    @GetMapping(value = "/tree/{tree-name}/application-list")
    public Result getApplicationList(@PathVariable("tree-name") String genealogyName) throws MySQLException {
        return ResultFactory.buildSuccessResult(genealogyService.getApplicationList(genealogyName));
    }

    // 设置默认中心节点
    @PostMapping(value = "/tree/{tree-name}/center-node")
    public Result setDefaultCenterPerson(@PathVariable("tree-name") String genealogyName, @RequestBody String nodeName) throws MySQLException {
        genealogyService.setDefaultCenterPerson(genealogyName, nodeName);
        return ResultFactory.buildSuccessResult("设置成功。");
    }

    // 设置管理员
    @PutMapping(value = "/tree/{tree-name}/admin")
    public Result setAdmin(@PathVariable("tree-name") String genealogyName, @RequestBody String newAdminNickname) throws MySQLException {
        genealogyService.setAdmin(genealogyName, newAdminNickname);
        return ResultFactory.buildSuccessResult("设置成功。");
    }

    // 修改图谱名称和描述
    @PatchMapping(value = "/tree/{tree-name}/name-and-description")
    public Result changeGenealogyNameAndDescription(@PathVariable("tree-name") String genealogyName,
                                                    @RequestBody String description) throws MySQLException {
        genealogyService.changeGenealogyDescription(genealogyName, description);
        return ResultFactory.buildSuccessResult("修改成功。");
    }

    // 转让管理员
    @PatchMapping(value = "/tree/{tree-name}/admin")
    public Result transferAdmin(@PathVariable("tree-name") String genealogyName,
                                @RequestParam("newAdminNickname") String newAdminNickname,
                                @RequestParam("oldAdminNickname") String oldAdminNickname) throws MySQLException {
        genealogyService.transferAdmin(genealogyName, newAdminNickname, oldAdminNickname);
        return ResultFactory.buildSuccessResult("转让成功");
    }

    // 更改默认中心节点
    @PatchMapping(value = "/tree/{tree-name}/center-node")
    public Result changeDefaultCenterPerson(@PathVariable("tree-name") String genealogyName,
                                            @RequestParam("newCenterNode") String newCenterPerson) throws MySQLException {
        genealogyService.changeDefaultCenterPerson(genealogyName, newCenterPerson);
        return ResultFactory.buildSuccessResult("更改成功");
    }

    // 通过用户对图谱的关注申请
    @PatchMapping(value = "/tree/{tree-name}/application/{user-name}")
    public Result passApplication(@PathVariable("tree-name") String genealogyName,
                                  @PathVariable("user-name") String userNickname) throws MySQLException {
        genealogyService.passApplication(genealogyName, userNickname);
        return ResultFactory.buildSuccessResult("成功通过。");
    }

    // 取消用户对图谱的关注
    @DeleteMapping(value = "/tree/{tree-name}/user/{user-name}")
    public Result cancelGenealogyFocus(@PathVariable("tree-name") String genealogyName,
                                       @PathVariable("user-name") String userName) throws MySQLException {
        genealogyService.cancelGenealogyFocus(genealogyName, userName);
        return ResultFactory.buildSuccessResult("成功取消。");
    }

}
