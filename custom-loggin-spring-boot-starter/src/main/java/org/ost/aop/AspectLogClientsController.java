package org.ost.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectLogClientsController {

    //проверяем принадлежит ли класс слою контроллера (@within проверяет аннотации над классом)
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {}

    //проверяем принадлежит ли класс слою сервиса (within проверяет название класса)
//    @Pointcut("within(org.ost.*.services.*ServiceImpl)")
//    public void isServiceLayer() {}

    //this смотрит реализует ли прокси класс Repository, target смотрит на объект в прокси
//    @Pointcut("this(org.springframework.stereotype.Repository)")
//    @Pointcut("target(org.springframework.stereotype.Repository)")
//    public void isRepositoryLayer() {}

    //надо понимать что будут проверяться все классы и методы, что долго, лучше добавить доп условие
//    @Pointcut("isControllerLayer() && @annotation(org.springframework.*.web.bind.annotation.GetMapping)")
//    public void isGetMapping() {}

    //если после Model указать *, то будет ожидаться 2 параметра, первый Model
//    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)") //сколько угодно параметров после Model
//    public void hasModelArg() {}

    //проверяем бин, можно указать конкретный бин
//    @Pointcut("bean(*Service)")
//    public void isServiceLayerBean() {}

    @Pointcut("isControllerLayer() && execution(public * createClient(*, *))")
    public void isCreateClient() {}

    @Before(value = "isCreateClient() && target(controller)", argNames = "controller")
    public void addLoggingBefore(Object controller) {
        log.info("Start create new client in createClient method in class {}.", controller);
    }

    @AfterReturning(value = "isCreateClient() && target(controller)", argNames = "controller")
    public void addLoggingAfter(Object controller) {
        log.info("Create new client is successful in class {}.", controller);
    }

    @AfterThrowing(value = "isCreateClient() && target(controller)", argNames = "controller")
    public void addLoggingAfterThrowing(Object controller) {
        log.error("Error create new client in class {}.", controller);
    }

}
