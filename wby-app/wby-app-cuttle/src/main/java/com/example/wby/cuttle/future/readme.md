实现Runnable接口和实现Callable接口的区别：

1、Runnable是自从java1.1就有了，而Callable是1.5之后才加上去的。

2、Callable规定的方法是call(),Runnable规定的方法是run()。

3、Callable的任务执行后可返回值，而Runnable的任务是不能返回值(是void)。

4、call方法可以抛出异常，run方法不可以。

5、运行Callable任务可以拿到一个Future对象，表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。通过Future对象可以了解任务执行情况，可取消任务的执行，还可获取执行结果。

6、加入线程池运行，Runnable使用ExecutorService的execute方法，Callable使用submit方法。