package com.rogge.common.annotation;

import java.lang.annotation.*;

/**
 * 类、方法等描述
 * 
 * @author droxy
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {

	/**
	 * 描述说明
	 * 
	 * @return
	 */
	String value();

}
