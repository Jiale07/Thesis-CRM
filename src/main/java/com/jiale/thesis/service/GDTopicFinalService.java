package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDTopicFinal;

import java.math.BigInteger;

public interface GDTopicFinalService {

    GDTopicFinal findGDTopicFinalByStudentId(BigInteger studentId);
    int addGDTopicFinal(GDTopicFinal gdTopicFinal);
}
