package akka.pi;

import akka.actor.UntypedAbstractActor;

public class WorkerActor extends UntypedAbstractActor{

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof WorkMessage){
            WorkMessage wmsg = (WorkMessage) message;
            int start = wmsg.getRound() * wmsg.getNumsElements();
            int end = (wmsg.getRound()+1) * wmsg.getNumsElements() -1;
            double result = 0;
            for (int i = start; i <= end; ++ i){
                double flag = 1 - i%2*2;
                result += flag / (2 * i +1);
            }
            getSender().tell(new ResultMessage(result), getSelf());
        }else{
            unhandled(message);
        }
    }
}
