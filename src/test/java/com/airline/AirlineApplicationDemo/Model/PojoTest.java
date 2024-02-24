package com.airline.AirlineApplicationDemo.Model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PojoTest {
    private static final String POJO_PACKAGE = "com.airline.AirlineApplicationDemo.Model";

    private final List<PojoClass> pojoClass = PojoClassFactory.getPojoClassesRecursively(
            POJO_PACKAGE, new FilterPackageInfo());

    @Test
    public void testPojoStructure(){
        Validator validator = ValidatorBuilder
                .create()
                .with(new GetterTester())
                .with(new SetterTester())
                .build();
        validator.validate(pojoClass);
    }

    @Test
    public void testToString(){
        pojoClass.forEach(e->{
            e.getPojoMethods().forEach(c->{
                if(c.getName().equals("toString")){
                    try{
                        assertNotNull(c.invoke(e.getClazz().getName()));
                    }catch (Exception ex){
                        assertTrue(false);
                    }
                }
            });
        });
    }

    @Test
    public void testHashCode(){
        pojoClass.forEach(e->{
            e.getPojoMethods().forEach(c->{
                if(c.getName().equals("hashCode")){
                    try{
                        assertNotNull(c.invoke(e.getClazz().newInstance()));
                    }catch (Exception ex){
                        assertTrue(false);
                    }
                }
            });
        });
    }
}
