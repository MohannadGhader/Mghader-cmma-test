import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dao.omni.common.pull.impl.OmniCommonPullDAOImpl;
import com.path.dbmaps.vo.ErrorVO;
import com.path.ws.security.manager.OAuthRegistrationService;
public class RestClient
{

    public static void main(String args[])
    {
	try
	{
//	    LoginBOImpl LoginBO = new LoginBOImpl();
//	    String encryptedPwd = LoginBO.returnEncryptedPassword("9","123","3");

//	    Object obj = StringUtil.nullEmptyToValue(new BigDecimal(1), "-1");




	    /*	    String s = null;
	    System.out.println(s);
	    TransactionsCO co = new TransactionsCO();
	    if(true)
	    {
		System.out.println(co);
	    }
	    Object obj;

	    obj = MockDataEngine.parse(TransactionsCO.class, 0);
	    System.out.println(obj);

	    obj = MockDataEngine.parse(TransactionsCO.class, -1);
	    System.out.println(obj);

	    obj = MockDataEngine.parse(TransactionsCO.class, 1);
	    System.out.println(obj);*/
	    //obj = PathJSONUtil.deserialize(line);


	    /*	    
	    //Error Message Translation
	    request.setLanguage("EN");
	    request.setException(31515);
	     */


	    /*	    //Account Types
	    request.setAccountCategory("D");
	    List<String> gls = new  ArrayList<String>();
	    gls.add("201101");
	    gls.add("201103");
	    request.setPermittedGLs(gls);
	     */

	    /*
	    //OmniCorePullServices   Account Information Request, Currencies Request,  Account Types
	    String baseUrl = "http://localhost:8180/omni_services/pathservices";
	    String secureServiceName1 = "/rest/omniCorePull";
	    List<Object> providers = new ArrayList<Object>();
	    providers.add(new JacksonJaxbJsonProvider());
	    OmniCorePullServices unSecureRestService = JAXRSClientFactory.create(baseUrl + "/" + secureServiceName1, OmniCorePullServices.class, providers);
	    Client client = WebClient.client(unSecureRestService);
	    client.accept(MediaType.APPLICATION_JSON);
	    client.type(MediaType.APPLICATION_JSON);

	    OcReasonsRequest request = new OcReasonsRequest();
	    request.setCompCode(BigDecimal.ONE);
	    request.setChannelId(new BigDecimal(6));
	    request.setAppId(new BigDecimal(3));
	    request.setLanguage("EN");
	    request.setReasonType("1");
	    request.setVsCode("1");
	    //request.setServiceMethod("2");
	    //request.setUserName("test");
	    //request.setUserId("naderh");
	    //request.setAccountCategory("G");
	    //request.setCurrencyCode(new BigDecimal(640));
	    // request.setBillType(new BigDecimal(640));
	    //request.setGlCode("1");
	    //request.setCode(new BigDecimal(210308));
	    //request.setCifSubNo("teaaast");

	    //Validation.buildDefaultValidatorFactory().getValidator().validate(request);
	    //request.setTransactionTypeCode("'code123','123','1'");
	    //request.setCURRENCY_CODE(new BigDecimal(840));
	    //request.setCodesInclude("840,12");
	    OmniCommonResponse  response = unSecureRestService.returnLostDocument_service(request);
	    System.out.println(response);

	     */

	    /*  
	    //OmniCommonServices
	    String baseUrl = "http://localhost:8180/omni_services/pathservices";
	    String secureServiceName1 = "rest/omniCommon";
	    List<Object> providers = new ArrayList<Object>();
	    providers.add(new JacksonJaxbJsonProvider());
	    OmniCommonServices unSecureRestService = JAXRSClientFactory.create(baseUrl + "/" + secureServiceName1, OmniCommonServices.class, providers);
	    Client client = WebClient.client(unSecureRestService);
	    client.accept(MediaType.APPLICATION_JSON);
	    client.type(MediaType.APPLICATION_JSON);

	    BeneficiariesRequest sc = new BeneficiariesRequest();
	    sc.setApiCode(new BigDecimal(100026));
	    sc.setChannelId(new BigDecimal(0));
	    sc.setAppId(new BigDecimal(0));
	    sc.setCompCode(BigDecimal.ONE);
	    sc.setBenefName("gilbert");
	    sc.setUserId("1");
	    OmniResponseBaseObject arr = unSecureRestService.checkAgencyNickName_service(sc);	// TODO to replace the call of service by call of BO immediately
	    System.out.println(arr);*/



	    //Oauth2   Account Information Request, Currencies Request,  Account Types
	    String baseUrl = "http://localhost:8180/omni_services/pathservices";
	    String secureServiceName1 = "/oauthRegistration";
	    List<Object> providers = new ArrayList<Object>();
	    providers.add(new JacksonJaxbJsonProvider());
	    OAuthRegistrationService unSecureRestService = JAXRSClientFactory.create(baseUrl + "/" + secureServiceName1, OAuthRegistrationService.class, providers);
	    Client client = WebClient.client(unSecureRestService);
	    client.accept(MediaType.APPLICATION_JSON);
	    client.type(MediaType.APPLICATION_JSON);


	    //OAuthClientRegistration clientRegistration = new returnOAuthClientRegistration(new PathWsClientCredentials("CSM_NAME", "CSM_PWD"));

	    // OAuthClientRegistration  response = unSecureRestService.registerClient(clientRegistration);
	    

	    
	    //System.out.println(response);

	  /*  OAuthClientRegistration  response = PathWsSecurityUtil.registerClientApplication(baseUrl, new PathWsClientCredentials("CSM_NAME", "CSM_PWD"));
	    System.out.println(response);
	    ClientAccessToken  token = PathWsSecurityUtil.returnClientAccessToken(baseUrl,response);*/
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /*
	    //OmniCommonPushServices
	    String baseUrl = "http://localhost:8180/omni_services/pathservices";
	    String secureServiceName1 = "rest/omniCommonPush";
	    List<Object> providers = new ArrayList<Object>();
	    JacksonJsonProvider provider = new JacksonJsonProvider();
	    provider.setMapper(new PathObjectMapper());
	    providers.add(provider);
	    OmniCommonPushServices unSecureRestService = JAXRSClientFactory.create(baseUrl + "/" + secureServiceName1, OmniCommonPushServices.class, providers);
	    Client client = WebClient.client(unSecureRestService);
	    client.accept(MediaType.APPLICATION_JSON);
	    client.type(MediaType.APPLICATION_JSON);

	    OC_CARD_REQUESTVO   sc = new OC_CARD_REQUESTVO();
	    sc.setACCOUNT_FORMAT("F111");
	    sc.setAPPROVED_DATE(new Date());
	    sc.setCARD_REQUEST_ID(new BigDecimal(25));
	    OmniResponseBaseObject  arr = unSecureRestService.doCardRequest_service(sc);	// TODO to replace the call of service by call of BO immediately
	    System.out.println(arr);

	     */
	    /*
	    //OmniCommonPullServices
	    String baseUrl = "http://localhost:8180/omni_services/pathservices";
	    String secureServiceName1 = "rest/omniCommonPull";
	    List<Object> providers = new ArrayList<Object>();
	    providers.add(new JacksonJaxbJsonProvider());
	    OmniCommonPullServices unSecureRestService = JAXRSClientFactory.create(baseUrl + "/" + secureServiceName1, OmniCommonPullServices.class, providers);
	    Client client = WebClient.client(unSecureRestService);
	    client.accept(MediaType.APPLICATION_JSON);
	    client.type(MediaType.APPLICATION_JSON);

	    ManageOmniUsersRequest  sc = new ManageOmniUsersRequest();
	    //sc.setCreditTypeCode("123");
	    sc.setCompCode(new BigDecimal(1));
	    sc.setUserCifNo("2");
	    sc.setLovTypeId(new BigDecimal(70));
	    sc.setBusinessProfileTableRef(new BigDecimal(22));
	    sc.setAppId(new BigDecimal(1));
	    sc.setChannelId(new BigDecimal(1));
	    ManageOmniUsersResponse   arr = unSecureRestService.returnOmniUsers_service(sc);	// TODO to replace the call of service by call of BO immediately
	    System.out.println(arr);
	     */

	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}
    }
}
