package com.lhy.systemdemo.pojo;

import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;

/**
 * Description:
 *
 * @since 2020/5/28 17:28
 */
@Data
@Builder
public class JodaMoney {

    private Long id;

    private Money money;

}
