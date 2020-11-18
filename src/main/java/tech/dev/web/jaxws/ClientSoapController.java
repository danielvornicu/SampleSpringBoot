package tech.dev.web.jaxws;

import com.sei.generate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ws.client.core.WebServiceTemplate;
import tech.dev.to.ClientTO;
import tech.dev.web.common.base.AbstractSearchEditController;
import tech.dev.web.formulaires.ClientForm;

import java.util.List;

/**
 * Description de la classe
 * <p>
 * Date: 23/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Controller
@RequestMapping(ClientSoapController.REQUEST_MAPPING)
@SessionAttributes({
        ClientSoapController.CLIENT_FORM
})
public class ClientSoapController extends AbstractSearchEditController<ClientTO, ClientForm> {

    public static final String REQUEST_MAPPING = "/wsclient";
    //vues dans le repertoire wspages
    private static final String ROOT_VUE = "wspages/wsclient";

    public static final String CLIENT= "client";
    public static final String CLIENT_FORM = "clientForm";
    public static final String CLIENTS_LISTE = "listeClients";


    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Override
    protected String getRootView() {
        return ROOT_VUE;
    }

    protected String getRootLink(){
        // ".." + REQUEST_MAPPING
        return PARENT_DIRECTORY + REQUEST_MAPPING.replace("/","");
    }

    @Override
    protected void initializeIndexTO(ModelMap model) {
        //liste des TO
        //List<ClientTO> clients = clientService.findAllFillTO();

//        ClientWebService service = new ClientWebService() ;
//        //Get the web service proxy or port from the web service client factory. This port is nothing but the stub for the web service created.
//        ClientWebServ servicePort = service.getClientWebServicePort();
//        List<tech.dev.web.jaxws.sei.ClientTO> clients = servicePort.fetchClients();

        ObjectFactory factory = new ObjectFactory();
        FetchClientsRequest request = factory.createFetchClientsRequest();

        FetchClientsResponse response = (FetchClientsResponse) webServiceTemplate.marshalSendAndReceive(request);
        List<com.sei.generate.ClientTO> clients = response.getReturns();

        model.put(CLIENTS_LISTE, clients);
    }

    @Override
    protected void initializeShowTO(Long id, ClientForm form, ModelMap model) {
        //ClientTO client = clientService.findByIdFillTO(id);

//        ClientWebService service = new ClientWebService() ;
//        //Get the web service proxy or port from the web service client factory. This port is nothing but the stub for the web service created.
//        ClientWebServ servicePort = service.getClientWebServicePort();
//        tech.dev.web.jaxws.sei.ClientTO client = servicePort.fetchClientById(id);

        // on definie redirectUrl(../wsclient) en utilisant REQUEST_MAPPING( /wsclient )
        // et pas ROOT_VUE( wspages/wsclient ) pour gerer un sous repertoire wspages
        // donc getRedirectAfterEdit() dans le controlleur de base est decalé d'un pas
        form.setRedirectUrl(getRootLink());

        ObjectFactory factory = new ObjectFactory();
        FetchClientByIdRequest request = factory.createFetchClientByIdRequest();
        request.setArg0(id);

        FetchClientByIdResponse response = (FetchClientByIdResponse) webServiceTemplate.marshalSendAndReceive(request);

        com.sei.generate.ClientTO client = response.getReturn();

        model.put(CLIENT, client);
    }

    @Override
    protected void initializeNewTO(ClientForm form, ModelMap model) {

        // on definie redirectUrl(../wsclient) en utilisant REQUEST_MAPPING( /wsclient )
        // et pas ROOT_VUE( wspages/wsclient ) pour gerer un sous repertoire wspages
        // donc getRedirectAfterEdit() dans le controlleur de base est decalé d'un pas
        form.setRedirectUrl(getRootLink());

        fillModel(form, model, null);
    }

    @Override
    protected void initializeEditTO(Long id, ClientForm form, ModelMap model) {
        try {
            //ClientTO client = clientService.findByIdFillTO(id);

//            ClientWebService service = new ClientWebService() ;
//            //Get the web service proxy or port from the web service client factory. This port is nothing but the stub for the web service created.
//            ClientWebServ servicePort = service.getClientWebServicePort();
//            tech.dev.web.jaxws.sei.ClientTO client = servicePort.fetchClientById(id);

            // on definie redirectUrl(../wsclient) en utilisant REQUEST_MAPPING( /wsclient )
            // et pas ROOT_VUE( wspages/wsclient ) pour gerer un sous repertoire wspages
            // donc getRedirectAfterEdit() dans le controlleur de base est decalé d'un pas
            form.setRedirectUrl(getRootLink());

            ObjectFactory factory = new ObjectFactory();
            FetchClientByIdRequest request = factory.createFetchClientByIdRequest();
            request.setArg0(id);
            FetchClientByIdResponse response = (FetchClientByIdResponse) webServiceTemplate.marshalSendAndReceive(request);

            com.sei.generate.ClientTO client = response.getReturn();

            //clientTO web service -> clientTO clasique
            ClientTO clientTO =  new ClientTO();
            clientTO.setId(client.getId());
            clientTO.setPrenom(client.getPrenom());
            clientTO.setNom(client.getNom());

            fillModel(form, model, clientTO);
        } catch (Exception e) {
        }
    }

    private void fillModel(ClientForm form, ModelMap model, ClientTO to) {
        form.initForm(to);
        model.put(CLIENT_FORM, form);
    }

    @Override
    protected boolean saveTO(ClientTO to, ModelMap model, RedirectAttributes redirectAttributes, boolean isCreation) {
        //clientService.saveTO(to, isCreation);

//        ClientWebService service = new ClientWebService() ;
//        //Get the web service proxy or port from the web service client factory. This port is nothing but the stub for the web service created.
//        ClientWebServ servicePort = service.getClientWebServicePort();
//        tech.dev.web.jaxws.sei.ClientTO clientTO =  new tech.dev.web.jaxws.sei.ClientTO();
        //servicePort.saveClient(clientTO, isCreation);

        ObjectFactory factory = new ObjectFactory();
        SaveClientRequest request = factory.createSaveClientRequest();

        com.sei.generate.ClientTO clientTO = new com.sei.generate.ClientTO();
        clientTO.setId(to.getId());
        clientTO.setPrenom(to.getPrenom());
        clientTO.setNom(to.getNom());

        request.setArg0(clientTO);
        request.setArg1(isCreation);

        SaveClientResponse response = (SaveClientResponse) webServiceTemplate.marshalSendAndReceive(request);

        //add flash message
        if (isCreation) {
            messages.put("success", messageSource.getMessage("liste.action.creerSucces", null, null));
        } else {
            messages.put("success", messageSource.getMessage("liste.action.modifierSucces", null, null));
        }
        redirectAttributes.addFlashAttribute("messages", messages);

        return true;
    }

    @Override
    protected void deleteTO(Long id, ModelMap model, RedirectAttributes redirectAttributes) {
        //clientService.deleteClientByClientId(id);

        //ClientWebService service = new ClientWebService() ;
        //Get the web service proxy or port from the web service client factory. This port is nothing but the stub for the web service created.
        //ClientWebServ servicePort = service.getClientWebServicePort();
        //servicePort.deleteClientById(id);

        ObjectFactory factory = new ObjectFactory();
        DeleteClientByIdRequest request = factory.createDeleteClientByIdRequest();
        request.setArg0(id);
        DeleteClientByIdResponse response = (DeleteClientByIdResponse) webServiceTemplate.marshalSendAndReceive(request);

        //add flash message
        messages.put("success", messageSource.getMessage("liste.action.supprimerSucces", null, null));
        redirectAttributes.addFlashAttribute("messages", messages);
    }


    //override necessaire que pour la partie WS Soap pour gerer le sous repertoire wspages
    @Override
    public String delete(@PathVariable Long id, ModelMap model, RedirectAttributes redirectAttributes) {
        deleteTO(id, model, redirectAttributes);
        return "redirect:../.." + REQUEST_MAPPING;
    }

}
