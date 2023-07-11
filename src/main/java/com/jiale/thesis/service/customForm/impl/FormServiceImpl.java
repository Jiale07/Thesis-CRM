package com.jiale.thesis.service.customForm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.customForm.FormEntity;
import com.jiale.thesis.entity.customForm.vo.FormVO;
import com.jiale.thesis.mapper.customFormMapper.FormMapper;
import com.jiale.thesis.service.customForm.FormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FormServiceImpl implements FormService {

    @Resource
    FormMapper formMapper;

    @Override
    public int createForm(FormEntity form) {
        form.setIsDeleted(0);
        return formMapper.insert(form);
    }

    @Override
    public int deleteForm(Long formId) {
        QueryWrapper<FormEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", formId);
        return formMapper.delete(queryWrapper);
    }

    @Override
    public int updateForm(FormEntity form) {
        return formMapper.updateById(form);
    }

    @Override
    public FormEntity selectForm(Long formId, Long authorId) {
        QueryWrapper<FormEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", formId);
        queryWrapper.eq("author_id", authorId);
        queryWrapper.eq("is_deleted", 0);
        return formMapper.selectOne(queryWrapper);
    }

    @Override
    public FormVO selectFormVO(Long formId) {
        return formMapper.selectFormVO(formId);
    }

    @Override
    public FormVO selectFormVO(Long formTemplateId, Long authorId) {
        System.out.println("---------------------------");
        System.out.println(formTemplateId);
        System.out.println(authorId);
        return formMapper.selectFormVOByTemplateId(formTemplateId, authorId);
    }

    @Override
    public int formCount(Long formTemplateId, Long authorId) {
        QueryWrapper<FormEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("form_template_id", formTemplateId);
        queryWrapper.eq("author_id", authorId);
        queryWrapper.eq("is_deleted", 0);
        return formMapper.selectCount(queryWrapper);
    }
}
