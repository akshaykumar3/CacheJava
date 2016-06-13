
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.sun.tools.classfile.Synthetic_attribute;

import java.util.Map;
import java.util.Queue;

/**
 * Created by akshay.kumar1 on 14/06/16.
 */
public class Hazelcast {

    public static void main(String[] args) {
        Config config = new Config();
        HazelcastInstance hazelcastInstance = com.hazelcast.core.Hazelcast.newHazelcastInstance(config);
        Map<Integer, String> mapCustomers = hazelcastInstance.getMap("customers");
        mapCustomers.put(1, "Joe");
        mapCustomers.put(2, "Ali");
        mapCustomers.put(3, "Aks");

        System.out.println("Customer 1 = "+mapCustomers.get(1));
        System.out.println("Map size = "+mapCustomers.size());

        Queue<String> queueCutomers = hazelcastInstance.getQueue("customers");
        queueCutomers.offer("Tom");
        queueCutomers.offer("Jane");
        queueCutomers.offer("Hank");

        System.out.println("First customer : "+queueCutomers.poll());
        System.out.println("Second customer : "+queueCutomers.peek());
        System.out.println("Queue size : "+queueCutomers.size());
    }
}
