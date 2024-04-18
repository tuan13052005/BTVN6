package com.tn.req;

import lombok.Data;

import java.util.Date;

@Data
public class AccountReq {

    private String accountName;

    private Date birthday;

    private String address;
}
