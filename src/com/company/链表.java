package com.company;
import java.util.*;

public class 链表 implements Cloneable {
    public int s = 10;

    static class Node {

        int val;
        Node next;

        Node(int value) {
            val = value;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int value) {
            val = value;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            val = value;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public void reorderList() {
        ListNode head = new ListNode(12);
        head.next = new ListNode(23);
        head.next.next = new ListNode(89);
        head.next.next.next = new ListNode(45);
        head.next.next.next.next = new ListNode(34);
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode preslow = head;
        while (slow != null && fast != null && fast.next != null) {
            preslow = slow;
            slow = slow.next;
            if (fast.next.next == null) {
                fast = fast.next;
            } else {
                fast = fast.next.next;
            }
        }

        ListNode a = head;
        ListNode b = slow;
        preslow.next = null;

        ListNode k = b;
        if (b.next != null) {
            ListNode p = b;
            ListNode q = b.next;
            while (q != null) {
                ListNode r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            k = p;
        }
        b = k;
        System.out.println(a.val);
        System.out.println(b.val);
//System.out.println(a.next.val);
//System.out.println(b.next.val);
//System.out.println(a.next.next.val);
//System.out.println(b.next.next.val);
        while (a != null && b != null) {
            ListNode temp = a.next;
            a.next = b;
            a = a.next;
            b = b.next;
            if (temp == null) {
                if (b == null) {
                    break;
                } else {
                    a.next = b;
                    break;
                }
            } else {
                a.next = temp;
                a = a.next;
            }

        }


    }

    //反转链表
    public Node fanzhuan(Node head) {
        Node p = head;
        Node q = head.next;
        while (q != null) {
            Node r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return head;
    }

    //在m到n之间反转链表
    public ListNode fanzhuanFromMtoN(ListNode head, int m, int n) {
        if (m == n)
            return head;
        ListNode first = head;
        ListNode prev = head;
        ListNode end = head;
        for (int i = 0; i < m - 1; i++) {
            prev = first;
            first = first.next;
        }
        for (int i = 0; i < n - 1; i++)
            end = end.next;
        ListNode tail = end.next;
        end.next = null;
        ListNode p = first;
        ListNode q = first.next;
        while (q != null) {
            ListNode r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        if (p.val != prev.val) {
            prev.next = p;
        }
        q = tail;
        return head;
    }

    //按n个数为一组反转链表
    public Node fanzhuanN(Node head, int k) {
        Node p = head;
        if (head == null || k == 1)
            return head;
        Node q = head.next;
        int len = 0;
        List<Node> temp = new ArrayList<>();
        while (head != null) {
            if (len % k == 0) {
                temp.add(head);
            }
            len += 1;
            head = head.next;
        }
        int w = temp.size();
        if (len == 1 || k > len)
            return p;
        Node result = null;
        int index = 0;

        while (p != null && q != null && len >= k) {

            while (index < k - 1 && q != null) {
                index += 1;

                Node r = q.next;
                q.next = p;
                p = q;
                if (index == k - 1) {
                    temp.add(p);
                    System.out.println(p.val + "adas1");
                }
                q = r;
            }
            if (result == null) {
                result = p;
            }
            p = q;
            if (p == null)
                break;
            q = q.next;
            index = 0;
            len -= k;
        }
        int o = 0;
        if (temp.size() / 2 == 0) {
            while (o < temp.size() && o + w + 1 < temp.size()) {
                temp.get(o).next = temp.get(o + w + 1);
                o += 1;
            }
        } else {
            while (o < temp.size() && o + w + 1 < temp.size()) {
                temp.get(o).next = temp.get(o + w + 1);
                o += 1;
            }
            temp.get(o).next = p;
        }
        return result;
    }

    //判断链表是否存在环
    //使用快慢指针
    //也可以借助集合类
    public boolean panduanHuan(Node head) {
        Node slow = head;
        Node fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    //使用归并算法对链表进行排序
    public Node sortList(Node head) {
        // write code here
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        Node preSlow = head;
        Node slow = head;
        Node fast = head;
        while (slow != null && fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        Node listA = head;
        Node listB = slow;
        preSlow.next = null;
        listA = sortList(listA);
        listB = sortList(listB);
        return merge(listA, listB);
    }

    public Node merge(Node listA, Node listB) {
        Node dummy = new Node(0);
        Node nodeR = dummy;
        Node nodeA = listA;
        Node nodeB = listB;
        while (nodeA != null && nodeB != null) {
            if (nodeA.val < nodeB.val) {
                nodeR.next = nodeA;
                nodeA = nodeA.next;
            } else {
                nodeR.next = nodeB;
                nodeB = nodeB.next;
            }
            nodeR = nodeR.next;
        }
        if (nodeA != null) {
            nodeR.next = nodeA;
        }
        if (nodeB != null) {
            nodeR.next = nodeB;
        }
        return dummy.next;
    }

    //给定一个单链表，其中的元素按升序排序，请将它转化成平衡二叉搜索树（BST）
    //采用归并的方法
    public TreeNode sortedListToBST(ListNode head) {
        // write code here
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (slow != null && fast != null && fast.next != null) {

            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        TreeNode temp = new TreeNode(slow.val);
        if (head.val == slow.val) {
            return temp;
        }
        temp.left = sortedListToBST(head);
        temp.right = sortedListToBST(slow.next);
        return temp;

    }

    public TreeNode sortedArrayToBST(int[] num) {
        int len = num.length;
        if (len == 0) {
            System.out.println("dasda");
            return null;
        } else {
            int mid = len / 2;
            TreeNode temp = new TreeNode((num[mid]));
            if (mid == 0)
                return temp;
            else {

                ArrayList<Integer> m = new ArrayList<>();
                m.addAll(Arrays.asList(Arrays.stream(num).boxed().toArray(Integer[]::new)).subList(0, mid));

                Integer[] m1 = new Integer[m.size()];
                temp.left = sortedArrayToBST(Arrays.stream(m.toArray(m1)).mapToInt(Integer::valueOf).toArray());
                ArrayList<Integer> n = new ArrayList<>();
                n.addAll(Arrays.asList(Arrays.stream(num).boxed().toArray(Integer[]::new)).subList(mid + 1, len));

                Integer[] n1 = new Integer[n.size()];
                temp.right = sortedArrayToBST(Arrays.stream(n.toArray(n1)).mapToInt(Integer::valueOf).toArray());
            }
            return temp;
        }

    }

    //给出一个链表和一个值 ，以 为参照将链表划分成两部分，使所有小于 的节点都位于大于或等于 的节点之前。
    //两个部分之内的节点之间要保持的原始相对顺序。
    //双指针，一个指针保存小于目标值的点，一个指针保存大于等于目标值的点
    public ListNode partition(ListNode head, int x) {
        // write code here
        ListNode small = new ListNode(0);
        ListNode big = new ListNode(0);
        ListNode m = big;
        ListNode n = small;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x) {
                small.next = new ListNode(temp.val);
                small = small.next;
            } else {
                big.next = new ListNode(temp.val);
                big = big.next;
            }
            temp = temp.next;
        }
        small.next = m.next;
        return n.next;
    }

    //给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
    public ListNode deleteDuplicates(ListNode head) {
        // write code here
        Map<Integer, Integer> temp = new LinkedHashMap<>();
        while (head != null) {
            if (temp.containsKey(head.val)) {
                temp.put(head.val, temp.get(head.val) + 1);
            } else {
                temp.put(head.val, 1);
            }
            head = head.next;
        }
        ListNode result = new ListNode(0);
        ListNode result1 = result;
        for (Map.Entry<Integer, Integer> item : temp.entrySet()) {
            if (item.getValue() == 1) {
                result.next = new ListNode(item.getKey());
                result = result.next;
            }
        }
        return result1.next;
    }

    //删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
    public ListNode deleteDuplicates1(ListNode head) {
        // write code here
        Set<Integer> help = new HashSet<>();
        ListNode temp = new ListNode(0);
        ListNode temp1 = head;
        ListNode temp2 = temp;
        while (temp1 != null) {
            if (!help.contains(temp1.val)) {
                System.out.println("dasd");
                help.add(temp1.val);
                temp.next = temp1;
                temp = temp.next;
            }
            temp1 = temp1.next;
        }
        temp.next = null;
        return temp2.next;
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write code here
        ListNode result = new ListNode(0);
        ListNode result1 = result;
        while (true) {
            if (l1 == null && l2 == null)
                break;
            else {
                if (l1 != null && l2 != null) {
                    if (l1.val <= l2.val) {
                        result.next = new ListNode(l1.val);
                        l1 = l1.next;
                    } else {
                        result.next = new ListNode(l2.val);
                        l2 = l2.next;
                    }
                    result = result.next;
                } else {
                    if (l1 != null) {
                        result.next = l1;
                    } else {
                        result.next = l2;
                    }
                    break;
                }
            }
        }
        return result1.next;
    }

    //将给定的链表向右转动k个位置，k是非负数。
    //例如：
    //给定1->2->3->4->5->null ， k=2，
    //返回4->5->1->2->3->null。
    //设置两个指针，定位到末节点和末节点的前K个节点，然后再改变一下结构就可以
    public ListNode rotateRight(ListNode head, int k) {
        // write code here
        if (head == null || k == 0)
            return head;
        ListNode first = head;
        ListNode end = head;
        for (int i = 1; i <= k; i++) {
            if (end.next != null)
                end = end.next;
            else
                end = head;
        }
        while (end.next != null) {
            end = end.next;
            first = first.next;
        }
        if (first == end)
            return head;
        ListNode result = first.next;
        end.next = head;
        first.next = null;
        return result;
    }

    public ListNode swapPairs(ListNode head) {
        // write code here
        if (head == null || head.next == null)
            return head;
        ListNode p = head;
        ListNode q = head.next;
        ListNode result = q;
        while (true) {
            ListNode r = q.next;
            ListNode w = null;
            if (r != null) {
                w = r.next;
            }
            q.next = p;
            if (w != null) {
                p.next = w;
                p = r;
                q = w;
            } else {
                p.next = r;
                break;
            }

        }
        return result;
    }

    //合并k 个已排序的链表并将其作为一个已排序的链表返回。分析并描述其复杂度。
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        ListNode head = new ListNode(0);
        ListNode result = head;
        boolean flag = true;
        if (lists.size() == 0)
            return result.next;
        else {
            while (flag) {
                flag = false;
                int temp = Integer.MAX_VALUE;
                int index = -1;
                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i) != null && lists.get(i).val < temp) {
                        temp = lists.get(i).val;
                        index = i;
                    }
                }
                if (index != -1) {
                    flag = true;
                    head.next = lists.get(index);
                    lists.set(index, lists.get(index).next);
                    head = head.next;
                }
            }
            return result.next;
        }
    }

    //删除链表倒数第n个节点
    //使用双指针，从而定位到倒数第k+1个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // write code here
        if (head == null) {
            return head;
        }
        ListNode before = head;
        ListNode after = head;
        for (int i = 0; i < n; i++) {
            if (after.next != null)
                after = after.next;
            else {
                return head.next;
            }
        }
        while (after.next != null) {
            before = before.next;
            after = after.next;

        }
        before.next = before.next.next;
        return head;
    }
    //给定两个代表非负数的链表，数字在链表中是反向存储的（链表头结点处的数字是个位数，第二个结点上的数字是十位数...），求这个两个数的和，结果也用链表表示。
    public ListNode addTwoNumbers (ListNode l1, ListNode l2) {
        // write code here
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode result = new ListNode(0);
        ListNode result1 = result;
        int jin = 0;
        while(l1 != null || l2 != null){
            if(l1 == null){
                while (l2 != null && l2.val + jin > 10){
                    jin = 1;
                    result.next = new ListNode(0);
                    result = result.next;
                    l2 = l2.next;
                }
                if(l2 !=null){
                    result.next = new ListNode(l2.val + jin);
                    result.next.next = l2.next;
                    return result1.next;
                }else{
                    result.next = new ListNode(1);
                    return result1.next;
                }
            }
            if(l2 == null){
                while (l1 != null && l1.val + jin > 10){
                    jin = 1;
                    result.next = new ListNode(0);
                    result = result.next;
                    l1 = l1.next;
                }

                if(l1 !=null){
                    result.next = new ListNode(l1.val + jin);
                    result.next.next = l1.next;
                    return result1.next;
                }else{
                    result.next = new ListNode(1);
                    return result1.next;
                }
            }
            else {

                if(l1.val + l2.val +jin >=10){
                    result.next = new ListNode((l1.val + l2.val + jin)%10);
                    jin = 1;
                }else{
                    result.next = new ListNode(l1.val + l2.val + jin);
                    jin = 0;
                }
                result = result.next;
                l1 = l1.next;
                l2 = l2.next;

            }
        }
        if(jin == 1){
            result.next = new ListNode(1);
        }
        return result1.next;
    }
    //判断链表是否有环
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        else{
            ListNode slow = head;
            ListNode fast = head;
            while(slow != null && fast != null && fast.next != null){
                slow =slow.next;
                fast = fast.next.next;
                if(slow == fast)
                    return true;
            }
            return false;
        }
    }
    //对于一个给定的链表，返回环的入口节点，如果没有环，返回null
    //拓展：
    //你能给出不利用额外空间的解法么？
    public ListNode detect(ListNode head){
        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;

            if(fast==slow){                 //利用快慢指针找相遇点
                ListNode slow2=head;    //设置以相同速度的新指针从起始位置出发
                while(slow2!=slow){      //未相遇循环。
                    slow=slow.next;
                    slow2=slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
    //求两个链表的公共节点
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 ==null || pHead2 == null)
            return null;
        ListNode node1 = pHead1;
        ListNode node2 = pHead2;
        while(pHead1 != pHead2){
            if(pHead1 == null)
                pHead1 = node2;
            else
                pHead1 = pHead1.next;
            if(pHead2 == null)
                pHead2 = node1;
            else
                pHead2 = pHead2.next;

        }
        return pHead1;
    }
    //给定一个无序单链表，实现单链表的选择排序(按升序排序)。
    public ListNode sortInList (ListNode head) {
        // write code here
        if (head == null || head.next == null)
            return head;
        else {
            ListNode temp = head;
            while (temp != null) {
                ListNode temp1 = temp;
                ListNode temp2 = temp.next;
                while(temp2 != null){
                    if(temp2.val < temp1.val){
                        temp1 = temp2;
                    }
                    temp2 = temp2.next;
                }
                if(temp1 != temp){
                    int p = temp.val;
                    temp.val = temp1.val;
                    temp1.val = p;

                }
                temp = temp.next;
            }
            return head;
        }
    }
    public ListNode oddEvenList (ListNode head) {
        // write code here
        if(head == null || head.next == null)
            return head;
        ListNode ji = new ListNode(1);
        ListNode ou = new ListNode(1);
        ListNode result = ji;
        ListNode prev = ou;
        boolean flag = true;
        while(head != null){
            if(flag){
                ji.next = head;
                ji = ji.next;
            }else{
                ou.next = head;
                ou = ou.next;
            }
            flag = !flag;
            head = head.next;
        }
        ou.next = null;
        ji.next = prev.next;
        return result.next;
    }
    //实现链表的快速排序
    public ListNode quickSort(ListNode start, ListNode end){
        if(start == end){
            return start;
        }else{
            int val = start.val;
            ListNode preL = new ListNode(0);
            ListNode preR = start;
            ListNode left = start;
            ListNode right = left.next;
            preL.next = left;
            while(right != end){
                while(right != end && right.val >= val){
                    right = right.next;
                    preR = preR.next;
                }
                if(right != end && right.val < val){
                    left = left.next;
                    preL = preL.next;
                    ListNode temp = right.next;
                    right.next = left.next;
                    preL.next = right;
                    preR.next = left;
                    left.next = temp;
                    ListNode temp1 = right;
                    right = left.next;
                    left = right;
                }
            }
            //交换一下头结点和left结点

            return left;
        }
    }
    public void quickMain(ListNode start, ListNode end){
        ListNode mid = quickSort(start, end);
        quickMain(start, mid);
        quickMain(mid.next, end);

    }


}
