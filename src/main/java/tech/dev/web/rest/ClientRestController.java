package tech.dev.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.dev.service.ClientService;
import tech.dev.to.ClientTO;
import tech.dev.web.common.base.AbstractRESTController;

import java.util.List;

@RestController
@RequestMapping(value= ClientRestController.REQUEST_MAPPING)
public class ClientRestController extends AbstractRESTController<ClientTO> {

    public static final String REQUEST_MAPPING = "/clients";

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected List<ClientTO> initializeIndexTO() {
        //returne la liste des TO
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }

    @Override
    protected ClientTO initializeShowTO(Long id) {
        //with TO
        ClientTO client = clientService.findByIdFillTO(id);
        return client;
    }

    @Override
    protected List<ClientTO> saveTO(ClientTO to, boolean isCreation) {
        clientService.saveTO(to, isCreation);
        //returne la liste des TO apres la creation/modification
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }

    @Override
    protected List<ClientTO> deleteTO(Long id) {
        clientService.deleteClientByClientId(id);
        //returne la liste des TO apres la suppression
        List<ClientTO> clients = clientService.findAllFillTO();
        return clients;
    }
}