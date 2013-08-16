package net.compor.frameworks.jcf.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Service {

	String name() default Component.USE_METHOD_NAME;
	String description() default Component.USE_METHOD_NAME;
	String requiredServices() default Component.DO_NOT_REQUIRE_SERVICES;
}
