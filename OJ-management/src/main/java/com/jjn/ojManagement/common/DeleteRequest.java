package com.jjn.ojManagement.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author 焦久宁
 * @date 2023/12/20
 */
@Data
public class DeleteRequest implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;
}
