package legacy;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import javax.ejb.CreateException;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/proveedorrecepcion")
})

public class ProcesarProveedorBean implements MessageDrivenBean, MessageListener {

	public ProcesarProveedorBean() {
		// TODO Auto-generated constructor stub
	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
		throws EJBException {
		// TODO Auto-generated method stub

	}

	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		TextMessage msg = null;
		
		try {
			if (arg0 instanceof TextMessage) {
				msg = (TextMessage) arg0;
				System.out.println("MESSAGE BEAN: Mensaje recibido: "+ msg.getText() );
			} else {
				System.out.println("MESSAGE BEAN: Mensaje de tipo incorrecto ");
			}
				
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() throws CreateException {
		// TODO Auto-generated method stub
	}
}
