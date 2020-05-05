package com.smgeek.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**表示服务
 * @author ZEXIN HUANG
 * @version 1.0
 * @date 2020-04-23 23:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String []parameterTypes;

    public static ServiceDescriptor from(Class claszz, Method method){

        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(claszz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class [] paramterClasses = method.getParameterTypes();
        String []  parameterTypes = new String [paramterClasses.length];
        for(int i=0;i<paramterClasses.length;i++){
            parameterTypes[i]=paramterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;

        if(obj==null||getClass()!= obj.getClass())return false;

        ServiceDescriptor that = (ServiceDescriptor) obj ;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "clazz="+clazz
                + ",method="+method
                + ",returnType=" + returnType +
                ",parameterTypes="+Arrays.toString(parameterTypes);
    }
}
