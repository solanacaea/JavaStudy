package com.mantu.advance;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;


/**
 * blog http://www.cnblogs.com/mantu/
 * github https://github.com/mantuliu/
 * @author mantu
 *
 */
public class Lesson9DistributedTask {

    public static String  zkNode="";
    public static ZooKeeper zk=null;
    public static void main( String[] args )
    {
        try {
                zk = new ZooKeeper("127.0.0.1:2182", 3000,new Watcher() {
                // 监控所有被触发的事件
                    public void process(WatchedEvent event) {
                    
                    }
                });
                //zk.create("/task","timer".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
                zk.create("/task/timer","timer".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
                zkNode= zk.create("/task/timer/","1".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL).replaceAll("/task/timer/", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 1000, 2000);
    }
}

class MyTask extends TimerTask {
    @Override
    public void run() {
        if(Lesson9DistributedTask.zk!=null){
            try {
                System.out.println(Lesson9DistributedTask.zkNode);
                List<String> list = Lesson9DistributedTask.zk.getChildren("/task/timer", false);
                String temp = null;
                System.out.println(list);
                for(String i : list){
                    if(temp!=null){
                        if(i.compareTo(temp)<0){
                            temp=i;
                        }
                    }
                    else {
                        temp=i;
                    }
                }
                if(temp.equals(Lesson9DistributedTask.zkNode)){
                    System.out.println("timer1 excute");
                }
            } catch (KeeperException e) {
                e.printStackTrace();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

