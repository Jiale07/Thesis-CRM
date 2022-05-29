package com.jiale.thesis.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class LoginUserInfoResVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private BigInteger userId;
    private List<Integer> roleIdList;
}
