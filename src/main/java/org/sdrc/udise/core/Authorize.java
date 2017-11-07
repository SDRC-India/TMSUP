package org.sdrc.udise.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Harsh Pratyush (harsh@sdrc.co.in)
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {
	
	public String feature() default "default" ;
	public String permission() default "default" ;

}
