package tech.dev.web.jaxws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import tech.dev.to.ClientTO;
import tech.dev.web.common.base.AbstractSearchEditController;
import tech.dev.web.formulaires.ClientForm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
@RequestMapping(TestClientRestController.REQUEST_MAPPING)
@SessionAttributes({
        TestClientRestController.CLIENT_FORM
})
public class TestClientRestController extends AbstractSearchEditController<ClientTO, ClientForm> {
    public static final String REQUEST_MAPPING = "/rsclient";

    //vues dans le repertoire rspages
    private static final String ROOT_VUE = "rspages/rsclient";

    public static final String CLIENT= "client";
    public static final String CLIENT_FORM = "clientForm";
    public static final String CLIENTS_LISTE = "listeClients";

    public static final String REST_SERVICE_URI = "http://localhost:8090/clients";
    //Heroku REST API URL
    //public static final String REST_SERVICE_URI = "https://sample-crud-springboot.herokuapp.com/clients";

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

        List<ClientTO> clients = new ArrayList<ClientTO>();
        RestTemplate restTemplate = new RestTemplate();

        List<LinkedHashMap<String, Object>> clientsMap  = restTemplate.getForObject(REST_SERVICE_URI, List.class);

        if(clientsMap!=null) {
            for (LinkedHashMap<String, Object> map : clientsMap) {
                ClientTO client = new ClientTO();
                client.setId(Long.parseLong(map.get("id").toString()));
                client.setPrenom(map.get("prenom").toString());
                client.setNom(map.get("nom").toString());
                //add to list
                clients.add(client);
            }
        }
        model.put(CLIENTS_LISTE, clients);
    }

    @Override
    protected void initializeShowTO(Long id, ClientForm form, ModelMap model) {
        //ClientTO client = clientService.findByIdFillTO(id);

        // on definie redirectUrl(../rsclient) en utilisant REQUEST_MAPPING( /rsclient )
        // et pas ROOT_VUE( rspages/rsclient ) pour gerer un sous repertoire rspages
        // donc getRedirectAfterEdit() dans le controlleur de base est decalé d'un pas
        form.setRedirectUrl(getRootLink());

        RestTemplate restTemplate = new RestTemplate();
        ClientTO client  = restTemplate.getForObject(REST_SERVICE_URI + "/" +id, ClientTO.class);

        model.put(CLIENT, client);
    }

    @Override
    protected void initializeNewTO(ClientForm form, ModelMap model) {

        // on definie redirectUrl(../rsclient) en utilisant REQUEST_MAPPING( /rsclient )
        // et pas ROOT_VUE( rspages/rsclient ) pour gerer un sous repertoire rspages
        // donc getRedirectAfterEdit() dans le controlleur de base est decalé d'un pas
        form.setRedirectUrl(getRootLink());

        fillModel(form, model, null);
    }

    @Override
    protected void initializeEditTO(Long id, ClientForm form, ModelMap model) {
        try {
            //ClientTO client = clientService.findByIdFillTO(id);

            // on definie redirectUrl(../rsclient) en utilisant REQUEST_MAPPING( /rsclient )
            // et pas ROOT_VUE( rspages/rsclient ) pour gerer un sous repertoire rspages
            // donc getRedirectAfterEdit() dans le controlleur de base est decalé d'un pas
            form.setRedirectUrl(getRootLink());

            RestTemplate restTemplate = new RestTemplate();
            ClientTO client  = restTemplate.getForObject(REST_SERVICE_URI + "/" +id, ClientTO.class);

            fillModel(form, model, client);

        } catch (Exception e) {
        }
    }

    private void fillModel(ClientForm form, ModelMap model, ClientTO to) {
        form.initForm(to);
        model.put(CLIENT_FORM, form);
    }

    @Override
    protected boolean saveTO(ClientTO to, ModelMap model, boolean isCreation) {
        //clientService.saveTO(to, isCreation);

        RestTemplate restTemplate = new RestTemplate();
        if (isCreation) {
            restTemplate.postForLocation(REST_SERVICE_URI + "/new", to, ClientTO.class);
        } else {
            restTemplate.postForLocation(REST_SERVICE_URI + "/" +to.getId() + "/edit", to, ClientTO.class);
        }

        return true;
    }

    @Override
    protected void deleteTO(Long id, ModelMap model) {
        //clientService.deleteClientByClientId(id);

        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.delete(REST_SERVICE_URI+"/clients/"+id+"/delete");

        //on fait la suppresion avec un HTTP GET et pas evec un HTTP DELETE
        restTemplate.getForObject(REST_SERVICE_URI + "/" + id + "/delete", List.class);
    }

    //override necessaire que pour la partie REST pour gerer le sous repertoire rspages
    @Override
    public String delete(@PathVariable Long id, ModelMap model) {
        deleteTO(id, model);
        return "redirect:../.." + REQUEST_MAPPING;
    }

}

