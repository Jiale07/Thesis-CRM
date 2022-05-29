package com.jiale.thesis.controller.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.vo.ThesisProposalApproveByInstructorVO;
import com.jiale.thesis.service.SysUserRoleService;
import com.jiale.thesis.service.ThesisProposalService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/teacher/thesisProposalAuditByDH")
public class ThesisProposalAuditByDHController {
    @Autowired
    SysUserRoleService userRoleService;

    @Autowired
    ThesisProposalService thesisProposalService;

    @RequestMapping("/postThesisProposalByDepartment")
    @ResponseBody
    public Result<Page<ThesisProposalApproveByInstructorVO>> postThesisProposalByDepartment(Integer currentPage, Integer pageSize, BigInteger teacherId){
        System.out.println(currentPage);
        System.out.println(pageSize);
        System.out.println(teacherId);
        Result<Page<ThesisProposalApproveByInstructorVO>> result = new Result<>();
        SysUserRole sysUserRole = userRoleService.VerifyUserHasRoleId(teacherId,2004);
        if (null!=sysUserRole){
            Page<ThesisProposalApproveByInstructorVO> page
                    = thesisProposalService.findThesisProposalApproveByInstructorIdPage(currentPage,pageSize,teacherId);
            if (page.getTotal()>0){
                result.setResultCode(200);
                result.setMessage("获取成功");
                result.setData(page);
            }else{
                result.setResultCode(204);
                result.setMessage("暂无需要审核的开提报告");
            }
        }else{
            result.setResultCode(500);
            result.setMessage("尚未拥有权限");
        }
        return result;
    }
}
