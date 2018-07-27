package hu.virgo.courses.hibernate.lesson10.service;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Singleton
@Startup
public class Bootstrap {

    @Inject
    Lockservice service;
    private static final Object lock = new Object();

    @Resource
    private ManagedExecutorService mes;


    @PostConstruct
    private void init() {
//        CompletableFuture.allOf(
//                CompletableFuture.supplyAsync(() -> service.lock("First", 5, LockModeType.OPTIMISTIC_FORCE_INCREMENT), mes)
//                        .exceptionally(t -> {
//                            System.out.println("First failed");
//                            return null;
//                        })
//                        .thenAccept(System.out::println)
//                ,
//                CompletableFuture.supplyAsync(() -> service.lock("Second", 8, LockModeType.OPTIMISTIC_FORCE_INCREMENT), mes)
//                        .exceptionally(t -> {
//                            System.out.println("Second failed");
//                            return null;
//                        })
//                        .thenAccept(System.out::println)
//        );

        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> service.load(5, LockModeType.PESSIMISTIC_FORCE_INCREMENT), mes)
                        .exceptionally(t -> {
                            System.out.println("First failed");
                            throw new RuntimeException(t);
                        })
                        .thenAccept(System.out::println),
                CompletableFuture.supplyAsync(() -> {
                    wait.accept(1);
                    return service.lock("Second", 2, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                }, mes)
                        .exceptionally(t -> {
                            System.out.println("Second failed");
                            throw new RuntimeException(t);
                        })
                        .thenAccept(System.out::println)
        );

    }


    private Consumer<Integer> wait = (sec) -> {
        synchronized (lock) {
            try {
                TimeUnit.SECONDS.timedWait(lock, sec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };}
