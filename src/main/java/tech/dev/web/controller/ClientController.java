package tech.dev.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.dev.service.ClientService;
import tech.dev.to.ClientTO;
import tech.dev.web.common.base.AbstractSearchEditController;
import tech.dev.web.formulaires.ClientForm;

import java.util.List;

@Controller
@RequestMapping(ClientController.REQUEST_MAPPING)
@SessionAttributes({
        ClientController.CLIENT_FORM
})
public class ClientController extends AbstractSearchEditController<ClientTO, ClientForm> {

    public static final String REQUEST_MAPPING = "/client";
    private static final String ROOT_VUE = "client";

    public static final String CLIENT= "client";
    public static final String CLIENT_FORM = "clientForm";
    public static final String CLIENTS_LISTE = "listeClients";

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected String getRootView() {
        return ROOT_VUE;
    }

    @Override
    protected void initializeIndexTO(ModelMap model) {
        //liste des TO
        List<ClientTO> clients = clientService.findAllFillTO();
        model.put(CLIENTS_LISTE, clients);
    }

    @Override
    protected void initializeShowTO(Long id, ClientForm form, ModelMap model) {
        ClientTO client = clientService.findByIdFillTO(id);
        model.put(CLIENT, client);
    }

    @Override
    protected void initializeNewTO(ClientForm form, ModelMap model) {
        fillModel(form, model, null);
    }

    @Override
    protected void initializeEditTO(Long id, ClientForm form, ModelMap model) {
        try {
            ClientTO client = clientService.findByIdFillTO(id);
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
        clientService.saveTO(to, isCreation);
        return true;
    }

    @Override
    protected void deleteTO(Long id, ModelMap model) {
        clientService.deleteClientByClientId(id);
    }

}