/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogProvider {

    @Produces
    Logger getLogger(InjectionPoint injectPoint) {
        return LoggerFactory.getLogger(injectPoint.getMember().getDeclaringClass().getClass());
    }
           
}
