package com.path.lib.social.Twitter;

public class TwitterServices
{

    public void sendTwitterMessage(MessageElements messageElement){
	
	int companyCode=1;
	String messageDesc="";
	String[] arg = new String[2];
		messageDesc="----------------------------------------------------------------------------------------------\n";
		messageDesc+=messageElement.getMessageCode();
		messageDesc+="----------------------------------------------------------------------------------------------\n";
		
		arg[0] ="@"+messageElement.getSenderAccount();
		arg[1]=messageDesc;

	/*try {
		DirectMessage message = twitter.sendDirectMessage(arg[0], arg[1]);
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	}
}
