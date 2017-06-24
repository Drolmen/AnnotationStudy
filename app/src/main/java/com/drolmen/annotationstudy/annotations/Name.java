package com.drolmen.annotationstudy.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by drolmen on 2017/6/24.
 */

@StringDef({Name.JORK,Name.SMITH,Name.TOM})
@Retention(RetentionPolicy.SOURCE)
public @interface Name {
    public static final String TOM = "tom";
    public static final String JORK = "jork";
    public static final String SMITH = "SMITH";
}