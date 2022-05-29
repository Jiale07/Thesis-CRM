package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDThesisInput;

import java.math.BigInteger;
import java.util.List;

public interface GDThesisInputService {
    List<GDThesisInput> findGDThesisInputListByStudentId(BigInteger studentId);

    List<GDThesisInput> findGDThesisInputListByGDThesisId(Long gdThesisId);

    int addGDThesisInput(GDThesisInput gdThesisInput);



}
