可以用并查集来写

---

Kruksal算法可跑， 把每个friend看成一个node。每一个连接看成一个边。

如果遍历完全部的边，并且这个图只有一个clutser，那么显然无论我的id是什么，我一定和所有人是好友。

并查集find看所有人的爹，检查所有人是否都是同一个爹。

Weblab test很松
普通版能过3/3但是不一定能过hidden

我这个代码是用OI题库测过的

![image-20221216021703464](C:\Users\ymche\AppData\Roaming\Typora\typora-user-images\image-20221216021703464.png)