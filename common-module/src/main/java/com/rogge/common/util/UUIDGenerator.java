package com.rogge.common.util;

import java.util.UUID;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/03/21
 * @since 1.0.0
 */
public class UUIDGenerator {
    private UUIDGenerator() {
    }

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        str = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return str.substring(0, 20);
    }

}
