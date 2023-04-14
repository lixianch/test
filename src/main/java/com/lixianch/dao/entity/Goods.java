package com.lixianch.dao.entity;

import java.util.Date;
import lombok.Data;

/**
 * created by lixianch on 2023/4/14
 */
@Data
public class Goods {
    private Long id;
    private String goodsCode;
    private String goodsName;
    private Date created;
    private Date modified;
    private Integer yn;
}
