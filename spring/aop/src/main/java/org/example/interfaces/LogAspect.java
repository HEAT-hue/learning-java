package org.example.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Run at run time
@Target(ElementType.METHOD) // Can only be used on methods
public @interface LogAspect {

}
