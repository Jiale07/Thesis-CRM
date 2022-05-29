package com.jiale.thesis.service;

import com.jiale.thesis.entity.GD.GDTopicProperty;

import java.math.BigInteger;

public interface GDTopicPropertyService {

    int updateTopicProperty(GDTopicProperty gdTopicProperty);

    GDTopicProperty findGDTopicProperty(BigInteger topicId);
}
