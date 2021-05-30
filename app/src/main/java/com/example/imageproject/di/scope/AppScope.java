package com.example.imageproject.di.scope;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Documented
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
