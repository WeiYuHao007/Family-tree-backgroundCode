package com.orange.familyTree.controller;

import com.orange.familyTree.dao.mysql.UserMySQLRepository;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.specialPojo.DescriptionAndNameVO;
import com.orange.familyTree.pojo.specialPojo.NewCenterPersonVO;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AdminGenealogyController {

    // 权限条件：图谱管理员

    @Autowired
    private GenealogyService genealogyService;

    @Autowired
    private UserMySQLRepository userMySQLRepository;

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
                                                    @RequestBody DescriptionAndNameVO descriptionAndNameVO) throws MySQLException {
        genealogyService.changeGenealogyDescription(genealogyName, descriptionAndNameVO.getNewDescription());
        genealogyService.changeGenealogyName(genealogyName, descriptionAndNameVO.getNewGenealogyName());
        return ResultFactory.buildSuccessResult("修改成功。");
    }

    // 转让管理员
    @PatchMapping(value = "/tree/{tree-name}/admin/{new-admin}")
    public Result transferAdmin(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
                                @PathVariable("new-admin") String newAdmin) throws MySQLException {
        List<Long> adminsId = genealogyService.findGenealogyAdminsByName(genealogyName);
        if(adminsId.size() <= 2) {
            Boolean state = genealogyService.setAdmin(genealogyName, newAdmin);
            if(state) {
                return ResultFactory.buildSuccessResult("设置成功。");
            }
            else {
                return ResultFactory.buildFailResult("指定用户已是管理员。");
            }
        }
        else {
            HttpSession session = request.getSession(false);
            String oldAdminNickname = (String) session.getAttribute("SESSION_NICKNAME");
            Boolean state = genealogyService.transferAdmin(genealogyName, oldAdminNickname, newAdmin);
            if(state) {
                return ResultFactory.buildSuccessResult("转让成功。");
            }
            else {
                return ResultFactory.buildFailResult("指定用户已是管理员。");
            }
        }
    }

    // 更改默认中心节点
    @PatchMapping(value = "/tree/{tree-name}/center-node")
    public Result changeDefaultCenterPerson(@PathVariable("tree-name") String genealogyName,
                                            @RequestBody NewCenterPersonVO newCenterPersonVO) throws MySQLException {
        genealogyService.changeDefaultCenterPerson(genealogyName, newCenterPersonVO.getName());
        return ResultFactory.buildSuccessResult("更改成功");
    }

    // 通过用户对图谱的关注申请
    @PatchMapping(value = "/tree/{tree-name}/application/{user-name}")
    public Result passApplication(@PathVariable("tree-name") String genealogyName,
                                  @PathVariable("user-name") String userNickname) throws MySQLException {
        // 验证用户对图谱的关注情况
        Long userId = userMySQLRepository.findUserIdByNickname(userNickname);
        List<String> focusedGenealogy =  genealogyService.findAllGenealogy(userId);
        int length = focusedGenealogy.size();
        for(int i = 0; i < length; i++) {
            // 用户已经该图谱，关闭该操作
            if(focusedGenealogy.get(i).equals(genealogyName)) {
                // 数据同步出现问题，删除请求表中数据
                this.genealogyService.refuseApplication(genealogyName, userNickname);
                return ResultFactory.buildFailResult("该用户已经关注该图谱，数据同步出现异常。");
            }
        }
        genealogyService.passApplication(genealogyName, userNickname);
        return ResultFactory.buildSuccessResult("成功通过。");
    }

    // 拒绝用户对图谱的关注请求
    @DeleteMapping(value = "/tree/{tree-name}/application/{user-name}")
    public Result refuseApplication(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
                                    @PathVariable("user-name") String userNickname) throws MySQLException {
        genealogyService.refuseApplication(genealogyName, userNickname);
        return ResultFactory.buildSuccessResult("拒绝成功。");
    }

    // 取消用户对图谱的关注
    @DeleteMapping(value = "/tree/{tree-name}/user/{user-name}")
    public Result cancelGenealogyFocus(@PathVariable("tree-name") String genealogyName,
                                       @PathVariable("user-name") String userName) throws MySQLException {
        genealogyService.cancelGenealogyFocus(genealogyName, userName);
        return ResultFactory.buildSuccessResult("成功取消。");
    }

}
