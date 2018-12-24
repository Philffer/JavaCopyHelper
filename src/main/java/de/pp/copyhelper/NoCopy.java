package de.pp.copyhelper;

import java.lang.annotation.*;

/**
 * @author Philipp Pfeiffer
 * Annotation on Methods representing values, that should not be copied by the CopyHelper
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface NoCopy {

}
