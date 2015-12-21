package com.degloba.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation = Propagation.MANDATORY)
@Target(ElementType.TYPE)
public @interface InternalApplicationService {

}
