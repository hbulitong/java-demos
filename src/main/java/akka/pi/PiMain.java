package akka.pi;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PiMain {
    static final int numsWorkers = 4;
    static final long numsRounds = 30000;
    static final long numsElements = 100000;
    public static void main(String... args){
        // Create an Akka system
        ActorSystem system = ActorSystem.create("PiSystem");

        // create the result listener, which will print the result and shutdown the system
        final ActorRef stoper = system.actorOf(Props.create(StoperActor.class), "stoper");

        // create the master
        ActorRef master = system.actorOf(Props.create(MasterActor.class, numsWorkers, numsRounds, numsElements, stoper),"master");

        // start the calculation
        master.tell(new CalculateMessage(), system.lookupRoot());
    }
}