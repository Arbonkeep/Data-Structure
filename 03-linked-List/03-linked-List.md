# 链表
    1. 概念(什么是链表)
        * 链表是真正的动态数据结构
        * 数据存储在节点(Node)中，节点之间相互连接。
        * 在存储数据时，存储了一个数据，那么node就会指向下一个存储数据的位置，如果node == null，那么就说明到了链表
          的结尾
        * 优点：真正的动态，不需要处理固定容量的问题
        * 缺点：丧失了随机访问的能力（简单理解就是不能通过索引访问元素）
        * 链表不适合用于索引有语义的情况

        * 代码实现节点（Node）
            在链表中定义内部类实现
    
    2. 在链表中添加元素
        * 在链表头添加添加元素
            首先定义head描述头元素，然后定义node节点，将需要添加的元素的node节点指向head(即node.next = head),此时
            这个元素就会成为新的链表头，然后需要将head指向node(即head=node，就是说让head指向新添加的元素)，最后维护
            size变量即可，这就完成添加，node节点也就释放了

        * 在链表的中间添加元素
            实现的关键：找到要添加的节点的前一个节点(prev)
            首先需要添加元素的节点指向后一个元素的节点，然后需要添加元素位置的前一个元素的node节点指向需要插入元素的节点
            （注意如果先后顺序调转就不能实现，因为调转后prev已经指向了node）
            即 node.nxt = prev.next,prev.next = node

    3. 为链表设立虚拟头节点
        * 需要设立虚拟头结点的原因：在链表头添加元素是没有前一个节点。
        * 通过创建一个dunnyHead（虚拟头结点）来实现添加头元素，这样就实现了相对于所有元素而言都有了一个prev。

    4. 链表的查询，修改和遍历
        * 定义获取链表中元素的方法
            1. 判断索引的合法性
            2. 遍历（由于我们需要找到index位置的元素，所以需要从dummyHead.next（链表中的第一个元素）开始遍历）
        * 定义获取第一个元素的方法
            复用获取元素的方法
        * 定义获取最后一个元素的方法
            复用获取元素的方法
        * 定义修改第index位置的的元素为e的方法（set）
            1. 判断索引的合法性
            2. 遍历（找到第index位置的元素）
            3. 将e设置为index位置的元素
        * 定义查找链表中是否有元素e的方法
            1. 获取当前索引的节点
            2. 遍历查看是否有元素与e相等
        * 重写toString方法

    5. 链表中删除元素
        * 删除原理
            首先，我们需要找到需要删除元素的前一个节点(prev)，将这个节点指向需要删除元素的下一个节点            
        * 删除实现
            1. prev.next = delNode.next
            2. delNode.next = null
        * 链表删除中常见的错误
            获取到要删除的元素（cur），直接将cur指向cur.next，这样并没有删除，这样只是将cur指向的变量
            指向了下一个节点。这样做这是将cur的引用指向了cur.next
        * 定义删除第一个元素的方法
        * 定义删除最后一个元素的方法
        * 链表的时间复杂度分析：
            增删改查都是O(n)级别的操作，但是其中删除第一个位置的元素与从第一个位置增加元素都是O(1)级别
            的复杂度。
        * 数组与链表的比较
            数组能直接通过索引找到元素，这是其一大优势，但是链表在第一个元素进行操作时，其时间复杂度也是
            O(1)级别的，此外链表还是一个动态的，所以链表也是具有自己的优势的（不会浪费内存空间）

    6. 使用链表实现栈
        在实现链表栈之后，对链表栈与数组栈进行性能测试。正常情况：链表栈会时间复杂度快一点点，但是这个
        它们两者的时间比较时复杂的，因为linkedListStack中包含更多的new的操作（new Node()）

    7. 使用链表来实现队列
        * 需要改进链表
            * 要求在链表的尾部(tail)也能是O(1)的时间复杂度，但是我们只有在添加元素才能达到O(1)级别的时间
              复杂度，在删除元素上依然是O(n)级别的复杂度，因为删除元素依然需要从头开始
            * 根据上面特点以及队列先进先出的特点，我们就能知道需要将链表头作为队列的队首（负责出队），链表
              的尾作为队列的队尾（负责入队）




        



         