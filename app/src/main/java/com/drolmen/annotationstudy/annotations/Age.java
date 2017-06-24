package com.drolmen.annotationstudy.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by drolmen on 2017/6/24.
 */

@IntDef({Age.I1,Age.I2,Age.I3})
@Retention(RetentionPolicy.SOURCE)
public @interface Age {
    public static final int I1 = 1;
    public static final int I2 = 2;
    public static final int I3 = 3;
}
