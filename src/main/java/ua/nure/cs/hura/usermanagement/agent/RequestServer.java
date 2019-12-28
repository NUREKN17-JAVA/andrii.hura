package ua.nure.cs.hura.usermanagement.agent;

import java.util.Collection;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RequestServer extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if(message != null) {
			if(message.getPerformative()==ACLMessage.REQUEST) {
				myAgent.send(createReply(message));
			} else {
				Collection users = parseMessage(message);
				((SearchAgent) myAgent).showUsers(users);
			}
		}else {
			block();
		}
	}


	private Collection parseMessage(ACLMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

	private ACLMessage createReply(ACLMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

}