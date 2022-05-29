package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.vo.StudentBasicInformationVO;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import com.jiale.thesis.entity.vo.StudentInformationVO;
import com.jiale.thesis.mapper.StudentInfoMapper;
import com.jiale.thesis.service.StudentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    @Resource
    StudentInfoMapper studentInfoMapper;

    @Override
    public StudentInfo findStudentInfoById(BigInteger studentId) {
        return studentInfoMapper.selectById(studentId);
    }

    @Override
    public StudentInformationVO findStudentInformationVOById(BigInteger studentId) {
        return studentInfoMapper.findStudentInformationByStudentId(studentId);
    }

    @Override
    public int addStudentInfo(StudentInfo studentInfo) {
        return studentInfoMapper.insert(studentInfo);
    }

    @Override
    public int deletedStudentInfoLogic(BigInteger studentId) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(studentId);
        studentInfo.setIsDeleted(1);
        return studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public int updateStudentInfo(StudentInfo studentInfo) {
        return studentInfoMapper.updateById(studentInfo);
    }

    @Override
    public StudentInfo findOneStudentInfoById(BigInteger studentId) {
        QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",studentId);
        queryWrapper.eq("is_deleted",0);
        return studentInfoMapper.selectOne(queryWrapper);
    }


    //    admin management
    @Override
    public Page<StudentInfoResVO> findStudentInfoByPage(Page<StudentInfoResVO> page, Integer collegeId, Integer majorId, Integer classId){
        Page page1 = new Page();
        page1.getTotal();
        page1.getRecords();
        page1.getCountId();
        page1.getPages();

        return studentInfoMapper.findStudentByPage(page,collegeId,majorId,classId);
    }

    @Override
    public BigInteger findOneStudentId(Integer start,Integer end){
        return studentInfoMapper.getOneStudentId("DESC",start,end);
    }

    @Override
    public StudentBasicInformationVO findStudentBasicInformationVOById(BigInteger studentId) {
        return studentInfoMapper.findStudentBasicInformationVOById(studentId);
    }
}
