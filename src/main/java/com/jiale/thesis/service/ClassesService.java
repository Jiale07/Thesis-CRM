package com.jiale.thesis.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.Classes;

import java.util.List;

public interface ClassesService {
    int addClassesInfo(Classes classes);

    int deleteClassesById(Integer classId);

    int updateClassesInfo(Classes classes);

    IPage<Classes> findClassesPage(Page<Classes> page);

    IPage<Classes> findClassesPage(Page<Classes> page,Integer collegeId);

    IPage<Classes> findClassesPage(Page<Classes> page,Integer collegeId,Integer majorId);

    IPage<Classes> findClassesPage(Page<Classes> page,Integer collegeId,Integer majorId,Integer classesId);
}
