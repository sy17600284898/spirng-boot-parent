package com.syy.springboot.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Grid
 *
 * @author: shiyan
 * @version: 2019-12-06 15:46
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
    private long total;
    private List rows;
}
